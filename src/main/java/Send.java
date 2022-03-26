
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {
    public final static String EXCHANGE_NAME = "My Exchange";
    private final static String QUEUE_NAME = "q1";
    public static void main(String[] args)  throws IOException, TimeoutException{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("34.228.74.99");
        factory.setUsername("eua");
        factory.setPassword("distr123!");

        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();

//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        String message = "WTF!";
//        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//        System.out.println(" [x] Sent '" + message + "'");

        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String routingKey = "My Topic";
        String message = "Hello";

        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
        channel.basicPublish(EXCHANGE_NAME, "abc", null, "Hello from sender".getBytes(StandardCharsets.UTF_8));
        System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

        channel.close();
        connection.close();

    }
}