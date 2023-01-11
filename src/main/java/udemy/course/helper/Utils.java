package udemy.course.helper;

import java.text.*;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Utils {

    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    static NumberFormat numberFormat = new DecimalFormat("R$ #,##0.00", new DecimalFormatSymbols(new Locale ("pt", "BR")));
    public static String dateToString(Date date) {
        return Utils.dateFormat.format(date);
    }

    public static String doubleToString(Double value) {
        return Utils.numberFormat.format(value);
    }

    public static Double stringToDouble(String value) {
        try {
            return (Double)Utils.numberFormat.parse(value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static void pause(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            System.out.println("Erro ao pausar por " +seconds+ " segundos.");
            throw new RuntimeException(e);
        }
    }

}
