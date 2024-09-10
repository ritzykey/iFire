package com.iFire.webservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@ConfigurationProperties(prefix = "ifire")
@Configuration
public class iFireProperties {

    private Email email;

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public static record Email(
        String username,
        String password,
        String host,
        int port,
        String from,
        String clientHost
    ) {
    }
    
}
