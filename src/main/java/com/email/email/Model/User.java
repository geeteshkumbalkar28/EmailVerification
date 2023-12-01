package com.email.email.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Boolean vStatus;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", vStatus=" + vStatus +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", otp='" + otp + '\'' +
                '}';
    }

    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "password",nullable = false)

    private String password;

    private String otp;

    public String getName() {
        return name;
    }

    public String getOtp() {
        return otp;
    }



    public Boolean getvStatus() {
        return vStatus;
    }

    public void setvStatus(Boolean vStatus) {
        this.vStatus = vStatus;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
