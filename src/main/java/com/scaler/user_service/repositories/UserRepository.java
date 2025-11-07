package com.scaler.user_service.repositories;

import com.scaler.user_service.dtos.Userdto;
import com.scaler.user_service.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    //select * from users where email =????

    User save(User user);

}
