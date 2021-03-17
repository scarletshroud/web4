package slayer404.web4.validators;

import slayer404.web4.exceptions.ValidationException;

public class ValidatorInt extends Validator {

    @Override
    public void validate(String value, String name) throws ValidationException {
        isEmpty(value, name);

        try {
            int val = Integer.parseInt(value);

            if(!(val >= -5 && val <= 3)) {
                throw new ValidationException("Value " + name + " is incorrect.\n");
            }

        } catch (NumberFormatException e) {
            throw new ValidationException("Value " + name + " is incorrect.\n");
        }
    }
}
