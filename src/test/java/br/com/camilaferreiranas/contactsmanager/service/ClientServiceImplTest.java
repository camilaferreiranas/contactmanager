package br.com.camilaferreiranas.contactsmanager.service;

import br.com.camilaferreiranas.contactsmanager.entity.ClientContact;
import br.com.camilaferreiranas.contactsmanager.entity.StatusEnum;
import br.com.camilaferreiranas.contactsmanager.repository.ClientRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    Faker faker = new Faker();

    @Test
    public void shouldReturnListFromClients() {
        ClientContact clientContact = mockClientValues();
        List<ClientContact> listOfClients = new ArrayList<>();
        listOfClients.add(clientContact);
        Mockito.when(clientRepository.findAll()).thenReturn(listOfClients);
        List<ClientContact> list = clientService.findAllClients();
        Assertions.assertEquals(1, listOfClients.size());
        Assertions.assertEquals(1, list.size());

    }

    @Test
    public void shouldReturnClientWhenFindById() {
        ClientContact clientContact = mockClientValues();
        UUID id = UUID.randomUUID();
        Mockito.when(clientRepository.findById(id)).thenReturn(Optional.of(clientContact));
        Optional<ClientContact> clientFound = clientService.findClienById(id);
        Assertions.assertNotNull(clientContact);
        Assertions.assertNotNull(clientFound);
    }

    private ClientContact mockClientValues() {
        ClientContact clientContact = new ClientContact();
        clientContact.setId(UUID.randomUUID());
        clientContact.setName(faker.harryPotter().character());
        clientContact.setStatus(StatusEnum.CREATED);
        clientContact.setEmail(faker.harryPotter().book());
        clientContact.setDescription(faker.harryPotter().book());
        clientContact.setTelephone(faker.harryPotter().spell());
        return clientContact;
    }

}
