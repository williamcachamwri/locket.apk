package androidx.camera.extensions;

import android.content.Context;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.CameraControl;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraProvider;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.ExtendedCameraConfigProviderStore;
import androidx.camera.core.impl.utils.ContextUtil;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.Futures;
import androidx.camera.extensions.impl.InitializerImpl;
import androidx.camera.extensions.internal.ClientVersion;
import androidx.camera.extensions.internal.ExtensionVersion;
import androidx.camera.extensions.internal.Version;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.ExecutionException;

public final class ExtensionsManager {
    private static final Object EXTENSIONS_LOCK = new Object();
    private static final String TAG = "ExtensionsManager";
    private static ListenableFuture<Void> sDeinitializeFuture;
    private static ExtensionsManager sExtensionsManager;
    private static ListenableFuture<ExtensionsManager> sInitializeFuture;
    private final ExtensionsAvailability mExtensionsAvailability;
    private final ExtensionsInfo mExtensionsInfo;

    enum ExtensionsAvailability {
        LIBRARY_AVAILABLE,
        LIBRARY_UNAVAILABLE_ERROR_LOADING,
        LIBRARY_UNAVAILABLE_MISSING_IMPLEMENTATION,
        NONE
    }

    public static ListenableFuture<ExtensionsManager> getInstanceAsync(Context context, CameraProvider cameraProvider) {
        return getInstanceAsync(context, cameraProvider, ClientVersion.getCurrentVersion());
    }

    public static ListenableFuture<ExtensionsManager> getInstanceAsync(Context context, CameraProvider cameraProvider, String str) {
        ClientVersion clientVersion = new ClientVersion(str);
        ClientVersion.setCurrentVersion(clientVersion);
        return getInstanceAsync(context, cameraProvider, clientVersion);
    }

    static ListenableFuture<ExtensionsManager> getInstanceAsync(Context context, CameraProvider cameraProvider, ClientVersion clientVersion) {
        synchronized (EXTENSIONS_LOCK) {
            ListenableFuture<Void> listenableFuture = sDeinitializeFuture;
            if (listenableFuture != null) {
                if (!listenableFuture.isDone()) {
                    throw new IllegalStateException("Not yet done deinitializing extensions");
                }
            }
            sDeinitializeFuture = null;
            if (ExtensionVersion.getRuntimeVersion() == null) {
                ListenableFuture<ExtensionsManager> immediateFuture = Futures.immediateFuture(getOrCreateExtensionsManager(ExtensionsAvailability.NONE, cameraProvider));
                return immediateFuture;
            }
            if (!ClientVersion.isMaximumCompatibleVersion(Version.VERSION_1_0)) {
                if (!ExtensionVersion.isMaximumCompatibleVersion(Version.VERSION_1_0)) {
                    if (sInitializeFuture == null) {
                        sInitializeFuture = CallbackToFutureAdapter.getFuture(new ExtensionsManager$$ExternalSyntheticLambda1(clientVersion, context, cameraProvider));
                    }
                    ListenableFuture<ExtensionsManager> listenableFuture2 = sInitializeFuture;
                    return listenableFuture2;
                }
            }
            ListenableFuture<ExtensionsManager> immediateFuture2 = Futures.immediateFuture(getOrCreateExtensionsManager(ExtensionsAvailability.LIBRARY_AVAILABLE, cameraProvider));
            return immediateFuture2;
        }
    }

    static /* synthetic */ Object lambda$getInstanceAsync$0(ClientVersion clientVersion, Context context, final CameraProvider cameraProvider, final CallbackToFutureAdapter.Completer completer) throws Exception {
        try {
            InitializerImpl.init(clientVersion.toVersionString(), ContextUtil.getApplicationContext(context), new InitializerImpl.OnExtensionsInitializedCallback() {
                public void onSuccess() {
                    Logger.d(ExtensionsManager.TAG, "Successfully initialized extensions");
                    CallbackToFutureAdapter.Completer.this.set(ExtensionsManager.getOrCreateExtensionsManager(ExtensionsAvailability.LIBRARY_AVAILABLE, cameraProvider));
                }

                public void onFailure(int i) {
                    Logger.e(ExtensionsManager.TAG, "Failed to initialize extensions");
                    CallbackToFutureAdapter.Completer.this.set(ExtensionsManager.getOrCreateExtensionsManager(ExtensionsAvailability.LIBRARY_UNAVAILABLE_ERROR_LOADING, cameraProvider));
                }
            }, CameraXExecutors.directExecutor());
            return "Initialize extensions";
        } catch (AbstractMethodError | NoClassDefFoundError | NoSuchMethodError e) {
            Logger.e(TAG, "Failed to initialize extensions. Some classes or methods are missed in the vendor library. " + e);
            completer.set(getOrCreateExtensionsManager(ExtensionsAvailability.LIBRARY_UNAVAILABLE_MISSING_IMPLEMENTATION, cameraProvider));
            return "Initialize extensions";
        } catch (RuntimeException e2) {
            Logger.e(TAG, "Failed to initialize extensions. Something wents wrong when initializing the vendor library. " + e2);
            completer.set(getOrCreateExtensionsManager(ExtensionsAvailability.LIBRARY_UNAVAILABLE_ERROR_LOADING, cameraProvider));
            return "Initialize extensions";
        }
    }

