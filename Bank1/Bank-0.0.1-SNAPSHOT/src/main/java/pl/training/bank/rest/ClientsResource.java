package pl.training.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import pl.training.bank.BankException;
import pl.training.bank.entity.Client;
import pl.training.bank.service.repository.Clients;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("clients")
@RestService
public class ClientsResource {

    private Clients clients;

    @Autowired
    public ClientsResource(Clients clients) {
        this.clients = clients;
    }

    @GET
    public List<Client> getAll() {
        return clients.findAll();
    }

    @GET
    @Path("{id:\\d+}")
    public Client get(@PathParam("id") Long id) {
        try {
            return clients.getById(id);
        } catch (BankException ex) {
            return null;
        }
    }

    @POST
    public Response add(Client client) {
        clients.save(client);
        return Response.created(URI.create("" + client.getId())).build();
    }

    @PUT
    @Path("{id:\\d+}")
    public Response update(@PathParam("id") Long id, Client client) {
        if (id == client.getId()) {
            clients.save(client);
            return Response.noContent().build();
        } else {
            throw new IllegalArgumentException();
        }
    }

    @DELETE
    @Path("{id:\\d+}")
    public Response delete(@PathParam("id") Long id) {
        clients.delete(id);
        return Response.noContent().build();
    }
}
