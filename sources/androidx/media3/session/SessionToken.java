package androidx.media3.session;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.text.TextUtils;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.session.legacy.LegacyParcelableUtil;
import androidx.media3.session.legacy.MediaControllerCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import com.google.common.collect.ImmutableSet;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class SessionToken {
    private static final String FIELD_IMPL = Util.intToStringMaxRadix(1);
    private static final String FIELD_IMPL_TYPE = Util.intToStringMaxRadix(0);
    private static final int IMPL_TYPE_BASE = 0;
    private static final int IMPL_TYPE_LEGACY = 1;
    static final int TYPE_BROWSER_SERVICE_LEGACY = 101;
    public static final int TYPE_LIBRARY_SERVICE = 2;
    public static final int TYPE_SESSION = 0;
    static final int TYPE_SESSION_LEGACY = 100;
    public static final int TYPE_SESSION_SERVICE = 1;
    private static final long WAIT_TIME_MS_FOR_SESSION3_TOKEN = 500;
    private final SessionTokenImpl impl;

    interface SessionTokenImpl {
        Object getBinder();

        ComponentName getComponentName();

        Bundle getExtras();

        int getInterfaceVersion();

        int getLibraryVersion();

        String getPackageName();

        MediaSession.Token getPlatformToken();

        String getServiceName();

        int getType();

        int getUid();

        boolean isLegacySession();

        Bundle toBundle();
    }

    @Documented
    @Target({ElementType.TYPE_USE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TokenType {
    }

    static {
        MediaLibraryInfo.registerModule("media3.session");
    }

    public SessionToken(Context context, ComponentName componentName) {
        int i;
        Assertions.checkNotNull(context, "context must not be null");
        Assertions.checkNotNull(componentName, "serviceComponent must not be null");
        PackageManager packageManager = context.getPackageManager();
        int uid = getUid(packageManager, componentName.getPackageName());
        if (isInterfaceDeclared(packageManager, MediaLibraryService.SERVICE_INTERFACE, componentName)) {
            i = 2;
        } else if (isInterfaceDeclared(packageManager, MediaSessionService.SERVICE_INTERFACE, componentName)) {
            i = 1;
        } else if (isInterfaceDeclared(packageManager, "android.media.browse.MediaBrowserService", componentName)) {
            i = 101;
        } else {
            throw new IllegalArgumentException("Failed to resolve SessionToken for " + componentName + ". Manifest doesn't declare one of either MediaSessionService, MediaLibraryService, MediaBrowserService or MediaBrowserServiceCompat. Use service's full name.");
        }
        if (i != 101) {
            this.impl = new SessionTokenImplBase(componentName, uid, i);
        } else {
            this.impl = new SessionTokenImplLegacy(componentName, uid);
        }
    }

    SessionToken(int i, int i2, int i3, int i4, String str, IMediaSession iMediaSession, Bundle bundle, MediaSession.Token token) {
        this.impl = new SessionTokenImplBase(i, i2, i3, i4, str, iMediaSession, bundle, token);
    }

    private SessionToken(MediaSessionCompat.Token token, String str, int i, Bundle bundle) {
        this.impl = new SessionTokenImplLegacy(token, str, i, bundle);
    }

    private SessionToken(Bundle bundle, MediaSession.Token token) {
        String str = FIELD_IMPL_TYPE;
        Assertions.checkArgument(bundle.containsKey(str), "Impl type needs to be set.");
        int i = bundle.getInt(str);
        Bundle bundle2 = (Bundle) Assertions.checkNotNull(bundle.getBundle(FIELD_IMPL));
        if (i == 0) {
            this.impl = SessionTokenImplBase.fromBundle(bundle2, token);
        } else {
            this.impl = SessionTokenImplLegacy.fromBundle(bundle2);
        }
    }

    public int hashCode() {
        return this.impl.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SessionToken)) {
            return false;
        }
        return this.impl.equals(((SessionToken) obj).impl);
    }

    public String toString() {
        return this.impl.toString();
    }

    public int getUid() {
        return this.impl.getUid();
    }

    public String getPackageName() {
        return this.impl.getPackageName();
    }

    public String getServiceName() {
        return this.impl.getServiceName();
    }

    /* access modifiers changed from: package-private */
    public ComponentName getComponentName() {
        return this.impl.getComponentName();
    }

    public int getType() {
        return this.impl.getType();
    }

    public int getSessionVersion() {
        return this.impl.getLibraryVersion();
    }

    public int getInterfaceVersion() {
        return this.impl.getInterfaceVersion();
    }

    public Bundle getExtras() {
        return this.impl.getExtras();
    }

    /* access modifiers changed from: package-private */
    public boolean isLegacySession() {
        return this.impl.isLegacySession();
    }

    /* access modifiers changed from: package-private */
    public Object getBinder() {
        return this.impl.getBinder();
    }

    /* access modifiers changed from: package-private */
    public MediaSession.Token getPlatformToken() {
        return this.impl.getPlatformToken();
    }

    public static ListenableFuture<SessionToken> createSessionToken(Context context, Parcelable parcelable) {
        return createSessionToken(context, createCompatToken(parcelable));
    }

    public static ListenableFuture<SessionToken> createSessionToken(Context context, Parcelable parcelable, Looper looper) {
        return createSessionToken(context, createCompatToken(parcelable), looper);
    }

    private static MediaSessionCompat.Token createCompatToken(Parcelable parcelable) {
        if (parcelable instanceof MediaSession.Token) {
            return MediaSessionCompat.Token.fromToken(parcelable);
        }
        return (MediaSessionCompat.Token) LegacyParcelableUtil.convert(parcelable, MediaSessionCompat.Token.CREATOR);
    }

    private static ListenableFuture<SessionToken> createSessionToken(Context context, MediaSessionCompat.Token token) {
        HandlerThread handlerThread = new HandlerThread("SessionTokenThread");
        handlerThread.start();
        ListenableFuture<SessionToken> createSessionToken = createSessionToken(context, token, handlerThread.getLooper());
        Objects.requireNonNull(handlerThread);
        createSessionToken.addListener(new SessionToken$$ExternalSyntheticLambda1(handlerThread), MoreExecutors.directExecutor());
        return createSessionToken;
    }

    private static ListenableFuture<SessionToken> createSessionToken(Context context, MediaSessionCompat.Token token, Looper looper) {
        Assertions.checkNotNull(context, "context must not be null");
        Assertions.checkNotNull(token, "compatToken must not be null");
        SettableFuture create = SettableFuture.create();
        MediaControllerCompat mediaControllerCompat = new MediaControllerCompat(context, token);
        Handler handler = new Handler(looper);
        SessionToken$$ExternalSyntheticLambda0 sessionToken$$ExternalSyntheticLambda0 = new SessionToken$$ExternalSyntheticLambda0(context, (String) Assertions.checkNotNull(mediaControllerCompat.getPackageName()), token, mediaControllerCompat, create);
        handler.postDelayed(sessionToken$$ExternalSyntheticLambda0, 500);
        final Handler handler2 = handler;
        final SettableFuture settableFuture = create;
        final MediaSessionCompat.Token token2 = token;
        final SessionToken$$ExternalSyntheticLambda0 sessionToken$$ExternalSyntheticLambda02 = sessionToken$$ExternalSyntheticLambda0;
        mediaControllerCompat.sendCommand("androidx.media3.session.SESSION_COMMAND_REQUEST_SESSION3_TOKEN", (Bundle) null, new ResultReceiver(handler) {
            /* access modifiers changed from: protected */
            public void onReceiveResult(int i, Bundle bundle) {
                handler2.removeCallbacksAndMessages((Object) null);
                try {
                    settableFuture.set(SessionToken.fromBundle(bundle, (MediaSession.Token) token2.getToken()));
                } catch (RuntimeException unused) {
                    sessionToken$$ExternalSyntheticLambda02.run();
                }
            }
        });
        return create;
    }

    public static ImmutableSet<SessionToken> getAllServiceTokens(Context context) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList<ResolveInfo> arrayList = new ArrayList<>();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(new Intent(MediaLibraryService.SERVICE_INTERFACE), 128);
        if (queryIntentServices != null) {
            arrayList.addAll(queryIntentServices);
        }
        List<ResolveInfo> queryIntentServices2 = packageManager.queryIntentServices(new Intent(MediaSessionService.SERVICE_INTERFACE), 128);
        if (queryIntentServices2 != null) {
            arrayList.addAll(queryIntentServices2);
        }
        List<ResolveInfo> queryIntentServices3 = packageManager.queryIntentServices(new Intent("android.media.browse.MediaBrowserService"), 128);
        if (queryIntentServices3 != null) {
            arrayList.addAll(queryIntentServices3);
        }
        ImmutableSet.Builder builder = ImmutableSet.builder();
        for (ResolveInfo resolveInfo : arrayList) {
            if (!(resolveInfo == null || resolveInfo.serviceInfo == null)) {
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                builder.add((Object) new SessionToken(context, new ComponentName(serviceInfo.packageName, serviceInfo.name)));
            }
        }
        return builder.build();
    }

    private static boolean isInterfaceDeclared(PackageManager packageManager, String str, ComponentName componentName) {
        Intent intent = new Intent(str);
        intent.setPackage(componentName.getPackageName());
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 128);
        if (queryIntentServices != null) {
            for (int i = 0; i < queryIntentServices.size(); i++) {
                ResolveInfo resolveInfo = queryIntentServices.get(i);
                if (resolveInfo != null && resolveInfo.serviceInfo != null && TextUtils.equals(resolveInfo.serviceInfo.name, componentName.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int getUid(PackageManager packageManager, String str) {
        try {
            return packageManager.getApplicationInfo(str, 0).uid;
        } catch (PackageManager.NameNotFoundException unused) {
            return -1;
        }
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        if (this.impl instanceof SessionTokenImplBase) {
            bundle.putInt(FIELD_IMPL_TYPE, 0);
        } else {
            bundle.putInt(FIELD_IMPL_TYPE, 1);
        }
        bundle.putBundle(FIELD_IMPL, this.impl.toBundle());
        return bundle;
    }

    public static SessionToken fromBundle(Bundle bundle) {
        return new SessionToken(bundle, (MediaSession.Token) null);
    }

    /* access modifiers changed from: private */
    public static SessionToken fromBundle(Bundle bundle, MediaSession.Token token) {
        return new SessionToken(bundle, token);
    }
}
