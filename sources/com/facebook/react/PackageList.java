package com.facebook.react;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import cl.json.RNSharePackage;
import com.amplitude.reactnative.AmplitudeReactNativePackage;
import com.brentvatne.react.ReactVideoPackage;
import com.dylanvann.fastimage.FastImageViewPackage;
import com.facebook.react.shell.MainPackageConfig;
import com.facebook.react.shell.MainReactPackage;
import com.google.recaptchaenterprisereactnative.RecaptchaEnterpriseReactNativePackage;
import com.horcrux.svg.SvgPackage;
import com.ijzerenhein.sharedelement.RNSharedElementPackage;
import com.jimmydaddy.imagemarker.ImageMarkerPackage;
import com.klarna.vectordrawable.VectorDrawablePackage;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import com.mrousavy.camera.react.CameraPackage;
import com.reactnativecommunity.asyncstorage.AsyncStoragePackage;
import com.reactnativecommunity.cameraroll.CameraRollPackage;
import com.reactnativecommunity.picker.RNCPickerPackage;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.reactnativekeyboardcontroller.KeyboardControllerPackage;
import com.reactnativemmkv.MmkvPackage;
import com.reactnativepagerview.PagerViewPackage;
import com.swmansion.gesturehandler.RNGestureHandlerPackage;
import com.swmansion.reanimated.ReanimatedPackage;
import com.swmansion.rnscreens.RNScreensPackage;
import com.th3rdwave.safeareacontext.SafeAreaContextPackage;
import expo.modules.ExpoModulesPackage;
import io.invertase.firebase.analytics.ReactNativeFirebaseAnalyticsPackage;
import io.invertase.firebase.app.ReactNativeFirebaseAppPackage;
import io.invertase.firebase.appcheck.ReactNativeFirebaseAppCheckPackage;
import io.invertase.firebase.auth.ReactNativeFirebaseAuthPackage;
import io.invertase.firebase.config.ReactNativeFirebaseConfigPackage;
import io.invertase.firebase.crashlytics.ReactNativeFirebaseCrashlyticsPackage;
import io.invertase.firebase.dynamiclinks.ReactNativeFirebaseDynamicLinksPackage;
import io.invertase.firebase.firestore.ReactNativeFirebaseFirestorePackage;
import io.invertase.firebase.functions.ReactNativeFirebaseFunctionsPackage;
import io.invertase.firebase.messaging.ReactNativeFirebaseMessagingPackage;
import io.invertase.firebase.perf.ReactNativeFirebasePerfPackage;
import io.invertase.firebase.storage.ReactNativeFirebaseStoragePackage;
import io.sentry.react.RNSentryPackage;
import java.util.ArrayList;
import java.util.Arrays;
import org.linusu.RNGetRandomValuesPackage;
import org.reactnative.maskedview.RNCMaskedViewPackage;

public class PackageList {
    private Application application;
    private MainPackageConfig mConfig;
    private ReactNativeHost reactNativeHost;

    public PackageList(ReactNativeHost reactNativeHost2) {
        this(reactNativeHost2, (MainPackageConfig) null);
    }

    public PackageList(Application application2) {
        this(application2, (MainPackageConfig) null);
    }

    public PackageList(ReactNativeHost reactNativeHost2, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = reactNativeHost2;
        this.mConfig = mainPackageConfig;
    }

    public PackageList(Application application2, MainPackageConfig mainPackageConfig) {
        this.reactNativeHost = null;
        this.application = application2;
        this.mConfig = mainPackageConfig;
    }

    private ReactNativeHost getReactNativeHost() {
        return this.reactNativeHost;
    }

    private Resources getResources() {
        return getApplication().getResources();
    }

    private Application getApplication() {
        ReactNativeHost reactNativeHost2 = this.reactNativeHost;
        if (reactNativeHost2 == null) {
            return this.application;
        }
        return reactNativeHost2.getApplication();
    }

    private Context getApplicationContext() {
        return getApplication().getApplicationContext();
    }

    public ArrayList<ReactPackage> getPackages() {
        return new ArrayList<>(Arrays.asList(new ReactPackage[]{new MainReactPackage(this.mConfig), new AmplitudeReactNativePackage(), new RecaptchaEnterpriseReactNativePackage(), new VectorDrawablePackage(), new AsyncStoragePackage(), new CameraRollPackage(), new ReactNativeFirebaseAnalyticsPackage(), new ReactNativeFirebaseAppPackage(), new ReactNativeFirebaseAppCheckPackage(), new ReactNativeFirebaseAuthPackage(), new ReactNativeFirebaseCrashlyticsPackage(), new ReactNativeFirebaseDynamicLinksPackage(), new ReactNativeFirebaseFirestorePackage(), new ReactNativeFirebaseFunctionsPackage(), new ReactNativeFirebaseMessagingPackage(), new ReactNativeFirebasePerfPackage(), new ReactNativeFirebaseConfigPackage(), new ReactNativeFirebaseStoragePackage(), new RNCMaskedViewPackage(), new RNCPickerPackage(), new RNSentryPackage(), new ExpoModulesPackage(), new RNDeviceInfo(), new FastImageViewPackage(), new RNGestureHandlerPackage(), new RNGetRandomValuesPackage(), new ImageMarkerPackage(), new KeyboardControllerPackage(), new MmkvPackage(), new PagerViewPackage(), new ReanimatedPackage(), new SafeAreaContextPackage(), new RNScreensPackage(), new RNSharePackage(), new RNSharedElementPackage(), new SvgPackage(), new ReactVideoPackage(), new CameraPackage(), new RNCWebViewPackage()}));
    }
}
