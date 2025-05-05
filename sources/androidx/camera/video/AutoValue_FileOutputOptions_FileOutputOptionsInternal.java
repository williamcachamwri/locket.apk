package androidx.camera.video;

import android.location.Location;
import androidx.camera.video.FileOutputOptions;
import java.io.File;

final class AutoValue_FileOutputOptions_FileOutputOptionsInternal extends FileOutputOptions.FileOutputOptionsInternal {
    private final long durationLimitMillis;
    private final File file;
    private final long fileSizeLimit;
    private final Location location;

    private AutoValue_FileOutputOptions_FileOutputOptionsInternal(long j, long j2, Location location2, File file2) {
        this.fileSizeLimit = j;
        this.durationLimitMillis = j2;
        this.location = location2;
        this.file = file2;
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
    public File getFile() {
        return this.file;
    }

    public String toString() {
        return "FileOutputOptionsInternal{fileSizeLimit=" + this.fileSizeLimit + ", durationLimitMillis=" + this.durationLimitMillis + ", location=" + this.location + ", file=" + this.file + "}";
    }

    public boolean equals(Object obj) {
        Location location2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FileOutputOptions.FileOutputOptionsInternal)) {
            return false;
        }
        FileOutputOptions.FileOutputOptionsInternal fileOutputOptionsInternal = (FileOutputOptions.FileOutputOptionsInternal) obj;
        if (this.fileSizeLimit != fileOutputOptionsInternal.getFileSizeLimit() || this.durationLimitMillis != fileOutputOptionsInternal.getDurationLimitMillis() || ((location2 = this.location) != null ? !location2.equals(fileOutputOptionsInternal.getLocation()) : fileOutputOptionsInternal.getLocation() != null) || !this.file.equals(fileOutputOptionsInternal.getFile())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.fileSizeLimit;
        long j2 = this.durationLimitMillis;
        int i = (((((int) (j ^ (j >>> 32))) ^ 1000003) * 1000003) ^ ((int) ((j2 >>> 32) ^ j2))) * 1000003;
        Location location2 = this.location;
        return ((i ^ (location2 == null ? 0 : location2.hashCode())) * 1000003) ^ this.file.hashCode();
    }

    static final class Builder extends FileOutputOptions.FileOutputOptionsInternal.Builder {
        private Long durationLimitMillis;
        private File file;
        private Long fileSizeLimit;
        private Location location;

        Builder() {
        }

        /* access modifiers changed from: package-private */
        public FileOutputOptions.FileOutputOptionsInternal.Builder setFileSizeLimit(long j) {
            this.fileSizeLimit = Long.valueOf(j);
            return this;
        }

        /* access modifiers changed from: package-private */
        public FileOutputOptions.FileOutputOptionsInternal.Builder setDurationLimitMillis(long j) {
            this.durationLimitMillis = Long.valueOf(j);
            return this;
        }

        /* access modifiers changed from: package-private */
        public FileOutputOptions.FileOutputOptionsInternal.Builder setLocation(Location location2) {
            this.location = location2;
            return this;
        }

        /* access modifiers changed from: package-private */
        public FileOutputOptions.FileOutputOptionsInternal.Builder setFile(File file2) {
            if (file2 != null) {
                this.file = file2;
                return this;
            }
            throw new NullPointerException("Null file");
        }

        /* access modifiers changed from: package-private */
        public FileOutputOptions.FileOutputOptionsInternal build() {
            String str = this.fileSizeLimit == null ? " fileSizeLimit" : "";
            if (this.durationLimitMillis == null) {
                str = str + " durationLimitMillis";
            }
            if (this.file == null) {
                str = str + " file";
            }
            if (str.isEmpty()) {
                return new AutoValue_FileOutputOptions_FileOutputOptionsInternal(this.fileSizeLimit.longValue(), this.durationLimitMillis.longValue(), this.location, this.file);
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