    public ListenableFuture<Void> shutdown() {
        synchronized (EXTENSIONS_LOCK) {
            if (ExtensionVersion.getRuntimeVersion() == null) {
                sInitializeFuture = null;
                sExtensionsManager = null;
                ExtensionVersion.injectInstance((ExtensionVersion) null);
                ListenableFuture<Void> immediateFuture = Futures.immediateFuture(null);
                return immediateFuture;
            }
            ExtensionVersion.injectInstance((ExtensionVersion) null);
            ListenableFuture<ExtensionsManager> listenableFuture = sInitializeFuture;
            if (listenableFuture == null) {
                ListenableFuture<Void> immediateFuture2 = Futures.immediateFuture(null);
                return immediateFuture2;
            }
            ListenableFuture<Void> listenableFuture2 = sDeinitializeFuture;
            if (listenableFuture2 != null) {
                return listenableFuture2;
            }
            try {
                listenableFuture.get();
                sInitializeFuture = null;
                ExtensionsAvailability extensionsAvailability = sExtensionsManager.mExtensionsAvailability;
                sExtensionsManager = null;
                if (extensionsAvailability == ExtensionsAvailability.LIBRARY_AVAILABLE) {
                    ExtendedCameraConfigProviderStore.clear();
                    sDeinitializeFuture = CallbackToFutureAdapter.getFuture(new ExtensionsManager$$ExternalSyntheticLambda0(this));
                } else {
                    sDeinitializeFuture = Futures.immediateFuture(null);
                }
                ListenableFuture<Void> listenableFuture3 = sDeinitializeFuture;
                return listenableFuture3;
            } catch (ExecutionException e) {
                e = e;
                ListenableFuture<Void> immediateFailedFuture = Futures.immediateFailedFuture(e);
                sDeinitializeFuture = immediateFailedFuture;
                return immediateFailedFuture;
            } catch (InterruptedException e2) {
                e = e2;
                ListenableFuture<Void> immediateFailedFuture2 = Futures.immediateFailedFuture(e);
                sDeinitializeFuture = immediateFailedFuture2;
                return immediateFailedFuture2;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$shutdown$1$androidx-camera-extensions-ExtensionsManager  reason: not valid java name */
    public /* synthetic */ Object m228lambda$shutdown$1$androidxcameraextensionsExtensionsManager(final CallbackToFutureAdapter.Completer completer) throws Exception {
        try {
            InitializerImpl.deinit(new InitializerImpl.OnExtensionsDeinitializedCallback() {
                public void onSuccess() {
                    completer.set(null);
                }

                public void onFailure(int i) {
                    completer.setException(new Exception("Failed to deinitialize extensions."));
                }
            }, CameraXExecutors.directExecutor());
            return null;
        } catch (NoClassDefFoundError | NoSuchMethodError e) {
            completer.setException(e);
            return null;
        }
    }

    static ExtensionsManager getOrCreateExtensionsManager(ExtensionsAvailability extensionsAvailability, CameraProvider cameraProvider) {
        synchronized (EXTENSIONS_LOCK) {
            ExtensionsManager extensionsManager = sExtensionsManager;
            if (extensionsManager != null) {
                return extensionsManager;
            }
            ExtensionsManager extensionsManager2 = new ExtensionsManager(extensionsAvailability, cameraProvider);
            sExtensionsManager = extensionsManager2;
            return extensionsManager2;
        }
    }

    public CameraSelector getExtensionEnabledCameraSelector(CameraSelector cameraSelector, int i) {
        if (i == 0) {
            return cameraSelector;
        }
        if (this.mExtensionsAvailability == ExtensionsAvailability.LIBRARY_AVAILABLE) {
            return this.mExtensionsInfo.getExtensionCameraSelectorAndInjectCameraConfig(cameraSelector, i);
        }
        throw new IllegalArgumentException("This device doesn't support extensions function! isExtensionAvailable should be checked first before calling getExtensionEnabledCameraSelector.");
    }

    public boolean isExtensionAvailable(CameraSelector cameraSelector, int i) {
        if (i == 0) {
            return true;
        }
        if (this.mExtensionsAvailability != ExtensionsAvailability.LIBRARY_AVAILABLE) {
            return false;
        }
        return this.mExtensionsInfo.isExtensionAvailable(cameraSelector, i);
    }

    public Range<Long> getEstimatedCaptureLatencyRange(CameraSelector cameraSelector, int i) {
        if (i == 0 || this.mExtensionsAvailability != ExtensionsAvailability.LIBRARY_AVAILABLE) {
            return null;
        }
        return this.mExtensionsInfo.getEstimatedCaptureLatencyRange(cameraSelector, i, (Size) null);
    }

    public boolean isImageAnalysisSupported(CameraSelector cameraSelector, int i) {
        if (i == 0) {
            return true;
        }
        if (this.mExtensionsAvailability != ExtensionsAvailability.LIBRARY_AVAILABLE) {
            return false;
        }
        return this.mExtensionsInfo.isImageAnalysisSupported(cameraSelector, i);
    }

    public CameraExtensionsControl getCameraExtensionsControl(CameraControl cameraControl) {
        return CameraExtensionsControls.from(cameraControl);
    }

    public CameraExtensionsInfo getCameraExtensionsInfo(CameraInfo cameraInfo) {
        return CameraExtensionsInfos.from(cameraInfo);
    }

    /* access modifiers changed from: package-private */
    public ExtensionsAvailability getExtensionsAvailability() {
        return this.mExtensionsAvailability;
    }

    /* access modifiers changed from: package-private */
    public void setVendorExtenderFactory(VendorExtenderFactory vendorExtenderFactory) {
        this.mExtensionsInfo.setVendorExtenderFactory(vendorExtenderFactory);
    }

    private ExtensionsManager(ExtensionsAvailability extensionsAvailability, CameraProvider cameraProvider) {
        this.mExtensionsAvailability = extensionsAvailability;
        this.mExtensionsInfo = new ExtensionsInfo(cameraProvider);
    }
}
