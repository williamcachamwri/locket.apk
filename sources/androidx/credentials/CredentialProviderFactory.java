package androidx.credentials;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Landroidx/credentials/CredentialProviderFactory;", "", "()V", "Companion", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CredentialProviderFactory.kt */
public final class CredentialProviderFactory {
    private static final String CREDENTIAL_PROVIDER_KEY = "androidx.credentials.CREDENTIAL_PROVIDER_KEY";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_CRED_MAN_PRE_FRAMEWORK_API_LEVEL = 33;
    private static final String TAG = "CredProviderFactory";

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u000bJ\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bH\u0007J \u0010\u000f\u001a\u0004\u0018\u00010\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/credentials/CredentialProviderFactory$Companion;", "", "()V", "CREDENTIAL_PROVIDER_KEY", "", "MAX_CRED_MAN_PRE_FRAMEWORK_API_LEVEL", "", "TAG", "getAllowedProvidersFromManifest", "", "context", "Landroid/content/Context;", "getBestAvailableProvider", "Landroidx/credentials/CredentialProvider;", "getUAndAboveProvider", "instantiatePreUProvider", "classNames", "tryCreatePreUOemProvider", "credentials_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CredentialProviderFactory.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CredentialProvider getBestAvailableProvider(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (Build.VERSION.SDK_INT >= 34) {
                return new CredentialProviderFrameworkImpl(context);
            }
            if (Build.VERSION.SDK_INT <= 33) {
                return tryCreatePreUOemProvider(context);
            }
            return null;
        }

        public final CredentialProvider getUAndAboveProvider(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return new CredentialProviderFrameworkImpl(context);
        }

        private final CredentialProvider tryCreatePreUOemProvider(Context context) {
            List<String> allowedProvidersFromManifest = getAllowedProvidersFromManifest(context);
            if (allowedProvidersFromManifest.isEmpty()) {
                return null;
            }
            return instantiatePreUProvider(allowedProvidersFromManifest, context);
        }

        private final CredentialProvider instantiatePreUProvider(List<String> list, Context context) {
            CredentialProvider credentialProvider = null;
            for (String cls : list) {
                try {
                    Object newInstance = Class.forName(cls).getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
                    Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type androidx.credentials.CredentialProvider");
                    CredentialProvider credentialProvider2 = (CredentialProvider) newInstance;
                    if (!credentialProvider2.isAvailableOnDevice()) {
                        continue;
                    } else if (credentialProvider != null) {
                        Log.i(CredentialProviderFactory.TAG, "Only one active OEM CredentialProvider allowed");
                        return null;
                    } else {
                        credentialProvider = credentialProvider2;
                    }
                } catch (Throwable unused) {
                }
            }
            return credentialProvider;
        }

        private final List<String> getAllowedProvidersFromManifest(Context context) {
            String string;
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 132);
            List arrayList = new ArrayList();
            if (packageInfo.services != null) {
                ServiceInfo[] serviceInfoArr = packageInfo.services;
                Intrinsics.checkNotNullExpressionValue(serviceInfoArr, "packageInfo.services");
                for (ServiceInfo serviceInfo : serviceInfoArr) {
                    if (!(serviceInfo.metaData == null || (string = serviceInfo.metaData.getString(CredentialProviderFactory.CREDENTIAL_PROVIDER_KEY)) == null)) {
                        arrayList.add(string);
                    }
                }
            }
            return CollectionsKt.toList(arrayList);
        }
    }
}
