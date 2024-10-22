package com.ifire.webservice.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * UserUpdate
 */
public record UserUpdate(
        @NotBlank(message = "{iFire.constraints.Username.NotBlank}") @Size(min = 4, max = 255) String username) {
}