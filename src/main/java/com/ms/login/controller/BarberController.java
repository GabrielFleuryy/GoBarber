package com.ms.login.controller;

import com.ms.login.model.Barber;
import com.ms.login.record.*;
import com.ms.login.repository.BarberRepository;
import com.ms.login.service.AuthService;
import com.ms.login.service.BarberService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

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
    public ResponseEntity<ListBarberDTO> newBarber(@RequestBody @Valid BarberDTO barberDTO, UriComponentsBuilder uriBuilder) {
        var user = authService.newUser(new LoginDTO(barberDTO.user()));

        var barber = new Barber();

        BeanUtils.copyProperties(barberDTO, barber);

        barber.setUser(user);

        var uri = uriBuilder.path("barbers/{id}").buildAndExpand(barber.getBarberId()).toUri();

        return  ResponseEntity.created(uri).body(barberService.newBarber(barber));
    }

    @GetMapping("/get-barber/{id}")
    public ResponseEntity<ListBarberDTO> getBarber(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(barberService.getBarber(id));
    }

    @GetMapping("/get-barbers")
    public ResponseEntity<List<Barber>> getBarber(){
        return ResponseEntity.status(HttpStatus.OK).body(barberRepository.findAll());
    }

    @PutMapping("update-barber")
    @Transactional
    public ResponseEntity<?> updateCustomer(@RequestBody ListBarberDTO barber){
        return ResponseEntity.ok(barberService.updateBarber(barber));
    }

    @DeleteMapping("delete-barber/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        barberService.deleteBarber(id);
        return ResponseEntity.noContent().build();
    }
}
