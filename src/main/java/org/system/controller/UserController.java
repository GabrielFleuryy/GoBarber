package org.system.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.system.model.User;
import org.system.record.DataListUser;
import org.system.record.DataUpdateUser;
import org.system.record.UserRecord;
import org.system.repository.UserRepository;
import org.system.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserService userService = new UserService();
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/new-user")
    public ResponseEntity<UserRecord> newUser(@RequestBody @Valid UserRecord user, UriComponentsBuilder uriBuilder) {
        var newUser = new User(user);
        userRepository.save(newUser);

        var uri = uriBuilder.path("users/{id}").buildAndExpand(newUser.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserRecord(newUser));
    }

    @GetMapping("/get-user/{id}")
    public ResponseEntity<DataListUser> newUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }

    @PutMapping("update-user")
    @Transactional
        public ResponseEntity<?> updateUser(@RequestBody DataUpdateUser dataUpdateUser){
        return ResponseEntity.ok(userService.updateUser(dataUpdateUser));
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
