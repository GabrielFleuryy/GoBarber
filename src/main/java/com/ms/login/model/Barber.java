package com.ms.login.model;

import com.ms.login.record.BarberRecord;
import com.ms.login.record.DataToListBarber;
import com.ms.login.record.DataToUpdateBarber;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "barbers")
@Data
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String username;

    @Column
    private LocalDate birthday;

    @Column(unique = true)
    @Length(max = 60)
    @NotBlank
    private String email;

    @Column
    @NotBlank
    @Length(max = 60)
    private String password;

    @Column(unique = true)
    @NotBlank
    @Length(max = 20)
    private String phoneNumber;

    @Column
    @NotBlank
    @Length(max = 20)
    private String style;

    @Column
    @NotBlank
    private String role;

    @Column
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime createdAt;

    public Barber() {

    }

    public DataToListBarber updateData(DataToUpdateBarber data) {
        if(data.name() != null && !data.name().isEmpty()){
            this.setName(data.name());
        }

        if(data.phoneNumber() != null && !data.phoneNumber().isEmpty()){
            this.setPhoneNumber(data.phoneNumber());
        }

        if(data.username() != null && !data.username().isEmpty()){
            this.setUsername(data.username());
        }

        if(data.style() != null && !data.style().isEmpty()){
            this.setStyle(data.style());
        }

        this.updatedAt = LocalDateTime.now();

        return new DataToListBarber(this.getId(), this.getName(), this.getUsername(), this.getStyle(), this.getPhoneNumber());
    }
}
