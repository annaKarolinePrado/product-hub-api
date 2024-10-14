package com.games.resources;
import com.games.DTO.UserDTO;
import com.games.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> getUsersByEmail(@RequestParam String email) {
        List<UserDTO> users = userService.findUsersByEmail(email);
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.saveUser(userDTO);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/create-lote")
    public ResponseEntity<Void> createUser(@RequestBody List<UserDTO> users) {
        users.forEach(user -> userService.saveUser(user));
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findDTOById(id));
    }

    @PutMapping()
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @PutMapping("update-lote")
    public ResponseEntity<String> updateUser(@RequestBody List<UserDTO> users) {
        userService.updateUserLote(users);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteUser(@RequestBody UserDTO user) {
        userService.deleteUserById(user.getId());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete-lote")
    public ResponseEntity<Void> deleteUser(@RequestBody List<UserDTO> users) {
        userService.deleteUserLote(users);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkNickname(@RequestParam String nickname) {
        boolean exists = userService.existsByNickname(nickname);
        return ResponseEntity.ok(exists);
    }
}
