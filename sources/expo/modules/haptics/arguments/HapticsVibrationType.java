package expo.modules.haptics.arguments;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0015\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0018"}, d2 = {"Lexpo/modules/haptics/arguments/HapticsVibrationType;", "", "timings", "", "amplitudes", "", "oldSDKPattern", "([J[I[J)V", "getAmplitudes", "()[I", "getOldSDKPattern", "()[J", "getTimings", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "expo-haptics_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HapticsVibrationType.kt */
public final class HapticsVibrationType {
    private final int[] amplitudes;
    private final long[] oldSDKPattern;
    private final long[] timings;

    public static /* synthetic */ HapticsVibrationType copy$default(HapticsVibrationType hapticsVibrationType, long[] jArr, int[] iArr, long[] jArr2, int i, Object obj) {
        if ((i & 1) != 0) {
            jArr = hapticsVibrationType.timings;
        }
        if ((i & 2) != 0) {
            iArr = hapticsVibrationType.amplitudes;
        }
        if ((i & 4) != 0) {
            jArr2 = hapticsVibrationType.oldSDKPattern;
        }
        return hapticsVibrationType.copy(jArr, iArr, jArr2);
    }

    public final long[] component1() {
        return this.timings;
    }

    public final int[] component2() {
        return this.amplitudes;
    }

    public final long[] component3() {
        return this.oldSDKPattern;
    }

    public final HapticsVibrationType copy(long[] jArr, int[] iArr, long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "timings");
        Intrinsics.checkNotNullParameter(iArr, "amplitudes");
        Intrinsics.checkNotNullParameter(jArr2, "oldSDKPattern");
        return new HapticsVibrationType(jArr, iArr, jArr2);
    }

    public String toString() {
        String arrays = Arrays.toString(this.timings);
        String arrays2 = Arrays.toString(this.amplitudes);
        return "HapticsVibrationType(timings=" + arrays + ", amplitudes=" + arrays2 + ", oldSDKPattern=" + Arrays.toString(this.oldSDKPattern) + ")";
    }

    public HapticsVibrationType(long[] jArr, int[] iArr, long[] jArr2) {
        Intrinsics.checkNotNullParameter(jArr, "timings");
        Intrinsics.checkNotNullParameter(iArr, "amplitudes");
        Intrinsics.checkNotNullParameter(jArr2, "oldSDKPattern");
        this.timings = jArr;
        this.amplitudes = iArr;
        this.oldSDKPattern = jArr2;
    }

    public final long[] getTimings() {
        return this.timings;
    }

    public final int[] getAmplitudes() {
        return this.amplitudes;
    }

    public final long[] getOldSDKPattern() {
        return this.oldSDKPattern;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type expo.modules.haptics.arguments.HapticsVibrationType");
        HapticsVibrationType hapticsVibrationType = (HapticsVibrationType) obj;
        return Arrays.equals(this.timings, hapticsVibrationType.timings) && Arrays.equals(this.amplitudes, hapticsVibrationType.amplitudes) && Arrays.equals(this.oldSDKPattern, hapticsVibrationType.oldSDKPattern);
    }

    public int hashCode() {
        return (((Arrays.hashCode(this.timings) * 31) + Arrays.hashCode(this.amplitudes)) * 31) + Arrays.hashCode(this.oldSDKPattern);
    }
}
