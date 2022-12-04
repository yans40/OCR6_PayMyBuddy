package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {
    @Query("select user from UserAccount user where user.eMail= :eMail")
    UserAccount findByEMail(@Param("eMail")String eMail);
}
