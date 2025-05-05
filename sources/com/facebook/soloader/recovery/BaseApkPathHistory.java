package com.facebook.soloader.recovery;

import com.facebook.soloader.LogUtil;
import com.facebook.soloader.SoLoader;
import java.io.File;

public class BaseApkPathHistory {
    private int mCounter;
    private final String[] mPaths;

    public BaseApkPathHistory(int i) {
        if (i > 0) {
            this.mPaths = new String[i];
            this.mCounter = 0;
            return;
        }
        throw new IllegalArgumentException();
    }

    public synchronized boolean recordPathIfNew(String str) {
        for (String equals : this.mPaths) {
            if (str.equals(equals)) {
                return false;
            }
        }
        StringBuilder append = new StringBuilder("Recording new base apk path: ").append(str).append("\n");
        report(append);
        LogUtil.w(SoLoader.TAG, append.toString());
        String[] strArr = this.mPaths;
        int i = this.mCounter;
        strArr[i % strArr.length] = str;
        this.mCounter = i + 1;
        return true;
    }

    public synchronized void report(StringBuilder sb) {
        sb.append("Previously recorded ").append(this.mCounter).append(" base apk paths.");
        if (this.mCounter > 0) {
            sb.append(" Most recent ones:");
        }
        int i = 0;
        while (true) {
            String[] strArr = this.mPaths;
            if (i < strArr.length) {
                int i2 = (this.mCounter - i) - 1;
                if (i2 >= 0) {
                    String str = strArr[i2 % strArr.length];
                    sb.append("\n").append(str).append(" (").append(new File(str).exists() ? "exists" : "does not exist").append(")");
                }
                i++;
            }
        }
    }

    public String report() {
        StringBuilder sb = new StringBuilder();
        report(sb);
        return sb.toString();
    }

    public synchronized int size() {
        return this.mCounter;
    }
}
