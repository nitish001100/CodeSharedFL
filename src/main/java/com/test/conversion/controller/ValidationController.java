package com.test.conversion.controller;

import com.test.conversion.response.GenericResponse;
import com.test.conversion.service.ValidationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/v1")
public class ValidationController {

    @Autowired
    ValidationService validationService;

    @ApiOperation(value = "Validate SSN")
    @PostMapping(path = "/validate_ssn/{ssn}")
    @ApiImplicitParam(paramType = "header", dataTypeClass = String.class)
    public ResponseEntity<GenericResponse> validate_ssn(@PathVariable String ssn) throws Exception {
        Boolean status = validationService.validate_ssn(ssn);
        return new ResponseEntity<>(GenericResponse.builder()
                .message("Validation status : " + status).status(HttpStatus.CREATED.value())
                .repute(status)
                .build(), HttpStatus.CREATED);
    }
}
