package com.masprogtechs.sales.application.system.services;

import com.masprogtechs.sales.application.system.domain.entities.User;
import com.masprogtechs.sales.application.system.domain.enums.Role;
import com.masprogtechs.sales.application.system.domain.repositories.UserRepository;
import com.masprogtechs.sales.application.system.domain.entities.dto.stock.user.UserDTO;
import com.masprogtechs.sales.application.system.domain.entities.dto.user.UserReducedDTO;
import com.masprogtechs.sales.application.system.exception.AuthorizationException;
import com.masprogtechs.sales.application.system.exception.UnauthorizedAccessException;
import com.masprogtechs.sales.application.system.exception.UsernameExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;


    public UserDTO saveUser(UserDTO userDTO){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = auth.getName();

        User loggedInUser = userRepository.findByUsername(loggedInUsername);

        // Verificar se o usuário autenticado é ADMIN
        if (loggedInUser != null && loggedInUser.getRole() == Role.ADMIN) {

            // Verificar se o nome de usuário já existe
            if (userRepository.findByUsername(userDTO.getUsername()) != null) {
                throw new UsernameExistsException("Username already exists: " + userDTO.getUsername());
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
        } else {
            throw new AuthorizationException("O usuário não está autorizado a criar um usuário..");
        }
    }

    public Page<UserDTO> listUersWithPagination(Pageable pageable){

        // Verifique se o usuário autenticado tem a função "Admin"
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getAuthorities().stream().noneMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
            throw new AuthorizationException("O usuário não está autorizado a ver a lista de usuário..");
        }

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
