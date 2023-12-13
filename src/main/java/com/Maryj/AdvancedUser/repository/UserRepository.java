package com.Maryj.AdvancedUser.repository;

import com.Maryj.AdvancedUser.model.AdvancedUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<AdvancedUser,Long> {

    List<AdvancedUser> findByFullName(String fullName);
    AdvancedUser findByEmail(String email);
    List<AdvancedUser> findByGender(String gender);


    //@Override
  //  <S extends AdvancedUser> List<S> saveAll(Iterable<S> entities);
}
