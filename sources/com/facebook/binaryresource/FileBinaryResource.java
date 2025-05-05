package com.facebook.binaryresource;

import com.facebook.common.internal.Files;
import io.sentry.instrumentation.file.SentryFileInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/facebook/binaryresource/FileBinaryResource;", "Lcom/facebook/binaryresource/BinaryResource;", "file", "Ljava/io/File;", "(Ljava/io/File;)V", "getFile", "()Ljava/io/File;", "equals", "", "other", "", "hashCode", "", "openStream", "Ljava/io/InputStream;", "read", "", "size", "", "Companion", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FileBinaryResource.kt */
public final class FileBinaryResource implements BinaryResource {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final File file;

    public /* synthetic */ FileBinaryResource(File file2, DefaultConstructorMarker defaultConstructorMarker) {
        this(file2);
    }

    @JvmStatic
    public static final FileBinaryResource create(File file2) {
        return Companion.create(file2);
    }

    @JvmStatic
    public static final FileBinaryResource createOrNull(File file2) {
        return Companion.createOrNull(file2);
    }

    private FileBinaryResource(File file2) {
        this.file = file2;
    }

    public final File getFile() {
        return this.file;
    }

    public InputStream openStream() throws IOException {
        File file2 = this.file;
        return SentryFileInputStream.Factory.create(new FileInputStream(file2), file2);
    }

    public long size() {
        return this.file.length();
    }

    public byte[] read() throws IOException {
        byte[] byteArray = Files.toByteArray(this.file);
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(file)");
        return byteArray;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof FileBinaryResource)) {
            return false;
        }
        return Intrinsics.areEqual((Object) this.file, (Object) ((FileBinaryResource) obj).file);
    }

    public int hashCode() {
        return this.file.hashCode();
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007¨\u0006\b"}, d2 = {"Lcom/facebook/binaryresource/FileBinaryResource$Companion;", "", "()V", "create", "Lcom/facebook/binaryresource/FileBinaryResource;", "file", "Ljava/io/File;", "createOrNull", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: FileBinaryResource.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FileBinaryResource createOrNull(File file) {
            if (file != null) {
                return new FileBinaryResource(file, (DefaultConstructorMarker) null);
            }
            return null;
        }

        @JvmStatic
        public final FileBinaryResource create(File file) {
            Intrinsics.checkNotNullParameter(file, "file");
            return new FileBinaryResource(file, (DefaultConstructorMarker) null);
        }
    }
}
