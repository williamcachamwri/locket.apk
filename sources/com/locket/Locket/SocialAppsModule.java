package com.locket.Locket;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Telephony;
import androidx.core.net.MailTo;
import androidx.webkit.internal.AssetHelper;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import io.sentry.android.core.SentryLogcatAdapter;

public class SocialAppsModule extends ReactContextBaseJavaModule {
    Context context;

    public String getName() {
        return "SocialAppsModule";
    }

    SocialAppsModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.context = reactApplicationContext.getApplicationContext();
    }

    @ReactMethod
    public void sendMessages(String str) {
        PackageManager packageManager = this.context.getPackageManager();
        String defaultSmsPackage = Telephony.Sms.getDefaultSmsPackage(this.context);
        if (defaultSmsPackage == null || !defaultSmsPackage.equals("com.facebook.orca")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SENDTO");
            intent.setFlags(1342210048);
            intent.setData(Uri.parse("smsto:"));
            intent.putExtra("sms_body", str);
            intent.setPackage(defaultSmsPackage);
            if (intent.resolveActivity(packageManager) != null) {
                this.context.startActivity(intent);
                return;
            }
            return;
        }
        createActionSendIntent(str, "com.facebook.orca");
    }

    @ReactMethod
    public void sendWhatsapp(String str) {
        createActionSendIntent(str, "com.whatsapp");
    }

    @ReactMethod
    public void sendTelegram(String str) {
        createActionSendIntent(str, "org.telegram.messenger");
    }

    @ReactMethod
    public void sendInstagramDm(String str) {
        createActionSendIntent(str, "com.instagram.android");
    }

    @ReactMethod
    public void sendSnapchatDm(String str) {
        createActionSendIntent(str, "com.snapchat.android");
    }

    @ReactMethod
    public void sendMessengerDm(String str) {
        createActionSendIntent(str, "com.facebook.orca");
    }

    @ReactMethod
    public void sendLineDm(String str) {
        createActionSendIntent(str, "jp.naver.line.android");
    }

    @ReactMethod
    public void sendKakaoTalkDm(String str) {
        createActionSendIntent(str, "com.kakao.talk");
    }

    @ReactMethod
    public void sendZaloDm(String str) {
        createActionSendIntent(str, "com.zing.zalo");
    }

    @ReactMethod
    public void sendEmail(String str, String str2, String str3) {
        PackageManager packageManager = this.context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SENDTO");
        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME + str + "?subject=" + str2 + "&body=" + str3));
        intent.setFlags(268468224);
        if (intent.resolveActivity(packageManager) != null) {
            this.context.startActivity(intent);
        }
    }

    private void createActionSendIntent(String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setFlags(1342210048);
        intent.setType(AssetHelper.DEFAULT_MIME_TYPE);
        intent.putExtra("android.intent.extra.TEXT", str);
        intent.setPackage(str2);
        try {
            this.context.startActivity(intent);
        } catch (Exception e) {
            SentryLogcatAdapter.e("SocialAppsModule Error", e.getMessage());
        }
    }
}
