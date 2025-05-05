package com.adsbynimbus.request;

import androidx.core.app.NotificationCompat;
import com.adsbynimbus.NimbusError;
import com.adsbynimbus.internal.Logger;
import com.adsbynimbus.openrtb.request.Video;
import com.adsbynimbus.openrtb.response.BidResponse;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/adsbynimbus/request/OkHttpNimbusClient$request$1", "Lokhttp3/Callback;", "onFailure", "", "call", "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "request_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OkHttpNimbusClient.kt */
public final class OkHttpNimbusClient$request$1 implements Callback {
    final /* synthetic */ T $callback;
    final /* synthetic */ NimbusRequest $request;
    final /* synthetic */ OkHttpNimbusClient this$0;

    OkHttpNimbusClient$request$1(OkHttpNimbusClient okHttpNimbusClient, T t, NimbusRequest nimbusRequest) {
        this.this$0 = okHttpNimbusClient;
        this.$callback = t;
        this.$request = nimbusRequest;
    }

    public void onFailure(Call call, IOException iOException) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(iOException, "e");
        this.this$0.handleError(-1, iOException, (NimbusError.Listener) this.$callback);
    }

    public void onResponse(Call call, Response response) {
        String str;
        Byte b;
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(response, io.sentry.protocol.Response.TYPE);
        try {
            ResponseBody body = response.body();
            if (!response.isSuccessful() || body == null) {
                OkHttpNimbusClient okHttpNimbusClient = this.this$0;
                int code = response.code();
                if (body == null || (str = body.string()) == null) {
                    str = response.message();
                }
                okHttpNimbusClient.handleError(code, new RuntimeException(str), (NimbusError.Listener) this.$callback);
                response.close();
            }
            OkHttpNimbusClient okHttpNimbusClient2 = this.this$0;
            Byte b2 = null;
            NimbusResponse nimbusResponse = new NimbusResponse(BidResponse.Companion.fromJson$default(BidResponse.Companion, body.string(), (Json) null, 2, (Object) null));
            NimbusRequest nimbusRequest = this.$request;
            nimbusResponse.companionAds = nimbusRequest.getCompanionAds();
            boolean z = false;
            Video video = nimbusRequest.request.imp[0].video;
            if (video != null) {
                Byte b3 = video.ext.get("is_rewarded");
                b = Byte.valueOf(b3 != null ? b3.byteValue() : 0);
            } else {
                b = null;
            }
            if (b != null && b.byteValue() == 1) {
                z = true;
            }
            if (z) {
                b2 = b;
            }
            if (b2 != null) {
                b2.byteValue();
                nimbusResponse.renderInfoOverride.put("is_rewarded", "true");
            }
            okHttpNimbusClient2.handleResponse(nimbusResponse, this.$callback);
            response.close();
        } catch (Exception e) {
            String message = e.getMessage();
            if (message == null) {
                message = "Error parsing Nimbus response";
            }
            Logger.log(6, message);
            this.this$0.handleError(-2, e, (NimbusError.Listener) this.$callback);
        } catch (Throwable th) {
            response.close();
            throw th;
        }
    }
}
