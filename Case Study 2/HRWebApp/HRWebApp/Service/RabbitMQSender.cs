using RabbitMQ.Client;
using System.Text;
using System;

public class RabbitMQSender
{
    public static void SendPersonalMessage(string jsonData)
    {
        var factory = new ConnectionFactory() { HostName = "localhost" };
         var connection = factory.CreateConnection();
         var channel = connection.CreateModel();

        channel.QueueDeclare(queue: "AE", durable: false, exclusive: false, autoDelete: false, arguments: null);

        var body = Encoding.UTF8.GetBytes(jsonData);
        channel.BasicPublish(exchange: "", routingKey: "AE", basicProperties: null, body: body);
        Console.WriteLine($"✅ Sent to queue AE: {jsonData}");
    }
}