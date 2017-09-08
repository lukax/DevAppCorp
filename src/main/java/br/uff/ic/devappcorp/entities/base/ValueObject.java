package br.uff.ic.devappcorp.entities.base;

import java.io.Serializable;

public abstract class ValueObject<T extends ValueObject<T>> implements Serializable {

    public abstract boolean equals(T obj);

    @Override
    public boolean equals(Object obj) {
        T other;
        try{
            other = (T) obj;
            if(other == null) return false;
        } catch (ClassCastException ex){
            return false;
        }
        return this.equals(other);
    }
}
