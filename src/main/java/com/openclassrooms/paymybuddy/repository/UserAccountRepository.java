package com.openclassrooms.paymybuddy.repository;

import com.openclassrooms.paymybuddy.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount,Integer> {
    @Query("select user from UserAccount user where user.eMail= :eMail")
    UserAccount findByEMail(@Param("eMail")String eMail);

//    Optional<UserAccount> findByEMail(String eMail);
}
