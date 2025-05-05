package com.mrousavy.camera.core;

import android.os.Handler;
import android.os.HandlerThread;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.android.HandlerDispatcherKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/mrousavy/camera/core/CameraQueues;", "", "()V", "CameraQueue", "Companion", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CameraQueues.kt */
public final class CameraQueues {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final ExecutorService analyzerExecutor;
    /* access modifiers changed from: private */
    public static final ExecutorService cameraExecutor;
    /* access modifiers changed from: private */
    public static final CameraQueue videoQueue = new CameraQueue("mrousavy/VisionCamera.video");

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/mrousavy/camera/core/CameraQueues$Companion;", "", "()V", "analyzerExecutor", "Ljava/util/concurrent/ExecutorService;", "getAnalyzerExecutor", "()Ljava/util/concurrent/ExecutorService;", "cameraExecutor", "getCameraExecutor", "videoQueue", "Lcom/mrousavy/camera/core/CameraQueues$CameraQueue;", "getVideoQueue", "()Lcom/mrousavy/camera/core/CameraQueues$CameraQueue;", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraQueues.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ExecutorService getAnalyzerExecutor() {
            return CameraQueues.analyzerExecutor;
        }

        public final ExecutorService getCameraExecutor() {
            return CameraQueues.cameraExecutor;
        }

        public final CameraQueue getVideoQueue() {
            return CameraQueues.videoQueue;
        }
    }

    static {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        Intrinsics.checkNotNullExpressionValue(newCachedThreadPool, "newCachedThreadPool(...)");
        analyzerExecutor = newCachedThreadPool;
        ExecutorService newCachedThreadPool2 = Executors.newCachedThreadPool();
        Intrinsics.checkNotNullExpressionValue(newCachedThreadPool2, "newCachedThreadPool(...)");
        cameraExecutor = newCachedThreadPool2;
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0011\u001a\u00020\u0012H\u0004R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/mrousavy/camera/core/CameraQueues$CameraQueue;", "", "name", "", "(Ljava/lang/String;)V", "coroutineDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "executor", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "thread", "Landroid/os/HandlerThread;", "finalize", "", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CameraQueues.kt */
    public static final class CameraQueue {
        private final CoroutineDispatcher coroutineDispatcher;
        private final Executor executor;
        private final Handler handler;
        private final HandlerThread thread;

        public CameraQueue(String str) {
            Intrinsics.checkNotNullParameter(str, "name");
            HandlerThread handlerThread = new HandlerThread(str);
            this.thread = handlerThread;
            handlerThread.start();
            Handler handler2 = new Handler(handlerThread.getLooper());
            this.handler = handler2;
            CoroutineDispatcher from = HandlerDispatcherKt.from(handler2, str);
            this.coroutineDispatcher = from;
            this.executor = ExecutorsKt.asExecutor(from);
        }

        public final Handler getHandler() {
            return this.handler;
        }

        public final Executor getExecutor() {
            return this.executor;
        }

        /* access modifiers changed from: protected */
        public final void finalize() {
            this.thread.quitSafely();
        }
    }
}
