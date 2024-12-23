package com.capgemini.wsb.fitnesstracker.user.api;

import com.capgemini.wsb.fitnesstracker.exception.api.NotFoundException;

/**
 * Exception indicating that the {@link User} was not found.
 */
@SuppressWarnings("squid:S110")
public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(String message) { // Zmieniono na public
        super(message);
    }

    public UserNotFoundException(Long id) {
        this("User with ID=%s was not found".formatted(id));
    }

}
