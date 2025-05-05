package com.adsbynimbus.render;

import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B/\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000fR\u001c\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/adsbynimbus/render/CompanionAd;", "", "width", "", "height", "gravity", "provided", "Ljava/lang/ref/WeakReference;", "Landroid/view/ViewGroup;", "(IIILjava/lang/ref/WeakReference;)V", "getGravity", "()I", "getHeight", "isEndCard", "", "()Z", "getProvided", "()Ljava/lang/ref/WeakReference;", "getWidth", "Companion", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CompanionAd.kt */
public final class CompanionAd {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final int gravity;
    private final int height;
    private final boolean isEndCard;
    private final WeakReference<ViewGroup> provided;
    private final int width;

    public CompanionAd(int i, int i2, int i3, WeakReference<ViewGroup> weakReference) {
        this.width = i;
        this.height = i2;
        this.gravity = i3;
        this.provided = weakReference;
        this.isEndCard = i3 == 17;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getGravity() {
        return this.gravity;
    }

    /* access modifiers changed from: protected */
    public final WeakReference<ViewGroup> getProvided() {
        return this.provided;
    }

    public final boolean isEndCard() {
        return this.isEndCard;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fJ\u0018\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¨\u0006\u000f"}, d2 = {"Lcom/adsbynimbus/render/CompanionAd$Companion;", "", "()V", "bottom", "Lcom/adsbynimbus/render/CompanionAd;", "width", "", "height", "end", "left", "provided", "container", "Landroid/view/ViewGroup;", "right", "top", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CompanionAd.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final CompanionAd end(int i, int i2) {
            return new CompanionAd(i, i2, 17, (WeakReference<ViewGroup>) null);
        }

        public final CompanionAd provided(ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, TtmlNode.RUBY_CONTAINER);
            return new CompanionAd(0, 0, 0, new WeakReference(viewGroup));
        }

        @Deprecated(message = "Left aligned end cards are no longer supported")
        public final CompanionAd left(int i, int i2) {
            return new CompanionAd(i, i2, 8388627, (WeakReference<ViewGroup>) null);
        }

        @Deprecated(message = "Right aligned end cards are no longer supported")
        public final CompanionAd right(int i, int i2) {
            return new CompanionAd(i, i2, 8388629, (WeakReference<ViewGroup>) null);
        }

        @Deprecated(message = "Top aligned end cards are no longer supported")
        public final CompanionAd top(int i, int i2) {
            return new CompanionAd(i, i2, 49, (WeakReference<ViewGroup>) null);
        }

        @Deprecated(message = "Bottom aligned end cards are no longer supported")
        public final CompanionAd bottom(int i, int i2) {
            return new CompanionAd(i, i2, 81, (WeakReference<ViewGroup>) null);
        }
    }
}
