package com.facebook.drawee.backends.pipeline;

import android.content.Context;
import android.content.res.Resources;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.fresco.ui.common.ControllerListener2;
import com.facebook.fresco.ui.common.ImagePerfDataListener;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import java.util.Set;
import javax.annotation.Nullable;

public class PipelineDraweeControllerBuilderSupplier implements Supplier<PipelineDraweeControllerBuilder> {
    private final Set<ControllerListener> mBoundControllerListeners;
    private final Set<ControllerListener2> mBoundControllerListeners2;
    private final Context mContext;
    @Nullable
    private final ImagePerfDataListener mDefaultImagePerfDataListener;
    private final ImagePipeline mImagePipeline;
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    public PipelineDraweeControllerBuilderSupplier(Context context) {
        this(context, (DraweeConfig) null);
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, @Nullable DraweeConfig draweeConfig) {
        this(context, ImagePipelineFactory.getInstance(), draweeConfig);
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, ImagePipelineFactory imagePipelineFactory, @Nullable DraweeConfig draweeConfig) {
        this(context, imagePipelineFactory, (Set<ControllerListener>) null, (Set<ControllerListener2>) null, draweeConfig);
    }

    public PipelineDraweeControllerBuilderSupplier(Context context, ImagePipelineFactory imagePipelineFactory, Set<ControllerListener> set, Set<ControllerListener2> set2, @Nullable DraweeConfig draweeConfig) {
        Supplier<Boolean> supplier;
        this.mContext = context;
        ImagePipeline imagePipeline = imagePipelineFactory.getImagePipeline();
        this.mImagePipeline = imagePipeline;
        if (draweeConfig == null || draweeConfig.getPipelineDraweeControllerFactory() == null) {
            this.mPipelineDraweeControllerFactory = new PipelineDraweeControllerFactory();
        } else {
            this.mPipelineDraweeControllerFactory = draweeConfig.getPipelineDraweeControllerFactory();
        }
        PipelineDraweeControllerFactory pipelineDraweeControllerFactory = this.mPipelineDraweeControllerFactory;
        Resources resources = context.getResources();
        DeferredReleaser instance = DeferredReleaser.getInstance();
        DrawableFactory animatedDrawableFactory = imagePipelineFactory.getAnimatedDrawableFactory(context);
        UiThreadImmediateExecutorService instance2 = UiThreadImmediateExecutorService.getInstance();
        MemoryCache<CacheKey, CloseableImage> bitmapMemoryCache = imagePipeline.getBitmapMemoryCache();
        ImagePerfDataListener imagePerfDataListener = null;
        ImmutableList<DrawableFactory> customDrawableFactories = draweeConfig != null ? draweeConfig.getCustomDrawableFactories() : null;
        if (draweeConfig != null) {
            supplier = draweeConfig.getDebugOverlayEnabledSupplier();
        } else {
            supplier = null;
        }
        pipelineDraweeControllerFactory.init(resources, instance, animatedDrawableFactory, instance2, bitmapMemoryCache, customDrawableFactories, supplier);
        this.mBoundControllerListeners = set;
        this.mBoundControllerListeners2 = set2;
        this.mDefaultImagePerfDataListener = draweeConfig != null ? draweeConfig.getImagePerfDataListener() : imagePerfDataListener;
    }

    public PipelineDraweeControllerBuilder get() {
        return new PipelineDraweeControllerBuilder(this.mContext, this.mPipelineDraweeControllerFactory, this.mImagePipeline, this.mBoundControllerListeners, this.mBoundControllerListeners2).setPerfDataListener(this.mDefaultImagePerfDataListener);
    }
}
