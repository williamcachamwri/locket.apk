package com.locket.Locket.Streaks;

import com.locket.Locket.Utils.DateUtil;
import com.tencent.mmkv.MMKV;
import java.time.LocalDate;
import java.util.Random;

public class StreakConfig {
    private static final Random RANDOM = new Random();
    private final boolean enableStreakRecord;
    private final boolean enabled;
    private final int minimumDisplayCount;

    public StreakConfig(boolean z, boolean z2, int i) {
        this.enabled = z;
        this.enableStreakRecord = z2;
        this.minimumDisplayCount = i;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isStreakRecordEnabled() {
        return this.enableStreakRecord;
    }

    public int getMinimumDisplayCount() {
        return this.minimumDisplayCount;
    }

    public synchronized boolean shouldFetchStreak(Streak streak) {
        int i;
        boolean z = false;
        if (!isEnabled()) {
            return false;
        }
        if (DateUtil.getGregorianYYYYMMDD(LocalDate.now()) <= (streak != null ? streak.getLastUpdatedYYYYMMDD() : 0)) {
            return false;
        }
        MMKV defaultMMKV = MMKV.defaultMMKV();
        int decodeInt = defaultMMKV.decodeInt("streakDelayCountRemaining", getRandomDelay());
        if (decodeInt == 0) {
            i = getRandomDelay();
            z = true;
        } else {
            i = decodeInt - 1;
        }
        defaultMMKV.encode("streakDelayCountRemaining", i);
        return z;
    }

    private static int getRandomDelay() {
        return RANDOM.nextInt(10);
    }
}
