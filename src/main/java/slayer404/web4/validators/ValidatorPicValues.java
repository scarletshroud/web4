package slayer404.web4.validators;

import slayer404.web4.exceptions.ValidationException;

public class ValidatorPicValues extends Validator{
    @Override
    public void validate(String value, String name) throws ValidationException {
        isEmpty(value, name);

        try {
            double val = Double.parseDouble(value);

        } catch (NumberFormatException e) {
            throw new ValidationException("Value " + name + " is incorrect.\n");
        }
    }
}
