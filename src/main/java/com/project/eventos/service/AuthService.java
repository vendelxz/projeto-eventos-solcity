package com.project.eventos.service;

import com.project.eventos.dtos.user.AuthResponseDTO;
import com.project.eventos.dtos.user.LoginUserDTO;
import com.project.eventos.dtos.user.RegisterUserDTO;
import com.project.eventos.dtos.user.UserDTO;
import com.project.eventos.entity.user.Usuario;
import com.project.eventos.enums.user.Role;
import com.project.eventos.exceptions.EmailExistsException;
import com.project.eventos.mappers.user.UserMapper;
import com.project.eventos.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository, 
                         PasswordEncoder passwordEncoder, 
                         TokenService tokenService, 
                         UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDTO register(RegisterUserDTO registerDTO) {
        
        Optional<Usuario> existingUser = userRepository.findByEmail(registerDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new EmailExistsException("Email já cadastrado.");
        }

        Usuario newUser = new Usuario();
        newUser.setNome(registerDTO.getNome());
        newUser.setEmail(registerDTO.getEmail());
        
        newUser.setSenhaHash(passwordEncoder.encode(registerDTO.getSenha()));
        
        newUser.setCidade(registerDTO.getCidade());
        newUser.setNascimento(registerDTO.getNascimento());
        newUser.setRoles(Set.of(Role.USER)); // Define o papel padrão

        Usuario savedUser = userRepository.save(newUser);

        return userMapper.toDTO(savedUser);
    }

    public AuthResponseDTO login(LoginUserDTO loginDTO) {
        
        Usuario usuario = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new EmailExistsException("Email ou senha inválidos.")); 

        if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenhaHash())) {
            throw new RuntimeException("Email ou senha inválidos.");
        }

        String token = tokenService.generateToken(usuario);

        UserDTO userDTO = userMapper.toDTO(usuario);

        return new AuthResponseDTO(token, userDTO);
    }
}