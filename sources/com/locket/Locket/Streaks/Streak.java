package com.locket.Locket.Streaks;

import com.google.gson.annotations.SerializedName;
import com.locket.Locket.Utils.DateUtil;
import java.time.LocalDate;

public class Streak {
    @SerializedName("count")
    private int count;
    @SerializedName("last_updated_yyyymmdd")
    private int lastUpdatedYYYYMMDD;

    public Streak(int i, int i2) {
        this.count = i;
        this.lastUpdatedYYYYMMDD = i2;
    }

    public void setCount(int i) {
        this.count = i;
    }

    public int getCount() {
        return this.count;
    }

    public void setLastUpdatedYYYYMMDD(int i) {
        this.lastUpdatedYYYYMMDD = i;
    }

    public int getLastUpdatedYYYYMMDD() {
        return this.lastUpdatedYYYYMMDD;
    }

    public int getDisplayCount() {
        int gregorianYYYYMMDD = DateUtil.getGregorianYYYYMMDD(LocalDate.now().minusDays(1));
        if (isDailySendCompleted() || this.lastUpdatedYYYYMMDD == gregorianYYYYMMDD) {
            return this.count;
        }
        return 0;
    }

    public boolean isDailySendCompleted() {
        return this.lastUpdatedYYYYMMDD == DateUtil.getGregorianYYYYMMDD(LocalDate.now());
    }
}
