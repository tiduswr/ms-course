package com.tiduswr.hroauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tiduswr.hroauth.entities.User;
import com.tiduswr.hroauth.feignclients.UserFeignClient;

import feign.FeignException;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private UserFeignClient userFeignClient;

    public User findByEmail(String email) throws ResponseStatusException{
        try{
            User user = userFeignClient.findByEmail(email).getBody();

            if(user == null) 
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);

            return user;
        }catch(FeignException e){
            throw new ResponseStatusException(HttpStatus.valueOf(e.status()));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            return findByEmail(username);
        }catch(Exception e){
            throw new UsernameNotFoundException(e.getMessage());
        }
    }

}
