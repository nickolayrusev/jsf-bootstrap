package ca.pfroy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by nikolayrusev on 2/7/16.
 */
@Entity
public class User extends  BaseEntity{
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String username;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
