package com.Maryj.AdvancedUser.controller;

import com.Maryj.AdvancedUser.model.AdvancedUser;
import com.Maryj.AdvancedUser.model.AdvancedUserResource;
import com.Maryj.AdvancedUser.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")

    public ResponseEntity<AdvancedUser> saveUser(@RequestBody @Valid AdvancedUser advancedUser){
        return new ResponseEntity<>(userService.saveUser(advancedUser), HttpStatus.CREATED);
    }

    @PostMapping("allUsers")
    public Map<String,Boolean> saveAllUsers( @RequestBody List<AdvancedUser> users){
        return userService.saveAllUsers(users);
    }
    @GetMapping("/users")
    public ResponseEntity<List<AdvancedUser>> getAllUsers(){
        // return ResponseEntity.ok(userService.getAll());
        return new ResponseEntity<>(userService.getAll(),HttpStatus.CREATED);
    }



    @GetMapping("/users/{id}")
    public ResponseEntity<AdvancedUser> getUsersById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findUserById(id),HttpStatus.CREATED);
    }

    @GetMapping("/users/sorted/des")
    public  List<AdvancedUser> sortedUsersDescending(){
        List<AdvancedUser> sorted=getAllUsers().getBody();
        assert sorted != null;

        return sorted.stream().sorted((x,y) ->x.getFullName().compareTo(y.getFullName())).toList();
    }

    @GetMapping("/users/sorted/ascending")
    public  List<AdvancedUser> sortedUserAscending(){
        List<AdvancedUser> sorted=getAllUsers().getBody();
        assert sorted != null;

        return sorted.stream().sorted((x,y) ->y.getFullName().compareTo(x.getFullName())).toList();
    }


    @DeleteMapping("/users/{id}")
    public ResponseEntity<AdvancedUser> deleteUser(@PathVariable Long id){

        return ResponseEntity.ok( userService.deleteUser(id));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody @Valid AdvancedUser advancedUser){

        return ResponseEntity.ok(userService.
                updateUser(id,advancedUser));
    }


@GetMapping("user/{id}")
    public ResponseEntity<AdvancedUserResource> getUserResource(@PathVariable Long id){
        AdvancedUserResource advancedUserResource=new AdvancedUserResource();
        AdvancedUser user = getUsersById(id).getBody();
        advancedUserResource.setAdvancedUser(user);
    Link idLink= WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).getUsersById(id)).withRel("userById");
    Link updateLink=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).updateUser(id,user)).withRel("updateLink");
    Link deleteLink=WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
            .methodOn(UserController.class).deleteUser(id)).withRel("deleteLink");
    advancedUserResource.add(idLink,deleteLink,updateLink);
    return new ResponseEntity<>(advancedUserResource,HttpStatus.OK);
}



}
