package androidx.media3.session;

import android.content.Context;
import androidx.media3.session.legacy.MediaControllerCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import com.google.common.util.concurrent.SettableFuture;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SessionToken$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Context f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ MediaSessionCompat.Token f$2;
    public final /* synthetic */ MediaControllerCompat f$3;
    public final /* synthetic */ SettableFuture f$4;

    public /* synthetic */ SessionToken$$ExternalSyntheticLambda0(Context context, String str, MediaSessionCompat.Token token, MediaControllerCompat mediaControllerCompat, SettableFuture settableFuture) {
        this.f$0 = context;
        this.f$1 = str;
        this.f$2 = token;
        this.f$3 = mediaControllerCompat;
        this.f$4 = settableFuture;
    }

    public final void run() {
        this.f$4.set(new SessionToken(this.f$2, this.f$1, SessionToken.getUid(this.f$0.getPackageManager(), this.f$1), this.f$3.getSessionInfo()));
    }
}
