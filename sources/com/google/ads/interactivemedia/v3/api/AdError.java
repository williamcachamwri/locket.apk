package com.google.ads.interactivemedia.v3.api;

import androidx.constraintlayout.core.motion.utils.TypedValues;

/* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
public final class AdError extends Exception {
    private final AdErrorCode zza;
    private final AdErrorType zzb;

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    public enum AdErrorCode {
        INTERNAL_ERROR(-1),
        VAST_MALFORMED_RESPONSE(100),
        UNKNOWN_AD_RESPONSE(1010),
        VAST_TRAFFICKING_ERROR(200),
        VAST_LOAD_TIMEOUT(301),
        VAST_TOO_MANY_REDIRECTS(302),
        VAST_NO_ADS_AFTER_WRAPPER(303),
        VIDEO_PLAY_ERROR(400),
        VAST_MEDIA_LOAD_TIMEOUT(TypedValues.CycleType.TYPE_VISIBILITY),
        VAST_LINEAR_ASSET_MISMATCH(TypedValues.CycleType.TYPE_ALPHA),
        OVERLAY_AD_PLAYING_FAILED(500),
        OVERLAY_AD_LOADING_FAILED(TypedValues.PositionType.TYPE_DRAWPATH),
        VAST_NONLINEAR_ASSET_MISMATCH(TypedValues.PositionType.TYPE_PERCENT_WIDTH),
        COMPANION_AD_LOADING_FAILED(TypedValues.MotionType.TYPE_EASING),
        UNKNOWN_ERROR(900),
        VAST_EMPTY_RESPONSE(1009),
        FAILED_TO_REQUEST_ADS(1005),
        VAST_ASSET_NOT_FOUND(1007),
        ADS_REQUEST_NETWORK_ERROR(1012),
        INVALID_ARGUMENTS(1101),
        PLAYLIST_NO_CONTENT_TRACKING(1205),
        UNEXPECTED_ADS_LOADED_EVENT(1206),
        ADS_PLAYER_NOT_PROVIDED(1207);
        
        private final int zzb;

        private AdErrorCode(int i) {
            this.zzb = i;
        }

        public static AdErrorCode getErrorCodeByNumber(int i) {
            for (AdErrorCode adErrorCode : values()) {
                if (adErrorCode.getErrorNumber() == i) {
                    return adErrorCode;
                }
            }
            return i == 1204 ? INTERNAL_ERROR : UNKNOWN_ERROR;
        }

        public int getErrorNumber() {
            return this.zzb;
        }

        public String toString() {
            String name = name();
            return "AdErrorCode [name: " + name + ", number: " + this.zzb + "]";
        }
    }

    /* compiled from: com.google.ads.interactivemedia.v3:interactivemedia@@3.35.1 */
    public enum AdErrorType {
        LOAD,
        PLAY
    }

    public AdError(AdErrorType adErrorType, int i, String str) {
        this(adErrorType, AdErrorCode.getErrorCodeByNumber(i), str);
    }

    public AdErrorCode getErrorCode() {
        return this.zza;
    }

    public int getErrorCodeNumber() {
        return this.zza.getErrorNumber();
    }

    public AdErrorType getErrorType() {
        return this.zzb;
    }

    public String getMessage() {
        return super.getMessage();
    }

    public String toString() {
        AdErrorCode adErrorCode = this.zza;
        String valueOf = String.valueOf(this.zzb);
        String valueOf2 = String.valueOf(adErrorCode);
        String message = getMessage();
        return "AdError [errorType: " + valueOf + ", errorCode: " + valueOf2 + ", message: " + message + "]";
    }

    public AdError(AdErrorType adErrorType, AdErrorCode adErrorCode, String str) {
        super(str);
        this.zzb = adErrorType;
        this.zza = adErrorCode;
    }
}
