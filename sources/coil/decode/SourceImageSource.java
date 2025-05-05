package coil.decode;

import coil.decode.ImageSource;
import coil.util.Utils;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;
import okio.FileSystem;
import okio.Okio;
import okio.Path;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0016J\b\u0010\u0017\u001a\u00020\u000bH\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0019\u001a\u00020\u0003H\u0016R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcoil/decode/SourceImageSource;", "Lcoil/decode/ImageSource;", "source", "Lokio/BufferedSource;", "cacheDirectoryFactory", "Lkotlin/Function0;", "Ljava/io/File;", "metadata", "Lcoil/decode/ImageSource$Metadata;", "(Lokio/BufferedSource;Lkotlin/jvm/functions/Function0;Lcoil/decode/ImageSource$Metadata;)V", "file", "Lokio/Path;", "fileSystem", "Lokio/FileSystem;", "getFileSystem", "()Lokio/FileSystem;", "isClosed", "", "getMetadata", "()Lcoil/decode/ImageSource$Metadata;", "assertNotClosed", "", "close", "createTempFile", "fileOrNull", "sourceOrNull", "coil-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageSource.kt */
public final class SourceImageSource extends ImageSource {
    private Function0<? extends File> cacheDirectoryFactory;
    private Path file;
    private boolean isClosed;
    private final ImageSource.Metadata metadata;
    private BufferedSource source;

    public ImageSource.Metadata getMetadata() {
        return this.metadata;
    }

    public SourceImageSource(BufferedSource bufferedSource, Function0<? extends File> function0, ImageSource.Metadata metadata2) {
        super((DefaultConstructorMarker) null);
        this.metadata = metadata2;
        this.source = bufferedSource;
        this.cacheDirectoryFactory = function0;
    }

    public FileSystem getFileSystem() {
        return FileSystem.SYSTEM;
    }

    public synchronized BufferedSource source() {
        assertNotClosed();
        BufferedSource bufferedSource = this.source;
        if (bufferedSource != null) {
            return bufferedSource;
        }
        FileSystem fileSystem = getFileSystem();
        Path path = this.file;
        Intrinsics.checkNotNull(path);
        BufferedSource buffer = Okio.buffer(fileSystem.source(path));
        this.source = buffer;
        return buffer;
    }

    public BufferedSource sourceOrNull() {
        return source();
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0049 A[Catch:{ all -> 0x003a, all -> 0x0041 }] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0054 A[SYNTHETIC, Splitter:B:28:0x0054] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized okio.Path file() {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.assertNotClosed()     // Catch:{ all -> 0x0055 }
            okio.Path r0 = r5.file     // Catch:{ all -> 0x0055 }
            if (r0 == 0) goto L_0x000a
            monitor-exit(r5)
            return r0
        L_0x000a:
            okio.Path r0 = r5.createTempFile()     // Catch:{ all -> 0x0055 }
            okio.FileSystem r1 = r5.getFileSystem()     // Catch:{ all -> 0x0055 }
            r2 = 0
            okio.Sink r1 = r1.sink(r0, r2)     // Catch:{ all -> 0x0055 }
            okio.BufferedSink r1 = okio.Okio.buffer((okio.Sink) r1)     // Catch:{ all -> 0x0055 }
            java.io.Closeable r1 = (java.io.Closeable) r1     // Catch:{ all -> 0x0055 }
            r2 = 0
            r3 = r1
            okio.BufferedSink r3 = (okio.BufferedSink) r3     // Catch:{ all -> 0x003a }
            okio.BufferedSource r4 = r5.source     // Catch:{ all -> 0x003a }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x003a }
            okio.Source r4 = (okio.Source) r4     // Catch:{ all -> 0x003a }
            long r3 = r3.writeAll(r4)     // Catch:{ all -> 0x003a }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ all -> 0x003a }
            if (r1 == 0) goto L_0x0038
            r1.close()     // Catch:{ all -> 0x0036 }
            goto L_0x0038
        L_0x0036:
            r1 = move-exception
            goto L_0x0047
        L_0x0038:
            r1 = r2
            goto L_0x0047
        L_0x003a:
            r3 = move-exception
            if (r1 == 0) goto L_0x0045
            r1.close()     // Catch:{ all -> 0x0041 }
            goto L_0x0045
        L_0x0041:
            r1 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r3, r1)     // Catch:{ all -> 0x0055 }
        L_0x0045:
            r1 = r3
            r3 = r2
        L_0x0047:
            if (r1 != 0) goto L_0x0054
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch:{ all -> 0x0055 }
            r5.source = r2     // Catch:{ all -> 0x0055 }
            r5.file = r0     // Catch:{ all -> 0x0055 }
            r5.cacheDirectoryFactory = r2     // Catch:{ all -> 0x0055 }
            monitor-exit(r5)
            return r0
        L_0x0054:
            throw r1     // Catch:{ all -> 0x0055 }
        L_0x0055:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.decode.SourceImageSource.file():okio.Path");
    }

    public synchronized Path fileOrNull() {
        assertNotClosed();
        return this.file;
    }

    public synchronized void close() {
        this.isClosed = true;
        BufferedSource bufferedSource = this.source;
        if (bufferedSource != null) {
            Utils.closeQuietly(bufferedSource);
        }
        Path path = this.file;
        if (path != null) {
            getFileSystem().delete(path);
        }
    }

    private final Path createTempFile() {
        Function0<? extends File> function0 = this.cacheDirectoryFactory;
        Intrinsics.checkNotNull(function0);
        File file2 = (File) function0.invoke();
        if (file2.isDirectory()) {
            return Path.Companion.get$default(Path.Companion, File.createTempFile("tmp", (String) null, file2), false, 1, (Object) null);
        }
        throw new IllegalStateException("cacheDirectory must be a directory.".toString());
    }

    private final void assertNotClosed() {
        if (!(!this.isClosed)) {
            throw new IllegalStateException("closed".toString());
        }
    }
}
