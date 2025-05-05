package com.locket.Locket;

import android.telephony.TelephonyManager;
import android.util.Log;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class CountryCodeModule extends ReactContextBaseJavaModule {
    public String getName() {
        return "CountryCodeModule";
    }

    CountryCodeModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void getNetworkCountryIso(Promise promise) {
        String networkCountryIso = ((TelephonyManager) getReactApplicationContext().getSystemService("phone")).getNetworkCountryIso();
        Log.d("CountryCodeModule", networkCountryIso);
        promise.resolve(networkCountryIso);
    }
}
