package com.ms.login.record;

import com.ms.login.model.Barber;

public record ListBarberDTO(Long id, String name, String instagram, String style, String phoneNumber) {
    public ListBarberDTO(Barber barber){
        this(barber.getBarberId(), barber.getName(), barber.getInstagram(), barber.getStyle(), barber.getPhoneNumber());
    }

}
