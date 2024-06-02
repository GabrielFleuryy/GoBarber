package com.ms.login.record;

import com.ms.login.model.Barber;

public record DataToListBarber(Long id, String name, String userName, String style, String phoneNumber) {
    public DataToListBarber(Barber barber){
        this(barber.getId(), barber.getName(), barber.getUsername(), barber.getStyle(), barber.getPhoneNumber());
    }

}
