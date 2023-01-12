package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import com.openclassrooms.paymybuddy.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String eMail) throws UsernameNotFoundException {
        log.info("je suis dans la méthode loadUserByUsername");
        UserAccount userAccount = userAccountRepository.findByEMail(eMail);

        if (userAccount == null) {
            throw new UsernameNotFoundException("Email not found : " + eMail);
        }
        log.info("le user n'est pas null");
        return new org.springframework.security.core.userdetails.User(userAccount.geteMail(),
                userAccount.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
