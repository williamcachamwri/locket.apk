package com.adsbynimbus.render;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import androidx.collection.SimpleArrayMap;
import androidx.media3.common.MimeTypes;
import com.adsbynimbus.NimbusAd;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.internal.Logger;
import com.adsbynimbus.render.internal.AdLoader;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u0000 \u000e2\u00020\u0001:\u0003\r\u000e\u000fJ3\u0010\u0002\u001a\u00020\u0003\"\f\b\u0000\u0010\u0004*\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u0002H\u0004H'¢\u0006\u0002\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/adsbynimbus/render/Renderer;", "", "render", "", "T", "Lcom/adsbynimbus/render/Renderer$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "ad", "Lcom/adsbynimbus/NimbusAd;", "container", "Landroid/view/ViewGroup;", "listener", "(Lcom/adsbynimbus/NimbusAd;Landroid/view/ViewGroup;Lcom/adsbynimbus/render/Renderer$Listener;)V", "Blocking", "Companion", "Listener", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Renderer.kt */
public interface Renderer {
    public static final SimpleArrayMap<String, Blocking> BLOCKING;
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final SimpleArrayMap<String, Renderer> INLINE = new SimpleArrayMap<>(0, 1, (DefaultConstructorMarker) null);
    public static final List<Interceptor> interceptors = new ArrayList();

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/adsbynimbus/render/Renderer$Blocking;", "", "render", "Lcom/adsbynimbus/render/AdController;", "ad", "Lcom/adsbynimbus/NimbusAd;", "context", "Landroid/content/Context;", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Renderer.kt */
    public interface Blocking {
        AdController render(NimbusAd nimbusAd, Context context);
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Renderer.kt */
    public static final class DefaultImpls {
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/adsbynimbus/render/Renderer$Listener;", "", "onAdRendered", "", "controller", "Lcom/adsbynimbus/render/AdController;", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Renderer.kt */
    public interface Listener {
        void onAdRendered(AdController adController);
    }

    @JvmStatic
    static <T extends Listener & NimbusError.Listener> void loadAd(NimbusAd nimbusAd, ViewGroup viewGroup, T t) {
        Companion.loadAd(nimbusAd, viewGroup, t);
    }

    @JvmStatic
    static AdController loadBlockingAd(Context context, NimbusAd nimbusAd) {
        return Companion.loadBlockingAd(context, nimbusAd);
    }

    @JvmStatic
    @Deprecated(message = "Use Context.loadBlockingAd extension function instead.")
    static AdController loadBlockingAd(NimbusAd nimbusAd, Activity activity) {
        return Companion.loadBlockingAd(nimbusAd, activity);
    }

    <T extends Listener & NimbusError.Listener> void render(NimbusAd nimbusAd, ViewGroup viewGroup, T t);

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J3\u0010\f\u001a\u00020\r\"\f\b\u0000\u0010\u000e*\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u0002H\u000eH\u0007¢\u0006\u0002\u0010\u0016J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u0018*\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0007R\u001f\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001R\u001f\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001R\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001¨\u0006\u001c"}, d2 = {"Lcom/adsbynimbus/render/Renderer$Companion;", "", "()V", "BLOCKING", "Landroidx/collection/SimpleArrayMap;", "", "Lcom/adsbynimbus/render/Renderer$Blocking;", "INLINE", "Lcom/adsbynimbus/render/Renderer;", "interceptors", "", "Lcom/adsbynimbus/render/Interceptor;", "loadAd", "", "T", "Lcom/adsbynimbus/render/Renderer$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "ad", "Lcom/adsbynimbus/NimbusAd;", "container", "Landroid/view/ViewGroup;", "listener", "(Lcom/adsbynimbus/NimbusAd;Landroid/view/ViewGroup;Lcom/adsbynimbus/render/Renderer$Listener;)V", "loadBlockingAd", "Lcom/adsbynimbus/render/AdController;", "activity", "Landroid/app/Activity;", "Landroid/content/Context;", "render_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Renderer.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        @JvmStatic
        public final <T extends Listener & NimbusError.Listener> void loadAd(NimbusAd nimbusAd, ViewGroup viewGroup, T t) {
            Intrinsics.checkNotNullParameter(nimbusAd, "ad");
            Intrinsics.checkNotNullParameter(viewGroup, TtmlNode.RUBY_CONTAINER);
            Intrinsics.checkNotNullParameter(t, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            Renderer renderer = Renderer.INLINE.get(nimbusAd.network());
            if (renderer == null) {
                renderer = Renderer.INLINE.get(nimbusAd.type());
                if (Intrinsics.areEqual((Object) nimbusAd.type(), (Object) MimeTypes.BASE_TYPE_VIDEO) && nimbusAd.useNewRenderer() && Renderer.INLINE.containsKey("vast")) {
                    renderer = Renderer.INLINE.get("vast");
                }
            }
            if (renderer != null) {
                new AdLoader(nimbusAd, Renderer.interceptors).load(renderer, viewGroup, t);
            } else {
                ((NimbusError.Listener) t).onError(new NimbusError(NimbusError.ErrorType.RENDERER_ERROR, "No renderer installed for inline " + nimbusAd.network() + ' ' + nimbusAd.type(), (Throwable) null));
            }
        }

        @JvmStatic
        public final AdController loadBlockingAd(Context context, NimbusAd nimbusAd) {
            Intrinsics.checkNotNullParameter(context, "<this>");
            Intrinsics.checkNotNullParameter(nimbusAd, "ad");
            Blocking blocking = Renderer.BLOCKING.get(nimbusAd.network());
            if (blocking == null) {
                blocking = Renderer.BLOCKING.get(nimbusAd.type());
            }
            if (blocking != null) {
                return new AdLoader(nimbusAd, Renderer.interceptors).loadBlocking(blocking, context);
            }
            Logger.log(5, "No renderer installed for blocking " + nimbusAd.network() + ' ' + nimbusAd.type());
            return null;
        }

        @JvmStatic
        @Deprecated(message = "Use Context.loadBlockingAd extension function instead.")
        public final AdController loadBlockingAd(NimbusAd nimbusAd, Activity activity) {
            Intrinsics.checkNotNullParameter(nimbusAd, "ad");
            Intrinsics.checkNotNullParameter(activity, "activity");
            return loadBlockingAd((Context) activity, nimbusAd);
        }
    }

    static {
        SimpleArrayMap<String, Blocking> simpleArrayMap = new SimpleArrayMap<>(0, 1, (DefaultConstructorMarker) null);
        simpleArrayMap.put(StaticAdRenderer.STATIC_AD_TYPE, BlockingAdRenderer.INSTANCE);
        simpleArrayMap.put(MimeTypes.BASE_TYPE_VIDEO, BlockingAdRenderer.INSTANCE);
        BLOCKING = simpleArrayMap;
    }
}
