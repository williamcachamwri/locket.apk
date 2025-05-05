package com.adsbynimbus.request;

import com.adsbynimbus.NimbusError;
import com.adsbynimbus.internal.Component;
import com.adsbynimbus.openrtb.request.BidRequest;
import com.adsbynimbus.request.NimbusResponse;
import com.adsbynimbus.request.RequestManager;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import io.sentry.SentryBaseEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u000bH\u0016J+\u0010\f\u001a\u00020\u000b\"\f\b\u0000\u0010\r*\u00020\u000e*\u00020\u000f2\u0006\u0010\f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u0002H\rH\u0016¢\u0006\u0002\u0010\u0012R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\t8\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/adsbynimbus/request/OkHttpNimbusClient;", "Lcom/adsbynimbus/request/RequestManager$Client;", "Lcom/adsbynimbus/internal/Component;", "builder", "Lokhttp3/OkHttpClient$Builder;", "(Lokhttp3/OkHttpClient$Builder;)V", "client", "Lokhttp3/OkHttpClient;", "jsonMediaType", "Lokhttp3/MediaType;", "install", "", "request", "T", "Lcom/adsbynimbus/request/NimbusResponse$Listener;", "Lcom/adsbynimbus/NimbusError$Listener;", "Lcom/adsbynimbus/request/NimbusRequest;", "callback", "(Lcom/adsbynimbus/request/NimbusRequest;Lcom/adsbynimbus/request/NimbusResponse$Listener;)V", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OkHttpNimbusClient.kt */
public final class OkHttpNimbusClient implements RequestManager.Client, Component {
    public final OkHttpClient client;
    public final MediaType jsonMediaType;

    public OkHttpNimbusClient() {
        this((OkHttpClient.Builder) null, 1, (DefaultConstructorMarker) null);
    }

    public OkHttpNimbusClient(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        MediaType mediaType = MediaType.Companion.get("application/json; charset=utf-8");
        this.jsonMediaType = mediaType;
        this.client = builder.addInterceptor(new GzipRequestInterceptor(mediaType)).build();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ OkHttpNimbusClient(OkHttpClient.Builder builder, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new OkHttpClient.Builder() : builder);
    }

    public void handleError(int i, Exception exc, NimbusError.Listener listener) {
        RequestManager.Client.DefaultImpls.handleError(this, i, exc, listener);
    }

    public void handleResponse(NimbusResponse nimbusResponse, NimbusResponse.Listener listener) {
        RequestManager.Client.DefaultImpls.handleResponse(this, nimbusResponse, listener);
    }

    public Map<String, String> requiredHeaders(NimbusRequest nimbusRequest) {
        return RequestManager.Client.DefaultImpls.requiredHeaders(this, nimbusRequest);
    }

    public void install() {
        RequestManager.Companion.setClient(this);
    }

    public <T extends NimbusResponse.Listener & NimbusError.Listener> void request(NimbusRequest nimbusRequest, T t) {
        boolean z;
        boolean z2;
        Intrinsics.checkNotNullParameter(nimbusRequest, SentryBaseEvent.JsonKeys.REQUEST);
        Intrinsics.checkNotNullParameter(t, "callback");
        Map<String, String> headers = RequestExtensions.headers(nimbusRequest);
        Iterable values = headers.values();
        if (!(values instanceof Collection) || !((Collection) values).isEmpty()) {
            Iterator it = values.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                z = false;
                if (((String) it.next()).length() > 0) {
                    z2 = true;
                    continue;
                } else {
                    z2 = false;
                    continue;
                }
                if (!z2) {
                    break;
                }
            }
        }
        z = true;
        if (!z) {
            headers = null;
        }
        if (headers == null) {
            OkHttpNimbusClient okHttpNimbusClient = this;
            ((NimbusError.Listener) t).onError(new NimbusError(NimbusError.ErrorType.NOT_INITIALIZED, "Nimbus not initialized", (Throwable) null));
            return;
        }
        FirebasePerfOkHttpClient.enqueue(this.client.newCall(new Request.Builder().url(nimbusRequest.getRequestUrl()).headers(Headers.Companion.of(headers)).post(RequestBody.Companion.create(BidRequest.Companion.toJson$default(BidRequest.Companion, nimbusRequest.request, (Json) null, 1, (Object) null), this.jsonMediaType)).build()), new OkHttpNimbusClient$request$1(this, t, nimbusRequest));
    }
}
