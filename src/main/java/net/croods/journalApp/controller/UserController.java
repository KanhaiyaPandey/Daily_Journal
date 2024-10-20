package net.croods.journalApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.croods.journalApp.entity.User;
import net.croods.journalApp.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  // get all users

  @GetMapping
  public List <User> getAllUsers(){
    return userService.getAll();
  }

  // create user

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody User user){
    try {
      userService.saveEntry(user);
      String msg = "user created";
      return new ResponseEntity<>(msg,HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
    }
  
  }

  @PutMapping("/{userName}")
  public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
   User userInDb =  userService.findByUserName(userName);
   if (userInDb != null) {
      userInDb.setUserName(user.getUserName());
      userInDb.setPassword(user.getPassword());
      userService.saveEntry(userInDb);
   }

   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

    
}
