package androidx.credentials.provider;

import android.app.PendingIntent;
import android.app.slice.Slice;
import android.app.slice.SliceItem;
import android.app.slice.SliceSpec;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.credentials.CredentialOption;
import androidx.credentials.PasswordCredential;
import androidx.credentials.R;
import androidx.credentials.provider.BeginGetPasswordOption;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.material.internal.ViewUtils;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\b\u0007\u0018\u0000 #2\u00020\u0001:\u0003!\"#BS\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011B_\b\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0010¢\u0006\u0002\u0010\u0015R\u000e\u0010\u0013\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u001aR\u000e\u0010\u0014\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0012\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017¨\u0006$"}, d2 = {"Landroidx/credentials/provider/PasswordCredentialEntry;", "Landroidx/credentials/provider/CredentialEntry;", "context", "Landroid/content/Context;", "username", "", "pendingIntent", "Landroid/app/PendingIntent;", "beginGetPasswordOption", "Landroidx/credentials/provider/BeginGetPasswordOption;", "displayName", "lastUsedTime", "Ljava/time/Instant;", "icon", "Landroid/graphics/drawable/Icon;", "isAutoSelectAllowed", "", "(Landroid/content/Context;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroidx/credentials/provider/BeginGetPasswordOption;Ljava/lang/CharSequence;Ljava/time/Instant;Landroid/graphics/drawable/Icon;Z)V", "typeDisplayName", "autoSelectAllowedFromOption", "isDefaultIcon", "(Ljava/lang/CharSequence;Ljava/lang/CharSequence;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Ljava/time/Instant;Landroid/graphics/drawable/Icon;ZLandroidx/credentials/provider/BeginGetPasswordOption;ZZ)V", "getDisplayName", "()Ljava/lang/CharSequence;", "getIcon", "()Landroid/graphics/drawable/Icon;", "()Z", "getLastUsedTime", "()Ljava/time/Instant;", "getPendingIntent", "()Landroid/app/PendingIntent;", "getTypeDisplayName", "getUsername", "Api28Impl", "Builder", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PasswordCredentialEntry.kt */
public final class PasswordCredentialEntry extends CredentialEntry {
    private static final String AUTO_SELECT_FALSE_STRING = "false";
    private static final String AUTO_SELECT_TRUE_STRING = "true";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int REVISION_ID = 1;
    private static final String SLICE_HINT_AUTO_ALLOWED = "androidx.credentials.provider.credentialEntry.SLICE_HINT_AUTO_ALLOWED";
    private static final String SLICE_HINT_AUTO_SELECT_FROM_OPTION = "androidx.credentials.provider.credentialEntry.SLICE_HINT_AUTO_SELECT_FROM_OPTION";
    private static final String SLICE_HINT_DEFAULT_ICON_RES_ID = "androidx.credentials.provider.credentialEntry.SLICE_HINT_DEFAULT_ICON_RES_ID";
    private static final String SLICE_HINT_ICON = "androidx.credentials.provider.credentialEntry.SLICE_HINT_PROFILE_ICON";
    private static final String SLICE_HINT_LAST_USED_TIME_MILLIS = "androidx.credentials.provider.credentialEntry.SLICE_HINT_LAST_USED_TIME_MILLIS";
    private static final String SLICE_HINT_OPTION_ID = "androidx.credentials.provider.credentialEntry.SLICE_HINT_OPTION_ID";
    private static final String SLICE_HINT_PENDING_INTENT = "androidx.credentials.provider.credentialEntry.SLICE_HINT_PENDING_INTENT";
    private static final String SLICE_HINT_SUBTITLE = "androidx.credentials.provider.credentialEntry.SLICE_HINT_CREDENTIAL_TYPE_DISPLAY_NAME";
    private static final String SLICE_HINT_TITLE = "androidx.credentials.provider.credentialEntry.SLICE_HINT_USER_NAME";
    private static final String SLICE_HINT_TYPE_DISPLAY_NAME = "androidx.credentials.provider.credentialEntry.SLICE_HINT_TYPE_DISPLAY_NAME";
    private static final String TAG = "PasswordCredentialEntry";
    private final boolean autoSelectAllowedFromOption;
    private final CharSequence displayName;
    private final Icon icon;
    private final boolean isAutoSelectAllowed;
    private final boolean isDefaultIcon;
    private final Instant lastUsedTime;
    private final PendingIntent pendingIntent;
    private final CharSequence typeDisplayName;
    private final CharSequence username;

