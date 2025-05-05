package expo.modules.filesystem;

import android.os.Bundle;
import expo.modules.filesystem.FileSystemModule;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"expo/modules/filesystem/FileSystemModule$definition$1$21$progressListener$1", "Lexpo/modules/filesystem/FileSystemModule$ProgressListener;", "mLastUpdate", "", "getMLastUpdate", "()J", "setMLastUpdate", "(J)V", "update", "", "bytesRead", "contentLength", "done", "", "expo-file-system_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileSystemModule.kt */
public final class FileSystemModule$definition$1$21$progressListener$1 implements FileSystemModule.ProgressListener {
    final /* synthetic */ String $resumeData;
    final /* synthetic */ String $uuid;
    private long mLastUpdate = -1;
    final /* synthetic */ FileSystemModule this$0;

    FileSystemModule$definition$1$21$progressListener$1(String str, String str2, FileSystemModule fileSystemModule) {
        this.$resumeData = str;
        this.$uuid = str2;
        this.this$0 = fileSystemModule;
    }

    public final long getMLastUpdate() {
        return this.mLastUpdate;
    }

    public final void setMLastUpdate(long j) {
        this.mLastUpdate = j;
    }

    public void update(long j, long j2, boolean z) {
        Bundle bundle = new Bundle();
        Bundle bundle2 = new Bundle();
        String str = this.$resumeData;
        long j3 = 0;
        long parseLong = j + (str != null ? Long.parseLong(str) : 0);
        String str2 = this.$resumeData;
        if (str2 != null) {
            j3 = Long.parseLong(str2);
        }
        long j4 = j2 + j3;
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > this.mLastUpdate + 100 || parseLong == j4) {
            this.mLastUpdate = currentTimeMillis;
            bundle2.putDouble("totalBytesWritten", (double) parseLong);
            bundle2.putDouble("totalBytesExpectedToWrite", (double) j4);
            bundle.putString("uuid", this.$uuid);
            bundle.putBundle("data", bundle2);
            this.this$0.sendEvent("expo-file-system.downloadProgress", bundle);
        }
    }
}
