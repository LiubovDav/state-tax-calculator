package org.liubov.statetaxcalculator.service;

public interface TaxCalculatorService {

    double calculateStateTax(int year, String state, String fillingStatus, int income);

    double calculateFederalTax(int year, String fillingStatus, int income);

    double calculateFicaTax(int year, int income);

    double calculateAdditionalMedicareTax(int year, String fillingStatus, int income);

    double calculateEffectiveTaxRate(double tax, int income);
}
