package com.project.eventos.service;

import com.project.eventos.dtos.user.AuthResponseDTO;
import com.project.eventos.dtos.user.LoginUserDTO;
import com.project.eventos.dtos.user.RegisterUserDTO;
import com.project.eventos.dtos.user.UserDTO;
import com.project.eventos.entity.user.Usuario;
import com.project.eventos.enums.user.Role;
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

    // Injeção de dependências via construtor
    public AuthService(UserRepository userRepository, 
                         PasswordEncoder passwordEncoder, 
                         TokenService tokenService, 
                         UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.userMapper = userMapper;
    }

    /**
     * Lógica de Registro de novo usuário.
     */
    @Transactional
    public UserDTO register(RegisterUserDTO registerDTO) {
        
        // 1. Validar se o email já existe
        Optional<Usuario> existingUser = userRepository.findByEmail(registerDTO.getEmail());
        if (existingUser.isPresent()) {
            // Lançar uma exceção específica seria melhor, 
            // mas por enquanto lançamos uma geral.
            throw new RuntimeException("Email já cadastrado.");
        }

        // 2. Criar a nova entidade Usuario
        Usuario newUser = new Usuario();
        newUser.setNome(registerDTO.getNome());
        newUser.setEmail(registerDTO.getEmail());
        
        // 3. Hashear a senha
        newUser.setSenhaHash(passwordEncoder.encode(registerDTO.getSenha()));
        
        // 4. Definir campos opcionais e padrão
        newUser.setCidade(registerDTO.getCidade());
        newUser.setNascimento(registerDTO.getNascimento());
        newUser.setRoles(Set.of(Role.USER)); // Define o papel padrão

        // 5. Salvar no banco
        Usuario savedUser = userRepository.save(newUser);

        // 6. Converter e retornar o DTO seguro
        return userMapper.toDTO(savedUser);
    }

    /**
     * Lógica de Login do usuário.
     */
    public AuthResponseDTO login(LoginUserDTO loginDTO) {
        
        // 1. Buscar o usuário pelo email
        Usuario usuario = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos.")); // Exceção genérica por segurança

        // 2. Verificar a senha
        if (!passwordEncoder.matches(loginDTO.getSenha(), usuario.getSenhaHash())) {
            throw new RuntimeException("Email ou senha inválidos.");
        }

        // 3. Gerar o Token JWT
        String token = tokenService.generateToken(usuario);

        // 4. Converter o usuário para DTO
        UserDTO userDTO = userMapper.toDTO(usuario);

        // 5. Retornar a resposta completa
        return new AuthResponseDTO(token, userDTO);
    }
}