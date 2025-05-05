package androidx.credentials.provider.utils;

import android.app.slice.Slice;
import android.service.credentials.Action;
import androidx.credentials.provider.Action;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/credentials/provider/Action;", "entry", "Landroid/service/credentials/Action;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginGetCredentialUtil.kt */
final class BeginGetCredentialUtil$Companion$convertToJetpackResponse$4 extends Lambda implements Function1<Action, androidx.credentials.provider.Action> {
    public static final BeginGetCredentialUtil$Companion$convertToJetpackResponse$4 INSTANCE = new BeginGetCredentialUtil$Companion$convertToJetpackResponse$4();

    BeginGetCredentialUtil$Companion$convertToJetpackResponse$4() {
        super(1);
    }

    public final androidx.credentials.provider.Action invoke(Action action) {
        Action.Companion companion = androidx.credentials.provider.Action.Companion;
        Slice slice = action.getSlice();
        Intrinsics.checkNotNullExpressionValue(slice, "entry.slice");
        return companion.fromSlice(slice);
    }
}
