package com.scaler.user_service.controllers;

import com.scaler.user_service.dtos.LoginRequestdto;
import com.scaler.user_service.dtos.SignupRequestdto;
import com.scaler.user_service.dtos.Tokendto;
import com.scaler.user_service.dtos.Userdto;
import com.scaler.user_service.exceptions.InValidTokenExceptionclass;
import com.scaler.user_service.models.Token;
import com.scaler.user_service.models.User;
import com.scaler.user_service.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Tokendto> login(@RequestBody LoginRequestdto requestdto){
        Token token = userService.login(
                requestdto.getEmail(),
                requestdto.getPassword()
        );
        if(token == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Tokendto tokendto = Tokendto.from(token);
        return new ResponseEntity<>(tokendto, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public Userdto signup(@RequestBody SignupRequestdto requestdto){
        User user = userService.signup(
                requestdto.getName(),
                requestdto.getEmail(),
                requestdto.getPassword()
        );
        return Userdto.from(user);
        //return from(user);
    }

    @GetMapping("/validate/{tokenValue}")
    public ResponseEntity<Userdto> validateToken(@PathVariable("tokenValue") String tokenValue ) throws InValidTokenExceptionclass {
        User user = userService.validateToken(tokenValue);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        //token is valid
       return new ResponseEntity<>(
                Userdto.from(user),
                HttpStatus.OK
        );
    }
    //getting userdto from user
//    private Userdto from(User user){
//        if(user == null) return null;
//        Userdto userdto = new Userdto();
//        userdto.setUsername(user.getName());
//        userdto.setEmail(user.getEmail());
//        userdto.setUserId(user.getId());
//        userdto.setRoles(user.getRoles());
//
//        return userdto;
//    }


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
