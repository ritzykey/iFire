package com.ifire.webservice.user.dto;

import com.ifire.webservice.user.User;

import lombok.Data;

@Data
public class UserDTO {
    long id;

    String username;

    String email;

    String image;

    String fullName;

    public UserDTO(User user) {
        setId(user.getId());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setFullName(user.getFirstName() + " " + user.getLastName());
        setImage(user.getImage());
    }

    public String getImage() {
        return image == null ? "default.png" : image;
    }
}
