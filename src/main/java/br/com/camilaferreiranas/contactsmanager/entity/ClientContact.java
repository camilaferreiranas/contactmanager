package br.com.camilaferreiranas.contactsmanager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class ClientContact {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String email;

    private String telephone;

    private StatusEnum status;

    private String description;

    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
}
