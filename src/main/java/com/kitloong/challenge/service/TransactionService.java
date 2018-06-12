package com.kitloong.challenge.service;

import com.kitloong.challenge.dto.TransactionDto;
import com.kitloong.challenge.entity.CollectedTransaction;
import com.kitloong.challenge.service.data.Data;
import com.kitloong.challenge.service.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Kit Loong on 6/6/2018.
 */

@Service
public class TransactionService {
    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    private long acceptedDurationInNano = 60 * 1000;

    public TransactionService() {

    }

    public boolean collect(TransactionDto transactionDto) {
        final long acceptedTime = TimeUtil.epochTimeNow() - acceptedDurationInNano;
        if (transactionDto.getTimestamp() >= acceptedTime && transactionDto.getTimestamp() <= TimeUtil.epochTimeNow()) {

            Data.collectedTransaction.compute(transactionDto.getTimestamp() / 1000, (Long key, CollectedTransaction value) -> {
                if (value == null) {
                    return new CollectedTransaction(1, transactionDto.getAmount(), transactionDto.getAmount(), transactionDto.getAmount());
                } else {
                    value.setTotalCollected(value.getTotalCollected() + 1);
                    value.setTotalAmount(value.getTotalAmount() + transactionDto.getAmount());

                    if (value.getMax() < transactionDto.getAmount()) {
                        value.setMax(transactionDto.getAmount());
                    }

                    if (value.getMin() > transactionDto.getAmount()) {
                        value.setMin(transactionDto.getAmount());
                    }
                    return value;
                }
            });

            return true;
        }
        return false;
    }
}
