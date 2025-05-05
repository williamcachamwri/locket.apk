package androidx.camera.video;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.location.Location;
import android.net.Uri;
import androidx.camera.video.MediaStoreOutputOptions;

final class AutoValue_MediaStoreOutputOptions_MediaStoreOutputOptionsInternal extends MediaStoreOutputOptions.MediaStoreOutputOptionsInternal {
    private final Uri collectionUri;
    private final ContentResolver contentResolver;
    private final ContentValues contentValues;
    private final long durationLimitMillis;
    private final long fileSizeLimit;
    private final Location location;

    private AutoValue_MediaStoreOutputOptions_MediaStoreOutputOptionsInternal(long j, long j2, Location location2, ContentResolver contentResolver2, Uri uri, ContentValues contentValues2) {
        this.fileSizeLimit = j;
        this.durationLimitMillis = j2;
        this.location = location2;
        this.contentResolver = contentResolver2;
        this.collectionUri = uri;
        this.contentValues = contentValues2;
    }

    /* access modifiers changed from: package-private */
    public long getFileSizeLimit() {
        return this.fileSizeLimit;
    }

    /* access modifiers changed from: package-private */
    public long getDurationLimitMillis() {
        return this.durationLimitMillis;
    }

    /* access modifiers changed from: package-private */
    public Location getLocation() {
        return this.location;
    }

    /* access modifiers changed from: package-private */
    public ContentResolver getContentResolver() {
        return this.contentResolver;
    }

    /* access modifiers changed from: package-private */
    public Uri getCollectionUri() {
        return this.collectionUri;
    }

    /* access modifiers changed from: package-private */
    public ContentValues getContentValues() {
        return this.contentValues;
    }

    public String toString() {
        return "MediaStoreOutputOptionsInternal{fileSizeLimit=" + this.fileSizeLimit + ", durationLimitMillis=" + this.durationLimitMillis + ", location=" + this.location + ", contentResolver=" + this.contentResolver + ", collectionUri=" + this.collectionUri + ", contentValues=" + this.contentValues + "}";
    }

    public boolean equals(Object obj) {
        Location location2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MediaStoreOutputOptions.MediaStoreOutputOptionsInternal)) {
            return false;
        }
        MediaStoreOutputOptions.MediaStoreOutputOptionsInternal mediaStoreOutputOptionsInternal = (MediaStoreOutputOptions.MediaStoreOutputOptionsInternal) obj;
        if (this.fileSizeLimit != mediaStoreOutputOptionsInternal.getFileSizeLimit() || this.durationLimitMillis != mediaStoreOutputOptionsInternal.getDurationLimitMillis() || ((location2 = this.location) != null ? !location2.equals(mediaStoreOutputOptionsInternal.getLocation()) : mediaStoreOutputOptionsInternal.getLocation() != null) || !this.contentResolver.equals(mediaStoreOutputOptionsInternal.getContentResolver()) || !this.collectionUri.equals(mediaStoreOutputOptionsInternal.getCollectionUri()) || !this.contentValues.equals(mediaStoreOutputOptionsInternal.getContentValues())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.fileSizeLimit;
        long j2 = this.durationLimitMillis;
        int i = (((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003;
        Location location2 = this.location;
        return ((((((i ^ (location2 == null ? 0 : location2.hashCode())) * 1000003) ^ this.contentResolver.hashCode()) * 1000003) ^ this.collectionUri.hashCode()) * 1000003) ^ this.contentValues.hashCode();
    }

    static final class Builder extends MediaStoreOutputOptions.MediaStoreOutputOptionsInternal.Builder {
        private Uri collectionUri;
        private ContentResolver contentResolver;
        private ContentValues contentValues;
        private Long durationLimitMillis;
        private Long fileSizeLimit;
        private Location location;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public MediaStoreOutputOptions.MediaStoreOutputOptionsInternal.Builder setFileSizeLimit(long j) {
            this.fileSizeLimit = Long.valueOf(j);
            return this;
        }

        /* access modifiers changed from: package-private */
        public MediaStoreOutputOptions.MediaStoreOutputOptionsInternal.Builder setDurationLimitMillis(long j) {
            this.durationLimitMillis = Long.valueOf(j);
            return this;
        }

        /* access modifiers changed from: package-private */
        public MediaStoreOutputOptions.MediaStoreOutputOptionsInternal.Builder setLocation(Location location2) {
            this.location = location2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public MediaStoreOutputOptions.MediaStoreOutputOptionsInternal.Builder setContentResolver(ContentResolver contentResolver2) {
            if (contentResolver2 != null) {
                this.contentResolver = contentResolver2;
                return this;
            }
            throw new NullPointerException("Null contentResolver");
        }

        /* access modifiers changed from: package-private */
        public MediaStoreOutputOptions.MediaStoreOutputOptionsInternal.Builder setCollectionUri(Uri uri) {
            if (uri != null) {
                this.collectionUri = uri;
                return this;
            }
            throw new NullPointerException("Null collectionUri");
        }

        /* access modifiers changed from: package-private */
        public MediaStoreOutputOptions.MediaStoreOutputOptionsInternal.Builder setContentValues(ContentValues contentValues2) {
            if (contentValues2 != null) {
                this.contentValues = contentValues2;
                return this;
            }
            throw new NullPointerException("Null contentValues");
        }

        /* access modifiers changed from: package-private */
        public MediaStoreOutputOptions.MediaStoreOutputOptionsInternal build() {
            String str = this.fileSizeLimit == null ? " fileSizeLimit" : "";
            if (this.durationLimitMillis == null) {
                str = str + " durationLimitMillis";
            }
            if (this.contentResolver == null) {
                str = str + " contentResolver";
            }
            if (this.collectionUri == null) {
                str = str + " collectionUri";
            }
            if (this.contentValues == null) {
                str = str + " contentValues";
            }
            if (str.isEmpty()) {
                return new AutoValue_MediaStoreOutputOptions_MediaStoreOutputOptionsInternal(this.fileSizeLimit.longValue(), this.durationLimitMillis.longValue(), this.location, this.contentResolver, this.collectionUri, this.contentValues);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
