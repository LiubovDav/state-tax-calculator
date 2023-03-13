package org.liubov.statetaxcalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
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
    public double calculateStateTax(int year, String state, String fillingStatus, int income) {
        if (state.equals("Alaska") || state.equals("Florida") || state.equals("Nevada")
                || state.equals("New Hampshire") || state.equals("South Dakota") || state.equals("Tennessee")
                || state.equals("Texas") || state.equals("Washington") || state.equals("Wyoming")) {
            return 0.00;
        }

        if (year != 2022 && year != 2023) {
            year = 2022;
        }

        state = "California";

        List<StateTaxBracket> stateTaxBracketList = stateTaxBracketRepository.findByYearAndStateAndFillingStatusOrderByBracketLowerDesc(year, state, fillingStatus);

        if (stateTaxBracketList != null && stateTaxBracketList.size() > 0) {
            for (StateTaxBracket stateTaxBracket : stateTaxBracketList) {
                if (income >= stateTaxBracket.getBracketLower()) {
                    return Math.round((stateTaxBracket.getAccumulatedAmount() + (income - stateTaxBracket.getBracketLower()) * stateTaxBracket.getRate()) * 100) / 100;
                }
            }
        }

        // todo: implement
//        throw new IncomeTaxCalculatorException("Not enough IRS data to process the request");
        return Math.round(income * 0.12 * 100) / 100;
    }

    @Override
    public double calculateFederalTax(int year, String fillingStatus, int income) {
        if (year != 2022 && year != 2023) {
            year = 2022;
        }

        List<FederalTaxBracket> federalTaxBracketList = federalTaxBracketRepository.findByYearAndFillingStatusOrderByBracketLowerDesc(year, fillingStatus);

        if (federalTaxBracketList != null && federalTaxBracketList.size() > 0) {
            for (FederalTaxBracket federalTaxBracket : federalTaxBracketList) {
                if (income >= federalTaxBracket.getBracketLower()) {
                    return Math.round((federalTaxBracket.getAccumulatedAmount() + (income - federalTaxBracket.getBracketLower()) * federalTaxBracket.getRate()) * 100) / 100;
                }
            }
        }

        // todo: implement
//        throw new IncomeTaxCalculatorException("Not enough IRS data to process the request");
        return Math.round(income * 0.15 * 100) / 100;
    }

    @Override
    public double calculateFicaTax(int year, int income) {
        if (year == 2023) {
            if (income < 160_200) {
                return Math.round(income * 7.65) / 100;
            } else {
                return Math.round(160_200 * 7.65) / 100;
            }
        } else {
            if (income < 147_000) {
                return Math.round(income * 7.65) / 100;
            } else {
                return Math.round(147_000 * 7.65) / 100;
            }
        }
    }

    @Override
    public double calculateAdditionalMedicareTax(int year, String fillingStatus, int income) {
        if (fillingStatus.equals("Single") || fillingStatus.equals("Married filing separately")) {
            if (income > 200_000) {
                return Math.round((income - 200_000) * 0.9) / 100;
            }
        } else {
            if (income > 250_000) {
                return Math.round((income - 250_000) * 0.9) / 100;
            }
        }

        return 0.00;
    }

    @Override
    public double calculateEffectiveTaxRate(double tax, int income) {
        return Math.round(tax / income * 100 * 100) / 100;
    }
}
