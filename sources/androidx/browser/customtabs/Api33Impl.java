package androidx.browser.customtabs;

import android.os.Bundle;

class Api33Impl {
    private Api33Impl() {
    }

    static <T> T getParcelable(Bundle bundle, String str, Class<T> cls) {
        return bundle.getParcelable(str, cls);
    }
}
