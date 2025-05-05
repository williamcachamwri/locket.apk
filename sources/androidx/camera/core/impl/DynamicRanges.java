package androidx.camera.core.impl;

import androidx.camera.core.DynamicRange;
import androidx.core.util.Preconditions;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u001e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH\u0007J\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J*\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH\u0007¨\u0006\u0010"}, d2 = {"Landroidx/camera/core/impl/DynamicRanges;", "", "()V", "canMatchBitDepth", "", "dynamicRangeToTest", "Landroidx/camera/core/DynamicRange;", "fullySpecifiedDynamicRange", "canMatchEncoding", "canResolve", "fullySpecifiedDynamicRanges", "", "canResolveUnderSpecifiedTo", "underSpecifiedDynamicRange", "findAllPossibleMatches", "dynamicRangesToTest", "camera-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DynamicRanges.kt */
public final class DynamicRanges {
    public static final DynamicRanges INSTANCE = new DynamicRanges();

    private DynamicRanges() {
    }

    @JvmStatic
    public static final boolean canResolve(DynamicRange dynamicRange, Set<DynamicRange> set) {
        Object obj;
        Intrinsics.checkNotNullParameter(dynamicRange, "dynamicRangeToTest");
        Intrinsics.checkNotNullParameter(set, "fullySpecifiedDynamicRanges");
        if (dynamicRange.isFullySpecified()) {
            return set.contains(dynamicRange);
        }
        Iterator it = set.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (INSTANCE.canResolveUnderSpecifiedTo(dynamicRange, (DynamicRange) obj)) {
                break;
            }
        }
        return obj != null;
    }

    @JvmStatic
    public static final Set<DynamicRange> findAllPossibleMatches(Set<DynamicRange> set, Set<DynamicRange> set2) {
        Intrinsics.checkNotNullParameter(set, "dynamicRangesToTest");
        Intrinsics.checkNotNullParameter(set2, "fullySpecifiedDynamicRanges");
        if (!set.isEmpty()) {
            Set createSetBuilder = SetsKt.createSetBuilder();
            for (DynamicRange dynamicRange : set) {
                if (!dynamicRange.isFullySpecified()) {
                    for (DynamicRange dynamicRange2 : set2) {
                        if (INSTANCE.canResolveUnderSpecifiedTo(dynamicRange, dynamicRange2)) {
                            createSetBuilder.add(dynamicRange2);
                        }
                    }
                } else if (set2.contains(dynamicRange)) {
                    createSetBuilder.add(dynamicRange);
                }
            }
            return SetsKt.build(createSetBuilder);
        }
        throw new IllegalArgumentException("Candidate dynamic range set must contain at least 1 candidate dynamic range.");
    }

    private final boolean canResolveUnderSpecifiedTo(DynamicRange dynamicRange, DynamicRange dynamicRange2) {
        return canMatchBitDepth(dynamicRange, dynamicRange2) && canMatchEncoding(dynamicRange, dynamicRange2);
    }

    private final boolean canMatchBitDepth(DynamicRange dynamicRange, DynamicRange dynamicRange2) {
        Preconditions.checkState(dynamicRange2.isFullySpecified(), "Fully specified range is not actually fully specified.");
        if (dynamicRange.getBitDepth() == 0 || dynamicRange.getBitDepth() == dynamicRange2.getBitDepth()) {
            return true;
        }
        return false;
    }

    private final boolean canMatchEncoding(DynamicRange dynamicRange, DynamicRange dynamicRange2) {
        Preconditions.checkState(dynamicRange2.isFullySpecified(), "Fully specified range is not actually fully specified.");
        int encoding = dynamicRange.getEncoding();
        if (encoding == 0) {
            return true;
        }
        int encoding2 = dynamicRange2.getEncoding();
        if ((encoding != 2 || encoding2 == 1) && encoding != encoding2) {
            return false;
        }
        return true;
    }
}
