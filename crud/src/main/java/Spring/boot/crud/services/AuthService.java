package Spring.boot.crud.services;

import Spring.boot.crud.entities.User;
import Spring.boot.crud.dto.UserDTO;
import Spring.boot.crud.repos.UserRepo;
import Spring.boot.crud.security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    // User to UserDTO
    private UserDTO convertToDTO(User user) {
        return new UserDTO(user.getName(), user.getEmail(), null);  
    }

    // UserDTO to User
    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());  
        return user;
    }

    public UserDTO registerUser(UserDTO userDTO) {
        if (userDTO.getName() == null || userDTO.getEmail() == null || userDTO.getPassword() == null) {
            throw new BadCredentialsException("Missing fields: name, email, or password");
        }
        User user = convertToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepo.save(user);
        return convertToDTO(savedUser);
    }


    public String loginUser(UserDTO userDTO) {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();

        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new BadCredentialsException("Invalid Username");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid Password");
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(email, password);
        return JWTProvider.generateJwtToken(auth);
    }

    public UserDTO findByEmail(String email) {
        User user = userRepo.findByEmail(email);
        return user != null ? convertToDTO(user) : null;
    }
}
