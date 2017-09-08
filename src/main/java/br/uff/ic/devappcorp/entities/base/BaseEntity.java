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

    protected void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        BaseEntity other;
        try{
            other = (BaseEntity) obj;
            if(other == null) return false;
        } catch (ClassCastException ex){
            return false;
        }
        return this.id.equals(other.id);
    }
}
