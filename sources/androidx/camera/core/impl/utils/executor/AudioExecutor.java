package androidx.camera.core.impl.utils.executor;

import android.os.Process;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class AudioExecutor implements Executor {
    private static volatile Executor sExecutor;
    private final ExecutorService mAudioService = Executors.newFixedThreadPool(2, new ThreadFactory() {
        private static final String THREAD_NAME_STEM = "CameraX-camerax_audio_%d";
        private final AtomicInteger mThreadId = new AtomicInteger(0);

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(new AudioExecutor$1$$ExternalSyntheticLambda0(runnable));
            thread.setName(String.format(Locale.US, THREAD_NAME_STEM, new Object[]{Integer.valueOf(this.mThreadId.getAndIncrement())}));
            return thread;
        }

        static /* synthetic */ void lambda$newThread$0(Runnable runnable) {
            Process.setThreadPriority(-16);
            runnable.run();
        }
    });

    static Executor getInstance() {
        if (sExecutor != null) {
            return sExecutor;
        }
        synchronized (AudioExecutor.class) {
            if (sExecutor == null) {
                sExecutor = new AudioExecutor();
            }
        }
        return sExecutor;
    }

    public void execute(Runnable runnable) {
        this.mAudioService.execute(runnable);
    }
}
