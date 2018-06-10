package com.kitloong.challenge.web.rest;

import com.kitloong.challenge.dto.StatisticDto;
import com.kitloong.challenge.service.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Kit Loong on 10/6/2018.
 */
@RestController
public class StatisticController {
    private static Logger log = LoggerFactory.getLogger(StatisticController.class);

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("statistics")
    public ResponseEntity<StatisticDto> getStatistic() {
        log.info("get statisticDto");
        StatisticDto statistic = this.statisticService.getStatistic();
        return new ResponseEntity<>(statistic, HttpStatus.CREATED);
    }
}
