package com.jwt.demo.jwt_demo.service;

import com.jwt.demo.jwt_demo.domain.AppUser;
import com.jwt.demo.jwt_demo.domain.Role;
import com.jwt.demo.jwt_demo.repo.RoleRepo;
import com.jwt.demo.jwt_demo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepo.findByUsername(username);
        if(appUser==null){
            log.error("user not found");
            throw new UsernameNotFoundException("user not found");
        } else {
            log.info("user {} found in database",username);

        }
        Collection<SimpleGrantedAuthority> authorities =new ArrayList<>();
        appUser.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));}
        );
        return new User(appUser.getUsername(),appUser.getPassword(),authorities);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new user {} to database",user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database",role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("adding role {} to user {}",roleName,username);
        AppUser user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("fetching user {}",username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getAllUsers() {
        log.info("fetching all users");
        return userRepo.findAll();
    }
}
