package com.example.lab8.repository;

import com.example.lab8.model.Book;
import com.example.lab8.model.User;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface UserRepository extends Repository<User, Integer> {
    void save(User user);

    User getById(Integer id);

    
}
