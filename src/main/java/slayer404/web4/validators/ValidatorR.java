package slayer404.web4.validators;

public class ValidatorR extends Validator {

    @Override
    public boolean validate(String value, StringBuilder message) {

        if (isEmpty(value, "R", message)) {
            return false;
        }

        try {
            int val = Integer.parseInt(value);

            for (int i = -5; i <= 5; i++) {
                if (val == i) {
                    return true;
                }
            }

            message.append("Value X is incorrect.\n");
            return false;

        } catch (NumberFormatException e) {
            message.append("Value  R  is incorrect.\n");
            return false;
        }
    }
}