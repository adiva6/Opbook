package com.example.opbook.service;

import com.example.opbook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class JwtUserDetailsService implements UserDetailsService {
    private static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);
        if (user != null) {
            ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

            if (user.getIsAdmin()) {
                authorities.add(new SimpleGrantedAuthority(ADMIN_ROLE_NAME));
            }

            return new org.springframework.security.core.userdetails.User(email,
                    bCryptPasswordEncoder.encode(user.getPassword()), authorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }
}
