package com.kitloong.challenge.service;

import com.kitloong.challenge.dto.StatisticDto;
import com.kitloong.challenge.dto.TransactionDto;
import com.kitloong.challenge.entity.CollectedTransaction;
import com.kitloong.challenge.service.data.Data;
import com.kitloong.challenge.service.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Kit Loong on 10/6/2018.
 */
@Service
public class StatisticService {
    private static final Logger log = LoggerFactory.getLogger(StatisticService.class);

    private TransactionService transactionService;

    public StatisticService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * For manual test only
     *
     * @throws InterruptedException
     */
    public void test() throws InterruptedException {
        log.info("get transactionDto");
        log.info("time now {} ", TimeUtil.epochTimeNow());
        this.transactionService.collect(new TransactionDto(20.00, TimeUtil.epochTimeNow() - 70000));
        this.transactionService.collect(new TransactionDto(20.00, TimeUtil.epochTimeNow() - 70000));
        this.transactionService.collect(new TransactionDto(10.00, TimeUtil.epochTimeNow() - 50000));
        this.transactionService.collect(new TransactionDto(12.00, TimeUtil.epochTimeNow() - 40000));
        this.transactionService.collect(new TransactionDto(14.00, TimeUtil.epochTimeNow() - 30000));
        this.transactionService.collect(new TransactionDto(9.00, TimeUtil.epochTimeNow() - 30000));
        this.transactionService.collect(new TransactionDto(15.00, TimeUtil.epochTimeNow() - 30000));
        this.transactionService.collect(new TransactionDto(16.00, TimeUtil.epochTimeNow() - 20000));
        this.transactionService.collect(new TransactionDto(5.00, TimeUtil.epochTimeNow() - 10000));
        this.transactionService.collect(new TransactionDto(18.00, TimeUtil.epochTimeNow() - 40000));

        log.info("Total collected {}", Data.collectedTransaction);

        this.getStatistic();
    }

    /**
     * Compute statistic from collected transactions
     */
    public StatisticDto getStatistic() {
        final long timeNow = TimeUtil.epochTimeNow() / 1000;
        final long timeOneMinuteAgo = timeNow - 60;

        log.info("time now {}", timeNow);
        log.info("time one minute ago {}", timeOneMinuteAgo);

        // final List<CollectedTransaction> transactionToReport = new ArrayList<>();

        double sum = 0;
        double min = 0;
        double max = 0;
        long count = 0;

        for (long i = timeNow; i >= timeOneMinuteAgo; i--) {
            final CollectedTransaction value = Data.collectedTransaction.get(i);
            if (value != null) {
                sum += value.getTotalAmount();
                count += value.getTotalCollected();

                if (value.getMax() > max) {
                    max = value.getMax();
                }

                if (min == 0) {
                    min = value.getMin();
                } else if (value.getMin() < min) {
                    min = value.getMin();
                }
            }
            log.info("time {}", i);
            log.info("contain {}", Data.collectedTransaction.get(i));
        }

        /*TransactionDto max = Data.collectedMaxAmountTransaction.entrySet().stream()
                .max(Comparator.comparingDouble(entry -> entry.getValue().getAmount()))
                .orElse(new HashMap.SimpleEntry<>(0L, new TransactionDto(0.0, 0L)))
                .getValue();

        TransactionDto min = Data.collectedMinAmountTransaction.entrySet().stream()
                .min(Comparator.comparingDouble(entry -> entry.getValue().getAmount()))
                .orElse(new HashMap.SimpleEntry<>(0L, new TransactionDto(0.0, 0L)))
                .getValue();

        final long count = transactionToReport.stream().mapToLong(CollectedTransaction::getTotalCollected).sum();
        final double sum = transactionToReport.stream().mapToDouble(CollectedTransaction::getTotalAmount).sum();*/

        log.info("Max {}", max);
        log.info("Min {}", min);
        log.info("Count {}", count);
        log.info("Sum {}", sum);
        log.info("Avg {}", (count != 0 ? sum / count : 0));

        return new StatisticDto(
                sum,
                (count != 0 ? sum / count : 0),
                max,
                min,
                count
        );
    }

    /**
     * To clear transaction more than 60 seconds
     * Run every second
     */
    @Scheduled(cron = "* * * * * *")
    public void removeObsoleteTransaction() {
        final long timeNow = TimeUtil.epochTimeNow() / 1000;
        final long timeOneMinuteAgo = timeNow - 60;

        final Set<Long> set = new HashSet<>();
        for (long i = timeNow; i >= timeOneMinuteAgo; i--) {
            set.add(i);
        }
        Data.collectedTransaction.keySet().retainAll(set);

        log.info("Cleaned collectedTransaction {}", Data.collectedTransaction);
        log.info("removeObsoleteTransaction ran");
    }
}
