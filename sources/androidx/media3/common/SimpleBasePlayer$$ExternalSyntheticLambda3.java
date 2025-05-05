package androidx.media3.common;

import android.view.SurfaceHolder;
import androidx.media3.common.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda3 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ SurfaceHolder f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda3(SimpleBasePlayer.State state, SurfaceHolder surfaceHolder) {
        this.f$0 = state;
        this.f$1 = surfaceHolder;
    }

    public final Object get() {
        return this.f$0.buildUpon().setSurfaceSize(SimpleBasePlayer.getSurfaceHolderSize(this.f$1)).build();
    }
}
