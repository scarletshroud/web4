package slayer404.web4.validators;

import slayer404.web4.exceptions.ValidationException;

public class ValidatorY extends Validator{

    @Override
    public void validate(String value) throws ValidationException {

        isEmpty(value, "Y");

        try {
            double val = handleValue(value);

            if (!(val > -5 && val < 5)) {
                throw new ValidationException("Value Y out of range.\n");
            }
        } catch (NumberFormatException e) {
            throw new ValidationException("Value  Y  is incorrect.\n");
        }

    }
}