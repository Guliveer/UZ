using Microsoft.SemanticKernel;
using Microsoft.SemanticKernel.ChatCompletion;
using Microsoft.SemanticKernel.Connectors.Ollama;

#pragma warning disable SKEXP0070

const string ModelId = "llama3.2";
const string Endpoint = "http://localhost:11434";

var kernel = Kernel.CreateBuilder()
    .AddOllamaChatCompletion(ModelId, new Uri(Endpoint))
    .Build();

var chat = kernel.GetRequiredService<IChatCompletionService>();
var history = new ChatHistory("Jesteś pomocnym asystentem. Odpowiadaj zwięźle po polsku.");

Console.WriteLine("=== Chat z LLM (Semantic Kernel + Ollama) ===");
Console.WriteLine($"Model: {ModelId}  |  Endpoint: {Endpoint}");
Console.WriteLine("Wpisz pytanie i naciśnij Enter. Wpisz 'exit' aby zakończyć.\n");

while (true)
{
    Console.Write("Ty: ");
    var input = Console.ReadLine();
    if (string.IsNullOrWhiteSpace(input) || input.Equals("exit", StringComparison.OrdinalIgnoreCase))
        break;

    history.AddUserMessage(input);

    Console.Write("Asystent: ");
    var response = await chat.GetChatMessageContentAsync(history);
    Console.WriteLine(response.Content);
    Console.WriteLine();

    history.AddAssistantMessage(response.Content ?? string.Empty);
}
