package at.ac.tgm.rebay.rebay_backend.repositories;

import at.ac.tgm.rebay.rebay_backend.models.Role;
import at.ac.tgm.rebay.rebay_backend.models.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Es wird ein Repository für die Role-Entität mit einem Primärschlüssel vom Typ BigInteger definiert.
 * Der Zugriff auf die Datenbank für die Role-Entität wird gekapselt. Sie wird verwendet, um CRUD-Operationen (Create, Read, Update, Delete)
 * und benutzerdefinierte Abfragen für die Role-Entität zu ermöglichen.
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleEnum roleName); //Rolle über den Namen finden, Methodenname ist wichtig!
}
