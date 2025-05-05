package androidx.media3.extractor.text;

import androidx.media3.common.util.Consumer;
import com.google.common.collect.ImmutableList;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SubtitleParser$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ ImmutableList.Builder f$0;

    public /* synthetic */ SubtitleParser$$ExternalSyntheticLambda0(ImmutableList.Builder builder) {
        this.f$0 = builder;
    }

    public final void accept(Object obj) {
        ImmutableList.Builder unused = this.f$0.add((Object) (CuesWithTiming) obj);
    }
}
