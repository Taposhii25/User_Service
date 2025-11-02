package com.scaler.user_service.controllers;

import com.scaler.user_service.dtos.SignupRequestdto;
import com.scaler.user_service.dtos.Userdto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping(/"login")
    public Token login(@RequestBody LonginRequestdto requestdto){
        return null;
    }

    @PostMapping("/signup")
    public Userdto signup(@RequestBody SignupRequestdto requestdto){
        return null;
    }

    @GetMapping("/validate/{tokenValue}")
    public ResponseEntity<Userdto> validateToken(@PathVariable("tokenValue") String tokenValue ){
        return null;
    }
    //todo-logout and forgot password

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotpassword(@PathVariable("email") String email){
        return null;
        //return ResponseEntity.ok("Password reset link sent to " + email);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String token){
        return null;
    }
}
