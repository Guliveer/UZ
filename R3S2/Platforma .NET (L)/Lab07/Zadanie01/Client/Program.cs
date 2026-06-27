using Grpc.Net.Client;
using Zadanie01.Service;

using var channel = GrpcChannel.ForAddress("https://localhost:7001");
var client = new RandomNumberService.RandomNumberServiceClient(channel);

Console.WriteLine("Zadanie 01 - gRPC Random Number Client");
Console.WriteLine("Requesting 10 random numbers between 1 and 100...\n");

var request = new NumberRequest { Count = 10, Min = 1, Max = 100 };
using var call = client.GenerateNumbers(request);

int index = 1;
while (await call.ResponseStream.MoveNext(CancellationToken.None))
{
    Console.WriteLine($"  [{index++:D2}] Received: {call.ResponseStream.Current.Value}");
}

Console.WriteLine("\nStream complete.");
