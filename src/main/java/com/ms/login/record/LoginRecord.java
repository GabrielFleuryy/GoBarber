package com.ms.login.record;

import com.ms.login.model.User;

public record LoginRecord(String login, String password) {
    public LoginRecord(User user){
        this(user.getLogin(), user.getPassword());
    }
}
