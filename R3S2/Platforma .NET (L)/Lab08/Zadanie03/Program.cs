using Microsoft.SemanticKernel;
using Microsoft.SemanticKernel.ChatCompletion;
using Microsoft.SemanticKernel.Connectors.Ollama;

#pragma warning disable SKEXP0070

const string ModelId = "llama3.2";
const string Endpoint = "http://localhost:11434";

var kernel = Kernel.CreateBuilder()
    .AddOllamaChatCompletion(ModelId, new Uri(Endpoint))
    .Build();

kernel.Plugins.AddFromObject(new SmartHomePlugin(), "SmartHome");

var chat = kernel.GetRequiredService<IChatCompletionService>();
var settings = new OllamaPromptExecutionSettings
{
    FunctionChoiceBehavior = FunctionChoiceBehavior.Auto()
};

var history = new ChatHistory(
    "Jesteś inteligentnym asystentem systemu domowej automatyki. " +
    "Gdy użytkownik wyda polecenie lub zada pytanie dotyczące domu, " +
    "wywołaj odpowiednią funkcję i podaj wynik. Odpowiadaj krótko i po polsku.");

Console.WriteLine("=== System Domowej Automatyki (Semantic Kernel) ===");
Console.WriteLine("Przykłady: 'Która jest godzina?', 'Włącz światła w salonie',");
Console.WriteLine("           'Jaka jest temperatura?', 'Ustaw termostat na 22 stopnie'\n");

while (true)
{
    Console.Write("Polecenie: ");
    var input = Console.ReadLine();
    if (string.IsNullOrWhiteSpace(input) || input.Equals("exit", StringComparison.OrdinalIgnoreCase))
        break;

    history.AddUserMessage(input);

    var response = await chat.GetChatMessageContentAsync(history, settings, kernel);
    Console.WriteLine($"Dom: {response.Content}\n");

    history.AddAssistantMessage(response.Content ?? string.Empty);
}
