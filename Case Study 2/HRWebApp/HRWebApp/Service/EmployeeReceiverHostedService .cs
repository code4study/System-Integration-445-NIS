using Microsoft.Extensions.Hosting;
using System.Threading.Tasks;
using System.Threading;

public class EmployeeReceiverHostedService : BackgroundService
{
    protected override Task ExecuteAsync(CancellationToken stoppingToken)
    {
        return Task.Run(() =>
        {
            var receiver = new EmployeeReceiver();
            receiver.StartReceiving();
        }, stoppingToken);
    }
}
