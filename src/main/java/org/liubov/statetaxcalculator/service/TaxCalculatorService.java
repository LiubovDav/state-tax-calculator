package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.exception.IncomeTaxCalculatorException;

public interface TaxCalculatorService {

    double calculateStateTax(int year, String state, String fillingStatus, int income) throws IncomeTaxCalculatorException;

    double calculateFederalTax(int year, String fillingStatus, int income) throws IncomeTaxCalculatorException;

    double calculateFicaTax(int year, int income);

    double calculateAdditionalMedicareTax(int year, String fillingStatus, int income);

    double calculateEffectiveTaxRate(double tax, int income);
}
