package okio;

import java.io.IOException;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import okio.Path;
import okio.internal.ResourceFileSystem;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000f\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 42\u00020\u0001:\u00014B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H&J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H&J\u0018\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0006J\u0018\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\bJ\u000e\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0006J\u001a\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\bH&J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H&J\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u0006J\u001a\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH&J\u000e\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0006J\u001a\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\u0018\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u0006J\u0016\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u001a2\u0006\u0010\u0011\u001a\u00020\u0006H&J\u0018\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u001a2\u0006\u0010\u0011\u001a\u00020\u0006H&J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u001d2\u0006\u0010\u0011\u001a\u00020\u0006J \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u001d2\u0006\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\bH\u0016J\u000e\u0010\u001f\u001a\u00020 2\u0006\u0010\u000e\u001a\u00020\u0006J\u0012\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010\u000e\u001a\u00020\u0006H&J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\u0005\u001a\u00020\u0006H&J\u000e\u0010$\u001a\u00020#2\u0006\u0010\u0005\u001a\u00020\u0006J$\u0010$\u001a\u00020#2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\b2\b\b\u0002\u0010\u0007\u001a\u00020\bH&J:\u0010%\u001a\u0002H&\"\u0004\b\u0000\u0010&2\u0006\u0010\u0005\u001a\u00020\u00062\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u0002H&0(¢\u0006\u0002\b*H\bø\u0001\u0000¢\u0006\u0004\b+\u0010,J\u000e\u0010-\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001a\u0010-\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\bH&J\u0010\u0010\u000b\u001a\u00020.2\u0006\u0010\u0005\u001a\u00020\u0006H&JD\u0010/\u001a\u0002H&\"\u0004\b\u0000\u0010&2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\b2\u0017\u00100\u001a\u0013\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u0002H&0(¢\u0006\u0002\b*H\bø\u0001\u0000¢\u0006\u0004\b2\u00103\u0002\u0007\n\u0005\b20\u0001¨\u00065"}, d2 = {"Lokio/FileSystem;", "", "()V", "appendingSink", "Lokio/Sink;", "file", "Lokio/Path;", "mustExist", "", "atomicMove", "", "source", "target", "canonicalize", "path", "copy", "createDirectories", "dir", "mustCreate", "createDirectory", "createSymlink", "delete", "deleteRecursively", "fileOrDirectory", "exists", "list", "", "listOrNull", "listRecursively", "Lkotlin/sequences/Sequence;", "followSymlinks", "metadata", "Lokio/FileMetadata;", "metadataOrNull", "openReadOnly", "Lokio/FileHandle;", "openReadWrite", "read", "T", "readerAction", "Lkotlin/Function1;", "Lokio/BufferedSource;", "Lkotlin/ExtensionFunctionType;", "-read", "(Lokio/Path;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "sink", "Lokio/Source;", "write", "writerAction", "Lokio/BufferedSink;", "-write", "(Lokio/Path;ZLkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Companion", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileSystem.kt */
public abstract class FileSystem {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final FileSystem RESOURCES;
    public static final FileSystem SYSTEM;
    public static final Path SYSTEM_TEMPORARY_DIRECTORY;

    @JvmStatic
    public static final FileSystem get(java.nio.file.FileSystem fileSystem) {
        return Companion.get(fileSystem);
    }

    public abstract Sink appendingSink(Path path, boolean z) throws IOException;

    public abstract void atomicMove(Path path, Path path2) throws IOException;

    public abstract Path canonicalize(Path path) throws IOException;

    public abstract void createDirectory(Path path, boolean z) throws IOException;

    public abstract void createSymlink(Path path, Path path2) throws IOException;

    public abstract void delete(Path path, boolean z) throws IOException;

    public abstract List<Path> list(Path path) throws IOException;

    public abstract List<Path> listOrNull(Path path);

    public abstract FileMetadata metadataOrNull(Path path) throws IOException;

    public abstract FileHandle openReadOnly(Path path) throws IOException;

    public abstract FileHandle openReadWrite(Path path, boolean z, boolean z2) throws IOException;

    public abstract Sink sink(Path path, boolean z) throws IOException;

    public abstract Source source(Path path) throws IOException;

    public final FileMetadata metadata(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        return okio.internal.FileSystem.commonMetadata(this, path);
    }

    public final boolean exists(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        return okio.internal.FileSystem.commonExists(this, path);
    }

