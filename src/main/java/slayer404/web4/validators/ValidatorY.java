package slayer404.web4.validators;

public class ValidatorY extends Validator{

    @Override
    public boolean validate(String value, StringBuilder message) {

        if (isEmpty(value, "Y", message)) {
            return false;
        }

        try {
            double val = handleValue(value);

            if (!(val > -5 && val < 5)) {
                message.append("Value Y out of range.\n");
                return false;
            }
        } catch (NumberFormatException e) {
            message.append("Value  Y  is incorrect.\n");
            return false;
        }

        return true;

    }
}