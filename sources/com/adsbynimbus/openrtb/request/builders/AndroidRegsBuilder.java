package com.adsbynimbus.openrtb.request.builders;

import com.adsbynimbus.openrtb.request.Regs;
import com.adsbynimbus.request.RequestExtensions;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nH\u0016R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\f"}, d2 = {"Lcom/adsbynimbus/openrtb/request/builders/AndroidRegsBuilder;", "", "regs", "Lcom/adsbynimbus/openrtb/request/Regs;", "getRegs", "()Lcom/adsbynimbus/openrtb/request/Regs;", "ccpa", "usPrivacyString", "", "coppa", "", "gdpr", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AndroidRegsBuilder.kt */
public interface AndroidRegsBuilder {
    AndroidRegsBuilder ccpa(String str);

    AndroidRegsBuilder coppa(boolean z);

    AndroidRegsBuilder gdpr(boolean z);

    Regs getRegs();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AndroidRegsBuilder.kt */
    public static final class DefaultImpls {
        public static AndroidRegsBuilder coppa(AndroidRegsBuilder androidRegsBuilder, boolean z) {
            androidRegsBuilder.getRegs().coppa = RequestExtensions.getByteValue(z);
            return androidRegsBuilder;
        }

        public static AndroidRegsBuilder gdpr(AndroidRegsBuilder androidRegsBuilder, boolean z) {
            androidRegsBuilder.getRegs().ext.gdpr = Byte.valueOf(RequestExtensions.getByteValue(z));
            return androidRegsBuilder;
        }

        public static AndroidRegsBuilder ccpa(AndroidRegsBuilder androidRegsBuilder, String str) {
            Intrinsics.checkNotNullParameter(str, "usPrivacyString");
            androidRegsBuilder.getRegs().ext.us_privacy = str;
            return androidRegsBuilder;
        }
    }
}
