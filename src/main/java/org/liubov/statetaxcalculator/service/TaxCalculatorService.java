package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.exception.IncomeTaxCalculatorException;

public interface TaxCalculatorService {

    double calculateStateTax(int year, String state, String filingStatus, int income) throws IncomeTaxCalculatorException;

    double calculateFederalTax(int year, String filingStatus, int income) throws IncomeTaxCalculatorException;

    double calculateFicaTax(int year, int income);

    double calculateAdditionalMedicareTax(int year, String filingStatus, int income);

    double calculateEffectiveTaxRate(double tax, int income);
}
