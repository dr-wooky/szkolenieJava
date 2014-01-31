package pl.training;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.training.bank.entity.Client;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Sender sender = context.getBean(Sender.class);
        sender.send(new Client("Spring", "Spring"));
    }
}
