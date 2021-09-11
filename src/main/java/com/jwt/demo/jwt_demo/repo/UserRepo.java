package com.jwt.demo.jwt_demo.repo;


import com.jwt.demo.jwt_demo.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<AppUser,Long> {
    AppUser findByUsername(String username);
}
