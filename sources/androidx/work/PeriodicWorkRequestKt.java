package androidx.work;

import androidx.exifinterface.media.ExifInterface;
import androidx.work.PeriodicWorkRequest;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\b\u001a%\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\b\u001a%\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\b\u001a5\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\tH\b¨\u0006\u000b"}, d2 = {"PeriodicWorkRequestBuilder", "Landroidx/work/PeriodicWorkRequest$Builder;", "W", "Landroidx/work/ListenableWorker;", "repeatInterval", "Ljava/time/Duration;", "flexTimeInterval", "", "repeatIntervalTimeUnit", "Ljava/util/concurrent/TimeUnit;", "flexTimeIntervalUnit", "work-runtime-ktx_release"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* compiled from: PeriodicWorkRequest.kt */
public final class PeriodicWorkRequestKt {
    public static final /* synthetic */ <W extends ListenableWorker> PeriodicWorkRequest.Builder PeriodicWorkRequestBuilder(long j, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(timeUnit, "repeatIntervalTimeUnit");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_WEST);
        Class<ListenableWorker> cls = ListenableWorker.class;
        Class cls2 = cls;
        return new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) cls, j, timeUnit);
    }

    public static final /* synthetic */ <W extends ListenableWorker> PeriodicWorkRequest.Builder PeriodicWorkRequestBuilder(Duration duration) {
        Intrinsics.checkNotNullParameter(duration, "repeatInterval");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_WEST);
        Class<ListenableWorker> cls = ListenableWorker.class;
        Class cls2 = cls;
        return new PeriodicWorkRequest.Builder(cls, duration);
    }

    public static final /* synthetic */ <W extends ListenableWorker> PeriodicWorkRequest.Builder PeriodicWorkRequestBuilder(long j, TimeUnit timeUnit, long j2, TimeUnit timeUnit2) {
        Intrinsics.checkNotNullParameter(timeUnit, "repeatIntervalTimeUnit");
        Intrinsics.checkNotNullParameter(timeUnit2, "flexTimeIntervalUnit");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_WEST);
        Class<ListenableWorker> cls = ListenableWorker.class;
        Class cls2 = cls;
        return new PeriodicWorkRequest.Builder(cls, j, timeUnit, j2, timeUnit2);
    }

    public static final /* synthetic */ <W extends ListenableWorker> PeriodicWorkRequest.Builder PeriodicWorkRequestBuilder(Duration duration, Duration duration2) {
        Intrinsics.checkNotNullParameter(duration, "repeatInterval");
        Intrinsics.checkNotNullParameter(duration2, "flexTimeInterval");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LONGITUDE_WEST);
        Class<ListenableWorker> cls = ListenableWorker.class;
        Class cls2 = cls;
        return new PeriodicWorkRequest.Builder((Class<? extends ListenableWorker>) cls, duration, duration2);
    }
}
