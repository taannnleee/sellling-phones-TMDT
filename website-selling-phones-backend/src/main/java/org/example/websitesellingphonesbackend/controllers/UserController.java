//package org.example.websitesellingphonesbackend.repositories.controllers;
//
//import org.example.websitesellingphonesbackend.entities.old.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
////@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin("*")
//@RestController
//@RequestMapping("/api/")
//public class UserController {
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    // get all user
//    @GetMapping("/user")
//    public List<User> getAllUser() {
//        return userService.getAllUser();
//    }
//
//    // create user rest api
//    @PostMapping("/user")
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
////        return  new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
//    }
//
//    // get user by id rest api
//    @GetMapping("/user/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        User user = userService.getUserById(id);
//        return ResponseEntity.ok(user);
//    }
//
//    // update user rest api
//
//    @PutMapping("/user/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
//        User updatedUser = userService.updateUser(id, userDetails);
//        return ResponseEntity.ok(updatedUser);
//    }
//
//    // delete user rest api
//    @DeleteMapping("/user/{id}")
//    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("deleted", Boolean.TRUE);
//        return ResponseEntity.ok(response);
//    }
//}
