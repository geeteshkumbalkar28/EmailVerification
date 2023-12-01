package com.email.email.Controller;

import com.email.email.Email.GEmailSender;
import com.email.email.Model.User;
import com.email.email.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mail")
@CrossOrigin
public class EmailController {
    @Autowired
    private UserRepo userRepo ;
     @Autowired
     private GEmailSender gEmailSender;
    @PostMapping("/Create")
    public ResponseEntity<?> Create(@RequestBody User user ) {
        try {
            if(userRepo.findByEmail(user.getEmail()).isPresent())throw new RuntimeException("Email AllReady Exist");
            user.setEmail(user.getEmail().toLowerCase());
            Boolean b = gEmailSender.sendEmail(user);
            if (b) {
                System.out.println("Email send successfully");
            }
            userRepo.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("otp sent onn your mail verify email and login");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    @GetMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestParam String otp ,@RequestParam String email){

        try {
            User user =userRepo.findByOtp(otp).orElseThrow(()->new RuntimeException("otp invalid"));
            user.setvStatus(true);
            userRepo.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("your account verified");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }



    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email ,@RequestParam String password){

        try {
            User user =userRepo.findByEmail(email.toLowerCase()).orElseThrow(()->new RuntimeException("email invalid"));
            if(!(user.getPassword().equals(password)))throw new RuntimeException("password invalid");
            if(!user.getvStatus())throw new RuntimeException("your account is not verified");


            return ResponseEntity.status(HttpStatus.OK).body("ok");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }



    }
}
