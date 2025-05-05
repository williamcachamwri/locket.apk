package androidx.camera.video;

import android.location.Location;
import androidx.core.util.Preconditions;

public abstract class OutputOptions {
    public static final int DURATION_UNLIMITED = 0;
    public static final int FILE_SIZE_UNLIMITED = 0;
    private final OutputOptionsInternal mOutputOptionsInternal;

    OutputOptions(OutputOptionsInternal outputOptionsInternal) {
        this.mOutputOptionsInternal = outputOptionsInternal;
    }

    public long getFileSizeLimit() {
        return this.mOutputOptionsInternal.getFileSizeLimit();
    }

    public Location getLocation() {
        return this.mOutputOptionsInternal.getLocation();
    }

    public long getDurationLimitMillis() {
        return this.mOutputOptionsInternal.getDurationLimitMillis();
    }

    static abstract class Builder<T extends OutputOptions, B> {
        final OutputOptionsInternal.Builder<?> mRootInternalBuilder;

        /* access modifiers changed from: package-private */
        public abstract T build();

        Builder(OutputOptionsInternal.Builder<?> builder) {
            this.mRootInternalBuilder = builder;
            builder.setFileSizeLimit(0);
            builder.setDurationLimitMillis(0);
        }

        public B setFileSizeLimit(long j) {
            Preconditions.checkArgument(j >= 0, "The specified file size limit can't be negative.");
            this.mRootInternalBuilder.setFileSizeLimit(j);
            return this;
        }

        public B setDurationLimitMillis(long j) {
            Preconditions.checkArgument(j >= 0, "The specified duration limit can't be negative.");
            this.mRootInternalBuilder.setDurationLimitMillis(j);
            return this;
        }

        public B setLocation(Location location) {
            if (location != null) {
                boolean z = true;
                Preconditions.checkArgument(location.getLatitude() >= -90.0d && location.getLatitude() <= 90.0d, "Latitude must be in the range [-90, 90]");
                if (location.getLongitude() < -180.0d || location.getLongitude() > 180.0d) {
                    z = false;
                }
                Preconditions.checkArgument(z, "Longitude must be in the range [-180, 180]");
            }
            this.mRootInternalBuilder.setLocation(location);
            return this;
        }
    }

    static abstract class OutputOptionsInternal {
        /* access modifiers changed from: package-private */
        public abstract long getDurationLimitMillis();

        /* access modifiers changed from: package-private */
        public abstract long getFileSizeLimit();

        /* access modifiers changed from: package-private */
        public abstract Location getLocation();

        OutputOptionsInternal() {
        }

        static abstract class Builder<B> {
            /* access modifiers changed from: package-private */
            public abstract OutputOptionsInternal build();

            /* access modifiers changed from: package-private */
            public abstract B setDurationLimitMillis(long j);

            /* access modifiers changed from: package-private */
            public abstract B setFileSizeLimit(long j);

            /* access modifiers changed from: package-private */
            public abstract B setLocation(Location location);

            Builder() {
            }
        }
    }
}
