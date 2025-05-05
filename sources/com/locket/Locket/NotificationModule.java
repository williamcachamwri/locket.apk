package com.locket.Locket;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

class NotificationModule extends ReactContextBaseJavaModule {
    public String getName() {
        return "NotificationModule";
    }

    public NotificationModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void suppressConversationNotifications(String str) {
        NotificationStateManager.getInstance().setSuppressedConversationId(str);
    }

    @ReactMethod
    public void unsuppressConversationNotifications() {
        NotificationStateManager.getInstance().setSuppressedConversationId("");
    }
}
