package slayer404.web4.utils;

public class HitChecker {
    public static boolean isInArea(String firstVal, String secondVal, String thirdVal) {
        try {

            double x = Double.parseDouble(firstVal);
            double y = Double.parseDouble(secondVal);
            int r = Integer.parseInt(thirdVal);

            return ((x > -r / 2.0 && x < 0 && y > -r && y < 0)
                    || (x > 0 && y <= 0 && x * x + y * y < r / 2.0 * r / 2.0)
                    || (x > 0 && y <= 0 && y > 0.5 * x - r));

        } catch(NumberFormatException ex) {
            return false;
        }
    }
}
