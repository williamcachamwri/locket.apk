package com.facebook.soloader.recovery;

import com.facebook.soloader.AsyncInitSoSource;
import com.facebook.soloader.LogUtil;
import com.facebook.soloader.SoLoader;
import com.facebook.soloader.SoLoaderULError;
import com.facebook.soloader.SoSource;

public class WaitForAsyncInit implements RecoveryStrategy {
    public boolean recover(UnsatisfiedLinkError unsatisfiedLinkError, SoSource[] soSourceArr) {
        String str;
        String soName = unsatisfiedLinkError instanceof SoLoaderULError ? ((SoLoaderULError) unsatisfiedLinkError).getSoName() : null;
        StringBuilder append = new StringBuilder("Waiting on SoSources due to ").append(unsatisfiedLinkError);
        if (soName == null) {
            str = "";
        } else {
            str = ", retrying for specific library " + soName;
        }
        LogUtil.e(SoLoader.TAG, append.append(str).toString());
        for (SoSource soSource : soSourceArr) {
            if (soSource instanceof AsyncInitSoSource) {
                LogUtil.e(SoLoader.TAG, "Waiting on SoSource " + soSource.getName());
                ((AsyncInitSoSource) soSource).waitUntilInitCompleted();
            }
        }
        return true;
    }
}
