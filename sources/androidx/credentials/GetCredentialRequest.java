package androidx.credentials;

import android.content.ComponentName;
import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import io.sentry.SentryBaseEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016BA\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u000b\u001a\u00020\b8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Landroidx/credentials/GetCredentialRequest;", "", "credentialOptions", "", "Landroidx/credentials/CredentialOption;", "origin", "", "preferIdentityDocUi", "", "preferUiBrandingComponentName", "Landroid/content/ComponentName;", "preferImmediatelyAvailableCredentials", "(Ljava/util/List;Ljava/lang/String;ZLandroid/content/ComponentName;Z)V", "getCredentialOptions", "()Ljava/util/List;", "getOrigin", "()Ljava/lang/String;", "getPreferIdentityDocUi", "()Z", "getPreferUiBrandingComponentName", "()Landroid/content/ComponentName;", "Builder", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: GetCredentialRequest.kt */
public final class GetCredentialRequest {
    private static final String BUNDLE_KEY_PREFER_IDENTITY_DOC_UI = "androidx.credentials.BUNDLE_KEY_PREFER_IDENTITY_DOC_UI";
    public static final String BUNDLE_KEY_PREFER_IMMEDIATELY_AVAILABLE_CREDENTIALS = "androidx.credentials.BUNDLE_KEY_PREFER_IMMEDIATELY_AVAILABLE_CREDENTIALS";
    private static final String BUNDLE_KEY_PREFER_UI_BRANDING_COMPONENT_NAME = "androidx.credentials.BUNDLE_KEY_PREFER_UI_BRANDING_COMPONENT_NAME";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<CredentialOption> credentialOptions;
    private final String origin;
    private final boolean preferIdentityDocUi;
    private final boolean preferImmediatelyAvailableCredentials;
    private final ComponentName preferUiBrandingComponentName;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GetCredentialRequest(List<? extends CredentialOption> list) {
        this(list, (String) null, false, (ComponentName) null, false, 30, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "credentialOptions");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GetCredentialRequest(List<? extends CredentialOption> list, String str) {
        this(list, str, false, (ComponentName) null, false, 28, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "credentialOptions");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GetCredentialRequest(List<? extends CredentialOption> list, String str, boolean z) {
        this(list, str, z, (ComponentName) null, false, 24, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "credentialOptions");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GetCredentialRequest(List<? extends CredentialOption> list, String str, boolean z, ComponentName componentName) {
        this(list, str, z, componentName, false, 16, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "credentialOptions");
    }

    @JvmStatic
    public static final GetCredentialRequest createFrom(List<? extends CredentialOption> list, String str, Bundle bundle) {
        return Companion.createFrom(list, str, bundle);
    }

    @JvmStatic
    public static final Bundle toRequestDataBundle(GetCredentialRequest getCredentialRequest) {
        return Companion.toRequestDataBundle(getCredentialRequest);
    }

    public GetCredentialRequest(List<? extends CredentialOption> list, String str, boolean z, ComponentName componentName, boolean z2) {
        Intrinsics.checkNotNullParameter(list, "credentialOptions");
        this.credentialOptions = list;
        this.origin = str;
        this.preferIdentityDocUi = z;
        this.preferUiBrandingComponentName = componentName;
        this.preferImmediatelyAvailableCredentials = z2;
        if (!(!list.isEmpty())) {
            throw new IllegalArgumentException("credentialOptions should not be empty".toString());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GetCredentialRequest(List list, String str, boolean z, ComponentName componentName, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? null : str, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : componentName, (i & 16) != 0 ? false : z2);
    }

    public final List<CredentialOption> getCredentialOptions() {
        return this.credentialOptions;
    }

    public final String getOrigin() {
        return this.origin;
    }

    public final boolean getPreferIdentityDocUi() {
        return this.preferIdentityDocUi;
    }

    public final ComponentName getPreferUiBrandingComponentName() {
        return this.preferUiBrandingComponentName;
    }

    public final boolean preferImmediatelyAvailableCredentials() {
        return this.preferImmediatelyAvailableCredentials;
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0005J\u0006\u0010\u000f\u001a\u00020\u0010J\u0014\u0010\u0011\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tJ\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\tJ\u0010\u0010\u0016\u001a\u00020\u00002\b\u0010\u0017\u001a\u0004\u0018\u00010\fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/credentials/GetCredentialRequest$Builder;", "", "()V", "credentialOptions", "", "Landroidx/credentials/CredentialOption;", "origin", "", "preferIdentityDocUi", "", "preferImmediatelyAvailableCredentials", "preferUiBrandingComponentName", "Landroid/content/ComponentName;", "addCredentialOption", "credentialOption", "build", "Landroidx/credentials/GetCredentialRequest;", "setCredentialOptions", "", "setOrigin", "setPreferIdentityDocUi", "setPreferImmediatelyAvailableCredentials", "setPreferUiBrandingComponentName", "component", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: GetCredentialRequest.kt */
    public static final class Builder {
        private List<CredentialOption> credentialOptions = new ArrayList();
        private String origin;
        private boolean preferIdentityDocUi;
        private boolean preferImmediatelyAvailableCredentials;
        private ComponentName preferUiBrandingComponentName;

        public final Builder addCredentialOption(CredentialOption credentialOption) {
            Intrinsics.checkNotNullParameter(credentialOption, "credentialOption");
            this.credentialOptions.add(credentialOption);
            return this;
        }

        public final Builder setCredentialOptions(List<? extends CredentialOption> list) {
            Intrinsics.checkNotNullParameter(list, "credentialOptions");
            this.credentialOptions = CollectionsKt.toMutableList(list);
            return this;
        }

        public final Builder setOrigin(String str) {
            Intrinsics.checkNotNullParameter(str, "origin");
            this.origin = str;
            return this;
        }

        public final Builder setPreferImmediatelyAvailableCredentials(boolean z) {
            this.preferImmediatelyAvailableCredentials = z;
            return this;
        }

        public final Builder setPreferUiBrandingComponentName(ComponentName componentName) {
            this.preferUiBrandingComponentName = componentName;
            return this;
        }

        public final Builder setPreferIdentityDocUi(boolean z) {
            this.preferIdentityDocUi = z;
            return this;
        }

        public final GetCredentialRequest build() {
            return new GetCredentialRequest(CollectionsKt.toList(this.credentialOptions), this.origin, this.preferIdentityDocUi, this.preferUiBrandingComponentName, this.preferImmediatelyAvailableCredentials);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/credentials/GetCredentialRequest$Companion;", "", "()V", "BUNDLE_KEY_PREFER_IDENTITY_DOC_UI", "", "BUNDLE_KEY_PREFER_IMMEDIATELY_AVAILABLE_CREDENTIALS", "BUNDLE_KEY_PREFER_UI_BRANDING_COMPONENT_NAME", "createFrom", "Landroidx/credentials/GetCredentialRequest;", "credentialOptions", "", "Landroidx/credentials/CredentialOption;", "origin", "data", "Landroid/os/Bundle;", "toRequestDataBundle", "request", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: GetCredentialRequest.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Bundle toRequestDataBundle(GetCredentialRequest getCredentialRequest) {
            Intrinsics.checkNotNullParameter(getCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
            Bundle bundle = new Bundle();
            bundle.putBoolean(GetCredentialRequest.BUNDLE_KEY_PREFER_IDENTITY_DOC_UI, getCredentialRequest.getPreferIdentityDocUi());
            bundle.putBoolean("androidx.credentials.BUNDLE_KEY_PREFER_IMMEDIATELY_AVAILABLE_CREDENTIALS", getCredentialRequest.preferImmediatelyAvailableCredentials());
            bundle.putParcelable(GetCredentialRequest.BUNDLE_KEY_PREFER_UI_BRANDING_COMPONENT_NAME, getCredentialRequest.getPreferUiBrandingComponentName());
            return bundle;
        }

        @JvmStatic
        public final GetCredentialRequest createFrom(List<? extends CredentialOption> list, String str, Bundle bundle) {
            Intrinsics.checkNotNullParameter(list, "credentialOptions");
            Intrinsics.checkNotNullParameter(bundle, "data");
            try {
                boolean z = bundle.getBoolean(GetCredentialRequest.BUNDLE_KEY_PREFER_IDENTITY_DOC_UI);
                Builder preferImmediatelyAvailableCredentials = new Builder().setCredentialOptions(list).setPreferIdentityDocUi(z).setPreferUiBrandingComponentName((ComponentName) bundle.getParcelable(GetCredentialRequest.BUNDLE_KEY_PREFER_UI_BRANDING_COMPONENT_NAME)).setPreferImmediatelyAvailableCredentials(bundle.getBoolean("androidx.credentials.BUNDLE_KEY_PREFER_IMMEDIATELY_AVAILABLE_CREDENTIALS"));
                if (str != null) {
                    preferImmediatelyAvailableCredentials.setOrigin(str);
                }
                return preferImmediatelyAvailableCredentials.build();
            } catch (Exception unused) {
                throw new FrameworkClassParsingException();
            }
        }
    }
}
