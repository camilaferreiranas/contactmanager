package br.com.camilaferreiranas.contactsmanager.controller;

import br.com.camilaferreiranas.contactsmanager.entity.ClientContact;
import br.com.camilaferreiranas.contactsmanager.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;


    @GetMapping
    public ResponseEntity<List<ClientContact>> getAllClients() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAllClients());
    }

    @PostMapping
    public ResponseEntity<ClientContact> saveClient(@RequestBody ClientContact clientContact) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.saveClient(clientContact));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientContact> updateClientStatus(@PathVariable UUID id, @RequestBody ClientContact clientContact) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.changeStatusOfClient(id, clientContact));
    }
}
