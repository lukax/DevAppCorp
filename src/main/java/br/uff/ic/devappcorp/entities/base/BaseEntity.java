package br.uff.ic.devappcorp.entities.base;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        BaseEntity other;
        try{
            other = (BaseEntity) obj;
            if(other == null) return false;
        } catch (ClassCastException ex){
            return false;
        }
        if(this.id == 0 || other.id == 0) return false;
        return this.id.equals(other.id);
    }
}
