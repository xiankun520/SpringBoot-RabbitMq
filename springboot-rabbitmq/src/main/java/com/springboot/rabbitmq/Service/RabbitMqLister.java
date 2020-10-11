package com.springboot.rabbitmq.Service;


import com.springboot.rabbitmq.Pojo.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqLister {

    @RabbitListener(queues = "xiamo")
    public void ListenQueue(User user){
        System.out.println("接受到xiamo消息队列的消息");
        System.out.println(user);
    }
//    @RabbitListener(queues = "xiamo")
//    public void ListenQueue01(Message message){
//        System.out.println("接受到xiamo消息队列的消息");
//        System.out.println(message.getBody());
//        System.out.println(message.getMessageProperties());
//    }
}
