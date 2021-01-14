package slayer404.web4.validators;

import slayer404.web4.exceptions.ValidationException;

public class ValidatorR extends Validator {

    @Override
    public void validate(String value) throws ValidationException {

        isEmpty(value, "R");

        try {
            int val = Integer.parseInt(value);

            boolean f = false;
            for (int i = -5; i <= 5; i++) {
                if (val == i) {
                    f = true;
                }
            }

            if(!f) {
                throw new ValidationException("Value R is incorrect.\n");
            }

            throw new ValidationException("Value R is incorrect.\n");

        } catch (NumberFormatException e) {
            throw new ValidationException("Value  R  is incorrect.\n");
        }
    }
}