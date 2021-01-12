package slayer404.web4.validators;

import slayer404.web4.exceptions.ValidationException;

public class ValidatorR extends Validator {

    @Override
    public void validate(String value) throws ValidationException {

        isEmpty(value, "R");


        try {
            int val = Integer.parseInt(value);

            for (int i = -5; i <= 5; i++) {
                if (val == i) {

                }
            }

            throw new ValidationException("Value X is incorrect.\n");

        } catch (NumberFormatException e) {
            throw new ValidationException("Value  R  is incorrect.\n");
        }
    }
}