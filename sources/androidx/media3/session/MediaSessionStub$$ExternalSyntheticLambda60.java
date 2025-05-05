package androidx.media3.session;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.util.Consumer;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MediaSessionStub$$ExternalSyntheticLambda60 implements Consumer {
    public final /* synthetic */ AudioAttributes f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ MediaSessionStub$$ExternalSyntheticLambda60(AudioAttributes audioAttributes, boolean z) {
        this.f$0 = audioAttributes;
        this.f$1 = z;
    }

    public final void accept(Object obj) {
        ((PlayerWrapper) obj).setAudioAttributes(this.f$0, this.f$1);
    }
}
