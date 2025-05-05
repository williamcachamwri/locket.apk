package com.facebook.fresco.vito.options;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0014\n\u0002\b\u0012\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J=\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001H\u0002J\u0006\u0010\u0018\u001a\u00020\u0003J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u000fR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u000f¨\u0006\u001e"}, d2 = {"Lcom/facebook/fresco/vito/options/RoundingOptions;", "", "isCircular", "", "cornerRadius", "", "cornerRadii", "", "isAntiAliased", "isForceRoundAtDecode", "(ZF[FZZ)V", "getCornerRadii", "()[F", "getCornerRadius", "()F", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hasRoundedCorners", "hashCode", "", "toString", "", "Companion", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoundingOptions.kt */
public final class RoundingOptions {
    /* access modifiers changed from: private */
    public static final RoundingOptions AS_CIRCLE = new RoundingOptions(true, 0.0f, (float[]) null, false, false);
    /* access modifiers changed from: private */
    public static final RoundingOptions AS_CIRCLE_ANTI_ALIASING = new RoundingOptions(true, 0.0f, (float[]) null, true, false);
    public static final float CORNER_RADIUS_UNSET = 0.0f;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final float[] cornerRadii;
    private final float cornerRadius;
    private final boolean isAntiAliased;
    private final boolean isCircular;
    private final boolean isForceRoundAtDecode;

    @JvmStatic
    public static final RoundingOptions asCircle() {
        return Companion.asCircle();
    }

    @JvmStatic
    public static final RoundingOptions asCircle(boolean z) {
        return Companion.asCircle(z);
    }

    @JvmStatic
    public static final RoundingOptions asCircle(boolean z, boolean z2) {
        return Companion.asCircle(z, z2);
    }

    public static /* synthetic */ RoundingOptions copy$default(RoundingOptions roundingOptions, boolean z, float f, float[] fArr, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = roundingOptions.isCircular;
        }
        if ((i & 2) != 0) {
            f = roundingOptions.cornerRadius;
        }
        float f2 = f;
        if ((i & 4) != 0) {
            fArr = roundingOptions.cornerRadii;
        }
        float[] fArr2 = fArr;
        if ((i & 8) != 0) {
            z2 = roundingOptions.isAntiAliased;
        }
        boolean z4 = z2;
        if ((i & 16) != 0) {
            z3 = roundingOptions.isForceRoundAtDecode;
        }
        return roundingOptions.copy(z, f2, fArr2, z4, z3);
    }

    @JvmStatic
    public static final RoundingOptions forCornerRadii(float f, float f2, float f3, float f4) {
        return Companion.forCornerRadii(f, f2, f3, f4);
    }

    @JvmStatic
    public static final RoundingOptions forCornerRadii(float[] fArr) {
        return Companion.forCornerRadii(fArr);
    }

    @JvmStatic
    public static final RoundingOptions forCornerRadii(float[] fArr, boolean z) {
        return Companion.forCornerRadii(fArr, z);
    }

    @JvmStatic
    public static final RoundingOptions forCornerRadiusPx(float f) {
        return Companion.forCornerRadiusPx(f);
    }

    public final boolean component1() {
        return this.isCircular;
    }

    public final float component2() {
        return this.cornerRadius;
    }

    public final float[] component3() {
        return this.cornerRadii;
    }

    public final boolean component4() {
        return this.isAntiAliased;
    }

    public final boolean component5() {
        return this.isForceRoundAtDecode;
    }

    public final RoundingOptions copy(boolean z, float f, float[] fArr, boolean z2, boolean z3) {
        return new RoundingOptions(z, f, fArr, z2, z3);
    }

    public String toString() {
        return "RoundingOptions(isCircular=" + this.isCircular + ", cornerRadius=" + this.cornerRadius + ", cornerRadii=" + Arrays.toString(this.cornerRadii) + ", isAntiAliased=" + this.isAntiAliased + ", isForceRoundAtDecode=" + this.isForceRoundAtDecode + ')';
    }

    public RoundingOptions(boolean z, float f, float[] fArr, boolean z2, boolean z3) {
        this.isCircular = z;
        this.cornerRadius = f;
        this.cornerRadii = fArr;
        this.isAntiAliased = z2;
        this.isForceRoundAtDecode = z3;
    }

    public final boolean isCircular() {
        return this.isCircular;
    }

    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    public final float[] getCornerRadii() {
        return this.cornerRadii;
    }

    public final boolean isAntiAliased() {
        return this.isAntiAliased;
    }

    public final boolean isForceRoundAtDecode() {
        return this.isForceRoundAtDecode;
    }

    public final boolean hasRoundedCorners() {
        return !((this.cornerRadius > 0.0f ? 1 : (this.cornerRadius == 0.0f ? 0 : -1)) == 0) || this.cornerRadii != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.facebook.fresco.vito.options.RoundingOptions");
        RoundingOptions roundingOptions = (RoundingOptions) obj;
        if (this.isCircular == roundingOptions.isCircular) {
            if ((this.cornerRadius == roundingOptions.cornerRadius) && Arrays.equals(this.cornerRadii, roundingOptions.cornerRadii) && this.isAntiAliased == roundingOptions.isAntiAliased && this.isForceRoundAtDecode == roundingOptions.isForceRoundAtDecode) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (((((((Boolean.hashCode(this.isCircular) * 31) + Float.hashCode(this.cornerRadius)) * 31) + Arrays.hashCode(this.cornerRadii)) * 31) + Boolean.hashCode(this.isAntiAliased)) * 31) + Boolean.hashCode(this.isForceRoundAtDecode);
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\u0004H\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0007J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0007J(\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\nH\u0007J\u001a\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\f\u001a\u00020\rH\u0007J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\nH\u0007R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u000e\u0010\t\u001a\u00020\nXT¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/facebook/fresco/vito/options/RoundingOptions$Companion;", "", "()V", "AS_CIRCLE", "Lcom/facebook/fresco/vito/options/RoundingOptions;", "getAS_CIRCLE", "()Lcom/facebook/fresco/vito/options/RoundingOptions;", "AS_CIRCLE_ANTI_ALIASING", "getAS_CIRCLE_ANTI_ALIASING", "CORNER_RADIUS_UNSET", "", "asCircle", "antiAliasing", "", "forceRoundAtDecode", "forCornerRadii", "topLeft", "topRight", "bottomRight", "bottomLeft", "cornerRadii", "", "forCornerRadiusPx", "cornerRadiusPx", "options_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RoundingOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final RoundingOptions forCornerRadii(float[] fArr) {
            Intrinsics.checkNotNullParameter(fArr, "cornerRadii");
            return forCornerRadii$default(this, fArr, false, 2, (Object) null);
        }

        private Companion() {
        }

        public final RoundingOptions getAS_CIRCLE() {
            return RoundingOptions.AS_CIRCLE;
        }

        public final RoundingOptions getAS_CIRCLE_ANTI_ALIASING() {
            return RoundingOptions.AS_CIRCLE_ANTI_ALIASING;
        }

        @JvmStatic
        public final RoundingOptions asCircle() {
            return getAS_CIRCLE();
        }

        @JvmStatic
        public final RoundingOptions asCircle(boolean z) {
            return z ? getAS_CIRCLE_ANTI_ALIASING() : getAS_CIRCLE();
        }

        @JvmStatic
        public final RoundingOptions asCircle(boolean z, boolean z2) {
            return new RoundingOptions(true, 0.0f, (float[]) null, z, z2);
        }

        @JvmStatic
        public final RoundingOptions forCornerRadiusPx(float f) {
            return new RoundingOptions(false, f, (float[]) null, false, false);
        }

        @JvmStatic
        public final RoundingOptions forCornerRadii(float f, float f2, float f3, float f4) {
            return new RoundingOptions(false, 0.0f, new float[]{f, f, f2, f2, f3, f3, f4, f4}, false, false);
        }

        public static /* synthetic */ RoundingOptions forCornerRadii$default(Companion companion, float[] fArr, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            return companion.forCornerRadii(fArr, z);
        }

        @JvmStatic
        public final RoundingOptions forCornerRadii(float[] fArr, boolean z) {
            Intrinsics.checkNotNullParameter(fArr, "cornerRadii");
            return new RoundingOptions(false, 0.0f, fArr, z, false);
        }
    }
}
