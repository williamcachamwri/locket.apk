package com.reactnativecommunity.cameraroll;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

public abstract class NativeCameraRollModuleSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    @ReactMethod
    public abstract void deletePhotos(ReadableArray readableArray, Promise promise);

    @ReactMethod
    public abstract void getAlbums(ReadableMap readableMap, Promise promise);

    @ReactMethod
    public abstract void getPhotoByInternalID(String str, ReadableMap readableMap, Promise promise);

    @ReactMethod
    public abstract void getPhotos(ReadableMap readableMap, Promise promise);

    @ReactMethod
    public abstract void saveToCameraRoll(String str, ReadableMap readableMap, Promise promise);

    public NativeCameraRollModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}
