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
            return Result.Failure("Person name cannot be empty.");
        }
        if(personName.length() >= 200){
            return Result.Failure("Person name too long");
        }
        return Result.Success(new PersonName(personName));
    }

    public String getFullName() {
        return name;
    }

    @Override
    public boolean equals(PersonName obj) {
        return this.name.equals(obj.name);
    }
}
