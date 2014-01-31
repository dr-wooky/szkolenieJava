package pl.training.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.training.bank.entity.Client;
import pl.training.bank.service.repository.Clients;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@Service
public class JmsClients implements MessageListener{

    private Clients clients;

    @Autowired
    public JmsClients(Clients clients) {
        this.clients = clients;
    }

    @Override
    public void onMessage(Message message) {

        try {
            Client client = message.getBody(Client.class);
            clients.save(client);
        } catch (JMSException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
