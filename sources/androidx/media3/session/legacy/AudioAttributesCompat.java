package androidx.media3.session.legacy;

import android.media.AudioAttributes;
import android.util.SparseIntArray;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class AudioAttributesCompat {
    public static final int CONTENT_TYPE_MOVIE = 3;
    public static final int CONTENT_TYPE_MUSIC = 2;
    public static final int CONTENT_TYPE_SONIFICATION = 4;
    public static final int CONTENT_TYPE_SPEECH = 1;
    public static final int CONTENT_TYPE_UNKNOWN = 0;
    static final int FLAG_ALL = 1023;
    static final int FLAG_ALL_PUBLIC = 273;
    public static final int FLAG_AUDIBILITY_ENFORCED = 1;
    static final int FLAG_BEACON = 8;
    static final int FLAG_BYPASS_INTERRUPTION_POLICY = 64;
    static final int FLAG_BYPASS_MUTE = 128;
    static final int FLAG_DEEP_BUFFER = 512;
    public static final int FLAG_HW_AV_SYNC = 16;
    static final int FLAG_HW_HOTWORD = 32;
    static final int FLAG_LOW_LATENCY = 256;
    static final int FLAG_SCO = 4;
    static final int FLAG_SECURE = 2;
    static final int INVALID_STREAM_TYPE = -1;
    private static final int[] SDK_USAGES = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16};
    private static final int SUPPRESSIBLE_CALL = 2;
    private static final int SUPPRESSIBLE_NOTIFICATION = 1;
    private static final SparseIntArray SUPPRESSIBLE_USAGES;
    static final String TAG = "AudioAttributesCompat";
    public static final int USAGE_ALARM = 4;
    public static final int USAGE_ASSISTANCE_ACCESSIBILITY = 11;
    public static final int USAGE_ASSISTANCE_NAVIGATION_GUIDANCE = 12;
    public static final int USAGE_ASSISTANCE_SONIFICATION = 13;
    public static final int USAGE_ASSISTANT = 16;
    public static final int USAGE_GAME = 14;
    public static final int USAGE_MEDIA = 1;
    public static final int USAGE_NOTIFICATION = 5;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_DELAYED = 9;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_INSTANT = 8;
    public static final int USAGE_NOTIFICATION_COMMUNICATION_REQUEST = 7;
    public static final int USAGE_NOTIFICATION_EVENT = 10;
    public static final int USAGE_NOTIFICATION_RINGTONE = 6;
    public static final int USAGE_UNKNOWN = 0;
    public static final int USAGE_VIRTUAL_SOURCE = 15;
    public static final int USAGE_VOICE_COMMUNICATION = 2;
    public static final int USAGE_VOICE_COMMUNICATION_SIGNALLING = 3;
    static boolean sForceLegacyBehavior;
    public final AudioAttributesImpl mImpl;

    @Retention(RetentionPolicy.SOURCE)
    public @interface AttributeContentType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AttributeUsage {
    }

    public interface AudioAttributesImpl {

        public interface Builder {
            AudioAttributesImpl build();

            Builder setContentType(int i);

            Builder setFlags(int i);

            Builder setLegacyStreamType(int i);

            Builder setUsage(int i);
        }

        Object getAudioAttributes();

        int getContentType();

        int getFlags();

        int getLegacyStreamType();

        int getRawLegacyStreamType();

        int getUsage();

        int getVolumeControlStream();
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        SUPPRESSIBLE_USAGES = sparseIntArray;
        sparseIntArray.put(5, 1);
        sparseIntArray.put(6, 2);
        sparseIntArray.put(7, 2);
        sparseIntArray.put(8, 1);
        sparseIntArray.put(9, 1);
        sparseIntArray.put(10, 1);
    }

    AudioAttributesCompat(AudioAttributesImpl audioAttributesImpl) {
        this.mImpl = audioAttributesImpl;
    }

    public int getVolumeControlStream() {
        return this.mImpl.getVolumeControlStream();
    }

    public Object unwrap() {
        return this.mImpl.getAudioAttributes();
    }

    public int getLegacyStreamType() {
        return this.mImpl.getLegacyStreamType();
    }

    public static AudioAttributesCompat wrap(Object obj) {
        if (sForceLegacyBehavior) {
            return null;
        }
        return new AudioAttributesCompat(new AudioAttributesImplApi26((AudioAttributes) obj));
    }

    public int getContentType() {
        return this.mImpl.getContentType();
    }

    public int getUsage() {
        return this.mImpl.getUsage();
    }

    public int getFlags() {
        return this.mImpl.getFlags();
    }

    public static class Builder {
        final AudioAttributesImpl.Builder mBuilderImpl;

        public Builder() {
            if (AudioAttributesCompat.sForceLegacyBehavior) {
                this.mBuilderImpl = new AudioAttributesImplBase.Builder();
            } else {
                this.mBuilderImpl = new AudioAttributesImplApi26.Builder();
            }
        }

        public Builder(AudioAttributesCompat audioAttributesCompat) {
            if (AudioAttributesCompat.sForceLegacyBehavior) {
                this.mBuilderImpl = new AudioAttributesImplBase.Builder(audioAttributesCompat);
            } else {
                this.mBuilderImpl = new AudioAttributesImplApi26.Builder(Assertions.checkNotNull(audioAttributesCompat.unwrap()));
            }
        }

        public AudioAttributesCompat build() {
            return new AudioAttributesCompat(this.mBuilderImpl.build());
        }

        public Builder setUsage(int i) {
            this.mBuilderImpl.setUsage(i);
            return this;
        }

        public Builder setContentType(int i) {
            this.mBuilderImpl.setContentType(i);
            return this;
        }

        public Builder setFlags(int i) {
            this.mBuilderImpl.setFlags(i);
            return this;
        }

        public Builder setLegacyStreamType(int i) {
            this.mBuilderImpl.setLegacyStreamType(i);
            return this;
        }
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public String toString() {
        return this.mImpl.toString();
    }

    static String usageToString(int i) {
        switch (i) {
            case 0:
                return "USAGE_UNKNOWN";
            case 1:
                return "USAGE_MEDIA";
            case 2:
                return "USAGE_VOICE_COMMUNICATION";
            case 3:
                return "USAGE_VOICE_COMMUNICATION_SIGNALLING";
            case 4:
                return "USAGE_ALARM";
            case 5:
                return "USAGE_NOTIFICATION";
            case 6:
                return "USAGE_NOTIFICATION_RINGTONE";
            case 7:
                return "USAGE_NOTIFICATION_COMMUNICATION_REQUEST";
            case 8:
                return "USAGE_NOTIFICATION_COMMUNICATION_INSTANT";
            case 9:
                return "USAGE_NOTIFICATION_COMMUNICATION_DELAYED";
            case 10:
                return "USAGE_NOTIFICATION_EVENT";
            case 11:
                return "USAGE_ASSISTANCE_ACCESSIBILITY";
            case 12:
                return "USAGE_ASSISTANCE_NAVIGATION_GUIDANCE";
            case 13:
                return "USAGE_ASSISTANCE_SONIFICATION";
            case 14:
                return "USAGE_GAME";
            case 16:
                return "USAGE_ASSISTANT";
            default:
                return "unknown usage " + i;
        }
    }

    static abstract class AudioManagerHidden {
        public static final int STREAM_ACCESSIBILITY = 10;
        public static final int STREAM_BLUETOOTH_SCO = 6;
        public static final int STREAM_SYSTEM_ENFORCED = 7;
        public static final int STREAM_TTS = 9;

        private AudioManagerHidden() {
        }
    }

    public static void setForceLegacyBehavior(boolean z) {
        sForceLegacyBehavior = z;
    }

    /* access modifiers changed from: package-private */
    public int getRawLegacyStreamType() {
        return this.mImpl.getRawLegacyStreamType();
    }

    static int toVolumeStreamType(boolean z, int i, int i2) {
        if ((i & 1) == 1) {
            return z ? 1 : 7;
        }
        if ((i & 4) == 4) {
            return z ? 0 : 6;
        }
        switch (i2) {
            case 0:
            case 1:
            case 12:
            case 14:
            case 16:
                return 3;
            case 2:
                return 0;
            case 3:
                return z ? 0 : 8;
            case 4:
                return 4;
            case 5:
            case 7:
            case 8:
            case 9:
            case 10:
                return 5;
            case 6:
                return 2;
            case 11:
                return 10;
            case 13:
                return 1;
            default:
                if (!z) {
                    return 3;
                }
                throw new IllegalArgumentException("Unknown usage value " + i2 + " in audio attributes");
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AudioAttributesCompat)) {
            return false;
        }
        AudioAttributesCompat audioAttributesCompat = (AudioAttributesCompat) obj;
        AudioAttributesImpl audioAttributesImpl = this.mImpl;
        if (audioAttributesImpl != null) {
            return audioAttributesImpl.equals(audioAttributesCompat.mImpl);
        }
        if (audioAttributesCompat.mImpl == null) {
            return true;
        }
        return false;
    }

    public static class AudioAttributesImplBase implements AudioAttributesImpl {
        public int mContentType;
        public int mFlags;
        public int mLegacyStream;
        public int mUsage;

        static int usageForStreamType(int i) {
            switch (i) {
                case 0:
                    return 2;
                case 1:
                case 7:
                    return 13;
                case 2:
                    return 6;
                case 3:
                    return 1;
                case 4:
                    return 4;
                case 5:
                    return 5;
                case 6:
                    return 2;
                case 8:
                    return 3;
                case 10:
                    return 11;
                default:
                    return 0;
            }
        }

        public Object getAudioAttributes() {
            return null;
        }

        public AudioAttributesImplBase() {
            this.mUsage = 0;
            this.mContentType = 0;
            this.mFlags = 0;
            this.mLegacyStream = -1;
        }

        AudioAttributesImplBase(int i, int i2, int i3, int i4) {
            this.mContentType = i;
            this.mFlags = i2;
            this.mUsage = i3;
            this.mLegacyStream = i4;
        }

        public int getVolumeControlStream() {
            return AudioAttributesCompat.toVolumeStreamType(true, this.mFlags, this.mUsage);
        }

        public int getLegacyStreamType() {
            int i = this.mLegacyStream;
            if (i != -1) {
                return i;
            }
            return AudioAttributesCompat.toVolumeStreamType(false, this.mFlags, this.mUsage);
        }

        public int getRawLegacyStreamType() {
            return this.mLegacyStream;
        }

        public int getContentType() {
            return this.mContentType;
        }

        public int getUsage() {
            return this.mUsage;
        }

        public int getFlags() {
            int i = this.mFlags;
            int legacyStreamType = getLegacyStreamType();
            if (legacyStreamType == 6) {
                i |= 4;
            } else if (legacyStreamType == 7) {
                i |= 1;
            }
            return i & AudioAttributesCompat.FLAG_ALL_PUBLIC;
        }

        public int hashCode() {
            return Arrays.hashCode(new Object[]{Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUsage), Integer.valueOf(this.mLegacyStream)});
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof AudioAttributesImplBase)) {
                return false;
            }
            AudioAttributesImplBase audioAttributesImplBase = (AudioAttributesImplBase) obj;
            if (this.mContentType == audioAttributesImplBase.getContentType() && this.mFlags == audioAttributesImplBase.getFlags() && this.mUsage == audioAttributesImplBase.getUsage() && this.mLegacyStream == audioAttributesImplBase.mLegacyStream) {
                return true;
            }
            return false;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("AudioAttributesCompat:");
            if (this.mLegacyStream != -1) {
                sb.append(" stream=").append(this.mLegacyStream);
                sb.append(" derived");
            }
            sb.append(" usage=").append(AudioAttributesCompat.usageToString(this.mUsage)).append(" content=").append(this.mContentType).append(" flags=0x").append(Integer.toHexString(this.mFlags).toUpperCase(Locale.ROOT));
            return sb.toString();
        }

        static class Builder implements AudioAttributesImpl.Builder {
            private int mContentType = 0;
            private int mFlags = 0;
            private int mLegacyStream = -1;
            private int mUsage = 0;

            Builder() {
            }

            Builder(AudioAttributesCompat audioAttributesCompat) {
                this.mUsage = audioAttributesCompat.getUsage();
                this.mContentType = audioAttributesCompat.getContentType();
                this.mFlags = audioAttributesCompat.getFlags();
                this.mLegacyStream = audioAttributesCompat.getRawLegacyStreamType();
            }

            public AudioAttributesImpl build() {
                return new AudioAttributesImplBase(this.mContentType, this.mFlags, this.mUsage, this.mLegacyStream);
            }

            public Builder setUsage(int i) {
                switch (i) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                        this.mUsage = i;
                        break;
                    case 16:
                        this.mUsage = 12;
                        break;
                    default:
                        this.mUsage = 0;
                        break;
                }
                return this;
            }

            public Builder setContentType(int i) {
                if (i == 0 || i == 1 || i == 2 || i == 3 || i == 4) {
                    this.mContentType = i;
                } else {
                    this.mContentType = 0;
                }
                return this;
            }

            public Builder setFlags(int i) {
                this.mFlags = (i & 1023) | this.mFlags;
                return this;
            }

            public Builder setLegacyStreamType(int i) {
                if (i != 10) {
                    this.mLegacyStream = i;
                    return setInternalLegacyStreamType(i);
                }
                throw new IllegalArgumentException("STREAM_ACCESSIBILITY is not a legacy stream type that was used for audio playback");
            }

            private Builder setInternalLegacyStreamType(int i) {
                switch (i) {
                    case 0:
                        this.mContentType = 1;
                        break;
                    case 1:
                        break;
                    case 2:
                        this.mContentType = 4;
                        break;
                    case 3:
                        this.mContentType = 2;
                        break;
                    case 4:
                        this.mContentType = 4;
                        break;
                    case 5:
                        this.mContentType = 4;
                        break;
                    case 6:
                        this.mContentType = 1;
                        this.mFlags |= 4;
                        break;
                    case 7:
                        this.mFlags = 1 | this.mFlags;
                        break;
                    case 8:
                        this.mContentType = 4;
                        break;
                    case 9:
                        this.mContentType = 4;
                        break;
                    case 10:
                        this.mContentType = 1;
                        break;
                    default:
                        Log.e(AudioAttributesCompat.TAG, "Invalid stream type " + i + " for AudioAttributesCompat");
                        break;
                }
                this.mContentType = 4;
                this.mUsage = AudioAttributesImplBase.usageForStreamType(i);
                return this;
            }
        }
    }

    public static class AudioAttributesImplApi21 implements AudioAttributesImpl {
        public AudioAttributes mAudioAttributes;
        public int mLegacyStreamType;

        public AudioAttributesImplApi21() {
            this.mLegacyStreamType = -1;
        }

        AudioAttributesImplApi21(AudioAttributes audioAttributes) {
            this(audioAttributes, -1);
        }

        AudioAttributesImplApi21(AudioAttributes audioAttributes, int i) {
            this.mAudioAttributes = audioAttributes;
            this.mLegacyStreamType = i;
        }

        public Object getAudioAttributes() {
            return this.mAudioAttributes;
        }

        public int getVolumeControlStream() {
            return AudioAttributesCompat.toVolumeStreamType(true, getFlags(), getUsage());
        }

        public int getLegacyStreamType() {
            int i = this.mLegacyStreamType;
            if (i != -1) {
                return i;
            }
            return AudioAttributesCompat.toVolumeStreamType(false, getFlags(), getUsage());
        }

        public int getRawLegacyStreamType() {
            return this.mLegacyStreamType;
        }

        public int getContentType() {
            return ((AudioAttributes) Assertions.checkNotNull(this.mAudioAttributes)).getContentType();
        }

        public int getUsage() {
            return ((AudioAttributes) Assertions.checkNotNull(this.mAudioAttributes)).getUsage();
        }

        public int getFlags() {
            return ((AudioAttributes) Assertions.checkNotNull(this.mAudioAttributes)).getFlags();
        }

        public int hashCode() {
            return ((AudioAttributes) Assertions.checkNotNull(this.mAudioAttributes)).hashCode();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof AudioAttributesImplApi21)) {
                return false;
            }
            return Objects.equals(this.mAudioAttributes, ((AudioAttributesImplApi21) obj).mAudioAttributes);
        }

        public String toString() {
            return "AudioAttributesCompat: audioattributes=" + this.mAudioAttributes;
        }

        static class Builder implements AudioAttributesImpl.Builder {
            final AudioAttributes.Builder mFwkBuilder;

            Builder() {
                this.mFwkBuilder = new AudioAttributes.Builder();
            }

            Builder(Object obj) {
                this.mFwkBuilder = new AudioAttributes.Builder((AudioAttributes) obj);
            }

            public AudioAttributesImpl build() {
                return new AudioAttributesImplApi21(this.mFwkBuilder.build());
            }

            public Builder setUsage(int i) {
                if (i == 16) {
                    i = 12;
                }
                this.mFwkBuilder.setUsage(i);
                return this;
            }

            public Builder setContentType(int i) {
                this.mFwkBuilder.setContentType(i);
                return this;
            }

            public Builder setFlags(int i) {
                this.mFwkBuilder.setFlags(i);
                return this;
            }

            public Builder setLegacyStreamType(int i) {
                this.mFwkBuilder.setLegacyStreamType(i);
                return this;
            }
        }
    }

    public static class AudioAttributesImplApi26 extends AudioAttributesImplApi21 {
        public AudioAttributesImplApi26() {
        }

        AudioAttributesImplApi26(AudioAttributes audioAttributes) {
            super(audioAttributes, -1);
        }

        public int getVolumeControlStream() {
            return ((AudioAttributes) Assertions.checkNotNull(this.mAudioAttributes)).getVolumeControlStream();
        }

        static class Builder extends AudioAttributesImplApi21.Builder {
            Builder() {
            }

            Builder(Object obj) {
                super(obj);
            }

            public AudioAttributesImpl build() {
                return new AudioAttributesImplApi26(this.mFwkBuilder.build());
            }

            public Builder setUsage(int i) {
                this.mFwkBuilder.setUsage(i);
                return this;
            }
        }
    }
}
