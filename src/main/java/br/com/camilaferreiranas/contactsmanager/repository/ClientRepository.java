package br.com.camilaferreiranas.contactsmanager.repository;

import br.com.camilaferreiranas.contactsmanager.entity.ClientContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<ClientContact, UUID> {
}
