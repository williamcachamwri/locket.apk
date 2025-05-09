package com.facebook.drawee.backends.pipeline;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.time.AwakeTimeSinceBootClock;
import com.facebook.datasource.DataSource;
import com.facebook.drawable.base.DrawableWithCaches;
import com.facebook.drawee.backends.pipeline.info.ImageOriginListener;
import com.facebook.drawee.backends.pipeline.info.ImagePerfMonitor;
import com.facebook.drawee.components.DeferredReleaser;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.debug.DebugControllerOverlayDrawable;
import com.facebook.drawee.debug.listener.ImageLoadingTimeControllerListener;
import com.facebook.drawee.drawable.ScaleTypeDrawable;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import com.facebook.fresco.ui.common.ImagePerfDataListener;
import com.facebook.fresco.ui.common.MultiUriHelper;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.systrace.FrescoSystrace;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

public class PipelineDraweeController extends AbstractDraweeController<CloseableReference<CloseableImage>, ImageInfo> {
    private static final Class<?> TAG = PipelineDraweeController.class;
    private CacheKey mCacheKey;
    @Nullable
    private ImmutableList<DrawableFactory> mCustomDrawableFactories;
    private Supplier<DataSource<CloseableReference<CloseableImage>>> mDataSourceSupplier;
    private final DrawableFactory mDefaultDrawableFactory;
    private boolean mDrawDebugOverlay;
    @Nullable
    private ImageRequest[] mFirstAvailableImageRequests;
    @Nullable
    private final ImmutableList<DrawableFactory> mGlobalDrawableFactories;
    @Nullable
    private ImageOriginListener mImageOriginListener;
    @Nullable
    private ImagePerfMonitor mImagePerfMonitor;
    @Nullable
    private ImageRequest mImageRequest;
    @Nullable
    private ImageRequest mLowResImageRequest;
    @Nullable
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;
    @Nullable
    private Set<RequestListener> mRequestListeners;
    private final Resources mResources;

    public PipelineDraweeController(Resources resources, DeferredReleaser deferredReleaser, DrawableFactory drawableFactory, Executor executor, @Nullable MemoryCache<CacheKey, CloseableImage> memoryCache, @Nullable ImmutableList<DrawableFactory> immutableList) {
        super(deferredReleaser, executor, (String) null, (Object) null);
        this.mResources = resources;
        this.mDefaultDrawableFactory = new DefaultDrawableFactory(resources, drawableFactory);
        this.mGlobalDrawableFactories = immutableList;
        this.mMemoryCache = memoryCache;
    }

    public void initialize(Supplier<DataSource<CloseableReference<CloseableImage>>> supplier, String str, CacheKey cacheKey, Object obj, @Nullable ImmutableList<DrawableFactory> immutableList) {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("PipelineDraweeController#initialize");
        }
        super.initialize(str, obj);
        init(supplier);
        this.mCacheKey = cacheKey;
        setCustomDrawableFactories(immutableList);
        maybeUpdateDebugOverlay((CloseableImage) null);
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void initializePerformanceMonitoring(@Nullable ImagePerfDataListener imagePerfDataListener, AbstractDraweeControllerBuilder<PipelineDraweeControllerBuilder, ImageRequest, CloseableReference<CloseableImage>, ImageInfo> abstractDraweeControllerBuilder, Supplier<Boolean> supplier) {
        ImagePerfMonitor imagePerfMonitor = this.mImagePerfMonitor;
        if (imagePerfMonitor != null) {
            imagePerfMonitor.reset();
        }
        if (imagePerfDataListener != null) {
            if (this.mImagePerfMonitor == null) {
                this.mImagePerfMonitor = new ImagePerfMonitor(AwakeTimeSinceBootClock.get(), this, supplier);
            }
            this.mImagePerfMonitor.addImagePerfDataListener(imagePerfDataListener);
            this.mImagePerfMonitor.setEnabled(true);
        }
        this.mImageRequest = abstractDraweeControllerBuilder.getImageRequest();
        this.mFirstAvailableImageRequests = (ImageRequest[]) abstractDraweeControllerBuilder.getFirstAvailableImageRequests();
        this.mLowResImageRequest = abstractDraweeControllerBuilder.getLowResImageRequest();
    }