    @JvmStatic
    public static final PasswordCredentialEntry fromSlice(Slice slice) {
        return Companion.fromSlice(slice);
    }

    @JvmStatic
    public static final Slice toSlice(PasswordCredentialEntry passwordCredentialEntry) {
        return Companion.toSlice(passwordCredentialEntry);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ PasswordCredentialEntry(java.lang.CharSequence r15, java.lang.CharSequence r16, java.lang.CharSequence r17, android.app.PendingIntent r18, java.time.Instant r19, android.graphics.drawable.Icon r20, boolean r21, androidx.credentials.provider.BeginGetPasswordOption r22, boolean r23, boolean r24, int r25, kotlin.jvm.internal.DefaultConstructorMarker r26) {
        /*
            r14 = this;
            r0 = r25
            r1 = r0 & 256(0x100, float:3.59E-43)
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r12 = r2
            goto L_0x000b
        L_0x0009:
            r12 = r23
        L_0x000b:
            r0 = r0 & 512(0x200, float:7.175E-43)
            if (r0 == 0) goto L_0x0011
            r13 = r2
            goto L_0x0013
        L_0x0011:
            r13 = r24
        L_0x0013:
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r17
            r7 = r18
            r8 = r19
            r9 = r20
            r10 = r21
            r11 = r22
            r3.<init>((java.lang.CharSequence) r4, (java.lang.CharSequence) r5, (java.lang.CharSequence) r6, (android.app.PendingIntent) r7, (java.time.Instant) r8, (android.graphics.drawable.Icon) r9, (boolean) r10, (androidx.credentials.provider.BeginGetPasswordOption) r11, (boolean) r12, (boolean) r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.provider.PasswordCredentialEntry.<init>(java.lang.CharSequence, java.lang.CharSequence, java.lang.CharSequence, android.app.PendingIntent, java.time.Instant, android.graphics.drawable.Icon, boolean, androidx.credentials.provider.BeginGetPasswordOption, boolean, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final CharSequence getUsername() {
        return this.username;
    }

    public final CharSequence getDisplayName() {
        return this.displayName;
    }

    public final CharSequence getTypeDisplayName() {
        return this.typeDisplayName;
    }

    public final PendingIntent getPendingIntent() {
        return this.pendingIntent;
    }

    public final Instant getLastUsedTime() {
        return this.lastUsedTime;
    }

    public final Icon getIcon() {
        return this.icon;
    }

    public final boolean isAutoSelectAllowed() {
        return this.isAutoSelectAllowed;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PasswordCredentialEntry(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, PendingIntent pendingIntent2, Instant instant, Icon icon2, boolean z, BeginGetPasswordOption beginGetPasswordOption, boolean z2, boolean z3) {
        super(PasswordCredential.TYPE_PASSWORD_CREDENTIAL, beginGetPasswordOption);
        Intrinsics.checkNotNullParameter(charSequence, "username");
        Intrinsics.checkNotNullParameter(charSequence3, "typeDisplayName");
        Intrinsics.checkNotNullParameter(pendingIntent2, BaseGmsClient.KEY_PENDING_INTENT);
        Intrinsics.checkNotNullParameter(icon2, "icon");
        Intrinsics.checkNotNullParameter(beginGetPasswordOption, "beginGetPasswordOption");
        this.username = charSequence;
        this.displayName = charSequence2;
        this.typeDisplayName = charSequence3;
        this.pendingIntent = pendingIntent2;
        this.lastUsedTime = instant;
        this.icon = icon2;
        this.isAutoSelectAllowed = z;
        this.autoSelectAllowedFromOption = z2;
        this.isDefaultIcon = z3;
        if (!(charSequence.length() > 0)) {
            throw new IllegalArgumentException("username must not be empty".toString());
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ PasswordCredentialEntry(android.content.Context r13, java.lang.CharSequence r14, android.app.PendingIntent r15, androidx.credentials.provider.BeginGetPasswordOption r16, java.lang.CharSequence r17, java.time.Instant r18, android.graphics.drawable.Icon r19, boolean r20, int r21, kotlin.jvm.internal.DefaultConstructorMarker r22) {
        /*
            r12 = this;
            r0 = r21
            r1 = r0 & 16
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r8 = r2
            goto L_0x000b
        L_0x0009:
            r8 = r17
        L_0x000b:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0011
            r9 = r2
            goto L_0x0013
        L_0x0011:
            r9 = r18
        L_0x0013:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0025
            int r1 = androidx.credentials.R.drawable.ic_password
            r2 = r13
            android.graphics.drawable.Icon r1 = android.graphics.drawable.Icon.createWithResource(r13, r1)
            java.lang.String r3 = "createWithResource(conte…, R.drawable.ic_password)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            r10 = r1
            goto L_0x0028
        L_0x0025:
            r2 = r13
            r10 = r19
        L_0x0028:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x002f
            r0 = 0
            r11 = r0
            goto L_0x0031
        L_0x002f:
            r11 = r20
        L_0x0031:
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r7 = r16
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.provider.PasswordCredentialEntry.<init>(android.content.Context, java.lang.CharSequence, android.app.PendingIntent, androidx.credentials.provider.BeginGetPasswordOption, java.lang.CharSequence, java.time.Instant, android.graphics.drawable.Icon, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PasswordCredentialEntry(android.content.Context r16, java.lang.CharSequence r17, android.app.PendingIntent r18, androidx.credentials.provider.BeginGetPasswordOption r19, java.lang.CharSequence r20, java.time.Instant r21, android.graphics.drawable.Icon r22, boolean r23) {
        /*
            r15 = this;
            r0 = r16
            java.lang.String r1 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            java.lang.String r1 = "username"
            r3 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r1)
            java.lang.String r1 = "pendingIntent"
            r6 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r1)
            java.lang.String r1 = "beginGetPasswordOption"
            r10 = r19
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r1)
            java.lang.String r1 = "icon"
            r8 = r22
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r1)
            int r1 = androidx.credentials.R.string.android_credentials_TYPE_PASSWORD_CREDENTIAL
            java.lang.String r0 = r0.getString(r1)
            java.lang.String r1 = "context.getString(\n     …WORD_CREDENTIAL\n        )"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r5 = r0
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r11 = 0
            r12 = 0
            r13 = 768(0x300, float:1.076E-42)
            r14 = 0
            r2 = r15
            r4 = r20
            r7 = r21
            r9 = r23
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.credentials.provider.PasswordCredentialEntry.<init>(android.content.Context, java.lang.CharSequence, android.app.PendingIntent, androidx.credentials.provider.BeginGetPasswordOption, java.lang.CharSequence, java.time.Instant, android.graphics.drawable.Icon, boolean):void");
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007¨\u0006\t"}, d2 = {"Landroidx/credentials/provider/PasswordCredentialEntry$Api28Impl;", "", "()V", "fromSlice", "Landroidx/credentials/provider/PasswordCredentialEntry;", "slice", "Landroid/app/slice/Slice;", "toSlice", "entry", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PasswordCredentialEntry.kt */
    private static final class Api28Impl {
        public static final Api28Impl INSTANCE = new Api28Impl();

        private Api28Impl() {
        }

        @JvmStatic
        public static final Slice toSlice(PasswordCredentialEntry passwordCredentialEntry) {
            Intrinsics.checkNotNullParameter(passwordCredentialEntry, "entry");
            String type = passwordCredentialEntry.getType();
            CharSequence username = passwordCredentialEntry.getUsername();
            CharSequence displayName = passwordCredentialEntry.getDisplayName();
            PendingIntent pendingIntent = passwordCredentialEntry.getPendingIntent();
            CharSequence typeDisplayName = passwordCredentialEntry.getTypeDisplayName();
            Instant lastUsedTime = passwordCredentialEntry.getLastUsedTime();
            Icon icon = passwordCredentialEntry.getIcon();
            boolean isAutoSelectAllowed = passwordCredentialEntry.isAutoSelectAllowed();
            BeginGetCredentialOption beginGetCredentialOption = passwordCredentialEntry.getBeginGetCredentialOption();
            Slice.Builder addIcon = new Slice.Builder(Uri.EMPTY, new SliceSpec(type, 1)).addText(typeDisplayName, (String) null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_TYPE_DISPLAY_NAME)).addText(username, (String) null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_TITLE)).addText(displayName, (String) null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_SUBTITLE)).addText(isAutoSelectAllowed ? PasswordCredentialEntry.AUTO_SELECT_TRUE_STRING : "false", (String) null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_AUTO_ALLOWED)).addText(beginGetCredentialOption.getId(), (String) null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_OPTION_ID)).addIcon(icon, (String) null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_ICON));
            try {
                if (icon.getResId() == R.drawable.ic_password) {
                    addIcon.addInt(1, (String) null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_DEFAULT_ICON_RES_ID));
                }
            } catch (IllegalStateException unused) {
            }
            if (CredentialOption.Companion.extractAutoSelectValue$credentials_release(beginGetCredentialOption.getCandidateQueryData())) {
                addIcon.addInt(1, (String) null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_AUTO_SELECT_FROM_OPTION));
            }
            if (lastUsedTime != null) {
                addIcon.addLong(lastUsedTime.toEpochMilli(), (String) null, CollectionsKt.listOf(PasswordCredentialEntry.SLICE_HINT_LAST_USED_TIME_MILLIS));
            }
            addIcon.addAction(pendingIntent, new Slice.Builder(addIcon).addHints(Collections.singletonList(PasswordCredentialEntry.SLICE_HINT_PENDING_INTENT)).build(), (String) null);
            Slice build = addIcon.build();
            Intrinsics.checkNotNullExpressionValue(build, "sliceBuilder.build()");
            return build;
        }

        @JvmStatic
        public static final PasswordCredentialEntry fromSlice(Slice slice) {
            Intrinsics.checkNotNullParameter(slice, "slice");
            List<SliceItem> items = slice.getItems();
            Intrinsics.checkNotNullExpressionValue(items, "slice.items");
            CharSequence charSequence = null;
            CharSequence charSequence2 = null;
            CharSequence charSequence3 = null;
            PendingIntent pendingIntent = null;
            Instant instant = null;
            Icon icon = null;
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            CharSequence charSequence4 = null;
            for (SliceItem sliceItem : items) {
                if (sliceItem.hasHint(PasswordCredentialEntry.SLICE_HINT_TYPE_DISPLAY_NAME)) {
                    charSequence3 = sliceItem.getText();
                } else if (sliceItem.hasHint(PasswordCredentialEntry.SLICE_HINT_TITLE)) {
                    charSequence = sliceItem.getText();
                } else if (sliceItem.hasHint(PasswordCredentialEntry.SLICE_HINT_SUBTITLE)) {
                    charSequence2 = sliceItem.getText();
                } else if (sliceItem.hasHint(PasswordCredentialEntry.SLICE_HINT_ICON)) {
                    icon = sliceItem.getIcon();
                } else if (sliceItem.hasHint(PasswordCredentialEntry.SLICE_HINT_PENDING_INTENT)) {
                    pendingIntent = sliceItem.getAction();
                } else if (sliceItem.hasHint(PasswordCredentialEntry.SLICE_HINT_OPTION_ID)) {
                    charSequence4 = sliceItem.getText();
                } else if (sliceItem.hasHint(PasswordCredentialEntry.SLICE_HINT_LAST_USED_TIME_MILLIS)) {
                    instant = Instant.ofEpochMilli(sliceItem.getLong());
                } else if (sliceItem.hasHint(PasswordCredentialEntry.SLICE_HINT_AUTO_ALLOWED)) {
                    if (Intrinsics.areEqual((Object) sliceItem.getText(), (Object) PasswordCredentialEntry.AUTO_SELECT_TRUE_STRING)) {
                        z = true;
                    }
                } else if (sliceItem.hasHint(PasswordCredentialEntry.SLICE_HINT_AUTO_SELECT_FROM_OPTION)) {
                    z2 = true;
                } else if (sliceItem.hasHint(PasswordCredentialEntry.SLICE_HINT_DEFAULT_ICON_RES_ID)) {
                    z3 = true;
                }
            }
            try {
                Intrinsics.checkNotNull(charSequence);
                Intrinsics.checkNotNull(charSequence3);
                Intrinsics.checkNotNull(pendingIntent);
                Intrinsics.checkNotNull(icon);
                BeginGetPasswordOption.Companion companion = BeginGetPasswordOption.Companion;
                Bundle bundle = new Bundle();
                Intrinsics.checkNotNull(charSequence4);
                return new PasswordCredentialEntry(charSequence, charSequence2, charSequence3, pendingIntent, instant, icon, z, companion.createFrom$credentials_release(bundle, charSequence4.toString()), z2, z3);
            } catch (Exception e) {
                Log.i(PasswordCredentialEntry.TAG, "fromSlice failed with: " + e.getMessage());
                return null;
            }
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0018\u001a\u00020\u0014H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Landroidx/credentials/provider/PasswordCredentialEntry$Companion;", "", "()V", "AUTO_SELECT_FALSE_STRING", "", "AUTO_SELECT_TRUE_STRING", "REVISION_ID", "", "SLICE_HINT_AUTO_ALLOWED", "SLICE_HINT_AUTO_SELECT_FROM_OPTION", "SLICE_HINT_DEFAULT_ICON_RES_ID", "SLICE_HINT_ICON", "SLICE_HINT_LAST_USED_TIME_MILLIS", "SLICE_HINT_OPTION_ID", "SLICE_HINT_PENDING_INTENT", "SLICE_HINT_SUBTITLE", "SLICE_HINT_TITLE", "SLICE_HINT_TYPE_DISPLAY_NAME", "TAG", "fromSlice", "Landroidx/credentials/provider/PasswordCredentialEntry;", "slice", "Landroid/app/slice/Slice;", "toSlice", "entry", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PasswordCredentialEntry.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Slice toSlice(PasswordCredentialEntry passwordCredentialEntry) {
            Intrinsics.checkNotNullParameter(passwordCredentialEntry, "entry");
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.toSlice(passwordCredentialEntry);
            }
            return null;
        }

        @JvmStatic
        public final PasswordCredentialEntry fromSlice(Slice slice) {
            Intrinsics.checkNotNullParameter(slice, "slice");
            if (Build.VERSION.SDK_INT >= 28) {
                return Api28Impl.fromSlice(slice);
            }
            return null;
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u0015\u001a\u00020\u00002\b\u0010\r\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0017\u001a\u00020\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/credentials/provider/PasswordCredentialEntry$Builder;", "", "context", "Landroid/content/Context;", "username", "", "pendingIntent", "Landroid/app/PendingIntent;", "beginGetPasswordOption", "Landroidx/credentials/provider/BeginGetPasswordOption;", "(Landroid/content/Context;Ljava/lang/CharSequence;Landroid/app/PendingIntent;Landroidx/credentials/provider/BeginGetPasswordOption;)V", "autoSelectAllowed", "", "displayName", "icon", "Landroid/graphics/drawable/Icon;", "lastUsedTime", "Ljava/time/Instant;", "build", "Landroidx/credentials/provider/PasswordCredentialEntry;", "setAutoSelectAllowed", "setDisplayName", "setIcon", "setLastUsedTime", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PasswordCredentialEntry.kt */
    public static final class Builder {
        private boolean autoSelectAllowed;
        private final BeginGetPasswordOption beginGetPasswordOption;
        private final Context context;
        private CharSequence displayName;
        private Icon icon;
        private Instant lastUsedTime;
        private final PendingIntent pendingIntent;
        private final CharSequence username;

        public Builder(Context context2, CharSequence charSequence, PendingIntent pendingIntent2, BeginGetPasswordOption beginGetPasswordOption2) {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(charSequence, "username");
            Intrinsics.checkNotNullParameter(pendingIntent2, BaseGmsClient.KEY_PENDING_INTENT);
            Intrinsics.checkNotNullParameter(beginGetPasswordOption2, "beginGetPasswordOption");
            this.context = context2;
            this.username = charSequence;
            this.pendingIntent = pendingIntent2;
            this.beginGetPasswordOption = beginGetPasswordOption2;
        }

        public final Builder setDisplayName(CharSequence charSequence) {
            this.displayName = charSequence;
            return this;
        }

        public final Builder setIcon(Icon icon2) {
            Intrinsics.checkNotNullParameter(icon2, "icon");
            this.icon = icon2;
            return this;
        }

        public final Builder setAutoSelectAllowed(boolean z) {
            this.autoSelectAllowed = z;
            return this;
        }

        public final Builder setLastUsedTime(Instant instant) {
            this.lastUsedTime = instant;
            return this;
        }

        public final PasswordCredentialEntry build() {
            if (this.icon == null) {
                this.icon = Icon.createWithResource(this.context, R.drawable.ic_password);
            }
            String string = this.context.getString(R.string.android_credentials_TYPE_PASSWORD_CREDENTIAL);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(\n     …_CREDENTIAL\n            )");
            PendingIntent pendingIntent2 = this.pendingIntent;
            Instant instant = this.lastUsedTime;
            Icon icon2 = this.icon;
            Intrinsics.checkNotNull(icon2);
            return new PasswordCredentialEntry(this.username, this.displayName, string, pendingIntent2, instant, icon2, this.autoSelectAllowed, this.beginGetPasswordOption, false, false, ViewUtils.EDGE_TO_EDGE_FLAGS, (DefaultConstructorMarker) null);
        }
    }
}
