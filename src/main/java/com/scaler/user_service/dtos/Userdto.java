package com.scaler.user_service.dtos;

import com.scaler.user_service.models.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Userdto {
    private Long userId;
    private String username;
    private List<Role> roles;
}
