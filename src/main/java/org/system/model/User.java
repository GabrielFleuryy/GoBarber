package org.system.model;

import jakarta.persistence.*;
import org.system.record.DataListUser;
import org.system.record.DataUpdateUser;
import org.system.record.UserRecord;

import java.time.LocalDate;

@Entity(name = "User")
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "email")
    @Column(unique = true)
    private String email;

    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "birthday")
    private LocalDate birthday;

    @JoinColumn(name = "phone_number")
    @Column(unique = true)
    private String phoneNumber;

    @JoinColumn(name = "password")
    private String password;

    @JoinColumn(name = "role")
    private String role;

    public User() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public User(UserRecord newUser){
        this.setName(newUser.name());
        this.setEmail(newUser.email());
        this.setPassword(newUser.password());
        this.setRole(newUser.role());
        this.setBirthday(newUser.birthday());
        this.setPhoneNumber(newUser.phoneNumber());
    }

    public DataListUser updateData(DataUpdateUser data) {
        if(data.name() != null && !data.name().isEmpty()){
            this.setName(data.name());
        }

        if(data.phoneNumber() != null && !data.phoneNumber().isEmpty()){
            this.setPhoneNumber(data.phoneNumber());
        }
        return new DataListUser(this.getId(), this.getName(), this.getPhoneNumber());
    }
}
