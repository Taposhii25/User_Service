package com.scaler.user_service.repositories;

import com.scaler.user_service.dtos.Userdto;
import com.scaler.user_service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
