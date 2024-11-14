package com.example.be_quan_tri.services;

import com.example.be_quan_tri.entitys.Partner;
import com.example.be_quan_tri.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailSever implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Partner user = userRepository.findFirstByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found", null);
        }
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+user.getRole());
        List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        updatedAuthorities.add(authority);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), updatedAuthorities);
    }

}
