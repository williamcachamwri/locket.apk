package com.facebook.imagepipeline.listener;

import android.os.SystemClock;
import android.util.Pair;
import com.facebook.common.logging.FLog;
import com.facebook.imagepipeline.request.ImageRequest;
import io.sentry.SentryBaseEvent;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B\u0005¢\u0006\u0002\u0010\u0002J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0006H\u0016J.\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0010H\u0016J6\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0010H\u0016J.\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0010H\u0016J\u0018\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006H\u0016J\u0010\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J(\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J(\u0010\u001c\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J \u0010\u001f\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J \u0010 \u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u001bH\u0016J\u0010\u0010\"\u001a\u00020\u001b2\u0006\u0010#\u001a\u00020\u0006H\u0016R(\u0010\u0003\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u00070\u00048\u0002X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00048\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/facebook/imagepipeline/listener/RequestLoggingListener;", "Lcom/facebook/imagepipeline/listener/RequestListener;", "()V", "producerStartTimeMap", "", "Landroid/util/Pair;", "", "", "requestStartTimeMap", "onProducerEvent", "", "requestId", "producerName", "producerEventName", "onProducerFinishWithCancellation", "extraMap", "", "onProducerFinishWithFailure", "throwable", "", "onProducerFinishWithSuccess", "onProducerStart", "onRequestCancellation", "onRequestFailure", "request", "Lcom/facebook/imagepipeline/request/ImageRequest;", "isPrefetch", "", "onRequestStart", "callerContextObject", "", "onRequestSuccess", "onUltimateProducerReached", "successful", "requiresExtraMap", "id", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RequestLoggingListener.kt */
public final class RequestLoggingListener implements RequestListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "RequestLoggingListener";
    private final Map<Pair<String, String>, Long> producerStartTimeMap = new HashMap();
    private final Map<String, Long> requestStartTimeMap = new HashMap();

    public synchronized void onRequestStart(ImageRequest imageRequest, Object obj, String str, boolean z) {
        Intrinsics.checkNotNullParameter(imageRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(obj, "callerContextObject");
        Intrinsics.checkNotNullParameter(str, "requestId");
        if (FLog.isLoggable(2)) {
            Companion companion = Companion;
            FLog.v(TAG, "time %d: onRequestSubmit: {requestId: %s, callerContext: %s, isPrefetch: %b}", (Object) Long.valueOf(companion.getTime()), (Object) str, obj, (Object) Boolean.valueOf(z));
            this.requestStartTimeMap.put(str, Long.valueOf(companion.getTime()));
        }
    }

    public synchronized void onProducerStart(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        if (FLog.isLoggable(2)) {
            Pair create = Pair.create(str, str2);
            long access$getTime = Companion.getTime();
            Long valueOf = Long.valueOf(access$getTime);
            Map<Pair<String, String>, Long> map = this.producerStartTimeMap;
            Intrinsics.checkNotNullExpressionValue(create, "mapKey");
            map.put(create, valueOf);
            FLog.v(TAG, "time %d: onProducerStart: {requestId: %s, producer: %s}", (Object) Long.valueOf(access$getTime), (Object) str, (Object) str2);
        }
    }

    public synchronized void onProducerFinishWithSuccess(String str, String str2, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        if (FLog.isLoggable(2)) {
            Pair create = Pair.create(str, str2);
            Companion companion = Companion;
            long access$getTime = companion.getTime();
            FLog.v(TAG, "time %d: onProducerFinishWithSuccess: {requestId: %s, producer: %s, elapsedTime: %d ms, extraMap: %s}", Long.valueOf(access$getTime), str, str2, Long.valueOf(companion.getElapsedTime(this.producerStartTimeMap.remove(create), access$getTime)), map);
        }
    }

    public synchronized void onProducerFinishWithFailure(String str, String str2, Throwable th, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        Intrinsics.checkNotNullParameter(th, "throwable");
        if (FLog.isLoggable(5)) {
            Pair create = Pair.create(str, str2);
            Companion companion = Companion;
            long access$getTime = companion.getTime();
            FLog.w(TAG, th, "time %d: onProducerFinishWithFailure: {requestId: %s, stage: %s, elapsedTime: %d ms, extraMap: %s, throwable: %s}", Long.valueOf(access$getTime), str, str2, Long.valueOf(companion.getElapsedTime(this.producerStartTimeMap.remove(create), access$getTime)), map, th.toString());
        }
    }

    public synchronized void onProducerFinishWithCancellation(String str, String str2, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        if (FLog.isLoggable(2)) {
            Pair create = Pair.create(str, str2);
            Companion companion = Companion;
            long access$getTime = companion.getTime();
            FLog.v(TAG, "time %d: onProducerFinishWithCancellation: {requestId: %s, stage: %s, elapsedTime: %d ms, extraMap: %s}", Long.valueOf(access$getTime), str, str2, Long.valueOf(companion.getElapsedTime(this.producerStartTimeMap.remove(create), access$getTime)), map);
        }
    }

    public synchronized void onProducerEvent(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        Intrinsics.checkNotNullParameter(str3, "producerEventName");
        if (FLog.isLoggable(2)) {
            Pair create = Pair.create(str, str2);
            Companion companion = Companion;
            FLog.v(TAG, "time %d: onProducerEvent: {requestId: %s, stage: %s, eventName: %s; elapsedTime: %d ms}", Long.valueOf(companion.getTime()), str, str2, str3, Long.valueOf(companion.getElapsedTime(this.producerStartTimeMap.get(create), companion.getTime())));
        }
    }

    public synchronized void onUltimateProducerReached(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(str2, "producerName");
        if (FLog.isLoggable(2)) {
            Pair create = Pair.create(str, str2);
            Companion companion = Companion;
            long access$getTime = companion.getTime();
            FLog.v(TAG, "time %d: onUltimateProducerReached: {requestId: %s, producer: %s, elapsedTime: %d ms, success: %b}", Long.valueOf(access$getTime), str, str2, Long.valueOf(companion.getElapsedTime(this.producerStartTimeMap.remove(create), access$getTime)), Boolean.valueOf(z));
        }
    }

    public synchronized void onRequestSuccess(ImageRequest imageRequest, String str, boolean z) {
        Intrinsics.checkNotNullParameter(imageRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(str, "requestId");
        if (FLog.isLoggable(2)) {
            Companion companion = Companion;
            long access$getTime = companion.getTime();
            FLog.v(TAG, "time %d: onRequestSuccess: {requestId: %s, elapsedTime: %d ms}", (Object) Long.valueOf(access$getTime), (Object) str, (Object) Long.valueOf(companion.getElapsedTime(this.requestStartTimeMap.remove(str), access$getTime)));
        }
    }

    public synchronized void onRequestFailure(ImageRequest imageRequest, String str, Throwable th, boolean z) {
        Intrinsics.checkNotNullParameter(imageRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(str, "requestId");
        Intrinsics.checkNotNullParameter(th, "throwable");
        if (FLog.isLoggable(5)) {
            Companion companion = Companion;
            long access$getTime = companion.getTime();
            FLog.w(TAG, "time %d: onRequestFailure: {requestId: %s, elapsedTime: %d ms, throwable: %s}", Long.valueOf(access$getTime), str, Long.valueOf(companion.getElapsedTime(this.requestStartTimeMap.remove(str), access$getTime)), th.toString());
        }
    }

    public synchronized void onRequestCancellation(String str) {
        Intrinsics.checkNotNullParameter(str, "requestId");
        if (FLog.isLoggable(2)) {
            Companion companion = Companion;
            long access$getTime = companion.getTime();
            FLog.v(TAG, "time %d: onRequestCancellation: {requestId: %s, elapsedTime: %d ms}", (Object) Long.valueOf(access$getTime), (Object) str, (Object) Long.valueOf(companion.getElapsedTime(this.requestStartTimeMap.remove(str), access$getTime)));
        }
    }

    public boolean requiresExtraMap(String str) {
        Intrinsics.checkNotNullParameter(str, "id");
        return FLog.isLoggable(2);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\t\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\r"}, d2 = {"Lcom/facebook/imagepipeline/listener/RequestLoggingListener$Companion;", "", "()V", "TAG", "", "time", "", "getTime", "()J", "getElapsedTime", "startTime", "endTime", "(Ljava/lang/Long;J)J", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RequestLoggingListener.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final long getElapsedTime(Long l, long j) {
            if (l != null) {
                return j - l.longValue();
            }
            return -1;
        }

        /* access modifiers changed from: private */
        public final long getTime() {
            return SystemClock.uptimeMillis();
        }
    }
}
