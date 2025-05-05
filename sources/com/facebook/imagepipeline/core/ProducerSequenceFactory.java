package com.facebook.imagepipeline.core;

import android.content.ContentResolver;
import android.net.Uri;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.media.MediaUtils;
import com.facebook.common.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.webp.WebpSupportStatus;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.AddImageTransformMetaDataProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheGetProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.BitmapProbeProducer;
import com.facebook.imagepipeline.producers.BranchOnSeparateImagesProducer;
import com.facebook.imagepipeline.producers.CustomProducerSequenceFactory;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.imagepipeline.producers.DelayProducer;
import com.facebook.imagepipeline.producers.DiskCacheReadProducer;
import com.facebook.imagepipeline.producers.DiskCacheWriteProducer;
import com.facebook.imagepipeline.producers.EncodedCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.EncodedProbeProducer;
import com.facebook.imagepipeline.producers.LocalExifThumbnailProducer;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.PartialDiskCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessorProducer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.ResizeAndRotateProducer;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.ThrottlingProducer;
import com.facebook.imagepipeline.producers.ThumbnailBranchProducer;
import com.facebook.imagepipeline.producers.ThumbnailProducer;
import com.facebook.imagepipeline.producers.WebpTranscodeProducer;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00012\u00020\u0001:\u0002\u0001B\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\t\u0012\u0006\u0010\u0014\u001a\u00020\t\u0012\u0006\u0010\u0015\u001a\u00020\t\u0012\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017¢\u0006\u0002\u0010\u0019J\u001c\u0010i\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\u0006\u0010j\u001a\u00020kH\u0002J(\u0010l\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\u0012\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001bH\u0002J\u0016\u0010n\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001b2\u0006\u0010j\u001a\u00020kJ$\u0010o\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001b2\u0012\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001bH\u0002J\u001a\u0010p\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\u0006\u0010j\u001a\u00020kJ(\u0010q\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\u0012\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001bH\u0002J\u0016\u0010r\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001b2\u0006\u0010j\u001a\u00020kJ\u001a\u0010s\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020@0)0\u001b2\u0006\u0010j\u001a\u00020kJ(\u0010t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\u0012\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001bH\u0002J(\u0010u\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\u0012\u0010m\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001bH\u0002J \u0010v\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bJ\"\u0010w\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J;\u0010w\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0012\u0010x\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0z0yH\u0002¢\u0006\u0002\u0010{J\u0018\u0010|\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007J\u001c\u0010}\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J\u001c\u0010~\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J(\u0010\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0012\u0010x\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0z0yH\u0002¢\u0006\u0003\u0010\u0001J6\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0012\u0010x\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0z0yH\u0002¢\u0006\u0002\u0010{R\u000e\u0010\u0015\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R#\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b8FX\u0002¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b\u001d\u0010\u001eR#\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b8FX\u0002¢\u0006\f\n\u0004\b#\u0010 \u001a\u0004\b\"\u0010\u001eR#\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b8FX\u0002¢\u0006\f\n\u0004\b&\u0010 \u001a\u0004\b%\u0010\u001eRH\u0010'\u001a&\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b0(8\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100RD\u00101\u001a\"\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001b0(8\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b3\u0010,\u001a\u0004\b4\u0010.\"\u0004\b5\u00100R!\u00106\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b8FX\u0002¢\u0006\f\n\u0004\b8\u0010 \u001a\u0004\b7\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0017X\u0004¢\u0006\u0002\n\u0000R'\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0002¢\u0006\f\n\u0004\b;\u0010 \u001a\u0004\b:\u0010\u001eR\u000e\u0010\u0010\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R'\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0002¢\u0006\f\n\u0004\b>\u0010 \u001a\u0004\b=\u0010\u001eR'\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020@0)0\u001b8FX\u0002¢\u0006\f\n\u0004\bB\u0010 \u001a\u0004\bA\u0010\u001eR'\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0002¢\u0006\f\n\u0004\bE\u0010 \u001a\u0004\bD\u0010\u001eR'\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8GX\u0002¢\u0006\f\n\u0004\bH\u0010 \u001a\u0004\bG\u0010\u001eR-\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020@0)0\u001b8FX\u0002¢\u0006\u0012\n\u0004\bL\u0010 \u0012\u0004\bJ\u0010,\u001a\u0004\bK\u0010\u001eR#\u0010M\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001b8FX\u0002¢\u0006\f\n\u0004\bO\u0010 \u001a\u0004\bN\u0010\u001eR'\u0010P\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0002¢\u0006\f\n\u0004\bR\u0010 \u001a\u0004\bQ\u0010\u001eR'\u0010S\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0002¢\u0006\f\n\u0004\bU\u0010 \u001a\u0004\bT\u0010\u001eR'\u0010V\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0002¢\u0006\f\n\u0004\bX\u0010 \u001a\u0004\bW\u0010\u001eR'\u0010Y\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020@0)0\u001b8FX\u0002¢\u0006\f\n\u0004\b[\u0010 \u001a\u0004\bZ\u0010\u001eR'\u0010\\\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0002¢\u0006\f\n\u0004\b^\u0010 \u001a\u0004\b]\u0010\u001eR#\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001020\u001b8FX\u0002¢\u0006\f\n\u0004\ba\u0010 \u001a\u0004\b`\u0010\u001eR\u0012\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000RH\u0010b\u001a&\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b0(8\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bc\u0010,\u001a\u0004\bd\u0010.\"\u0004\be\u00100R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R'\u0010f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020*0)0\u001b8FX\u0002¢\u0006\f\n\u0004\bh\u0010 \u001a\u0004\bg\u0010\u001eR\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001"}, d2 = {"Lcom/facebook/imagepipeline/core/ProducerSequenceFactory;", "", "contentResolver", "Landroid/content/ContentResolver;", "producerFactory", "Lcom/facebook/imagepipeline/core/ProducerFactory;", "networkFetcher", "Lcom/facebook/imagepipeline/producers/NetworkFetcher;", "resizeAndRotateEnabledForNetwork", "", "webpSupportEnabled", "threadHandoffProducerQueue", "Lcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;", "downSampleEnabled", "useBitmapPrepareToDraw", "partialImageCachingEnabled", "diskCacheEnabled", "imageTranscoderFactory", "Lcom/facebook/imagepipeline/transcoder/ImageTranscoderFactory;", "isEncodedMemoryCacheProbingEnabled", "isDiskCacheProbingEnabled", "allowDelay", "customProducerSequenceFactories", "", "Lcom/facebook/imagepipeline/producers/CustomProducerSequenceFactory;", "(Landroid/content/ContentResolver;Lcom/facebook/imagepipeline/core/ProducerFactory;Lcom/facebook/imagepipeline/producers/NetworkFetcher;ZZLcom/facebook/imagepipeline/producers/ThreadHandoffProducerQueue;ZZZZLcom/facebook/imagepipeline/transcoder/ImageTranscoderFactory;ZZZLjava/util/Set;)V", "backgroundLocalContentUriFetchToEncodeMemorySequence", "Lcom/facebook/imagepipeline/producers/Producer;", "Lcom/facebook/imagepipeline/image/EncodedImage;", "getBackgroundLocalContentUriFetchToEncodeMemorySequence", "()Lcom/facebook/imagepipeline/producers/Producer;", "backgroundLocalContentUriFetchToEncodeMemorySequence$delegate", "Lkotlin/Lazy;", "backgroundLocalFileFetchToEncodeMemorySequence", "getBackgroundLocalFileFetchToEncodeMemorySequence", "backgroundLocalFileFetchToEncodeMemorySequence$delegate", "backgroundNetworkFetchToEncodedMemorySequence", "getBackgroundNetworkFetchToEncodedMemorySequence", "backgroundNetworkFetchToEncodedMemorySequence$delegate", "bitmapPrepareSequences", "", "Lcom/facebook/common/references/CloseableReference;", "Lcom/facebook/imagepipeline/image/CloseableImage;", "getBitmapPrepareSequences$annotations", "()V", "getBitmapPrepareSequences", "()Ljava/util/Map;", "setBitmapPrepareSequences", "(Ljava/util/Map;)V", "closeableImagePrefetchSequences", "Ljava/lang/Void;", "getCloseableImagePrefetchSequences$annotations", "getCloseableImagePrefetchSequences", "setCloseableImagePrefetchSequences", "commonNetworkFetchToEncodedMemorySequence", "getCommonNetworkFetchToEncodedMemorySequence", "commonNetworkFetchToEncodedMemorySequence$delegate", "dataFetchSequence", "getDataFetchSequence", "dataFetchSequence$delegate", "localAssetFetchSequence", "getLocalAssetFetchSequence", "localAssetFetchSequence$delegate", "localContentUriFetchEncodedImageProducerSequence", "Lcom/facebook/common/memory/PooledByteBuffer;", "getLocalContentUriFetchEncodedImageProducerSequence", "localContentUriFetchEncodedImageProducerSequence$delegate", "localContentUriFetchSequence", "getLocalContentUriFetchSequence", "localContentUriFetchSequence$delegate", "localContentUriThumbnailFetchSequence", "getLocalContentUriThumbnailFetchSequence", "localContentUriThumbnailFetchSequence$delegate", "localFileFetchEncodedImageProducerSequence", "getLocalFileFetchEncodedImageProducerSequence$annotations", "getLocalFileFetchEncodedImageProducerSequence", "localFileFetchEncodedImageProducerSequence$delegate", "localFileFetchToEncodedMemoryPrefetchSequence", "getLocalFileFetchToEncodedMemoryPrefetchSequence", "localFileFetchToEncodedMemoryPrefetchSequence$delegate", "localImageFileFetchSequence", "getLocalImageFileFetchSequence", "localImageFileFetchSequence$delegate", "localResourceFetchSequence", "getLocalResourceFetchSequence", "localResourceFetchSequence$delegate", "localVideoFileFetchSequence", "getLocalVideoFileFetchSequence", "localVideoFileFetchSequence$delegate", "networkFetchEncodedImageProducerSequence", "getNetworkFetchEncodedImageProducerSequence", "networkFetchEncodedImageProducerSequence$delegate", "networkFetchSequence", "getNetworkFetchSequence", "networkFetchSequence$delegate", "networkFetchToEncodedMemoryPrefetchSequence", "getNetworkFetchToEncodedMemoryPrefetchSequence", "networkFetchToEncodedMemoryPrefetchSequence$delegate", "postprocessorSequences", "getPostprocessorSequences$annotations", "getPostprocessorSequences", "setPostprocessorSequences", "qualifiedResourceFetchSequence", "getQualifiedResourceFetchSequence", "qualifiedResourceFetchSequence$delegate", "getBasicDecodedImageSequence", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "getBitmapPrepareSequence", "inputProducer", "getDecodedImagePrefetchProducerSequence", "getDecodedImagePrefetchSequence", "getDecodedImageProducerSequence", "getDelaySequence", "getEncodedImagePrefetchProducerSequence", "getEncodedImageProducerSequence", "getPostprocessorSequence", "newBitmapCacheGetToBitmapCacheSequence", "newBitmapCacheGetToDecodeSequence", "newBitmapCacheGetToLocalTransformSequence", "thumbnailProducers", "", "Lcom/facebook/imagepipeline/producers/ThumbnailProducer;", "(Lcom/facebook/imagepipeline/producers/Producer;[Lcom/facebook/imagepipeline/producers/ThumbnailProducer;)Lcom/facebook/imagepipeline/producers/Producer;", "newCommonNetworkFetchToEncodedMemorySequence", "newDiskCacheSequence", "newEncodedCacheMultiplexToTranscodeSequence", "newLocalThumbnailProducer", "([Lcom/facebook/imagepipeline/producers/ThumbnailProducer;)Lcom/facebook/imagepipeline/producers/Producer;", "newLocalTransformationsSequence", "Companion", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProducerSequenceFactory.kt */
public final class ProducerSequenceFactory {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean allowDelay;
    private final Lazy backgroundLocalContentUriFetchToEncodeMemorySequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$backgroundLocalContentUriFetchToEncodeMemorySequence$2(this));
    private final Lazy backgroundLocalFileFetchToEncodeMemorySequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$backgroundLocalFileFetchToEncodeMemorySequence$2(this));
    private final Lazy backgroundNetworkFetchToEncodedMemorySequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$backgroundNetworkFetchToEncodedMemorySequence$2(this));
    private Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> bitmapPrepareSequences = new LinkedHashMap();
    private Map<Producer<CloseableReference<CloseableImage>>, Producer<Void>> closeableImagePrefetchSequences = new LinkedHashMap();
    private final Lazy commonNetworkFetchToEncodedMemorySequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$commonNetworkFetchToEncodedMemorySequence$2(this));
    private final ContentResolver contentResolver;
    private final Set<CustomProducerSequenceFactory> customProducerSequenceFactories;
    private final Lazy dataFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$dataFetchSequence$2(this));
    private final boolean diskCacheEnabled;
    private final boolean downSampleEnabled;
    /* access modifiers changed from: private */
    public final ImageTranscoderFactory imageTranscoderFactory;
    private final boolean isDiskCacheProbingEnabled;
    private final boolean isEncodedMemoryCacheProbingEnabled;
    private final Lazy localAssetFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$localAssetFetchSequence$2(this));
    private final Lazy localContentUriFetchEncodedImageProducerSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$localContentUriFetchEncodedImageProducerSequence$2(this));
    private final Lazy localContentUriFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$localContentUriFetchSequence$2(this));
    private final Lazy localContentUriThumbnailFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$localContentUriThumbnailFetchSequence$2(this));
    private final Lazy localFileFetchEncodedImageProducerSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$localFileFetchEncodedImageProducerSequence$2(this));
    private final Lazy localFileFetchToEncodedMemoryPrefetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$localFileFetchToEncodedMemoryPrefetchSequence$2(this));
    private final Lazy localImageFileFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$localImageFileFetchSequence$2(this));
    private final Lazy localResourceFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$localResourceFetchSequence$2(this));
    private final Lazy localVideoFileFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$localVideoFileFetchSequence$2(this));
    private final Lazy networkFetchEncodedImageProducerSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$networkFetchEncodedImageProducerSequence$2(this));
    private final Lazy networkFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$networkFetchSequence$2(this));
    private final Lazy networkFetchToEncodedMemoryPrefetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$networkFetchToEncodedMemoryPrefetchSequence$2(this));
    /* access modifiers changed from: private */
    public final NetworkFetcher<?> networkFetcher;
    private final boolean partialImageCachingEnabled;
    private Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> postprocessorSequences = new LinkedHashMap();
    /* access modifiers changed from: private */
    public final ProducerFactory producerFactory;
    private final Lazy qualifiedResourceFetchSequence$delegate = LazyKt.lazy(new ProducerSequenceFactory$qualifiedResourceFetchSequence$2(this));
    private final boolean resizeAndRotateEnabledForNetwork;
    /* access modifiers changed from: private */
    public final ThreadHandoffProducerQueue threadHandoffProducerQueue;
    private final boolean useBitmapPrepareToDraw;
    /* access modifiers changed from: private */
    public final boolean webpSupportEnabled;

    public static /* synthetic */ void getBitmapPrepareSequences$annotations() {
    }

    public static /* synthetic */ void getCloseableImagePrefetchSequences$annotations() {
    }

    public static /* synthetic */ void getLocalFileFetchEncodedImageProducerSequence$annotations() {
    }

    public static /* synthetic */ void getPostprocessorSequences$annotations() {
    }

    public ProducerSequenceFactory(ContentResolver contentResolver2, ProducerFactory producerFactory2, NetworkFetcher<?> networkFetcher2, boolean z, boolean z2, ThreadHandoffProducerQueue threadHandoffProducerQueue2, boolean z3, boolean z4, boolean z5, boolean z6, ImageTranscoderFactory imageTranscoderFactory2, boolean z7, boolean z8, boolean z9, Set<? extends CustomProducerSequenceFactory> set) {
        ImageTranscoderFactory imageTranscoderFactory3 = imageTranscoderFactory2;
        Intrinsics.checkNotNullParameter(contentResolver2, "contentResolver");
        Intrinsics.checkNotNullParameter(producerFactory2, "producerFactory");
        Intrinsics.checkNotNullParameter(networkFetcher2, "networkFetcher");
        Intrinsics.checkNotNullParameter(threadHandoffProducerQueue2, "threadHandoffProducerQueue");
        Intrinsics.checkNotNullParameter(imageTranscoderFactory3, "imageTranscoderFactory");
        this.contentResolver = contentResolver2;
        this.producerFactory = producerFactory2;
        this.networkFetcher = networkFetcher2;
        this.resizeAndRotateEnabledForNetwork = z;
        this.webpSupportEnabled = z2;
        this.threadHandoffProducerQueue = threadHandoffProducerQueue2;
        this.downSampleEnabled = z3;
        this.useBitmapPrepareToDraw = z4;
        this.partialImageCachingEnabled = z5;
        this.diskCacheEnabled = z6;
        this.imageTranscoderFactory = imageTranscoderFactory3;
        this.isEncodedMemoryCacheProbingEnabled = z7;
        this.isDiskCacheProbingEnabled = z8;
        this.allowDelay = z9;
        this.customProducerSequenceFactories = set;
    }

    public final Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> getPostprocessorSequences() {
        return this.postprocessorSequences;
    }

    public final void setPostprocessorSequences(Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.postprocessorSequences = map;
    }

    public final Map<Producer<CloseableReference<CloseableImage>>, Producer<Void>> getCloseableImagePrefetchSequences() {
        return this.closeableImagePrefetchSequences;
    }

    public final void setCloseableImagePrefetchSequences(Map<Producer<CloseableReference<CloseableImage>>, Producer<Void>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.closeableImagePrefetchSequences = map;
    }

    public final Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> getBitmapPrepareSequences() {
        return this.bitmapPrepareSequences;
    }

    public final void setBitmapPrepareSequences(Map<Producer<CloseableReference<CloseableImage>>, Producer<CloseableReference<CloseableImage>>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.bitmapPrepareSequences = map;
    }

    public final Producer<CloseableReference<PooledByteBuffer>> getEncodedImageProducerSequence(ImageRequest imageRequest) {
        Producer<CloseableReference<PooledByteBuffer>> producer;
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            Companion companion = Companion;
            companion.validateEncodedImageRequest(imageRequest);
            Uri sourceUri = imageRequest.getSourceUri();
            Intrinsics.checkNotNullExpressionValue(sourceUri, "imageRequest.sourceUri");
            int sourceUriType = imageRequest.getSourceUriType();
            if (sourceUriType == 0) {
                return getNetworkFetchEncodedImageProducerSequence();
            }
            if (sourceUriType == 2 || sourceUriType == 3) {
                return getLocalFileFetchEncodedImageProducerSequence();
            }
            if (sourceUriType == 4) {
                return getLocalContentUriFetchEncodedImageProducerSequence();
            }
            throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + companion.getShortenedUriString(sourceUri));
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getEncodedImageProducerSequence");
        try {
            Companion companion2 = Companion;
            companion2.validateEncodedImageRequest(imageRequest);
            Uri sourceUri2 = imageRequest.getSourceUri();
            Intrinsics.checkNotNullExpressionValue(sourceUri2, "imageRequest.sourceUri");
            int sourceUriType2 = imageRequest.getSourceUriType();
            if (sourceUriType2 == 0) {
                producer = getNetworkFetchEncodedImageProducerSequence();
            } else if (sourceUriType2 == 2 || sourceUriType2 == 3) {
                producer = getLocalFileFetchEncodedImageProducerSequence();
            } else if (sourceUriType2 == 4) {
                producer = getLocalContentUriFetchEncodedImageProducerSequence();
            } else {
                throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + companion2.getShortenedUriString(sourceUri2));
            }
            return producer;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Producer<CloseableReference<PooledByteBuffer>> getNetworkFetchEncodedImageProducerSequence() {
        return (Producer) this.networkFetchEncodedImageProducerSequence$delegate.getValue();
    }

    public final Producer<CloseableReference<PooledByteBuffer>> getLocalFileFetchEncodedImageProducerSequence() {
        return (Producer) this.localFileFetchEncodedImageProducerSequence$delegate.getValue();
    }

    public final Producer<CloseableReference<PooledByteBuffer>> getLocalContentUriFetchEncodedImageProducerSequence() {
        return (Producer) this.localContentUriFetchEncodedImageProducerSequence$delegate.getValue();
    }

    public final Producer<Void> getEncodedImagePrefetchProducerSequence(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        Companion companion = Companion;
        companion.validateEncodedImageRequest(imageRequest);
        int sourceUriType = imageRequest.getSourceUriType();
        if (sourceUriType == 0) {
            return getNetworkFetchToEncodedMemoryPrefetchSequence();
        }
        if (sourceUriType == 2 || sourceUriType == 3) {
            return getLocalFileFetchToEncodedMemoryPrefetchSequence();
        }
        Uri sourceUri = imageRequest.getSourceUri();
        Intrinsics.checkNotNullExpressionValue(sourceUri, "imageRequest.sourceUri");
        throw new IllegalArgumentException("Unsupported uri scheme for encoded image fetch! Uri is: " + companion.getShortenedUriString(sourceUri));
    }

    public final Producer<CloseableReference<CloseableImage>> getDecodedImageProducerSequence(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            Producer<CloseableReference<CloseableImage>> basicDecodedImageSequence = getBasicDecodedImageSequence(imageRequest);
            if (imageRequest.getPostprocessor() != null) {
                basicDecodedImageSequence = getPostprocessorSequence(basicDecodedImageSequence);
            }
            if (this.useBitmapPrepareToDraw) {
                basicDecodedImageSequence = getBitmapPrepareSequence(basicDecodedImageSequence);
            }
            if (!this.allowDelay || imageRequest.getDelayMs() <= 0) {
                return basicDecodedImageSequence;
            }
            return getDelaySequence(basicDecodedImageSequence);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#getDecodedImageProducerSequence");
        try {
            Producer<CloseableReference<CloseableImage>> basicDecodedImageSequence2 = getBasicDecodedImageSequence(imageRequest);
            if (imageRequest.getPostprocessor() != null) {
                basicDecodedImageSequence2 = getPostprocessorSequence(basicDecodedImageSequence2);
            }
            if (this.useBitmapPrepareToDraw) {
                basicDecodedImageSequence2 = getBitmapPrepareSequence(basicDecodedImageSequence2);
            }
            if (this.allowDelay && imageRequest.getDelayMs() > 0) {
                basicDecodedImageSequence2 = getDelaySequence(basicDecodedImageSequence2);
            }
            return basicDecodedImageSequence2;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Producer<Void> getDecodedImagePrefetchProducerSequence(ImageRequest imageRequest) {
        Intrinsics.checkNotNullParameter(imageRequest, "imageRequest");
        Producer<CloseableReference<CloseableImage>> basicDecodedImageSequence = getBasicDecodedImageSequence(imageRequest);
        if (this.useBitmapPrepareToDraw) {
            basicDecodedImageSequence = getBitmapPrepareSequence(basicDecodedImageSequence);
        }
        return getDecodedImagePrefetchSequence(basicDecodedImageSequence);
    }

    private final Producer<CloseableReference<CloseableImage>> getBasicDecodedImageSequence(ImageRequest imageRequest) {
        Producer<CloseableReference<CloseableImage>> producer;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            Uri sourceUri = imageRequest.getSourceUri();
            Intrinsics.checkNotNullExpressionValue(sourceUri, "imageRequest.sourceUri");
            if (sourceUri != null) {
                int sourceUriType = imageRequest.getSourceUriType();
                if (sourceUriType == 0) {
                    return getNetworkFetchSequence();
                }
                switch (sourceUriType) {
                    case 2:
                        return getLocalVideoFileFetchSequence();
                    case 3:
                        return getLocalImageFileFetchSequence();
                    case 4:
                        if (imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                            return getLocalContentUriThumbnailFetchSequence();
                        }
                        if (MediaUtils.isVideo(this.contentResolver.getType(sourceUri))) {
                            return getLocalVideoFileFetchSequence();
                        }
                        return getLocalContentUriFetchSequence();
                    case 5:
                        return getLocalAssetFetchSequence();
                    case 6:
                        return getLocalResourceFetchSequence();
                    case 7:
                        return getDataFetchSequence();
                    case 8:
                        return getQualifiedResourceFetchSequence();
                    default:
                        Set<CustomProducerSequenceFactory> set = this.customProducerSequenceFactories;
                        if (set != null) {
                            for (CustomProducerSequenceFactory customDecodedImageSequence : set) {
                                Producer<CloseableReference<CloseableImage>> customDecodedImageSequence2 = customDecodedImageSequence.getCustomDecodedImageSequence(imageRequest, this);
                                if (customDecodedImageSequence2 != null) {
                                    return customDecodedImageSequence2;
                                }
                            }
                        }
                        throw new IllegalArgumentException("Unsupported uri scheme! Uri is: " + Companion.getShortenedUriString(sourceUri));
                }
            } else {
                throw new IllegalStateException("Uri is null.".toString());
            }
        } else {
            FrescoSystrace.beginSection("ProducerSequenceFactory#getBasicDecodedImageSequence");
            try {
                Uri sourceUri2 = imageRequest.getSourceUri();
                Intrinsics.checkNotNullExpressionValue(sourceUri2, "imageRequest.sourceUri");
                if (sourceUri2 != null) {
                    int sourceUriType2 = imageRequest.getSourceUriType();
                    if (sourceUriType2 != 0) {
                        switch (sourceUriType2) {
                            case 2:
                                producer = getLocalVideoFileFetchSequence();
                                break;
                            case 3:
                                producer = getLocalImageFileFetchSequence();
                                break;
                            case 4:
                                if (!imageRequest.getLoadThumbnailOnlyForAndroidSdkAboveQ()) {
                                    if (!MediaUtils.isVideo(this.contentResolver.getType(sourceUri2))) {
                                        producer = getLocalContentUriFetchSequence();
                                        break;
                                    } else {
                                        Producer<CloseableReference<CloseableImage>> localVideoFileFetchSequence = getLocalVideoFileFetchSequence();
                                        FrescoSystrace.endSection();
                                        return localVideoFileFetchSequence;
                                    }
                                } else {
                                    Producer<CloseableReference<CloseableImage>> localContentUriThumbnailFetchSequence = getLocalContentUriThumbnailFetchSequence();
                                    FrescoSystrace.endSection();
                                    return localContentUriThumbnailFetchSequence;
                                }
                            case 5:
                                producer = getLocalAssetFetchSequence();
                                break;
                            case 6:
                                producer = getLocalResourceFetchSequence();
                                break;
                            case 7:
                                producer = getDataFetchSequence();
                                break;
                            case 8:
                                producer = getQualifiedResourceFetchSequence();
                                break;
                            default:
                                Set<CustomProducerSequenceFactory> set2 = this.customProducerSequenceFactories;
                                if (set2 != null) {
                                    for (CustomProducerSequenceFactory customDecodedImageSequence3 : set2) {
                                        Producer<CloseableReference<CloseableImage>> customDecodedImageSequence4 = customDecodedImageSequence3.getCustomDecodedImageSequence(imageRequest, this);
                                        if (customDecodedImageSequence4 != null) {
                                            return customDecodedImageSequence4;
                                        }
                                    }
                                }
                                throw new IllegalArgumentException("Unsupported uri scheme! Uri is: " + Companion.getShortenedUriString(sourceUri2));
                        }
                    } else {
                        producer = getNetworkFetchSequence();
                    }
                    FrescoSystrace.endSection();
                    return producer;
                }
                throw new IllegalStateException("Uri is null.".toString());
            } finally {
                FrescoSystrace.endSection();
            }
        }
    }

    public final Producer<CloseableReference<CloseableImage>> getNetworkFetchSequence() {
        return (Producer) this.networkFetchSequence$delegate.getValue();
    }

    public final Producer<EncodedImage> getBackgroundNetworkFetchToEncodedMemorySequence() {
        Object value = this.backgroundNetworkFetchToEncodedMemorySequence$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backgroundNetworkFe…codedMemorySequence>(...)");
        return (Producer) value;
    }

    public final Producer<Void> getNetworkFetchToEncodedMemoryPrefetchSequence() {
        Object value = this.networkFetchToEncodedMemoryPrefetchSequence$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-networkFetchToEncod…oryPrefetchSequence>(...)");
        return (Producer) value;
    }

    public final Producer<EncodedImage> getCommonNetworkFetchToEncodedMemorySequence() {
        return (Producer) this.commonNetworkFetchToEncodedMemorySequence$delegate.getValue();
    }

    public final synchronized Producer<EncodedImage> newCommonNetworkFetchToEncodedMemorySequence(NetworkFetcher<?> networkFetcher2) {
        Intrinsics.checkNotNullParameter(networkFetcher2, "networkFetcher");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        boolean z = true;
        if (!FrescoSystrace.isTracing()) {
            Producer<EncodedImage> newNetworkFetchProducer = this.producerFactory.newNetworkFetchProducer(networkFetcher2);
            Intrinsics.checkNotNullExpressionValue(newNetworkFetchProducer, "producerFactory.newNetwo…hProducer(networkFetcher)");
            AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer = ProducerFactory.newAddImageTransformMetaDataProducer(newEncodedCacheMultiplexToTranscodeSequence(newNetworkFetchProducer));
            Intrinsics.checkNotNullExpressionValue(newAddImageTransformMetaDataProducer, "newAddImageTransformMeta…taProducer(inputProducer)");
            Producer producer = newAddImageTransformMetaDataProducer;
            ProducerFactory producerFactory2 = this.producerFactory;
            if (!this.resizeAndRotateEnabledForNetwork || this.downSampleEnabled) {
                z = false;
            }
            ResizeAndRotateProducer newResizeAndRotateProducer = producerFactory2.newResizeAndRotateProducer(producer, z, this.imageTranscoderFactory);
            Intrinsics.checkNotNullExpressionValue(newResizeAndRotateProducer, "producerFactory.newResiz…  imageTranscoderFactory)");
            Producer<EncodedImage> producer2 = newResizeAndRotateProducer;
            Intrinsics.checkNotNullExpressionValue(producer2, "networkFetchToEncodedMemorySequence");
            return producer2;
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#createCommonNetworkFetchToEncodedMemorySequence");
        try {
            Producer<EncodedImage> newNetworkFetchProducer2 = this.producerFactory.newNetworkFetchProducer(networkFetcher2);
            Intrinsics.checkNotNullExpressionValue(newNetworkFetchProducer2, "producerFactory.newNetwo…hProducer(networkFetcher)");
            AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer2 = ProducerFactory.newAddImageTransformMetaDataProducer(newEncodedCacheMultiplexToTranscodeSequence(newNetworkFetchProducer2));
            Intrinsics.checkNotNullExpressionValue(newAddImageTransformMetaDataProducer2, "newAddImageTransformMeta…taProducer(inputProducer)");
            Producer producer3 = newAddImageTransformMetaDataProducer2;
            ProducerFactory producerFactory3 = this.producerFactory;
            if (!this.resizeAndRotateEnabledForNetwork || this.downSampleEnabled) {
                z = false;
            }
            ResizeAndRotateProducer newResizeAndRotateProducer2 = producerFactory3.newResizeAndRotateProducer(producer3, z, this.imageTranscoderFactory);
            Intrinsics.checkNotNullExpressionValue(newResizeAndRotateProducer2, "producerFactory.newResiz…  imageTranscoderFactory)");
            Producer<EncodedImage> producer4 = newResizeAndRotateProducer2;
            Intrinsics.checkNotNullExpressionValue(producer4, "networkFetchToEncodedMemorySequence");
            return producer4;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    public final Producer<Void> getLocalFileFetchToEncodedMemoryPrefetchSequence() {
        Object value = this.localFileFetchToEncodedMemoryPrefetchSequence$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-localFileFetchToEnc…oryPrefetchSequence>(...)");
        return (Producer) value;
    }

    public final Producer<EncodedImage> getBackgroundLocalFileFetchToEncodeMemorySequence() {
        Object value = this.backgroundLocalFileFetchToEncodeMemorySequence$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backgroundLocalFile…ncodeMemorySequence>(...)");
        return (Producer) value;
    }

    public final Producer<EncodedImage> getBackgroundLocalContentUriFetchToEncodeMemorySequence() {
        Object value = this.backgroundLocalContentUriFetchToEncodeMemorySequence$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-backgroundLocalCont…ncodeMemorySequence>(...)");
        return (Producer) value;
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalImageFileFetchSequence() {
        return (Producer) this.localImageFileFetchSequence$delegate.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalVideoFileFetchSequence() {
        return (Producer) this.localVideoFileFetchSequence$delegate.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalContentUriFetchSequence() {
        return (Producer) this.localContentUriFetchSequence$delegate.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalContentUriThumbnailFetchSequence() {
        return (Producer) this.localContentUriThumbnailFetchSequence$delegate.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getQualifiedResourceFetchSequence() {
        return (Producer) this.qualifiedResourceFetchSequence$delegate.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalResourceFetchSequence() {
        return (Producer) this.localResourceFetchSequence$delegate.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getLocalAssetFetchSequence() {
        return (Producer) this.localAssetFetchSequence$delegate.getValue();
    }

    public final Producer<CloseableReference<CloseableImage>> getDataFetchSequence() {
        return (Producer) this.dataFetchSequence$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> producer) {
        LocalExifThumbnailProducer newLocalExifThumbnailProducer = this.producerFactory.newLocalExifThumbnailProducer();
        Intrinsics.checkNotNullExpressionValue(newLocalExifThumbnailProducer, "producerFactory.newLocalExifThumbnailProducer()");
        return newBitmapCacheGetToLocalTransformSequence(producer, new ThumbnailProducer[]{newLocalExifThumbnailProducer});
    }

    /* access modifiers changed from: private */
    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToLocalTransformSequence(Producer<EncodedImage> producer, ThumbnailProducer<EncodedImage>[] thumbnailProducerArr) {
        return newBitmapCacheGetToDecodeSequence(newLocalTransformationsSequence(newEncodedCacheMultiplexToTranscodeSequence(producer), thumbnailProducerArr));
    }

    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToDecodeSequence(Producer<EncodedImage> producer) {
        Intrinsics.checkNotNullParameter(producer, "inputProducer");
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            DecodeProducer newDecodeProducer = this.producerFactory.newDecodeProducer(producer);
            Intrinsics.checkNotNullExpressionValue(newDecodeProducer, "producerFactory.newDecodeProducer(inputProducer)");
            return newBitmapCacheGetToBitmapCacheSequence(newDecodeProducer);
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#newBitmapCacheGetToDecodeSequence");
        try {
            DecodeProducer newDecodeProducer2 = this.producerFactory.newDecodeProducer(producer);
            Intrinsics.checkNotNullExpressionValue(newDecodeProducer2, "producerFactory.newDecodeProducer(inputProducer)");
            return newBitmapCacheGetToBitmapCacheSequence(newDecodeProducer2);
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final Producer<EncodedImage> newEncodedCacheMultiplexToTranscodeSequence(Producer<EncodedImage> producer) {
        if (WebpSupportStatus.sIsWebpSupportRequired && (!this.webpSupportEnabled || WebpSupportStatus.sWebpBitmapFactory == null)) {
            WebpTranscodeProducer newWebpTranscodeProducer = this.producerFactory.newWebpTranscodeProducer(producer);
            Intrinsics.checkNotNullExpressionValue(newWebpTranscodeProducer, "producerFactory.newWebpTranscodeProducer(ip)");
            producer = newWebpTranscodeProducer;
        }
        if (this.diskCacheEnabled) {
            producer = newDiskCacheSequence(producer);
        }
        Producer<EncodedImage> newEncodedMemoryCacheProducer = this.producerFactory.newEncodedMemoryCacheProducer(producer);
        Intrinsics.checkNotNullExpressionValue(newEncodedMemoryCacheProducer, "producerFactory.newEncodedMemoryCacheProducer(ip)");
        if (this.isDiskCacheProbingEnabled) {
            EncodedProbeProducer newEncodedProbeProducer = this.producerFactory.newEncodedProbeProducer(newEncodedMemoryCacheProducer);
            Intrinsics.checkNotNullExpressionValue(newEncodedProbeProducer, "producerFactory.newEncod…codedMemoryCacheProducer)");
            EncodedCacheKeyMultiplexProducer newEncodedCacheKeyMultiplexProducer = this.producerFactory.newEncodedCacheKeyMultiplexProducer(newEncodedProbeProducer);
            Intrinsics.checkNotNullExpressionValue(newEncodedCacheKeyMultiplexProducer, "producerFactory.newEncod…exProducer(probeProducer)");
            return newEncodedCacheKeyMultiplexProducer;
        }
        EncodedCacheKeyMultiplexProducer newEncodedCacheKeyMultiplexProducer2 = this.producerFactory.newEncodedCacheKeyMultiplexProducer(newEncodedMemoryCacheProducer);
        Intrinsics.checkNotNullExpressionValue(newEncodedCacheKeyMultiplexProducer2, "producerFactory.newEncod…codedMemoryCacheProducer)");
        return newEncodedCacheKeyMultiplexProducer2;
    }

    private final Producer<EncodedImage> newDiskCacheSequence(Producer<EncodedImage> producer) {
        DiskCacheWriteProducer diskCacheWriteProducer;
        DiskCacheWriteProducer diskCacheWriteProducer2;
        FrescoSystrace frescoSystrace = FrescoSystrace.INSTANCE;
        if (!FrescoSystrace.isTracing()) {
            if (this.partialImageCachingEnabled) {
                PartialDiskCacheProducer newPartialDiskCacheProducer = this.producerFactory.newPartialDiskCacheProducer(producer);
                Intrinsics.checkNotNullExpressionValue(newPartialDiskCacheProducer, "producerFactory.newParti…heProducer(inputProducer)");
                diskCacheWriteProducer2 = this.producerFactory.newDiskCacheWriteProducer(newPartialDiskCacheProducer);
            } else {
                diskCacheWriteProducer2 = this.producerFactory.newDiskCacheWriteProducer(producer);
            }
            Intrinsics.checkNotNullExpressionValue(diskCacheWriteProducer2, "if (partialImageCachingE…utProducer)\n            }");
            DiskCacheReadProducer newDiskCacheReadProducer = this.producerFactory.newDiskCacheReadProducer(diskCacheWriteProducer2);
            Intrinsics.checkNotNullExpressionValue(newDiskCacheReadProducer, "producerFactory.newDiskC…ducer(cacheWriteProducer)");
            return newDiskCacheReadProducer;
        }
        FrescoSystrace.beginSection("ProducerSequenceFactory#newDiskCacheSequence");
        try {
            if (this.partialImageCachingEnabled) {
                PartialDiskCacheProducer newPartialDiskCacheProducer2 = this.producerFactory.newPartialDiskCacheProducer(producer);
                Intrinsics.checkNotNullExpressionValue(newPartialDiskCacheProducer2, "producerFactory.newParti…heProducer(inputProducer)");
                diskCacheWriteProducer = this.producerFactory.newDiskCacheWriteProducer(newPartialDiskCacheProducer2);
            } else {
                diskCacheWriteProducer = this.producerFactory.newDiskCacheWriteProducer(producer);
            }
            Intrinsics.checkNotNullExpressionValue(diskCacheWriteProducer, "if (partialImageCachingE…utProducer)\n            }");
            DiskCacheReadProducer newDiskCacheReadProducer2 = this.producerFactory.newDiskCacheReadProducer(diskCacheWriteProducer);
            Intrinsics.checkNotNullExpressionValue(newDiskCacheReadProducer2, "producerFactory.newDiskC…ducer(cacheWriteProducer)");
            return newDiskCacheReadProducer2;
        } finally {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: private */
    public final Producer<CloseableReference<CloseableImage>> newBitmapCacheGetToBitmapCacheSequence(Producer<CloseableReference<CloseableImage>> producer) {
        BitmapMemoryCacheProducer newBitmapMemoryCacheProducer = this.producerFactory.newBitmapMemoryCacheProducer(producer);
        Intrinsics.checkNotNullExpressionValue(newBitmapMemoryCacheProducer, "producerFactory.newBitma…heProducer(inputProducer)");
        BitmapMemoryCacheKeyMultiplexProducer newBitmapMemoryCacheKeyMultiplexProducer = this.producerFactory.newBitmapMemoryCacheKeyMultiplexProducer(newBitmapMemoryCacheProducer);
        Intrinsics.checkNotNullExpressionValue(newBitmapMemoryCacheKeyMultiplexProducer, "producerFactory.newBitma…itmapMemoryCacheProducer)");
        Producer newBackgroundThreadHandoffProducer = this.producerFactory.newBackgroundThreadHandoffProducer(newBitmapMemoryCacheKeyMultiplexProducer, this.threadHandoffProducerQueue);
        Intrinsics.checkNotNullExpressionValue(newBackgroundThreadHandoffProducer, "producerFactory.newBackg…readHandoffProducerQueue)");
        if (this.isEncodedMemoryCacheProbingEnabled || this.isDiskCacheProbingEnabled) {
            BitmapMemoryCacheGetProducer newBitmapMemoryCacheGetProducer = this.producerFactory.newBitmapMemoryCacheGetProducer(newBackgroundThreadHandoffProducer);
            Intrinsics.checkNotNullExpressionValue(newBitmapMemoryCacheGetProducer, "producerFactory.newBitma…er(threadHandoffProducer)");
            BitmapProbeProducer newBitmapProbeProducer = this.producerFactory.newBitmapProbeProducer(newBitmapMemoryCacheGetProducer);
            Intrinsics.checkNotNullExpressionValue(newBitmapProbeProducer, "producerFactory.newBitma…apMemoryCacheGetProducer)");
            return newBitmapProbeProducer;
        }
        BitmapMemoryCacheGetProducer newBitmapMemoryCacheGetProducer2 = this.producerFactory.newBitmapMemoryCacheGetProducer(newBackgroundThreadHandoffProducer);
        Intrinsics.checkNotNullExpressionValue(newBitmapMemoryCacheGetProducer2, "producerFactory.newBitma…er(threadHandoffProducer)");
        return newBitmapMemoryCacheGetProducer2;
    }

    private final Producer<EncodedImage> newLocalTransformationsSequence(Producer<EncodedImage> producer, ThumbnailProducer<EncodedImage>[] thumbnailProducerArr) {
        AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer = ProducerFactory.newAddImageTransformMetaDataProducer(producer);
        Intrinsics.checkNotNullExpressionValue(newAddImageTransformMetaDataProducer, "newAddImageTransformMeta…taProducer(inputProducer)");
        ResizeAndRotateProducer newResizeAndRotateProducer = this.producerFactory.newResizeAndRotateProducer(newAddImageTransformMetaDataProducer, true, this.imageTranscoderFactory);
        Intrinsics.checkNotNullExpressionValue(newResizeAndRotateProducer, "producerFactory.newResiz…, imageTranscoderFactory)");
        ThrottlingProducer newThrottlingProducer = this.producerFactory.newThrottlingProducer(newResizeAndRotateProducer);
        Intrinsics.checkNotNullExpressionValue(newThrottlingProducer, "producerFactory.newThrot…ducer(localImageProducer)");
        BranchOnSeparateImagesProducer newBranchOnSeparateImagesProducer = ProducerFactory.newBranchOnSeparateImagesProducer(newLocalThumbnailProducer(thumbnailProducerArr), newThrottlingProducer);
        Intrinsics.checkNotNullExpressionValue(newBranchOnSeparateImagesProducer, "newBranchOnSeparateImage…lImageThrottlingProducer)");
        return newBranchOnSeparateImagesProducer;
    }

    private final Producer<EncodedImage> newLocalThumbnailProducer(ThumbnailProducer<EncodedImage>[] thumbnailProducerArr) {
        ThumbnailBranchProducer newThumbnailBranchProducer = this.producerFactory.newThumbnailBranchProducer(thumbnailProducerArr);
        Intrinsics.checkNotNullExpressionValue(newThumbnailBranchProducer, "producerFactory.newThumb…ducer(thumbnailProducers)");
        ResizeAndRotateProducer newResizeAndRotateProducer = this.producerFactory.newResizeAndRotateProducer(newThumbnailBranchProducer, true, this.imageTranscoderFactory);
        Intrinsics.checkNotNullExpressionValue(newResizeAndRotateProducer, "producerFactory.newResiz…, imageTranscoderFactory)");
        return newResizeAndRotateProducer;
    }

    private final synchronized Producer<CloseableReference<CloseableImage>> getPostprocessorSequence(Producer<CloseableReference<CloseableImage>> producer) {
        Producer<CloseableReference<CloseableImage>> producer2;
        producer2 = this.postprocessorSequences.get(producer);
        if (producer2 == null) {
            PostprocessorProducer newPostprocessorProducer = this.producerFactory.newPostprocessorProducer(producer);
            Intrinsics.checkNotNullExpressionValue(newPostprocessorProducer, "producerFactory.newPostp…orProducer(inputProducer)");
            producer2 = this.producerFactory.newPostprocessorBitmapMemoryCacheProducer(newPostprocessorProducer);
            this.postprocessorSequences.put(producer, producer2);
        }
        return producer2;
    }

    private final synchronized Producer<Void> getDecodedImagePrefetchSequence(Producer<CloseableReference<CloseableImage>> producer) {
        Producer<Void> producer2;
        producer2 = this.closeableImagePrefetchSequences.get(producer);
        if (producer2 == null) {
            producer2 = this.producerFactory.newSwallowResultProducer(producer);
            this.closeableImagePrefetchSequences.put(producer, producer2);
        }
        return producer2;
    }

    private final synchronized Producer<CloseableReference<CloseableImage>> getBitmapPrepareSequence(Producer<CloseableReference<CloseableImage>> producer) {
        Producer<CloseableReference<CloseableImage>> producer2;
        producer2 = this.bitmapPrepareSequences.get(producer);
        if (producer2 == null) {
            producer2 = this.producerFactory.newBitmapPrepareProducer(producer);
            this.bitmapPrepareSequences.put(producer, producer2);
        }
        return producer2;
    }

    private final synchronized Producer<CloseableReference<CloseableImage>> getDelaySequence(Producer<CloseableReference<CloseableImage>> producer) {
        DelayProducer newDelayProducer;
        newDelayProducer = this.producerFactory.newDelayProducer(producer);
        Intrinsics.checkNotNullExpressionValue(newDelayProducer, "producerFactory.newDelayProducer(inputProducer)");
        return newDelayProducer;
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002¨\u0006\u000b"}, d2 = {"Lcom/facebook/imagepipeline/core/ProducerSequenceFactory$Companion;", "", "()V", "getShortenedUriString", "", "uri", "Landroid/net/Uri;", "validateEncodedImageRequest", "", "imageRequest", "Lcom/facebook/imagepipeline/request/ImageRequest;", "imagepipeline_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ProducerSequenceFactory.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final void validateEncodedImageRequest(ImageRequest imageRequest) {
            Preconditions.checkArgument(Boolean.valueOf(imageRequest.getLowestPermittedRequestLevel().getValue() <= ImageRequest.RequestLevel.ENCODED_MEMORY_CACHE.getValue()));
        }

        /* access modifiers changed from: private */
        public final String getShortenedUriString(Uri uri) {
            String uri2 = uri.toString();
            Intrinsics.checkNotNullExpressionValue(uri2, "uri.toString()");
            if (uri2.length() <= 30) {
                return uri2;
            }
            StringBuilder sb = new StringBuilder();
            String substring = uri2.substring(0, 30);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return sb.append(substring).append("...").toString();
        }
    }
}
