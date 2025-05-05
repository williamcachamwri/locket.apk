package com.facebook.imagepipeline.common;

import com.facebook.common.util.HashCodeUtil;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.annotation.Retention;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0003\u0012\u0013\u0014B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\n\u001a\u00020\u0005J\u0013\u0010\u000b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\r\u001a\u00020\u0003H\u0016J\u0006\u0010\u000e\u001a\u00020\u0005J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0006\u0010\u0011\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/facebook/imagepipeline/common/RotationOptions;", "", "rotation", "", "deferUntilRendered", "", "(IZ)V", "forcedAngle", "getForcedAngle", "()I", "canDeferUntilRendered", "equals", "other", "hashCode", "rotationEnabled", "toString", "", "useImageMetadata", "Companion", "Rotation", "RotationAngle", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RotationOptions.kt */
public final class RotationOptions {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int DISABLE_ROTATION = -2;
    public static final int NO_ROTATION = 0;
    public static final int ROTATE_180 = 180;
    public static final int ROTATE_270 = 270;
    public static final int ROTATE_90 = 90;
    /* access modifiers changed from: private */
    public static final RotationOptions ROTATION_OPTIONS_AUTO_ROTATE = new RotationOptions(-1, false);
    /* access modifiers changed from: private */
    public static final RotationOptions ROTATION_OPTIONS_DISABLE_ROTATION = new RotationOptions(-2, false);
    /* access modifiers changed from: private */
    public static final RotationOptions ROTATION_OPTIONS_ROTATE_AT_RENDER_TIME = new RotationOptions(-1, true);
    private static final int USE_EXIF_ROTATION_ANGLE = -1;
    private final boolean deferUntilRendered;
    private final int rotation;

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/facebook/imagepipeline/common/RotationOptions$Rotation;", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @Retention(AnnotationRetention.SOURCE)
    @java.lang.annotation.Retention(RetentionPolicy.SOURCE)
    /* compiled from: RotationOptions.kt */
    private @interface Rotation {
    }

    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lcom/facebook/imagepipeline/common/RotationOptions$RotationAngle;", "", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @Retention(AnnotationRetention.SOURCE)
    @java.lang.annotation.Retention(RetentionPolicy.SOURCE)
    /* compiled from: RotationOptions.kt */
    public @interface RotationAngle {
    }

    public /* synthetic */ RotationOptions(int i, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, z);
    }

    @JvmStatic
    public static final RotationOptions autoRotate() {
        return Companion.autoRotate();
    }

    @JvmStatic
    public static final RotationOptions autoRotateAtRenderTime() {
        return Companion.autoRotateAtRenderTime();
    }

    @JvmStatic
    public static final RotationOptions disableRotation() {
        return Companion.disableRotation();
    }

    @JvmStatic
    public static final RotationOptions forceRotation(int i) {
        return Companion.forceRotation(i);
    }

    private RotationOptions(int i, boolean z) {
        this.rotation = i;
        this.deferUntilRendered = z;
    }

    public final boolean useImageMetadata() {
        return this.rotation == -1;
    }

    public final boolean rotationEnabled() {
        return this.rotation != -2;
    }

    public final int getForcedAngle() {
        if (!useImageMetadata()) {
            return this.rotation;
        }
        throw new IllegalStateException("Rotation is set to use EXIF".toString());
    }

    public final boolean canDeferUntilRendered() {
        return this.deferUntilRendered;
    }

    public int hashCode() {
        return HashCodeUtil.hashCode((Object) Integer.valueOf(this.rotation), (Object) Boolean.valueOf(this.deferUntilRendered));
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RotationOptions)) {
            return false;
        }
        RotationOptions rotationOptions = (RotationOptions) obj;
        if (this.rotation == rotationOptions.rotation && this.deferUntilRendered == rotationOptions.deferUntilRendered) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format((Locale) null, "%d defer:%b", Arrays.copyOf(new Object[]{Integer.valueOf(this.rotation), Boolean.valueOf(this.deferUntilRendered)}, 2));
        Intrinsics.checkNotNullExpressionValue(format, "format(locale, format, *args)");
        return format;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\nH\u0007J\b\u0010\u000f\u001a\u00020\nH\u0007J\b\u0010\u0010\u001a\u00020\nH\u0007J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/facebook/imagepipeline/common/RotationOptions$Companion;", "", "()V", "DISABLE_ROTATION", "", "NO_ROTATION", "ROTATE_180", "ROTATE_270", "ROTATE_90", "ROTATION_OPTIONS_AUTO_ROTATE", "Lcom/facebook/imagepipeline/common/RotationOptions;", "ROTATION_OPTIONS_DISABLE_ROTATION", "ROTATION_OPTIONS_ROTATE_AT_RENDER_TIME", "USE_EXIF_ROTATION_ANGLE", "autoRotate", "autoRotateAtRenderTime", "disableRotation", "forceRotation", "angle", "imagepipeline-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RotationOptions.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final RotationOptions autoRotate() {
            return RotationOptions.ROTATION_OPTIONS_AUTO_ROTATE;
        }

        @JvmStatic
        public final RotationOptions disableRotation() {
            return RotationOptions.ROTATION_OPTIONS_DISABLE_ROTATION;
        }

        @JvmStatic
        public final RotationOptions autoRotateAtRenderTime() {
            return RotationOptions.ROTATION_OPTIONS_ROTATE_AT_RENDER_TIME;
        }

        @JvmStatic
        public final RotationOptions forceRotation(int i) {
            return new RotationOptions(i, false, (DefaultConstructorMarker) null);
        }
    }
}
