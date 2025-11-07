package com.scaler.user_service.services;

import com.scaler.user_service.exceptions.InValidTokenExceptionclass;
import com.scaler.user_service.models.Token;
import com.scaler.user_service.models.User;
import com.scaler.user_service.repositories.TokenRepository;
import com.scaler.user_service.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
         this.userRepository = userRepository;
         this.tokenRepository = tokenRepository;
         this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Token login(String email, String password){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            //redirect to signup page
            return null;
        }
        User user = optionalUser.get();

//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        if(!passwordEncoder.matches(password, user.getPassword())){
            if(!bCryptPasswordEncoder.matches(password, user.getPassword())){
                //login failed
                return null;
            }

//        if(!user.getPassword().equals(password)){
//            //login successful,generate the token
//            return null;
//        }
        Token token = new Token();
        token.setUser(user);

        LocalDate localDate = LocalDate.now().plusDays(30);
        Date expiryDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.DAY_OF_MONTH, 30);

//        token.setExpiryAt(calendar.getTime());

        token.setExpiryAt(expiryDate);
        token.setValue(RandomStringUtils.randomAlphanumeric(128));

        return tokenRepository.save(token);
    }

    public User signup(String name, String email, String password){
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isPresent()){
            //redirect the user in login page
            //User user = optionalUser.get();
        }

        User user = new User();
        user.setEmail(email);
        user.setName(name);

        //todo - use bcrypt passwordEncoder
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        user.setPassword(passwordEncoder.encode(password));
        user.setPassword(bCryptPasswordEncoder.encode(password));
//        user.setPassword(password);

        return userRepository.save(user);
    }

    public User validateToken(String tokenValue) throws InValidTokenExceptionclass {
        //token value should be present in the db,&
        // the expiry time should be > the current time
        Optional<Token> optionalToken = tokenRepository.findByValueAndExpiryAtAfter(tokenValue,new Date());
        //Optional<Token> optionalToken = tokenRepository.findByValue(tokenValue);
        if(optionalToken.isEmpty()){
            //token is invalid
            throw new InValidTokenExceptionclass("Token is invalid or expired, Please try to login again");
        }
        Token token = optionalToken.get();

        return token.getUser();
    }
}
