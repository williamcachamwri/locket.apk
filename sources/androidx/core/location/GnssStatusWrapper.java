package androidx.core.location;

import android.location.GnssStatus;
import android.os.Build;
import androidx.core.util.Preconditions;

class GnssStatusWrapper extends GnssStatusCompat {
    private final GnssStatus mWrapped;

    GnssStatusWrapper(Object obj) {
        this.mWrapped = (GnssStatus) Preconditions.checkNotNull((GnssStatus) obj);
    }

    public int getSatelliteCount() {
        return this.mWrapped.getSatelliteCount();
    }

    public int getConstellationType(int i) {
        return this.mWrapped.getConstellationType(i);
    }

    public int getSvid(int i) {
        return this.mWrapped.getSvid(i);
    }

    public float getCn0DbHz(int i) {
        return this.mWrapped.getCn0DbHz(i);
    }

    public float getElevationDegrees(int i) {
        return this.mWrapped.getElevationDegrees(i);
    }

    public float getAzimuthDegrees(int i) {
        return this.mWrapped.getAzimuthDegrees(i);
    }

    public boolean hasEphemerisData(int i) {
        return this.mWrapped.hasEphemerisData(i);
    }

    public boolean hasAlmanacData(int i) {
        return this.mWrapped.hasAlmanacData(i);
    }

    public boolean usedInFix(int i) {
        return this.mWrapped.usedInFix(i);
    }

    public boolean hasCarrierFrequencyHz(int i) {
        return Api26Impl.hasCarrierFrequencyHz(this.mWrapped, i);
    }

    public float getCarrierFrequencyHz(int i) {
        return Api26Impl.getCarrierFrequencyHz(this.mWrapped, i);
    }

    public boolean hasBasebandCn0DbHz(int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.hasBasebandCn0DbHz(this.mWrapped, i);
        }
        return false;
    }

    public float getBasebandCn0DbHz(int i) {
        if (Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.getBasebandCn0DbHz(this.mWrapped, i);
        }
        throw new UnsupportedOperationException();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GnssStatusWrapper)) {
            return false;
        }
        return this.mWrapped.equals(((GnssStatusWrapper) obj).mWrapped);
    }

    public int hashCode() {
        return this.mWrapped.hashCode();
    }

    static class Api26Impl {
        private Api26Impl() {
        }

        static float getCarrierFrequencyHz(GnssStatus gnssStatus, int i) {
            return gnssStatus.getCarrierFrequencyHz(i);
        }

        static boolean hasCarrierFrequencyHz(GnssStatus gnssStatus, int i) {
            return gnssStatus.hasCarrierFrequencyHz(i);
        }
    }

    static class Api30Impl {
        private Api30Impl() {
        }

        static boolean hasBasebandCn0DbHz(GnssStatus gnssStatus, int i) {
            return gnssStatus.hasBasebandCn0DbHz(i);
        }

        static float getBasebandCn0DbHz(GnssStatus gnssStatus, int i) {
            return gnssStatus.getBasebandCn0DbHz(i);
        }
    }
}
