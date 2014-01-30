package pl.training.bank.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.training.bank.Bank;
import pl.training.bank.entity.Address;
import pl.training.bank.entity.Client;

@Controller
public class Clients {

    private Bank bank;

    @Autowired
    public Clients(Bank bank) {
        this.bank = bank;
    }

    @RequestMapping(value = "addClient", method = RequestMethod.GET)
    public ModelAndView prepareNewClientForm() {
        Client client = new Client();
        client.addAddress(new Address());
        client.addAddress(new Address());
        ModelAndView modelAndView = new ModelAndView("newClientForm");
        modelAndView.addObject(client);
        return modelAndView;
    }
}
