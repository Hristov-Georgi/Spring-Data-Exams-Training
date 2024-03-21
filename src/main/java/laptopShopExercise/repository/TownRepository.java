package laptopShopExercise.repository;


import laptopShopExercise.model.town.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {

    Optional<Town> findByName(String townName);
}
