package com.kitloong.challenge.web.rest;

import com.kitloong.challenge.dto.TransactionDto;
import com.kitloong.challenge.service.StatisticService;
import com.kitloong.challenge.service.TransactionService;
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

    private StatisticService statisticService;

    private TransactionService transactionService;

    public TransactionController(StatisticService statisticService, TransactionService transactionService) {
        this.statisticService = statisticService;
        this.transactionService = transactionService;
    }

    @GetMapping("test")
    public ResponseEntity<String> test() throws InterruptedException {
        this.statisticService.test();
        return new ResponseEntity<>("Ran test", HttpStatus.CREATED);
    }

    @PostMapping("transactions")
    public ResponseEntity<Void> collectTransaction(@Valid @RequestBody final TransactionDto transactionDto) {
        log.info("transactionDto {}", transactionDto);
        if (this.transactionService.collect(transactionDto)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
