package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessorRunner;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class PostprocessorProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final String NAME = "PostprocessorProducer";
    static final String POSTPROCESSOR = "Postprocessor";
    /* access modifiers changed from: private */
    public final PlatformBitmapFactory mBitmapFactory;
    /* access modifiers changed from: private */
    public final Executor mExecutor;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;

    /* JADX WARNING: type inference failed for: r1v0, types: [com.facebook.imagepipeline.producers.Producer<com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PostprocessorProducer(com.facebook.imagepipeline.producers.Producer<com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>> r1, com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory r2, java.util.concurrent.Executor r3) {
        /*
            r0 = this;
            r0.<init>()
            java.lang.Object r1 = com.facebook.common.internal.Preconditions.checkNotNull(r1)
            com.facebook.imagepipeline.producers.Producer r1 = (com.facebook.imagepipeline.producers.Producer) r1
            r0.mInputProducer = r1
            r0.mBitmapFactory = r2
            java.lang.Object r1 = com.facebook.common.internal.Preconditions.checkNotNull(r3)
            java.util.concurrent.Executor r1 = (java.util.concurrent.Executor) r1
            r0.mExecutor = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PostprocessorProducer.<init>(com.facebook.imagepipeline.producers.Producer, com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory, java.util.concurrent.Executor):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v2, resolved type: com.facebook.imagepipeline.producers.PostprocessorProducer$RepeatedPostprocessorConsumer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: com.facebook.imagepipeline.producers.PostprocessorProducer$SingleUsePostprocessorConsumer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v4, resolved type: com.facebook.imagepipeline.producers.PostprocessorProducer$RepeatedPostprocessorConsumer} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v1, resolved type: com.facebook.imagepipeline.producers.PostprocessorProducer$RepeatedPostprocessorConsumer} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void produceResults(com.facebook.imagepipeline.producers.Consumer<com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>> r14, com.facebook.imagepipeline.producers.ProducerContext r15) {
        /*
            r13 = this;
            com.facebook.imagepipeline.producers.ProducerListener2 r3 = r15.getProducerListener()
            com.facebook.imagepipeline.request.ImageRequest r0 = r15.getImageRequest()
            com.facebook.imagepipeline.request.Postprocessor r6 = r0.getPostprocessor()
            com.facebook.common.internal.Preconditions.checkNotNull(r6)
            com.facebook.imagepipeline.producers.PostprocessorProducer$PostprocessorConsumer r9 = new com.facebook.imagepipeline.producers.PostprocessorProducer$PostprocessorConsumer
            r0 = r9
            r1 = r13
            r2 = r14
            r4 = r6
            r5 = r15
            r0.<init>(r2, r3, r4, r5)
            boolean r14 = r6 instanceof com.facebook.imagepipeline.request.RepeatedPostprocessor
            if (r14 == 0) goto L_0x002a
            com.facebook.imagepipeline.producers.PostprocessorProducer$RepeatedPostprocessorConsumer r14 = new com.facebook.imagepipeline.producers.PostprocessorProducer$RepeatedPostprocessorConsumer
            r10 = r6
            com.facebook.imagepipeline.request.RepeatedPostprocessor r10 = (com.facebook.imagepipeline.request.RepeatedPostprocessor) r10
            r12 = 0
            r7 = r14
            r8 = r13
            r11 = r15
            r7.<init>(r9, r10, r11)
            goto L_0x0030
        L_0x002a:
            com.facebook.imagepipeline.producers.PostprocessorProducer$SingleUsePostprocessorConsumer r14 = new com.facebook.imagepipeline.producers.PostprocessorProducer$SingleUsePostprocessorConsumer
            r0 = 0
            r14.<init>(r9)
        L_0x0030:
            com.facebook.imagepipeline.producers.Producer<com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage>> r0 = r13.mInputProducer
            r0.produceResults(r14, r15)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PostprocessorProducer.produceResults(com.facebook.imagepipeline.producers.Consumer, com.facebook.imagepipeline.producers.ProducerContext):void");
    }

    private class PostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private boolean mIsClosed;
        /* access modifiers changed from: private */
        public boolean mIsDirty = false;
        private boolean mIsPostProcessingRunning = false;
        private final ProducerListener2 mListener;
        private final Postprocessor mPostprocessor;
        private final ProducerContext mProducerContext;
        /* access modifiers changed from: private */
        @Nullable
        public CloseableReference<CloseableImage> mSourceImageRef = null;
        /* access modifiers changed from: private */
        public int mStatus = 0;

        public PostprocessorConsumer(Consumer<CloseableReference<CloseableImage>> consumer, ProducerListener2 producerListener2, Postprocessor postprocessor, ProducerContext producerContext) {
            super(consumer);
            this.mListener = producerListener2;
            this.mPostprocessor = postprocessor;
            this.mProducerContext = producerContext;
            producerContext.addCallbacks(new BaseProducerContextCallbacks(PostprocessorProducer.this) {
                public void onCancellationRequested() {
                    PostprocessorConsumer.this.maybeNotifyOnCancellation();
                }
            });
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(@Nullable CloseableReference<CloseableImage> closeableReference, int i) {
            if (CloseableReference.isValid(closeableReference)) {
                updateSourceImageRef(closeableReference, i);
            } else if (isLast(i)) {
                maybeNotifyOnNewResult((CloseableReference<CloseableImage>) null, i);
            }
        }

        /* access modifiers changed from: protected */
        public void onFailureImpl(Throwable th) {
            maybeNotifyOnFailure(th);
        }

        /* access modifiers changed from: protected */
        public void onCancellationImpl() {
            maybeNotifyOnCancellation();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
            submitPostprocessing();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
            com.facebook.common.references.CloseableReference.closeSafely((com.facebook.common.references.CloseableReference<?>) r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
            if (r2 == false) goto L_?;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void updateSourceImageRef(@javax.annotation.Nullable com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage> r2, int r3) {
            /*
                r1 = this;
                monitor-enter(r1)
                boolean r0 = r1.mIsClosed     // Catch:{ all -> 0x0022 }
                if (r0 == 0) goto L_0x0007
                monitor-exit(r1)     // Catch:{ all -> 0x0022 }
                return
            L_0x0007:
                com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage> r0 = r1.mSourceImageRef     // Catch:{ all -> 0x0022 }
                com.facebook.common.references.CloseableReference r2 = com.facebook.common.references.CloseableReference.cloneOrNull(r2)     // Catch:{ all -> 0x0022 }
                r1.mSourceImageRef = r2     // Catch:{ all -> 0x0022 }
                r1.mStatus = r3     // Catch:{ all -> 0x0022 }
                r2 = 1
                r1.mIsDirty = r2     // Catch:{ all -> 0x0022 }
                boolean r2 = r1.setRunningIfDirtyAndNotRunning()     // Catch:{ all -> 0x0022 }
                monitor-exit(r1)     // Catch:{ all -> 0x0022 }
                com.facebook.common.references.CloseableReference.closeSafely((com.facebook.common.references.CloseableReference<?>) r0)
                if (r2 == 0) goto L_0x0021
                r1.submitPostprocessing()
            L_0x0021:
                return
            L_0x0022:
                r2 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0022 }
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PostprocessorProducer.PostprocessorConsumer.updateSourceImageRef(com.facebook.common.references.CloseableReference, int):void");
        }

        private void submitPostprocessing() {
            PostprocessorProducer.this.mExecutor.execute(new Runnable() {
                public void run() {
                    CloseableReference access$300;
                    int access$400;
                    synchronized (PostprocessorConsumer.this) {
                        access$300 = PostprocessorConsumer.this.mSourceImageRef;
                        access$400 = PostprocessorConsumer.this.mStatus;
                        CloseableReference unused = PostprocessorConsumer.this.mSourceImageRef = null;
                        boolean unused2 = PostprocessorConsumer.this.mIsDirty = false;
                    }
                    if (CloseableReference.isValid(access$300)) {
                        try {
                            PostprocessorConsumer.this.doPostprocessing(access$300, access$400);
                        } finally {
                            CloseableReference.closeSafely((CloseableReference<?>) access$300);
                        }
                    }
                    PostprocessorConsumer.this.clearRunningAndStartIfDirty();
                }
            });
        }

        /* access modifiers changed from: private */
        public void clearRunningAndStartIfDirty() {
            boolean runningIfDirtyAndNotRunning;
            synchronized (this) {
                this.mIsPostProcessingRunning = false;
                runningIfDirtyAndNotRunning = setRunningIfDirtyAndNotRunning();
            }
            if (runningIfDirtyAndNotRunning) {
                submitPostprocessing();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
            return false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private synchronized boolean setRunningIfDirtyAndNotRunning() {
            /*
                r1 = this;
                monitor-enter(r1)
                boolean r0 = r1.mIsClosed     // Catch:{ all -> 0x001d }
                if (r0 != 0) goto L_0x001a
                boolean r0 = r1.mIsDirty     // Catch:{ all -> 0x001d }
                if (r0 == 0) goto L_0x001a
                boolean r0 = r1.mIsPostProcessingRunning     // Catch:{ all -> 0x001d }
                if (r0 != 0) goto L_0x001a
                com.facebook.common.references.CloseableReference<com.facebook.imagepipeline.image.CloseableImage> r0 = r1.mSourceImageRef     // Catch:{ all -> 0x001d }
                boolean r0 = com.facebook.common.references.CloseableReference.isValid(r0)     // Catch:{ all -> 0x001d }
                if (r0 == 0) goto L_0x001a
                r0 = 1
                r1.mIsPostProcessingRunning = r0     // Catch:{ all -> 0x001d }
                monitor-exit(r1)
                return r0
            L_0x001a:
                monitor-exit(r1)
                r0 = 0
                return r0
            L_0x001d:
                r0 = move-exception
                monitor-exit(r1)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.PostprocessorProducer.PostprocessorConsumer.setRunningIfDirtyAndNotRunning():boolean");
        }

        /* access modifiers changed from: private */
        public void doPostprocessing(CloseableReference<CloseableImage> closeableReference, int i) {
            Preconditions.checkArgument(Boolean.valueOf(CloseableReference.isValid(closeableReference)));
            if (!shouldPostprocess(closeableReference.get())) {
                maybeNotifyOnNewResult(closeableReference, i);
                return;
            }
            this.mListener.onProducerStart(this.mProducerContext, PostprocessorProducer.NAME);
            CloseableReference<CloseableImage> closeableReference2 = null;
            try {
                closeableReference2 = postprocessInternal(closeableReference.get());
                ProducerListener2 producerListener2 = this.mListener;
                ProducerContext producerContext = this.mProducerContext;
                producerListener2.onProducerFinishWithSuccess(producerContext, PostprocessorProducer.NAME, getExtraMap(producerListener2, producerContext, this.mPostprocessor));
                maybeNotifyOnNewResult(closeableReference2, i);
            } catch (Exception e) {
                ProducerListener2 producerListener22 = this.mListener;
                ProducerContext producerContext2 = this.mProducerContext;
                producerListener22.onProducerFinishWithFailure(producerContext2, PostprocessorProducer.NAME, e, getExtraMap(producerListener22, producerContext2, this.mPostprocessor));
                maybeNotifyOnFailure(e);
            } finally {
                CloseableReference.closeSafely((CloseableReference<?>) closeableReference2);
            }
        }

        @Nullable
        private Map<String, String> getExtraMap(ProducerListener2 producerListener2, ProducerContext producerContext, Postprocessor postprocessor) {
            if (!producerListener2.requiresExtraMap(producerContext, PostprocessorProducer.NAME)) {
                return null;
            }
            return ImmutableMap.of(PostprocessorProducer.POSTPROCESSOR, postprocessor.getName());
        }

        private boolean shouldPostprocess(CloseableImage closeableImage) {
            return closeableImage instanceof CloseableStaticBitmap;
        }

        private CloseableReference<CloseableImage> postprocessInternal(CloseableImage closeableImage) {
            CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap) closeableImage;
            CloseableReference<Bitmap> process = this.mPostprocessor.process(closeableStaticBitmap.getUnderlyingBitmap(), PostprocessorProducer.this.mBitmapFactory);
            try {
                CloseableStaticBitmap of = CloseableStaticBitmap.of(process, closeableImage.getQualityInfo(), closeableStaticBitmap.getRotationAngle(), closeableStaticBitmap.getExifOrientation());
                of.putExtras(closeableStaticBitmap.getExtras());
                return CloseableReference.of(of);
            } finally {
                CloseableReference.closeSafely((CloseableReference<?>) process);
            }
        }

        private void maybeNotifyOnNewResult(@Nullable CloseableReference<CloseableImage> closeableReference, int i) {
            boolean isLast = isLast(i);
            if ((!isLast && !isClosed()) || (isLast && close())) {
                getConsumer().onNewResult(closeableReference, i);
            }
        }

        private void maybeNotifyOnFailure(Throwable th) {
            if (close()) {
                getConsumer().onFailure(th);
            }
        }

        /* access modifiers changed from: private */
        public void maybeNotifyOnCancellation() {
            if (close()) {
                getConsumer().onCancellation();
            }
        }

        private synchronized boolean isClosed() {
            return this.mIsClosed;
        }

        private boolean close() {
            synchronized (this) {
                if (this.mIsClosed) {
                    return false;
                }
                CloseableReference<CloseableImage> closeableReference = this.mSourceImageRef;
                this.mSourceImageRef = null;
                this.mIsClosed = true;
                CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
                return true;
            }
        }
    }

    class SingleUsePostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> {
        private SingleUsePostprocessorConsumer(PostprocessorConsumer postprocessorConsumer) {
            super(postprocessorConsumer);
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(@Nullable CloseableReference<CloseableImage> closeableReference, int i) {
            if (!isNotLast(i)) {
                getConsumer().onNewResult(closeableReference, i);
            }
        }
    }

    class RepeatedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> implements RepeatedPostprocessorRunner {
        private boolean mIsClosed;
        @Nullable
        private CloseableReference<CloseableImage> mSourceImageRef;

        private RepeatedPostprocessorConsumer(PostprocessorConsumer postprocessorConsumer, RepeatedPostprocessor repeatedPostprocessor, ProducerContext producerContext) {
            super(postprocessorConsumer);
            this.mIsClosed = false;
            this.mSourceImageRef = null;
            repeatedPostprocessor.setCallback(this);
            producerContext.addCallbacks(new BaseProducerContextCallbacks(PostprocessorProducer.this) {
                public void onCancellationRequested() {
                    if (RepeatedPostprocessorConsumer.this.close()) {
                        RepeatedPostprocessorConsumer.this.getConsumer().onCancellation();
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        public void onNewResultImpl(CloseableReference<CloseableImage> closeableReference, int i) {
            if (!isNotLast(i)) {
                setSourceImageRef(closeableReference);
                updateInternal();
            }
        }

        /* access modifiers changed from: protected */
        public void onFailureImpl(Throwable th) {
            if (close()) {
                getConsumer().onFailure(th);
            }
        }

        /* access modifiers changed from: protected */
        public void onCancellationImpl() {
            if (close()) {
                getConsumer().onCancellation();
            }
        }

        public synchronized void update() {
            updateInternal();
        }

        private void updateInternal() {
            synchronized (this) {
                if (!this.mIsClosed) {
                    CloseableReference<CloseableImage> cloneOrNull = CloseableReference.cloneOrNull(this.mSourceImageRef);
                    try {
                        getConsumer().onNewResult(cloneOrNull, 0);
                    } finally {
                        CloseableReference.closeSafely((CloseableReference<?>) cloneOrNull);
                    }
                }
            }
        }

        private void setSourceImageRef(CloseableReference<CloseableImage> closeableReference) {
            synchronized (this) {
                if (!this.mIsClosed) {
                    CloseableReference<CloseableImage> closeableReference2 = this.mSourceImageRef;
                    this.mSourceImageRef = CloseableReference.cloneOrNull(closeableReference);
                    CloseableReference.closeSafely((CloseableReference<?>) closeableReference2);
                }
            }
        }

        /* access modifiers changed from: private */
        public boolean close() {
            synchronized (this) {
                if (this.mIsClosed) {
                    return false;
                }
                CloseableReference<CloseableImage> closeableReference = this.mSourceImageRef;
                this.mSourceImageRef = null;
                this.mIsClosed = true;
                CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
                return true;
            }
        }
    }
}
