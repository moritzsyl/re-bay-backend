package at.ac.tgm.rebay.rebay_backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Die Hauptklasse der ReBay-Backend-Anwendung. Sie dient als Einstiegspunkt
 * für die Spring Boot-Anwendung und enthält die `main`-Methode, die den Server startet.
 * <p>
 * Diese Klasse initialisiert ein einfaches Beispielprodukt und einen Controller,
 * um den Anwendungsstart zu demonstrieren.
 */
@SpringBootApplication
public class RebayBackendApplication {

    // Logger für die Protokollierung von Systemmeldungen, um den Start und kritische Ereignisse festzuhalten
    private static final Logger log = LoggerFactory.getLogger(RebayBackendApplication.class);

    /**
     * Hauptmethode der Anwendung. Diese Methode wird beim Start ausgeführt und
     * initialisiert den Spring Boot-Server.
     *
     * @param args Argumente für den Kommandozeilenaufruf
     */
    public static void main(String[] args) {
        // Startet die Spring Boot-Anwendung und bindet die Backend-Dienste
        SpringApplication.run(RebayBackendApplication.class, args);
        log.info("Spring Boot started successfully.");
    }
}