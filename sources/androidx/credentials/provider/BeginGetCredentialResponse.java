package androidx.credentials.provider;

import android.os.Build;
import android.os.Bundle;
import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import io.sentry.protocol.Response;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 \u00142\u00020\u0001:\u0003\u0012\u0013\u0014BA\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0015"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialResponse;", "", "credentialEntries", "", "Landroidx/credentials/provider/CredentialEntry;", "actions", "Landroidx/credentials/provider/Action;", "authenticationActions", "Landroidx/credentials/provider/AuthenticationAction;", "remoteEntry", "Landroidx/credentials/provider/RemoteEntry;", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Landroidx/credentials/provider/RemoteEntry;)V", "getActions", "()Ljava/util/List;", "getAuthenticationActions", "getCredentialEntries", "getRemoteEntry", "()Landroidx/credentials/provider/RemoteEntry;", "Api34Impl", "Builder", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginGetCredentialResponse.kt */
public final class BeginGetCredentialResponse {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<Action> actions;
    private final List<AuthenticationAction> authenticationActions;
    private final List<CredentialEntry> credentialEntries;
    private final RemoteEntry remoteEntry;

    public BeginGetCredentialResponse() {
        this((List) null, (List) null, (List) null, (RemoteEntry) null, 15, (DefaultConstructorMarker) null);
    }

    @JvmStatic
    public static final Bundle asBundle(BeginGetCredentialResponse beginGetCredentialResponse) {
        return Companion.asBundle(beginGetCredentialResponse);
    }

    @JvmStatic
    public static final BeginGetCredentialResponse fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    public BeginGetCredentialResponse(List<? extends CredentialEntry> list, List<Action> list2, List<AuthenticationAction> list3, RemoteEntry remoteEntry2) {
        Intrinsics.checkNotNullParameter(list, "credentialEntries");
        Intrinsics.checkNotNullParameter(list2, "actions");
        Intrinsics.checkNotNullParameter(list3, "authenticationActions");
        this.credentialEntries = list;
        this.actions = list2;
        this.authenticationActions = list3;
        this.remoteEntry = remoteEntry2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BeginGetCredentialResponse(List list, List list2, List list3, RemoteEntry remoteEntry2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2, (i & 4) != 0 ? CollectionsKt.emptyList() : list3, (i & 8) != 0 ? null : remoteEntry2);
    }

    public final List<CredentialEntry> getCredentialEntries() {
        return this.credentialEntries;
    }

    public final List<Action> getActions() {
        return this.actions;
    }

    public final List<AuthenticationAction> getAuthenticationActions() {
        return this.authenticationActions;
    }

