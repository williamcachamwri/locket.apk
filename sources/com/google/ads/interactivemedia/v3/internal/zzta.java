package com.google.ads.interactivemedia.v3.internal;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class zzta implements FilenameFilter {
    private final Pattern zza;

    public final boolean accept(File file, String str) {
        return this.zza.matcher(str).matches();
    }

    public zzta(Pattern pattern) {
        pattern.getClass();
        Pattern pattern2 = pattern;
        this.zza = pattern;
    }
}
