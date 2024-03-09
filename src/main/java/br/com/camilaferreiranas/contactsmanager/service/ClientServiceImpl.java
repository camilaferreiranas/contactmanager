package br.com.camilaferreiranas.contactsmanager.service;

import br.com.camilaferreiranas.contactsmanager.entity.ClientContact;
import br.com.camilaferreiranas.contactsmanager.entity.StatusEnum;
import br.com.camilaferreiranas.contactsmanager.exceptions.ClientNotFound;
import br.com.camilaferreiranas.contactsmanager.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    @Override
    public List<ClientContact> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<ClientContact> findClienById(UUID id) {
        return clientRepository.findById(id);
    }

    @Override
    public ClientContact saveClient(ClientContact clientContact) {
        ClientContact clientContactToSave = new ClientContact();
        clientContactToSave.setName(clientContact.getName());
        clientContactToSave.setEmail(clientContact.getEmail());
        clientContactToSave.setDescription(clientContact.getDescription());
        clientContactToSave.setTelephone(clientContact.getTelephone());
        clientContactToSave.setDateCreated(LocalDateTime.now());
        clientContactToSave.setStatus(StatusEnum.CREATED);
        return clientRepository.save(clientContactToSave);
    }

    @Override
    public ClientContact changeStatusOfClient(UUID id, ClientContact clientContact) {
        ClientContact clientContactToSave = clientRepository.findById(id).orElseThrow(() -> new ClientNotFound("This client doesnt exist."));
        clientContactToSave.setStatus(clientContact.getStatus());
        clientContactToSave.setDateUpdated(LocalDateTime.now());
        return clientRepository.save(clientContactToSave);
    }


}
