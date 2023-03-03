package org.liubov.statetaxcalculator.logic;

public class TaxCalculator {

    public double calculateTax(String state, String status, int income) {
        double tax = 0;

        switch (status) {
            case "Single":
                if (income > 8_350) {
                    tax += 8_350 * 10 / 100;
                    if (income > 33_950) {
                        tax += (33_950 - 8_350) * 15 / 100;
                        if (income > 82_250) {
                            tax += (82_250 - 33_950) * 25 / 100;
                            if (income > 171_550) {
                                tax += (171_550 - 82_250) * 28 / 100;
                                if (income > 372_950) {
                                    tax += (372_950 - 171_550) * 33 / 100;
                                    if (income > 372_951) {
                                        tax += (income - 372_951) * 35 / 100;
                                    }
                                } else {
                                    tax += (income - 171_550) * 33 / 100;
                                }
                            } else {
                                tax += (income - 82_250) * 28 / 100;
                            }
                        } else {
                            tax += (income - 33_950) * 25 / 100;
                        }
                    } else {
                        tax += (income - 8_350) * 15 / 100;
                    }
                } else {
                    tax += income * 10 / 100;
                }
                break;
                
            case "Married filing jointly or qualifying widow":
                if (income > 16_700) {
                    tax += 16_700 * 10 / 100;
                    if (income > 67_900) {
                        tax += (67_900 - 16_700) * 15 / 100;
                        if (income > 137_050) {
                            tax += (137_050 - 67_900) * 25 / 100;
                            if (income > 208_850) {
                                tax += (208_850 - 137_050) * 28 / 100;
                                if (income > 372_950) {
                                    tax += (372_950 - 208_850) * 33 / 100;
                                    if (income > 372_951) {
                                        tax += (income - 372_951) * 35 / 100;
                                    }
                                } else {
                                    tax += (income - 208_850) * 33 / 100;
                                }
                            } else {
                                tax += (income - 137_050) * 28 / 100;
                            }
                        } else {
                            tax += (income - 67_900) * 25 / 100;
                        }
                    } else {
                        tax += (income - 16_700) * 15 / 100;
                    }
                } else {
                    tax += income * 10 / 100;
                }
                break;
                
            case "Married filing separately":
                if (income > 8_350) {
                    tax += 8_350 * 10 / 100;
                    if (income > 33_950) {
                        tax += (33_950 - 8_350) * 15 / 100;
                        if (income > 68_525) {
                            tax += (68_525 - 33_950) * 25 / 100;
                            if (income > 104_425) {
                                tax += (104_425 - 68_525) * 28 / 100;
                                if (income > 186_475) {
                                    tax += (186_475 - 104_425) * 33 / 100;
                                    if (income > 186_475) {
                                        tax += (income - 186_475) * 35 / 100;
                                    }
                                } else {
                                    tax += (income - 104_425) * 33 / 100;
                                }
                            } else {
                                tax += (income - 68_525) * 28 / 100;
                            }
                        } else {
                            tax += (income - 33_950) * 25 / 100;
                        }
                    } else {
                        tax += (income - 8_350) * 15 / 100;
                    }
                } else {
                    tax += income * 10 / 100;
                }
                break;

            case "Head of household":
                if (income > 11_950) {
                    tax += 11_950 * 10 / 100;
                    if (income > 45_500) {
                        tax += (45_500 - 11_950) * 15 / 100;
                        if (income > 117_450) {
                            tax += (117_450 - 45_500) * 25 / 100;
                            if (income > 190_200) {
                                tax += (190_200 - 117_450) * 28 / 100;
                                if (income > 372_950) {
                                    tax += (372_950 - 190_200) * 33 / 100;
                                    if (income > 372_951) {
                                        tax += (income - 372_951) * 35 / 100;
                                    }
                                } else {
                                    tax += (income - 190_200) * 33 / 100;
                                }
                            } else {
                                tax += (income - 117_450) * 28 / 100;
                            }
                        } else {
                            tax += (income - 45_500) * 25 / 100;
                        }
                    } else {
                        tax += (income - 11_950) * 15 / 100;
                    }
                } else {
                    tax += income * 10 / 100;
                }
                break;
        }

        return tax;
    }

    public double calculateEffectiveRate(double tax, int income) {
        return tax / income * 100;
    }
}
