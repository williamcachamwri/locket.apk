package com.locket.Locket.Streaks;

import android.content.Context;
import com.locket.Locket.RemoteConfig;
import com.locket.Locket.Util;

public class StreakManager {
    private final Context context;
    private final StreakConfig streakConfig = RemoteConfig.androidStreakConfig();

    public StreakManager(Context context2) {
        this.context = context2;
    }

    public boolean shouldShowOnWidget(Streak streak) {
        if (this.context == null || streak == null || !this.streakConfig.isEnabled() || !isStreakSetAsVisibleByUser() || streak.getDisplayCount() <= this.streakConfig.getMinimumDisplayCount()) {
            return false;
        }
        return true;
    }

    private boolean isStreakSetAsVisibleByUser() {
        return this.context.getSharedPreferences("DATA", 0).getBoolean(Util.STREAK_VISIBLE_KEY, true);
    }
}
