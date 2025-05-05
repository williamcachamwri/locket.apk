package com.google.android.gms.auth.api;

@Deprecated
/* compiled from: com.google.android.gms:play-services-auth@@21.2.0 */
public final class zbc {
    protected Boolean zba = false;
    protected String zbb;

    public zbc() {
    }

    public final zbc zba(String str) {
        this.zbb = str;
        return this;
    }

    public zbc(zbd zbd) {
        String unused = zbd.zbb;
        this.zba = Boolean.valueOf(zbd.zbc);
        this.zbb = zbd.zbd;
    }
}
