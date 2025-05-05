package com.brentvatne.common.react;

import com.brentvatne.common.react.VideoEventEmitter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import java.io.PrintWriter;
import java.io.StringWriter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u00062\u0006\u0010\u0007\u001a\u00020\u0003H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "errorString", "", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "errorCode", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoEventEmitter.kt */
final class VideoEventEmitter$addEventEmitters$3 extends Lambda implements Function3<String, Exception, String, Unit> {
    final /* synthetic */ VideoEventEmitter.EventBuilder $event;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoEventEmitter$addEventEmitters$3(VideoEventEmitter.EventBuilder eventBuilder) {
        super(3);
        this.$event = eventBuilder;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        invoke((String) obj, (Exception) obj2, (String) obj3);
        return Unit.INSTANCE;
    }

    public final void invoke(final String str, final Exception exc, final String str2) {
        Intrinsics.checkNotNullParameter(str, "errorString");
        Intrinsics.checkNotNullParameter(exc, "exception");
        Intrinsics.checkNotNullParameter(str2, "errorCode");
        this.$event.dispatch(EventTypes.EVENT_ERROR, new Function1<WritableMap, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((WritableMap) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(WritableMap writableMap) {
                Intrinsics.checkNotNullParameter(writableMap, "$this$dispatch");
                WritableMap createMap = Arguments.createMap();
                Exception exc = exc;
                String str = str;
                String str2 = str2;
                StringWriter stringWriter = new StringWriter();
                exc.printStackTrace(new PrintWriter(stringWriter));
                String stringWriter2 = stringWriter.toString();
                Intrinsics.checkNotNullExpressionValue(stringWriter2, "toString(...)");
                createMap.putString("errorString", str);
                createMap.putString("errorException", exc.toString());
                createMap.putString("errorCode", str2);
                createMap.putString("errorStackTrace", stringWriter2);
                Unit unit = Unit.INSTANCE;
                writableMap.putMap("error", createMap);
            }
        });
    }
}
