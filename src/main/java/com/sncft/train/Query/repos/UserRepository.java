package com.sncft.train.Query.repos;


import com.sncft.train.Query.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    // You can define custom query methods here if needed

}
