package com.ms.login.record;

import com.ms.login.model.User;

public record LoginDTO(String login, String password) {
    public LoginDTO(User user){
        this(user.getLogin(), user.getPassword());
    }
}
