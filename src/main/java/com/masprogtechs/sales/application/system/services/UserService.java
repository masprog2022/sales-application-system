package com.masprogtechs.sales.application.system.services;

import com.masprogtechs.sales.application.system.domain.entities.User;
import com.masprogtechs.sales.application.system.domain.repositories.UserRepository;
import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserReducedDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;


    public UserDTO saveUser(UserDTO userDTO){

        if(userRepository.findByUsername(userDTO.getUsername()) != null){
            return null;
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        User newUser = new User(userDTO.getName(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPhone(),
                encryptedPassword,
                userDTO.getRole());
        userRepository.save(newUser);

        return modelMapper.map(newUser, UserDTO.class);
    }

    public Page<UserDTO> listUersWithPagination(Pageable pageable){
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage.map(user -> modelMapper.map(user, UserDTO.class));
    }

    public UserReducedDTO mapToReducedDTO(User user) {
        UserReducedDTO reducedDTO = new UserReducedDTO();
        reducedDTO.setName(user.getName());
        reducedDTO.setUsername(user.getUsername());
        reducedDTO.setEmail(user.getEmail());
        reducedDTO.setPhone(user.getPhone());

        return reducedDTO;
    }
}
