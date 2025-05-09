package androidx.work;

import androidx.work.impl.DefaultRunnableScheduler;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class Configuration {
    public static final int MIN_SCHEDULER_LIMIT = 20;
    final String mDefaultProcessName;
    final InitializationExceptionHandler mExceptionHandler;
    final Executor mExecutor;
    final InputMergerFactory mInputMergerFactory;
    private final boolean mIsUsingDefaultTaskExecutor;
    final int mLoggingLevel;
    final int mMaxJobSchedulerId;
    final int mMaxSchedulerLimit;
    final int mMinJobSchedulerId;
    final RunnableScheduler mRunnableScheduler;
    final Executor mTaskExecutor;
    final WorkerFactory mWorkerFactory;

    public interface Provider {
        Configuration getWorkManagerConfiguration();
    }

    Configuration(Builder builder) {
        if (builder.mExecutor == null) {
            this.mExecutor = createDefaultExecutor(false);
        } else {
            this.mExecutor = builder.mExecutor;
        }
        if (builder.mTaskExecutor == null) {
            this.mIsUsingDefaultTaskExecutor = true;
            this.mTaskExecutor = createDefaultExecutor(true);
        } else {
            this.mIsUsingDefaultTaskExecutor = false;
            this.mTaskExecutor = builder.mTaskExecutor;
        }
        if (builder.mWorkerFactory == null) {
            this.mWorkerFactory = WorkerFactory.getDefaultWorkerFactory();
        } else {
            this.mWorkerFactory = builder.mWorkerFactory;
        }
        if (builder.mInputMergerFactory == null) {
            this.mInputMergerFactory = InputMergerFactory.getDefaultInputMergerFactory();
        } else {
            this.mInputMergerFactory = builder.mInputMergerFactory;
        }
        if (builder.mRunnableScheduler == null) {
            this.mRunnableScheduler = new DefaultRunnableScheduler();
        } else {
            this.mRunnableScheduler = builder.mRunnableScheduler;
        }
        this.mLoggingLevel = builder.mLoggingLevel;
        this.mMinJobSchedulerId = builder.mMinJobSchedulerId;
        this.mMaxJobSchedulerId = builder.mMaxJobSchedulerId;
        this.mMaxSchedulerLimit = builder.mMaxSchedulerLimit;
        this.mExceptionHandler = builder.mExceptionHandler;
        this.mDefaultProcessName = builder.mDefaultProcessName;
    }

    public Executor getExecutor() {
        return this.mExecutor;
    }

    public Executor getTaskExecutor() {
        return this.mTaskExecutor;
    }

    public WorkerFactory getWorkerFactory() {
        return this.mWorkerFactory;
    }

    public InputMergerFactory getInputMergerFactory() {
        return this.mInputMergerFactory;
    }

    public RunnableScheduler getRunnableScheduler() {
        return this.mRunnableScheduler;
    }

    public int getMinimumLoggingLevel() {
        return this.mLoggingLevel;
    }

    public int getMinJobSchedulerId() {
        return this.mMinJobSchedulerId;
    }

    public int getMaxJobSchedulerId() {
        return this.mMaxJobSchedulerId;
    }

    public String getDefaultProcessName() {
        return this.mDefaultProcessName;
    }

    public int getMaxSchedulerLimit() {
        return this.mMaxSchedulerLimit;
    }

    public boolean isUsingDefaultTaskExecutor() {
        return this.mIsUsingDefaultTaskExecutor;
    }

    public InitializationExceptionHandler getExceptionHandler() {
        return this.mExceptionHandler;
    }

    private Executor createDefaultExecutor(boolean z) {
        return Executors.newFixedThreadPool(Math.max(2, Math.min(Runtime.getRuntime().availableProcessors() - 1, 4)), createDefaultThreadFactory(z));
    }

    private ThreadFactory createDefaultThreadFactory(final boolean z) {
        return new ThreadFactory() {
            private final AtomicInteger mThreadCount = new AtomicInteger(0);

            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, (z ? "WM.task-" : "androidx.work-") + this.mThreadCount.incrementAndGet());
            }
        };
    }

    public static final class Builder {
        String mDefaultProcessName;
        InitializationExceptionHandler mExceptionHandler;
        Executor mExecutor;
        InputMergerFactory mInputMergerFactory;
        int mLoggingLevel;
        int mMaxJobSchedulerId;
        int mMaxSchedulerLimit;
        int mMinJobSchedulerId;
        RunnableScheduler mRunnableScheduler;
        Executor mTaskExecutor;
        WorkerFactory mWorkerFactory;

        public Builder() {
            this.mLoggingLevel = 4;
            this.mMinJobSchedulerId = 0;
            this.mMaxJobSchedulerId = Integer.MAX_VALUE;
            this.mMaxSchedulerLimit = 20;
        }

        public Builder(Configuration configuration) {
            this.mExecutor = configuration.mExecutor;
            this.mWorkerFactory = configuration.mWorkerFactory;
            this.mInputMergerFactory = configuration.mInputMergerFactory;
            this.mTaskExecutor = configuration.mTaskExecutor;
            this.mLoggingLevel = configuration.mLoggingLevel;
            this.mMinJobSchedulerId = configuration.mMinJobSchedulerId;
            this.mMaxJobSchedulerId = configuration.mMaxJobSchedulerId;
            this.mMaxSchedulerLimit = configuration.mMaxSchedulerLimit;
            this.mRunnableScheduler = configuration.mRunnableScheduler;
            this.mExceptionHandler = configuration.mExceptionHandler;
            this.mDefaultProcessName = configuration.mDefaultProcessName;
        }

        public Builder setWorkerFactory(WorkerFactory workerFactory) {
            this.mWorkerFactory = workerFactory;
            return this;
        }

        public Builder setInputMergerFactory(InputMergerFactory inputMergerFactory) {
            this.mInputMergerFactory = inputMergerFactory;
            return this;
        }

        public Builder setExecutor(Executor executor) {
            this.mExecutor = executor;
            return this;
        }

        public Builder setTaskExecutor(Executor executor) {
            this.mTaskExecutor = executor;
            return this;
        }

        public Builder setJobSchedulerJobIdRange(int i, int i2) {
            if (i2 - i >= 1000) {
                this.mMinJobSchedulerId = i;
                this.mMaxJobSchedulerId = i2;
                return this;
            }
            throw new IllegalArgumentException("WorkManager needs a range of at least 1000 job ids.");
        }

        public Builder setMaxSchedulerLimit(int i) {
            if (i >= 20) {
                this.mMaxSchedulerLimit = Math.min(i, 50);
                return this;
            }
            throw new IllegalArgumentException("WorkManager needs to be able to schedule at least 20 jobs in JobScheduler.");
        }

        public Builder setMinimumLoggingLevel(int i) {
            this.mLoggingLevel = i;
            return this;
        }

        public Builder setRunnableScheduler(RunnableScheduler runnableScheduler) {
            this.mRunnableScheduler = runnableScheduler;
            return this;
        }

        public Builder setInitializationExceptionHandler(InitializationExceptionHandler initializationExceptionHandler) {
            this.mExceptionHandler = initializationExceptionHandler;
            return this;
        }

        public Builder setDefaultProcessName(String str) {
            this.mDefaultProcessName = str;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }
}
