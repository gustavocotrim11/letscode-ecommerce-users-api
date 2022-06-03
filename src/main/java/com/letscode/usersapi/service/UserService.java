package com.letscode.usersapi.service;

import com.letscode.usersapi.domain.UserEntity;
import com.letscode.usersapi.repository.UserRepository;
import com.letscode.usersapi.service.exception.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    public UserEntity save(UserEntity user) {
        return repository.save(user);
    }

    public UserEntity findById(String id) {
            Optional<UserEntity> user = repository.findById(Integer.valueOf(id));
            return user.orElseThrow(() -> new UserNotFoundException(id));
    }

    public void delete(String id) {
        try {
            repository.deleteById(Integer.valueOf(id));
        } catch (EmptyResultDataAccessException e) {
            throw new UserNotFoundException(id);
        }
    }

    public UserEntity update(String id, UserEntity userToUpdate) {
        try {
            UserEntity user = repository.getReferenceById(Integer.valueOf(id));
            user.setName(userToUpdate.getName());
            user.setEmail(userToUpdate.getEmail());

            return repository.save(user);
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(id);
        }
    }
}
