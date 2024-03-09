package br.com.camilaferreiranas.contactsmanager.exceptions;

public class ClientNotFound extends RuntimeException{

    public ClientNotFound(String message) {
        super(message);
    }
}
