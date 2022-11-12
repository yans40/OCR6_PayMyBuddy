package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {

}
