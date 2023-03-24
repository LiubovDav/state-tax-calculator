package org.liubov.statetaxcalculator.service;

import org.liubov.statetaxcalculator.exception.IncomeTaxCalculatorException;

import java.math.BigDecimal;

public interface TaxCalculatorService {

    BigDecimal calculateStateTax(int year, String state, String filingStatus, BigDecimal income) throws IncomeTaxCalculatorException;

    BigDecimal calculateFederalTax(int year, String filingStatus, BigDecimal income) throws IncomeTaxCalculatorException;

    BigDecimal calculateSocialSecurityTax(int year, BigDecimal income);

    BigDecimal calculateMedicareTax(int year, BigDecimal income);

    BigDecimal calculateAdditionalMedicareTax(int year, String filingStatus, BigDecimal income);

    BigDecimal calculateEffectiveTaxRate(BigDecimal tax, BigDecimal income);
}
