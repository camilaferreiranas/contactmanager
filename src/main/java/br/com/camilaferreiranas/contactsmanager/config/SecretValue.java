package br.com.camilaferreiranas.contactsmanager.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecretValue {
    private String username;
    private String password;
    private String engine;
    private String host;
    private String port;
    private String dbname;
    private String dbInstanceIdentifier;
}
