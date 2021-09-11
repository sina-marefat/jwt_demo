package com.jwt.demo.jwt_demo.service;

import com.jwt.demo.jwt_demo.domain.AppUser;
import com.jwt.demo.jwt_demo.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    AppUser saveUser(AppUser user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    AppUser getUser(String username);
    List<AppUser> getAllUsers();

}
