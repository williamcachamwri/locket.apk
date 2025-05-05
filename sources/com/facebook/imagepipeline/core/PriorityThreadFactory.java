package com.facebook.imagepipeline.core;

import android.os.Process;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/facebook/imagepipeline/core/PriorityThreadFactory;", "Ljava/util/concurrent/ThreadFactory;", "threadPriority", "", "prefix", "", "addThreadNumber", "", "(ILjava/lang/String;Z)V", "threadNumber", "Ljava/util/concurrent/atomic/AtomicInteger;", "newThread", "Ljava/lang/Thread;", "runnable", "Ljava/lang/Runnable;", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PriorityThreadFactory.kt */
public final class PriorityThreadFactory implements ThreadFactory {
    private final boolean addThreadNumber;
    private final String prefix;
    private final AtomicInteger threadNumber;
    private final int threadPriority;

    public PriorityThreadFactory(int i) {
        this(i, (String) null, false, 6, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PriorityThreadFactory(int i, String str) {
        this(i, str, false, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "prefix");
    }

    public PriorityThreadFactory(int i, String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "prefix");
        this.threadPriority = i;
        this.prefix = str;
        this.addThreadNumber = z;
        this.threadNumber = new AtomicInteger(1);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PriorityThreadFactory(int i, String str, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? "PriorityThreadFactory" : str, (i2 & 4) != 0 ? true : z);
    }

    public Thread newThread(Runnable runnable) {
        String str;
        Intrinsics.checkNotNullParameter(runnable, "runnable");
        PriorityThreadFactory$$ExternalSyntheticLambda0 priorityThreadFactory$$ExternalSyntheticLambda0 = new PriorityThreadFactory$$ExternalSyntheticLambda0(this, runnable);
        if (this.addThreadNumber) {
            str = this.prefix + '-' + this.threadNumber.getAndIncrement();
        } else {
            str = this.prefix;
        }
        return new Thread(priorityThreadFactory$$ExternalSyntheticLambda0, str);
    }

    /* access modifiers changed from: private */
    public static final void newThread$lambda$0(PriorityThreadFactory priorityThreadFactory, Runnable runnable) {
        Intrinsics.checkNotNullParameter(priorityThreadFactory, "this$0");
        Intrinsics.checkNotNullParameter(runnable, "$runnable");
        try {
            Process.setThreadPriority(priorityThreadFactory.threadPriority);
        } catch (Throwable unused) {
        }
        runnable.run();
    }
}
