package client_task_management.controller;
import jakarta.validation.Valid;
import client_task_management.entity.Client;
import client_task_management.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@CrossOrigin(origins = "*")

@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public Client createClient(@Valid @RequestBody Client client) {
        return clientService.createClient(client);
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {

        Client existingClient = clientService.getClientById(id);

        existingClient.setName(client.getName());
        existingClient.setEmail(client.getEmail());
        existingClient.setCompany(client.getCompany());
        existingClient.setPhone(client.getPhone());

        return clientService.createClient(existingClient);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }
}