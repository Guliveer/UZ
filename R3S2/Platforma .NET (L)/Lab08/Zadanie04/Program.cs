using Microsoft.SemanticKernel;
using Microsoft.SemanticKernel.ChatCompletion;
using Microsoft.SemanticKernel.Connectors.Ollama;

#pragma warning disable SKEXP0070

const string ModelId = "llama3.2";
const string Endpoint = "http://localhost:11434";

var kernel = Kernel.CreateBuilder()
    .AddOllamaChatCompletion(ModelId, new Uri(Endpoint))
    .Build();

kernel.Plugins.AddFromObject(new ReservationPlugin(), "Rezerwacje");

var chat = kernel.GetRequiredService<IChatCompletionService>();
var settings = new OllamaPromptExecutionSettings
{
    FunctionChoiceBehavior = FunctionChoiceBehavior.Auto()
};

var history = new ChatHistory("""
    Jesteś uprzejmym asystentem pomagającym w rezerwacjach. Obsługujesz:
    - rezerwacje stolików w restauracji,
    - zakup biletów do kina,
    - rezerwacje biletów do teatru.

    Jeśli brakuje Ci informacji (np. imienia, terminu, liczby osób), zapytaj o nie.
    Przed dokonaniem rezerwacji potwierdź szczegóły z użytkownikiem.
    Odpowiadaj po polsku, krótko i uprzejmie.
    """);

Console.WriteLine("=== Asystent Rezerwacji (Semantic Kernel) ===");
Console.WriteLine("Mogę pomóc zarezerwować stolik w restauracji, bilety do kina lub teatru.");
Console.WriteLine("Wpisz 'moje rezerwacje' aby zobaczyć listę. Wpisz 'exit' aby zakończyć.\n");

while (true)
{
    Console.Write("Ty: ");
    var input = Console.ReadLine();
    if (string.IsNullOrWhiteSpace(input) || input.Equals("exit", StringComparison.OrdinalIgnoreCase))
        break;

    history.AddUserMessage(input);

    var response = await chat.GetChatMessageContentAsync(history, settings, kernel);
    Console.WriteLine($"Asystent: {response.Content}\n");

    history.AddAssistantMessage(response.Content ?? string.Empty);
}

Console.WriteLine("\nDziękuję za skorzystanie z asystenta rezerwacji!");
