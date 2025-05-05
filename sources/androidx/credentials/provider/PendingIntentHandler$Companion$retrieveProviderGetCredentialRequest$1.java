package androidx.credentials.provider;

import android.credentials.CredentialOption;
import android.os.Bundle;
import androidx.credentials.CredentialOption;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u000e\u0010\u0003\u001a\n \u0002*\u0004\u0018\u00010\u00040\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/credentials/CredentialOption;", "kotlin.jvm.PlatformType", "option", "Landroid/credentials/CredentialOption;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PendingIntentHandler.kt */
final class PendingIntentHandler$Companion$retrieveProviderGetCredentialRequest$1 extends Lambda implements Function1<CredentialOption, androidx.credentials.CredentialOption> {
    public static final PendingIntentHandler$Companion$retrieveProviderGetCredentialRequest$1 INSTANCE = new PendingIntentHandler$Companion$retrieveProviderGetCredentialRequest$1();

    PendingIntentHandler$Companion$retrieveProviderGetCredentialRequest$1() {
        super(1);
    }

    public final androidx.credentials.CredentialOption invoke(CredentialOption credentialOption) {
        CredentialOption.Companion companion = androidx.credentials.CredentialOption.Companion;
        String type = credentialOption.getType();
        Intrinsics.checkNotNullExpressionValue(type, "option.type");
        Bundle credentialRetrievalData = credentialOption.getCredentialRetrievalData();
        Intrinsics.checkNotNullExpressionValue(credentialRetrievalData, "option.credentialRetrievalData");
        Bundle candidateQueryData = credentialOption.getCandidateQueryData();
        Intrinsics.checkNotNullExpressionValue(candidateQueryData, "option.candidateQueryData");
        boolean isSystemProviderRequired = credentialOption.isSystemProviderRequired();
        Set allowedProviders = credentialOption.getAllowedProviders();
        Intrinsics.checkNotNullExpressionValue(allowedProviders, "option.allowedProviders");
        return companion.createFrom(type, credentialRetrievalData, candidateQueryData, isSystemProviderRequired, allowedProviders);
    }
}