    public static /* synthetic */ Sequence listRecursively$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            return fileSystem.listRecursively(path, z);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: listRecursively");
    }

    public Sequence<Path> listRecursively(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "dir");
        return okio.internal.FileSystem.commonListRecursively(this, path, z);
    }

    public final Sequence<Path> listRecursively(Path path) {
        Intrinsics.checkNotNullParameter(path, "dir");
        return listRecursively(path, false);
    }

    public static /* synthetic */ FileHandle openReadWrite$default(FileSystem fileSystem, Path path, boolean z, boolean z2, int i, Object obj) throws IOException {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            if ((i & 4) != 0) {
                z2 = false;
            }
            return fileSystem.openReadWrite(path, z, z2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: openReadWrite");
    }

    public final FileHandle openReadWrite(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "file");
        return openReadWrite(path, false, false);
    }

    public static /* synthetic */ Sink sink$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            return fileSystem.sink(path, z);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sink");
    }

    public final Sink sink(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "file");
        return sink(path, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x004c  */
    /* renamed from: -write$default  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ java.lang.Object m2061write$default(okio.FileSystem r1, okio.Path r2, boolean r3, kotlin.jvm.functions.Function1 r4, int r5, java.lang.Object r6) throws java.io.IOException {
        /*
            if (r6 != 0) goto L_0x004d
            r5 = r5 & 2
            if (r5 == 0) goto L_0x0007
            r3 = 0
        L_0x0007:
            java.lang.String r5 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r5)
            java.lang.String r5 = "writerAction"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r5)
            okio.Sink r1 = r1.sink(r2, r3)
            okio.BufferedSink r1 = okio.Okio.buffer((okio.Sink) r1)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2 = 0
            r3 = 1
            r5 = r1
            okio.BufferedSink r5 = (okio.BufferedSink) r5     // Catch:{ all -> 0x0032 }
            java.lang.Object r4 = r4.invoke(r5)     // Catch:{ all -> 0x0032 }
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch:{ all -> 0x002d }
            goto L_0x002e
        L_0x002d:
            r2 = move-exception
        L_0x002e:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            goto L_0x0046
        L_0x0032:
            r4 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r3)
            if (r1 == 0) goto L_0x0040
            r1.close()     // Catch:{ all -> 0x003c }
            goto L_0x0040
        L_0x003c:
            r1 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r4, r1)
        L_0x0040:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r3)
            r0 = r4
            r4 = r2
            r2 = r0
        L_0x0046:
            if (r2 != 0) goto L_0x004c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            return r4
        L_0x004c:
            throw r2
        L_0x004d:
            java.lang.UnsupportedOperationException r1 = new java.lang.UnsupportedOperationException
            java.lang.String r2 = "Super calls with default arguments not supported in this target, function: write"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.FileSystem.m2061write$default(okio.FileSystem, okio.Path, boolean, kotlin.jvm.functions.Function1, int, java.lang.Object):java.lang.Object");
    }

    public static /* synthetic */ Sink appendingSink$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            return fileSystem.appendingSink(path, z);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: appendingSink");
    }

    public final Sink appendingSink(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "file");
        return appendingSink(path, false);
    }

    public static /* synthetic */ void createDirectory$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            fileSystem.createDirectory(path, z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createDirectory");
    }

    public final void createDirectory(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "dir");
        createDirectory(path, false);
    }

    public static /* synthetic */ void createDirectories$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            fileSystem.createDirectories(path, z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createDirectories");
    }

    public final void createDirectories(Path path, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path, "dir");
        okio.internal.FileSystem.commonCreateDirectories(this, path, z);
    }

    public final void createDirectories(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "dir");
        createDirectories(path, false);
    }

    public void copy(Path path, Path path2) throws IOException {
        Intrinsics.checkNotNullParameter(path, "source");
        Intrinsics.checkNotNullParameter(path2, "target");
        okio.internal.FileSystem.commonCopy(this, path, path2);
    }

    public static /* synthetic */ void delete$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            fileSystem.delete(path, z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: delete");
    }

    public final void delete(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        delete(path, false);
    }

    public static /* synthetic */ void deleteRecursively$default(FileSystem fileSystem, Path path, boolean z, int i, Object obj) throws IOException {
        if (obj == null) {
            if ((i & 2) != 0) {
                z = false;
            }
            fileSystem.deleteRecursively(path, z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteRecursively");
    }

    public void deleteRecursively(Path path, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(path, "fileOrDirectory");
        okio.internal.FileSystem.commonDeleteRecursively(this, path, z);
    }

    public final void deleteRecursively(Path path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "fileOrDirectory");
        deleteRecursively(path, false);
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\b\u001a\u00020\u0004*\u00020\tH\u0007¢\u0006\u0002\b\nR\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lokio/FileSystem$Companion;", "", "()V", "RESOURCES", "Lokio/FileSystem;", "SYSTEM", "SYSTEM_TEMPORARY_DIRECTORY", "Lokio/Path;", "asOkioFileSystem", "Ljava/nio/file/FileSystem;", "get", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FileSystem.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final FileSystem get(java.nio.file.FileSystem fileSystem) {
            Intrinsics.checkNotNullParameter(fileSystem, "<this>");
            return new NioFileSystemWrappingFileSystem(fileSystem);
        }
    }

    static {
        JvmSystemFileSystem jvmSystemFileSystem;
        try {
            Class.forName("java.nio.file.Files");
            jvmSystemFileSystem = new NioSystemFileSystem();
        } catch (ClassNotFoundException unused) {
            jvmSystemFileSystem = new JvmSystemFileSystem();
        }
        SYSTEM = jvmSystemFileSystem;
        Path.Companion companion = Path.Companion;
        String property = System.getProperty("java.io.tmpdir");
        Intrinsics.checkNotNullExpressionValue(property, "getProperty(...)");
        SYSTEM_TEMPORARY_DIRECTORY = Path.Companion.get$default(companion, property, false, 1, (Object) null);
        ClassLoader classLoader = ResourceFileSystem.class.getClassLoader();
        Intrinsics.checkNotNullExpressionValue(classLoader, "getClassLoader(...)");
        RESOURCES = new ResourceFileSystem(classLoader, false, (FileSystem) null, 4, (DefaultConstructorMarker) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    /* renamed from: -read  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> T m2062read(okio.Path r5, kotlin.jvm.functions.Function1<? super okio.BufferedSource, ? extends T> r6) throws java.io.IOException {
        /*
            r4 = this;
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "readerAction"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            okio.Source r5 = r4.source(r5)
            okio.BufferedSource r5 = okio.Okio.buffer((okio.Source) r5)
            java.io.Closeable r5 = (java.io.Closeable) r5
            r0 = 1
            r1 = 0
            r2 = r5
            okio.BufferedSource r2 = (okio.BufferedSource) r2     // Catch:{ all -> 0x002b }
            java.lang.Object r6 = r6.invoke(r2)     // Catch:{ all -> 0x002b }
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            if (r5 == 0) goto L_0x0027
            r5.close()     // Catch:{ all -> 0x0026 }
            goto L_0x0027
        L_0x0026:
            r1 = move-exception
        L_0x0027:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            goto L_0x003f
        L_0x002b:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r0)
            if (r5 == 0) goto L_0x0039
            r5.close()     // Catch:{ all -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r5 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r6, r5)
        L_0x0039:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r0)
            r3 = r1
            r1 = r6
            r6 = r3
        L_0x003f:
            if (r1 != 0) goto L_0x0045
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            return r6
        L_0x0045:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.FileSystem.m2062read(okio.Path, kotlin.jvm.functions.Function1):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0045  */
    /* renamed from: -write  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <T> T m2063write(okio.Path r4, boolean r5, kotlin.jvm.functions.Function1<? super okio.BufferedSink, ? extends T> r6) throws java.io.IOException {
        /*
            r3 = this;
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "writerAction"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            okio.Sink r4 = r3.sink(r4, r5)
            okio.BufferedSink r4 = okio.Okio.buffer((okio.Sink) r4)
            java.io.Closeable r4 = (java.io.Closeable) r4
            r5 = 1
            r0 = 0
            r1 = r4
            okio.BufferedSink r1 = (okio.BufferedSink) r1     // Catch:{ all -> 0x002b }
            java.lang.Object r6 = r6.invoke(r1)     // Catch:{ all -> 0x002b }
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            if (r4 == 0) goto L_0x0027
            r4.close()     // Catch:{ all -> 0x0026 }
            goto L_0x0027
        L_0x0026:
            r0 = move-exception
        L_0x0027:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            goto L_0x003f
        L_0x002b:
            r6 = move-exception
            kotlin.jvm.internal.InlineMarker.finallyStart(r5)
            if (r4 == 0) goto L_0x0039
            r4.close()     // Catch:{ all -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r4 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r6, r4)
        L_0x0039:
            kotlin.jvm.internal.InlineMarker.finallyEnd(r5)
            r2 = r0
            r0 = r6
            r6 = r2
        L_0x003f:
            if (r0 != 0) goto L_0x0045
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            return r6
        L_0x0045:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.FileSystem.m2063write(okio.Path, boolean, kotlin.jvm.functions.Function1):java.lang.Object");
    }
}
