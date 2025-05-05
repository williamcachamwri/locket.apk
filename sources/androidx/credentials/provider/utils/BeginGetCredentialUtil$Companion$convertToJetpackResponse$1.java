package androidx.credentials.provider.utils;

import android.app.slice.Slice;
import android.service.credentials.CredentialEntry;
import androidx.credentials.provider.CredentialEntry;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/credentials/provider/CredentialEntry;", "entry", "Landroid/service/credentials/CredentialEntry;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginGetCredentialUtil.kt */
final class BeginGetCredentialUtil$Companion$convertToJetpackResponse$1 extends Lambda implements Function1<CredentialEntry, androidx.credentials.provider.CredentialEntry> {
    public static final BeginGetCredentialUtil$Companion$convertToJetpackResponse$1 INSTANCE = new BeginGetCredentialUtil$Companion$convertToJetpackResponse$1();

    BeginGetCredentialUtil$Companion$convertToJetpackResponse$1() {
        super(1);
    }

    public final androidx.credentials.provider.CredentialEntry invoke(CredentialEntry credentialEntry) {
        CredentialEntry.Companion companion = androidx.credentials.provider.CredentialEntry.Companion;
        Slice slice = credentialEntry.getSlice();
        Intrinsics.checkNotNullExpressionValue(slice, "entry.slice");
        return companion.createFrom$credentials_release(slice);
    }
}
