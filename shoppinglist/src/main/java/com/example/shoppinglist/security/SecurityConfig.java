package com.example.shoppinglist.security;

import com.example.shoppinglist.models.User;
import com.example.shoppinglist.repositorys.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;

/**
 * Configurazione di Spring Security per gestire l'autenticazione e
 * l'autorizzazione.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    /**
     * Definisce il bean per il PasswordEncoder utilizzato per criptare le password.
     *
     * @return Istanza di BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Definisce il bean per il servizio dei dettagli utente. Recupera l'utente dal
     * database.
     *
     * @return Implementazione di UserDetailsService
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("Utente non trovato con email: " + email);
            }
            // Si crea un oggetto UserDetails con le informazioni dell'utente
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    new ArrayList<>() // Lista vuota di ruoli/authorities per semplicità
            );
        };
    }

    /**
     * Configura l'autenticazione utilizzando un provider basato su DAO.
     *
     * @return DaoAuthenticationProvider configurato
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configura le regole di sicurezza per le richieste HTTP.
     *
     * @param http Oggetto HttpSecurity da configurare
     * @return SecurityFilterChain configurato
     * @throws Exception in caso di errori
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disabilitiamo il CSRF per semplificare (in produzione valutare attentamente
                // questa scelta)
                .csrf(csrf -> csrf.disable())
                // Configuriamo le autorizzazioni: le pagine di registrazione e login sono
                // accessibili a tutti
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**", "/css/**", "/js/**").permitAll()
                        .anyRequest().authenticated())
                // Configuriamo il form di login
                .formLogin(form -> form
                        .loginPage("/auth/login") // Pagina personalizzata di login
                        .loginProcessingUrl("/auth/login") // URL a cui il form invia i dati
                        .defaultSuccessUrl("/products", true) // Redirect in caso di login avvenuto con successo
                        .permitAll())
                // Configuriamo il logout
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessUrl("/auth/login?logout")
                        .permitAll())
                // Configuriamo il provider di autenticazione
                .authenticationProvider(authenticationProvider());

        return http.build();
    }
}
