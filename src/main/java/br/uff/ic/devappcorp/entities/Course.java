package br.uff.ic.devappcorp.entities;

import br.uff.ic.devappcorp.entities.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Course extends BaseEntity {
    @Column
    private String description;

    public Course() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
