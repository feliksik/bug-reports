package nl.feliksik.bugs.spybean.jpa;

import org.hibernate.annotations.Type;

import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name = "CHARGING_PROFILE")
public class Entity {
    @Id
    @Type(type = "string")
    public String id;

    public Entity(){
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
