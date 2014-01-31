package pl.training.bank.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.training.bank.Bank;
import pl.training.bank.entity.Address;
import pl.training.bank.entity.Client;
import pl.training.bank.service.CreateAccount;

import javax.validation.Valid;

@Controller
public class ClientsController {

    private Bank bank;
    private PasswordEncoder passwordEncoder;
    private TaskExecutor taskExecutor;

    @Autowired
    public ClientsController(Bank bank, PasswordEncoder encoder, TaskExecutor taskExecutor) {
        this.bank = bank;
        this.passwordEncoder = encoder;
        this.taskExecutor = taskExecutor;
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

    @RequestMapping(value = "addClient", method = RequestMethod.POST)
    public String saveClient(@Valid Client client, BindingResult result) {
        if (result.hasErrors()) {
            return "newClientForm";
        }
        String encodedPassword = passwordEncoder.encode(client.getPassword());
        client.setPassword(encodedPassword);
        client.setRole("ROLE_ADMIN");
        bank.addClient(client);
        CreateAccount task = new CreateAccount(bank, client);
        taskExecutor.execute(task);
        return "redirect:home.html";
    }
}
