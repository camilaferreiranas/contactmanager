package br.com.camilaferreiranas.contactsmanager.service;

import br.com.camilaferreiranas.contactsmanager.entity.ClientContact;
import br.com.camilaferreiranas.contactsmanager.entity.StatusEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface ClientService {

    List<ClientContact> findAllClients();
    Optional<ClientContact> findClienById(UUID id);

    ClientContact saveClient(ClientContact clientContact);

    ClientContact changeStatusOfClient(UUID id, ClientContact clientContact);
}
