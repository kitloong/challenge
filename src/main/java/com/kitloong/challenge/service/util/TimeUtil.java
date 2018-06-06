package com.kitloong.challenge.service.util;

import java.time.Instant;

/**
 * Copyright 2018 Allure Systems.
 * Created by Kit Loong on 2018/06/06.
 */

public final class TimeUtil {
    public static long epochTimeNow() {
        return Instant.now().getEpochSecond();
    }
}
