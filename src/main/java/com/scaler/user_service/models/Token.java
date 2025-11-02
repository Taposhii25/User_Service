package com.scaler.user_service.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name ="tokens")
public class Token extends BaseModel{
    private String value;
    private String expiryAt;

    @ManyToOne
    private User user;

}
