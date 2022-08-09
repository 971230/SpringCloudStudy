package com.longj.shardingtest;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ShardingtestApplicationTests {

    /**
     * RabbitMQ测试代码,需开启消息队列服务测试
     */
    @Test
    public void RabbitMqTest() {
        //使用ConnectionFactory来创建连接
        ConnectionFactory factory = new ConnectionFactory();

        //设定连接信息，基操
        factory.setHost("192.168.0.12");
        factory.setPort(5672);  //注意这里写5672，是amqp协议端口
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("/test");

        //创建连接
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {   //通过Connection创建新的Channel
            //声明队列，如果此队列不存在，会自动创建
            /*
            queue：队列的名称（默认创建后routingKey和队列名称一致）
            durable：是否持久化。
            exclusive：是否排他，如果一个队列被声明为排他队列，该队列仅对首次声明它的连接可见，并在连接断开时自动删除。排他队列是基于Connection可见，同一个Connection的不同Channel是可以同时访问同一个连接创建的排他队列，并且，如果一个Connection已经声明了一个排他队列，其他的Connection是不允许建立同名的排他队列的，即使该队列是持久化的，一旦Connection关闭或者客户端退出，该排他队列都会自动被删除。
            autoDelete：是否自动删除。
            arguments：设置队列的其他一些参数，这里我们暂时不需要什么其他参数。
            */
            channel.queueDeclare("yyds", false, false, false, null);
            //将队列绑定到交换机
            /*
            queue：需要绑定的队列名称。
            exchange：需要绑定的交换机名称。
            routingKey：路由键
            */
            channel.queueBind("yyds", "amq.direct", "my-yyds");
            //发布新的消息，注意消息需要转换为byte[]
            /*
            exchange: 对应的Exchange名称，我们这里就使用第二个直连交换机。
            routingKey：这里我们填写绑定时指定的routingKey，其实和之前在管理页面操作一样。
            props：其他的配置。
            body：消息本体。
            */
            channel.basicPublish("amq.direct", "yyds", null, "Hello World!".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
