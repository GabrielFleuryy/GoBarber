package com.ms.login.model;

import com.ms.login.record.ListBarberDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "barbers")
@Data
@NoArgsConstructor
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long barberId;

    @Column
    @NotBlank
    private String name;

    @Column(unique = true)
    @NotBlank
    private String instagram;

    @Column
    private LocalDate birthday;

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

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ListBarberDTO updateBarber(ListBarberDTO data) {
        if(data.name() != null && !data.name().isEmpty()){
            this.setName(data.name());
        }

        if(data.phoneNumber() != null && !data.phoneNumber().isEmpty()){
            this.setPhoneNumber(data.phoneNumber());
        }

        if(data.instagram() != null && !data.instagram().isEmpty()){
            this.setInstagram(data.instagram());
        }

        if(data.style() != null && !data.style().isEmpty()){
            this.setStyle(data.style());
        }

        this.updatedAt = LocalDateTime.now();

        return new ListBarberDTO(this.getBarberId(), this.getName(), this.getInstagram(), this.getStyle(), this.getPhoneNumber());
    }
}
