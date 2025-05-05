package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import android.net.Uri;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.ExceptionWithNoStacktrace;
import com.facebook.common.util.UriUtil;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegParser;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.DownsampleUtil;
import com.facebook.imageutils.BitmapUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 72\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u0004789:Bu\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0001\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u0019¢\u0006\u0002\u0010\u001aJ$\u00101\u001a\u0002022\u0012\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002042\u0006\u00105\u001a\u000206H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u000f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b!\u0010 R\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010 R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0001¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\r0\u0019¢\u0006\b\n\u0000\u001a\u0004\b/\u00100¨\u0006;"}, d2 = {"Lcom/facebook/imagepipeline/producers/DecodeProducer;", "Lcom/facebook/imagepipeline/producers/Producer;", "Lcom/facebook/common/references/CloseableReference;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "byteArrayPool", "Lcom/facebook/common/memory/ByteArrayPool;", "executor", "Ljava/util/concurrent/Executor;", "imageDecoder", "Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "progressiveJpegConfig", "Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "downsampleEnabled", "", "downsampleEnabledForNetwork", "decodeCancellationEnabled", "inputProducer", "Lcom/facebook/imagepipeline/image/EncodedImage;", "maxBitmapSize", "", "closeableReferenceFactory", "Lcom/facebook/imagepipeline/core/CloseableReferenceFactory;", "reclaimMemoryRunnable", "Ljava/lang/Runnable;", "recoverFromDecoderOOM", "Lcom/facebook/common/internal/Supplier;", "(Lcom/facebook/common/memory/ByteArrayPool;Ljava/util/concurrent/Executor;Lcom/facebook/imagepipeline/decoder/ImageDecoder;Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;ZZZLcom/facebook/imagepipeline/producers/Producer;ILcom/facebook/imagepipeline/core/CloseableReferenceFactory;Ljava/lang/Runnable;Lcom/facebook/common/internal/Supplier;)V", "getByteArrayPool", "()Lcom/facebook/common/memory/ByteArrayPool;", "getCloseableReferenceFactory", "()Lcom/facebook/imagepipeline/core/CloseableReferenceFactory;", "getDecodeCancellationEnabled", "()Z", "getDownsampleEnabled", "getDownsampleEnabledForNetwork", "getExecutor", "()Ljava/util/concurrent/Executor;", "getImageDecoder", "()Lcom/facebook/imagepipeline/decoder/ImageDecoder;", "getInputProducer", "()Lcom/facebook/imagepipeline/producers/Producer;", "getMaxBitmapSize", "()I", "getProgressiveJpegConfig", "()Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "getReclaimMemoryRunnable", "()Ljava/lang/Runnable;", "getRecoverFromDecoderOOM", "()Lcom/facebook/common/internal/Supplier;", "produceResults", "", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "context", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "Companion", "LocalImagesProgressiveDecoder", "NetworkImagesProgressiveDecoder", "ProgressiveDecoder", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DecodeProducer.kt */
public final class DecodeProducer implements Producer<CloseableReference<CloseableImage>> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int DECODE_EXCEPTION_MESSAGE_NUM_HEADER_BYTES = 10;
    public static final String ENCODED_IMAGE_SIZE = "encodedImageSize";
    public static final String EXTRA_BITMAP_BYTES = "byteCount";
    public static final String EXTRA_BITMAP_SIZE = "bitmapSize";
    public static final String EXTRA_HAS_GOOD_QUALITY = "hasGoodQuality";
    public static final String EXTRA_IMAGE_FORMAT_NAME = "imageFormat";
    public static final String EXTRA_IS_FINAL = "isFinal";
    private static final int MAX_BITMAP_SIZE = 104857600;
    public static final String NON_FATAL_DECODE_ERROR = "non_fatal_decode_error";
    public static final String PRODUCER_NAME = "DecodeProducer";
    public static final String REQUESTED_IMAGE_SIZE = "requestedImageSize";
    public static final String SAMPLE_SIZE = "sampleSize";
    private final ByteArrayPool byteArrayPool;
    private final CloseableReferenceFactory closeableReferenceFactory;
    private final boolean decodeCancellationEnabled;
    private final boolean downsampleEnabled;
    private final boolean downsampleEnabledForNetwork;
    private final Executor executor;
    private final ImageDecoder imageDecoder;
    private final Producer<EncodedImage> inputProducer;
    private final int maxBitmapSize;
    private final ProgressiveJpegConfig progressiveJpegConfig;
    private final Runnable reclaimMemoryRunnable;
    private final Supplier<Boolean> recoverFromDecoderOOM;

    public DecodeProducer(ByteArrayPool byteArrayPool2, Executor executor2, ImageDecoder imageDecoder2, ProgressiveJpegConfig progressiveJpegConfig2, boolean z, boolean z2, boolean z3, Producer<EncodedImage> producer, int i, CloseableReferenceFactory closeableReferenceFactory2, Runnable runnable, Supplier<Boolean> supplier) {
        Intrinsics.checkNotNullParameter(byteArrayPool2, "byteArrayPool");
        Intrinsics.checkNotNullParameter(executor2, "executor");
        Intrinsics.checkNotNullParameter(imageDecoder2, "imageDecoder");
        Intrinsics.checkNotNullParameter(progressiveJpegConfig2, "progressiveJpegConfig");
        Intrinsics.checkNotNullParameter(producer, "inputProducer");
        Intrinsics.checkNotNullParameter(closeableReferenceFactory2, "closeableReferenceFactory");
        Intrinsics.checkNotNullParameter(supplier, "recoverFromDecoderOOM");
        this.byteArrayPool = byteArrayPool2;
        this.executor = executor2;
        this.imageDecoder = imageDecoder2;
        this.progressiveJpegConfig = progressiveJpegConfig2;
        this.downsampleEnabled = z;
        this.downsampleEnabledForNetwork = z2;
        this.decodeCancellationEnabled = z3;
        this.inputProducer = producer;
        this.maxBitmapSize = i;
        this.closeableReferenceFactory = closeableReferenceFactory2;
        this.reclaimMemoryRunnable = runnable;
        this.recoverFromDecoderOOM = supplier;
    }

    public final ByteArrayPool getByteArrayPool() {
        return this.byteArrayPool;
    }

    public final Executor getExecutor() {
        return this.executor;
    }

    public final ImageDecoder getImageDecoder() {
        return this.imageDecoder;
    }

    public final ProgressiveJpegConfig getProgressiveJpegConfig() {
        return this.progressiveJpegConfig;
    }

    public final boolean getDownsampleEnabled() {
        return this.downsampleEnabled;
    }

    public final boolean getDownsampleEnabledForNetwork() {
        return this.downsampleEnabledForNetwork;
    }

    public final boolean getDecodeCancellationEnabled() {
        return this.decodeCancellationEnabled;
    }

    public final Producer<EncodedImage> getInputProducer() {
        return this.inputProducer;
    }

    public final int getMaxBitmapSize() {
        return this.maxBitmapSize;
    }

    public final CloseableReferenceFactory getCloseableReferenceFactory() {
        return this.closeableReferenceFactory;
    }

    public final Runnable getReclaimMemoryRunnable() {
        return this.reclaimMemoryRunnable;
    }

    public final Supplier<Boolean> getRecoverFromDecoderOOM() {
        return this.recoverFromDecoderOOM;
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        ProgressiveDecoder progressiveDecoder;
        ProgressiveDecoder progressiveDecoder2;
        Intrinsics.checkNotNullParameter(consumer, "consumer");
        Intrinsics.checkNotNullParameter(producerContext, "context");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            if (!UriUtil.isNetworkUri(producerContext.getImageRequest().getSourceUri())) {
                progressiveDecoder2 = new LocalImagesProgressiveDecoder(this, consumer, producerContext, this.decodeCancellationEnabled, this.maxBitmapSize);
            } else {
                progressiveDecoder2 = new NetworkImagesProgressiveDecoder(this, consumer, producerContext, new ProgressiveJpegParser(this.byteArrayPool), this.progressiveJpegConfig, this.decodeCancellationEnabled, this.maxBitmapSize);
            }
            this.inputProducer.produceResults(progressiveDecoder2, producerContext);
            return;
        }
        FrescoSystrace.beginSection("DecodeProducer#produceResults");
        try {
            if (!UriUtil.isNetworkUri(producerContext.getImageRequest().getSourceUri())) {
                progressiveDecoder = new LocalImagesProgressiveDecoder(this, consumer, producerContext, this.decodeCancellationEnabled, this.maxBitmapSize);
            } else {
                progressiveDecoder = new NetworkImagesProgressiveDecoder(this, consumer, producerContext, new ProgressiveJpegParser(this.byteArrayPool), this.progressiveJpegConfig, this.decodeCancellationEnabled, this.maxBitmapSize);
            }
            this.inputProducer.produceResults(progressiveDecoder, producerContext);
            Unit unit = Unit.INSTANCE;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    @Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0003\n\u0002\b\r\n\u0002\u0010\u0007\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u0016\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0001B1\u0012\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ \u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00022\u0006\u0010#\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\fH\u0002JX\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\u00042\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020\u000f2\u0006\u0010,\u001a\u00020\u000f2\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u000fH\u0002J\u0010\u0010/\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\u0002H$J\b\u00100\u001a\u00020!H\u0002J\u0010\u00101\u001a\u00020!2\u0006\u00102\u001a\u000203H\u0002J\u001a\u00104\u001a\u00020!2\b\u00105\u001a\u0004\u0018\u00010\u00042\u0006\u0010#\u001a\u00020\fH\u0002J\"\u00106\u001a\u0004\u0018\u00010\u00042\u0006\u0010\"\u001a\u00020\u00022\u0006\u00107\u001a\u00020\f2\u0006\u0010)\u001a\u00020\u001dH\u0002J\u0010\u00108\u001a\u00020!2\u0006\u00109\u001a\u00020\nH\u0002J\u0010\u0010:\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0002H\u0002J\b\u0010;\u001a\u00020!H\u0016J\u0010\u0010<\u001a\u00020!2\u0006\u00102\u001a\u000203H\u0016J\u001a\u0010=\u001a\u00020!2\b\u0010>\u001a\u0004\u0018\u00010\u00022\u0006\u0010#\u001a\u00020\fH\u0016J\u0010\u0010?\u001a\u00020!2\u0006\u0010@\u001a\u00020AH\u0014J\"\u0010B\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00022\b\u0010&\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0015\u001a\u00020\fH\u0002J\u001a\u0010C\u001a\u00020\n2\b\u0010D\u001a\u0004\u0018\u00010\u00022\u0006\u0010#\u001a\u00020\fH\u0014R\u000e\u0010\u000e\u001a\u00020\u000fXD¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00020\n8B@\u0002X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001c\u001a\u00020\u001dX¤\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u0006E"}, d2 = {"Lcom/facebook/imagepipeline/producers/DecodeProducer$ProgressiveDecoder;", "Lcom/facebook/imagepipeline/producers/DelegatingConsumer;", "Lcom/facebook/imagepipeline/image/EncodedImage;", "Lcom/facebook/common/references/CloseableReference;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "producerContext", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "decodeCancellationEnabled", "", "maxBitmapSize", "", "(Lcom/facebook/imagepipeline/producers/DecodeProducer;Lcom/facebook/imagepipeline/producers/Consumer;Lcom/facebook/imagepipeline/producers/ProducerContext;ZI)V", "TAG", "", "imageDecodeOptions", "Lcom/facebook/imagepipeline/common/ImageDecodeOptions;", "isFinished", "jobScheduler", "Lcom/facebook/imagepipeline/producers/JobScheduler;", "lastScheduledScanNumber", "getLastScheduledScanNumber", "()I", "setLastScheduledScanNumber", "(I)V", "producerListener", "Lcom/facebook/imagepipeline/producers/ProducerListener2;", "qualityInfo", "Lcom/facebook/imagepipeline/image/QualityInfo;", "getQualityInfo", "()Lcom/facebook/imagepipeline/image/QualityInfo;", "doDecode", "", "encodedImage", "status", "getExtraMap", "", "image", "queueTime", "", "quality", "isFinal", "imageFormatName", "encodedImageSize", "requestImageSize", "sampleSize", "getIntermediateImageEndOffset", "handleCancellation", "handleError", "t", "", "handleResult", "decodedImage", "internalDecode", "length", "maybeFinish", "shouldFinish", "maybeIncreaseSampleSize", "onCancellationImpl", "onFailureImpl", "onNewResultImpl", "newResult", "onProgressUpdateImpl", "progress", "", "setImageExtras", "updateDecodeJob", "ref", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DecodeProducer.kt */
    private abstract class ProgressiveDecoder extends DelegatingConsumer<EncodedImage, CloseableReference<CloseableImage>> {
        private final String TAG = "ProgressiveDecoder";
        private final ImageDecodeOptions imageDecodeOptions;
        private boolean isFinished;
        /* access modifiers changed from: private */
        public final JobScheduler jobScheduler;
        private int lastScheduledScanNumber;
        /* access modifiers changed from: private */
        public final ProducerContext producerContext;
        private final ProducerListener2 producerListener;
        final /* synthetic */ DecodeProducer this$0;

        /* access modifiers changed from: protected */
        public abstract int getIntermediateImageEndOffset(EncodedImage encodedImage);

        /* access modifiers changed from: protected */
        public abstract QualityInfo getQualityInfo();

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ProgressiveDecoder(DecodeProducer decodeProducer, Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext2, final boolean z, int i) {
            super(consumer);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            Intrinsics.checkNotNullParameter(producerContext2, "producerContext");
            this.this$0 = decodeProducer;
            this.producerContext = producerContext2;
            this.producerListener = producerContext2.getProducerListener();
            ImageDecodeOptions imageDecodeOptions2 = producerContext2.getImageRequest().getImageDecodeOptions();
            Intrinsics.checkNotNullExpressionValue(imageDecodeOptions2, "producerContext.imageRequest.imageDecodeOptions");
            this.imageDecodeOptions = imageDecodeOptions2;
            this.jobScheduler = new JobScheduler(decodeProducer.getExecutor(), new DecodeProducer$ProgressiveDecoder$$ExternalSyntheticLambda0(this, decodeProducer, i), imageDecodeOptions2.minDecodeIntervalMs);
            producerContext2.addCallbacks(new BaseProducerContextCallbacks(this) {
                final /* synthetic */ ProgressiveDecoder this$0;

                {
                    this.this$0 = r1;
                }

                public void onIsIntermediateResultExpectedChanged() {
                    if (this.this$0.producerContext.isIntermediateResultExpected()) {
                        this.this$0.jobScheduler.scheduleJob();
                    }
                }

                public void onCancellationRequested() {
                    if (z) {
                        this.this$0.handleCancellation();
                    }
                }
            });
        }

        /* access modifiers changed from: protected */
        public final int getLastScheduledScanNumber() {
            return this.lastScheduledScanNumber;
        }

        /* access modifiers changed from: protected */
        public final void setLastScheduledScanNumber(int i) {
            this.lastScheduledScanNumber = i;
        }

        private final void maybeIncreaseSampleSize(EncodedImage encodedImage) {
            if (encodedImage.getImageFormat() == DefaultImageFormats.JPEG) {
                encodedImage.setSampleSize(DownsampleUtil.determineSampleSizeJPEG(encodedImage, BitmapUtil.getPixelSizeForBitmapConfig(this.imageDecodeOptions.bitmapConfig), DecodeProducer.MAX_BITMAP_SIZE));
            }
        }

        public void onNewResultImpl(EncodedImage encodedImage, int i) {
            FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
            if (!FrescoSystrace.isTracing()) {
                boolean isLast = BaseConsumer.isLast(i);
                if (isLast) {
                    if (encodedImage == null) {
                        boolean areEqual = Intrinsics.areEqual(this.producerContext.getExtra("cached_value_found"), (Object) true);
                        if (!this.producerContext.getImagePipelineConfig().getExperiments().getCancelDecodeOnCacheMiss() || this.producerContext.getLowestPermittedRequestLevel() == ImageRequest.RequestLevel.FULL_FETCH || areEqual) {
                            handleError(new ExceptionWithNoStacktrace("Encoded image is null."));
                            return;
                        }
                    } else if (!encodedImage.isValid()) {
                        handleError(new ExceptionWithNoStacktrace("Encoded image is not valid."));
                        return;
                    }
                }
                if (updateDecodeJob(encodedImage, i)) {
                    boolean statusHasFlag = BaseConsumer.statusHasFlag(i, 4);
                    if (isLast || statusHasFlag || this.producerContext.isIntermediateResultExpected()) {
                        this.jobScheduler.scheduleJob();
                        return;
                    }
                    return;
                }
                return;
            }
            FrescoSystrace.beginSection("DecodeProducer#onNewResultImpl");
            try {
                boolean isLast2 = BaseConsumer.isLast(i);
                if (isLast2) {
                    if (encodedImage == null) {
                        boolean areEqual2 = Intrinsics.areEqual(this.producerContext.getExtra("cached_value_found"), (Object) true);
                        if (!this.producerContext.getImagePipelineConfig().getExperiments().getCancelDecodeOnCacheMiss() || this.producerContext.getLowestPermittedRequestLevel() == ImageRequest.RequestLevel.FULL_FETCH || areEqual2) {
                            handleError(new ExceptionWithNoStacktrace("Encoded image is null."));
                            return;
                        }
                    } else if (!encodedImage.isValid()) {
                        handleError(new ExceptionWithNoStacktrace("Encoded image is not valid."));
                        FrescoSystrace.endSection();
                        return;
                    }
                }
                if (!updateDecodeJob(encodedImage, i)) {
                    FrescoSystrace.endSection();
                    return;
                }
                boolean statusHasFlag2 = BaseConsumer.statusHasFlag(i, 4);
                if (isLast2 || statusHasFlag2 || this.producerContext.isIntermediateResultExpected()) {
                    this.jobScheduler.scheduleJob();
                }
                Unit unit = Unit.INSTANCE;
                FrescoSystrace.endSection();
            } finally {
                FrescoSystrace.endSection();
            }
        }

        /* access modifiers changed from: protected */
        public void onProgressUpdateImpl(float f) {
            super.onProgressUpdateImpl(f * 0.99f);
        }

        public void onFailureImpl(Throwable th) {
            Intrinsics.checkNotNullParameter(th, "t");
            handleError(th);
        }

        public void onCancellationImpl() {
            handleCancellation();
        }

        /* access modifiers changed from: protected */
        public boolean updateDecodeJob(EncodedImage encodedImage, int i) {
            return this.jobScheduler.updateJob(encodedImage, i);
        }

        /* JADX WARNING: Removed duplicated region for block: B:33:0x00c6 A[Catch:{ all -> 0x0164 }] */
        /* JADX WARNING: Removed duplicated region for block: B:44:0x00e7  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final void doDecode(com.facebook.imagepipeline.image.EncodedImage r20, int r21, int r22) {
            /*
                r19 = this;
                r11 = r19
                r12 = r20
                r0 = r21
                java.lang.String r1 = "quality"
                java.lang.String r13 = "DecodeProducer"
                com.facebook.imageformat.ImageFormat r2 = r20.getImageFormat()
                com.facebook.imageformat.ImageFormat r3 = com.facebook.imageformat.DefaultImageFormats.JPEG
                if (r2 == r3) goto L_0x0019
                boolean r2 = com.facebook.imagepipeline.producers.BaseConsumer.isNotLast(r21)
                if (r2 == 0) goto L_0x0019
                return
            L_0x0019:
                boolean r2 = r11.isFinished
                if (r2 != 0) goto L_0x0169
                boolean r2 = com.facebook.imagepipeline.image.EncodedImage.isValid(r20)
                if (r2 != 0) goto L_0x0025
                goto L_0x0169
            L_0x0025:
                com.facebook.imageformat.ImageFormat r2 = r20.getImageFormat()
                java.lang.String r3 = "unknown"
                if (r2 == 0) goto L_0x0037
                java.lang.String r2 = r2.getName()
                if (r2 != 0) goto L_0x0035
                goto L_0x0037
            L_0x0035:
                r7 = r2
                goto L_0x0038
            L_0x0037:
                r7 = r3
            L_0x0038:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                int r4 = r20.getWidth()
                java.lang.StringBuilder r2 = r2.append(r4)
                r4 = 120(0x78, float:1.68E-43)
                java.lang.StringBuilder r2 = r2.append(r4)
                int r5 = r20.getHeight()
                java.lang.StringBuilder r2 = r2.append(r5)
                java.lang.String r8 = r2.toString()
                int r2 = r20.getSampleSize()
                java.lang.String r10 = java.lang.String.valueOf(r2)
                boolean r6 = com.facebook.imagepipeline.producers.BaseConsumer.isLast(r21)
                if (r6 == 0) goto L_0x006f
                r9 = 8
                boolean r9 = com.facebook.imagepipeline.producers.BaseConsumer.statusHasFlag(r0, r9)
                if (r9 != 0) goto L_0x006f
                r9 = 1
                goto L_0x0070
            L_0x006f:
                r9 = 0
            L_0x0070:
                r14 = 4
                boolean r15 = com.facebook.imagepipeline.producers.BaseConsumer.statusHasFlag(r0, r14)
                com.facebook.imagepipeline.producers.ProducerContext r2 = r11.producerContext
                com.facebook.imagepipeline.request.ImageRequest r2 = r2.getImageRequest()
                com.facebook.imagepipeline.common.ResizeOptions r2 = r2.getResizeOptions()
                if (r2 == 0) goto L_0x009c
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                int r14 = r2.width
                java.lang.StringBuilder r3 = r3.append(r14)
                java.lang.StringBuilder r3 = r3.append(r4)
                int r2 = r2.height
                java.lang.StringBuilder r2 = r3.append(r2)
                java.lang.String r2 = r2.toString()
                r14 = r2
                goto L_0x009d
            L_0x009c:
                r14 = r3
            L_0x009d:
                com.facebook.imagepipeline.producers.JobScheduler r2 = r11.jobScheduler     // Catch:{ all -> 0x0164 }
                long r3 = r2.getQueuedTime()     // Catch:{ all -> 0x0164 }
                com.facebook.imagepipeline.producers.ProducerContext r2 = r11.producerContext     // Catch:{ all -> 0x0164 }
                com.facebook.imagepipeline.request.ImageRequest r2 = r2.getImageRequest()     // Catch:{ all -> 0x0164 }
                android.net.Uri r2 = r2.getSourceUri()     // Catch:{ all -> 0x0164 }
                java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0164 }
                java.lang.String r5 = "producerContext.imageRequest.sourceUri.toString()"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r5)     // Catch:{ all -> 0x0164 }
                if (r9 != 0) goto L_0x00c0
                if (r15 == 0) goto L_0x00bb
                goto L_0x00c0
            L_0x00bb:
                int r5 = r19.getIntermediateImageEndOffset(r20)     // Catch:{ all -> 0x0164 }
                goto L_0x00c4
            L_0x00c0:
                int r5 = r20.getSize()     // Catch:{ all -> 0x0164 }
            L_0x00c4:
                if (r9 != 0) goto L_0x00ce
                if (r15 == 0) goto L_0x00c9
                goto L_0x00ce
            L_0x00c9:
                com.facebook.imagepipeline.image.QualityInfo r9 = r19.getQualityInfo()     // Catch:{ all -> 0x0164 }
                goto L_0x00d0
            L_0x00ce:
                com.facebook.imagepipeline.image.QualityInfo r9 = com.facebook.imagepipeline.image.ImmutableQualityInfo.FULL_QUALITY     // Catch:{ all -> 0x0164 }
            L_0x00d0:
                com.facebook.imagepipeline.producers.ProducerListener2 r15 = r11.producerListener     // Catch:{ all -> 0x0164 }
                r18 = r2
                com.facebook.imagepipeline.producers.ProducerContext r2 = r11.producerContext     // Catch:{ all -> 0x0164 }
                r15.onProducerStart(r2, r13)     // Catch:{ all -> 0x0164 }
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)     // Catch:{ DecodeException -> 0x010f }
                com.facebook.imagepipeline.image.CloseableImage r15 = r11.internalDecode(r12, r5, r9)     // Catch:{ DecodeException -> 0x010f }
                int r1 = r20.getSampleSize()     // Catch:{ Exception -> 0x0105 }
                r2 = 1
                if (r1 == r2) goto L_0x00e9
                r0 = r0 | 16
            L_0x00e9:
                r1 = r19
                r2 = r15
                r5 = r9
                r9 = r14
                java.util.Map r1 = r1.getExtraMap(r2, r3, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0164 }
                com.facebook.imagepipeline.producers.ProducerListener2 r2 = r11.producerListener     // Catch:{ all -> 0x0164 }
                com.facebook.imagepipeline.producers.ProducerContext r3 = r11.producerContext     // Catch:{ all -> 0x0164 }
                r2.onProducerFinishWithSuccess(r3, r13, r1)     // Catch:{ all -> 0x0164 }
                r1 = r22
                r11.setImageExtras(r12, r15, r1)     // Catch:{ all -> 0x0164 }
                r11.handleResult(r15, r0)     // Catch:{ all -> 0x0164 }
                com.facebook.imagepipeline.image.EncodedImage.closeSafely(r20)
                return
            L_0x0105:
                r0 = move-exception
                r16 = r13
                r2 = r15
                goto L_0x0144
            L_0x010a:
                r0 = move-exception
                r16 = r13
            L_0x010d:
                r2 = 0
                goto L_0x0144
            L_0x010f:
                r0 = move-exception
                com.facebook.imagepipeline.image.EncodedImage r5 = r0.getEncodedImage()     // Catch:{ Exception -> 0x010a }
                java.lang.String r15 = r11.TAG     // Catch:{ Exception -> 0x010a }
                java.lang.String r2 = "%s, {uri: %s, firstEncodedBytes: %s, length: %d}"
                r12 = 4
                java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch:{ Exception -> 0x010a }
                java.lang.String r17 = r0.getMessage()     // Catch:{ Exception -> 0x010a }
                r16 = 0
                r12[r16] = r17     // Catch:{ Exception -> 0x010a }
                r16 = 1
                r12[r16] = r18     // Catch:{ Exception -> 0x010a }
                r16 = r13
                r13 = 10
                java.lang.String r13 = r5.getFirstBytesAsHexString(r13)     // Catch:{ Exception -> 0x0142 }
                r17 = 2
                r12[r17] = r13     // Catch:{ Exception -> 0x0142 }
                int r5 = r5.getSize()     // Catch:{ Exception -> 0x0142 }
                java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x0142 }
                r13 = 3
                r12[r13] = r5     // Catch:{ Exception -> 0x0142 }
                com.facebook.common.logging.FLog.w((java.lang.String) r15, (java.lang.String) r2, (java.lang.Object[]) r12)     // Catch:{ Exception -> 0x0142 }
                throw r0     // Catch:{ Exception -> 0x0142 }
            L_0x0142:
                r0 = move-exception
                goto L_0x010d
            L_0x0144:
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)     // Catch:{ all -> 0x0164 }
                r1 = r19
                r5 = r9
                r9 = r14
                java.util.Map r1 = r1.getExtraMap(r2, r3, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0164 }
                com.facebook.imagepipeline.producers.ProducerListener2 r2 = r11.producerListener     // Catch:{ all -> 0x0164 }
                com.facebook.imagepipeline.producers.ProducerContext r3 = r11.producerContext     // Catch:{ all -> 0x0164 }
                r4 = r0
                java.lang.Throwable r4 = (java.lang.Throwable) r4     // Catch:{ all -> 0x0164 }
                r5 = r16
                r2.onProducerFinishWithFailure(r3, r5, r4, r1)     // Catch:{ all -> 0x0164 }
                java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x0164 }
                r11.handleError(r0)     // Catch:{ all -> 0x0164 }
                com.facebook.imagepipeline.image.EncodedImage.closeSafely(r20)
                return
            L_0x0164:
                r0 = move-exception
                com.facebook.imagepipeline.image.EncodedImage.closeSafely(r20)
                throw r0
            L_0x0169:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.doDecode(com.facebook.imagepipeline.image.EncodedImage, int, int):void");
        }

        private final CloseableImage internalDecode(EncodedImage encodedImage, int i, QualityInfo qualityInfo) {
            boolean z;
            if (this.this$0.getReclaimMemoryRunnable() != null) {
                Boolean bool = this.this$0.getRecoverFromDecoderOOM().get();
                Intrinsics.checkNotNullExpressionValue(bool, "recoverFromDecoderOOM.get()");
                if (bool.booleanValue()) {
                    z = true;
                    return this.this$0.getImageDecoder().decode(encodedImage, i, qualityInfo, this.imageDecodeOptions);
                }
            }
            z = false;
            try {
                return this.this$0.getImageDecoder().decode(encodedImage, i, qualityInfo, this.imageDecodeOptions);
            } catch (OutOfMemoryError e) {
                if (z) {
                    Runnable reclaimMemoryRunnable = this.this$0.getReclaimMemoryRunnable();
                    if (reclaimMemoryRunnable != null) {
                        reclaimMemoryRunnable.run();
                    }
                    System.gc();
                    return this.this$0.getImageDecoder().decode(encodedImage, i, qualityInfo, this.imageDecodeOptions);
                }
                throw e;
            }
        }

        private final void setImageExtras(EncodedImage encodedImage, CloseableImage closeableImage, int i) {
            this.producerContext.putExtra("encoded_width", Integer.valueOf(encodedImage.getWidth()));
            this.producerContext.putExtra("encoded_height", Integer.valueOf(encodedImage.getHeight()));
            this.producerContext.putExtra("encoded_size", Integer.valueOf(encodedImage.getSize()));
            this.producerContext.putExtra("image_color_space", encodedImage.getColorSpace());
            if (closeableImage instanceof CloseableBitmap) {
                this.producerContext.putExtra("bitmap_config", String.valueOf(((CloseableBitmap) closeableImage).getUnderlyingBitmap().getConfig()));
            }
            if (closeableImage != null) {
                closeableImage.putExtras(this.producerContext.getExtras());
            }
            this.producerContext.putExtra("last_scan_num", Integer.valueOf(i));
        }

        private final Map<String, String> getExtraMap(CloseableImage closeableImage, long j, QualityInfo qualityInfo, boolean z, String str, String str2, String str3, String str4) {
            Map<String, Object> extras;
            Object obj;
            CloseableImage closeableImage2 = closeableImage;
            String str5 = str;
            String str6 = str2;
            String str7 = str3;
            String str8 = str4;
            String str9 = null;
            if (!this.producerListener.requiresExtraMap(this.producerContext, DecodeProducer.PRODUCER_NAME)) {
                return null;
            }
            String valueOf = String.valueOf(j);
            String valueOf2 = String.valueOf(qualityInfo.isOfGoodEnoughQuality());
            String valueOf3 = String.valueOf(z);
            if (!(closeableImage2 == null || (extras = closeableImage.getExtras()) == null || (obj = extras.get("non_fatal_decode_error")) == null)) {
                str9 = obj.toString();
            }
            Object obj2 = "non_fatal_decode_error";
            String str10 = str9;
            if (closeableImage2 instanceof CloseableStaticBitmap) {
                Bitmap underlyingBitmap = ((CloseableStaticBitmap) closeableImage2).getUnderlyingBitmap();
                Intrinsics.checkNotNullExpressionValue(underlyingBitmap, "image.underlyingBitmap");
                String sb = new StringBuilder().append(underlyingBitmap.getWidth()).append('x').append(underlyingBitmap.getHeight()).toString();
                Map hashMap = new HashMap(8);
                hashMap.put("bitmapSize", sb);
                hashMap.put("queueTime", valueOf);
                hashMap.put("hasGoodQuality", valueOf2);
                hashMap.put("isFinal", valueOf3);
                hashMap.put("encodedImageSize", str6);
                hashMap.put("imageFormat", str5);
                hashMap.put("requestedImageSize", str7);
                hashMap.put("sampleSize", str4);
                hashMap.put("byteCount", underlyingBitmap.getByteCount() + "");
                if (str10 != null) {
                    hashMap.put(obj2, str10);
                }
                return ImmutableMap.copyOf(hashMap);
            }
            String str11 = str8;
            String str12 = str10;
            Map hashMap2 = new HashMap(7);
            hashMap2.put("queueTime", valueOf);
            hashMap2.put("hasGoodQuality", valueOf2);
            hashMap2.put("isFinal", valueOf3);
            hashMap2.put("encodedImageSize", str6);
            hashMap2.put("imageFormat", str5);
            hashMap2.put("requestedImageSize", str7);
            hashMap2.put("sampleSize", str11);
            if (str10 != null) {
                hashMap2.put(obj2, str10);
            }
            return ImmutableMap.copyOf(hashMap2);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x0021, code lost:
            return;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final void maybeFinish(boolean r2) {
            /*
                r1 = this;
                monitor-enter(r1)
                if (r2 == 0) goto L_0x0020
                boolean r2 = r1.isFinished     // Catch:{ all -> 0x001d }
                if (r2 == 0) goto L_0x0008
                goto L_0x0020
            L_0x0008:
                com.facebook.imagepipeline.producers.Consumer r2 = r1.getConsumer()     // Catch:{ all -> 0x001d }
                r0 = 1065353216(0x3f800000, float:1.0)
                r2.onProgressUpdate(r0)     // Catch:{ all -> 0x001d }
                r2 = 1
                r1.isFinished = r2     // Catch:{ all -> 0x001d }
                kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x001d }
                monitor-exit(r1)
                com.facebook.imagepipeline.producers.JobScheduler r2 = r1.jobScheduler
                r2.clearJob()
                return
            L_0x001d:
                r2 = move-exception
                monitor-exit(r1)
                throw r2
            L_0x0020:
                monitor-exit(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DecodeProducer.ProgressiveDecoder.maybeFinish(boolean):void");
        }

        private final void handleResult(CloseableImage closeableImage, int i) {
            CloseableReference create = this.this$0.getCloseableReferenceFactory().create(closeableImage);
            try {
                maybeFinish(BaseConsumer.isLast(i));
                getConsumer().onNewResult(create, i);
            } finally {
                CloseableReference.closeSafely((CloseableReference<?>) create);
            }
        }

        private final void handleError(Throwable th) {
            maybeFinish(true);
            getConsumer().onFailure(th);
        }

        /* access modifiers changed from: private */
        public final void handleCancellation() {
            maybeFinish(true);
            getConsumer().onCancellation();
        }

        /* access modifiers changed from: private */
        public static final void _init_$lambda$2(ProgressiveDecoder progressiveDecoder, DecodeProducer decodeProducer, int i, EncodedImage encodedImage, int i2) {
            Intrinsics.checkNotNullParameter(progressiveDecoder, "this$0");
            Intrinsics.checkNotNullParameter(decodeProducer, "this$1");
            if (encodedImage != null) {
                ImageRequest imageRequest = progressiveDecoder.producerContext.getImageRequest();
                progressiveDecoder.producerContext.putExtra("image_format", encodedImage.getImageFormat().getName());
                Uri sourceUri = imageRequest.getSourceUri();
                encodedImage.setSource(sourceUri != null ? sourceUri.toString() : null);
                if ((decodeProducer.getDownsampleEnabled() || !BaseConsumer.statusHasFlag(i2, 16)) && (decodeProducer.getDownsampleEnabledForNetwork() || !UriUtil.isNetworkUri(imageRequest.getSourceUri()))) {
                    RotationOptions rotationOptions = imageRequest.getRotationOptions();
                    Intrinsics.checkNotNullExpressionValue(rotationOptions, "request.rotationOptions");
                    encodedImage.setSampleSize(DownsampleUtil.determineSampleSize(rotationOptions, imageRequest.getResizeOptions(), encodedImage, i));
                }
                if (progressiveDecoder.producerContext.getImagePipelineConfig().getExperiments().getDownsampleIfLargeBitmap()) {
                    progressiveDecoder.maybeIncreaseSampleSize(encodedImage);
                }
                progressiveDecoder.doDecode(encodedImage, i2, progressiveDecoder.lastScheduledScanNumber);
            }
        }
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00060\u0001R\u00020\u0002B1\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0014J\u001a\u0010\u0015\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0016\u001a\u00020\fH\u0014R\u0014\u0010\u000e\u001a\u00020\u000f8TX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, d2 = {"Lcom/facebook/imagepipeline/producers/DecodeProducer$LocalImagesProgressiveDecoder;", "Lcom/facebook/imagepipeline/producers/DecodeProducer$ProgressiveDecoder;", "Lcom/facebook/imagepipeline/producers/DecodeProducer;", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "Lcom/facebook/common/references/CloseableReference;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "producerContext", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "decodeCancellationEnabled", "", "maxBitmapSize", "", "(Lcom/facebook/imagepipeline/producers/DecodeProducer;Lcom/facebook/imagepipeline/producers/Consumer;Lcom/facebook/imagepipeline/producers/ProducerContext;ZI)V", "qualityInfo", "Lcom/facebook/imagepipeline/image/QualityInfo;", "getQualityInfo", "()Lcom/facebook/imagepipeline/image/QualityInfo;", "getIntermediateImageEndOffset", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "updateDecodeJob", "status", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DecodeProducer.kt */
    private final class LocalImagesProgressiveDecoder extends ProgressiveDecoder {
        final /* synthetic */ DecodeProducer this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public LocalImagesProgressiveDecoder(DecodeProducer decodeProducer, Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, boolean z, int i) {
            super(decodeProducer, consumer, producerContext, z, i);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            Intrinsics.checkNotNullParameter(producerContext, "producerContext");
            this.this$0 = decodeProducer;
        }

        /* access modifiers changed from: protected */
        public synchronized boolean updateDecodeJob(EncodedImage encodedImage, int i) {
            boolean z;
            if (BaseConsumer.isNotLast(i)) {
                z = false;
            } else {
                z = super.updateDecodeJob(encodedImage, i);
            }
            return z;
        }

        /* access modifiers changed from: protected */
        public int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
            return encodedImage.getSize();
        }

        /* access modifiers changed from: protected */
        public QualityInfo getQualityInfo() {
            QualityInfo of = ImmutableQualityInfo.of(0, false, false);
            Intrinsics.checkNotNullExpressionValue(of, "of(0, false, false)");
            return of;
        }
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00060\u0001R\u00020\u0002BA\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u0010\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J\u001a\u0010\u001d\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001e\u001a\u00020\u0010H\u0014R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178TX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001f"}, d2 = {"Lcom/facebook/imagepipeline/producers/DecodeProducer$NetworkImagesProgressiveDecoder;", "Lcom/facebook/imagepipeline/producers/DecodeProducer$ProgressiveDecoder;", "Lcom/facebook/imagepipeline/producers/DecodeProducer;", "consumer", "Lcom/facebook/imagepipeline/producers/Consumer;", "Lcom/facebook/common/references/CloseableReference;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "producerContext", "Lcom/facebook/imagepipeline/producers/ProducerContext;", "progressiveJpegParser", "Lcom/facebook/imagepipeline/decoder/ProgressiveJpegParser;", "progressiveJpegConfig", "Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "decodeCancellationEnabled", "", "maxBitmapSize", "", "(Lcom/facebook/imagepipeline/producers/DecodeProducer;Lcom/facebook/imagepipeline/producers/Consumer;Lcom/facebook/imagepipeline/producers/ProducerContext;Lcom/facebook/imagepipeline/decoder/ProgressiveJpegParser;Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;ZI)V", "getProgressiveJpegConfig", "()Lcom/facebook/imagepipeline/decoder/ProgressiveJpegConfig;", "getProgressiveJpegParser", "()Lcom/facebook/imagepipeline/decoder/ProgressiveJpegParser;", "qualityInfo", "Lcom/facebook/imagepipeline/image/QualityInfo;", "getQualityInfo", "()Lcom/facebook/imagepipeline/image/QualityInfo;", "getIntermediateImageEndOffset", "encodedImage", "Lcom/facebook/imagepipeline/image/EncodedImage;", "updateDecodeJob", "status", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DecodeProducer.kt */
    private final class NetworkImagesProgressiveDecoder extends ProgressiveDecoder {
        private final ProgressiveJpegConfig progressiveJpegConfig;
        private final ProgressiveJpegParser progressiveJpegParser;
        final /* synthetic */ DecodeProducer this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public NetworkImagesProgressiveDecoder(DecodeProducer decodeProducer, Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext, ProgressiveJpegParser progressiveJpegParser2, ProgressiveJpegConfig progressiveJpegConfig2, boolean z, int i) {
            super(decodeProducer, consumer, producerContext, z, i);
            Intrinsics.checkNotNullParameter(consumer, "consumer");
            Intrinsics.checkNotNullParameter(producerContext, "producerContext");
            Intrinsics.checkNotNullParameter(progressiveJpegParser2, "progressiveJpegParser");
            Intrinsics.checkNotNullParameter(progressiveJpegConfig2, "progressiveJpegConfig");
            this.this$0 = decodeProducer;
            this.progressiveJpegParser = progressiveJpegParser2;
            this.progressiveJpegConfig = progressiveJpegConfig2;
            setLastScheduledScanNumber(0);
        }

        public final ProgressiveJpegParser getProgressiveJpegParser() {
            return this.progressiveJpegParser;
        }

        public final ProgressiveJpegConfig getProgressiveJpegConfig() {
            return this.progressiveJpegConfig;
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Code restructure failed: missing block: B:35:0x005f, code lost:
            return r1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized boolean updateDecodeJob(com.facebook.imagepipeline.image.EncodedImage r4, int r5) {
            /*
                r3 = this;
                monitor-enter(r3)
                r0 = 0
                if (r4 != 0) goto L_0x0006
                monitor-exit(r3)
                return r0
            L_0x0006:
                boolean r1 = super.updateDecodeJob(r4, r5)     // Catch:{ all -> 0x0060 }
                boolean r2 = com.facebook.imagepipeline.producers.BaseConsumer.isNotLast(r5)     // Catch:{ all -> 0x0060 }
                if (r2 != 0) goto L_0x0018
                r2 = 8
                boolean r2 = com.facebook.imagepipeline.producers.BaseConsumer.statusHasFlag(r5, r2)     // Catch:{ all -> 0x0060 }
                if (r2 == 0) goto L_0x005e
            L_0x0018:
                r2 = 4
                boolean r5 = com.facebook.imagepipeline.producers.BaseConsumer.statusHasFlag(r5, r2)     // Catch:{ all -> 0x0060 }
                if (r5 != 0) goto L_0x005e
                boolean r5 = com.facebook.imagepipeline.image.EncodedImage.isValid(r4)     // Catch:{ all -> 0x0060 }
                if (r5 == 0) goto L_0x005e
                com.facebook.imageformat.ImageFormat r5 = r4.getImageFormat()     // Catch:{ all -> 0x0060 }
                com.facebook.imageformat.ImageFormat r2 = com.facebook.imageformat.DefaultImageFormats.JPEG     // Catch:{ all -> 0x0060 }
                if (r5 != r2) goto L_0x005e
                com.facebook.imagepipeline.decoder.ProgressiveJpegParser r5 = r3.progressiveJpegParser     // Catch:{ all -> 0x0060 }
                boolean r4 = r5.parseMoreData(r4)     // Catch:{ all -> 0x0060 }
                if (r4 != 0) goto L_0x0037
                monitor-exit(r3)
                return r0
            L_0x0037:
                com.facebook.imagepipeline.decoder.ProgressiveJpegParser r4 = r3.progressiveJpegParser     // Catch:{ all -> 0x0060 }
                int r4 = r4.getBestScanNumber()     // Catch:{ all -> 0x0060 }
                int r5 = r3.getLastScheduledScanNumber()     // Catch:{ all -> 0x0060 }
                if (r4 > r5) goto L_0x0045
                monitor-exit(r3)
                return r0
            L_0x0045:
                com.facebook.imagepipeline.decoder.ProgressiveJpegConfig r5 = r3.progressiveJpegConfig     // Catch:{ all -> 0x0060 }
                int r2 = r3.getLastScheduledScanNumber()     // Catch:{ all -> 0x0060 }
                int r5 = r5.getNextScanNumberToDecode(r2)     // Catch:{ all -> 0x0060 }
                if (r4 >= r5) goto L_0x005b
                com.facebook.imagepipeline.decoder.ProgressiveJpegParser r5 = r3.progressiveJpegParser     // Catch:{ all -> 0x0060 }
                boolean r5 = r5.isEndMarkerRead()     // Catch:{ all -> 0x0060 }
                if (r5 != 0) goto L_0x005b
                monitor-exit(r3)
                return r0
            L_0x005b:
                r3.setLastScheduledScanNumber(r4)     // Catch:{ all -> 0x0060 }
            L_0x005e:
                monitor-exit(r3)
                return r1
            L_0x0060:
                r4 = move-exception
                monitor-exit(r3)
                throw r4
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.DecodeProducer.NetworkImagesProgressiveDecoder.updateDecodeJob(com.facebook.imagepipeline.image.EncodedImage, int):boolean");
        }

        /* access modifiers changed from: protected */
        public int getIntermediateImageEndOffset(EncodedImage encodedImage) {
            Intrinsics.checkNotNullParameter(encodedImage, "encodedImage");
            return this.progressiveJpegParser.getBestScanEndOffset();
        }

        /* access modifiers changed from: protected */
        public QualityInfo getQualityInfo() {
            QualityInfo qualityInfo = this.progressiveJpegConfig.getQualityInfo(this.progressiveJpegParser.getBestScanNumber());
            Intrinsics.checkNotNullExpressionValue(qualityInfo, "progressiveJpegConfig.ge…pegParser.bestScanNumber)");
            return qualityInfo;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/facebook/imagepipeline/producers/DecodeProducer$Companion;", "", "()V", "DECODE_EXCEPTION_MESSAGE_NUM_HEADER_BYTES", "", "ENCODED_IMAGE_SIZE", "", "EXTRA_BITMAP_BYTES", "EXTRA_BITMAP_SIZE", "EXTRA_HAS_GOOD_QUALITY", "EXTRA_IMAGE_FORMAT_NAME", "EXTRA_IS_FINAL", "MAX_BITMAP_SIZE", "NON_FATAL_DECODE_ERROR", "PRODUCER_NAME", "REQUESTED_IMAGE_SIZE", "SAMPLE_SIZE", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DecodeProducer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
