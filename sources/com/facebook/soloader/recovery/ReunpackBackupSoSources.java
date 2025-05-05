package com.facebook.soloader.recovery;

import com.facebook.soloader.BackupSoSource;
import com.facebook.soloader.LogUtil;
import com.facebook.soloader.SoLoader;
import com.facebook.soloader.SoLoaderDSONotFoundError;
import com.facebook.soloader.SoLoaderULError;
import com.facebook.soloader.SoSource;

public class ReunpackBackupSoSources implements RecoveryStrategy {
    public boolean recover(UnsatisfiedLinkError unsatisfiedLinkError, SoSource[] soSourceArr) {
        SoLoaderULError soLoaderULError;
        String message;
        String str;
        if (!(unsatisfiedLinkError instanceof SoLoaderULError) || (unsatisfiedLinkError instanceof SoLoaderDSONotFoundError) || (message = soLoaderULError.getMessage()) == null || (!message.contains("/app/") && !message.contains("/mnt/"))) {
            return false;
        }
        String soName = (soLoaderULError = (SoLoaderULError) unsatisfiedLinkError).getSoName();
        StringBuilder append = new StringBuilder("Reunpacking BackupSoSources due to ").append(unsatisfiedLinkError);
        if (soName == null) {
            str = "";
        } else {
            str = ", retrying for specific library " + soName;
        }
        LogUtil.e(SoLoader.TAG, append.append(str).toString());
        for (BackupSoSource backupSoSource : soSourceArr) {
            if (backupSoSource instanceof BackupSoSource) {
                BackupSoSource backupSoSource2 = backupSoSource;
                try {
                    LogUtil.e(SoLoader.TAG, "Runpacking BackupSoSource " + backupSoSource2.getName());
                    backupSoSource2.prepareForceRefresh();
                } catch (Exception e) {
                    LogUtil.e(SoLoader.TAG, "Encountered an exception while reunpacking BackupSoSource " + backupSoSource2.getName() + " for library " + soName + ": ", e);
                    return false;
                }
            }
        }
        return true;
    }
}
