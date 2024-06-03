package com.ms.login.service;

import com.ms.login.model.Barber;
import com.ms.login.record.ListBarberDTO;
import com.ms.login.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BarberService {

    @Autowired
    private BarberRepository barberRepository;

    public ListBarberDTO newBarber(Barber barber){
        barber.setCreatedAt(LocalDateTime.now());
        barberRepository.save(barber);

        return new ListBarberDTO(barber);
    }

    public ListBarberDTO getBarber(Long id) {
        Barber barber = barberRepository.getReferenceById(id);

        return new ListBarberDTO(barber);
    }

    public ListBarberDTO updateBarber(ListBarberDTO data) {
        Barber barber = barberRepository.getReferenceById(data.id());

       return barber.updateBarber(data);
    }

    public void deleteBarber(Long id) {
        Barber barber = barberRepository.getReferenceById(id);

        barberRepository.deleteById(barber.getBarberId());
    }

}
