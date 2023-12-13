package com.Maryj.AdvancedUser.service;

import com.Maryj.AdvancedUser.model.AdvancedUser;
import com.Maryj.AdvancedUser.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Service

public class UserService { //model -> repository -> service ->controller ->client


@Autowired
    private UserRepository userRepository;


    @CacheEvict(value = "allUsers", allEntries = true)
    public AdvancedUser saveUser(AdvancedUser advancedUser){
    return userRepository.save(advancedUser);
    }

    @CacheEvict(value = "allUsers", allEntries = true)
    public Map<String, Boolean> saveAllUsers( List<AdvancedUser> users){
        Map<String,Boolean> response = new HashMap<>();
        for (AdvancedUser user :users){
            response.put(user.getFullName() + "posted successfully", true);
        }
        userRepository.saveAll(users);
        return response;
    }

    @Cacheable("allUsers")
    public List<AdvancedUser> getAll(){
        return userRepository.findAll();
    }

    @Cacheable(value = "SingleUser", key = "#id")
    public AdvancedUser findUserById(Long id){
        return userRepository.findById(id).orElse(null);

    }

    @CacheEvict(value = {"singleUser","allUsers"}, key = "#id")
    public String updateUser(Long id, AdvancedUser advancedUser){
        AdvancedUser toUpdate=findUserById(id);
        toUpdate.setFullName(advancedUser.getFullName());
        toUpdate.setEmail(advancedUser.getEmail());
        toUpdate.setGender(advancedUser.getGender());
        toUpdate.setPhoneNumber(advancedUser.getPhoneNumber());
        userRepository.save(toUpdate);
        return "Advanced user successfully updated";
    }


    public AdvancedUser deleteUser(Long id){
        AdvancedUser user=findUserById(id);
       userRepository.delete(user);
        return user;
    }







   /*
    private UserRepository userRepository;

    @CacheEvict(value = "allUsers", allEntries = true)
    public AdvancedUser saveUser(AdvancedUser advancedUser){
      return userRepository.save(advancedUser);
    }








    @CacheEvict(value = {"singleUser","allUsers"}, key = "#id")
    public String updateUser(Long id, AdvancedUser advancedUser){
        AdvancedUser toUpdate=findUserById(id);
        toUpdate.setFullName(advancedUser.getFullName());
        toUpdate.setEmail(advancedUser.getEmail());
        toUpdate.setGender(advancedUser.getGender());
        toUpdate.setPhoneNumber(advancedUser.getPhoneNumber());
        userRepository.save(toUpdate);
        return "Advanced user successfully updated";
    }



*/
}
