package nl.feliksik.bugs.spybean.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityJpaRepo extends JpaRepository<Entity, String> {

}
