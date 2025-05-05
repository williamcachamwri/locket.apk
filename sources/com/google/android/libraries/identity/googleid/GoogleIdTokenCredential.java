package com.google.android.libraries.identity.googleid;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.credentials.CustomCredential;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/google/android/libraries/identity/googleid/GoogleIdTokenCredential;", "Landroidx/credentials/CustomCredential;", "id", "", "idToken", "displayName", "familyName", "givenName", "profilePictureUri", "Landroid/net/Uri;", "phoneNumber", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;)V", "getDisplayName", "()Ljava/lang/String;", "getFamilyName", "getGivenName", "getId", "getIdToken", "getPhoneNumber", "getProfilePictureUri", "()Landroid/net/Uri;", "Builder", "Companion", "java.com.google.android.libraries.identity.googleid.granule_granule"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: com.google.android.libraries.identity.googleid:googleid@@1.1.0 */
public final class GoogleIdTokenCredential extends CustomCredential {
    public static final String BUNDLE_KEY_GOOGLE_ID_TOKEN_SUBTYPE = "com.google.android.libraries.identity.googleid.BUNDLE_KEY_GOOGLE_ID_TOKEN_SUBTYPE";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TYPE_GOOGLE_ID_TOKEN_CREDENTIAL = "com.google.android.libraries.identity.googleid.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL";
    public static final String TYPE_GOOGLE_ID_TOKEN_SIWG_CREDENTIAL = "com.google.android.libraries.identity.googleid.TYPE_GOOGLE_ID_TOKEN_SIWG_CREDENTIAL";
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final Uri zzf;
    private final String zzg;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u000f\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0010\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0004J\u0010\u0010\u0013\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0014\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/google/android/libraries/identity/googleid/GoogleIdTokenCredential$Builder;", "", "()V", "displayName", "", "familyName", "givenName", "id", "idToken", "phoneNumber", "profilePictureUri", "Landroid/net/Uri;", "build", "Lcom/google/android/libraries/identity/googleid/GoogleIdTokenCredential;", "setDisplayName", "setFamilyName", "setGivenName", "setId", "setIdToken", "setPhoneNumber", "setProfilePictureUri", "java.com.google.android.libraries.identity.googleid.granule_granule"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: com.google.android.libraries.identity.googleid:googleid@@1.1.0 */
    public static final class Builder {
        private String zza = "";
        private String zzb = "";
        private String zzc;
        private String zzd;
        private String zze;
        private Uri zzf;
        private String zzg;