    public final RemoteEntry getRemoteEntry() {
        return this.remoteEntry;
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\u0005J\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0007J\u000e\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\tJ\u0006\u0010\u0012\u001a\u00020\u0013J\u0014\u0010\u0014\u001a\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0015J\u0014\u0010\u0016\u001a\u00020\u00002\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015J\u0014\u0010\u0018\u001a\u00020\u00002\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u0015J\u0010\u0010\u001a\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialResponse$Builder;", "", "()V", "actions", "", "Landroidx/credentials/provider/Action;", "authenticationActions", "Landroidx/credentials/provider/AuthenticationAction;", "credentialEntries", "Landroidx/credentials/provider/CredentialEntry;", "remoteEntry", "Landroidx/credentials/provider/RemoteEntry;", "addAction", "action", "addAuthenticationAction", "authenticationAction", "addCredentialEntry", "entry", "build", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "setActions", "", "setAuthenticationActions", "authenticationEntries", "setCredentialEntries", "entries", "setRemoteEntry", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BeginGetCredentialResponse.kt */
    public static final class Builder {
        private List<Action> actions = new ArrayList();
        private List<AuthenticationAction> authenticationActions = new ArrayList();
        private List<CredentialEntry> credentialEntries = new ArrayList();
        private RemoteEntry remoteEntry;

        public final Builder setRemoteEntry(RemoteEntry remoteEntry2) {
            this.remoteEntry = remoteEntry2;
            return this;
        }

        public final Builder addCredentialEntry(CredentialEntry credentialEntry) {
            Intrinsics.checkNotNullParameter(credentialEntry, "entry");
            this.credentialEntries.add(credentialEntry);
            return this;
        }

        public final Builder setCredentialEntries(List<? extends CredentialEntry> list) {
            Intrinsics.checkNotNullParameter(list, RemoteConfigConstants.ResponseFieldKey.ENTRIES);
            this.credentialEntries = CollectionsKt.toMutableList(list);
            return this;
        }

        public final Builder addAction(Action action) {
            Intrinsics.checkNotNullParameter(action, "action");
            this.actions.add(action);
            return this;
        }

        public final Builder setActions(List<Action> list) {
            Intrinsics.checkNotNullParameter(list, "actions");
            this.actions = CollectionsKt.toMutableList(list);
            return this;
        }

        public final Builder addAuthenticationAction(AuthenticationAction authenticationAction) {
            Intrinsics.checkNotNullParameter(authenticationAction, "authenticationAction");
            this.authenticationActions.add(authenticationAction);
            return this;
        }

        public final Builder setAuthenticationActions(List<AuthenticationAction> list) {
            Intrinsics.checkNotNullParameter(list, "authenticationEntries");
            this.authenticationActions = CollectionsKt.toMutableList(list);
            return this;
        }

        public final BeginGetCredentialResponse build() {
            return new BeginGetCredentialResponse(CollectionsKt.toList(this.credentialEntries), CollectionsKt.toList(this.actions), CollectionsKt.toList(this.authenticationActions), this.remoteEntry);
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialResponse$Api34Impl;", "", "()V", "REQUEST_KEY", "", "asBundle", "", "bundle", "Landroid/os/Bundle;", "response", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "fromBundle", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BeginGetCredentialResponse.kt */
    private static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();
        private static final String REQUEST_KEY = "androidx.credentials.provider.BeginGetCredentialResponse";

        private Api34Impl() {
        }

        @JvmStatic
        public static final void asBundle(Bundle bundle, BeginGetCredentialResponse beginGetCredentialResponse) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(beginGetCredentialResponse, Response.TYPE);
            bundle.putParcelable(REQUEST_KEY, BeginGetCredentialUtil.Companion.convertToFrameworkResponse(beginGetCredentialResponse));
        }

        @JvmStatic
        public static final BeginGetCredentialResponse fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            android.service.credentials.BeginGetCredentialResponse beginGetCredentialResponse = (android.service.credentials.BeginGetCredentialResponse) bundle.getParcelable(REQUEST_KEY, android.service.credentials.BeginGetCredentialResponse.class);
            if (beginGetCredentialResponse != null) {
                return BeginGetCredentialUtil.Companion.convertToJetpackResponse(beginGetCredentialResponse);
            }
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007¨\u0006\t"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialResponse$Companion;", "", "()V", "asBundle", "Landroid/os/Bundle;", "response", "Landroidx/credentials/provider/BeginGetCredentialResponse;", "fromBundle", "bundle", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BeginGetCredentialResponse.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Bundle asBundle(BeginGetCredentialResponse beginGetCredentialResponse) {
            Intrinsics.checkNotNullParameter(beginGetCredentialResponse, Response.TYPE);
            Bundle bundle = new Bundle();
            if (Build.VERSION.SDK_INT >= 34) {
                Api34Impl.asBundle(bundle, beginGetCredentialResponse);
            }
            return bundle;
        }

        @JvmStatic
        public final BeginGetCredentialResponse fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            if (Build.VERSION.SDK_INT >= 34) {
                return Api34Impl.fromBundle(bundle);
            }
            return null;
        }
    }
}
