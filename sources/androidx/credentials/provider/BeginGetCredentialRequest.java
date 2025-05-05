package androidx.credentials.provider;

import android.os.Build;
import android.os.Bundle;
import androidx.credentials.provider.utils.BeginGetCredentialUtil;
import io.sentry.SentryBaseEvent;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \r2\u00020\u0001:\u0002\f\rB!\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000e"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialRequest;", "", "beginGetCredentialOptions", "", "Landroidx/credentials/provider/BeginGetCredentialOption;", "callingAppInfo", "Landroidx/credentials/provider/CallingAppInfo;", "(Ljava/util/List;Landroidx/credentials/provider/CallingAppInfo;)V", "getBeginGetCredentialOptions", "()Ljava/util/List;", "getCallingAppInfo", "()Landroidx/credentials/provider/CallingAppInfo;", "Api34Impl", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BeginGetCredentialRequest.kt */
public final class BeginGetCredentialRequest {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<BeginGetCredentialOption> beginGetCredentialOptions;
    private final CallingAppInfo callingAppInfo;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public BeginGetCredentialRequest(List<? extends BeginGetCredentialOption> list) {
        this(list, (CallingAppInfo) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "beginGetCredentialOptions");
    }

    @JvmStatic
    public static final Bundle asBundle(BeginGetCredentialRequest beginGetCredentialRequest) {
        return Companion.asBundle(beginGetCredentialRequest);
    }

    @JvmStatic
    public static final BeginGetCredentialRequest fromBundle(Bundle bundle) {
        return Companion.fromBundle(bundle);
    }

    public BeginGetCredentialRequest(List<? extends BeginGetCredentialOption> list, CallingAppInfo callingAppInfo2) {
        Intrinsics.checkNotNullParameter(list, "beginGetCredentialOptions");
        this.beginGetCredentialOptions = list;
        this.callingAppInfo = callingAppInfo2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BeginGetCredentialRequest(List list, CallingAppInfo callingAppInfo2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? null : callingAppInfo2);
    }

    public final List<BeginGetCredentialOption> getBeginGetCredentialOptions() {
        return this.beginGetCredentialOptions;
    }

    public final CallingAppInfo getCallingAppInfo() {
        return this.callingAppInfo;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0007J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialRequest$Api34Impl;", "", "()V", "REQUEST_KEY", "", "asBundle", "", "bundle", "Landroid/os/Bundle;", "request", "Landroidx/credentials/provider/BeginGetCredentialRequest;", "fromBundle", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BeginGetCredentialRequest.kt */
    private static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();
        private static final String REQUEST_KEY = "androidx.credentials.provider.BeginGetCredentialRequest";

        private Api34Impl() {
        }

        @JvmStatic
        public static final void asBundle(Bundle bundle, BeginGetCredentialRequest beginGetCredentialRequest) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            Intrinsics.checkNotNullParameter(beginGetCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
            bundle.putParcelable(REQUEST_KEY, BeginGetCredentialUtil.Companion.convertToFrameworkRequest(beginGetCredentialRequest));
        }

        @JvmStatic
        public static final BeginGetCredentialRequest fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            android.service.credentials.BeginGetCredentialRequest beginGetCredentialRequest = (android.service.credentials.BeginGetCredentialRequest) bundle.getParcelable(REQUEST_KEY, android.service.credentials.BeginGetCredentialRequest.class);
            if (beginGetCredentialRequest != null) {
                return BeginGetCredentialUtil.Companion.convertToJetpackRequest$credentials_release(beginGetCredentialRequest);
            }
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0012\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0007¨\u0006\t"}, d2 = {"Landroidx/credentials/provider/BeginGetCredentialRequest$Companion;", "", "()V", "asBundle", "Landroid/os/Bundle;", "request", "Landroidx/credentials/provider/BeginGetCredentialRequest;", "fromBundle", "bundle", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BeginGetCredentialRequest.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Bundle asBundle(BeginGetCredentialRequest beginGetCredentialRequest) {
            Intrinsics.checkNotNullParameter(beginGetCredentialRequest, SentryBaseEvent.JsonKeys.REQUEST);
            Bundle bundle = new Bundle();
            if (Build.VERSION.SDK_INT >= 34) {
                Api34Impl.asBundle(bundle, beginGetCredentialRequest);
            }
            return bundle;
        }

        @JvmStatic
        public final BeginGetCredentialRequest fromBundle(Bundle bundle) {
            Intrinsics.checkNotNullParameter(bundle, "bundle");
            if (Build.VERSION.SDK_INT >= 34) {
                return Api34Impl.fromBundle(bundle);
            }
            return null;
        }
    }
}
