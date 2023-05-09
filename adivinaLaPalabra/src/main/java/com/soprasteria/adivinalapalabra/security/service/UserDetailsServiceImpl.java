package com.soprasteria.adivinalapalabra.security.service;

import com.soprasteria.adivinalapalabra.security.entity.UserEntity;
import com.soprasteria.adivinalapalabra.security.entity.UserDetailsImpl;
import com.soprasteria.adivinalapalabra.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    LoginServiceImpl loginService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = loginService.login(username).get();

        return new UserDetailsImpl(userEntity);
    }
}
