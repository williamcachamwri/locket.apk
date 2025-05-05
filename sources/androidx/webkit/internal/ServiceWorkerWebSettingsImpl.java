package androidx.webkit.internal;

import android.webkit.ServiceWorkerWebSettings;
import androidx.webkit.ServiceWorkerWebSettingsCompat;
import androidx.webkit.internal.ApiFeature;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Set;
import org.chromium.support_lib_boundary.ServiceWorkerWebSettingsBoundaryInterface;
import org.chromium.support_lib_boundary.util.BoundaryInterfaceReflectionUtil;

public class ServiceWorkerWebSettingsImpl extends ServiceWorkerWebSettingsCompat {
    private ServiceWorkerWebSettingsBoundaryInterface mBoundaryInterface;
    private ServiceWorkerWebSettings mFrameworksImpl;

    public ServiceWorkerWebSettingsImpl(ServiceWorkerWebSettings serviceWorkerWebSettings) {
        this.mFrameworksImpl = serviceWorkerWebSettings;
    }

    public ServiceWorkerWebSettingsImpl(InvocationHandler invocationHandler) {
        this.mBoundaryInterface = (ServiceWorkerWebSettingsBoundaryInterface) BoundaryInterfaceReflectionUtil.castToSuppLibClass(ServiceWorkerWebSettingsBoundaryInterface.class, invocationHandler);
    }

    private ServiceWorkerWebSettings getFrameworksImpl() {
        if (this.mFrameworksImpl == null) {
            this.mFrameworksImpl = WebViewGlueCommunicator.getCompatConverter().convertServiceWorkerSettings(Proxy.getInvocationHandler(this.mBoundaryInterface));
        }
        return this.mFrameworksImpl;
    }

    private ServiceWorkerWebSettingsBoundaryInterface getBoundaryInterface() {
        if (this.mBoundaryInterface == null) {
            this.mBoundaryInterface = (ServiceWorkerWebSettingsBoundaryInterface) BoundaryInterfaceReflectionUtil.castToSuppLibClass(ServiceWorkerWebSettingsBoundaryInterface.class, WebViewGlueCommunicator.getCompatConverter().convertServiceWorkerSettings(this.mFrameworksImpl));
        }
        return this.mBoundaryInterface;
    }

    public void setCacheMode(int i) {
        ApiFeature.N n = WebViewFeatureInternal.SERVICE_WORKER_CACHE_MODE;
        if (n.isSupportedByFramework()) {
            ApiHelperForN.setCacheMode(getFrameworksImpl(), i);
        } else if (n.isSupportedByWebView()) {
            getBoundaryInterface().setCacheMode(i);
        } else {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public int getCacheMode() {
        ApiFeature.N n = WebViewFeatureInternal.SERVICE_WORKER_CACHE_MODE;
        if (n.isSupportedByFramework()) {
            return ApiHelperForN.getCacheMode(getFrameworksImpl());
        }
        if (n.isSupportedByWebView()) {
            return getBoundaryInterface().getCacheMode();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public void setAllowContentAccess(boolean z) {
        ApiFeature.N n = WebViewFeatureInternal.SERVICE_WORKER_CONTENT_ACCESS;
        if (n.isSupportedByFramework()) {
            ApiHelperForN.setAllowContentAccess(getFrameworksImpl(), z);
        } else if (n.isSupportedByWebView()) {
            getBoundaryInterface().setAllowContentAccess(z);
        } else {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public boolean getAllowContentAccess() {
        ApiFeature.N n = WebViewFeatureInternal.SERVICE_WORKER_CONTENT_ACCESS;
        if (n.isSupportedByFramework()) {
            return ApiHelperForN.getAllowContentAccess(getFrameworksImpl());
        }
        if (n.isSupportedByWebView()) {
            return getBoundaryInterface().getAllowContentAccess();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public void setAllowFileAccess(boolean z) {
        ApiFeature.N n = WebViewFeatureInternal.SERVICE_WORKER_FILE_ACCESS;
        if (n.isSupportedByFramework()) {
            ApiHelperForN.setAllowFileAccess(getFrameworksImpl(), z);
        } else if (n.isSupportedByWebView()) {
            getBoundaryInterface().setAllowFileAccess(z);
        } else {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public boolean getAllowFileAccess() {
        ApiFeature.N n = WebViewFeatureInternal.SERVICE_WORKER_FILE_ACCESS;
        if (n.isSupportedByFramework()) {
            return ApiHelperForN.getAllowFileAccess(getFrameworksImpl());
        }
        if (n.isSupportedByWebView()) {
            return getBoundaryInterface().getAllowFileAccess();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public void setBlockNetworkLoads(boolean z) {
        ApiFeature.N n = WebViewFeatureInternal.SERVICE_WORKER_BLOCK_NETWORK_LOADS;
        if (n.isSupportedByFramework()) {
            ApiHelperForN.setBlockNetworkLoads(getFrameworksImpl(), z);
        } else if (n.isSupportedByWebView()) {
            getBoundaryInterface().setBlockNetworkLoads(z);
        } else {
            throw WebViewFeatureInternal.getUnsupportedOperationException();
        }
    }

    public boolean getBlockNetworkLoads() {
        ApiFeature.N n = WebViewFeatureInternal.SERVICE_WORKER_BLOCK_NETWORK_LOADS;
        if (n.isSupportedByFramework()) {
            return ApiHelperForN.getBlockNetworkLoads(getFrameworksImpl());
        }
        if (n.isSupportedByWebView()) {
            return getBoundaryInterface().getBlockNetworkLoads();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public Set<String> getRequestedWithHeaderOriginAllowList() {
        if (WebViewFeatureInternal.REQUESTED_WITH_HEADER_ALLOW_LIST.isSupportedByWebView()) {
            return getBoundaryInterface().getRequestedWithHeaderOriginAllowList();
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }

    public void setRequestedWithHeaderOriginAllowList(Set<String> set) {
        if (WebViewFeatureInternal.REQUESTED_WITH_HEADER_ALLOW_LIST.isSupportedByWebView()) {
            getBoundaryInterface().setRequestedWithHeaderOriginAllowList(set);
            return;
        }
        throw WebViewFeatureInternal.getUnsupportedOperationException();
    }
}
