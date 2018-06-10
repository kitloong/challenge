package com.kitloong.challenge.statistic;

import com.kitloong.challenge.ChallengeApplication;
import com.kitloong.challenge.service.StatisticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Kit Loong on 10/6/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeApplication.class)
@AutoConfigureMockMvc
public class StatisticTest {

    private final Logger log = LoggerFactory.getLogger(StatisticTest.class);

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getStatistic() throws Exception {
        mockMvc.perform(get("/statistics"))
                .andExpect(status().isOk());
    }
}
