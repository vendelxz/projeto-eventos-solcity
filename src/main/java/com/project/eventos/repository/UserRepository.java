package com.project.eventos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.eventos.entity.user.Usuario;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {

    /**
     * Busca um usuário pelo seu endereço de e-mail.
     * O Spring Data JPA cria automaticamente a consulta (query) 
     * baseada no nome do método.
     *
     * @param email O email do usuário a ser buscado.
     * @return Um Optional contendo o Usuário se encontrado, 
     * ou um Optional vazio se não.
     */
    Optional<Usuario> findByEmail(String email);

}