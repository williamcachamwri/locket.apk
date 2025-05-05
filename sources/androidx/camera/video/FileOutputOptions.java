package androidx.camera.video;

import android.location.Location;
import androidx.camera.video.AutoValue_FileOutputOptions_FileOutputOptionsInternal;
import androidx.camera.video.OutputOptions;
import androidx.core.util.Preconditions;
import java.io.File;

public final class FileOutputOptions extends OutputOptions {
    private final FileOutputOptionsInternal mFileOutputOptionsInternal;

    FileOutputOptions(FileOutputOptionsInternal fileOutputOptionsInternal) {
        super(fileOutputOptionsInternal);
        this.mFileOutputOptionsInternal = fileOutputOptionsInternal;
    }

    public File getFile() {
        return this.mFileOutputOptionsInternal.getFile();
    }

    public String toString() {
        return this.mFileOutputOptionsInternal.toString().replaceFirst("FileOutputOptionsInternal", "FileOutputOptions");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FileOutputOptions)) {
            return false;
        }
        return this.mFileOutputOptionsInternal.equals(((FileOutputOptions) obj).mFileOutputOptionsInternal);
    }

    public int hashCode() {
        return this.mFileOutputOptionsInternal.hashCode();
    }

    public static final class Builder extends OutputOptions.Builder<FileOutputOptions, Builder> {
        private final FileOutputOptionsInternal.Builder mInternalBuilder;

        public /* bridge */ /* synthetic */ Object setDurationLimitMillis(long j) {
            return super.setDurationLimitMillis(j);
        }

        public /* bridge */ /* synthetic */ Object setFileSizeLimit(long j) {
            return super.setFileSizeLimit(j);
        }

        public /* bridge */ /* synthetic */ Object setLocation(Location location) {
            return super.setLocation(location);
        }

        public Builder(File file) {
            super(new AutoValue_FileOutputOptions_FileOutputOptionsInternal.Builder());
            Preconditions.checkNotNull(file, "File can't be null.");
            FileOutputOptionsInternal.Builder builder = (FileOutputOptionsInternal.Builder) this.mRootInternalBuilder;
            this.mInternalBuilder = builder;
            builder.setFile(file);
        }

        public FileOutputOptions build() {
            return new FileOutputOptions(this.mInternalBuilder.build());
        }
    }

    static abstract class FileOutputOptionsInternal extends OutputOptions.OutputOptionsInternal {
        /* access modifiers changed from: package-private */
        public abstract File getFile();

        FileOutputOptionsInternal() {
        }

        static abstract class Builder extends OutputOptions.OutputOptionsInternal.Builder<Builder> {
            /* access modifiers changed from: package-private */
            public abstract FileOutputOptionsInternal build();

            /* access modifiers changed from: package-private */
            public abstract Builder setFile(File file);

            Builder() {
            }
        }
    }
}
