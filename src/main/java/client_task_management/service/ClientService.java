package client_task_management.service;

import client_task_management.exception.ResourceNotFoundException;
import client_task_management.entity.Client;
import client_task_management.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client with ID " + id + " not found"));
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}