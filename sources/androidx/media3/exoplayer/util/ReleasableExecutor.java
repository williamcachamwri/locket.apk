package androidx.media3.exoplayer.util;

import androidx.media3.common.util.Consumer;
import java.util.concurrent.Executor;

public interface ReleasableExecutor extends Executor {
    void release();

    static <T extends Executor> ReleasableExecutor from(final T t, final Consumer<T> consumer) {
        return new ReleasableExecutor() {
            public void execute(Runnable runnable) {
                t.execute(runnable);
            }

            public void release() {
                consumer.accept(t);
            }
        };
    }
}
