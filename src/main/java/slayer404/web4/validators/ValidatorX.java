package slayer404.web4.validators;

import slayer404.web4.exceptions.ValidationException;

public class ValidatorX extends Validator {

    @Override
    public void validate(String value) throws ValidationException {

        isEmpty(value, "X");

        try {
            int val = Integer.parseInt(value);

            boolean f = false;
            for (int i = -5; i <= 5; i++) {
                if (val == i) {
                    f = true;
                }
            }

            if(!f) {
                throw new ValidationException("Value X is incorrect.\n");
            }

        } catch (NumberFormatException e) {
            throw new ValidationException("Value  X  is incorrect.\n");
        }
    }
}
