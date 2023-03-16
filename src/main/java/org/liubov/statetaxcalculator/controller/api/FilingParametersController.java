package org.liubov.statetaxcalculator.controller.api;

import org.liubov.statetaxcalculator.dto.FilingParametersDTO;
import org.liubov.statetaxcalculator.service.FilingParametersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/filing_parameters")
public class FilingParametersController {

    private FilingParametersService filingParametersService;

    public FilingParametersController(FilingParametersService filingParametersService) {
        this.filingParametersService = filingParametersService;
    }

    @PostMapping("/save_test")
    public void saveFilingParameters() {
        FilingParametersDTO filingParametersDTO = new FilingParametersDTO();
        filingParametersDTO.setUserId(1);
        filingParametersDTO.setYear("2022");
        filingParametersDTO.setState("CA");
        filingParametersDTO.setFilingStatus("Single");
        filingParametersDTO.setIncome(150_000.0);
        filingParametersDTO.setStateTaxAmount(16_500.50);
        filingParametersDTO.setFederalTaxAmount(20_000.53);
        filingParametersDTO.setFicaTaxAmount(16_500.50);
        filingParametersDTO.setAdditionalMedicareTaxAmount(20_000.53);
        filingParametersDTO.setTotalTaxAmount(20_000.53);

        filingParametersService.save(filingParametersDTO);
    }

    @GetMapping("/find_by_user_id_test")
    public List<FilingParametersDTO> findByUserId() {
        return filingParametersService.findByUserId(1);
    }
}
