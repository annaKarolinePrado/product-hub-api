package com.producthub.services;

import com.producthub.DTO.UserDTO;
import com.producthub.enums.Profile;
import com.producthub.exceptions.BussinesException;
import com.producthub.models.User;
import com.producthub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<UserDTO> findUsersByEmail(String email) {
        List<User> users = userRepository.findByEmailContainingIgnoreCase(email);
        return users.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public UserDTO saveUser(UserDTO userDTO) {
        if (userRepository.existsByNickname(userDTO.getNickname())) {
            throw new BussinesException("Nickname já está em uso.");
        }

        User user = convertToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Criptografar a senha aqui
        user = userRepository.save(user);
        return convertToDTO(user);
    }

    public UserDTO updateUser(UserDTO user) {
        if (user.getId() == null) {
            throw new BussinesException("Campo id não informado.");
        }
        return saveUser(user);
    }

    public void updateUserLote(List<UserDTO> users) {
        users.forEach(user -> {
            if (user.getId() == null) {
                throw new BussinesException("Campo id não informado.");
            }
            saveUser(user);
        });
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void deleteUserLote(List<UserDTO> users) {
        users.forEach(user -> {
            if (user.getId() == null) {
                throw new BussinesException("Campo id não informado.");
            }
            deleteUserById(user.getId());
        });
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RestClientException("User not found with id " + id));
    }

    public UserDTO findDTOById(Long id) {
        return convertToDTO(userRepository.findById(id)
                .orElseThrow(() -> new RestClientException("User not found with id " + id)));
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setNickname(user.getNickname());
        userDTO.setEmail(user.getEmail());
        userDTO.setProfiles(user.getProfiles());
        userDTO.setActive(user.getActive());
        return userDTO;
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setProfiles(userDTO.getProfiles());
        user.setActive(userDTO.isActive());
        return user;
    }

    public boolean existsByNickname(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    public void createDefaultUser() {
        if (userRepository.count() == 0) {
            User defaultUser = new User();
            UserDTO defaultUserDTO = new UserDTO();

            defaultUserDTO.setName("Default User");
            defaultUserDTO.setNickname("default.user");
            defaultUserDTO.setEmail("default.user@example.com");
            defaultUserDTO.setPassword("password3241");
            defaultUserDTO.setActive(true);
            defaultUserDTO.setProfiles(Arrays.asList(Profile.ROOT, Profile.PREMIUM));

            saveUser(defaultUserDTO);
        }
    }
}
