package com.amplitude.api;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.LocaleList;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class DeviceInfo {
    public static final String OS_NAME = "android";
    private static final String SETTING_ADVERTISING_ID = "advertising_id";
    private static final String SETTING_LIMIT_AD_TRACKING = "limit_ad_tracking";
    /* access modifiers changed from: private */
    public static final String TAG = "com.amplitude.api.DeviceInfo";
    private CachedInfo cachedInfo;
    /* access modifiers changed from: private */
    public Context context;
    private boolean locationListening;

    private class CachedInfo {
        /* access modifiers changed from: private */
        public String advertisingId;
        /* access modifiers changed from: private */
        public String appSetId;
        /* access modifiers changed from: private */
        public String brand;
        /* access modifiers changed from: private */
        public String carrier;
        /* access modifiers changed from: private */
        public String country;
        /* access modifiers changed from: private */
        public boolean gpsEnabled;
        /* access modifiers changed from: private */
        public String language;
        /* access modifiers changed from: private */
        public boolean limitAdTrackingEnabled;
        /* access modifiers changed from: private */
        public String manufacturer;
        /* access modifiers changed from: private */
        public String model;
        /* access modifiers changed from: private */
        public String osName;
        /* access modifiers changed from: private */
        public String osVersion;
        /* access modifiers changed from: private */
        public String versionName;

        private String getOsName() {
            return DeviceInfo.OS_NAME;
        }

        private CachedInfo() {
            this.advertisingId = getAdvertisingId();
            this.versionName = getVersionName();
            this.osName = getOsName();
            this.osVersion = getOsVersion();
            this.brand = getBrand();
            this.manufacturer = getManufacturer();
            this.model = getModel();
            this.carrier = getCarrier();
            this.country = getCountry();
            this.language = getLanguage();
            this.gpsEnabled = checkGPSEnabled();
            this.appSetId = getAppSetId();
        }

        private String getVersionName() {
            try {
                return DeviceInfo.this.context.getPackageManager().getPackageInfo(DeviceInfo.this.context.getPackageName(), 0).versionName;
            } catch (PackageManager.NameNotFoundException | Exception unused) {
                return null;
            }
        }

        private String getOsVersion() {
            return Build.VERSION.RELEASE;
        }

        private String getBrand() {
            return Build.BRAND;
        }

        private String getManufacturer() {
            return Build.MANUFACTURER;
        }

        private String getModel() {
            return Build.MODEL;
        }

        private String getCarrier() {
            try {
                return ((TelephonyManager) DeviceInfo.this.context.getSystemService("phone")).getNetworkOperatorName();
            } catch (Exception unused) {
                return null;
            }
        }

        private String getCountry() {
            String countryFromLocation = getCountryFromLocation();
            if (!Utils.isEmptyString(countryFromLocation)) {
                return countryFromLocation;
            }
            String countryFromNetwork = getCountryFromNetwork();
            if (!Utils.isEmptyString(countryFromNetwork)) {
                return countryFromNetwork;
            }
            return getCountryFromLocale();
        }

        private String getCountryFromLocation() {
            Location mostRecentLocation;
            List<Address> fromLocation;
            if (DeviceInfo.this.isLocationListening() && (mostRecentLocation = DeviceInfo.this.getMostRecentLocation()) != null) {
                try {
                    if (Geocoder.isPresent() && (fromLocation = DeviceInfo.this.getGeocoder().getFromLocation(mostRecentLocation.getLatitude(), mostRecentLocation.getLongitude(), 1)) != null) {
                        for (Address next : fromLocation) {
                            if (next != null) {
                                return next.getCountryCode();
                            }
                        }
                    }
                } catch (IOException | IllegalArgumentException | IllegalStateException | NoSuchMethodError | NullPointerException | SecurityException unused) {
                }
            }
            return null;
        }

        private String getCountryFromNetwork() {
            String networkCountryIso;
            try {
                TelephonyManager telephonyManager = (TelephonyManager) DeviceInfo.this.context.getSystemService("phone");
                if (telephonyManager.getPhoneType() == 2 || (networkCountryIso = telephonyManager.getNetworkCountryIso()) == null) {
                    return null;
                }
                return networkCountryIso.toUpperCase(Locale.US);
            } catch (Exception unused) {
                return null;
            }
        }

        private Locale getLocale() {
            LocaleList locales = Resources.getSystem().getConfiguration().getLocales();
            if (locales.isEmpty()) {
                return Locale.getDefault();
            }
            return locales.get(0);
        }

        private String getCountryFromLocale() {
            return getLocale().getCountry();
        }

        private String getLanguage() {
            return getLocale().getLanguage();
        }

        private String getAdvertisingId() {
            if ("Amazon".equals(getManufacturer())) {
                return getAndCacheAmazonAdvertisingId();
            }
            return getAndCacheGoogleAdvertisingId();
        }

        private String getAppSetId() {
            try {
                Object invoke = Class.forName("com.google.android.gms.appset.AppSet").getMethod("getClient", new Class[]{Context.class}).invoke((Object) null, new Object[]{DeviceInfo.this.context});
                Object invoke2 = invoke.getClass().getMethod("getAppSetIdInfo", new Class[0]).invoke(invoke, new Object[0]);
                Object invoke3 = Class.forName("com.google.android.gms.tasks.Tasks").getMethod("await", new Class[]{Class.forName("com.google.android.gms.tasks.Task")}).invoke((Object) null, new Object[]{invoke2});
                this.appSetId = (String) invoke3.getClass().getMethod("getId", new Class[0]).invoke(invoke3, new Object[0]);
            } catch (ClassNotFoundException unused) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services SDK not found for app set id!");
            } catch (InvocationTargetException unused2) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services not available for app set id");
            } catch (Exception e) {
                AmplitudeLog.getLogger().e(DeviceInfo.TAG, "Encountered an error connecting to Google Play Services for app set id", e);
            }
            return this.appSetId;
        }

        private String getAndCacheAmazonAdvertisingId() {
            ContentResolver contentResolver = DeviceInfo.this.context.getContentResolver();
            boolean z = false;
            if (Settings.Secure.getInt(contentResolver, DeviceInfo.SETTING_LIMIT_AD_TRACKING, 0) == 1) {
                z = true;
            }
            this.limitAdTrackingEnabled = z;
            String string = Settings.Secure.getString(contentResolver, DeviceInfo.SETTING_ADVERTISING_ID);
            this.advertisingId = string;
            return string;
        }

        private String getAndCacheGoogleAdvertisingId() {
            try {
                boolean z = true;
                Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{DeviceInfo.this.context});
                Boolean bool = (Boolean) invoke.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0]);
                if (bool == null || !bool.booleanValue()) {
                    z = false;
                }
                this.limitAdTrackingEnabled = z;
                this.advertisingId = (String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
            } catch (ClassNotFoundException unused) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services SDK not found for advertising id!");
            } catch (InvocationTargetException unused2) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services not available for advertising id");
            } catch (Exception e) {
                AmplitudeLog.getLogger().e(DeviceInfo.TAG, "Encountered an error connecting to Google Play Services for advertising id", e);
            }
            return this.advertisingId;
        }

        private boolean checkGPSEnabled() {
            try {
                Integer num = (Integer) Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke((Object) null, new Object[]{DeviceInfo.this.context});
                if (num == null || num.intValue() != 0) {
                    return false;
                }
                return true;
            } catch (NoClassDefFoundError unused) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services Util not found!");
                return false;
            } catch (ClassNotFoundException unused2) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services Util not found!");
                return false;
            } catch (NoSuchMethodException unused3) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services not available");
                return false;
            } catch (InvocationTargetException unused4) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services not available");
                return false;
            } catch (IllegalAccessException unused5) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Google Play Services not available");
                return false;
            } catch (Exception e) {
                AmplitudeLog.getLogger().w(DeviceInfo.TAG, "Error when checking for Google Play Services: " + e);
                return false;
            }
        }
    }

    public DeviceInfo(Context context2, boolean z) {
        this.context = context2;
        this.locationListening = z;
    }

    private CachedInfo getCachedInfo() {
        if (this.cachedInfo == null) {
            this.cachedInfo = new CachedInfo();
        }
        return this.cachedInfo;
    }

    public void prefetch() {
        getCachedInfo();
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public String getVersionName() {
        return getCachedInfo().versionName;
    }

    public String getOsName() {
        return getCachedInfo().osName;
    }

    public String getOsVersion() {
        return getCachedInfo().osVersion;
    }

    public String getBrand() {
        return getCachedInfo().brand;
    }

    public String getManufacturer() {
        return getCachedInfo().manufacturer;
    }

    public String getModel() {
        return getCachedInfo().model;
    }

    public String getCarrier() {
        return getCachedInfo().carrier;
    }

    public String getCountry() {
        return getCachedInfo().country;
    }

    public String getLanguage() {
        return getCachedInfo().language;
    }

    public String getAdvertisingId() {
        return getCachedInfo().advertisingId;
    }

    public boolean isLimitAdTrackingEnabled() {
        return getCachedInfo().limitAdTrackingEnabled;
    }

    public String getAppSetId() {
        return getCachedInfo().appSetId;
    }

    public boolean isGooglePlayServicesEnabled() {
        return getCachedInfo().gpsEnabled;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0033 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.location.Location getMostRecentLocation() {
        /*
            r7 = this;
            java.lang.String r0 = "Failed to get most recent location"
            boolean r1 = r7.isLocationListening()
            r2 = 0
            if (r1 != 0) goto L_0x000a
            return r2
        L_0x000a:
            android.content.Context r1 = r7.context
            boolean r1 = com.amplitude.api.Utils.checkLocationPermissionAllowed(r1)
            if (r1 != 0) goto L_0x0013
            return r2
        L_0x0013:
            android.content.Context r1 = r7.context
            java.lang.String r3 = "location"
            java.lang.Object r1 = r1.getSystemService(r3)
            android.location.LocationManager r1 = (android.location.LocationManager) r1
            if (r1 != 0) goto L_0x0020
            return r2
        L_0x0020:
            r3 = 1
            java.util.List r3 = r1.getProviders(r3)     // Catch:{ Exception | SecurityException -> 0x0026 }
            goto L_0x0027
        L_0x0026:
            r3 = r2
        L_0x0027:
            if (r3 != 0) goto L_0x002a
            return r2
        L_0x002a:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r3 = r3.iterator()
        L_0x0033:
            boolean r5 = r3.hasNext()
            if (r5 == 0) goto L_0x005e
            java.lang.Object r5 = r3.next()
            java.lang.String r5 = (java.lang.String) r5
            android.location.Location r5 = r1.getLastKnownLocation(r5)     // Catch:{ SecurityException -> 0x004e, Exception -> 0x0044 }
            goto L_0x0058
        L_0x0044:
            com.amplitude.api.AmplitudeLog r5 = com.amplitude.api.AmplitudeLog.getLogger()
            java.lang.String r6 = TAG
            r5.w((java.lang.String) r6, (java.lang.String) r0)
            goto L_0x0057
        L_0x004e:
            com.amplitude.api.AmplitudeLog r5 = com.amplitude.api.AmplitudeLog.getLogger()
            java.lang.String r6 = TAG
            r5.w((java.lang.String) r6, (java.lang.String) r0)
        L_0x0057:
            r5 = r2
        L_0x0058:
            if (r5 == 0) goto L_0x0033
            r4.add(r5)
            goto L_0x0033
        L_0x005e:
            java.util.Iterator r0 = r4.iterator()
            r3 = -1
        L_0x0064:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x007f
            java.lang.Object r1 = r0.next()
            android.location.Location r1 = (android.location.Location) r1
            long r5 = r1.getTime()
            int r5 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r5 <= 0) goto L_0x0064
            long r2 = r1.getTime()
            r3 = r2
            r2 = r1
            goto L_0x0064
        L_0x007f:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DeviceInfo.getMostRecentLocation():android.location.Location");
    }

    public boolean isLocationListening() {
        return this.locationListening;
    }

    public void setLocationListening(boolean z) {
        this.locationListening = z;
    }

    /* access modifiers changed from: protected */
    public Geocoder getGeocoder() {
        return new Geocoder(this.context, Locale.ENGLISH);
    }
}
