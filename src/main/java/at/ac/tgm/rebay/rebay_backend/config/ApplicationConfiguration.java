package at.ac.tgm.rebay.rebay_backend.config;

import at.ac.tgm.rebay.rebay_backend.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Diese Konfiguration enthält die Sicherheitsfunktionen für die Spring Boot Anwendung.
 * Sie verwendet Spring Security, um die Authentifizierung und Autorisierung für alle Anfragen zu implementieren.
 *
 * Die Konfiguration enthält:
 * - Eine Sicherheitsfilterkette für HTTP-Anfragen.
 * - Ein UserDetailsService für die Verwaltung von Benutzerinformationen.
 * - Einen PasswordEncoder, um Passwörter sicher zu speichern.
 */
@Configuration
@EnableWebSecurity // Aktiviert Web-Sicherheitsfunktionen für die Anwendung
public class ApplicationConfiguration {

    private final UserRepository userRepository;

    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Konfiguriert die Sicherheitsfilterkette für HTTP-Anfragen.
     *
     * Diese Methode legt fest, dass alle Anfragen authentifiziert werden müssen und HTTP Basic Authentifizierung verwendet wird.
     *
     * @param http - Die HttpSecurity-Instanz, die für die Konfiguration der HTTP-Sicherheit verwendet wird.
     * @return Eine konstruierte Sicherheitsfilterkette.
     * @throws Exception Falls Fehler bei der Konfiguration der Sicherheit auftreten.
     */

    /*
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated() // Alle Anfragen erfordern eine Authentifizierung
                )
                .httpBasic(withDefaults()); // Aktiviert die HTTP Basic Authentifizierung, die Benutzername und Passwort im Header überträgt

        return http.build(); // Gibt die konfigurierte Sicherheitsfilterkette zurück
    }
    */

    /**
     * Erstellt und konfiguriert ein UserDetailsService, das Benutzerinformationen speichert.
     * <p>
     * In diesem Fall wird ein Benutzer im Arbeitsspeicher (InMemory) erstellt, der für Demonstrationszwecke und einfache Tests verwendet wird.
     * Der Benutzer hat den Benutzernamen "user", das Passwort "password" (verschlüsselt) und die Rolle "USER".
     *
     * @return Ein InMemoryUserDetailsManager, das den Benutzer enthält.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return loginContactEmail -> userRepository.findByLoginContactEmail(loginContactEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
    /**
     * Erstellt einen PasswordEncoder, der verwendet wird, um Passwörter sicher zu verschlüsseln.
     *
     * In dieser Konfiguration wird der BCryptPasswordEncoder verwendet, da BCrypt ein bewährter und sicherer Algorithmus ist,
     * um Passwörter zu verschlüsseln und sie gegen Brute-Force-Angriffe zu schützen.
     *
     * @return Ein BCryptPasswordEncoder, der für die Verschlüsselung von Passwörtern verwendet wird.
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Rückgabe eines BCryptPasswordEncoders
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

}