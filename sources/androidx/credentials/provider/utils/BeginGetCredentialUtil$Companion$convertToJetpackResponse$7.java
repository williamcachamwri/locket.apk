package androidx.credentials.provider.utils;

import android.app.slice.Slice;
import android.service.credentials.Action;
import androidx.credentials.provider.AuthenticationAction;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/credentials/provider/AuthenticationAction;", "entry", "Landroid/service/credentials/Action;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginGetCredentialUtil.kt */
final class BeginGetCredentialUtil$Companion$convertToJetpackResponse$7 extends Lambda implements Function1<Action, AuthenticationAction> {
    public static final BeginGetCredentialUtil$Companion$convertToJetpackResponse$7 INSTANCE = new BeginGetCredentialUtil$Companion$convertToJetpackResponse$7();

    BeginGetCredentialUtil$Companion$convertToJetpackResponse$7() {
        super(1);
    }

    public final AuthenticationAction invoke(Action action) {
        AuthenticationAction.Companion companion = AuthenticationAction.Companion;
        Slice slice = action.getSlice();
        Intrinsics.checkNotNullExpressionValue(slice, "entry.slice");
        return companion.fromSlice(slice);
    }
}
