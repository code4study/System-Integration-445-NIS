using HRWebApp.Controllers;
using RabbitMQ.Client;
using RabbitMQ.Client.Events;
using System;
using System.Text;

public class EmployeeReceiver
{
    PersonalsController _personalsController = new PersonalsController();
    public void StartReceiving()
    {
        // 1. Khởi tạo connection factory
        var factory = new ConnectionFactory() { HostName = "localhost", Port=5672 };

        // 2. Tạo kết nối
        var connection = factory.CreateConnection();
        try
        {
            // 3. Tạo channel để giao tiếp với RabbitMQ
            var channel = connection.CreateModel();

            // 4. Khai báo hàng đợi (queue) nếu chưa tồn tại
            channel.QueueDeclare(queue: "AP",
                                 durable: false,
                                 exclusive: false,
                                 autoDelete: false,
                                 arguments: null);

            // 5. Tạo consumer để lắng nghe message từ queue
            var consumer = new EventingBasicConsumer(channel);
            consumer.Received += (model, ea) =>
            {
                var body = ea.Body.ToArray();
                var message = Encoding.UTF8.GetString(body);
                Console.WriteLine(" [x] Received: " + message);
                // Gọi phương thức xử lý message ở đây
                _personalsController.CreatePersonalFromMessageFromEmployee(message);

                // TODO: Tách chuỗi và tạo personal tương ứng ở đây nếu cần
            };


            // 6. Bắt đầu tiêu thụ message
            channel.BasicConsume(queue: "AP",
                                 autoAck: true,
                                 consumer: consumer);

            Console.WriteLine(" [*] Đang lắng nghe queue 'AP'. Nhấn Enter để thoát...");
            Console.ReadLine();
        }
        finally
        {
            connection.Close();
            connection.Dispose();
        }
    }
}
