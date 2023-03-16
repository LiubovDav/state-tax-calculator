package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.exception.IncomeTaxCalculatorException;

public interface TaxCalculatorService {

    double calculateStateTax(int year, String state, String filingStatus, double income) throws IncomeTaxCalculatorException;

    double calculateFederalTax(int year, String filingStatus, double income) throws IncomeTaxCalculatorException;

    double calculateFicaTax(int year, double income);

    double calculateAdditionalMedicareTax(int year, String filingStatus, double income);

    double calculateEffectiveTaxRate(double tax, double income);
}
