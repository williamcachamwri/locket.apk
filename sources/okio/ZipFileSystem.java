package okio;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;
import okio.internal.ZipEntry;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 '2\u00020\u0001:\u0001'B5\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H\u0016J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H\u0002J\u0018\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J\u0018\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H\u0016J\u0018\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u001d2\u0006\u0010\u0018\u001a\u00020\u0003H\u0016J \u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001d2\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u000fH\u0002J\u0018\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u001d2\u0006\u0010\u0018\u001a\u00020\u0003H\u0016J\u0012\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u0015\u001a\u00020\u0003H\u0016J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\r\u001a\u00020\u0003H\u0016J \u0010$\u001a\u00020#2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010%\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J\u0010\u0010\u0012\u001a\u00020&2\u0006\u0010\r\u001a\u00020\u0003H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lokio/ZipFileSystem;", "Lokio/FileSystem;", "zipPath", "Lokio/Path;", "fileSystem", "entries", "", "Lokio/internal/ZipEntry;", "comment", "", "(Lokio/Path;Lokio/FileSystem;Ljava/util/Map;Ljava/lang/String;)V", "appendingSink", "Lokio/Sink;", "file", "mustExist", "", "atomicMove", "", "source", "target", "canonicalize", "path", "canonicalizeInternal", "createDirectory", "dir", "mustCreate", "createSymlink", "delete", "list", "", "throwOnFailure", "listOrNull", "metadataOrNull", "Lokio/FileMetadata;", "openReadOnly", "Lokio/FileHandle;", "openReadWrite", "sink", "Lokio/Source;", "Companion", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ZipFileSystem.kt */
public final class ZipFileSystem extends FileSystem {
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Path ROOT = Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null);
    private final String comment;
    private final Map<Path, ZipEntry> entries;
    private final FileSystem fileSystem;
    private final Path zipPath;

    public ZipFileSystem(Path path, FileSystem fileSystem2, Map<Path, ZipEntry> map, String str) {
        Intrinsics.checkNotNullParameter(path, "zipPath");
        Intrinsics.checkNotNullParameter(fileSystem2, "fileSystem");
        Intrinsics.checkNotNullParameter(map, RemoteConfigConstants.ResponseFieldKey.ENTRIES);
        this.zipPath = path;
        this.fileSystem = fileSystem2;
        this.entries = map;
        this.comment = str;
    }

    public Path canonicalize(Path path) {
        Intrinsics.checkNotNullParameter(path, "path");
        Path canonicalizeInternal = canonicalizeInternal(path);
        if (this.entries.containsKey(canonicalizeInternal)) {
            return canonicalizeInternal;
        }
        throw new FileNotFoundException(String.valueOf(path));
    }

    private final Path canonicalizeInternal(Path path) {
        return ROOT.resolve(path, true);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x0087 A[Catch:{ all -> 0x0078, all -> 0x007f, all -> 0x0093 }] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0092 A[SYNTHETIC, Splitter:B:35:0x0092] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okio.FileMetadata metadataOrNull(okio.Path r14) {
        /*
            r13 = this;
            java.lang.String r0 = "path"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            okio.Path r14 = r13.canonicalizeInternal(r14)
            java.util.Map<okio.Path, okio.internal.ZipEntry> r0 = r13.entries
            java.lang.Object r14 = r0.get(r14)
            okio.internal.ZipEntry r14 = (okio.internal.ZipEntry) r14
            r0 = 0
            if (r14 != 0) goto L_0x0015
            return r0
        L_0x0015:
            okio.FileMetadata r12 = new okio.FileMetadata
            boolean r1 = r14.isDirectory()
            r2 = r1 ^ 1
            boolean r3 = r14.isDirectory()
            r4 = 0
            boolean r1 = r14.isDirectory()
            if (r1 == 0) goto L_0x002a
            r5 = r0
            goto L_0x0033
        L_0x002a:
            long r5 = r14.getSize()
            java.lang.Long r1 = java.lang.Long.valueOf(r5)
            r5 = r1
        L_0x0033:
            r6 = 0
            java.lang.Long r7 = r14.getLastModifiedAtMillis()
            r8 = 0
            r9 = 0
            r10 = 128(0x80, float:1.794E-43)
            r11 = 0
            r1 = r12
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            long r1 = r14.getOffset()
            r3 = -1
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L_0x004c
            return r12
        L_0x004c:
            okio.FileSystem r1 = r13.fileSystem
            okio.Path r2 = r13.zipPath
            okio.FileHandle r1 = r1.openReadOnly(r2)
            java.io.Closeable r1 = (java.io.Closeable) r1
            r2 = r1
            okio.FileHandle r2 = (okio.FileHandle) r2     // Catch:{ all -> 0x0093 }
            long r3 = r14.getOffset()     // Catch:{ all -> 0x0093 }
            okio.Source r14 = r2.source(r3)     // Catch:{ all -> 0x0093 }
            okio.BufferedSource r14 = okio.Okio.buffer((okio.Source) r14)     // Catch:{ all -> 0x0093 }
            java.io.Closeable r14 = (java.io.Closeable) r14     // Catch:{ all -> 0x0093 }
            r2 = r14
            okio.BufferedSource r2 = (okio.BufferedSource) r2     // Catch:{ all -> 0x0078 }
            okio.FileMetadata r2 = okio.internal.ZipFilesKt.readLocalHeader(r2, r12)     // Catch:{ all -> 0x0078 }
            if (r14 == 0) goto L_0x0076
            r14.close()     // Catch:{ all -> 0x0074 }
            goto L_0x0076
        L_0x0074:
            r14 = move-exception
            goto L_0x0085
        L_0x0076:
            r14 = r0
            goto L_0x0085
        L_0x0078:
            r2 = move-exception
            if (r14 == 0) goto L_0x0083
            r14.close()     // Catch:{ all -> 0x007f }
            goto L_0x0083
        L_0x007f:
            r14 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r2, r14)     // Catch:{ all -> 0x0093 }
        L_0x0083:
            r14 = r2
            r2 = r0
        L_0x0085:
            if (r14 != 0) goto L_0x0092
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ all -> 0x0093 }
            if (r1 == 0) goto L_0x00a0
            r1.close()     // Catch:{ all -> 0x0090 }
            goto L_0x00a0
        L_0x0090:
            r0 = move-exception
            goto L_0x00a0
        L_0x0092:
            throw r14     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r14 = move-exception
            if (r1 == 0) goto L_0x009e
            r1.close()     // Catch:{ all -> 0x009a }
            goto L_0x009e
        L_0x009a:
            r1 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r14, r1)
        L_0x009e:
            r2 = r0
            r0 = r14
        L_0x00a0:
            if (r0 != 0) goto L_0x00a6
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            return r2
        L_0x00a6:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ZipFileSystem.metadataOrNull(okio.Path):okio.FileMetadata");
    }

    public FileHandle openReadOnly(Path path) {
        Intrinsics.checkNotNullParameter(path, "file");
        throw new UnsupportedOperationException("not implemented yet!");
    }

    public FileHandle openReadWrite(Path path, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(path, "file");
        throw new IOException("zip entries are not writable");
    }

    public List<Path> list(Path path) {
        Intrinsics.checkNotNullParameter(path, "dir");
        List<Path> list = list(path, true);
        Intrinsics.checkNotNull(list);
        return list;
    }

    public List<Path> listOrNull(Path path) {
        Intrinsics.checkNotNullParameter(path, "dir");
        return list(path, false);
    }

    private final List<Path> list(Path path, boolean z) {
        ZipEntry zipEntry = this.entries.get(canonicalizeInternal(path));
        if (zipEntry != null) {
            return CollectionsKt.toList(zipEntry.getChildren());
        }
        if (!z) {
            return null;
        }
        throw new IOException("not a directory: " + path);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0087  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okio.Source source(okio.Path r8) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            okio.Path r0 = r7.canonicalizeInternal(r8)
            java.util.Map<okio.Path, okio.internal.ZipEntry> r1 = r7.entries
            java.lang.Object r0 = r1.get(r0)
            okio.internal.ZipEntry r0 = (okio.internal.ZipEntry) r0
            if (r0 == 0) goto L_0x0088
            okio.FileSystem r8 = r7.fileSystem
            okio.Path r1 = r7.zipPath
            okio.FileHandle r8 = r8.openReadOnly(r1)
            java.io.Closeable r8 = (java.io.Closeable) r8
            r1 = 0
            r2 = r8
            okio.FileHandle r2 = (okio.FileHandle) r2     // Catch:{ all -> 0x0035 }
            long r3 = r0.getOffset()     // Catch:{ all -> 0x0035 }
            okio.Source r2 = r2.source(r3)     // Catch:{ all -> 0x0035 }
            okio.BufferedSource r2 = okio.Okio.buffer((okio.Source) r2)     // Catch:{ all -> 0x0035 }
            if (r8 == 0) goto L_0x0043
            r8.close()     // Catch:{ all -> 0x0033 }
            goto L_0x0043
        L_0x0033:
            r1 = move-exception
            goto L_0x0043
        L_0x0035:
            r2 = move-exception
            if (r8 == 0) goto L_0x0040
            r8.close()     // Catch:{ all -> 0x003c }
            goto L_0x0040
        L_0x003c:
            r8 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r2, r8)
        L_0x0040:
            r6 = r2
            r2 = r1
            r1 = r6
        L_0x0043:
            if (r1 != 0) goto L_0x0087
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            okio.internal.ZipFilesKt.skipLocalHeader(r2)
            int r8 = r0.getCompressionMethod()
            r1 = 1
            if (r8 != 0) goto L_0x0060
            okio.internal.FixedLengthSource r8 = new okio.internal.FixedLengthSource
            okio.Source r2 = (okio.Source) r2
            long r3 = r0.getSize()
            r8.<init>(r2, r3, r1)
            okio.Source r8 = (okio.Source) r8
            goto L_0x0086
        L_0x0060:
            okio.InflaterSource r8 = new okio.InflaterSource
            okio.internal.FixedLengthSource r3 = new okio.internal.FixedLengthSource
            okio.Source r2 = (okio.Source) r2
            long r4 = r0.getCompressedSize()
            r3.<init>(r2, r4, r1)
            okio.Source r3 = (okio.Source) r3
            java.util.zip.Inflater r2 = new java.util.zip.Inflater
            r2.<init>(r1)
            r8.<init>((okio.Source) r3, (java.util.zip.Inflater) r2)
            okio.internal.FixedLengthSource r1 = new okio.internal.FixedLengthSource
            okio.Source r8 = (okio.Source) r8
            long r2 = r0.getSize()
            r0 = 0
            r1.<init>(r8, r2, r0)
            r8 = r1
            okio.Source r8 = (okio.Source) r8
        L_0x0086:
            return r8
        L_0x0087:
            throw r1
        L_0x0088:
            java.io.FileNotFoundException r0 = new java.io.FileNotFoundException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "no such file: "
            r1.<init>(r2)
            java.lang.StringBuilder r8 = r1.append(r8)
            java.lang.String r8 = r8.toString()
            r0.<init>(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.ZipFileSystem.source(okio.Path):okio.Source");
    }

    public Sink sink(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "file");
        throw new IOException("zip file systems are read-only");
    }

    public Sink appendingSink(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "file");
        throw new IOException("zip file systems are read-only");
    }

    public void createDirectory(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "dir");
        throw new IOException("zip file systems are read-only");
    }

    public void atomicMove(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "source");
        Intrinsics.checkNotNullParameter(path2, "target");
        throw new IOException("zip file systems are read-only");
    }

    public void delete(Path path, boolean z) {
        Intrinsics.checkNotNullParameter(path, "path");
        throw new IOException("zip file systems are read-only");
    }

    public void createSymlink(Path path, Path path2) {
        Intrinsics.checkNotNullParameter(path, "source");
        Intrinsics.checkNotNullParameter(path2, "target");
        throw new IOException("zip file systems are read-only");
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lokio/ZipFileSystem$Companion;", "", "()V", "ROOT", "Lokio/Path;", "getROOT", "()Lokio/Path;", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ZipFileSystem.kt */
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Path getROOT() {
            return ZipFileSystem.ROOT;
        }
    }
}
