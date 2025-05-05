package com.brentvatne.exoplayer;

import android.content.Context;
import android.content.ContextWrapper;
import androidx.activity.ComponentActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"findActivity", "Landroidx/activity/ComponentActivity;", "Landroid/content/Context;", "react-native-video_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: PictureInPictureUtil.kt */
public final class PictureInPictureUtilKt {
    public static final ComponentActivity findActivity(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        while (context instanceof ContextWrapper) {
            if (context instanceof ComponentActivity) {
                return (ComponentActivity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
            Intrinsics.checkNotNullExpressionValue(context, "getBaseContext(...)");
        }
        throw new IllegalStateException("Picture in picture should be called in the context of an Activity");
    }
}
