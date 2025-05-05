package com.adsbynimbus.request;

import android.content.SharedPreferences;
import com.adsbynimbus.openrtb.request.User;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u000f\u001a\u00020\u0010*\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0000\u001a\u0016\u0010\u0012\u001a\u00020\u0013*\u00020\u00132\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0000\u001a\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001H\u0000\"\u001b\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001b\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u001b\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u001b\u0010\u000b\u001a\u0004\u0018\u00010\u0001*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0004\"\u001b\u0010\r\u001a\u0004\u0018\u00010\u0001*\u00020\u00028À\u0002X\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0004¨\u0006\u0017"}, d2 = {"GppSID", "", "Landroid/content/SharedPreferences;", "getGppSID", "(Landroid/content/SharedPreferences;)Ljava/lang/String;", "GppString", "getGppString", "TCFGdprApplies", "", "getTCFGdprApplies", "(Landroid/content/SharedPreferences;)Ljava/lang/Boolean;", "TCFString", "getTCFString", "USPrivacyString", "getUSPrivacyString", "applyPrivacyRegs", "Lcom/adsbynimbus/openrtb/request/Regs;", "preferences", "applyTCFv2", "Lcom/adsbynimbus/openrtb/request/User;", "setGdprConsent", "", "consentString", "request_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PrivacyExtensions.kt */
public final class PrivacyExtensions {
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
        if (r15 == null) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.adsbynimbus.openrtb.request.User applyTCFv2(com.adsbynimbus.openrtb.request.User r14, android.content.SharedPreferences r15) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            com.adsbynimbus.openrtb.request.User$Extension r0 = r14.ext
            r1 = 0
            if (r0 == 0) goto L_0x000d
            java.lang.String r0 = r0.consent
            goto L_0x000e
        L_0x000d:
            r0 = r1
        L_0x000e:
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r2 = 0
            r3 = 1
            if (r0 == 0) goto L_0x001d
            int r0 = r0.length()
            if (r0 != 0) goto L_0x001b
            goto L_0x001d
        L_0x001b:
            r0 = r2
            goto L_0x001e
        L_0x001d:
            r0 = r3
        L_0x001e:
            if (r0 == 0) goto L_0x0055
            if (r15 == 0) goto L_0x0055
            java.lang.String r0 = "IABTCF_TCString"
            java.lang.String r15 = r15.getString(r0, r1)
            r0 = r15
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x0033
            boolean r0 = kotlin.text.StringsKt.isBlank(r0)
            if (r0 == 0) goto L_0x0034
        L_0x0033:
            r2 = r3
        L_0x0034:
            if (r2 != 0) goto L_0x0038
            r4 = r15
            goto L_0x0039
        L_0x0038:
            r4 = r1
        L_0x0039:
            if (r4 == 0) goto L_0x0055
            com.adsbynimbus.openrtb.request.User$Extension r15 = r14.ext
            if (r15 == 0) goto L_0x0043
            r15.consent = r4
            if (r15 != 0) goto L_0x0053
        L_0x0043:
            com.adsbynimbus.openrtb.request.User$Extension r15 = new com.adsbynimbus.openrtb.request.User$Extension
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 254(0xfe, float:3.56E-43)
            r13 = 0
            r3 = r15
            r3.<init>((java.lang.String) r4, (java.lang.String) r5, (java.lang.String) r6, (java.lang.String) r7, (java.lang.String) r8, (java.util.Set) r9, (java.util.Map) r10, (java.util.Map) r11, (int) r12, (kotlin.jvm.internal.DefaultConstructorMarker) r13)
        L_0x0053:
            r14.ext = r15
        L_0x0055:
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.request.PrivacyExtensions.applyTCFv2(com.adsbynimbus.openrtb.request.User, android.content.SharedPreferences):com.adsbynimbus.openrtb.request.User");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0032, code lost:
        if ((r4.length() == 4) != false) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x012f, code lost:
        if ((r5 == null || kotlin.text.StringsKt.isBlank(r5)) == false) goto L_0x0133;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final com.adsbynimbus.openrtb.request.Regs applyPrivacyRegs(com.adsbynimbus.openrtb.request.Regs r7, android.content.SharedPreferences r8) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            boolean r0 = com.adsbynimbus.Nimbus.COPPA
            r1 = 1
            if (r0 == 0) goto L_0x0010
            byte r0 = com.adsbynimbus.request.RequestExtensions.getByteValue(r1)
            r7.coppa = r0
        L_0x0010:
            com.adsbynimbus.openrtb.request.Regs$Extension r0 = r7.ext
            java.lang.String r0 = r0.us_privacy
            r2 = 0
            r3 = 0
            if (r0 != 0) goto L_0x0038
            com.adsbynimbus.openrtb.request.Regs$Extension r0 = r7.ext
            java.lang.String r4 = com.adsbynimbus.Nimbus.usPrivacyString
            if (r4 != 0) goto L_0x0036
            if (r8 == 0) goto L_0x0035
            java.lang.String r4 = "IABUSPrivacy_String"
            java.lang.String r4 = r8.getString(r4, r3)
            if (r4 == 0) goto L_0x0035
            int r5 = r4.length()
            r6 = 4
            if (r5 != r6) goto L_0x0031
            r5 = r1
            goto L_0x0032
        L_0x0031:
            r5 = r2
        L_0x0032:
            if (r5 == 0) goto L_0x0035
            goto L_0x0036
        L_0x0035:
            r4 = r3
        L_0x0036:
            r0.us_privacy = r4
        L_0x0038:
            if (r8 == 0) goto L_0x0102
            java.lang.String r0 = "IABTCF_gdprApplies"
            boolean r4 = r8.contains(r0)
            if (r4 == 0) goto L_0x0043
            goto L_0x0044
        L_0x0043:
            r0 = r3
        L_0x0044:
            if (r0 == 0) goto L_0x00c8
            kotlin.Result$Companion r4 = kotlin.Result.Companion     // Catch:{ all -> 0x0079 }
            r4 = -1
            int r5 = r8.getInt(r0, r4)     // Catch:{ all -> 0x0079 }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ all -> 0x0079 }
            r6 = r5
            java.lang.Number r6 = (java.lang.Number) r6     // Catch:{ all -> 0x0079 }
            int r6 = r6.intValue()     // Catch:{ all -> 0x0079 }
            if (r6 <= r4) goto L_0x005c
            r4 = r1
            goto L_0x005d
        L_0x005c:
            r4 = r2
        L_0x005d:
            if (r4 == 0) goto L_0x0060
            goto L_0x0061
        L_0x0060:
            r5 = r3
        L_0x0061:
            if (r5 == 0) goto L_0x0073
            java.lang.Number r5 = (java.lang.Number) r5     // Catch:{ all -> 0x0079 }
            int r4 = r5.intValue()     // Catch:{ all -> 0x0079 }
            if (r4 != r1) goto L_0x006d
            r4 = r1
            goto L_0x006e
        L_0x006d:
            r4 = r2
        L_0x006e:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r4)     // Catch:{ all -> 0x0079 }
            goto L_0x0074
        L_0x0073:
            r4 = r3
        L_0x0074:
            java.lang.Object r4 = kotlin.Result.m2444constructorimpl(r4)     // Catch:{ all -> 0x0079 }
            goto L_0x0084
        L_0x0079:
            r4 = move-exception
            kotlin.Result$Companion r5 = kotlin.Result.Companion
            java.lang.Object r4 = kotlin.ResultKt.createFailure(r4)
            java.lang.Object r4 = kotlin.Result.m2444constructorimpl(r4)
        L_0x0084:
            boolean r5 = kotlin.Result.m2450isFailureimpl(r4)
            if (r5 == 0) goto L_0x008b
            r4 = r3
        L_0x008b:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            if (r4 != 0) goto L_0x00c9
            kotlin.Result$Companion r4 = kotlin.Result.Companion     // Catch:{ all -> 0x00b2 }
            java.lang.String r0 = r8.getString(r0, r3)     // Catch:{ all -> 0x00b2 }
            if (r0 == 0) goto L_0x00ac
            java.lang.String r4 = "getString(key, null)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)     // Catch:{ all -> 0x00b2 }
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ all -> 0x00b2 }
            java.lang.String r4 = "1"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4     // Catch:{ all -> 0x00b2 }
            r5 = 2
            boolean r0 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r0, (java.lang.CharSequence) r4, (boolean) r2, (int) r5, (java.lang.Object) r3)     // Catch:{ all -> 0x00b2 }
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x00b2 }
            goto L_0x00ad
        L_0x00ac:
            r0 = r3
        L_0x00ad:
            java.lang.Object r0 = kotlin.Result.m2444constructorimpl(r0)     // Catch:{ all -> 0x00b2 }
            goto L_0x00bd
        L_0x00b2:
            r0 = move-exception
            kotlin.Result$Companion r4 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m2444constructorimpl(r0)
        L_0x00bd:
            boolean r4 = kotlin.Result.m2450isFailureimpl(r0)
            if (r4 == 0) goto L_0x00c4
            r0 = r3
        L_0x00c4:
            r4 = r0
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            goto L_0x00c9
        L_0x00c8:
            r4 = r3
        L_0x00c9:
            if (r4 == 0) goto L_0x0102
            boolean r0 = r4.booleanValue()
            byte r0 = com.adsbynimbus.request.RequestExtensions.getByteValue(r0)
            java.lang.Byte r0 = java.lang.Byte.valueOf(r0)
            r4 = r0
            java.lang.Number r4 = (java.lang.Number) r4
            byte r4 = r4.byteValue()
            com.adsbynimbus.openrtb.request.Regs$Extension r5 = r7.ext
            java.lang.Byte r5 = r5.gdpr
            if (r5 == 0) goto L_0x00ec
            byte r5 = r5.byteValue()
            if (r4 != r5) goto L_0x00ec
            r4 = r1
            goto L_0x00ed
        L_0x00ec:
            r4 = r2
        L_0x00ed:
            r4 = r4 ^ r1
            if (r4 == 0) goto L_0x00f1
            goto L_0x00f2
        L_0x00f1:
            r0 = r3
        L_0x00f2:
            if (r0 == 0) goto L_0x0102
            java.lang.Number r0 = (java.lang.Number) r0
            byte r0 = r0.byteValue()
            com.adsbynimbus.openrtb.request.Regs$Extension r4 = r7.ext
            java.lang.Byte r0 = java.lang.Byte.valueOf(r0)
            r4.gdpr = r0
        L_0x0102:
            com.adsbynimbus.openrtb.request.Regs$Extension r0 = r7.ext
            java.lang.String r0 = r0.gpp
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x0113
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0111
            goto L_0x0113
        L_0x0111:
            r0 = r2
            goto L_0x0114
        L_0x0113:
            r0 = r1
        L_0x0114:
            if (r0 == 0) goto L_0x0135
            com.adsbynimbus.openrtb.request.Regs$Extension r0 = r7.ext
            if (r8 == 0) goto L_0x0132
            java.lang.String r4 = "IABGPP_HDR_GppString"
            java.lang.String r4 = r8.getString(r4, r3)
            r5 = r4
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            if (r5 == 0) goto L_0x012e
            boolean r5 = kotlin.text.StringsKt.isBlank(r5)
            if (r5 == 0) goto L_0x012c
            goto L_0x012e
        L_0x012c:
            r5 = r2
            goto L_0x012f
        L_0x012e:
            r5 = r1
        L_0x012f:
            if (r5 != 0) goto L_0x0132
            goto L_0x0133
        L_0x0132:
            r4 = r3
        L_0x0133:
            r0.gpp = r4
        L_0x0135:
            com.adsbynimbus.openrtb.request.Regs$Extension r0 = r7.ext
            java.lang.String r0 = r0.gpp_sids
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x0146
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0144
            goto L_0x0146
        L_0x0144:
            r0 = r2
            goto L_0x0147
        L_0x0146:
            r0 = r1
        L_0x0147:
            if (r0 == 0) goto L_0x0165
            com.adsbynimbus.openrtb.request.Regs$Extension r0 = r7.ext
            if (r8 == 0) goto L_0x0163
            java.lang.String r4 = "IABGPP_GppSID"
            java.lang.String r8 = r8.getString(r4, r3)
            r4 = r8
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            if (r4 == 0) goto L_0x0160
            boolean r4 = kotlin.text.StringsKt.isBlank(r4)
            if (r4 == 0) goto L_0x015f
            goto L_0x0160
        L_0x015f:
            r1 = r2
        L_0x0160:
            if (r1 != 0) goto L_0x0163
            r3 = r8
        L_0x0163:
            r0.gpp_sids = r3
        L_0x0165:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adsbynimbus.request.PrivacyExtensions.applyPrivacyRegs(com.adsbynimbus.openrtb.request.Regs, android.content.SharedPreferences):com.adsbynimbus.openrtb.request.Regs");
    }

    public static final void setGdprConsent(User user, String str) {
        Intrinsics.checkNotNullParameter(user, "<this>");
        if (str == null || user.ext != null) {
            User.Extension extension = user.ext;
            if (extension != null) {
                extension.consent = str;
                return;
            }
            return;
        }
        user.ext = new User.Extension(str, (String) null, (String) null, (String) null, (String) null, (Set) null, (Map) null, (Map) null, 254, (DefaultConstructorMarker) null);
    }

    public static final String getTCFString(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        String string = sharedPreferences.getString("IABTCF_TCString", (String) null);
        CharSequence charSequence = string;
        if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
            return string;
        }
        return null;
    }

    public static final Boolean getTCFGdprApplies(SharedPreferences sharedPreferences) {
        Object obj;
        Object obj2;
        Boolean bool;
        Boolean bool2;
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        String str = "IABTCF_gdprApplies";
        Boolean bool3 = null;
        if (!sharedPreferences.contains(str)) {
            str = null;
        }
        if (str == null) {
            return null;
        }
        try {
            Result.Companion companion = Result.Companion;
            Integer valueOf = Integer.valueOf(sharedPreferences.getInt(str, -1));
            boolean z = true;
            if (!(valueOf.intValue() > -1)) {
                valueOf = null;
            }
            if (valueOf != null) {
                if (valueOf.intValue() != 1) {
                    z = false;
                }
                bool2 = Boolean.valueOf(z);
            } else {
                bool2 = null;
            }
            obj = Result.m2444constructorimpl(bool2);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2450isFailureimpl(obj)) {
            obj = null;
        }
        Boolean bool4 = (Boolean) obj;
        if (bool4 != null) {
            return bool4;
        }
        try {
            Result.Companion companion3 = Result.Companion;
            String string = sharedPreferences.getString(str, (String) null);
            if (string != null) {
                Intrinsics.checkNotNullExpressionValue(string, "getString(key, null)");
                bool = Boolean.valueOf(StringsKt.contains$default((CharSequence) string, (CharSequence) "1", false, 2, (Object) null));
            } else {
                bool = null;
            }
            obj2 = Result.m2444constructorimpl(bool);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.Companion;
            obj2 = Result.m2444constructorimpl(ResultKt.createFailure(th2));
        }
        if (!Result.m2450isFailureimpl(obj2)) {
            bool3 = obj2;
        }
        return bool3;
    }

    public static final String getUSPrivacyString(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        String string = sharedPreferences.getString("IABUSPrivacy_String", (String) null);
        if (string == null) {
            return null;
        }
        if (string.length() == 4) {
            return string;
        }
        return null;
    }

    public static final String getGppString(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        String string = sharedPreferences.getString("IABGPP_HDR_GppString", (String) null);
        CharSequence charSequence = string;
        if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
            return string;
        }
        return null;
    }

    public static final String getGppSID(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        String string = sharedPreferences.getString("IABGPP_GppSID", (String) null);
        CharSequence charSequence = string;
        if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
            return string;
        }
        return null;
    }
}
