package pl.training.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import pl.training.bank.entity.Client;
import pl.training.bank.service.repository.Clients;

import javax.ws.rs.Path;
import java.util.List;

@Path("clients")
@RestService
public class ClientsResource {

    private Clients clients;

    @Autowired
    public ClientsResource(Clients clients) {
        this.clients = clients;
    }

    public List<Client> getAll() {
        return clients.findAll();
    }
}
