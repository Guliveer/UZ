namespace Zadanie08;

public class TodoTask
{
    public int Id { get; set; }
    public string Title { get; set; } = "";
    public string? Description { get; set; }
    public string Status { get; set; } = "pending";
    public int Priority { get; set; } = 1;
    public DateTime CreatedAt { get; set; }
    public DateTime? DueDate { get; set; }
}

public record CreateTaskRequest(string Title, string? Description, int Priority = 1, DateTime? DueDate = null);
public record UpdateTaskRequest(string? Title, string? Description, string? Status, int? Priority, DateTime? DueDate);

public class TaskRepository
{
    private readonly List<TodoTask> _tasks = new()
    {
        new() { Id = 1, Title = "Zainstaluj .NET SDK", Description = "Zainstaluj najnowszy .NET SDK", Status = "done", Priority = 1, CreatedAt = DateTime.Now.AddDays(-7) },
        new() { Id = 2, Title = "Naucz się Minimal API", Description = "Przejdź przez dokumentację Minimal API", Status = "in-progress", Priority = 2, CreatedAt = DateTime.Now.AddDays(-3) },
        new() { Id = 3, Title = "Zaimplementuj Lab07", Description = "Wszystkie 8 zadań", Status = "in-progress", Priority = 3, CreatedAt = DateTime.Now.AddDays(-1), DueDate = DateTime.Now.AddDays(2) }
    };

    private int _nextId = 4;

    public IReadOnlyList<TodoTask> GetAll() => _tasks.AsReadOnly();

    public TodoTask? GetById(int id) => _tasks.FirstOrDefault(t => t.Id == id);

    public IReadOnlyList<TodoTask> GetByStatus(string status) =>
        _tasks.Where(t => t.Status.Equals(status, StringComparison.OrdinalIgnoreCase)).ToList().AsReadOnly();

    public TodoTask Add(CreateTaskRequest req)
    {
        var task = new TodoTask
        {
            Id = _nextId++,
            Title = req.Title,
            Description = req.Description,
            Priority = req.Priority,
            DueDate = req.DueDate,
            CreatedAt = DateTime.Now
        };
        _tasks.Add(task);
        return task;
    }

    public TodoTask? Update(int id, UpdateTaskRequest req)
    {
        var task = _tasks.FirstOrDefault(t => t.Id == id);
        if (task is null) return null;

        if (req.Title is not null) task.Title = req.Title;
        if (req.Description is not null) task.Description = req.Description;
        if (req.Status is not null) task.Status = req.Status;
        if (req.Priority.HasValue) task.Priority = req.Priority.Value;
        if (req.DueDate.HasValue) task.DueDate = req.DueDate.Value;
        return task;
    }

    public bool Delete(int id)
    {
        var task = _tasks.FirstOrDefault(t => t.Id == id);
        if (task is null) return false;
        _tasks.Remove(task);
        return true;
    }
}
