package com.kitloong.challenge.service.data;

import com.kitloong.challenge.dto.TransactionDto;
import com.kitloong.challenge.entity.CollectedTransaction;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Kit Loong on 6/6/2018.
 */
public final class Data {
    /**
     * <UnixTimestamp, CollectedTransaction>
     */
    public static final ConcurrentHashMap<Long, CollectedTransaction> collectedTransaction = new ConcurrentHashMap<>();

    public static final ConcurrentHashMap<Long, TransactionDto> collectedMaxAmountTransaction = new ConcurrentHashMap<>();

    public static final ConcurrentHashMap<Long, TransactionDto> collectedMinAmountTransaction = new ConcurrentHashMap<>();
}
