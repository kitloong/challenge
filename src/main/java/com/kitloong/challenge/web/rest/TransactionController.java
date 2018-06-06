package com.kitloong.challenge.web.rest;

import com.kitloong.challenge.dto.TransactionDto;
import com.kitloong.challenge.service.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Copyright 2018 Allure Systems.
 * Created by Kit Loong on 2018/06/06.
 */

@RestController
public class TransactionController {
    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    public TransactionController() {

    }

    @GetMapping("transactions")
    public ResponseEntity<String> getTransaction() {
        log.info("get transactionDto");
        log.info("{}", TimeUtil.epochTimeNow());
        return new ResponseEntity<>("Transaction body", HttpStatus.CREATED);
    }

    @PostMapping("transactions")
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        log.info("transactionDto {}", transactionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
