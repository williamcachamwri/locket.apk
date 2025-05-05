package okio.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import okio.FileMetadata;
import okio.Path;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aI\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\u001c\u0010\r\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0003H\u0000\u001a\u001c\u0010\u0010\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\nH\u0000\u001a\u001c\u0010\u0013\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\nH\u0000\u001a\u0014\u0010\u0016\u001a\u00020\n*\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0000\u001a\"\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0018*\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\u0014\u0010\u0019\u001a\u00020\u001a*\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0000\u001a\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u0003*\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"collectRecursively", "", "Lkotlin/sequences/SequenceScope;", "Lokio/Path;", "fileSystem", "Lokio/FileSystem;", "stack", "Lkotlin/collections/ArrayDeque;", "path", "followSymlinks", "", "postorder", "(Lkotlin/sequences/SequenceScope;Lokio/FileSystem;Lkotlin/collections/ArrayDeque;Lokio/Path;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "commonCopy", "source", "target", "commonCreateDirectories", "dir", "mustCreate", "commonDeleteRecursively", "fileOrDirectory", "mustExist", "commonExists", "commonListRecursively", "Lkotlin/sequences/Sequence;", "commonMetadata", "Lokio/FileMetadata;", "symlinkTarget", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* renamed from: okio.internal.-FileSystem  reason: invalid class name */
/* compiled from: FileSystem.kt */
public final class FileSystem {
    public static final FileMetadata commonMetadata(okio.FileSystem fileSystem, Path path) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        FileMetadata metadataOrNull = fileSystem.metadataOrNull(path);
        if (metadataOrNull != null) {
            return metadataOrNull;
        }
        throw new FileNotFoundException("no such file: " + path);
    }

    public static final boolean commonExists(okio.FileSystem fileSystem, Path path) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        return fileSystem.metadataOrNull(path) != null;
    }

    public static final void commonCreateDirectories(okio.FileSystem fileSystem, Path path, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "dir");
        ArrayDeque arrayDeque = new ArrayDeque();
        Path path2 = path;
        while (path2 != null && !fileSystem.exists(path2)) {
            arrayDeque.addFirst(path2);
            path2 = path2.parent();
        }
        if (!z || !arrayDeque.isEmpty()) {
            Iterator it = arrayDeque.iterator();
            while (it.hasNext()) {
                fileSystem.createDirectory((Path) it.next());
            }
            return;
        }
        throw new IOException(path + " already exists.");
    }

    public static final void commonDeleteRecursively(okio.FileSystem fileSystem, Path path, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "fileOrDirectory");
        Iterator it = SequencesKt.sequence(new FileSystem$commonDeleteRecursively$sequence$1(fileSystem, path, (Continuation<? super FileSystem$commonDeleteRecursively$sequence$1>) null)).iterator();
        while (it.hasNext()) {
            fileSystem.delete((Path) it.next(), z && !it.hasNext());
        }
    }

    public static final Sequence<Path> commonListRecursively(okio.FileSystem fileSystem, Path path, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "dir");
        return SequencesKt.sequence(new FileSystem$commonListRecursively$1(path, fileSystem, z, (Continuation<? super FileSystem$commonListRecursively$1>) null));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00e3, code lost:
        if (r0 != false) goto L_0x00e7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00e5, code lost:
        if (r14 != 0) goto L_0x013c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00e7, code lost:
        r6.addLast(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ee, code lost:
        r13 = r12;
        r12 = r11;
        r11 = r6;
        r6 = r1;
        r1 = r0;
        r0 = r2;
        r2 = r3.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0132, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0133, code lost:
        r11 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0134, code lost:
        r11.removeLast();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0137, code lost:
        throw r0;
     */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00fb A[Catch:{ all -> 0x005f }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0155  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x012b A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object collectRecursively(kotlin.sequences.SequenceScope<? super okio.Path> r17, okio.FileSystem r18, kotlin.collections.ArrayDeque<okio.Path> r19, okio.Path r20, boolean r21, boolean r22, kotlin.coroutines.Continuation<? super kotlin.Unit> r23) {
        /*
            r0 = r17
            r1 = r20
            r2 = r22
            r3 = r23
            boolean r4 = r3 instanceof okio.internal.FileSystem$collectRecursively$1
            if (r4 == 0) goto L_0x001c
            r4 = r3
            okio.internal.-FileSystem$collectRecursively$1 r4 = (okio.internal.FileSystem$collectRecursively$1) r4
            int r5 = r4.label
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r5 & r6
            if (r5 == 0) goto L_0x001c
            int r3 = r4.label
            int r3 = r3 - r6
            r4.label = r3
            goto L_0x0021
        L_0x001c:
            okio.internal.-FileSystem$collectRecursively$1 r4 = new okio.internal.-FileSystem$collectRecursively$1
            r4.<init>(r3)
        L_0x0021:
            java.lang.Object r3 = r4.result
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r6 = r4.label
            r7 = 0
            r8 = 3
            r9 = 2
            r10 = 1
            if (r6 == 0) goto L_0x0080
            if (r6 == r10) goto L_0x0062
            if (r6 == r9) goto L_0x0042
            if (r6 != r8) goto L_0x003a
            kotlin.ResultKt.throwOnFailure(r3)
            goto L_0x0152
        L_0x003a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0042:
            boolean r0 = r4.Z$1
            boolean r1 = r4.Z$0
            java.lang.Object r2 = r4.L$4
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r6 = r4.L$3
            okio.Path r6 = (okio.Path) r6
            java.lang.Object r11 = r4.L$2
            kotlin.collections.ArrayDeque r11 = (kotlin.collections.ArrayDeque) r11
            java.lang.Object r12 = r4.L$1
            okio.FileSystem r12 = (okio.FileSystem) r12
            java.lang.Object r13 = r4.L$0
            kotlin.sequences.SequenceScope r13 = (kotlin.sequences.SequenceScope) r13
            kotlin.ResultKt.throwOnFailure(r3)     // Catch:{ all -> 0x005f }
            goto L_0x00f5
        L_0x005f:
            r0 = move-exception
            goto L_0x0134
        L_0x0062:
            boolean r0 = r4.Z$1
            boolean r1 = r4.Z$0
            java.lang.Object r2 = r4.L$3
            okio.Path r2 = (okio.Path) r2
            java.lang.Object r6 = r4.L$2
            kotlin.collections.ArrayDeque r6 = (kotlin.collections.ArrayDeque) r6
            java.lang.Object r11 = r4.L$1
            okio.FileSystem r11 = (okio.FileSystem) r11
            java.lang.Object r12 = r4.L$0
            kotlin.sequences.SequenceScope r12 = (kotlin.sequences.SequenceScope) r12
            kotlin.ResultKt.throwOnFailure(r3)
            r16 = r2
            r2 = r0
            r0 = r1
            r1 = r16
            goto L_0x00a9
        L_0x0080:
            kotlin.ResultKt.throwOnFailure(r3)
            if (r2 != 0) goto L_0x00a0
            r4.L$0 = r0
            r3 = r18
            r4.L$1 = r3
            r6 = r19
            r4.L$2 = r6
            r4.L$3 = r1
            r11 = r21
            r4.Z$0 = r11
            r4.Z$1 = r2
            r4.label = r10
            java.lang.Object r12 = r0.yield(r1, r4)
            if (r12 != r5) goto L_0x00a6
            return r5
        L_0x00a0:
            r3 = r18
            r6 = r19
            r11 = r21
        L_0x00a6:
            r12 = r0
            r0 = r11
            r11 = r3
        L_0x00a9:
            java.util.List r3 = r11.listOrNull(r1)
            if (r3 != 0) goto L_0x00b3
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
        L_0x00b3:
            r13 = r3
            java.util.Collection r13 = (java.util.Collection) r13
            boolean r13 = r13.isEmpty()
            r13 = r13 ^ r10
            if (r13 == 0) goto L_0x013c
            r13 = r1
            r14 = r7
        L_0x00bf:
            if (r0 == 0) goto L_0x00dd
            boolean r15 = r6.contains(r13)
            if (r15 != 0) goto L_0x00c8
            goto L_0x00dd
        L_0x00c8:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "symlink cycle at "
            r2.<init>(r3)
            java.lang.StringBuilder r1 = r2.append(r1)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x00dd:
            okio.Path r15 = symlinkTarget(r11, r13)
            if (r15 != 0) goto L_0x0138
            if (r0 != 0) goto L_0x00e7
            if (r14 != 0) goto L_0x013c
        L_0x00e7:
            r6.addLast(r13)
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0132 }
            r13 = r12
            r12 = r11
            r11 = r6
            r6 = r1
            r1 = r0
            r0 = r2
            r2 = r3
        L_0x00f5:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x005f }
            if (r3 == 0) goto L_0x012b
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x005f }
            okio.Path r3 = (okio.Path) r3     // Catch:{ all -> 0x005f }
            if (r0 == 0) goto L_0x0105
            r14 = r10
            goto L_0x0106
        L_0x0105:
            r14 = r7
        L_0x0106:
            r4.L$0 = r13     // Catch:{ all -> 0x005f }
            r4.L$1 = r12     // Catch:{ all -> 0x005f }
            r4.L$2 = r11     // Catch:{ all -> 0x005f }
            r4.L$3 = r6     // Catch:{ all -> 0x005f }
            r4.L$4 = r2     // Catch:{ all -> 0x005f }
            r4.Z$0 = r1     // Catch:{ all -> 0x005f }
            r4.Z$1 = r0     // Catch:{ all -> 0x005f }
            r4.label = r9     // Catch:{ all -> 0x005f }
            r17 = r13
            r18 = r12
            r19 = r11
            r20 = r3
            r21 = r1
            r22 = r14
            r23 = r4
            java.lang.Object r3 = collectRecursively(r17, r18, r19, r20, r21, r22, r23)     // Catch:{ all -> 0x005f }
            if (r3 != r5) goto L_0x00f5
            return r5
        L_0x012b:
            r11.removeLast()
            r2 = r0
            r1 = r6
            r12 = r13
            goto L_0x013c
        L_0x0132:
            r0 = move-exception
            r11 = r6
        L_0x0134:
            r11.removeLast()
            throw r0
        L_0x0138:
            int r14 = r14 + 1
            r13 = r15
            goto L_0x00bf
        L_0x013c:
            if (r2 == 0) goto L_0x0155
            r0 = 0
            r4.L$0 = r0
            r4.L$1 = r0
            r4.L$2 = r0
            r4.L$3 = r0
            r4.L$4 = r0
            r4.label = r8
            java.lang.Object r0 = r12.yield(r1, r4)
            if (r0 != r5) goto L_0x0152
            return r5
        L_0x0152:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0155:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.FileSystem.collectRecursively(kotlin.sequences.SequenceScope, okio.FileSystem, kotlin.collections.ArrayDeque, okio.Path, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Path symlinkTarget(okio.FileSystem fileSystem, Path path) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem, "<this>");
        Intrinsics.checkNotNullParameter(path, "path");
        Path symlinkTarget = fileSystem.metadata(path).getSymlinkTarget();
        if (symlinkTarget == null) {
            return null;
        }
        Path parent = path.parent();
        Intrinsics.checkNotNull(parent);
        return parent.resolve(symlinkTarget);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047 A[Catch:{ all -> 0x0038, all -> 0x003f, all -> 0x005d }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x005c A[SYNTHETIC, Splitter:B:25:0x005c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void commonCopy(okio.FileSystem r4, okio.Path r5, okio.Path r6) throws java.io.IOException {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "source"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "target"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            okio.Source r5 = r4.source(r5)
            java.io.Closeable r5 = (java.io.Closeable) r5
            r0 = 0
            r1 = r5
            okio.Source r1 = (okio.Source) r1     // Catch:{ all -> 0x005d }
            okio.Sink r4 = r4.sink(r6)     // Catch:{ all -> 0x005d }
            okio.BufferedSink r4 = okio.Okio.buffer((okio.Sink) r4)     // Catch:{ all -> 0x005d }
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch:{ all -> 0x005d }
            r6 = r4
            okio.BufferedSink r6 = (okio.BufferedSink) r6     // Catch:{ all -> 0x0038 }
            long r1 = r6.writeAll(r1)     // Catch:{ all -> 0x0038 }
            java.lang.Long r6 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x0038 }
            if (r4 == 0) goto L_0x0036
            r4.close()     // Catch:{ all -> 0x0034 }
            goto L_0x0036
        L_0x0034:
            r4 = move-exception
            goto L_0x0045
        L_0x0036:
            r4 = r0
            goto L_0x0045
        L_0x0038:
            r6 = move-exception
            if (r4 == 0) goto L_0x0043
            r4.close()     // Catch:{ all -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r4 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r6, r4)     // Catch:{ all -> 0x005d }
        L_0x0043:
            r4 = r6
            r6 = r0
        L_0x0045:
            if (r4 != 0) goto L_0x005c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)     // Catch:{ all -> 0x005d }
            java.lang.Number r6 = (java.lang.Number) r6     // Catch:{ all -> 0x005d }
            long r1 = r6.longValue()     // Catch:{ all -> 0x005d }
            java.lang.Long r4 = java.lang.Long.valueOf(r1)     // Catch:{ all -> 0x005d }
            if (r5 == 0) goto L_0x006b
            r5.close()     // Catch:{ all -> 0x005a }
            goto L_0x006b
        L_0x005a:
            r0 = move-exception
            goto L_0x006b
        L_0x005c:
            throw r4     // Catch:{ all -> 0x005d }
        L_0x005d:
            r4 = move-exception
            if (r5 == 0) goto L_0x0068
            r5.close()     // Catch:{ all -> 0x0064 }
            goto L_0x0068
        L_0x0064:
            r5 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r4, r5)
        L_0x0068:
            r3 = r0
            r0 = r4
            r4 = r3
        L_0x006b:
            if (r0 != 0) goto L_0x0071
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            return
        L_0x0071:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.FileSystem.commonCopy(okio.FileSystem, okio.Path, okio.Path):void");
    }
}
