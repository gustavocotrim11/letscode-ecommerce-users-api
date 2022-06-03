package com.letscode.usersapi.repository;

import com.letscode.usersapi.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
}
