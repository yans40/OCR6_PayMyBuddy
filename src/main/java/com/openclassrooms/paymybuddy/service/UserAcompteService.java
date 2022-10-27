package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.UserAcompte;
import com.openclassrooms.paymybuddy.repository.UserAcompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAcompteService {
    @Autowired
  private UserAcompteRepository userAcompteRepository;

    public UserAcompte saveUserAcompte(UserAcompte userAcompte){
        return userAcompteRepository.save(userAcompte);
    }

    public UserAcompte getUserAcompte(int id){
        return userAcompteRepository.findById(id).get();
    }
    public UserAcompte deleteUserAcompte(int id) {

        userAcompteRepository.deleteById(id);
        return null;
    }
}
