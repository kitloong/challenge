package com.kitloong.challenge.transaction;

import com.kitloong.challenge.ChallengeApplication;
import com.kitloong.challenge.dto.StatisticDto;
import com.kitloong.challenge.dto.TransactionDto;
import com.kitloong.challenge.service.StatisticService;
import com.kitloong.challenge.service.TransactionService;
import com.kitloong.challenge.service.data.Data;
import com.kitloong.challenge.service.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by Kit Loong on 9/6/2018.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChallengeApplication.class)
@AutoConfigureMockMvc
public class TransactionTest {

    private final Logger log = LoggerFactory.getLogger(TransactionTest.class);

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StatisticService statisticService;

    @Autowired
    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Test
    public void collect() throws Exception {
        TransactionDto validTransactionDto = new TransactionDto(10.00, TimeUtil.epochTimeNow() - 50000);
        mockMvc.perform(post("/transactions")
                .content(this.json(validTransactionDto))
                .contentType(contentType)
        ).andExpect(status().isCreated());

        TransactionDto invalidTransactionDto = new TransactionDto(10.00, TimeUtil.epochTimeNow() - 70000);
        mockMvc.perform(post("/transactions")
                .content(this.json(invalidTransactionDto))
                .contentType(contentType)
        ).andExpect(status().isNoContent());
    }

    @Test
    public void multiCollectCheck() {

        log.info("get transactionDto");
        log.info("time now {} ", TimeUtil.epochTimeNow());

        // Shouldn't collect
        this.transactionService.collect(new TransactionDto(20.00, TimeUtil.epochTimeNow() - 70000));

        this.transactionService.collect(new TransactionDto(10.00, TimeUtil.epochTimeNow() - 50000));

        // Should merge
        this.transactionService.collect(new TransactionDto(12.00, TimeUtil.epochTimeNow() - 40000));
        this.transactionService.collect(new TransactionDto(18.00, TimeUtil.epochTimeNow() - 40000));
        // Should merge

        // Should merge
        this.transactionService.collect(new TransactionDto(14.00, TimeUtil.epochTimeNow() - 30000));
        this.transactionService.collect(new TransactionDto(9.00, TimeUtil.epochTimeNow() - 30000));
        this.transactionService.collect(new TransactionDto(15.00, TimeUtil.epochTimeNow() - 30000));
        // Should merge

        this.transactionService.collect(new TransactionDto(16.00, TimeUtil.epochTimeNow() - 20000));
        this.transactionService.collect(new TransactionDto(5.00, TimeUtil.epochTimeNow() - 10000));

        log.info("Total collected {}", Data.collectedTransaction);

        assertThat(Data.collectedTransaction.size()).isEqualTo(5);

        assertThat(Data.collectedMaxAmountTransaction.size()).isEqualTo(5);

        assertThat(Data.collectedMinAmountTransaction.size()).isEqualTo(5);

        StatisticDto statistic = this.statisticService.getStatistic();

        assertThat(statistic).isEqualTo(new StatisticDto(
                99.0,
                12.375,
                18.0,
                5.0,
                8
        ));

        log.info("Transaction test collect");
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
