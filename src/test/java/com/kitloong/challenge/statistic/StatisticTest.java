package com.kitloong.challenge.statistic;

import com.kitloong.challenge.ChallengeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Kit Loong on 10/6/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeApplication.class)
public class StatisticTest {

    private final Logger log = LoggerFactory.getLogger(StatisticTest.class);

    @Test
    public void compute() {
        assertThat(true).isTrue();
        log.info("Statistic test compute");
    }
}
