package mariapiabaldoin.Giorno_1.repositories;


import mariapiabaldoin.Giorno_1.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface ViaggiRepository extends JpaRepository<Viaggio, UUID> {

}