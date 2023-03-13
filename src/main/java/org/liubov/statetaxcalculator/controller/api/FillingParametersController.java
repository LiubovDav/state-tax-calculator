package org.liubov.statetaxcalculator.controller.api;

import org.liubov.statetaxcalculator.dto.FillingParametersDTO;
import org.liubov.statetaxcalculator.service.FillingParametersService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/filling_parameters")
public class FillingParametersController {

    private FillingParametersService fillingParametersService;

    public FillingParametersController(FillingParametersService fillingParametersService) {
        this.fillingParametersService = fillingParametersService;
    }

    @PostMapping("/save_test")
    public void saveFillingParameters() {
        FillingParametersDTO fillingParametersDTO = new FillingParametersDTO();
        fillingParametersDTO.setUserId(1);
        fillingParametersDTO.setYear("2022");
        fillingParametersDTO.setState("CA");
        fillingParametersDTO.setFillingStatus("Single");
        fillingParametersDTO.setIncome("150000");
        fillingParametersDTO.setStateTaxAmount(16_500.50);
        fillingParametersDTO.setFederalTaxAmount(20_000.53);
        fillingParametersDTO.setFicaTaxAmount(16_500.50);
        fillingParametersDTO.setAdditionalMedicareTaxAmount(20_000.53);
        fillingParametersDTO.setTotalTaxAmount(20_000.53);

        fillingParametersService.save(fillingParametersDTO);
    }

    @GetMapping("/find_by_user_id_test")
    public List<FillingParametersDTO> findByUserId() {
        return fillingParametersService.findByUserId(1);
    }
}
