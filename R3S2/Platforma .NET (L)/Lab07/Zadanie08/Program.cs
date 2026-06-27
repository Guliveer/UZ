using Scalar.AspNetCore;
using Zadanie08;

var builder = WebApplication.CreateBuilder(args);
builder.Services.AddOpenApi();
builder.Services.AddSingleton<TaskRepository>();

var app = builder.Build();

app.MapOpenApi();
app.MapScalarApiReference(options =>
{
    options.Title = "Zadanie 08 - Task API";
    options.Theme = ScalarTheme.Purple;
});
app.UseHttpsRedirection();

var tasks = app.MapGroup("/api/tasks").WithTags("Tasks");

tasks.MapGet("/", (TaskRepository repo) =>
    TypedResults.Ok(repo.GetAll()))
    .WithName("GetAllTasks")
    .WithSummary("Pobierz wszystkie zadania");

tasks.MapGet("/{id:int}", (int id, TaskRepository repo) =>
    repo.GetById(id) is { } task
        ? Results.Ok(task)
        : Results.NotFound(new { error = $"Zadanie o id={id} nie istnieje" }))
    .WithName("GetTaskById")
    .WithSummary("Pobierz zadanie po ID");

tasks.MapGet("/status/{status}", (string status, TaskRepository repo) =>
    TypedResults.Ok(repo.GetByStatus(status)))
    .WithName("GetTasksByStatus")
    .WithSummary("Pobierz zadania według statusu (pending/in-progress/done)");

tasks.MapPost("/", (CreateTaskRequest request, TaskRepository repo) =>
{
    if (string.IsNullOrWhiteSpace(request.Title))
        return Results.BadRequest(new { error = "Tytuł jest wymagany" });
    var task = repo.Add(request);
    return Results.Created($"/api/tasks/{task.Id}", task);
})
.WithName("CreateTask")
.WithSummary("Utwórz nowe zadanie");

tasks.MapPut("/{id:int}", (int id, UpdateTaskRequest request, TaskRepository repo) =>
{
    var task = repo.Update(id, request);
    return task is null
        ? Results.NotFound(new { error = $"Zadanie o id={id} nie istnieje" })
        : Results.Ok(task);
})
.WithName("UpdateTask")
.WithSummary("Zaktualizuj zadanie");

tasks.MapDelete("/{id:int}", (int id, TaskRepository repo) =>
    repo.Delete(id)
        ? Results.NoContent()
        : Results.NotFound(new { error = $"Zadanie o id={id} nie istnieje" }))
    .WithName("DeleteTask")
    .WithSummary("Usuń zadanie");

app.Run();
