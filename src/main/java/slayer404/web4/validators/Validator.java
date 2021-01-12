package slayer404.web4.validators;

import slayer404.web4.exceptions.ValidationException;

public abstract class Validator {

    public abstract void validate(String value) throws ValidationException;

    public void isEmpty(String value, String name) throws ValidationException {
        if (value == null || value.equals("")) {
            throw new ValidationException("Value " + name + " wasn't entered.\n");
        }
    }

    protected double handleValue(String value) {
        double val = Double.parseDouble(value);
        if (value.contains(".") || value.contains(",")) {
            value = value.replace(",", ".");
            if (value.length() > 8) {
                val = Double.parseDouble(value.substring(0, 5));
            }
        }
        return val;
    }
}