package com.facebook.fresco.vito.options;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B+\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\t\u0010\r\u001a\u00020\bHÆ\u0003J1\u0010\u000e\u001a\u00020\u00002\b\b\u0003\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u000f\u001a\u00020\b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\b8\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/facebook/fresco/vito/options/BorderOptions;", "", "color", "", "width", "", "padding", "scaleDownInsideBorders", "", "(IFFZ)V", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "toString", "", "Companion", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BorderOptions.kt */
public final class BorderOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public final int color;
    public final float padding;
    public final boolean scaleDownInsideBorders;
    public final float width;

    public static /* synthetic */ BorderOptions copy$default(BorderOptions borderOptions, int i, float f, float f2, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = borderOptions.color;
        }
        if ((i2 & 2) != 0) {
            f = borderOptions.width;
        }
        if ((i2 & 4) != 0) {
            f2 = borderOptions.padding;
        }
        if ((i2 & 8) != 0) {
            z = borderOptions.scaleDownInsideBorders;
        }
        return borderOptions.copy(i, f, f2, z);
    }

    @JvmStatic
    public static final BorderOptions create(int i, float f) {
        return Companion.create(i, f);
    }

    @JvmStatic
    public static final BorderOptions create(int i, float f, float f2) {
        return Companion.create(i, f, f2);
    }

    @JvmStatic
    public static final BorderOptions create(int i, float f, float f2, boolean z) {
        return Companion.create(i, f, f2, z);
    }

    public final int component1() {
        return this.color;
    }

    public final float component2() {
        return this.width;
    }

    public final float component3() {
        return this.padding;
    }

    public final boolean component4() {
        return this.scaleDownInsideBorders;
    }

    public final BorderOptions copy(int i, float f, float f2, boolean z) {
        return new BorderOptions(i, f, f2, z);
    }

    public String toString() {
        return "BorderOptions(color=" + this.color + ", width=" + this.width + ", padding=" + this.padding + ", scaleDownInsideBorders=" + this.scaleDownInsideBorders + ')';
    }

    public BorderOptions(int i, float f, float f2, boolean z) {
        this.color = i;
        this.width = f;
        this.padding = f2;
        this.scaleDownInsideBorders = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BorderOptions(int i, float f, float f2, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, f, (i2 & 4) != 0 ? 0.0f : f2, (i2 & 8) != 0 ? false : z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.fresco.vito.options.BorderOptions");
        BorderOptions borderOptions = (BorderOptions) obj;
        if (this.color == borderOptions.color) {
            if (this.width == borderOptions.width) {
                if ((this.padding == borderOptions.padding) && this.scaleDownInsideBorders == borderOptions.scaleDownInsideBorders) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((this.color * 31) + Float.hashCode(this.width)) * 31) + Float.hashCode(this.padding)) * 31) + Boolean.hashCode(this.scaleDownInsideBorders);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¨\u0006\f"}, d2 = {"Lcom/facebook/fresco/vito/options/BorderOptions$Companion;", "", "()V", "create", "Lcom/facebook/fresco/vito/options/BorderOptions;", "color", "", "width", "", "padding", "scaleDownInsideBorders", "", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BorderOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final BorderOptions create(int i, float f) {
            return create$default(this, i, f, 0.0f, false, 12, (Object) null);
        }

        @JvmStatic
        public final BorderOptions create(int i, float f, float f2) {
            return create$default(this, i, f, f2, false, 8, (Object) null);
        }

        private Companion() {
        }

        public static /* synthetic */ BorderOptions create$default(Companion companion, int i, float f, float f2, boolean z, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                f2 = 0.0f;
            }
            if ((i2 & 8) != 0) {
                z = false;
            }
            return companion.create(i, f, f2, z);
        }

        @JvmStatic
        public final BorderOptions create(int i, float f, float f2, boolean z) {
            return new BorderOptions(i, f, f2, z);
        }
    }
}
