package com.example.employeeapp.service;
import com.example.employeeapp.dto.UserDTO;
import com.example.employeeapp.entity.User;
import com.example.employeeapp.repository.UserRepository;
import com.example.employeeapp.service.UserService;
import com.example.employeeapp.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User registerUser (UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(userDTO.getRole());
        return userRepository.save(user);
    }

    @Override
    public String login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (user != null && passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            return jwtUtil.generateToken(user.getEmail());
        }
        throw new RuntimeException("Invalid email or password");
    }
}