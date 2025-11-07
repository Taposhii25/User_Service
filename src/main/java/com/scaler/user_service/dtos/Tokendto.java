package com.scaler.user_service.dtos;

import com.scaler.user_service.models.Token;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Tokendto {
    private String tokenValue;
    private Date expiryAt;

    public static Tokendto from(Token token) {
        if(token == null) {
            return null;
        }
        Tokendto tokendto = new Tokendto();
        tokendto.setTokenValue(token.getValue());
        tokendto.setExpiryAt(token.getExpiryAt());
        return tokendto;
    }
}
