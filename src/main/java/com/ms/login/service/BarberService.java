package com.ms.login.service;

import com.ms.login.model.Barber;
import com.ms.login.record.BarberRecord;
import com.ms.login.record.DataToListBarber;
import com.ms.login.record.DataToUpdateBarber;
import com.ms.login.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BarberService {

    @Autowired
    private BarberRepository barberRepository;

    public DataToListBarber newBarber(Barber barber){
        barber.setCreatedAt(LocalDateTime.now());
        barberRepository.save(barber);

        return new DataToListBarber(barber);
    }

    public DataToListBarber getBarber(Long id) {
        Barber barber = barberRepository.getReferenceById(id);

        return new DataToListBarber(barber);
    }

    public DataToListBarber updateBarber(DataToUpdateBarber data) {
        Barber barber = barberRepository.getReferenceById(data.id());

       return barber.updateData(data);
    }

    public void deleteBarber(Long id) {
        Barber barber = barberRepository.getReferenceById(id);

        barberRepository.deleteById(barber.getId());
    }

}
