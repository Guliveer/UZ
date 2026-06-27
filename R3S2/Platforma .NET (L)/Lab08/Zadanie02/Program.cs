using Microsoft.SemanticKernel;
using Microsoft.SemanticKernel.ChatCompletion;
using Microsoft.SemanticKernel.Connectors.Ollama;

#pragma warning disable SKEXP0070

const string ModelId = "llama3.2";
const string Endpoint = "http://localhost:11434";

var kernel = Kernel.CreateBuilder()
    .AddOllamaChatCompletion(ModelId, new Uri(Endpoint))
    .Build();

kernel.Plugins.AddFromObject(new MathPlugin(), "Matematyka");

var chat = kernel.GetRequiredService<IChatCompletionService>();
var settings = new OllamaPromptExecutionSettings
{
    FunctionChoiceBehavior = FunctionChoiceBehavior.Auto()
};

var history = new ChatHistory(
    "Jesteś asystentem matematycznym. Gdy użytkownik poda operację matematyczną, " +
    "wywołaj odpowiednią funkcję i podaj wynik. Odpowiadaj po polsku.");

Console.WriteLine("=== Rozpoznawanie poleceń matematycznych (SK Function Calling) ===");
Console.WriteLine("Przykłady: 'dodaj 5 do 6', 'ile to 12 minus 4?', 'pomnóż 7 przez 8'\n");

while (true)
{
    Console.Write("Polecenie: ");
    var input = Console.ReadLine();
    if (string.IsNullOrWhiteSpace(input) || input.Equals("exit", StringComparison.OrdinalIgnoreCase))
        break;

    history.AddUserMessage(input);

    var response = await chat.GetChatMessageContentAsync(history, settings, kernel);
    Console.WriteLine($"Wynik: {response.Content}\n");

    history.AddAssistantMessage(response.Content ?? string.Empty);
}
