package expo.modules.core.interfaces;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public interface ReactActivityLifecycleListener {
    boolean onBackPressed() {
        return false;
    }

    void onContentChanged(Activity activity) {
    }

    void onCreate(Activity activity, Bundle bundle) {
    }

    void onDestroy(Activity activity) {
    }

    boolean onNewIntent(Intent intent) {
        return false;
    }

    void onPause(Activity activity) {
    }

    void onResume(Activity activity) {
    }
}
