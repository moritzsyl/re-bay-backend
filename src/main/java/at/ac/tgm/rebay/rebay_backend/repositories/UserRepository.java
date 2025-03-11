package at.ac.tgm.rebay.rebay_backend.repositories;


import at.ac.tgm.rebay.rebay_backend.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Es wird ein Repository für die User-Entität mit einem Primärschlüssel vom Typ BigInteger definiert.
 * Der Zugriff auf die Datenbank für die User-Entität wird gekapselt. Sie wird verwendet, um CRUD-Operationen (Create, Read, Update, Delete)
 * und benutzerdefinierte Abfragen für die User-Entität zu ermöglichen.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByLoginContactEmail(String loginContactEmail);//Benutzer über die E-Mail-Adresse finden, Methodenname ist wichtig!
}
