package slayer404.web4.validators;

public abstract class Validator {

    public abstract boolean validate(String value, StringBuilder message);

    public boolean isEmpty(String value, String name, StringBuilder message) {
        if (value == null || value.equals("")) {
            message.append("Value " + name + " wasn't entered.\n");
            return true;
        }
        return false;
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