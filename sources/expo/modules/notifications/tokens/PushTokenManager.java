package expo.modules.notifications.tokens;

import expo.modules.core.interfaces.SingletonModule;
import expo.modules.notifications.service.delegates.FirebaseMessagingDelegate;
import expo.modules.notifications.tokens.interfaces.FirebaseTokenListener;
import expo.modules.notifications.tokens.interfaces.PushTokenListener;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class PushTokenManager implements SingletonModule, FirebaseTokenListener, expo.modules.notifications.tokens.interfaces.PushTokenManager {
    private static final String SINGLETON_NAME = "PushTokenManager";
    private String mLastToken;
    private WeakHashMap<PushTokenListener, WeakReference<PushTokenListener>> mListenerReferenceMap = new WeakHashMap<>();

    public String getName() {
        return SINGLETON_NAME;
    }

    public PushTokenManager() {
        FirebaseMessagingDelegate.addTokenListener(this);
    }

    public void addListener(PushTokenListener pushTokenListener) {
        if (!this.mListenerReferenceMap.containsKey(pushTokenListener)) {
            this.mListenerReferenceMap.put(pushTokenListener, new WeakReference(pushTokenListener));
            String str = this.mLastToken;
            if (str != null) {
                pushTokenListener.onNewToken(str);
            }
        }
    }

    public void removeListener(PushTokenListener pushTokenListener) {
        this.mListenerReferenceMap.remove(pushTokenListener);
    }

    public void onNewToken(String str) {
        for (WeakReference<PushTokenListener> weakReference : this.mListenerReferenceMap.values()) {
            PushTokenListener pushTokenListener = (PushTokenListener) weakReference.get();
            if (pushTokenListener != null) {
                pushTokenListener.onNewToken(str);
            }
        }
        this.mLastToken = str;
    }
}
