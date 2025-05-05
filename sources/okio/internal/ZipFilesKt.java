package okio.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.UShort;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okhttp3.internal.ws.WebSocketProtocol;
import okio.BufferedSource;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Path;
import okio.ZipFileSystem;

@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\"\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u00132\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0017H\u0002\u001a\u001f\u0010\u0018\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u001b\u001a.\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020 2\u0014\b\u0002\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020#0\"H\u0000\u001a\f\u0010$\u001a\u00020\u0015*\u00020%H\u0000\u001a\f\u0010&\u001a\u00020'*\u00020%H\u0002\u001a.\u0010(\u001a\u00020)*\u00020%2\u0006\u0010*\u001a\u00020\u00012\u0018\u0010+\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020)0,H\u0002\u001a\u0014\u0010-\u001a\u00020.*\u00020%2\u0006\u0010/\u001a\u00020.H\u0000\u001a\u0018\u00100\u001a\u0004\u0018\u00010.*\u00020%2\b\u0010/\u001a\u0004\u0018\u00010.H\u0002\u001a\u0014\u00101\u001a\u00020'*\u00020%2\u0006\u00102\u001a\u00020'H\u0002\u001a\f\u00103\u001a\u00020)*\u00020%H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u000bXT¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0018\u0010\u000e\u001a\u00020\u000f*\u00020\u00018BX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u00064"}, d2 = {"BIT_FLAG_ENCRYPTED", "", "BIT_FLAG_UNSUPPORTED_MASK", "CENTRAL_FILE_HEADER_SIGNATURE", "COMPRESSION_METHOD_DEFLATED", "COMPRESSION_METHOD_STORED", "END_OF_CENTRAL_DIRECTORY_SIGNATURE", "HEADER_ID_EXTENDED_TIMESTAMP", "HEADER_ID_ZIP64_EXTENDED_INFO", "LOCAL_FILE_HEADER_SIGNATURE", "MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE", "", "ZIP64_EOCD_RECORD_SIGNATURE", "ZIP64_LOCATOR_SIGNATURE", "hex", "", "getHex", "(I)Ljava/lang/String;", "buildIndex", "", "Lokio/Path;", "Lokio/internal/ZipEntry;", "entries", "", "dosDateTimeToEpochMillis", "date", "time", "(II)Ljava/lang/Long;", "openZip", "Lokio/ZipFileSystem;", "zipPath", "fileSystem", "Lokio/FileSystem;", "predicate", "Lkotlin/Function1;", "", "readEntry", "Lokio/BufferedSource;", "readEocdRecord", "Lokio/internal/EocdRecord;", "readExtra", "", "extraSize", "block", "Lkotlin/Function2;", "readLocalHeader", "Lokio/FileMetadata;", "basicMetadata", "readOrSkipLocalHeader", "readZip64EocdRecord", "regularRecord", "skipLocalHeader", "okio"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ZipFiles.kt */
public final class ZipFilesKt {
    private static final int BIT_FLAG_ENCRYPTED = 1;
    private static final int BIT_FLAG_UNSUPPORTED_MASK = 1;
    private static final int CENTRAL_FILE_HEADER_SIGNATURE = 33639248;
    public static final int COMPRESSION_METHOD_DEFLATED = 8;
    public static final int COMPRESSION_METHOD_STORED = 0;
    private static final int END_OF_CENTRAL_DIRECTORY_SIGNATURE = 101010256;
    private static final int HEADER_ID_EXTENDED_TIMESTAMP = 21589;
    private static final int HEADER_ID_ZIP64_EXTENDED_INFO = 1;
    private static final int LOCAL_FILE_HEADER_SIGNATURE = 67324752;
    private static final long MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE = 4294967295L;
    private static final int ZIP64_EOCD_RECORD_SIGNATURE = 101075792;
    private static final int ZIP64_LOCATOR_SIGNATURE = 117853008;

    public static /* synthetic */ ZipFileSystem openZip$default(Path path, FileSystem fileSystem, Function1 function1, int i, Object obj) throws IOException {
        if ((i & 4) != 0) {
            function1 = ZipFilesKt$openZip$1.INSTANCE;
        }
        return openZip(path, fileSystem, function1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
        r3.close();
        r6 = r6 - ((long) 20);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005f, code lost:
        if (r6 <= 0) goto L_0x00f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0061, code lost:
        r3 = okio.Okio.buffer(r5.source(r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r6 = (okio.BufferedSource) r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0075, code lost:
        if (r6.readIntLe() != ZIP64_LOCATOR_SIGNATURE) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0077, code lost:
        r7 = r6.readIntLe();
        r13 = r6.readLongLe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0084, code lost:
        if (r6.readIntLe() != 1) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0086, code lost:
        if (r7 != 0) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0088, code lost:
        r6 = okio.Okio.buffer(r5.source(r13));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        r7 = (okio.BufferedSource) r6;
        r13 = r7.readIntLe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x009c, code lost:
        if (r13 != ZIP64_EOCD_RECORD_SIGNATURE) goto L_0x00a9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x009e, code lost:
        r7 = readZip64EocdRecord(r7, r10);
        r10 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r6, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a7, code lost:
        r10 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00d3, code lost:
        throw new java.io.IOException("bad zip: expected " + getHex(ZIP64_EOCD_RECORD_SIGNATURE) + " but was " + getHex(r13));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d4, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00d5, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d7, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d8, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r6, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00dc, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00e4, code lost:
        throw new java.io.IOException("unsupported zip: spanned");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00e5, code lost:
        r6 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r3, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00eb, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ec, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ee, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00ef, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r3, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00f3, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00f4, code lost:
        r3 = new java.util.ArrayList();
        r5 = okio.Okio.buffer(r5.source(r10.getCentralDirectoryOffset()));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:?, code lost:
        r6 = (okio.BufferedSource) r5;
        r13 = r10.getEntryCount();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0112, code lost:
        if (r8 >= r13) goto L_0x0142;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0114, code lost:
        r7 = readEntry(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0122, code lost:
        if (r7.getOffset() >= r10.getCentralDirectoryOffset()) goto L_0x013a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x012e, code lost:
        if (r2.invoke(r7).booleanValue() == false) goto L_0x0136;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0130, code lost:
        r3.add(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0136, code lost:
        r8 = r8 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0141, code lost:
        throw new java.io.IOException("bad zip: local file header offset >= central directory offset");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0142, code lost:
        r2 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, (java.lang.Throwable) null);
        r3 = new okio.ZipFileSystem(r0, r1, buildIndex(r3), r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x0150, code lost:
        kotlin.io.CloseableKt.closeFinally(r4, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0153, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0154, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0155, code lost:
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0157, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0158, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x015c, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0048, code lost:
        r10 = readEocdRecord(r3);
        r11 = r3.readUtf8((long) r10.getCommentByteCount());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final okio.ZipFileSystem openZip(okio.Path r19, okio.FileSystem r20, kotlin.jvm.functions.Function1<? super okio.internal.ZipEntry, java.lang.Boolean> r21) throws java.io.IOException {
        /*
            r0 = r19
            r1 = r20
            r2 = r21
            java.lang.String r3 = "not a zip: size="
            java.lang.String r4 = "zipPath"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            java.lang.String r4 = "fileSystem"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r4)
            java.lang.String r4 = "predicate"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
            okio.FileHandle r4 = r1.openReadOnly(r0)
            java.io.Closeable r4 = (java.io.Closeable) r4
            r5 = r4
            okio.FileHandle r5 = (okio.FileHandle) r5     // Catch:{ all -> 0x018d }
            long r6 = r5.size()     // Catch:{ all -> 0x018d }
            r8 = 22
            long r8 = (long) r8     // Catch:{ all -> 0x018d }
            long r6 = r6 - r8
            r8 = 0
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 < 0) goto L_0x0176
            r10 = 65536(0x10000, double:3.2379E-319)
            long r10 = r6 - r10
            long r10 = java.lang.Math.max(r10, r8)     // Catch:{ all -> 0x018d }
        L_0x0037:
            okio.Source r3 = r5.source(r6)     // Catch:{ all -> 0x018d }
            okio.BufferedSource r3 = okio.Okio.buffer((okio.Source) r3)     // Catch:{ all -> 0x018d }
            int r12 = r3.readIntLe()     // Catch:{ all -> 0x0171 }
            r13 = 101010256(0x6054b50, float:2.506985E-35)
            if (r12 != r13) goto L_0x015d
            okio.internal.EocdRecord r10 = readEocdRecord(r3)     // Catch:{ all -> 0x0171 }
            int r11 = r10.getCommentByteCount()     // Catch:{ all -> 0x0171 }
            long r11 = (long) r11     // Catch:{ all -> 0x0171 }
            java.lang.String r11 = r3.readUtf8(r11)     // Catch:{ all -> 0x0171 }
            r3.close()     // Catch:{ all -> 0x018d }
            r3 = 20
            long r12 = (long) r3     // Catch:{ all -> 0x018d }
            long r6 = r6 - r12
            int r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            r12 = 0
            if (r3 <= 0) goto L_0x00f4
            okio.Source r3 = r5.source(r6)     // Catch:{ all -> 0x018d }
            okio.BufferedSource r3 = okio.Okio.buffer((okio.Source) r3)     // Catch:{ all -> 0x018d }
            java.io.Closeable r3 = (java.io.Closeable) r3     // Catch:{ all -> 0x018d }
            r6 = r3
            okio.BufferedSource r6 = (okio.BufferedSource) r6     // Catch:{ all -> 0x00eb }
            int r7 = r6.readIntLe()     // Catch:{ all -> 0x00eb }
            r13 = 117853008(0x7064b50, float:1.0103172E-34)
            if (r7 != r13) goto L_0x00e5
            int r7 = r6.readIntLe()     // Catch:{ all -> 0x00eb }
            long r13 = r6.readLongLe()     // Catch:{ all -> 0x00eb }
            int r6 = r6.readIntLe()     // Catch:{ all -> 0x00eb }
            r15 = 1
            if (r6 != r15) goto L_0x00dd
            if (r7 != 0) goto L_0x00dd
            okio.Source r6 = r5.source(r13)     // Catch:{ all -> 0x00eb }
            okio.BufferedSource r6 = okio.Okio.buffer((okio.Source) r6)     // Catch:{ all -> 0x00eb }
            java.io.Closeable r6 = (java.io.Closeable) r6     // Catch:{ all -> 0x00eb }
            r7 = r6
            okio.BufferedSource r7 = (okio.BufferedSource) r7     // Catch:{ all -> 0x00d4 }
            int r13 = r7.readIntLe()     // Catch:{ all -> 0x00d4 }
            r14 = 101075792(0x6064b50, float:2.525793E-35)
            if (r13 != r14) goto L_0x00a9
            okio.internal.EocdRecord r7 = readZip64EocdRecord(r7, r10)     // Catch:{ all -> 0x00d4 }
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00d4 }
            kotlin.io.CloseableKt.closeFinally(r6, r12)     // Catch:{ all -> 0x00eb }
            r10 = r7
            goto L_0x00e5
        L_0x00a9:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x00d4 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d4 }
            r1.<init>()     // Catch:{ all -> 0x00d4 }
            java.lang.String r2 = "bad zip: expected "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x00d4 }
            java.lang.String r2 = getHex(r14)     // Catch:{ all -> 0x00d4 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x00d4 }
            java.lang.String r2 = " but was "
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x00d4 }
            java.lang.String r2 = getHex(r13)     // Catch:{ all -> 0x00d4 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x00d4 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00d4 }
            r0.<init>(r1)     // Catch:{ all -> 0x00d4 }
            throw r0     // Catch:{ all -> 0x00d4 }
        L_0x00d4:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch:{ all -> 0x00d7 }
        L_0x00d7:
            r0 = move-exception
            r2 = r0
            kotlin.io.CloseableKt.closeFinally(r6, r1)     // Catch:{ all -> 0x00eb }
            throw r2     // Catch:{ all -> 0x00eb }
        L_0x00dd:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x00eb }
            java.lang.String r1 = "unsupported zip: spanned"
            r0.<init>(r1)     // Catch:{ all -> 0x00eb }
            throw r0     // Catch:{ all -> 0x00eb }
        L_0x00e5:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00eb }
            kotlin.io.CloseableKt.closeFinally(r3, r12)     // Catch:{ all -> 0x018d }
            goto L_0x00f4
        L_0x00eb:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch:{ all -> 0x00ee }
        L_0x00ee:
            r0 = move-exception
            r2 = r0
            kotlin.io.CloseableKt.closeFinally(r3, r1)     // Catch:{ all -> 0x018d }
            throw r2     // Catch:{ all -> 0x018d }
        L_0x00f4:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x018d }
            r3.<init>()     // Catch:{ all -> 0x018d }
            java.util.List r3 = (java.util.List) r3     // Catch:{ all -> 0x018d }
            long r6 = r10.getCentralDirectoryOffset()     // Catch:{ all -> 0x018d }
            okio.Source r5 = r5.source(r6)     // Catch:{ all -> 0x018d }
            okio.BufferedSource r5 = okio.Okio.buffer((okio.Source) r5)     // Catch:{ all -> 0x018d }
            java.io.Closeable r5 = (java.io.Closeable) r5     // Catch:{ all -> 0x018d }
            r6 = r5
            okio.BufferedSource r6 = (okio.BufferedSource) r6     // Catch:{ all -> 0x0154 }
            long r13 = r10.getEntryCount()     // Catch:{ all -> 0x0154 }
        L_0x0110:
            int r7 = (r8 > r13 ? 1 : (r8 == r13 ? 0 : -1))
            if (r7 >= 0) goto L_0x0142
            okio.internal.ZipEntry r7 = readEntry(r6)     // Catch:{ all -> 0x0154 }
            long r15 = r7.getOffset()     // Catch:{ all -> 0x0154 }
            long r17 = r10.getCentralDirectoryOffset()     // Catch:{ all -> 0x0154 }
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 >= 0) goto L_0x013a
            java.lang.Object r15 = r2.invoke(r7)     // Catch:{ all -> 0x0154 }
            java.lang.Boolean r15 = (java.lang.Boolean) r15     // Catch:{ all -> 0x0154 }
            boolean r15 = r15.booleanValue()     // Catch:{ all -> 0x0154 }
            if (r15 == 0) goto L_0x0136
            r15 = r3
            java.util.Collection r15 = (java.util.Collection) r15     // Catch:{ all -> 0x0154 }
            r15.add(r7)     // Catch:{ all -> 0x0154 }
        L_0x0136:
            r15 = 1
            long r8 = r8 + r15
            goto L_0x0110
        L_0x013a:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x0154 }
            java.lang.String r1 = "bad zip: local file header offset >= central directory offset"
            r0.<init>(r1)     // Catch:{ all -> 0x0154 }
            throw r0     // Catch:{ all -> 0x0154 }
        L_0x0142:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0154 }
            kotlin.io.CloseableKt.closeFinally(r5, r12)     // Catch:{ all -> 0x018d }
            java.util.Map r2 = buildIndex(r3)     // Catch:{ all -> 0x018d }
            okio.ZipFileSystem r3 = new okio.ZipFileSystem     // Catch:{ all -> 0x018d }
            r3.<init>(r0, r1, r2, r11)     // Catch:{ all -> 0x018d }
            kotlin.io.CloseableKt.closeFinally(r4, r12)
            return r3
        L_0x0154:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch:{ all -> 0x0157 }
        L_0x0157:
            r0 = move-exception
            r2 = r0
            kotlin.io.CloseableKt.closeFinally(r5, r1)     // Catch:{ all -> 0x018d }
            throw r2     // Catch:{ all -> 0x018d }
        L_0x015d:
            r3.close()     // Catch:{ all -> 0x018d }
            r12 = -1
            long r6 = r6 + r12
            int r3 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r3 < 0) goto L_0x0169
            goto L_0x0037
        L_0x0169:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x018d }
            java.lang.String r1 = "not a zip: end of central directory signature not found"
            r0.<init>(r1)     // Catch:{ all -> 0x018d }
            throw r0     // Catch:{ all -> 0x018d }
        L_0x0171:
            r0 = move-exception
            r3.close()     // Catch:{ all -> 0x018d }
            throw r0     // Catch:{ all -> 0x018d }
        L_0x0176:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x018d }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x018d }
            r1.<init>(r3)     // Catch:{ all -> 0x018d }
            long r2 = r5.size()     // Catch:{ all -> 0x018d }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x018d }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x018d }
            r0.<init>(r1)     // Catch:{ all -> 0x018d }
            throw r0     // Catch:{ all -> 0x018d }
        L_0x018d:
            r0 = move-exception
            r1 = r0
            throw r1     // Catch:{ all -> 0x0190 }
        L_0x0190:
            r0 = move-exception
            r2 = r0
            kotlin.io.CloseableKt.closeFinally(r4, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.internal.ZipFilesKt.openZip(okio.Path, okio.FileSystem, kotlin.jvm.functions.Function1):okio.ZipFileSystem");
    }

    private static final Map<Path, ZipEntry> buildIndex(List<ZipEntry> list) {
        Path path = Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null);
        Map<Path, ZipEntry> mutableMapOf = MapsKt.mutableMapOf(TuplesKt.to(path, new ZipEntry(path, true, (String) null, 0, 0, 0, 0, (Long) null, 0, TypedValues.PositionType.TYPE_CURVE_FIT, (DefaultConstructorMarker) null)));
        for (ZipEntry zipEntry : CollectionsKt.sortedWith(list, new ZipFilesKt$buildIndex$$inlined$sortedBy$1())) {
            if (mutableMapOf.put(zipEntry.getCanonicalPath(), zipEntry) == null) {
                while (true) {
                    Path parent = zipEntry.getCanonicalPath().parent();
                    if (parent == null) {
                        break;
                    }
                    ZipEntry zipEntry2 = mutableMapOf.get(parent);
                    if (zipEntry2 != null) {
                        zipEntry2.getChildren().add(zipEntry.getCanonicalPath());
                        break;
                    }
                    ZipEntry zipEntry3 = r4;
                    ZipEntry zipEntry4 = new ZipEntry(parent, true, (String) null, 0, 0, 0, 0, (Long) null, 0, TypedValues.PositionType.TYPE_CURVE_FIT, (DefaultConstructorMarker) null);
                    ZipEntry zipEntry5 = zipEntry3;
                    mutableMapOf.put(parent, zipEntry5);
                    zipEntry5.getChildren().add(zipEntry.getCanonicalPath());
                    zipEntry = zipEntry5;
                }
            }
        }
        return mutableMapOf;
    }

    public static final ZipEntry readEntry(BufferedSource bufferedSource) throws IOException {
        BufferedSource bufferedSource2 = bufferedSource;
        Intrinsics.checkNotNullParameter(bufferedSource2, "<this>");
        int readIntLe = bufferedSource.readIntLe();
        if (readIntLe == CENTRAL_FILE_HEADER_SIGNATURE) {
            bufferedSource2.skip(4);
            short readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
            if ((readShortLe & 1) == 0) {
                short readShortLe2 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
                Long dosDateTimeToEpochMillis = dosDateTimeToEpochMillis(bufferedSource.readShortLe() & UShort.MAX_VALUE, bufferedSource.readShortLe() & UShort.MAX_VALUE);
                long readIntLe2 = ((long) bufferedSource.readIntLe()) & 4294967295L;
                Ref.LongRef longRef = new Ref.LongRef();
                longRef.element = ((long) bufferedSource.readIntLe()) & 4294967295L;
                Ref.LongRef longRef2 = new Ref.LongRef();
                longRef2.element = ((long) bufferedSource.readIntLe()) & 4294967295L;
                short readShortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
                short readShortLe4 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
                short readShortLe5 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
                bufferedSource2.skip(8);
                Ref.LongRef longRef3 = new Ref.LongRef();
                longRef3.element = ((long) bufferedSource.readIntLe()) & 4294967295L;
                String readUtf8 = bufferedSource2.readUtf8((long) readShortLe3);
                if (!StringsKt.contains$default((CharSequence) readUtf8, 0, false, 2, (Object) null)) {
                    String str = readUtf8;
                    long j = longRef2.element == 4294967295L ? ((long) 8) + 0 : 0;
                    long j2 = longRef.element == 4294967295L ? j + ((long) 8) : j;
                    if (longRef3.element == 4294967295L) {
                        j2 += (long) 8;
                    }
                    long j3 = j2;
                    Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                    Long l = dosDateTimeToEpochMillis;
                    Ref.BooleanRef booleanRef2 = booleanRef;
                    short s = readShortLe2;
                    String str2 = str;
                    Ref.LongRef longRef4 = longRef3;
                    short s2 = readShortLe5;
                    readExtra(bufferedSource2, readShortLe4, new ZipFilesKt$readEntry$1(booleanRef, j3, longRef2, bufferedSource, longRef, longRef4));
                    if (j3 <= 0 || booleanRef2.element) {
                        String str3 = str2;
                        return new ZipEntry(Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null).resolve(str3), StringsKt.endsWith$default(str3, "/", false, 2, (Object) null), bufferedSource2.readUtf8((long) s2), readIntLe2, longRef.element, longRef2.element, s, l, longRef4.element);
                    }
                    throw new IOException("bad zip: zip64 extra required but absent");
                }
                throw new IOException("bad zip: filename contains 0x00");
            }
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(readShortLe));
        }
        throw new IOException("bad zip: expected " + getHex(CENTRAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(readIntLe));
    }

    private static final EocdRecord readEocdRecord(BufferedSource bufferedSource) throws IOException {
        short readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        short readShortLe2 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
        long readShortLe3 = (long) (bufferedSource.readShortLe() & UShort.MAX_VALUE);
        if (readShortLe3 == ((long) (bufferedSource.readShortLe() & UShort.MAX_VALUE)) && readShortLe == 0 && readShortLe2 == 0) {
            bufferedSource.skip(4);
            return new EocdRecord(readShortLe3, 4294967295L & ((long) bufferedSource.readIntLe()), bufferedSource.readShortLe() & UShort.MAX_VALUE);
        }
        throw new IOException("unsupported zip: spanned");
    }

    private static final EocdRecord readZip64EocdRecord(BufferedSource bufferedSource, EocdRecord eocdRecord) throws IOException {
        bufferedSource.skip(12);
        int readIntLe = bufferedSource.readIntLe();
        int readIntLe2 = bufferedSource.readIntLe();
        long readLongLe = bufferedSource.readLongLe();
        if (readLongLe == bufferedSource.readLongLe() && readIntLe == 0 && readIntLe2 == 0) {
            bufferedSource.skip(8);
            return new EocdRecord(readLongLe, bufferedSource.readLongLe(), eocdRecord.getCommentByteCount());
        }
        throw new IOException("unsupported zip: spanned");
    }

    private static final void readExtra(BufferedSource bufferedSource, int i, Function2<? super Integer, ? super Long, Unit> function2) {
        long j = (long) i;
        while (j != 0) {
            if (j >= 4) {
                short readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
                long readShortLe2 = ((long) bufferedSource.readShortLe()) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
                long j2 = j - ((long) 4);
                if (j2 >= readShortLe2) {
                    bufferedSource.require(readShortLe2);
                    long size = bufferedSource.getBuffer().size();
                    function2.invoke(Integer.valueOf(readShortLe), Long.valueOf(readShortLe2));
                    long size2 = (bufferedSource.getBuffer().size() + readShortLe2) - size;
                    int i2 = (size2 > 0 ? 1 : (size2 == 0 ? 0 : -1));
                    if (i2 >= 0) {
                        if (i2 > 0) {
                            bufferedSource.getBuffer().skip(size2);
                        }
                        j = j2 - readShortLe2;
                    } else {
                        throw new IOException("unsupported zip: too many bytes processed for " + readShortLe);
                    }
                } else {
                    throw new IOException("bad zip: truncated value in extra field");
                }
            } else {
                throw new IOException("bad zip: truncated header in extra field");
            }
        }
    }

    public static final void skipLocalHeader(BufferedSource bufferedSource) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        readOrSkipLocalHeader(bufferedSource, (FileMetadata) null);
    }

    public static final FileMetadata readLocalHeader(BufferedSource bufferedSource, FileMetadata fileMetadata) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        Intrinsics.checkNotNullParameter(fileMetadata, "basicMetadata");
        FileMetadata readOrSkipLocalHeader = readOrSkipLocalHeader(bufferedSource, fileMetadata);
        Intrinsics.checkNotNull(readOrSkipLocalHeader);
        return readOrSkipLocalHeader;
    }

    private static final FileMetadata readOrSkipLocalHeader(BufferedSource bufferedSource, FileMetadata fileMetadata) {
        BufferedSource bufferedSource2 = bufferedSource;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = fileMetadata != null ? fileMetadata.getLastModifiedAtMillis() : null;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        int readIntLe = bufferedSource.readIntLe();
        if (readIntLe == LOCAL_FILE_HEADER_SIGNATURE) {
            bufferedSource2.skip(2);
            short readShortLe = bufferedSource.readShortLe() & UShort.MAX_VALUE;
            if ((readShortLe & 1) == 0) {
                bufferedSource2.skip(18);
                long readShortLe2 = ((long) bufferedSource.readShortLe()) & WebSocketProtocol.PAYLOAD_SHORT_MAX;
                short readShortLe3 = bufferedSource.readShortLe() & UShort.MAX_VALUE;
                bufferedSource2.skip(readShortLe2);
                if (fileMetadata == null) {
                    bufferedSource2.skip((long) readShortLe3);
                    return null;
                }
                readExtra(bufferedSource2, readShortLe3, new ZipFilesKt$readOrSkipLocalHeader$1(bufferedSource2, objectRef, objectRef2, objectRef3));
                return new FileMetadata(fileMetadata.isRegularFile(), fileMetadata.isDirectory(), (Path) null, fileMetadata.getSize(), (Long) objectRef3.element, (Long) objectRef.element, (Long) objectRef2.element, (Map) null, 128, (DefaultConstructorMarker) null);
            }
            throw new IOException("unsupported zip: general purpose bit flag=" + getHex(readShortLe));
        }
        throw new IOException("bad zip: expected " + getHex(LOCAL_FILE_HEADER_SIGNATURE) + " but was " + getHex(readIntLe));
    }

    private static final Long dosDateTimeToEpochMillis(int i, int i2) {
        if (i2 == -1) {
            return null;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(14, 0);
        GregorianCalendar gregorianCalendar2 = gregorianCalendar;
        gregorianCalendar2.set(((i >> 9) & 127) + 1980, ((i >> 5) & 15) - 1, i & 31, (i2 >> 11) & 31, (i2 >> 5) & 63, (i2 & 31) << 1);
        return Long.valueOf(gregorianCalendar.getTime().getTime());
    }

    private static final String getHex(int i) {
        StringBuilder sb = new StringBuilder("0x");
        String num = Integer.toString(i, CharsKt.checkRadix(16));
        Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
        return sb.append(num).toString();
    }
}
