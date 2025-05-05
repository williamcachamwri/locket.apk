package okhttp3.internal.cache;

import com.facebook.cache.disk.DefaultDiskStorage;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.apache.commons.io.FilenameUtils;

@Metadata(d1 = {"\u0000y\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010)\n\u0002\b\u0007*\u0001\u0014\u0018\u0000 [2\u00020\u00012\u00020\u0002:\u0004[\\]^B7\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u00108\u001a\u000209H\u0002J\b\u0010:\u001a\u000209H\u0016J!\u0010;\u001a\u0002092\n\u0010<\u001a\u00060=R\u00020\u00002\u0006\u0010>\u001a\u00020\u0010H\u0000¢\u0006\u0002\b?J\u0006\u0010@\u001a\u000209J \u0010A\u001a\b\u0018\u00010=R\u00020\u00002\u0006\u0010B\u001a\u00020(2\b\b\u0002\u0010C\u001a\u00020\u000bH\u0007J\u0006\u0010D\u001a\u000209J\b\u0010E\u001a\u000209H\u0016J\u0017\u0010F\u001a\b\u0018\u00010GR\u00020\u00002\u0006\u0010B\u001a\u00020(H\u0002J\u0006\u0010H\u001a\u000209J\u0006\u0010I\u001a\u00020\u0010J\b\u0010J\u001a\u00020\u0010H\u0002J\b\u0010K\u001a\u00020%H\u0002J\b\u0010L\u001a\u000209H\u0002J\b\u0010M\u001a\u000209H\u0002J\u0010\u0010N\u001a\u0002092\u0006\u0010O\u001a\u00020(H\u0002J\r\u0010P\u001a\u000209H\u0000¢\u0006\u0002\bQJ\u000e\u0010R\u001a\u00020\u00102\u0006\u0010B\u001a\u00020(J\u0019\u0010S\u001a\u00020\u00102\n\u0010T\u001a\u00060)R\u00020\u0000H\u0000¢\u0006\u0002\bUJ\b\u0010V\u001a\u00020\u0010H\u0002J\u0006\u00105\u001a\u00020\u000bJ\u0010\u0010W\u001a\f\u0012\b\u0012\u00060GR\u00020\u00000XJ\u0006\u0010Y\u001a\u000209J\u0010\u0010Z\u001a\u0002092\u0006\u0010B\u001a\u00020(H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010%X\u000e¢\u0006\u0002\n\u0000R$\u0010&\u001a\u0012\u0012\u0004\u0012\u00020(\u0012\b\u0012\u00060)R\u00020\u00000'X\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R&\u0010\n\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u000b8F@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u000e\u00101\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00107¨\u0006_"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache;", "Ljava/io/Closeable;", "Ljava/io/Flushable;", "fileSystem", "Lokhttp3/internal/io/FileSystem;", "directory", "Ljava/io/File;", "appVersion", "", "valueCount", "maxSize", "", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "(Lokhttp3/internal/io/FileSystem;Ljava/io/File;IIJLokhttp3/internal/concurrent/TaskRunner;)V", "civilizedFileSystem", "", "cleanupQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "cleanupTask", "okhttp3/internal/cache/DiskLruCache$cleanupTask$1", "Lokhttp3/internal/cache/DiskLruCache$cleanupTask$1;", "closed", "getClosed$okhttp", "()Z", "setClosed$okhttp", "(Z)V", "getDirectory", "()Ljava/io/File;", "getFileSystem$okhttp", "()Lokhttp3/internal/io/FileSystem;", "hasJournalErrors", "initialized", "journalFile", "journalFileBackup", "journalFileTmp", "journalWriter", "Lokio/BufferedSink;", "lruEntries", "Ljava/util/LinkedHashMap;", "", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "getLruEntries$okhttp", "()Ljava/util/LinkedHashMap;", "value", "getMaxSize", "()J", "setMaxSize", "(J)V", "mostRecentRebuildFailed", "mostRecentTrimFailed", "nextSequenceNumber", "redundantOpCount", "size", "getValueCount$okhttp", "()I", "checkNotClosed", "", "close", "completeEdit", "editor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "success", "completeEdit$okhttp", "delete", "edit", "key", "expectedSequenceNumber", "evictAll", "flush", "get", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "initialize", "isClosed", "journalRebuildRequired", "newJournalWriter", "processJournal", "readJournal", "readJournalLine", "line", "rebuildJournal", "rebuildJournal$okhttp", "remove", "removeEntry", "entry", "removeEntry$okhttp", "removeOldestEntry", "snapshots", "", "trimToSize", "validateKey", "Companion", "Editor", "Entry", "Snapshot", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DiskLruCache.kt */
public final class DiskLruCache implements Closeable, Flushable {
    public static final long ANY_SEQUENCE_NUMBER = -1;
    public static final String CLEAN = "CLEAN";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String DIRTY = "DIRTY";
    public static final String JOURNAL_FILE = coil.disk.DiskLruCache.JOURNAL_FILE;
    public static final String JOURNAL_FILE_BACKUP = coil.disk.DiskLruCache.JOURNAL_FILE_BACKUP;
    public static final String JOURNAL_FILE_TEMP = coil.disk.DiskLruCache.JOURNAL_FILE_TMP;
    public static final Regex LEGAL_KEY_PATTERN = new Regex("[a-z0-9_-]{1,120}");
    public static final String MAGIC = coil.disk.DiskLruCache.MAGIC;
    public static final String READ = "READ";
    public static final String REMOVE = "REMOVE";
    public static final String VERSION_1 = "1";
    private final int appVersion;
    /* access modifiers changed from: private */
    public boolean civilizedFileSystem;
    private final TaskQueue cleanupQueue;
    private final DiskLruCache$cleanupTask$1 cleanupTask;
    private boolean closed;
    private final File directory;
    private final FileSystem fileSystem;
    /* access modifiers changed from: private */
    public boolean hasJournalErrors;
    /* access modifiers changed from: private */
    public boolean initialized;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    /* access modifiers changed from: private */
    public BufferedSink journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private long maxSize;
    /* access modifiers changed from: private */
    public boolean mostRecentRebuildFailed;
    /* access modifiers changed from: private */
    public boolean mostRecentTrimFailed;
    private long nextSequenceNumber;
    /* access modifiers changed from: private */
    public int redundantOpCount;
    private long size;
    private final int valueCount;

    public final Editor edit(String str) throws IOException {
        Intrinsics.checkNotNullParameter(str, "key");
        return edit$default(this, str, 0, 2, (Object) null);
    }

    public DiskLruCache(FileSystem fileSystem2, File file, int i, int i2, long j, TaskRunner taskRunner) {
        Intrinsics.checkNotNullParameter(fileSystem2, "fileSystem");
        Intrinsics.checkNotNullParameter(file, "directory");
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        this.fileSystem = fileSystem2;
        this.directory = file;
        this.appVersion = i;
        this.valueCount = i2;
        this.maxSize = j;
        boolean z = false;
        this.cleanupQueue = taskRunner.newQueue();
        this.cleanupTask = new DiskLruCache$cleanupTask$1(this, Util.okHttpName + " Cache");
        if (j > 0) {
            if (i2 > 0 ? true : z) {
                this.journalFile = new File(file, JOURNAL_FILE);
                this.journalFileTmp = new File(file, JOURNAL_FILE_TEMP);
                this.journalFileBackup = new File(file, JOURNAL_FILE_BACKUP);
                return;
            }
            throw new IllegalArgumentException("valueCount <= 0".toString());
        }
        throw new IllegalArgumentException("maxSize <= 0".toString());
    }

    public final FileSystem getFileSystem$okhttp() {
        return this.fileSystem;
    }

    public final File getDirectory() {
        return this.directory;
    }

    public final int getValueCount$okhttp() {
        return this.valueCount;
    }

    public final synchronized long getMaxSize() {
        return this.maxSize;
    }

    public final synchronized void setMaxSize(long j) {
        this.maxSize = j;
        if (this.initialized) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0, 2, (Object) null);
        }
    }

    public final LinkedHashMap<String, Entry> getLruEntries$okhttp() {
        return this.lruEntries;
    }

    public final boolean getClosed$okhttp() {
        return this.closed;
    }

    public final void setClosed$okhttp(boolean z) {
        this.closed = z;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:19|20|(1:22)(1:23)|24|25|26) */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r10.redundantOpCount = r9 - r10.lruEntries.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0077, code lost:
        if (r3.exhausted() == false) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0079, code lost:
        rebuildJournal$okhttp();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007d, code lost:
        r10.journalWriter = newJournalWriter();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0083, code lost:
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0085, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0089, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00bd, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00be, code lost:
        kotlin.io.CloseableKt.closeFinally(r2, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00c1, code lost:
        throw r1;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006a */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:19:0x006a=Splitter:B:19:0x006a, B:27:0x008a=Splitter:B:27:0x008a} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void readJournal() throws java.io.IOException {
        /*
            r10 = this;
            java.lang.String r0 = ", "
            java.lang.String r1 = "unexpected journal header: ["
            okhttp3.internal.io.FileSystem r2 = r10.fileSystem
            java.io.File r3 = r10.journalFile
            okio.Source r2 = r2.source(r3)
            okio.BufferedSource r2 = okio.Okio.buffer((okio.Source) r2)
            java.io.Closeable r2 = (java.io.Closeable) r2
            r3 = r2
            okio.BufferedSource r3 = (okio.BufferedSource) r3     // Catch:{ all -> 0x00bb }
            java.lang.String r4 = r3.readUtf8LineStrict()     // Catch:{ all -> 0x00bb }
            java.lang.String r5 = r3.readUtf8LineStrict()     // Catch:{ all -> 0x00bb }
            java.lang.String r6 = r3.readUtf8LineStrict()     // Catch:{ all -> 0x00bb }
            java.lang.String r7 = r3.readUtf8LineStrict()     // Catch:{ all -> 0x00bb }
            java.lang.String r8 = r3.readUtf8LineStrict()     // Catch:{ all -> 0x00bb }
            java.lang.String r9 = MAGIC     // Catch:{ all -> 0x00bb }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)     // Catch:{ all -> 0x00bb }
            if (r9 == 0) goto L_0x008a
            java.lang.String r9 = VERSION_1     // Catch:{ all -> 0x00bb }
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r5)     // Catch:{ all -> 0x00bb }
            if (r9 == 0) goto L_0x008a
            int r9 = r10.appVersion     // Catch:{ all -> 0x00bb }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ all -> 0x00bb }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r6)     // Catch:{ all -> 0x00bb }
            if (r6 == 0) goto L_0x008a
            int r6 = r10.valueCount     // Catch:{ all -> 0x00bb }
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ all -> 0x00bb }
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ all -> 0x00bb }
            if (r6 == 0) goto L_0x008a
            r6 = r8
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6     // Catch:{ all -> 0x00bb }
            int r6 = r6.length()     // Catch:{ all -> 0x00bb }
            r9 = 0
            if (r6 <= 0) goto L_0x005d
            r6 = 1
            goto L_0x005e
        L_0x005d:
            r6 = r9
        L_0x005e:
            if (r6 != 0) goto L_0x008a
        L_0x0060:
            java.lang.String r0 = r3.readUtf8LineStrict()     // Catch:{ EOFException -> 0x006a }
            r10.readJournalLine(r0)     // Catch:{ EOFException -> 0x006a }
            int r9 = r9 + 1
            goto L_0x0060
        L_0x006a:
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r10.lruEntries     // Catch:{ all -> 0x00bb }
            int r0 = r0.size()     // Catch:{ all -> 0x00bb }
            int r9 = r9 - r0
            r10.redundantOpCount = r9     // Catch:{ all -> 0x00bb }
            boolean r0 = r3.exhausted()     // Catch:{ all -> 0x00bb }
            if (r0 != 0) goto L_0x007d
            r10.rebuildJournal$okhttp()     // Catch:{ all -> 0x00bb }
            goto L_0x0083
        L_0x007d:
            okio.BufferedSink r0 = r10.newJournalWriter()     // Catch:{ all -> 0x00bb }
            r10.journalWriter = r0     // Catch:{ all -> 0x00bb }
        L_0x0083:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00bb }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r2, r0)
            return
        L_0x008a:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00bb }
            r6.<init>(r1)     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r1 = r6.append(r4)     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r1 = r1.append(r0)     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r1 = r1.append(r0)     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r1 = r1.append(r7)     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ all -> 0x00bb }
            java.lang.StringBuilder r0 = r0.append(r8)     // Catch:{ all -> 0x00bb }
            r1 = 93
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x00bb }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00bb }
            r3.<init>(r0)     // Catch:{ all -> 0x00bb }
            throw r3     // Catch:{ all -> 0x00bb }
        L_0x00bb:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x00bd }
        L_0x00bd:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.readJournal():void");
    }

    private final BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer((Sink) new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile), new DiskLruCache$newJournalWriter$faultHidingSink$1(this)));
    }

    private final void readJournalLine(String str) throws IOException {
        String str2;
        CharSequence charSequence = str;
        int indexOf$default = StringsKt.indexOf$default(charSequence, ' ', 0, false, 6, (Object) null);
        if (indexOf$default != -1) {
            int i = indexOf$default + 1;
            int indexOf$default2 = StringsKt.indexOf$default(charSequence, ' ', i, false, 4, (Object) null);
            if (indexOf$default2 == -1) {
                str2 = str.substring(i);
                Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String).substring(startIndex)");
                String str3 = REMOVE;
                if (indexOf$default == str3.length() && StringsKt.startsWith$default(str, str3, false, 2, (Object) null)) {
                    this.lruEntries.remove(str2);
                    return;
                }
            } else {
                str2 = str.substring(i, indexOf$default2);
                Intrinsics.checkNotNullExpressionValue(str2, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            Entry entry = this.lruEntries.get(str2);
            if (entry == null) {
                entry = new Entry(this, str2);
                this.lruEntries.put(str2, entry);
            }
            if (indexOf$default2 != -1) {
                String str4 = CLEAN;
                if (indexOf$default == str4.length() && StringsKt.startsWith$default(str, str4, false, 2, (Object) null)) {
                    String substring = str.substring(indexOf$default2 + 1);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
                    List split$default = StringsKt.split$default((CharSequence) substring, new char[]{' '}, false, 0, 6, (Object) null);
                    entry.setReadable$okhttp(true);
                    entry.setCurrentEditor$okhttp((Editor) null);
                    entry.setLengths$okhttp(split$default);
                    return;
                }
            }
            if (indexOf$default2 == -1) {
                String str5 = DIRTY;
                if (indexOf$default == str5.length() && StringsKt.startsWith$default(str, str5, false, 2, (Object) null)) {
                    entry.setCurrentEditor$okhttp(new Editor(this, entry));
                    return;
                }
            }
            if (indexOf$default2 == -1) {
                String str6 = READ;
                if (indexOf$default == str6.length() && StringsKt.startsWith$default(str, str6, false, 2, (Object) null)) {
                    return;
                }
            }
            throw new IOException("unexpected journal line: " + str);
        }
        throw new IOException("unexpected journal line: " + str);
    }

    private final void processJournal() throws IOException {
        this.fileSystem.delete(this.journalFileTmp);
        Iterator<Entry> it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry next = it.next();
            Intrinsics.checkNotNullExpressionValue(next, "i.next()");
            Entry entry = next;
            int i = 0;
            if (entry.getCurrentEditor$okhttp() == null) {
                int i2 = this.valueCount;
                while (i < i2) {
                    this.size += entry.getLengths$okhttp()[i];
                    i++;
                }
            } else {
                entry.setCurrentEditor$okhttp((Editor) null);
                int i3 = this.valueCount;
                while (i < i3) {
                    this.fileSystem.delete(entry.getCleanFiles$okhttp().get(i));
                    this.fileSystem.delete(entry.getDirtyFiles$okhttp().get(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c5, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00c9, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void rebuildJournal$okhttp() throws java.io.IOException {
        /*
            r7 = this;
            monitor-enter(r7)
            okio.BufferedSink r0 = r7.journalWriter     // Catch:{ all -> 0x00ca }
            if (r0 == 0) goto L_0x0008
            r0.close()     // Catch:{ all -> 0x00ca }
        L_0x0008:
            okhttp3.internal.io.FileSystem r0 = r7.fileSystem     // Catch:{ all -> 0x00ca }
            java.io.File r1 = r7.journalFileTmp     // Catch:{ all -> 0x00ca }
            okio.Sink r0 = r0.sink(r1)     // Catch:{ all -> 0x00ca }
            okio.BufferedSink r0 = okio.Okio.buffer((okio.Sink) r0)     // Catch:{ all -> 0x00ca }
            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch:{ all -> 0x00ca }
            r1 = r0
            okio.BufferedSink r1 = (okio.BufferedSink) r1     // Catch:{ all -> 0x00c3 }
            java.lang.String r2 = MAGIC     // Catch:{ all -> 0x00c3 }
            okio.BufferedSink r2 = r1.writeUtf8(r2)     // Catch:{ all -> 0x00c3 }
            r3 = 10
            r2.writeByte(r3)     // Catch:{ all -> 0x00c3 }
            java.lang.String r2 = VERSION_1     // Catch:{ all -> 0x00c3 }
            okio.BufferedSink r2 = r1.writeUtf8(r2)     // Catch:{ all -> 0x00c3 }
            r2.writeByte(r3)     // Catch:{ all -> 0x00c3 }
            int r2 = r7.appVersion     // Catch:{ all -> 0x00c3 }
            long r4 = (long) r2     // Catch:{ all -> 0x00c3 }
            okio.BufferedSink r2 = r1.writeDecimalLong(r4)     // Catch:{ all -> 0x00c3 }
            r2.writeByte(r3)     // Catch:{ all -> 0x00c3 }
            int r2 = r7.valueCount     // Catch:{ all -> 0x00c3 }
            long r4 = (long) r2     // Catch:{ all -> 0x00c3 }
            okio.BufferedSink r2 = r1.writeDecimalLong(r4)     // Catch:{ all -> 0x00c3 }
            r2.writeByte(r3)     // Catch:{ all -> 0x00c3 }
            r1.writeByte(r3)     // Catch:{ all -> 0x00c3 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r2 = r7.lruEntries     // Catch:{ all -> 0x00c3 }
            java.util.Collection r2 = r2.values()     // Catch:{ all -> 0x00c3 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00c3 }
        L_0x004e:
            boolean r4 = r2.hasNext()     // Catch:{ all -> 0x00c3 }
            if (r4 == 0) goto L_0x008d
            java.lang.Object r4 = r2.next()     // Catch:{ all -> 0x00c3 }
            okhttp3.internal.cache.DiskLruCache$Entry r4 = (okhttp3.internal.cache.DiskLruCache.Entry) r4     // Catch:{ all -> 0x00c3 }
            okhttp3.internal.cache.DiskLruCache$Editor r5 = r4.getCurrentEditor$okhttp()     // Catch:{ all -> 0x00c3 }
            r6 = 32
            if (r5 == 0) goto L_0x0076
            java.lang.String r5 = DIRTY     // Catch:{ all -> 0x00c3 }
            okio.BufferedSink r5 = r1.writeUtf8(r5)     // Catch:{ all -> 0x00c3 }
            r5.writeByte(r6)     // Catch:{ all -> 0x00c3 }
            java.lang.String r4 = r4.getKey$okhttp()     // Catch:{ all -> 0x00c3 }
            r1.writeUtf8(r4)     // Catch:{ all -> 0x00c3 }
            r1.writeByte(r3)     // Catch:{ all -> 0x00c3 }
            goto L_0x004e
        L_0x0076:
            java.lang.String r5 = CLEAN     // Catch:{ all -> 0x00c3 }
            okio.BufferedSink r5 = r1.writeUtf8(r5)     // Catch:{ all -> 0x00c3 }
            r5.writeByte(r6)     // Catch:{ all -> 0x00c3 }
            java.lang.String r5 = r4.getKey$okhttp()     // Catch:{ all -> 0x00c3 }
            r1.writeUtf8(r5)     // Catch:{ all -> 0x00c3 }
            r4.writeLengths$okhttp(r1)     // Catch:{ all -> 0x00c3 }
            r1.writeByte(r3)     // Catch:{ all -> 0x00c3 }
            goto L_0x004e
        L_0x008d:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00c3 }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ all -> 0x00ca }
            okhttp3.internal.io.FileSystem r0 = r7.fileSystem     // Catch:{ all -> 0x00ca }
            java.io.File r1 = r7.journalFile     // Catch:{ all -> 0x00ca }
            boolean r0 = r0.exists(r1)     // Catch:{ all -> 0x00ca }
            if (r0 == 0) goto L_0x00a6
            okhttp3.internal.io.FileSystem r0 = r7.fileSystem     // Catch:{ all -> 0x00ca }
            java.io.File r1 = r7.journalFile     // Catch:{ all -> 0x00ca }
            java.io.File r2 = r7.journalFileBackup     // Catch:{ all -> 0x00ca }
            r0.rename(r1, r2)     // Catch:{ all -> 0x00ca }
        L_0x00a6:
            okhttp3.internal.io.FileSystem r0 = r7.fileSystem     // Catch:{ all -> 0x00ca }
            java.io.File r1 = r7.journalFileTmp     // Catch:{ all -> 0x00ca }
            java.io.File r2 = r7.journalFile     // Catch:{ all -> 0x00ca }
            r0.rename(r1, r2)     // Catch:{ all -> 0x00ca }
            okhttp3.internal.io.FileSystem r0 = r7.fileSystem     // Catch:{ all -> 0x00ca }
            java.io.File r1 = r7.journalFileBackup     // Catch:{ all -> 0x00ca }
            r0.delete(r1)     // Catch:{ all -> 0x00ca }
            okio.BufferedSink r0 = r7.newJournalWriter()     // Catch:{ all -> 0x00ca }
            r7.journalWriter = r0     // Catch:{ all -> 0x00ca }
            r0 = 0
            r7.hasJournalErrors = r0     // Catch:{ all -> 0x00ca }
            r7.mostRecentRebuildFailed = r0     // Catch:{ all -> 0x00ca }
            monitor-exit(r7)
            return
        L_0x00c3:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x00c5 }
        L_0x00c5:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ all -> 0x00ca }
            throw r2     // Catch:{ all -> 0x00ca }
        L_0x00ca:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.rebuildJournal$okhttp():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0059, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized okhttp3.internal.cache.DiskLruCache.Snapshot get(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            monitor-enter(r7)
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)     // Catch:{ all -> 0x005a }
            r7.initialize()     // Catch:{ all -> 0x005a }
            r7.checkNotClosed()     // Catch:{ all -> 0x005a }
            r7.validateKey(r8)     // Catch:{ all -> 0x005a }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r7.lruEntries     // Catch:{ all -> 0x005a }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x005a }
            okhttp3.internal.cache.DiskLruCache$Entry r0 = (okhttp3.internal.cache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x005a }
            r1 = 0
            if (r0 != 0) goto L_0x001c
            monitor-exit(r7)
            return r1
        L_0x001c:
            okhttp3.internal.cache.DiskLruCache$Snapshot r0 = r0.snapshot$okhttp()     // Catch:{ all -> 0x005a }
            if (r0 != 0) goto L_0x0024
            monitor-exit(r7)
            return r1
        L_0x0024:
            int r1 = r7.redundantOpCount     // Catch:{ all -> 0x005a }
            int r1 = r1 + 1
            r7.redundantOpCount = r1     // Catch:{ all -> 0x005a }
            okio.BufferedSink r1 = r7.journalWriter     // Catch:{ all -> 0x005a }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x005a }
            java.lang.String r2 = READ     // Catch:{ all -> 0x005a }
            okio.BufferedSink r1 = r1.writeUtf8(r2)     // Catch:{ all -> 0x005a }
            r2 = 32
            okio.BufferedSink r1 = r1.writeByte(r2)     // Catch:{ all -> 0x005a }
            okio.BufferedSink r8 = r1.writeUtf8(r8)     // Catch:{ all -> 0x005a }
            r1 = 10
            r8.writeByte(r1)     // Catch:{ all -> 0x005a }
            boolean r8 = r7.journalRebuildRequired()     // Catch:{ all -> 0x005a }
            if (r8 == 0) goto L_0x0058
            okhttp3.internal.concurrent.TaskQueue r1 = r7.cleanupQueue     // Catch:{ all -> 0x005a }
            okhttp3.internal.cache.DiskLruCache$cleanupTask$1 r8 = r7.cleanupTask     // Catch:{ all -> 0x005a }
            r2 = r8
            okhttp3.internal.concurrent.Task r2 = (okhttp3.internal.concurrent.Task) r2     // Catch:{ all -> 0x005a }
            r3 = 0
            r5 = 2
            r6 = 0
            okhttp3.internal.concurrent.TaskQueue.schedule$default(r1, r2, r3, r5, r6)     // Catch:{ all -> 0x005a }
        L_0x0058:
            monitor-exit(r7)
            return r0
        L_0x005a:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.get(java.lang.String):okhttp3.internal.cache.DiskLruCache$Snapshot");
    }

    public static /* synthetic */ Editor edit$default(DiskLruCache diskLruCache, String str, long j, int i, Object obj) throws IOException {
        if ((i & 2) != 0) {
            j = ANY_SEQUENCE_NUMBER;
        }
        return diskLruCache.edit(str, j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized okhttp3.internal.cache.DiskLruCache.Editor edit(java.lang.String r10, long r11) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)     // Catch:{ all -> 0x0094 }
            r9.initialize()     // Catch:{ all -> 0x0094 }
            r9.checkNotClosed()     // Catch:{ all -> 0x0094 }
            r9.validateKey(r10)     // Catch:{ all -> 0x0094 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r9.lruEntries     // Catch:{ all -> 0x0094 }
            java.lang.Object r0 = r0.get(r10)     // Catch:{ all -> 0x0094 }
            okhttp3.internal.cache.DiskLruCache$Entry r0 = (okhttp3.internal.cache.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0094 }
            long r1 = ANY_SEQUENCE_NUMBER     // Catch:{ all -> 0x0094 }
            int r1 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            r2 = 0
            if (r1 == 0) goto L_0x002a
            if (r0 == 0) goto L_0x0028
            long r3 = r0.getSequenceNumber$okhttp()     // Catch:{ all -> 0x0094 }
            int r11 = (r3 > r11 ? 1 : (r3 == r11 ? 0 : -1))
            if (r11 == 0) goto L_0x002a
        L_0x0028:
            monitor-exit(r9)
            return r2
        L_0x002a:
            if (r0 == 0) goto L_0x0031
            okhttp3.internal.cache.DiskLruCache$Editor r11 = r0.getCurrentEditor$okhttp()     // Catch:{ all -> 0x0094 }
            goto L_0x0032
        L_0x0031:
            r11 = r2
        L_0x0032:
            if (r11 == 0) goto L_0x0036
            monitor-exit(r9)
            return r2
        L_0x0036:
            if (r0 == 0) goto L_0x0040
            int r11 = r0.getLockingSourceCount$okhttp()     // Catch:{ all -> 0x0094 }
            if (r11 == 0) goto L_0x0040
            monitor-exit(r9)
            return r2
        L_0x0040:
            boolean r11 = r9.mostRecentTrimFailed     // Catch:{ all -> 0x0094 }
            if (r11 != 0) goto L_0x0084
            boolean r11 = r9.mostRecentRebuildFailed     // Catch:{ all -> 0x0094 }
            if (r11 == 0) goto L_0x0049
            goto L_0x0084
        L_0x0049:
            okio.BufferedSink r11 = r9.journalWriter     // Catch:{ all -> 0x0094 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)     // Catch:{ all -> 0x0094 }
            java.lang.String r12 = DIRTY     // Catch:{ all -> 0x0094 }
            okio.BufferedSink r12 = r11.writeUtf8(r12)     // Catch:{ all -> 0x0094 }
            r1 = 32
            okio.BufferedSink r12 = r12.writeByte(r1)     // Catch:{ all -> 0x0094 }
            okio.BufferedSink r12 = r12.writeUtf8(r10)     // Catch:{ all -> 0x0094 }
            r1 = 10
            r12.writeByte(r1)     // Catch:{ all -> 0x0094 }
            r11.flush()     // Catch:{ all -> 0x0094 }
            boolean r11 = r9.hasJournalErrors     // Catch:{ all -> 0x0094 }
            if (r11 == 0) goto L_0x006c
            monitor-exit(r9)
            return r2
        L_0x006c:
            if (r0 != 0) goto L_0x007a
            okhttp3.internal.cache.DiskLruCache$Entry r0 = new okhttp3.internal.cache.DiskLruCache$Entry     // Catch:{ all -> 0x0094 }
            r0.<init>(r9, r10)     // Catch:{ all -> 0x0094 }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r11 = r9.lruEntries     // Catch:{ all -> 0x0094 }
            java.util.Map r11 = (java.util.Map) r11     // Catch:{ all -> 0x0094 }
            r11.put(r10, r0)     // Catch:{ all -> 0x0094 }
        L_0x007a:
            okhttp3.internal.cache.DiskLruCache$Editor r10 = new okhttp3.internal.cache.DiskLruCache$Editor     // Catch:{ all -> 0x0094 }
            r10.<init>(r9, r0)     // Catch:{ all -> 0x0094 }
            r0.setCurrentEditor$okhttp(r10)     // Catch:{ all -> 0x0094 }
            monitor-exit(r9)
            return r10
        L_0x0084:
            okhttp3.internal.concurrent.TaskQueue r3 = r9.cleanupQueue     // Catch:{ all -> 0x0094 }
            okhttp3.internal.cache.DiskLruCache$cleanupTask$1 r10 = r9.cleanupTask     // Catch:{ all -> 0x0094 }
            r4 = r10
            okhttp3.internal.concurrent.Task r4 = (okhttp3.internal.concurrent.Task) r4     // Catch:{ all -> 0x0094 }
            r5 = 0
            r7 = 2
            r8 = 0
            okhttp3.internal.concurrent.TaskQueue.schedule$default(r3, r4, r5, r7, r8)     // Catch:{ all -> 0x0094 }
            monitor-exit(r9)
            return r2
        L_0x0094:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.edit(java.lang.String, long):okhttp3.internal.cache.DiskLruCache$Editor");
    }

    public final synchronized long size() throws IOException {
        initialize();
        return this.size;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:53:0x013a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void completeEdit$okhttp(okhttp3.internal.cache.DiskLruCache.Editor r9, boolean r10) throws java.io.IOException {
        /*
            r8 = this;
            monitor-enter(r8)
            java.lang.String r0 = "editor"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)     // Catch:{ all -> 0x0147 }
            okhttp3.internal.cache.DiskLruCache$Entry r0 = r9.getEntry$okhttp()     // Catch:{ all -> 0x0147 }
            okhttp3.internal.cache.DiskLruCache$Editor r1 = r0.getCurrentEditor$okhttp()     // Catch:{ all -> 0x0147 }
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r9)     // Catch:{ all -> 0x0147 }
            if (r1 == 0) goto L_0x013b
            r1 = 0
            if (r10 == 0) goto L_0x0063
            boolean r2 = r0.getReadable$okhttp()     // Catch:{ all -> 0x0147 }
            if (r2 != 0) goto L_0x0063
            int r2 = r8.valueCount     // Catch:{ all -> 0x0147 }
            r3 = r1
        L_0x0020:
            if (r3 >= r2) goto L_0x0063
            boolean[] r4 = r9.getWritten$okhttp()     // Catch:{ all -> 0x0147 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x0147 }
            boolean r4 = r4[r3]     // Catch:{ all -> 0x0147 }
            if (r4 == 0) goto L_0x0047
            okhttp3.internal.io.FileSystem r4 = r8.fileSystem     // Catch:{ all -> 0x0147 }
            java.util.List r5 = r0.getDirtyFiles$okhttp()     // Catch:{ all -> 0x0147 }
            java.lang.Object r5 = r5.get(r3)     // Catch:{ all -> 0x0147 }
            java.io.File r5 = (java.io.File) r5     // Catch:{ all -> 0x0147 }
            boolean r4 = r4.exists(r5)     // Catch:{ all -> 0x0147 }
            if (r4 != 0) goto L_0x0044
            r9.abort()     // Catch:{ all -> 0x0147 }
            monitor-exit(r8)
            return
        L_0x0044:
            int r3 = r3 + 1
            goto L_0x0020
        L_0x0047:
            r9.abort()     // Catch:{ all -> 0x0147 }
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0147 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0147 }
            r10.<init>()     // Catch:{ all -> 0x0147 }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            java.lang.StringBuilder r10 = r10.append(r0)     // Catch:{ all -> 0x0147 }
            java.lang.StringBuilder r10 = r10.append(r3)     // Catch:{ all -> 0x0147 }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x0147 }
            r9.<init>(r10)     // Catch:{ all -> 0x0147 }
            throw r9     // Catch:{ all -> 0x0147 }
        L_0x0063:
            int r9 = r8.valueCount     // Catch:{ all -> 0x0147 }
        L_0x0065:
            if (r1 >= r9) goto L_0x00b1
            java.util.List r2 = r0.getDirtyFiles$okhttp()     // Catch:{ all -> 0x0147 }
            java.lang.Object r2 = r2.get(r1)     // Catch:{ all -> 0x0147 }
            java.io.File r2 = (java.io.File) r2     // Catch:{ all -> 0x0147 }
            if (r10 == 0) goto L_0x00a9
            boolean r3 = r0.getZombie$okhttp()     // Catch:{ all -> 0x0147 }
            if (r3 != 0) goto L_0x00a9
            okhttp3.internal.io.FileSystem r3 = r8.fileSystem     // Catch:{ all -> 0x0147 }
            boolean r3 = r3.exists(r2)     // Catch:{ all -> 0x0147 }
            if (r3 == 0) goto L_0x00ae
            java.util.List r3 = r0.getCleanFiles$okhttp()     // Catch:{ all -> 0x0147 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ all -> 0x0147 }
            java.io.File r3 = (java.io.File) r3     // Catch:{ all -> 0x0147 }
            okhttp3.internal.io.FileSystem r4 = r8.fileSystem     // Catch:{ all -> 0x0147 }
            r4.rename(r2, r3)     // Catch:{ all -> 0x0147 }
            long[] r2 = r0.getLengths$okhttp()     // Catch:{ all -> 0x0147 }
            r4 = r2[r1]     // Catch:{ all -> 0x0147 }
            okhttp3.internal.io.FileSystem r2 = r8.fileSystem     // Catch:{ all -> 0x0147 }
            long r2 = r2.size(r3)     // Catch:{ all -> 0x0147 }
            long[] r6 = r0.getLengths$okhttp()     // Catch:{ all -> 0x0147 }
            r6[r1] = r2     // Catch:{ all -> 0x0147 }
            long r6 = r8.size     // Catch:{ all -> 0x0147 }
            long r6 = r6 - r4
            long r6 = r6 + r2
            r8.size = r6     // Catch:{ all -> 0x0147 }
            goto L_0x00ae
        L_0x00a9:
            okhttp3.internal.io.FileSystem r3 = r8.fileSystem     // Catch:{ all -> 0x0147 }
            r3.delete(r2)     // Catch:{ all -> 0x0147 }
        L_0x00ae:
            int r1 = r1 + 1
            goto L_0x0065
        L_0x00b1:
            r9 = 0
            r0.setCurrentEditor$okhttp(r9)     // Catch:{ all -> 0x0147 }
            boolean r9 = r0.getZombie$okhttp()     // Catch:{ all -> 0x0147 }
            if (r9 == 0) goto L_0x00c0
            r8.removeEntry$okhttp(r0)     // Catch:{ all -> 0x0147 }
            monitor-exit(r8)
            return
        L_0x00c0:
            int r9 = r8.redundantOpCount     // Catch:{ all -> 0x0147 }
            r1 = 1
            int r9 = r9 + r1
            r8.redundantOpCount = r9     // Catch:{ all -> 0x0147 }
            okio.BufferedSink r9 = r8.journalWriter     // Catch:{ all -> 0x0147 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)     // Catch:{ all -> 0x0147 }
            boolean r2 = r0.getReadable$okhttp()     // Catch:{ all -> 0x0147 }
            r3 = 10
            r4 = 32
            if (r2 != 0) goto L_0x00f5
            if (r10 == 0) goto L_0x00d8
            goto L_0x00f5
        L_0x00d8:
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r10 = r8.lruEntries     // Catch:{ all -> 0x0147 }
            java.lang.String r1 = r0.getKey$okhttp()     // Catch:{ all -> 0x0147 }
            r10.remove(r1)     // Catch:{ all -> 0x0147 }
            java.lang.String r10 = REMOVE     // Catch:{ all -> 0x0147 }
            okio.BufferedSink r10 = r9.writeUtf8(r10)     // Catch:{ all -> 0x0147 }
            r10.writeByte(r4)     // Catch:{ all -> 0x0147 }
            java.lang.String r10 = r0.getKey$okhttp()     // Catch:{ all -> 0x0147 }
            r9.writeUtf8(r10)     // Catch:{ all -> 0x0147 }
            r9.writeByte(r3)     // Catch:{ all -> 0x0147 }
            goto L_0x011a
        L_0x00f5:
            r0.setReadable$okhttp(r1)     // Catch:{ all -> 0x0147 }
            java.lang.String r1 = CLEAN     // Catch:{ all -> 0x0147 }
            okio.BufferedSink r1 = r9.writeUtf8(r1)     // Catch:{ all -> 0x0147 }
            r1.writeByte(r4)     // Catch:{ all -> 0x0147 }
            java.lang.String r1 = r0.getKey$okhttp()     // Catch:{ all -> 0x0147 }
            r9.writeUtf8(r1)     // Catch:{ all -> 0x0147 }
            r0.writeLengths$okhttp(r9)     // Catch:{ all -> 0x0147 }
            r9.writeByte(r3)     // Catch:{ all -> 0x0147 }
            if (r10 == 0) goto L_0x011a
            long r1 = r8.nextSequenceNumber     // Catch:{ all -> 0x0147 }
            r3 = 1
            long r3 = r3 + r1
            r8.nextSequenceNumber = r3     // Catch:{ all -> 0x0147 }
            r0.setSequenceNumber$okhttp(r1)     // Catch:{ all -> 0x0147 }
        L_0x011a:
            r9.flush()     // Catch:{ all -> 0x0147 }
            long r9 = r8.size     // Catch:{ all -> 0x0147 }
            long r0 = r8.maxSize     // Catch:{ all -> 0x0147 }
            int r9 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r9 > 0) goto L_0x012b
            boolean r9 = r8.journalRebuildRequired()     // Catch:{ all -> 0x0147 }
            if (r9 == 0) goto L_0x0139
        L_0x012b:
            okhttp3.internal.concurrent.TaskQueue r0 = r8.cleanupQueue     // Catch:{ all -> 0x0147 }
            okhttp3.internal.cache.DiskLruCache$cleanupTask$1 r9 = r8.cleanupTask     // Catch:{ all -> 0x0147 }
            r1 = r9
            okhttp3.internal.concurrent.Task r1 = (okhttp3.internal.concurrent.Task) r1     // Catch:{ all -> 0x0147 }
            r2 = 0
            r4 = 2
            r5 = 0
            okhttp3.internal.concurrent.TaskQueue.schedule$default(r0, r1, r2, r4, r5)     // Catch:{ all -> 0x0147 }
        L_0x0139:
            monitor-exit(r8)
            return
        L_0x013b:
            java.lang.String r9 = "Check failed."
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0147 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0147 }
            r10.<init>(r9)     // Catch:{ all -> 0x0147 }
            throw r10     // Catch:{ all -> 0x0147 }
        L_0x0147:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.completeEdit$okhttp(okhttp3.internal.cache.DiskLruCache$Editor, boolean):void");
    }

    /* access modifiers changed from: private */
    public final boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean remove(java.lang.String r6) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)     // Catch:{ all -> 0x002e }
            r5.initialize()     // Catch:{ all -> 0x002e }
            r5.checkNotClosed()     // Catch:{ all -> 0x002e }
            r5.validateKey(r6)     // Catch:{ all -> 0x002e }
            java.util.LinkedHashMap<java.lang.String, okhttp3.internal.cache.DiskLruCache$Entry> r0 = r5.lruEntries     // Catch:{ all -> 0x002e }
            java.lang.Object r6 = r0.get(r6)     // Catch:{ all -> 0x002e }
            okhttp3.internal.cache.DiskLruCache$Entry r6 = (okhttp3.internal.cache.DiskLruCache.Entry) r6     // Catch:{ all -> 0x002e }
            r0 = 0
            if (r6 != 0) goto L_0x001c
            monitor-exit(r5)
            return r0
        L_0x001c:
            boolean r6 = r5.removeEntry$okhttp(r6)     // Catch:{ all -> 0x002e }
            if (r6 == 0) goto L_0x002c
            long r1 = r5.size     // Catch:{ all -> 0x002e }
            long r3 = r5.maxSize     // Catch:{ all -> 0x002e }
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 > 0) goto L_0x002c
            r5.mostRecentTrimFailed = r0     // Catch:{ all -> 0x002e }
        L_0x002c:
            monitor-exit(r5)
            return r6
        L_0x002e:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.remove(java.lang.String):boolean");
    }

    public final boolean removeEntry$okhttp(Entry entry) throws IOException {
        BufferedSink bufferedSink;
        Intrinsics.checkNotNullParameter(entry, "entry");
        if (!this.civilizedFileSystem) {
            if (entry.getLockingSourceCount$okhttp() > 0 && (bufferedSink = this.journalWriter) != null) {
                bufferedSink.writeUtf8(DIRTY);
                bufferedSink.writeByte(32);
                bufferedSink.writeUtf8(entry.getKey$okhttp());
                bufferedSink.writeByte(10);
                bufferedSink.flush();
            }
            if (entry.getLockingSourceCount$okhttp() > 0 || entry.getCurrentEditor$okhttp() != null) {
                entry.setZombie$okhttp(true);
                return true;
            }
        }
        Editor currentEditor$okhttp = entry.getCurrentEditor$okhttp();
        if (currentEditor$okhttp != null) {
            currentEditor$okhttp.detach$okhttp();
        }
        int i = this.valueCount;
        for (int i2 = 0; i2 < i; i2++) {
            this.fileSystem.delete(entry.getCleanFiles$okhttp().get(i2));
            this.size -= entry.getLengths$okhttp()[i2];
            entry.getLengths$okhttp()[i2] = 0;
        }
        this.redundantOpCount++;
        BufferedSink bufferedSink2 = this.journalWriter;
        if (bufferedSink2 != null) {
            bufferedSink2.writeUtf8(REMOVE);
            bufferedSink2.writeByte(32);
            bufferedSink2.writeUtf8(entry.getKey$okhttp());
            bufferedSink2.writeByte(10);
        }
        this.lruEntries.remove(entry.getKey$okhttp());
        if (journalRebuildRequired()) {
            TaskQueue.schedule$default(this.cleanupQueue, this.cleanupTask, 0, 2, (Object) null);
        }
        return true;
    }

    private final synchronized void checkNotClosed() {
        if (!(!this.closed)) {
            throw new IllegalStateException("cache is closed".toString());
        }
    }

    public synchronized void flush() throws IOException {
        if (this.initialized) {
            checkNotClosed();
            trimToSize();
            BufferedSink bufferedSink = this.journalWriter;
            Intrinsics.checkNotNull(bufferedSink);
            bufferedSink.flush();
        }
    }

    public final synchronized boolean isClosed() {
        return this.closed;
    }

    public synchronized void close() throws IOException {
        Editor currentEditor$okhttp;
        if (this.initialized) {
            if (!this.closed) {
                Collection<Entry> values = this.lruEntries.values();
                Intrinsics.checkNotNullExpressionValue(values, "lruEntries.values");
                for (Entry entry : (Entry[]) values.toArray(new Entry[0])) {
                    if (!(entry.getCurrentEditor$okhttp() == null || (currentEditor$okhttp = entry.getCurrentEditor$okhttp()) == null)) {
                        currentEditor$okhttp.detach$okhttp();
                    }
                }
                trimToSize();
                BufferedSink bufferedSink = this.journalWriter;
                Intrinsics.checkNotNull(bufferedSink);
                bufferedSink.close();
                this.journalWriter = null;
                this.closed = true;
                return;
            }
        }
        this.closed = true;
    }

    public final void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            if (!removeOldestEntry()) {
                return;
            }
        }
        this.mostRecentTrimFailed = false;
    }

    private final boolean removeOldestEntry() {
        for (Entry next : this.lruEntries.values()) {
            if (!next.getZombie$okhttp()) {
                Intrinsics.checkNotNullExpressionValue(next, "toEvict");
                removeEntry$okhttp(next);
                return true;
            }
        }
        return false;
    }

    public final void delete() throws IOException {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    public final synchronized void evictAll() throws IOException {
        initialize();
        Collection<Entry> values = this.lruEntries.values();
        Intrinsics.checkNotNullExpressionValue(values, "lruEntries.values");
        for (Entry entry : (Entry[]) values.toArray(new Entry[0])) {
            Intrinsics.checkNotNullExpressionValue(entry, "entry");
            removeEntry$okhttp(entry);
        }
        this.mostRecentTrimFailed = false;
    }

    private final void validateKey(String str) {
        if (!LEGAL_KEY_PATTERN.matches(str)) {
            throw new IllegalArgumentException(("keys must match regex [a-z0-9_-]{1,120}: \"" + str + '\"').toString());
        }
    }

    public final synchronized Iterator<Snapshot> snapshots() throws IOException {
        initialize();
        return new DiskLruCache$snapshots$1(this);
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B-\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\f\u0010\u000e\u001a\b\u0018\u00010\u000fR\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0002\u001a\u00020\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "Ljava/io/Closeable;", "key", "", "sequenceNumber", "", "sources", "", "Lokio/Source;", "lengths", "", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;JLjava/util/List;[J)V", "close", "", "edit", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getLength", "index", "", "getSource", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DiskLruCache.kt */
    public final class Snapshot implements Closeable {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final List<Source> sources;
        final /* synthetic */ DiskLruCache this$0;

        public Snapshot(DiskLruCache diskLruCache, String str, long j, List<? extends Source> list, long[] jArr) {
            Intrinsics.checkNotNullParameter(str, "key");
            Intrinsics.checkNotNullParameter(list, "sources");
            Intrinsics.checkNotNullParameter(jArr, "lengths");
            this.this$0 = diskLruCache;
            this.key = str;
            this.sequenceNumber = j;
            this.sources = list;
            this.lengths = jArr;
        }

        public final String key() {
            return this.key;
        }

        public final Editor edit() throws IOException {
            return this.this$0.edit(this.key, this.sequenceNumber);
        }

        public final Source getSource(int i) {
            return this.sources.get(i);
        }

        public final long getLength(int i) {
            return this.lengths[i];
        }

        public void close() {
            for (Source closeQuietly : this.sources) {
                Util.closeQuietly((Closeable) closeQuietly);
            }
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0013\b\u0000\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\r\u0010\u0011\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0012J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0002\u001a\u00060\u0003R\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Editor;", "", "entry", "Lokhttp3/internal/cache/DiskLruCache$Entry;", "Lokhttp3/internal/cache/DiskLruCache;", "(Lokhttp3/internal/cache/DiskLruCache;Lokhttp3/internal/cache/DiskLruCache$Entry;)V", "done", "", "getEntry$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Entry;", "written", "", "getWritten$okhttp", "()[Z", "abort", "", "commit", "detach", "detach$okhttp", "newSink", "Lokio/Sink;", "index", "", "newSource", "Lokio/Source;", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DiskLruCache.kt */
    public final class Editor {
        private boolean done;
        private final Entry entry;
        final /* synthetic */ DiskLruCache this$0;
        private final boolean[] written;

        public Editor(DiskLruCache diskLruCache, Entry entry2) {
            Intrinsics.checkNotNullParameter(entry2, "entry");
            this.this$0 = diskLruCache;
            this.entry = entry2;
            this.written = entry2.getReadable$okhttp() ? null : new boolean[diskLruCache.getValueCount$okhttp()];
        }

        public final Entry getEntry$okhttp() {
            return this.entry;
        }

        public final boolean[] getWritten$okhttp() {
            return this.written;
        }

        public final void detach$okhttp() {
            if (!Intrinsics.areEqual((Object) this.entry.getCurrentEditor$okhttp(), (Object) this)) {
                return;
            }
            if (this.this$0.civilizedFileSystem) {
                this.this$0.completeEdit$okhttp(this, false);
            } else {
                this.entry.setZombie$okhttp(true);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x003e, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final okio.Source newSource(int r5) {
            /*
                r4 = this;
                okhttp3.internal.cache.DiskLruCache r0 = r4.this$0
                monitor-enter(r0)
                boolean r1 = r4.done     // Catch:{ all -> 0x004b }
                r1 = r1 ^ 1
                if (r1 == 0) goto L_0x003f
                okhttp3.internal.cache.DiskLruCache$Entry r1 = r4.entry     // Catch:{ all -> 0x004b }
                boolean r1 = r1.getReadable$okhttp()     // Catch:{ all -> 0x004b }
                r2 = 0
                if (r1 == 0) goto L_0x003d
                okhttp3.internal.cache.DiskLruCache$Entry r1 = r4.entry     // Catch:{ all -> 0x004b }
                okhttp3.internal.cache.DiskLruCache$Editor r1 = r1.getCurrentEditor$okhttp()     // Catch:{ all -> 0x004b }
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r4)     // Catch:{ all -> 0x004b }
                if (r1 == 0) goto L_0x003d
                okhttp3.internal.cache.DiskLruCache$Entry r1 = r4.entry     // Catch:{ all -> 0x004b }
                boolean r1 = r1.getZombie$okhttp()     // Catch:{ all -> 0x004b }
                if (r1 == 0) goto L_0x0027
                goto L_0x003d
            L_0x0027:
                okhttp3.internal.io.FileSystem r1 = r0.getFileSystem$okhttp()     // Catch:{ FileNotFoundException -> 0x003b }
                okhttp3.internal.cache.DiskLruCache$Entry r3 = r4.entry     // Catch:{ FileNotFoundException -> 0x003b }
                java.util.List r3 = r3.getCleanFiles$okhttp()     // Catch:{ FileNotFoundException -> 0x003b }
                java.lang.Object r5 = r3.get(r5)     // Catch:{ FileNotFoundException -> 0x003b }
                java.io.File r5 = (java.io.File) r5     // Catch:{ FileNotFoundException -> 0x003b }
                okio.Source r2 = r1.source(r5)     // Catch:{ FileNotFoundException -> 0x003b }
            L_0x003b:
                monitor-exit(r0)
                return r2
            L_0x003d:
                monitor-exit(r0)
                return r2
            L_0x003f:
                java.lang.String r5 = "Check failed."
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x004b }
                java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x004b }
                r1.<init>(r5)     // Catch:{ all -> 0x004b }
                throw r1     // Catch:{ all -> 0x004b }
            L_0x004b:
                r5 = move-exception
                monitor-exit(r0)
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.Editor.newSource(int):okio.Source");
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(4:21|22|23|24) */
        /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
            r4 = okio.Okio.blackhole();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0053, code lost:
            return r4;
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x004e */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final okio.Sink newSink(int r4) {
            /*
                r3 = this;
                okhttp3.internal.cache.DiskLruCache r0 = r3.this$0
                monitor-enter(r0)
                boolean r1 = r3.done     // Catch:{ all -> 0x0060 }
                r2 = 1
                r1 = r1 ^ r2
                if (r1 == 0) goto L_0x0054
                okhttp3.internal.cache.DiskLruCache$Entry r1 = r3.entry     // Catch:{ all -> 0x0060 }
                okhttp3.internal.cache.DiskLruCache$Editor r1 = r1.getCurrentEditor$okhttp()     // Catch:{ all -> 0x0060 }
                boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r3)     // Catch:{ all -> 0x0060 }
                if (r1 != 0) goto L_0x001b
                okio.Sink r4 = okio.Okio.blackhole()     // Catch:{ all -> 0x0060 }
                monitor-exit(r0)
                return r4
            L_0x001b:
                okhttp3.internal.cache.DiskLruCache$Entry r1 = r3.entry     // Catch:{ all -> 0x0060 }
                boolean r1 = r1.getReadable$okhttp()     // Catch:{ all -> 0x0060 }
                if (r1 != 0) goto L_0x002a
                boolean[] r1 = r3.written     // Catch:{ all -> 0x0060 }
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x0060 }
                r1[r4] = r2     // Catch:{ all -> 0x0060 }
            L_0x002a:
                okhttp3.internal.cache.DiskLruCache$Entry r1 = r3.entry     // Catch:{ all -> 0x0060 }
                java.util.List r1 = r1.getDirtyFiles$okhttp()     // Catch:{ all -> 0x0060 }
                java.lang.Object r4 = r1.get(r4)     // Catch:{ all -> 0x0060 }
                java.io.File r4 = (java.io.File) r4     // Catch:{ all -> 0x0060 }
                okhttp3.internal.io.FileSystem r1 = r0.getFileSystem$okhttp()     // Catch:{ FileNotFoundException -> 0x004e }
                okio.Sink r4 = r1.sink(r4)     // Catch:{ FileNotFoundException -> 0x004e }
                okhttp3.internal.cache.FaultHidingSink r1 = new okhttp3.internal.cache.FaultHidingSink     // Catch:{ all -> 0x0060 }
                okhttp3.internal.cache.DiskLruCache$Editor$newSink$1$1 r2 = new okhttp3.internal.cache.DiskLruCache$Editor$newSink$1$1     // Catch:{ all -> 0x0060 }
                r2.<init>(r0, r3)     // Catch:{ all -> 0x0060 }
                kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2     // Catch:{ all -> 0x0060 }
                r1.<init>(r4, r2)     // Catch:{ all -> 0x0060 }
                okio.Sink r1 = (okio.Sink) r1     // Catch:{ all -> 0x0060 }
                monitor-exit(r0)
                return r1
            L_0x004e:
                okio.Sink r4 = okio.Okio.blackhole()     // Catch:{ all -> 0x0060 }
                monitor-exit(r0)
                return r4
            L_0x0054:
                java.lang.String r4 = "Check failed."
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0060 }
                java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0060 }
                r1.<init>(r4)     // Catch:{ all -> 0x0060 }
                throw r1     // Catch:{ all -> 0x0060 }
            L_0x0060:
                r4 = move-exception
                monitor-exit(r0)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.cache.DiskLruCache.Editor.newSink(int):okio.Sink");
        }

        public final void commit() throws IOException {
            DiskLruCache diskLruCache = this.this$0;
            synchronized (diskLruCache) {
                if (!this.done) {
                    if (Intrinsics.areEqual((Object) this.entry.getCurrentEditor$okhttp(), (Object) this)) {
                        diskLruCache.completeEdit$okhttp(this, true);
                    }
                    this.done = true;
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }

        public final void abort() throws IOException {
            DiskLruCache diskLruCache = this.this$0;
            synchronized (diskLruCache) {
                if (!this.done) {
                    if (Intrinsics.areEqual((Object) this.entry.getCurrentEditor$okhttp(), (Object) this)) {
                        diskLruCache.completeEdit$okhttp(this, false);
                    }
                    this.done = true;
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }
    }

    @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010.\u001a\u00020/2\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u000301H\u0002J\u0010\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\u001aH\u0002J\u001b\u00105\u001a\u0002062\f\u00100\u001a\b\u0012\u0004\u0012\u00020\u000301H\u0000¢\u0006\u0002\b7J\u0013\u00108\u001a\b\u0018\u000109R\u00020\fH\u0000¢\u0006\u0002\b:J\u0015\u0010;\u001a\u0002062\u0006\u0010<\u001a\u00020=H\u0000¢\u0006\u0002\b>R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0018\u00010\u000bR\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\tR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\"\"\u0004\b-\u0010$¨\u0006?"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Entry;", "", "key", "", "(Lokhttp3/internal/cache/DiskLruCache;Ljava/lang/String;)V", "cleanFiles", "", "Ljava/io/File;", "getCleanFiles$okhttp", "()Ljava/util/List;", "currentEditor", "Lokhttp3/internal/cache/DiskLruCache$Editor;", "Lokhttp3/internal/cache/DiskLruCache;", "getCurrentEditor$okhttp", "()Lokhttp3/internal/cache/DiskLruCache$Editor;", "setCurrentEditor$okhttp", "(Lokhttp3/internal/cache/DiskLruCache$Editor;)V", "dirtyFiles", "getDirtyFiles$okhttp", "getKey$okhttp", "()Ljava/lang/String;", "lengths", "", "getLengths$okhttp", "()[J", "lockingSourceCount", "", "getLockingSourceCount$okhttp", "()I", "setLockingSourceCount$okhttp", "(I)V", "readable", "", "getReadable$okhttp", "()Z", "setReadable$okhttp", "(Z)V", "sequenceNumber", "", "getSequenceNumber$okhttp", "()J", "setSequenceNumber$okhttp", "(J)V", "zombie", "getZombie$okhttp", "setZombie$okhttp", "invalidLengths", "", "strings", "", "newSource", "Lokio/Source;", "index", "setLengths", "", "setLengths$okhttp", "snapshot", "Lokhttp3/internal/cache/DiskLruCache$Snapshot;", "snapshot$okhttp", "writeLengths", "writer", "Lokio/BufferedSink;", "writeLengths$okhttp", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DiskLruCache.kt */
    public final class Entry {
        private final List<File> cleanFiles = new ArrayList();
        private Editor currentEditor;
        private final List<File> dirtyFiles = new ArrayList();
        private final String key;
        private final long[] lengths;
        private int lockingSourceCount;
        private boolean readable;
        private long sequenceNumber;
        final /* synthetic */ DiskLruCache this$0;
        private boolean zombie;

        public Entry(DiskLruCache diskLruCache, String str) {
            Intrinsics.checkNotNullParameter(str, "key");
            this.this$0 = diskLruCache;
            this.key = str;
            this.lengths = new long[diskLruCache.getValueCount$okhttp()];
            StringBuilder append = new StringBuilder(str).append(FilenameUtils.EXTENSION_SEPARATOR);
            int length = append.length();
            int valueCount$okhttp = diskLruCache.getValueCount$okhttp();
            for (int i = 0; i < valueCount$okhttp; i++) {
                append.append(i);
                this.cleanFiles.add(new File(this.this$0.getDirectory(), append.toString()));
                append.append(DefaultDiskStorage.FileType.TEMP);
                this.dirtyFiles.add(new File(this.this$0.getDirectory(), append.toString()));
                append.setLength(length);
            }
        }

        public final String getKey$okhttp() {
            return this.key;
        }

        public final long[] getLengths$okhttp() {
            return this.lengths;
        }

        public final List<File> getCleanFiles$okhttp() {
            return this.cleanFiles;
        }

        public final List<File> getDirtyFiles$okhttp() {
            return this.dirtyFiles;
        }

        public final boolean getReadable$okhttp() {
            return this.readable;
        }

        public final void setReadable$okhttp(boolean z) {
            this.readable = z;
        }

        public final boolean getZombie$okhttp() {
            return this.zombie;
        }

        public final void setZombie$okhttp(boolean z) {
            this.zombie = z;
        }

        public final Editor getCurrentEditor$okhttp() {
            return this.currentEditor;
        }

        public final void setCurrentEditor$okhttp(Editor editor) {
            this.currentEditor = editor;
        }

        public final int getLockingSourceCount$okhttp() {
            return this.lockingSourceCount;
        }

        public final void setLockingSourceCount$okhttp(int i) {
            this.lockingSourceCount = i;
        }

        public final long getSequenceNumber$okhttp() {
            return this.sequenceNumber;
        }

        public final void setSequenceNumber$okhttp(long j) {
            this.sequenceNumber = j;
        }

        public final void setLengths$okhttp(List<String> list) throws IOException {
            Intrinsics.checkNotNullParameter(list, "strings");
            if (list.size() == this.this$0.getValueCount$okhttp()) {
                try {
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        this.lengths[i] = Long.parseLong(list.get(i));
                    }
                } catch (NumberFormatException unused) {
                    invalidLengths(list);
                    throw new KotlinNothingValueException();
                }
            } else {
                invalidLengths(list);
                throw new KotlinNothingValueException();
            }
        }

        public final void writeLengths$okhttp(BufferedSink bufferedSink) throws IOException {
            Intrinsics.checkNotNullParameter(bufferedSink, "writer");
            for (long writeDecimalLong : this.lengths) {
                bufferedSink.writeByte(32).writeDecimalLong(writeDecimalLong);
            }
        }

        private final Void invalidLengths(List<String> list) throws IOException {
            throw new IOException("unexpected journal line: " + list);
        }

        public final Snapshot snapshot$okhttp() {
            DiskLruCache diskLruCache = this.this$0;
            if (Util.assertionsEnabled && !Thread.holdsLock(diskLruCache)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + diskLruCache);
            } else if (!this.readable) {
                return null;
            } else {
                if (!this.this$0.civilizedFileSystem && (this.currentEditor != null || this.zombie)) {
                    return null;
                }
                List<Source> arrayList = new ArrayList<>();
                long[] jArr = (long[]) this.lengths.clone();
                try {
                    int valueCount$okhttp = this.this$0.getValueCount$okhttp();
                    for (int i = 0; i < valueCount$okhttp; i++) {
                        arrayList.add(newSource(i));
                    }
                    return new Snapshot(this.this$0, this.key, this.sequenceNumber, arrayList, jArr);
                } catch (FileNotFoundException unused) {
                    for (Source closeQuietly : arrayList) {
                        Util.closeQuietly((Closeable) closeQuietly);
                    }
                    try {
                        this.this$0.removeEntry$okhttp(this);
                    } catch (IOException unused2) {
                    }
                    return null;
                }
            }
        }

        private final Source newSource(int i) {
            Source source = this.this$0.getFileSystem$okhttp().source(this.cleanFiles.get(i));
            if (this.this$0.civilizedFileSystem) {
                return source;
            }
            this.lockingSourceCount++;
            return new DiskLruCache$Entry$newSource$1(source, this.this$0, this);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\f8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00068\u0006XD¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/cache/DiskLruCache$Companion;", "", "()V", "ANY_SEQUENCE_NUMBER", "", "CLEAN", "", "DIRTY", "JOURNAL_FILE", "JOURNAL_FILE_BACKUP", "JOURNAL_FILE_TEMP", "LEGAL_KEY_PATTERN", "Lkotlin/text/Regex;", "MAGIC", "READ", "REMOVE", "VERSION_1", "okhttp"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DiskLruCache.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public final synchronized void initialize() throws IOException {
        if (Util.assertionsEnabled) {
            if (!Thread.holdsLock(this)) {
                throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
            }
        }
        if (!this.initialized) {
            if (this.fileSystem.exists(this.journalFileBackup)) {
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.delete(this.journalFileBackup);
                } else {
                    this.fileSystem.rename(this.journalFileBackup, this.journalFile);
                }
            }
            this.civilizedFileSystem = Util.isCivilized(this.fileSystem, this.journalFileBackup);
            if (this.fileSystem.exists(this.journalFile)) {
                try {
                    readJournal();
                    processJournal();
                    this.initialized = true;
                    return;
                } catch (IOException e) {
                    Platform.Companion.get().log("DiskLruCache " + this.directory + " is corrupt: " + e.getMessage() + ", removing", 5, e);
                    delete();
                    this.closed = false;
                } catch (Throwable th) {
                    this.closed = false;
                    throw th;
                }
            }
            rebuildJournal$okhttp();
            this.initialized = true;
        }
    }
}
