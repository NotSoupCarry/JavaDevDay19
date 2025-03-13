package com.example.shoppinglist.services;

import com.example.shoppinglist.models.User;
import com.example.shoppinglist.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Servizio per la gestione degli utenti.
 */
@Service
@RequiredArgsConstructor // Genera un costruttore per i campi finali
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registra un nuovo utente. La password viene criptata prima di salvare
     * l'utente.
     *
     * @param user L'utente da registrare
     * @return L'utente registrato
     */
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Trova un utente in base all'email.
     *
     * @param email L'email da cercare
     * @return L'utente trovato
     */
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}