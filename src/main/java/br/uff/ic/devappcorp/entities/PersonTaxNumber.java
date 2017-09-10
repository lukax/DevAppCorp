package br.uff.ic.devappcorp.entities;

import br.uff.ic.devappcorp.entities.base.ValueObject;
import br.uff.ic.devappcorp.utils.Result;

import java.util.regex.Pattern;

public class PersonTaxNumber extends ValueObject<PersonTaxNumber> {
    private final Integer taxNumber;

    private PersonTaxNumber(Integer taxNumber){
        this.taxNumber = taxNumber;
    }

    public static Result<PersonTaxNumber> create(Integer taxNumber){
        if(taxNumber <= 0){
            return Result.fail("Tax number cannot be empty.");
        }
        if(!validadeNumberOfDigits(taxNumber)){
            return Result.fail("Tax number invalid.");
        }
        return Result.ok(new PersonTaxNumber(taxNumber));
    }

    private static boolean validadeNumberOfDigits(Integer taxNumber) {
        return getNumberOfDigits(taxNumber) == 11  // CPF
            || getNumberOfDigits(taxNumber) == 14; // CNPJ
    }

    private static int getNumberOfDigits(Integer number){
        return (int) Math.floor(Math.log10(number) + 1);
    }

    public Integer getValue() {
        return taxNumber;
    }

    @Override
    public boolean equals(PersonTaxNumber obj) {
        return this.taxNumber.equals(obj.taxNumber);
    }
}