        public final GoogleIdTokenCredential build() {
            return new GoogleIdTokenCredential(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
        }

        public final Builder setDisplayName(String str) {
            this.zzc = str;
            return this;
        }

        public final Builder setFamilyName(String str) {
            this.zzd = str;
            return this;
        }

        public final Builder setGivenName(String str) {
            this.zze = str;
            return this;
        }

        public final Builder setId(String str) {
            Intrinsics.checkNotNullParameter(str, "id");
            this.zza = str;
            return this;
        }

        public final Builder setIdToken(String str) {
            Intrinsics.checkNotNullParameter(str, "idToken");
            this.zzb = str;
            return this;
        }

        public final Builder setPhoneNumber(String str) {
            this.zzg = str;
            return this;
        }

        public final Builder setProfilePictureUri(Uri uri) {
            this.zzf = uri;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0007JO\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u00042\b\u0010\u001d\u001a\u0004\u0018\u00010\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u00042\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0001¢\u0006\u0002\b\"R\u0016\u0010\u0003\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0002R\u0016\u0010\u0006\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0016\u0010\b\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\t\u0010\u0002R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0002R\u0016\u0010\r\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0002R\u0016\u0010\u000f\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0002R\u0016\u0010\u0011\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0002R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/google/android/libraries/identity/googleid/GoogleIdTokenCredential$Companion;", "", "()V", "BUNDLE_KEY_DISPLAY_NAME", "", "getBUNDLE_KEY_DISPLAY_NAME$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_FAMILY_NAME", "getBUNDLE_KEY_FAMILY_NAME$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_GIVEN_NAME", "getBUNDLE_KEY_GIVEN_NAME$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_GOOGLE_ID_TOKEN_SUBTYPE", "BUNDLE_KEY_ID", "getBUNDLE_KEY_ID$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_ID_TOKEN", "getBUNDLE_KEY_ID_TOKEN$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_PHONE_NUMBER", "getBUNDLE_KEY_PHONE_NUMBER$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_PROFILE_PICTURE_URI", "getBUNDLE_KEY_PROFILE_PICTURE_URI$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "TYPE_GOOGLE_ID_TOKEN_CREDENTIAL", "TYPE_GOOGLE_ID_TOKEN_SIWG_CREDENTIAL", "createFrom", "Lcom/google/android/libraries/identity/googleid/GoogleIdTokenCredential;", "data", "Landroid/os/Bundle;", "toBundle", "id", "idToken", "displayName", "familyName", "givenName", "profilePictureUri", "Landroid/net/Uri;", "phoneNumber", "toBundle$java_com_google_android_libraries_identity_googleid_granule_granule", "java.com.google.android.libraries.identity.googleid.granule_granule"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: com.google.android.libraries.identity.googleid:googleid@@1.1.0 */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
        }

        @JvmStatic
        public final GoogleIdTokenCredential createFrom(Bundle bundle) {
            Uri uri;
            Intrinsics.checkNotNullParameter(bundle, "data");
            try {
                String string = bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_ID");
                String string2 = bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_ID_TOKEN");
                String string3 = bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_DISPLAY_NAME");
                String string4 = bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_FAMILY_NAME");
                String string5 = bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_GIVEN_NAME");
                if (Build.VERSION.SDK_INT >= 33) {
                    uri = (Uri) bundle.getParcelable("com.google.android.libraries.identity.googleid.BUNDLE_KEY_PROFILE_PICTURE_URI", Uri.class);
                } else {
                    uri = (Uri) bundle.getParcelable("com.google.android.libraries.identity.googleid.BUNDLE_KEY_PROFILE_PICTURE_URI");
                }
                String string6 = bundle.getString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_PHONE_NUMBER");
                Intrinsics.checkNotNull(string);
                Intrinsics.checkNotNull(string2);
                return new GoogleIdTokenCredential(string, string2, string3, string4, string5, uri, string6);
            } catch (Exception e) {
                throw new GoogleIdTokenParsingException(e);
            }
        }
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public GoogleIdTokenCredential(java.lang.String r3, java.lang.String r4, java.lang.String r5, java.lang.String r6, java.lang.String r7, android.net.Uri r8, java.lang.String r9) {
        /*
            r2 = this;
            java.lang.String r0 = "id"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r1 = "idToken"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r1)
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = "com.google.android.libraries.identity.googleid.BUNDLE_KEY_ID"
            r0.putString(r1, r3)
            java.lang.String r1 = "com.google.android.libraries.identity.googleid.BUNDLE_KEY_ID_TOKEN"
            r0.putString(r1, r4)
            java.lang.String r1 = "com.google.android.libraries.identity.googleid.BUNDLE_KEY_DISPLAY_NAME"
            r0.putString(r1, r5)
            java.lang.String r1 = "com.google.android.libraries.identity.googleid.BUNDLE_KEY_FAMILY_NAME"
            r0.putString(r1, r6)
            java.lang.String r1 = "com.google.android.libraries.identity.googleid.BUNDLE_KEY_GIVEN_NAME"
            r0.putString(r1, r7)
            java.lang.String r1 = "com.google.android.libraries.identity.googleid.BUNDLE_KEY_PHONE_NUMBER"
            r0.putString(r1, r9)
            java.lang.String r1 = "com.google.android.libraries.identity.googleid.BUNDLE_KEY_PROFILE_PICTURE_URI"
            r0.putParcelable(r1, r8)
            java.lang.String r1 = "com.google.android.libraries.identity.googleid.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL"
            r2.<init>(r1, r0)
            r2.zza = r3
            r2.zzb = r4
            r2.zzc = r5
            r2.zzd = r6
            r2.zze = r7
            r2.zzf = r8
            r2.zzg = r9
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x0060
            int r3 = r4.length()
            if (r3 <= 0) goto L_0x0058
            return
        L_0x0058:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "idToken should not be empty"
            r3.<init>(r4)
            throw r3
        L_0x0060:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "id should not be empty"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, android.net.Uri, java.lang.String):void");
    }

    @JvmStatic
    public static final GoogleIdTokenCredential createFrom(Bundle bundle) {
        return Companion.createFrom(bundle);
    }

    public final String getDisplayName() {
        return this.zzc;
    }

    public final String getFamilyName() {
        return this.zzd;
    }

    public final String getGivenName() {
        return this.zze;
    }

    public final String getId() {
        return this.zza;
    }

    public final String getIdToken() {
        return this.zzb;
    }

    public final String getPhoneNumber() {
        return this.zzg;
    }

    public final Uri getProfilePictureUri() {
        return this.zzf;
    }
}
