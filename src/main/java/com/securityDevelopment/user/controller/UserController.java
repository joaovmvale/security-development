package com.securityDevelopment.user.controller;

import com.securityDevelopment.user.model.UserModel;
import com.securityDevelopment.user.model.dto.UserDTO;
import com.securityDevelopment.user.model.dto.UserResponseDTO;
import com.securityDevelopment.user.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserDTO dto) {
        UserModel user = userService.save(dto.transformToUserModel());
        UserResponseDTO response = UserResponseDTO.transformToUserResponseDTO(user);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listAll() {
        List<UserModel> users = userService.listAll();
        List<UserResponseDTO> response = users.stream().map(UserResponseDTO::transformToUserResponseDTO).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable UUID id) {
        UserModel user = userService.findById(id);
        UserResponseDTO response = UserResponseDTO.transformToUserResponseDTO(user);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable UUID id, @RequestBody UserDTO dto) {
        UserModel user = userService.findById(id);
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        userService.save(user);

        UserResponseDTO response = UserResponseDTO.transformToUserResponseDTO(user);

        return ResponseEntity.ok(response);
    }
}
