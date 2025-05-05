package androidx.credentials.provider.utils;

import android.os.Build;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Landroidx/credentials/provider/utils/PrivilegedApp;", "", "packageName", "", "fingerprints", "", "(Ljava/lang/String;Ljava/util/Set;)V", "getFingerprints", "()Ljava/util/Set;", "getPackageName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PrivilegedApp.kt */
public final class PrivilegedApp {
    private static final String ANDROID_TYPE_KEY = "android";
    private static final String APPS_KEY = "apps";
    private static final String APP_INFO_KEY = "info";
    private static final String BUILD_KEY = "build";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String FINGERPRINT_KEY = "cert_fingerprint_sha256";
    private static final String PACKAGE_NAME_KEY = "package_name";
    private static final String SIGNATURES_KEY = "signatures";
    private static final String TYPE_KEY = "type";
    private static final String USER_BUILD_TYPE = "userdebug";
    private static final String USER_DEBUG_KEY = "userdebug";
    private final Set<String> fingerprints;
    private final String packageName;

    public static /* synthetic */ PrivilegedApp copy$default(PrivilegedApp privilegedApp, String str, Set<String> set, int i, Object obj) {
        if ((i & 1) != 0) {
            str = privilegedApp.packageName;
        }
        if ((i & 2) != 0) {
            set = privilegedApp.fingerprints;
        }
        return privilegedApp.copy(str, set);
    }

    @JvmStatic
    public static final PrivilegedApp createFromJSONObject(JSONObject jSONObject, boolean z) {
        return Companion.createFromJSONObject(jSONObject, z);
    }

    @JvmStatic
    public static final List<PrivilegedApp> extractPrivilegedApps$credentials_release(JSONObject jSONObject) {
        return Companion.extractPrivilegedApps$credentials_release(jSONObject);
    }

    public final String component1() {
        return this.packageName;
    }

    public final Set<String> component2() {
        return this.fingerprints;
    }

    public final PrivilegedApp copy(String str, Set<String> set) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        Intrinsics.checkNotNullParameter(set, "fingerprints");
        return new PrivilegedApp(str, set);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrivilegedApp)) {
            return false;
        }
        PrivilegedApp privilegedApp = (PrivilegedApp) obj;
        return Intrinsics.areEqual((Object) this.packageName, (Object) privilegedApp.packageName) && Intrinsics.areEqual((Object) this.fingerprints, (Object) privilegedApp.fingerprints);
    }

    public int hashCode() {
        return (this.packageName.hashCode() * 31) + this.fingerprints.hashCode();
    }

    public String toString() {
        return "PrivilegedApp(packageName=" + this.packageName + ", fingerprints=" + this.fingerprints + ')';
    }

    public PrivilegedApp(String str, Set<String> set) {
        Intrinsics.checkNotNullParameter(str, RemoteConfigConstants.RequestFieldKey.PACKAGE_NAME);
        Intrinsics.checkNotNullParameter(set, "fingerprints");
        this.packageName = str;
        this.fingerprints = set;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final Set<String> getFingerprints() {
        return this.fingerprints;
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u001b\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00152\u0006\u0010\u0016\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0017R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/credentials/provider/utils/PrivilegedApp$Companion;", "", "()V", "ANDROID_TYPE_KEY", "", "APPS_KEY", "APP_INFO_KEY", "BUILD_KEY", "FINGERPRINT_KEY", "PACKAGE_NAME_KEY", "SIGNATURES_KEY", "TYPE_KEY", "USER_BUILD_TYPE", "USER_DEBUG_KEY", "createFromJSONObject", "Landroidx/credentials/provider/utils/PrivilegedApp;", "appInfoJsonObject", "Lorg/json/JSONObject;", "filterUserDebug", "", "extractPrivilegedApps", "", "jsonObject", "extractPrivilegedApps$credentials_release", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PrivilegedApp.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final List<PrivilegedApp> extractPrivilegedApps$credentials_release(JSONObject jSONObject) {
            Intrinsics.checkNotNullParameter(jSONObject, "jsonObject");
            List<PrivilegedApp> arrayList = new ArrayList<>();
            JSONArray jSONArray = jSONObject.getJSONArray(PrivilegedApp.APPS_KEY);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (Intrinsics.areEqual((Object) jSONObject2.getString("type"), (Object) "android")) {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject(PrivilegedApp.APP_INFO_KEY);
                    Intrinsics.checkNotNullExpressionValue(jSONObject3, "appJsonObject.getJSONObject(APP_INFO_KEY)");
                    arrayList.add(createFromJSONObject(jSONObject3, true));
                }
            }
            return arrayList;
        }

        @JvmStatic
        public final PrivilegedApp createFromJSONObject(JSONObject jSONObject, boolean z) {
            Intrinsics.checkNotNullParameter(jSONObject, "appInfoJsonObject");
            JSONArray jSONArray = jSONObject.getJSONArray(PrivilegedApp.SIGNATURES_KEY);
            Set linkedHashSet = new LinkedHashSet();
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                if (!z || !Intrinsics.areEqual((Object) "userdebug", (Object) jSONArray.getJSONObject(i).optString("build")) || Intrinsics.areEqual((Object) "userdebug", (Object) Build.TYPE)) {
                    String string = jSONArray.getJSONObject(i).getString(PrivilegedApp.FINGERPRINT_KEY);
                    Intrinsics.checkNotNullExpressionValue(string, "signaturesJson.getJSONOb…etString(FINGERPRINT_KEY)");
                    linkedHashSet.add(string);
                }
            }
            String string2 = jSONObject.getString("package_name");
            Intrinsics.checkNotNullExpressionValue(string2, "appInfoJsonObject.getString(PACKAGE_NAME_KEY)");
            return new PrivilegedApp(string2, linkedHashSet);
        }
    }
}
