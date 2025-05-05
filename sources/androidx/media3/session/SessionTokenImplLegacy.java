package androidx.media3.session;

import android.content.ComponentName;
import android.media.session.MediaSession;
import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.session.SessionToken;
import androidx.media3.session.legacy.MediaSessionCompat;
import com.google.common.base.Objects;

final class SessionTokenImplLegacy implements SessionToken.SessionTokenImpl {
    private static final String FIELD_COMPONENT_NAME = Util.intToStringMaxRadix(3);
    private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(5);
    private static final String FIELD_LEGACY_TOKEN = Util.intToStringMaxRadix(0);
    private static final String FIELD_PACKAGE_NAME = Util.intToStringMaxRadix(4);
    private static final String FIELD_TYPE = Util.intToStringMaxRadix(2);
    private static final String FIELD_UID = Util.intToStringMaxRadix(1);
    private final ComponentName componentName;
    private final Bundle extras;
    private final MediaSessionCompat.Token legacyToken;
    private final String packageName;
    private final int type;
    private final int uid;

    public int getInterfaceVersion() {
        return 0;
    }

    public int getLibraryVersion() {
        return 0;
    }

    public boolean isLegacySession() {
        return true;
    }

    public SessionTokenImplLegacy(MediaSessionCompat.Token token, String str, int i, Bundle bundle) {
        this((MediaSessionCompat.Token) Assertions.checkNotNull(token), i, 100, (ComponentName) null, Assertions.checkNotEmpty(str), (Bundle) Assertions.checkNotNull(bundle));
    }

    public SessionTokenImplLegacy(ComponentName componentName2, int i) {
        this((MediaSessionCompat.Token) null, i, 101, (ComponentName) Assertions.checkNotNull(componentName2), componentName2.getPackageName(), Bundle.EMPTY);
    }

    private SessionTokenImplLegacy(MediaSessionCompat.Token token, int i, int i2, ComponentName componentName2, String str, Bundle bundle) {
        this.legacyToken = token;
        this.uid = i;
        this.type = i2;
        this.componentName = componentName2;
        this.packageName = str;
        this.extras = bundle;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), this.componentName, this.legacyToken);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionTokenImplLegacy)) {
            return false;
        }
        SessionTokenImplLegacy sessionTokenImplLegacy = (SessionTokenImplLegacy) obj;
        int i = this.type;
        if (i != sessionTokenImplLegacy.type) {
            return false;
        }
        if (i == 100) {
            return Util.areEqual(this.legacyToken, sessionTokenImplLegacy.legacyToken);
        }
        if (i != 101) {
            return false;
        }
        return Util.areEqual(this.componentName, sessionTokenImplLegacy.componentName);
    }

    public String toString() {
        return "SessionToken {legacy, uid=" + this.uid + "}";
    }

    public int getUid() {
        return this.uid;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getServiceName() {
        ComponentName componentName2 = this.componentName;
        return componentName2 == null ? "" : componentName2.getClassName();
    }

    public ComponentName getComponentName() {
        return this.componentName;
    }

    public int getType() {
        return this.type != 101 ? 0 : 2;
    }

    public Bundle getExtras() {
        return new Bundle(this.extras);
    }

    public Object getBinder() {
        return this.legacyToken;
    }

    public MediaSession.Token getPlatformToken() {
        MediaSessionCompat.Token token = this.legacyToken;
        if (token == null) {
            return null;
        }
        return (MediaSession.Token) token.getToken();
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        String str = FIELD_LEGACY_TOKEN;
        MediaSessionCompat.Token token = this.legacyToken;
        bundle.putBundle(str, token == null ? null : token.toBundle());
        bundle.putInt(FIELD_UID, this.uid);
        bundle.putInt(FIELD_TYPE, this.type);
        bundle.putParcelable(FIELD_COMPONENT_NAME, this.componentName);
        bundle.putString(FIELD_PACKAGE_NAME, this.packageName);
        bundle.putBundle(FIELD_EXTRAS, this.extras);
        return bundle;
    }

    public static SessionTokenImplLegacy fromBundle(Bundle bundle) {
        MediaSessionCompat.Token token;
        Bundle bundle2 = bundle.getBundle(FIELD_LEGACY_TOKEN);
        if (bundle2 == null) {
            token = null;
        } else {
            token = MediaSessionCompat.Token.fromBundle(bundle2);
        }
        MediaSessionCompat.Token token2 = token;
        String str = FIELD_UID;
        Assertions.checkArgument(bundle.containsKey(str), "uid should be set.");
        int i = bundle.getInt(str);
        String str2 = FIELD_TYPE;
        Assertions.checkArgument(bundle.containsKey(str2), "type should be set.");
        int i2 = bundle.getInt(str2);
        ComponentName componentName2 = (ComponentName) bundle.getParcelable(FIELD_COMPONENT_NAME);
        String checkNotEmpty = Assertions.checkNotEmpty(bundle.getString(FIELD_PACKAGE_NAME), "package name should be set.");
        Bundle bundle3 = bundle.getBundle(FIELD_EXTRAS);
        if (bundle3 == null) {
            bundle3 = Bundle.EMPTY;
        }
        return new SessionTokenImplLegacy(token2, i, i2, componentName2, checkNotEmpty, bundle3);
    }
}
