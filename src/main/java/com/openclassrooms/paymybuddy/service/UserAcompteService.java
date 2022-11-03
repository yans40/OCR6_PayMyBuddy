package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.UserAcompte;
import com.openclassrooms.paymybuddy.repository.UserAcompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserAcompteService {
    @Autowired
  private UserAcompteRepository userAcompteRepository;


    public UserAcompte saveUserAcompte(UserAcompte userAcompte) {
        return userAcompteRepository.save(userAcompte);
    }

    public UserAcompte getUserAcompteById(int id){
        return userAcompteRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public UserAcompte deleteUserAcompte(int id) {
        userAcompteRepository.deleteById(id);
        return null;
    }

    public UserAcompte updateUserAcompte(UserAcompte userAcompteReceive, int id) {

        UserAcompte userAcompteToUpdate = getUserAcompteById(id);

        userAcompteToUpdate.setName(userAcompteReceive.getName());
        userAcompteToUpdate.setSolde(userAcompteReceive.getSolde());
        userAcompteToUpdate.setPassword(userAcompteReceive.getPassword());
        userAcompteToUpdate.seteMail(userAcompteReceive.geteMail());

        return userAcompteRepository.save(userAcompteToUpdate);
    }
}
