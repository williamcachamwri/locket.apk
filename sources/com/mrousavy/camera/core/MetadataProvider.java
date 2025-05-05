package com.mrousavy.camera.core;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\bH\u0007J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J$\u0010\u001a\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\"\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/mrousavy/camera/core/MetadataProvider;", "Landroid/location/LocationListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "hasLocationPermission", "", "getHasLocationPermission", "()Z", "<set-?>", "Landroid/location/Location;", "location", "getLocation", "()Landroid/location/Location;", "locationManager", "Landroid/location/LocationManager;", "enableLocationUpdates", "", "enable", "onLocationChanged", "onProviderDisabled", "provider", "", "onProviderEnabled", "onStatusChanged", "status", "", "extras", "Landroid/os/Bundle;", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MetadataProvider.kt */
public final class MetadataProvider implements LocationListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "MetadataProvider";
    private static final float UPDATE_DISTANCE_M = 5.0f;
    private static final long UPDATE_INTERVAL_MS = 5000;
    private final Context context;
    private Location location;
    private final LocationManager locationManager;

    public MetadataProvider(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        Object systemService = context2.getSystemService(FirebaseAnalytics.Param.LOCATION);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        this.locationManager = (LocationManager) systemService;
    }

    public final Context getContext() {
        return this.context;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/mrousavy/camera/core/MetadataProvider$Companion;", "", "()V", "TAG", "", "UPDATE_DISTANCE_M", "", "UPDATE_INTERVAL_MS", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MetadataProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    private final boolean getHasLocationPermission() {
        return ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this.context, "android.permission.ACCESS_COARSE_LOCATION") == 0;
    }

    public final Location getLocation() {
        return this.location;
    }

    public final void enableLocationUpdates(boolean z) {
        if (!z) {
            Log.i(TAG, "Stopping location updates...");
            this.locationManager.removeUpdates(this);
        } else if (getHasLocationPermission()) {
            Log.i(TAG, "Start updating location...");
            LocationListener locationListener = this;
            this.locationManager.requestLocationUpdates("gps", 5000, UPDATE_DISTANCE_M, locationListener);
            Location lastKnownLocation = this.locationManager.getLastKnownLocation("gps");
            this.location = lastKnownLocation;
            if (lastKnownLocation == null) {
                this.locationManager.requestSingleUpdate("gps", locationListener, (Looper) null);
            }
        } else {
            throw new LocationPermissionError();
        }
    }

    public void onLocationChanged(Location location2) {
        Intrinsics.checkNotNullParameter(location2, FirebaseAnalytics.Param.LOCATION);
        double latitude = location2.getLatitude();
        Log.i(TAG, "Location updated: " + latitude + ", " + location2.getLongitude());
        this.location = location2;
    }

    public void onProviderDisabled(String str) {
        Intrinsics.checkNotNullParameter(str, "provider");
        Log.i(TAG, "Location Provider " + str + " has been disabled.");
    }

    public void onProviderEnabled(String str) {
        Intrinsics.checkNotNullParameter(str, "provider");
        Log.i(TAG, "Location Provider " + str + " has been enabled.");
    }

    @Deprecated(message = "Deprecated in Java", replaceWith = @ReplaceWith(expression = "", imports = {""}))
    public void onStatusChanged(String str, int i, Bundle bundle) {
        Log.i(TAG, "Location Provider " + str + " status changed: " + i);
    }
}
