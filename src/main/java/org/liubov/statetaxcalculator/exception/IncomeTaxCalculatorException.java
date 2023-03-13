package org.liubov.statetaxcalculator.exception;

public class IncomeTaxCalculatorException extends Exception {

    public IncomeTaxCalculatorException() {
        super();
    }

    public IncomeTaxCalculatorException(String message) {
        super(message);
    }

    public IncomeTaxCalculatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncomeTaxCalculatorException(Throwable cause) {
        super(cause);
    }

    protected IncomeTaxCalculatorException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
