package androidx.media3.common;

import androidx.media3.common.SimpleBasePlayer;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SimpleBasePlayer$$ExternalSyntheticLambda4 implements Supplier {
    public final /* synthetic */ SimpleBasePlayer.State f$0;
    public final /* synthetic */ MediaMetadata f$1;

    public /* synthetic */ SimpleBasePlayer$$ExternalSyntheticLambda4(SimpleBasePlayer.State state, MediaMetadata mediaMetadata) {
        this.f$0 = state;
        this.f$1 = mediaMetadata;
    }

    public final Object get() {
        return this.f$0.buildUpon().setPlaylistMetadata(this.f$1).build();
    }
}
