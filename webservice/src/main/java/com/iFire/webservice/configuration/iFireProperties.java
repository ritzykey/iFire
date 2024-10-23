package com.ifire.webservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.Getter;

@ConfigurationProperties(prefix = "ifire")
@Configuration
public class iFireProperties {

    private Email email;

    @Getter
    private Storage storage = new Storage();

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
            String clientHost) {
    }

    @Data
    public static class Storage {

        String root = "uploads";

        String profile = "profile";

    }

}
