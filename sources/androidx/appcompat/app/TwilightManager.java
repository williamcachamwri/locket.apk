package androidx.appcompat.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Calendar;

class TwilightManager {
    private static final int SUNRISE = 6;
    private static final int SUNSET = 22;
    private static final String TAG = "TwilightManager";
    private static TwilightManager sInstance;
    private final Context mContext;
    private final LocationManager mLocationManager;
    private final TwilightState mTwilightState = new TwilightState();

    static TwilightManager getInstance(Context context) {
        if (sInstance == null) {
            Context applicationContext = context.getApplicationContext();
            sInstance = new TwilightManager(applicationContext, (LocationManager) applicationContext.getSystemService(FirebaseAnalytics.Param.LOCATION));
        }
        return sInstance;
    }

    static void setInstance(TwilightManager twilightManager) {
        sInstance = twilightManager;
    }

    TwilightManager(Context context, LocationManager locationManager) {
        this.mContext = context;
        this.mLocationManager = locationManager;
    }

    /* access modifiers changed from: package-private */
    public boolean isNight() {
        TwilightState twilightState = this.mTwilightState;
        if (isStateValid()) {
            return twilightState.isNight;
        }
        Location lastKnownLocation = getLastKnownLocation();
        if (lastKnownLocation != null) {
            updateState(lastKnownLocation);
            return twilightState.isNight;
        }
        Log.i(TAG, "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int i = Calendar.getInstance().get(11);
        return i < 6 || i >= 22;
    }

    private Location getLastKnownLocation() {
        Location location = null;
        Location lastKnownLocationForProvider = PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? getLastKnownLocationForProvider("network") : null;
        if (PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            location = getLastKnownLocationForProvider("gps");
        }
        return (location == null || lastKnownLocationForProvider == null) ? location != null ? location : lastKnownLocationForProvider : location.getTime() > lastKnownLocationForProvider.getTime() ? location : lastKnownLocationForProvider;
    }

    private Location getLastKnownLocationForProvider(String str) {
        try {
            if (this.mLocationManager.isProviderEnabled(str)) {
                return this.mLocationManager.getLastKnownLocation(str);
            }
            return null;
        } catch (Exception e) {
            Log.d(TAG, "Failed to get last known location", e);
            return null;
        }
    }

    private boolean isStateValid() {
        return this.mTwilightState.nextUpdate > System.currentTimeMillis();
    }

    private void updateState(Location location) {
        long j;
        TwilightState twilightState = this.mTwilightState;
        long currentTimeMillis = System.currentTimeMillis();
        TwilightCalculator instance = TwilightCalculator.getInstance();
        TwilightCalculator twilightCalculator = instance;
        twilightCalculator.calculateTwilight(currentTimeMillis - 86400000, location.getLatitude(), location.getLongitude());
        twilightCalculator.calculateTwilight(currentTimeMillis, location.getLatitude(), location.getLongitude());
        boolean z = true;
        if (instance.state != 1) {
            z = false;
        }
        boolean z2 = z;
        long j2 = instance.sunrise;
        long j3 = instance.sunset;
        long j4 = j2;
        instance.calculateTwilight(currentTimeMillis + 86400000, location.getLatitude(), location.getLongitude());
        long j5 = instance.sunrise;
        if (j4 == -1 || j3 == -1) {
            j = 43200000 + currentTimeMillis;
        } else {
            j = (currentTimeMillis > j3 ? j5 + 0 : currentTimeMillis > j4 ? j3 + 0 : j4 + 0) + 60000;
        }
        twilightState.isNight = z2;
        twilightState.nextUpdate = j;
    }

    private static class TwilightState {
        boolean isNight;
        long nextUpdate;

        TwilightState() {
        }
    }
}
