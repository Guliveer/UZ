using Zadanie01.Service.Services;

var builder = WebApplication.CreateBuilder(args);
builder.Services.AddGrpc();

var app = builder.Build();

app.MapGrpcService<RandomNumberServiceImpl>();
app.MapGet("/", () => "Zadanie 01 - gRPC Random Number Service. Use a gRPC client to connect.");

app.Run();
