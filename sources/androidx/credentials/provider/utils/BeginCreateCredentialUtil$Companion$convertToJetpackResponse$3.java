package androidx.credentials.provider.utils;

import androidx.credentials.provider.CreateEntry;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Landroidx/credentials/provider/CreateEntry;", "kotlin.jvm.PlatformType", "entry", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginCreateCredentialUtil.kt */
final class BeginCreateCredentialUtil$Companion$convertToJetpackResponse$3 extends Lambda implements Function1<CreateEntry, CreateEntry> {
    public static final BeginCreateCredentialUtil$Companion$convertToJetpackResponse$3 INSTANCE = new BeginCreateCredentialUtil$Companion$convertToJetpackResponse$3();

    BeginCreateCredentialUtil$Companion$convertToJetpackResponse$3() {
        super(1);
    }

    public final CreateEntry invoke(CreateEntry createEntry) {
        Intrinsics.checkNotNull(createEntry);
        return createEntry;
    }
}
