package com.openclassrooms.paymybuddy.service;

import com.openclassrooms.paymybuddy.entity.UserAcompte;
import com.openclassrooms.paymybuddy.repository.UserAcompteRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserAcompteService {
    @Autowired
  private UserAcompteRepository userAcompteRepository;


    public UserAcompte saveUserAcompte(UserAcompte userAcompteReceive) {
        List<UserAcompte> userAcompteList = getUserAcompteList();
        for(UserAcompte userAcompte:userAcompteList){
            if(userAcompte.geteMail().equals(userAcompteReceive.geteMail()) && userAcompte.getPassword().equals(userAcompteReceive.getPassword())){
                return null;
            }
        }
        return userAcompteRepository.save(userAcompteReceive);
    }

    public UserAcompte getUserAcompteById(int id){
        return userAcompteRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    public UserAcompte deleteUserAcompte(int id) {
        userAcompteRepository.deleteById(id);
        return null;
    }

    public UserAcompte updateUserAcompte(@NotNull UserAcompte userAcompteReceive, int id) {

        UserAcompte userAcompteToUpdate = getUserAcompteById(id);

        userAcompteToUpdate.setName(userAcompteReceive.getName());
        userAcompteToUpdate.setSolde(userAcompteReceive.getSolde());
        userAcompteToUpdate.setPassword(userAcompteReceive.getPassword());
        userAcompteToUpdate.seteMail(userAcompteReceive.geteMail());

        return userAcompteRepository.save(userAcompteToUpdate);
    }

     public List<UserAcompte> getUserAcompteList(){

        return userAcompteRepository.findAll();
     }
}
