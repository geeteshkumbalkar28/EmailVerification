package com.email.email.Repo;

import com.email.email.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
    public Optional<User> findByEmail(String email);

    public Optional<User> findByOtp(String otp);
}
