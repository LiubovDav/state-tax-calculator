package org.liubov.statetaxcalculator.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.liubov.statetaxcalculator.exception.IncomeTaxCalculatorException;
import org.liubov.statetaxcalculator.model.FederalTaxBracket;
import org.liubov.statetaxcalculator.model.StateTaxBracket;
import org.liubov.statetaxcalculator.repository.FederalTaxBracketRepository;
import org.liubov.statetaxcalculator.repository.StateTaxBracketRepository;
import org.liubov.statetaxcalculator.service.TaxCalculatorService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    public BigDecimal calculateStateTax(int year, String state, String filingStatus, BigDecimal income) throws IncomeTaxCalculatorException {
        if (state.equals("Alaska") || state.equals("Florida") || state.equals("Nevada")
                || state.equals("New Hampshire") || state.equals("South Dakota") || state.equals("Tennessee")
                || state.equals("Texas") || state.equals("Washington") || state.equals("Wyoming")) {
            return new BigDecimal(0);
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
                if (income.compareTo(stateTaxBracket.getBracketLower()) >= 0) {
                    return stateTaxBracket.getAccumulatedAmount().add(income.subtract(stateTaxBracket.getBracketLower()).multiply(stateTaxBracket.getRate().divide(new BigDecimal(100.00))))
                            .setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }
        }

        throw new IncomeTaxCalculatorException("Not enough IRS data to process the request");
    }

    @Override
    public BigDecimal calculateFederalTax(int year, String filingStatus, BigDecimal income) throws IncomeTaxCalculatorException {
        if (year != 2022 || year != 2023) {
            year = 2023;
        }

        List<FederalTaxBracket> federalTaxBracketList = federalTaxBracketRepository.findByYearAndFilingStatusOrderByBracketLowerDesc(year, filingStatus);

        if (federalTaxBracketList != null && federalTaxBracketList.size() > 0) {
            for (FederalTaxBracket federalTaxBracket : federalTaxBracketList) {
                if (income.compareTo(federalTaxBracket.getBracketLower()) >= 0) {
                    return federalTaxBracket.getAccumulatedAmount().add(income.subtract(federalTaxBracket.getBracketLower()).multiply(federalTaxBracket.getRate().divide(new BigDecimal(100.00))))
                            .setScale(2, BigDecimal.ROUND_HALF_UP);
                }
            }
        }

        throw new IncomeTaxCalculatorException("Not enough IRS data to process the request");
    }

    @Override
    public BigDecimal calculateSocialSecurityTax(int year, BigDecimal income) {
        if (year == 2023) {
            if (income.compareTo(new BigDecimal(160_200)) < 0) {
                return income.multiply(new BigDecimal(6.2).divide(new BigDecimal(100.00)))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            } else {
                return new BigDecimal(160_200).multiply(new BigDecimal(6.2)).divide(new BigDecimal(100.00))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        } else {
            if (income.compareTo(new BigDecimal(147_000)) < 0) {
                return income.multiply(new BigDecimal(6.2)).divide(new BigDecimal(100.00))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            } else {
                return new BigDecimal(147_000).multiply(new BigDecimal(6.2)).divide(new BigDecimal(100.00))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        }
    }

    @Override
    public BigDecimal calculateMedicareTax(int year, BigDecimal income) {
        if (year == 2023) {
            if (income.compareTo(new BigDecimal(160_200)) < 0) {
                return income.multiply(new BigDecimal(1.45).divide(new BigDecimal(100.00)))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            } else {
                return new BigDecimal(160_200).multiply(new BigDecimal(1.45)).divide(new BigDecimal(100.00))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        } else {
            if (income.compareTo(new BigDecimal(147_000)) < 0) {
                return income.multiply(new BigDecimal(1.45)).divide(new BigDecimal(100.00))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            } else {
                return new BigDecimal(147_000).multiply(new BigDecimal(1.45)).divide(new BigDecimal(100.00))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        }
    }

    @Override
    public BigDecimal calculateAdditionalMedicareTax(int year, String filingStatus, BigDecimal income) {
        if (filingStatus.equals("Single") || filingStatus.equals("Married filing separately")) {
            if (income.compareTo(new BigDecimal(200_000)) > 0) {
                return (income.subtract(new BigDecimal(200_000)).multiply(new BigDecimal(0.9)).divide(new BigDecimal(100.00)))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        } else {
            if (income.compareTo(new BigDecimal(250_000)) > 0) {
                return (income.subtract(new BigDecimal(250_000)).multiply(new BigDecimal(0.9)).divide(new BigDecimal(100.00)))
                        .setScale(2, BigDecimal.ROUND_HALF_UP);
            }
        }

        return new BigDecimal(0.00);
    }

    @Override
    public BigDecimal calculateEffectiveTaxRate(BigDecimal tax, BigDecimal income) {
        return tax.divide(income, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100.00))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
