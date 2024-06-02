package com.ms.login.controller;

import com.ms.login.model.Barber;
import com.ms.login.model.User;
import com.ms.login.record.*;
import com.ms.login.repository.BarberRepository;
import com.ms.login.service.AuthService;
import com.ms.login.service.BarberService;
import com.ms.login.service.CustomerService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import lombok.extern.java.Log;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/barbers")
public class BarberController {
    @Autowired
    private BarberRepository barberRepository;

    @Autowired
    private BarberService barberService;

    @Autowired
    private AuthService authService;

    @PostMapping("/new-barber")
    @Transactional
    public ResponseEntity<DataToListBarber> newBarber(@RequestBody @Valid BarberRecord barberRecord, UriComponentsBuilder uriBuilder) {
        User user = authService.newUser(new LoginRecord(barberRecord.user()));

        Barber barber = new Barber();

        BeanUtils.copyProperties(barberRecord, barber);
        barber.setUser(user);

        var uri = uriBuilder.path("barbers/{id}").buildAndExpand(barber.getId()).toUri();

        return  ResponseEntity.created(uri).body(barberService.newBarber(barber));
    }

    @GetMapping("/get-barber/{id}")
    public ResponseEntity<DataToListBarber> getBarber(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(barberService.getBarber(id));
    }

    @GetMapping("/get-barbers")
    public ResponseEntity<List<Barber>> getBarber(){
        return ResponseEntity.status(HttpStatus.OK).body(barberRepository.findAll());
    }

    @PutMapping("update-barber")
    @Transactional
    public ResponseEntity<?> updateCustomer(@RequestBody DataToUpdateBarber barber){
        return ResponseEntity.ok(barberService.updateBarber(barber));
    }

    @DeleteMapping("delete-barber/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        barberService.deleteBarber(id);
        return ResponseEntity.noContent().build();
    }
}
