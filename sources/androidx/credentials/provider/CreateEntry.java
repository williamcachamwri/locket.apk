package androidx.credentials.provider;

import android.app.PendingIntent;
import android.app.slice.Slice;
import android.app.slice.SliceItem;
import android.app.slice.SliceSpec;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.credentials.PasswordCredential;
import androidx.credentials.PublicKeyCredential;
import com.google.android.gms.common.internal.BaseGmsClient;
import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0013\b\u0007\u0018\u0000 &2\u00020\u0001:\u0003$%&Bi\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011BS\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0014\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0013\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0015J\r\u0010 \u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010!J\r\u0010\"\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010!J\r\u0010#\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0013X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u001bR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u0006'"}, d2 = {"Landroidx/credentials/provider/CreateEntry;", "", "accountName", "", "pendingIntent", "Landroid/app/PendingIntent;", "description", "lastUsedTime", "Ljava/time/Instant;", "icon", "Landroid/graphics/drawable/Icon;", "passwordCredentialCount", "", "publicKeyCredentialCount", "totalCredentialCount", "isAutoSelectAllowed", "", "(Ljava/lang/CharSequence;Landroid/app/PendingIntent;Ljava/lang/CharSequence;Ljava/time/Instant;Landroid/graphics/drawable/Icon;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Z)V", "credentialCountInformationMap", "", "", "(Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroid/graphics/drawable/Icon;Ljava/lang/CharSequence;Ljava/time/Instant;Ljava/util/Map;Z)V", "getAccountName", "()Ljava/lang/CharSequence;", "getDescription", "getIcon", "()Landroid/graphics/drawable/Icon;", "()Z", "getLastUsedTime", "()Ljava/time/Instant;", "getPendingIntent", "()Landroid/app/PendingIntent;", "getPasswordCredentialCount", "()Ljava/lang/Integer;", "getPublicKeyCredentialCount", "getTotalCredentialCount", "Api28Impl", "Builder", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreateEntry.kt */
public final class CreateEntry {
    private static final String AUTO_SELECT_FALSE_STRING = "false";
    private static final String AUTO_SELECT_TRUE_STRING = "true";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int DESCRIPTION_MAX_CHAR_LIMIT = 300;
    private static final int REVISION_ID = 1;
    private static final String SLICE_HINT_ACCOUNT_NAME = "androidx.credentials.provider.createEntry.SLICE_HINT_USER_PROVIDER_ACCOUNT_NAME";
    private static final String SLICE_HINT_AUTO_SELECT_ALLOWED = "androidx.credentials.provider.createEntry.SLICE_HINT_AUTO_SELECT_ALLOWED";
    private static final String SLICE_HINT_CREDENTIAL_COUNT_INFORMATION = "androidx.credentials.provider.createEntry.SLICE_HINT_CREDENTIAL_COUNT_INFORMATION";
    private static final String SLICE_HINT_ICON = "androidx.credentials.provider.createEntry.SLICE_HINT_PROFILE_ICON";
    private static final String SLICE_HINT_LAST_USED_TIME_MILLIS = "androidx.credentials.provider.createEntry.SLICE_HINT_LAST_USED_TIME_MILLIS";
    private static final String SLICE_HINT_NOTE = "androidx.credentials.provider.createEntry.SLICE_HINT_NOTE";
    private static final String SLICE_HINT_PENDING_INTENT = "androidx.credentials.provider.createEntry.SLICE_HINT_PENDING_INTENT";
    private static final String SLICE_SPEC_TYPE = "CreateEntry";
    private static final String TAG = "CreateEntry";
    public static final String TYPE_TOTAL_CREDENTIAL = "TOTAL_CREDENTIAL_COUNT_TYPE";
    private final CharSequence accountName;
    /* access modifiers changed from: private */
    public final Map<String, Integer> credentialCountInformationMap;
    private final CharSequence description;
    private final Icon icon;
    private final boolean isAutoSelectAllowed;
    private final Instant lastUsedTime;
    private final PendingIntent pendingIntent;

    @JvmStatic
    public static final CreateEntry fromSlice(Slice slice) {
        return Companion.fromSlice(slice);
    }

    @JvmStatic
    public static final Slice toSlice(CreateEntry createEntry) {
        return Companion.toSlice(createEntry);
    }

    public CreateEntry(CharSequence charSequence, PendingIntent pendingIntent2, Icon icon2, CharSequence charSequence2, Instant instant, Map<String, Integer> map, boolean z) {
        Intrinsics.checkNotNullParameter(charSequence, "accountName");
        Intrinsics.checkNotNullParameter(pendingIntent2, BaseGmsClient.KEY_PENDING_INTENT);
        Intrinsics.checkNotNullParameter(map, "credentialCountInformationMap");
        this.accountName = charSequence;
        this.pendingIntent = pendingIntent2;
        this.icon = icon2;
        this.description = charSequence2;
        this.lastUsedTime = instant;
        this.credentialCountInformationMap = map;
        this.isAutoSelectAllowed = z;
        boolean z2 = true;
        if (!(charSequence.length() > 0)) {
            throw new IllegalArgumentException("accountName must not be empty".toString());
        } else if (charSequence2 != null) {
            if (!(charSequence2.length() > 300 ? false : z2)) {
                throw new IllegalArgumentException("Description must follow a limit of 300 characters.".toString());
            }
        }
    }

    public final CharSequence getAccountName() {
        return this.accountName;
    }

    public final PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public final Icon getIcon() {
        return this.icon;
    }

    public final CharSequence getDescription() {
        return this.description;
    }

    public final Instant getLastUsedTime() {
        return this.lastUsedTime;
    }

    public final boolean isAutoSelectAllowed() {
        return this.isAutoSelectAllowed;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ CreateEntry(java.lang.CharSequence r14, android.app.PendingIntent r15, java.lang.CharSequence r16, java.time.Instant r17, android.graphics.drawable.Icon r18, java.lang.Integer r19, java.lang.Integer r20, java.lang.Integer r21, boolean r22, int r23, kotlin.jvm.internal.DefaultConstructorMarker r24) {
        /*
            r13 = this;
            r0 = r23
            r1 = r0 & 4
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r6 = r2
            goto L_0x000b
        L_0x0009:
            r6 = r16
        L_0x000b:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0011
            r7 = r2
            goto L_0x0013
        L_0x0011:
            r7 = r17
        L_0x0013:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0019
            r8 = r2
            goto L_0x001b
        L_0x0019:
            r8 = r18
        L_0x001b:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0021
            r9 = r2
            goto L_0x0023
        L_0x0021:
            r9 = r19
        L_0x0023:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0029
            r10 = r2
            goto L_0x002b
        L_0x0029:
            r10 = r20
        L_0x002b:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0031
            r11 = r2
            goto L_0x0033
        L_0x0031:
            r11 = r21
        L_0x0033:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x003a
            r0 = 0
            r12 = r0
            goto L_0x003c
        L_0x003a:
            r12 = r22
        L_0x003c:
            r3 = r13
            r4 = r14
            r5 = r15
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.provider.CreateEntry.<init>(java.lang.CharSequence, android.app.PendingIntent, java.lang.CharSequence, java.time.Instant, android.graphics.drawable.Icon, java.lang.Integer, java.lang.Integer, java.lang.Integer, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CreateEntry(java.lang.CharSequence r10, android.app.PendingIntent r11, java.lang.CharSequence r12, java.time.Instant r13, android.graphics.drawable.Icon r14, java.lang.Integer r15, java.lang.Integer r16, java.lang.Integer r17, boolean r18) {
        /*
            r9 = this;
            java.lang.String r0 = "accountName"
            r2 = r10
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "pendingIntent"
            r3 = r11
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            r0 = 3
            kotlin.Pair[] r0 = new kotlin.Pair[r0]
            java.lang.String r1 = "android.credentials.TYPE_PASSWORD_CREDENTIAL"
            r4 = r15
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r15)
            r4 = 0
            r0[r4] = r1
            java.lang.String r1 = "androidx.credentials.TYPE_PUBLIC_KEY_CREDENTIAL"
            r4 = r16
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r4)
            r4 = 1
            r0[r4] = r1
            java.lang.String r1 = "TOTAL_CREDENTIAL_COUNT_TYPE"
            r4 = r17
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r4)
            r4 = 2
            r0[r4] = r1
            java.util.Map r7 = kotlin.collections.MapsKt.mutableMapOf(r0)
            r1 = r9
            r4 = r14
            r5 = r12
            r6 = r13
            r8 = r18
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.provider.CreateEntry.<init>(java.lang.CharSequence, android.app.PendingIntent, java.lang.CharSequence, java.time.Instant, android.graphics.drawable.Icon, java.lang.Integer, java.lang.Integer, java.lang.Integer, boolean):void");
    }

    public final Integer getPasswordCredentialCount() {
        return this.credentialCountInformationMap.get(PasswordCredential.TYPE_PASSWORD_CREDENTIAL);
    }

    public final Integer getPublicKeyCredentialCount() {
        return this.credentialCountInformationMap.get(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL);
    }

    public final Integer getTotalCredentialCount() {
        return this.credentialCountInformationMap.get(TYPE_TOTAL_CREDENTIAL);
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u0019\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u0003J\u0010\u0010\u001a\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0010\u0010\u001b\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\fJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\fJ\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0004\n\u0002\u0010\u0013R\u0012\u0010\u0015\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006 "}, d2 = {"Landroidx/credentials/provider/CreateEntry$Builder;", "", "accountName", "", "pendingIntent", "Landroid/app/PendingIntent;", "(Ljava/lang/CharSequence;Landroid/app/PendingIntent;)V", "autoSelectAllowed", "", "credentialCountInformationMap", "", "", "", "description", "icon", "Landroid/graphics/drawable/Icon;", "lastUsedTime", "Ljava/time/Instant;", "passwordCredentialCount", "Ljava/lang/Integer;", "publicKeyCredentialCount", "totalCredentialCount", "build", "Landroidx/credentials/provider/CreateEntry;", "setAutoSelectAllowed", "setDescription", "setIcon", "setLastUsedTime", "setPasswordCredentialCount", "count", "setPublicKeyCredentialCount", "setTotalCredentialCount", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CreateEntry.kt */
    public static final class Builder {
        private final CharSequence accountName;
        private boolean autoSelectAllowed;
        private Map<String, Integer> credentialCountInformationMap = new LinkedHashMap();
        private CharSequence description;
        private Icon icon;
        private Instant lastUsedTime;
        private Integer passwordCredentialCount;
        private final PendingIntent pendingIntent;
        private Integer publicKeyCredentialCount;
        private Integer totalCredentialCount;

        public Builder(CharSequence charSequence, PendingIntent pendingIntent2) {
            Intrinsics.checkNotNullParameter(charSequence, "accountName");
            Intrinsics.checkNotNullParameter(pendingIntent2, BaseGmsClient.KEY_PENDING_INTENT);
            this.accountName = charSequence;
            this.pendingIntent = pendingIntent2;
        }

        public final Builder setAutoSelectAllowed(boolean z) {
            this.autoSelectAllowed = z;
            return this;
        }

        public final Builder setPasswordCredentialCount(int i) {
            this.passwordCredentialCount = Integer.valueOf(i);
            this.credentialCountInformationMap.put(PasswordCredential.TYPE_PASSWORD_CREDENTIAL, Integer.valueOf(i));
            return this;
        }

        public final Builder setPublicKeyCredentialCount(int i) {
            this.publicKeyCredentialCount = Integer.valueOf(i);
            this.credentialCountInformationMap.put(PublicKeyCredential.TYPE_PUBLIC_KEY_CREDENTIAL, Integer.valueOf(i));
            return this;
        }

        public final Builder setTotalCredentialCount(int i) {
            this.totalCredentialCount = Integer.valueOf(i);
            this.credentialCountInformationMap.put(CreateEntry.TYPE_TOTAL_CREDENTIAL, Integer.valueOf(i));
            return this;
        }

        public final Builder setIcon(Icon icon2) {
            this.icon = icon2;
            return this;
        }

        public final Builder setDescription(CharSequence charSequence) {
            if ((charSequence != null ? Integer.valueOf(charSequence.length()) : null) == null || charSequence.length() <= 300) {
                this.description = charSequence;
                return this;
            }
            throw new IllegalArgumentException("Description must follow a limit of 300 characters.");
        }

        public final Builder setLastUsedTime(Instant instant) {
            this.lastUsedTime = instant;
            return this;
        }

        public final CreateEntry build() {
            return new CreateEntry(this.accountName, this.pendingIntent, this.icon, this.description, this.lastUsedTime, this.credentialCountInformationMap, this.autoSelectAllowed);
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J%\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0001¢\u0006\u0002\b\tJ%\u0010\n\u001a\u0004\u0018\u00010\b2\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0004H\u0001¢\u0006\u0002\b\fJ\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000eH\u0007¨\u0006\u0013"}, d2 = {"Landroidx/credentials/provider/CreateEntry$Api28Impl;", "", "()V", "convertBundleToCredentialCountInfo", "", "", "", "bundle", "Landroid/os/Bundle;", "convertBundleToCredentialCountInfo$credentials_release", "convertCredentialCountInfoToBundle", "credentialCountInformationMap", "convertCredentialCountInfoToBundle$credentials_release", "fromSlice", "Landroidx/credentials/provider/CreateEntry;", "slice", "Landroid/app/slice/Slice;", "toSlice", "createEntry", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CreateEntry.kt */
    private static final class Api28Impl {
        public static final Api28Impl INSTANCE = new Api28Impl();

        private Api28Impl() {
        }

        @JvmStatic
        public static final Slice toSlice(CreateEntry createEntry) {
            Intrinsics.checkNotNullParameter(createEntry, "createEntry");
            CharSequence accountName = createEntry.getAccountName();
            Icon icon = createEntry.getIcon();
            CharSequence description = createEntry.getDescription();
            Instant lastUsedTime = createEntry.getLastUsedTime();
            Map access$getCredentialCountInformationMap$p = createEntry.credentialCountInformationMap;
            PendingIntent pendingIntent = createEntry.getPendingIntent();
            Slice.Builder builder = new Slice.Builder(Uri.EMPTY, new SliceSpec("CreateEntry", 1));
            String str = createEntry.isAutoSelectAllowed() ? CreateEntry.AUTO_SELECT_TRUE_STRING : "false";
            builder.addText(accountName, (String) null, CollectionsKt.listOf(CreateEntry.SLICE_HINT_ACCOUNT_NAME));
            if (lastUsedTime != null) {
                builder.addLong(lastUsedTime.toEpochMilli(), (String) null, CollectionsKt.listOf(CreateEntry.SLICE_HINT_LAST_USED_TIME_MILLIS));
            }
            if (description != null) {
                builder.addText(description, (String) null, CollectionsKt.listOf(CreateEntry.SLICE_HINT_NOTE));
            }
            if (icon != null) {
                builder.addIcon(icon, (String) null, CollectionsKt.listOf(CreateEntry.SLICE_HINT_ICON));
            }
            if (convertCredentialCountInfoToBundle$credentials_release(access$getCredentialCountInformationMap$p) != null) {
                builder.addBundle(convertCredentialCountInfoToBundle$credentials_release(access$getCredentialCountInformationMap$p), (String) null, CollectionsKt.listOf(CreateEntry.SLICE_HINT_CREDENTIAL_COUNT_INFORMATION));
            }
            builder.addAction(pendingIntent, new Slice.Builder(builder).addHints(Collections.singletonList(CreateEntry.SLICE_HINT_PENDING_INTENT)).build(), (String) null).addText(str, (String) null, CollectionsKt.listOf(CreateEntry.SLICE_HINT_AUTO_SELECT_ALLOWED));
            Slice build = builder.build();
            Intrinsics.checkNotNullExpressionValue(build, "sliceBuilder.build()");
            return build;
        }

        @JvmStatic
        public static final CreateEntry fromSlice(Slice slice) {
            Intrinsics.checkNotNullParameter(slice, "slice");
            List<SliceItem> items = slice.getItems();
            Intrinsics.checkNotNullExpressionValue(items, "slice.items");
            Map linkedHashMap = new LinkedHashMap();
            CharSequence charSequence = null;
            PendingIntent pendingIntent = null;
            Icon icon = null;
            CharSequence charSequence2 = null;
            Instant instant = null;
            boolean z = false;
            for (SliceItem sliceItem : items) {
                if (sliceItem.hasHint(CreateEntry.SLICE_HINT_ACCOUNT_NAME)) {
                    charSequence = sliceItem.getText();
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_ICON)) {
                    icon = sliceItem.getIcon();
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_PENDING_INTENT)) {
                    pendingIntent = sliceItem.getAction();
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_CREDENTIAL_COUNT_INFORMATION)) {
                    Map<String, Integer> convertBundleToCredentialCountInfo$credentials_release = convertBundleToCredentialCountInfo$credentials_release(sliceItem.getBundle());
                    Intrinsics.checkNotNull(convertBundleToCredentialCountInfo$credentials_release, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Int?>");
                    linkedHashMap = TypeIntrinsics.asMutableMap(convertBundleToCredentialCountInfo$credentials_release);
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_LAST_USED_TIME_MILLIS)) {
                    instant = Instant.ofEpochMilli(sliceItem.getLong());
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_NOTE)) {
                    charSequence2 = sliceItem.getText();
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_LAST_USED_TIME_MILLIS)) {
                    instant = Instant.ofEpochMilli(sliceItem.getLong());
                } else if (sliceItem.hasHint(CreateEntry.SLICE_HINT_AUTO_SELECT_ALLOWED) && Intrinsics.areEqual((Object) sliceItem.getText(), (Object) CreateEntry.AUTO_SELECT_TRUE_STRING)) {
                    z = true;
                }
            }
            try {
                Intrinsics.checkNotNull(charSequence);
                Intrinsics.checkNotNull(pendingIntent);
                return new CreateEntry(charSequence, pendingIntent, icon, charSequence2, instant, linkedHashMap, z);
            } catch (Exception e) {
                Log.i("CreateEntry", "fromSlice failed with: " + e.getMessage());
                return null;
            }
        }

        @JvmStatic
        public static final Map<String, Integer> convertBundleToCredentialCountInfo$credentials_release(Bundle bundle) {
            HashMap hashMap = new HashMap();
            if (bundle == null) {
                return hashMap;
            }
            Set<String> keySet = bundle.keySet();
            Intrinsics.checkNotNullExpressionValue(keySet, "bundle.keySet()");
            for (String str : keySet) {
                try {
                    Intrinsics.checkNotNullExpressionValue(str, "it");
                    hashMap.put(str, Integer.valueOf(bundle.getInt(str)));
                } catch (Exception e) {
                    Log.i("CreateEntry", "Issue unpacking credential count info bundle: " + e.getMessage());
                }
            }
            return hashMap;
        }

        @JvmStatic
        public static final Bundle convertCredentialCountInfoToBundle$credentials_release(Map<String, Integer> map) {
            Intrinsics.checkNotNullParameter(map, "credentialCountInformationMap");
            Bundle bundle = new Bundle();
            boolean z = false;
            for (Map.Entry next : map.entrySet()) {
                if (next.getValue() != null) {
                    Object value = next.getValue();
                    Intrinsics.checkNotNull(value);
                    bundle.putInt((String) next.getKey(), ((Number) value).intValue());
                    z = true;
                }
            }
            if (!z) {
                return null;
            }
            return bundle;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/credentials/provider/CreateEntry$Companion;", "", "()V", "AUTO_SELECT_FALSE_STRING", "", "AUTO_SELECT_TRUE_STRING", "DESCRIPTION_MAX_CHAR_LIMIT", "", "REVISION_ID", "SLICE_HINT_ACCOUNT_NAME", "SLICE_HINT_AUTO_SELECT_ALLOWED", "SLICE_HINT_CREDENTIAL_COUNT_INFORMATION", "SLICE_HINT_ICON", "SLICE_HINT_LAST_USED_TIME_MILLIS", "SLICE_HINT_NOTE", "SLICE_HINT_PENDING_INTENT", "SLICE_SPEC_TYPE", "TAG", "TYPE_TOTAL_CREDENTIAL", "fromSlice", "Landroidx/credentials/provider/CreateEntry;", "slice", "Landroid/app/slice/Slice;", "toSlice", "createEntry", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CreateEntry.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Slice toSlice(CreateEntry createEntry) {
            Intrinsics.checkNotNullParameter(createEntry, "createEntry");
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.toSlice(createEntry);
            }
            return null;
        }

        @JvmStatic
        public final CreateEntry fromSlice(Slice slice) {
            Intrinsics.checkNotNullParameter(slice, "slice");
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.fromSlice(slice);
            }
            return null;
        }
    }
}
