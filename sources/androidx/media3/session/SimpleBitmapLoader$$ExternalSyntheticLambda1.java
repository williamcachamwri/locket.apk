package androidx.media3.session;

import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBitmapLoader$$ExternalSyntheticLambda1 implements Callable {
    public final /* synthetic */ byte[] f$0;

    public /* synthetic */ SimpleBitmapLoader$$ExternalSyntheticLambda1(byte[] bArr) {
        this.f$0 = bArr;
    }

    public final Object call() {
        return SimpleBitmapLoader.decode(this.f$0);
    }
}
