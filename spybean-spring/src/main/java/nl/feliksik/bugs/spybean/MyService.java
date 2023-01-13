package nl.feliksik.bugs.spybean;

import nl.feliksik.bugs.spybean.jpa.Entity;
import nl.feliksik.bugs.spybean.jpa.EntityJpaRepo;
import org.springframework.stereotype.Service;

@Service("ChargingProfileDatabaseAdapter")
class MyService {
    private final EntityJpaRepo entityRepo;

    public MyService(EntityJpaRepo jpaRepo) {
        this.entityRepo = jpaRepo;
    }

    public String upsert(String id) {
        Entity e = new Entity();
        e.setId(id);

        entityRepo.saveAndFlush(e);
        return id;
    }
}
