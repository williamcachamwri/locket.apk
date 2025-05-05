package com.facebook.soloader.recovery;

import android.content.Context;

public class DefaultRecoveryStrategyFactory implements RecoveryStrategyFactory {
    private final BaseApkPathHistory mBaseApkPathHistory;
    private final Context mContext;

    public DefaultRecoveryStrategyFactory(Context context) {
        this.mContext = context;
        BaseApkPathHistory baseApkPathHistory = new BaseApkPathHistory(5);
        this.mBaseApkPathHistory = baseApkPathHistory;
        baseApkPathHistory.recordPathIfNew(context.getApplicationInfo().sourceDir);
    }

    public RecoveryStrategy get() {
        return new CompositeRecoveryStrategy(new WaitForAsyncInit(), new DetectDataAppMove(this.mContext, this.mBaseApkPathHistory), new ReunpackBackupSoSources(), new ReunpackNonBackupSoSources(), new WaitForAsyncInit(), new CheckBaseApkExists(this.mContext, this.mBaseApkPathHistory));
    }
}