    public void setDrawDebugOverlay(boolean z) {
        this.mDrawDebugOverlay = z;
    }

    public void setCustomDrawableFactories(@Nullable ImmutableList<DrawableFactory> immutableList) {
        this.mCustomDrawableFactories = immutableList;
    }

    public synchronized void addRequestListener(RequestListener requestListener) {
        if (this.mRequestListeners == null) {
            this.mRequestListeners = new HashSet();
        }
        this.mRequestListeners.add(requestListener);
    }

    public synchronized void removeRequestListener(RequestListener requestListener) {
        Set<RequestListener> set = this.mRequestListeners;
        if (set != null) {
            set.remove(requestListener);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0014, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeImageOriginListener(com.facebook.drawee.backends.pipeline.info.ImageOriginListener r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.facebook.drawee.backends.pipeline.info.ImageOriginListener r0 = r2.mImageOriginListener     // Catch:{ all -> 0x0015 }
            boolean r1 = r0 instanceof com.facebook.drawee.backends.pipeline.info.ForwardingImageOriginListener     // Catch:{ all -> 0x0015 }
            if (r1 == 0) goto L_0x000e
            com.facebook.drawee.backends.pipeline.info.ForwardingImageOriginListener r0 = (com.facebook.drawee.backends.pipeline.info.ForwardingImageOriginListener) r0     // Catch:{ all -> 0x0015 }
            r0.removeImageOriginListener(r3)     // Catch:{ all -> 0x0015 }
            monitor-exit(r2)
            return
        L_0x000e:
            if (r0 != r3) goto L_0x0013
            r3 = 0
            r2.mImageOriginListener = r3     // Catch:{ all -> 0x0015 }
        L_0x0013:
            monitor-exit(r2)
            return
        L_0x0015:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.backends.pipeline.PipelineDraweeController.removeImageOriginListener(com.facebook.drawee.backends.pipeline.info.ImageOriginListener):void");
    }

    private void init(Supplier<DataSource<CloseableReference<CloseableImage>>> supplier) {
        this.mDataSourceSupplier = supplier;
        maybeUpdateDebugOverlay((CloseableImage) null);
    }

    /* access modifiers changed from: protected */
    public Resources getResources() {
        return this.mResources;
    }

    /* access modifiers changed from: protected */
    public CacheKey getCacheKey() {
        return this.mCacheKey;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0023, code lost:
        return r1;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.facebook.imagepipeline.listener.RequestListener getRequestListener() {
        /*
            r3 = this;
            monitor-enter(r3)
            com.facebook.drawee.backends.pipeline.info.ImageOriginListener r0 = r3.mImageOriginListener     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0011
            com.facebook.drawee.backends.pipeline.info.ImageOriginRequestListener r0 = new com.facebook.drawee.backends.pipeline.info.ImageOriginRequestListener     // Catch:{ all -> 0x0026 }
            java.lang.String r1 = r3.getId()     // Catch:{ all -> 0x0026 }
            com.facebook.drawee.backends.pipeline.info.ImageOriginListener r2 = r3.mImageOriginListener     // Catch:{ all -> 0x0026 }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x0026 }
            goto L_0x0012
        L_0x0011:
            r0 = 0
        L_0x0012:
            java.util.Set<com.facebook.imagepipeline.listener.RequestListener> r1 = r3.mRequestListeners     // Catch:{ all -> 0x0026 }
            if (r1 == 0) goto L_0x0024
            com.facebook.imagepipeline.listener.ForwardingRequestListener r1 = new com.facebook.imagepipeline.listener.ForwardingRequestListener     // Catch:{ all -> 0x0026 }
            java.util.Set<com.facebook.imagepipeline.listener.RequestListener> r2 = r3.mRequestListeners     // Catch:{ all -> 0x0026 }
            r1.<init>((java.util.Set<com.facebook.imagepipeline.listener.RequestListener>) r2)     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0022
            r1.addRequestListener(r0)     // Catch:{ all -> 0x0026 }
        L_0x0022:
            monitor-exit(r3)
            return r1
        L_0x0024:
            monitor-exit(r3)
            return r0
        L_0x0026:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.drawee.backends.pipeline.PipelineDraweeController.getRequestListener():com.facebook.imagepipeline.listener.RequestListener");
    }

    /* access modifiers changed from: protected */
    public DataSource<CloseableReference<CloseableImage>> getDataSource() {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("PipelineDraweeController#getDataSource");
        }
        if (FLog.isLoggable(2)) {
            FLog.v(TAG, "controller %x: getDataSource", (Object) Integer.valueOf(System.identityHashCode(this)));
        }
        DataSource<CloseableReference<CloseableImage>> dataSource = this.mDataSourceSupplier.get();
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.endSection();
        }
        return dataSource;
    }

    /* access modifiers changed from: protected */
    public Drawable createDrawable(CloseableReference<CloseableImage> closeableReference) {
        try {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.beginSection("PipelineDraweeController#createDrawable");
            }
            Preconditions.checkState(CloseableReference.isValid(closeableReference));
            CloseableImage closeableImage = closeableReference.get();
            maybeUpdateDebugOverlay(closeableImage);
            Drawable maybeCreateDrawableFromFactories = maybeCreateDrawableFromFactories(this.mCustomDrawableFactories, closeableImage);
            if (maybeCreateDrawableFromFactories != null) {
                return maybeCreateDrawableFromFactories;
            }
            Drawable maybeCreateDrawableFromFactories2 = maybeCreateDrawableFromFactories(this.mGlobalDrawableFactories, closeableImage);
            if (maybeCreateDrawableFromFactories2 != null) {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return maybeCreateDrawableFromFactories2;
            }
            Drawable createDrawable = this.mDefaultDrawableFactory.createDrawable(closeableImage);
            if (createDrawable != null) {
                if (FrescoSystrace.isTracing()) {
                    FrescoSystrace.endSection();
                }
                return createDrawable;
            }
            throw new UnsupportedOperationException("Unrecognized image class: " + closeableImage);
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    @Nullable
    private Drawable maybeCreateDrawableFromFactories(@Nullable ImmutableList<DrawableFactory> immutableList, CloseableImage closeableImage) {
        Drawable createDrawable;
        if (immutableList == null) {
            return null;
        }
        Iterator it = immutableList.iterator();
        while (it.hasNext()) {
            DrawableFactory drawableFactory = (DrawableFactory) it.next();
            if (drawableFactory.supportsImageType(closeableImage) && (createDrawable = drawableFactory.createDrawable(closeableImage)) != null) {
                return createDrawable;
            }
        }
        return null;
    }

    public void setHierarchy(@Nullable DraweeHierarchy draweeHierarchy) {
        super.setHierarchy(draweeHierarchy);
        maybeUpdateDebugOverlay((CloseableImage) null);
    }

    public boolean isSameImageRequest(@Nullable DraweeController draweeController) {
        CacheKey cacheKey = this.mCacheKey;
        if (cacheKey == null || !(draweeController instanceof PipelineDraweeController)) {
            return false;
        }
        return Objects.equal(cacheKey, ((PipelineDraweeController) draweeController).getCacheKey());
    }

    private void maybeUpdateDebugOverlay(@Nullable CloseableImage closeableImage) {
        if (this.mDrawDebugOverlay) {
            if (getControllerOverlay() == null) {
                DebugControllerOverlayDrawable debugControllerOverlayDrawable = new DebugControllerOverlayDrawable();
                addControllerListener(new ImageLoadingTimeControllerListener(debugControllerOverlayDrawable));
                setControllerOverlay(debugControllerOverlayDrawable);
            }
            if (getControllerOverlay() instanceof DebugControllerOverlayDrawable) {
                updateDebugOverlay(closeableImage, (DebugControllerOverlayDrawable) getControllerOverlay());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateDebugOverlay(@Nullable CloseableImage closeableImage, DebugControllerOverlayDrawable debugControllerOverlayDrawable) {
        ScaleTypeDrawable activeScaleTypeDrawable;
        debugControllerOverlayDrawable.setControllerId(getId());
        DraweeHierarchy hierarchy = getHierarchy();
        ScalingUtils.ScaleType scaleType = null;
        if (!(hierarchy == null || (activeScaleTypeDrawable = ScalingUtils.getActiveScaleTypeDrawable(hierarchy.getTopLevelDrawable())) == null)) {
            scaleType = activeScaleTypeDrawable.getScaleType();
        }
        debugControllerOverlayDrawable.setScaleType(scaleType);
        String callerContextString = getCallerContextString();
        if (callerContextString != null) {
            debugControllerOverlayDrawable.addAdditionalData("cc", callerContextString);
        }
        if (closeableImage != null) {
            debugControllerOverlayDrawable.setDimensions(closeableImage.getWidth(), closeableImage.getHeight());
            debugControllerOverlayDrawable.setImageSize(closeableImage.getSizeInBytes());
            return;
        }
        debugControllerOverlayDrawable.reset();
    }

    /* access modifiers changed from: protected */
    public ImageInfo getImageInfo(CloseableReference<CloseableImage> closeableReference) {
        Preconditions.checkState(CloseableReference.isValid(closeableReference));
        return closeableReference.get().getImageInfo();
    }

    /* access modifiers changed from: protected */
    public int getImageHash(@Nullable CloseableReference<CloseableImage> closeableReference) {
        if (closeableReference != null) {
            return closeableReference.getValueHash();
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public void releaseImage(@Nullable CloseableReference<CloseableImage> closeableReference) {
        CloseableReference.closeSafely((CloseableReference<?>) closeableReference);
    }

    /* access modifiers changed from: protected */
    public void releaseDrawable(@Nullable Drawable drawable) {
        if (drawable instanceof DrawableWithCaches) {
            ((DrawableWithCaches) drawable).dropCaches();
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public CloseableReference<CloseableImage> getCachedImage() {
        if (FrescoSystrace.isTracing()) {
            FrescoSystrace.beginSection("PipelineDraweeController#getCachedImage");
        }
        try {
            MemoryCache<CacheKey, CloseableImage> memoryCache = this.mMemoryCache;
            if (memoryCache != null) {
                CacheKey cacheKey = this.mCacheKey;
                if (cacheKey != null) {
                    CloseableReference<CloseableImage> closeableReference = memoryCache.get(cacheKey);
                    if (closeableReference == null || closeableReference.get().getQualityInfo().isOfFullQuality()) {
                        if (FrescoSystrace.isTracing()) {
                            FrescoSystrace.endSection();
                        }
                        return closeableReference;
                    }
                    closeableReference.close();
                    if (FrescoSystrace.isTracing()) {
                        FrescoSystrace.endSection();
                    }
                    return null;
                }
            }
            return null;
        } finally {
            if (FrescoSystrace.isTracing()) {
                FrescoSystrace.endSection();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onImageLoadedFromCacheImmediately(String str, CloseableReference<CloseableImage> closeableReference) {
        super.onImageLoadedFromCacheImmediately(str, closeableReference);
        synchronized (this) {
            ImageOriginListener imageOriginListener = this.mImageOriginListener;
            if (imageOriginListener != null) {
                imageOriginListener.onImageLoaded(str, 6, true, "PipelineDraweeController");
            }
        }
    }

    /* access modifiers changed from: protected */
    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier() {
        return this.mDataSourceSupplier;
    }

    public String toString() {
        return Objects.toStringHelper((Object) this).add("super", (Object) super.toString()).add("dataSourceSupplier", (Object) this.mDataSourceSupplier).toString();
    }

    @Nullable
    public Map<String, Object> obtainExtrasFromImage(ImageInfo imageInfo) {
        if (imageInfo == null) {
            return null;
        }
        return imageInfo.getExtras();
    }

    /* access modifiers changed from: protected */
    @Nullable
    public Uri getMainUri() {
        return MultiUriHelper.getMainUri(this.mImageRequest, this.mLowResImageRequest, this.mFirstAvailableImageRequests, ImageRequest.REQUEST_TO_URI_FN);
    }

    /* access modifiers changed from: protected */
    @Nullable
    public String getCallerContextString() {
        Object callerContext = getCallerContext();
        if (callerContext == null) {
            return null;
        }
        return callerContext.toString();
    }
}
