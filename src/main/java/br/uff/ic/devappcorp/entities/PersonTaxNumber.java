package br.uff.ic.devappcorp.entities;

import br.uff.ic.devappcorp.entities.base.ValueObject;
import br.uff.ic.devappcorp.utils.Result;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class PersonTaxNumber extends ValueObject<PersonTaxNumber> {
    private final String taxNumber;

    private PersonTaxNumber(String taxNumber){
        this.taxNumber = taxNumber;
    }

    public static Result<PersonTaxNumber> create(String taxNumber){
        if(taxNumber == null || !StringUtils.hasText(taxNumber)){
            return Result.fail("Tax number cannot be empty.");
        }
        if(!validadeNumberOfDigits(taxNumber)){
            return Result.fail("Tax number invalid.");
        }
        return Result.ok(new PersonTaxNumber(taxNumber));
    }

    private static boolean validadeNumberOfDigits(String taxNumber) {
        return taxNumber.trim().length() == 11  // CPF
            || taxNumber.trim().length() == 14; // CNPJ
    }

    public String getValue() {
        return taxNumber;
    }

    @Override
    public boolean equals(PersonTaxNumber obj) {
        return this.taxNumber.equals(obj.taxNumber);
    }
}
