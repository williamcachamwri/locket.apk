package androidx.camera.video;

import android.location.Location;
import android.os.ParcelFileDescriptor;
import androidx.camera.video.AutoValue_FileDescriptorOutputOptions_FileDescriptorOutputOptionsInternal;
import androidx.camera.video.OutputOptions;
import androidx.core.util.Preconditions;

public final class FileDescriptorOutputOptions extends OutputOptions {
    private final FileDescriptorOutputOptionsInternal mFileDescriptorOutputOptionsInternal;

    FileDescriptorOutputOptions(FileDescriptorOutputOptionsInternal fileDescriptorOutputOptionsInternal) {
        super(fileDescriptorOutputOptionsInternal);
        this.mFileDescriptorOutputOptionsInternal = fileDescriptorOutputOptionsInternal;
    }

    public ParcelFileDescriptor getParcelFileDescriptor() {
        return this.mFileDescriptorOutputOptionsInternal.getParcelFileDescriptor();
    }

    public String toString() {
        return this.mFileDescriptorOutputOptionsInternal.toString().replaceFirst("FileDescriptorOutputOptionsInternal", "FileDescriptorOutputOptions");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FileDescriptorOutputOptions)) {
            return false;
        }
        return this.mFileDescriptorOutputOptionsInternal.equals(((FileDescriptorOutputOptions) obj).mFileDescriptorOutputOptionsInternal);
    }

    public int hashCode() {
        return this.mFileDescriptorOutputOptionsInternal.hashCode();
    }

    public static final class Builder extends OutputOptions.Builder<FileDescriptorOutputOptions, Builder> {
        private final FileDescriptorOutputOptionsInternal.Builder mInternalBuilder;

        public /* bridge */ /* synthetic */ Object setDurationLimitMillis(long j) {
            return super.setDurationLimitMillis(j);
        }

        public /* bridge */ /* synthetic */ Object setFileSizeLimit(long j) {
            return super.setFileSizeLimit(j);
        }

        public /* bridge */ /* synthetic */ Object setLocation(Location location) {
            return super.setLocation(location);
        }

        public Builder(ParcelFileDescriptor parcelFileDescriptor) {
            super(new AutoValue_FileDescriptorOutputOptions_FileDescriptorOutputOptionsInternal.Builder());
            Preconditions.checkNotNull(parcelFileDescriptor, "File descriptor can't be null.");
            FileDescriptorOutputOptionsInternal.Builder builder = (FileDescriptorOutputOptionsInternal.Builder) this.mRootInternalBuilder;
            this.mInternalBuilder = builder;
            builder.setParcelFileDescriptor(parcelFileDescriptor);
        }

        public FileDescriptorOutputOptions build() {
            return new FileDescriptorOutputOptions(this.mInternalBuilder.build());
        }
    }

    static abstract class FileDescriptorOutputOptionsInternal extends OutputOptions.OutputOptionsInternal {
        /* access modifiers changed from: package-private */
        public abstract ParcelFileDescriptor getParcelFileDescriptor();

        FileDescriptorOutputOptionsInternal() {
        }

        static abstract class Builder extends OutputOptions.OutputOptionsInternal.Builder<Builder> {
            /* access modifiers changed from: package-private */
            public abstract FileDescriptorOutputOptionsInternal build();

            /* access modifiers changed from: package-private */
            public abstract Builder setParcelFileDescriptor(ParcelFileDescriptor parcelFileDescriptor);

            Builder() {
            }
        }
    }
}
