package coil;

import coil.ImageLoader;
import coil.memory.MemoryCache;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcoil/memory/MemoryCache;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageLoader.kt */
final class ImageLoader$Builder$build$1 extends Lambda implements Function0<MemoryCache> {
    final /* synthetic */ ImageLoader.Builder this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageLoader$Builder$build$1(ImageLoader.Builder builder) {
        super(0);
        this.this$0 = builder;
    }

    public final MemoryCache invoke() {
        return new MemoryCache.Builder(this.this$0.applicationContext).build();
    }
}
