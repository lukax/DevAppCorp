package br.uff.ic.devappcorp.entities;

import br.uff.ic.devappcorp.entities.base.ValueObject;
import br.uff.ic.devappcorp.utils.Result;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAddress extends ValueObject<EmailAddress> {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final String email;

    private EmailAddress(String email){
        this.email = email;
    }

    public static Result<EmailAddress> create(String email){
        if(email == null || email.trim().length() == 0){
            return Result.fail("Email should not be empty.");
        }
        if(email.length() > 256){
            return Result.fail("Email is too long.");
        }
        if(!validateEmail(email)){
            return Result.fail("Invalid email address");
        }
        return Result.ok(new EmailAddress(email));
    }

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }

    public String getValue() {
        return email;
    }

    @Override
    public boolean equals(EmailAddress obj) {
        return this.email.equals(obj.email);
    }
}
