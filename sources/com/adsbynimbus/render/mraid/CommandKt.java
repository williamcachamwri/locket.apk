package com.adsbynimbus.render.mraid;

import androidx.core.app.NotificationCompat;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.StringFormat;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonKt;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a9\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005*\u00060\u0005j\u0002`\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\u000b\"\u00020\tH\u0000¢\u0006\u0002\u0010\f\u001a \u0010\r\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005*\u00060\u0005j\u0002`\u00072\u0006\u0010\u000e\u001a\u00020\tH\u0000\u001a(\u0010\u000f\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005*\u00060\u0005j\u0002`\u00072\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u001a \u0010\u0014\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005*\u00060\u0005j\u0002`\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u0000\u001a \u0010\u0017\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005*\u00060\u0005j\u0002`\u00072\u0006\u0010\u0018\u001a\u00020\tH\u0000\u001a\"\u0010\u0019\u001a\u00020\u001a*\u00060\u0005j\u0002`\u00072\u0006\u0010\u001b\u001a\u00020\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0000\u001a(\u0010\u001e\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005*\u00060\u0005j\u0002`\u00072\u0006\u0010\u001f\u001a\u00020\t2\u0006\u0010 \u001a\u00020\tH\u0000\u001a \u0010!\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005*\u00060\u0005j\u0002`\u00072\u0006\u0010\u0018\u001a\u00020\tH\u0000\"\u0014\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003¨\u0006\""}, d2 = {"mraidSerializer", "Lkotlinx/serialization/json/Json;", "getMraidSerializer", "()Lkotlinx/serialization/json/Json;", "dispatch", "Ljava/lang/StringBuilder;", "kotlin.jvm.PlatformType", "Lkotlin/text/StringBuilder;", "event", "", "arguments", "", "(Ljava/lang/StringBuilder;Ljava/lang/String;[Ljava/lang/String;)Ljava/lang/StringBuilder;", "dispatchError", "description", "dispatchExposureChange", "exposure", "", "visibleRect", "Lcom/adsbynimbus/render/mraid/Position;", "dispatchSizeChange", "size", "Lcom/adsbynimbus/render/mraid/Size;", "dispatchStateChange", "state", "updatePosition", "", "position", "includeDefault", "", "updateProperty", "propName", "update", "updateState", "static_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Command.kt */
public final class CommandKt {
    private static final Json mraidSerializer = JsonKt.Json$default((Json) null, CommandKt$mraidSerializer$1.INSTANCE, 1, (Object) null);

    public static final Json getMraidSerializer() {
        return mraidSerializer;
    }

    public static final StringBuilder dispatch(StringBuilder sb, String str, String... strArr) {
        String str2;
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(str, NotificationCompat.CATEGORY_EVENT);
        Intrinsics.checkNotNullParameter(strArr, "arguments");
        StringBuilder append = new StringBuilder("mraid.dispatch('").append(str).append('\'');
        if (!(!(strArr.length == 0))) {
            strArr = null;
        }
        String[] strArr2 = strArr;
        if (strArr2 == null || (str2 = ArraysKt.joinToString$default((Object[]) strArr2, (CharSequence) ",", (CharSequence) ",", (CharSequence) null, 0, (CharSequence) null, (Function1) null, 60, (Object) null)) == null) {
            str2 = "";
        }
        return sb.append(append.append(str2).append(");").toString());
    }

    public static final StringBuilder updateProperty(StringBuilder sb, String str, String str2) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(str, "propName");
        Intrinsics.checkNotNullParameter(str2, "update");
        return sb.append("mraid.h." + str + '=' + str2 + ';');
    }

    public static final StringBuilder dispatchError(StringBuilder sb, String str) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(str, "description");
        return dispatch(sb, "error", "'" + str + '\'');
    }

    public static final StringBuilder dispatchExposureChange(StringBuilder sb, int i, Position position) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(position, "visibleRect");
        String valueOf = String.valueOf(i);
        StringFormat stringFormat = mraidSerializer;
        stringFormat.getSerializersModule();
        return dispatch(sb, HostKt.EXPOSURE_CHANGE, valueOf, stringFormat.encodeToString(Position.Companion.serializer(), position));
    }

    public static final StringBuilder dispatchStateChange(StringBuilder sb, String str) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(str, "state");
        return dispatch(sb, HostKt.STATE_CHANGE, "'" + str + '\'');
    }

    public static final StringBuilder updateState(StringBuilder sb, String str) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(str, "state");
        return updateProperty(sb, "State", "'" + str + '\'');
    }

    public static final StringBuilder dispatchSizeChange(StringBuilder sb, Size size) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(size, "size");
        StringFormat stringFormat = mraidSerializer;
        stringFormat.getSerializersModule();
        return dispatch(sb, HostKt.SIZE_CHANGE, stringFormat.encodeToString(Size.Companion.serializer(), size));
    }

    public static /* synthetic */ void updatePosition$default(StringBuilder sb, Position position, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        updatePosition(sb, position, z);
    }

    public static final void updatePosition(StringBuilder sb, Position position, boolean z) {
        Intrinsics.checkNotNullParameter(sb, "<this>");
        Intrinsics.checkNotNullParameter(position, ViewProps.POSITION);
        StringFormat stringFormat = mraidSerializer;
        stringFormat.getSerializersModule();
        String encodeToString = stringFormat.encodeToString(Position.Companion.serializer(), position);
        updateProperty(sb, "CurrentPosition", encodeToString);
        if (z) {
            updateProperty(sb, "DefaultPosition", encodeToString);
        }
    }
}
