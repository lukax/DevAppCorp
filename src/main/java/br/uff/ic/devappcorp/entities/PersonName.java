package br.uff.ic.devappcorp.entities;

import br.uff.ic.devappcorp.entities.base.ValueObject;
import br.uff.ic.devappcorp.utils.Result;

public class PersonName extends ValueObject<PersonName> {
    private final String name;

    private PersonName(String name){
        this.name = name;
    }

    public static Result<PersonName> create(String personName){
        if(personName == null || personName.trim().length() == 0){
            return Result.fail("Name cannot be empty.");
        }
        if(personName.length() >= 200){
            return Result.fail("Name cannot exceed 200 characters.");
        }
        return Result.ok(new PersonName(personName));
    }

    public String getValue() {
        return name;
    }

    @Override
    public boolean equals(PersonName obj) {
        return this.name.equals(obj.name);
    }
}
