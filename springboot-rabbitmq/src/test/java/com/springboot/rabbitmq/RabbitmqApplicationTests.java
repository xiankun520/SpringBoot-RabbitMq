package com.springboot.rabbitmq;

import com.springboot.rabbitmq.Pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class RabbitmqApplicationTests {

//注入rabbitmq的工具接口,实现对交换机所绑定的消息队列进行发送消息
    @Autowired
    private RabbitTemplate rabbitTemplate;
//rabbitmq的管理组件，实现交换机和消息队列的创建及绑定
    @Autowired
    private AmqpAdmin amqpAdmin;
    @Test
    void contextLoads() {

    }


    //测试rabbitTemplate
    @Test
    void rabbitmqTemplateTest(){
        //Message需要自己构造一个消息体和消息头
//        rabbitTemplate.send(exchang,route-key,Message);

        //传入object默认当成消息体，只需要传入对象，自动序列化发送给rabbitmq

        //自动配置设置MessageConverter为 Jackson2JsonMessageConverter
        User user=new User(1,"夏沫","5201314");
        Map<String,Object> map=new HashMap<>();
        map.put("1",5201314);
        rabbitTemplate.convertAndSend("xiamo","xiamo",user);

    }
    //测试AmqpAdmin
    @Test
    void rabbitmqAmqpAdminTest(){
       //创建直连交换机(xiamo) 1.DirectExchange  2.FanoutExchang  3.TopicExchange
        amqpAdmin.declareExchange(new DirectExchange("xiamo"));

        //创建消息队列
        amqpAdmin.declareQueue(new Queue("xiamo",true));

        //交换机与消息队列进行绑定 Binding 对象
        amqpAdmin.declareBinding(new Binding("xiamo",
                                 Binding.DestinationType.QUEUE,
                                 "xiamo",
                                "xiamo",
                                 null));


    }
}
