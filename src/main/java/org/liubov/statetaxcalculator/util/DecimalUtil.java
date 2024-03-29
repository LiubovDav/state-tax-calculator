package org.liubov.statetaxcalculator.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

public class DecimalUtil {

    private static final String PATTERN = "#,##0.00";

    public static String toString(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            return "";
        }

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat(PATTERN, symbols);
        return decimalFormat.format(bigDecimal);
    }

    public static BigDecimal toBigDecimal(String number) throws ParseException {
        if (number == null) {
            return new BigDecimal(0.00);
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(',');
        symbols.setDecimalSeparator('.');

        DecimalFormat decimalFormat = new DecimalFormat(PATTERN, symbols);
        decimalFormat.setParseBigDecimal(true);

        return (BigDecimal) decimalFormat.parse(number);
    }
}
