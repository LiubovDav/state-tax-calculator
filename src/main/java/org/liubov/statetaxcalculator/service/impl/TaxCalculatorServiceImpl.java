package org.liubov.statetaxcalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.exception.IncomeTaxCalculatorException;
import org.liubov.statetaxcalculator.model.FederalTaxBracket;
import org.liubov.statetaxcalculator.model.StateTaxBracket;
import org.liubov.statetaxcalculator.repository.FederalTaxBracketRepository;
import org.liubov.statetaxcalculator.repository.StateTaxBracketRepository;
import org.liubov.statetaxcalculator.service.TaxCalculatorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TaxCalculatorServiceImpl implements TaxCalculatorService {

    private StateTaxBracketRepository stateTaxBracketRepository;

    private FederalTaxBracketRepository federalTaxBracketRepository;

    public TaxCalculatorServiceImpl(StateTaxBracketRepository stateTaxBracketRepository, FederalTaxBracketRepository federalTaxBracketRepository) {
        this.stateTaxBracketRepository = stateTaxBracketRepository;
        this.federalTaxBracketRepository = federalTaxBracketRepository;
    }

    @Override
    public double calculateStateTax(int year, String state, String filingStatus, double income) throws IncomeTaxCalculatorException {
        if (state.equals("Alaska") || state.equals("Florida") || state.equals("Nevada")
                || state.equals("New Hampshire") || state.equals("South Dakota") || state.equals("Tennessee")
                || state.equals("Texas") || state.equals("Washington") || state.equals("Wyoming")) {
            return 0.00;
        }

        if (year != 2023) {
            year = 2023;
        }

        state = "California";


        if (filingStatus.equals("Married filing jointly")) {
            filingStatus = "Married Filing Jointly";
        } else {
            filingStatus = "Single Filer";
        }

        List<StateTaxBracket> stateTaxBracketList = stateTaxBracketRepository.findByYearAndStateAndFilingStatusOrderByBracketLowerDesc(year, state, filingStatus);

        if (stateTaxBracketList != null && stateTaxBracketList.size() > 0) {
            for (StateTaxBracket stateTaxBracket : stateTaxBracketList) {
                if (income >= stateTaxBracket.getBracketLower()) {
                    return Math.round((stateTaxBracket.getAccumulatedAmount() + (income - stateTaxBracket.getBracketLower()) * stateTaxBracket.getRate() / 100) * 100) / 100.0;
                }
            }
        }

        throw new IncomeTaxCalculatorException("Not enough IRS data to process the request");
    }

    @Override
    public double calculateFederalTax(int year, String filingStatus, double income) throws IncomeTaxCalculatorException {
        if (year != 2022 || year != 2023) {
            year = 2023;
        }

        List<FederalTaxBracket> federalTaxBracketList = federalTaxBracketRepository.findByYearAndFilingStatusOrderByBracketLowerDesc(year, filingStatus);

        if (federalTaxBracketList != null && federalTaxBracketList.size() > 0) {
            for (FederalTaxBracket federalTaxBracket : federalTaxBracketList) {
                if (income >= federalTaxBracket.getBracketLower()) {
                    return Math.round((federalTaxBracket.getAccumulatedAmount() + (income - federalTaxBracket.getBracketLower()) * federalTaxBracket.getRate() / 100) * 100) / 100.0;
                }
            }
        }

        throw new IncomeTaxCalculatorException("Not enough IRS data to process the request");
    }

    @Override
    public double calculateFicaTax(int year, double income) {
        if (year == 2023) {
            if (income < 160_200) {
                return Math.round(income * 7.65) / 100.0;
            } else {
                return Math.round(160_200 * 7.65) / 100.0;
            }
        } else {
            if (income < 147_000) {
                return Math.round(income * 7.65) / 100.0;
            } else {
                return Math.round(147_000 * 7.65) / 100.0;
            }
        }
    }

    @Override
    public double calculateAdditionalMedicareTax(int year, String filingStatus, double income) {
        if (filingStatus.equals("Single") || filingStatus.equals("Married filing separately")) {
            if (income > 200_000) {
                return Math.round((income - 200_000) * 0.9) / 100.0;
            }
        } else {
            if (income > 250_000) {
                return Math.round((income - 250_000) * 0.9) / 100.0;
            }
        }

        return 0.00;
    }

    @Override
    public double calculateEffectiveTaxRate(double tax, double income) {
        return Math.round(tax / income * 100 * 100) / 100.0;
    }
}
