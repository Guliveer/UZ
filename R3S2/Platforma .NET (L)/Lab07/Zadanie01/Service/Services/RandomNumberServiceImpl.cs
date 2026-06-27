using Grpc.Core;

namespace Zadanie01.Service.Services;

public class RandomNumberServiceImpl : RandomNumberService.RandomNumberServiceBase
{
    private readonly Random _random = new();

    public override async Task GenerateNumbers(NumberRequest request, IServerStreamWriter<NumberResponse> responseStream, ServerCallContext context)
    {
        int count = request.Count > 0 ? request.Count : 10;
        int min = request.Min;
        int max = request.Max > min ? request.Max : min + 100;

        for (int i = 0; i < count && !context.CancellationToken.IsCancellationRequested; i++)
        {
            await responseStream.WriteAsync(new NumberResponse { Value = _random.Next(min, max) });
            await Task.Delay(200, context.CancellationToken);
        }
    }
}
