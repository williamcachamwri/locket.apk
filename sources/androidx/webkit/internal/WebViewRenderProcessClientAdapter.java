package androidx.webkit.internal;

import android.webkit.WebView;
import androidx.webkit.WebViewRenderProcessClient;
import java.lang.reflect.InvocationHandler;
import java.util.concurrent.Executor;
import org.chromium.support_lib_boundary.WebViewRendererClientBoundaryInterface;

public class WebViewRenderProcessClientAdapter implements WebViewRendererClientBoundaryInterface {
    private static final String[] sSupportedFeatures = {"WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE"};
    private final Executor mExecutor;
    private final WebViewRenderProcessClient mWebViewRenderProcessClient;

    public WebViewRenderProcessClientAdapter(Executor executor, WebViewRenderProcessClient webViewRenderProcessClient) {
        this.mExecutor = executor;
        this.mWebViewRenderProcessClient = webViewRenderProcessClient;
    }

    public WebViewRenderProcessClient getWebViewRenderProcessClient() {
        return this.mWebViewRenderProcessClient;
    }

    public final String[] getSupportedFeatures() {
        return sSupportedFeatures;
    }

    public final void onRendererUnresponsive(WebView webView, InvocationHandler invocationHandler) {
        WebViewRenderProcessImpl forInvocationHandler = WebViewRenderProcessImpl.forInvocationHandler(invocationHandler);
        WebViewRenderProcessClient webViewRenderProcessClient = this.mWebViewRenderProcessClient;
        Executor executor = this.mExecutor;
        if (executor == null) {
            webViewRenderProcessClient.onRenderProcessUnresponsive(webView, forInvocationHandler);
        } else {
            executor.execute(new WebViewRenderProcessClientAdapter$$ExternalSyntheticLambda0(webViewRenderProcessClient, webView, forInvocationHandler));
        }
    }

    public final void onRendererResponsive(WebView webView, InvocationHandler invocationHandler) {
        WebViewRenderProcessImpl forInvocationHandler = WebViewRenderProcessImpl.forInvocationHandler(invocationHandler);
        WebViewRenderProcessClient webViewRenderProcessClient = this.mWebViewRenderProcessClient;
        Executor executor = this.mExecutor;
        if (executor == null) {
            webViewRenderProcessClient.onRenderProcessResponsive(webView, forInvocationHandler);
        } else {
            executor.execute(new WebViewRenderProcessClientAdapter$$ExternalSyntheticLambda1(webViewRenderProcessClient, webView, forInvocationHandler));
        }
    }
}
