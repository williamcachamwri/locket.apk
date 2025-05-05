package androidx.camera.core.impl.utils;

import android.graphics.RectF;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.internal.utils.SizeUtil;
import androidx.core.util.Preconditions;
import java.util.Comparator;

public final class AspectRatioUtil {
    private static final int ALIGN16 = 16;
    public static final Rational ASPECT_RATIO_16_9 = new Rational(16, 9);
    public static final Rational ASPECT_RATIO_3_4 = new Rational(3, 4);
    public static final Rational ASPECT_RATIO_4_3 = new Rational(4, 3);
    public static final Rational ASPECT_RATIO_9_16 = new Rational(9, 16);

    private AspectRatioUtil() {
    }

    public static boolean hasMatchingAspectRatio(Size size, Rational rational) {
        return hasMatchingAspectRatio(size, rational, SizeUtil.RESOLUTION_VGA);
    }

    public static boolean hasMatchingAspectRatio(Size size, Rational rational, Size size2) {
        if (rational == null) {
            return false;
        }
        if (rational.equals(new Rational(size.getWidth(), size.getHeight()))) {
            return true;
        }
        if (SizeUtil.getArea(size) >= SizeUtil.getArea(size2)) {
            return isPossibleMod16FromAspectRatio(size, rational);
        }
        return false;
    }

    private static boolean isPossibleMod16FromAspectRatio(Size size, Rational rational) {
        int width = size.getWidth();
        int height = size.getHeight();
        Rational rational2 = new Rational(rational.getDenominator(), rational.getNumerator());
        int i = width % 16;
        if (i == 0 && height % 16 == 0) {
            if (ratioIntersectsMod16Segment(Math.max(0, height - 16), width, rational) || ratioIntersectsMod16Segment(Math.max(0, width - 16), height, rational2)) {
                return true;
            }
            return false;
        } else if (i == 0) {
            return ratioIntersectsMod16Segment(height, width, rational);
        } else {
            if (height % 16 == 0) {
                return ratioIntersectsMod16Segment(width, height, rational2);
            }
            return false;
        }
    }

    private static boolean ratioIntersectsMod16Segment(int i, int i2, Rational rational) {
        Preconditions.checkArgument(i2 % 16 == 0);
        double numerator = ((double) (i * rational.getNumerator())) / ((double) rational.getDenominator());
        if (numerator <= ((double) Math.max(0, i2 - 16)) || numerator >= ((double) (i2 + 16))) {
            return false;
        }
        return true;
    }

    public static final class CompareAspectRatiosByMappingAreaInFullFovAspectRatioSpace implements Comparator<Rational> {
        private final Rational mFullFovRatio;
        private final Rational mTargetRatio;
        private final RectF mTransformedMappingArea;

        public CompareAspectRatiosByMappingAreaInFullFovAspectRatioSpace(Rational rational, Rational rational2) {
            this.mTargetRatio = rational;
            this.mFullFovRatio = rational2 == null ? new Rational(4, 3) : rational2;
            this.mTransformedMappingArea = getTransformedMappingArea(rational);
        }

        public int compare(Rational rational, Rational rational2) {
            if (rational.equals(rational2)) {
                return 0;
            }
            RectF transformedMappingArea = getTransformedMappingArea(rational);
            RectF transformedMappingArea2 = getTransformedMappingArea(rational2);
            boolean isMappingAreaCovered = isMappingAreaCovered(transformedMappingArea, this.mTransformedMappingArea);
            boolean isMappingAreaCovered2 = isMappingAreaCovered(transformedMappingArea2, this.mTransformedMappingArea);
            if (isMappingAreaCovered && isMappingAreaCovered2) {
                return (int) Math.signum(getMappingAreaSize(transformedMappingArea) - getMappingAreaSize(transformedMappingArea2));
            }
            if (isMappingAreaCovered) {
                return -1;
            }
            if (isMappingAreaCovered2) {
                return 1;
            }
            return -((int) Math.signum(getOverlappingAreaSize(transformedMappingArea, this.mTransformedMappingArea) - getOverlappingAreaSize(transformedMappingArea2, this.mTransformedMappingArea)));
        }

        private RectF getTransformedMappingArea(Rational rational) {
            if (rational.floatValue() == this.mFullFovRatio.floatValue()) {
                return new RectF(0.0f, 0.0f, (float) this.mFullFovRatio.getNumerator(), (float) this.mFullFovRatio.getDenominator());
            }
            if (rational.floatValue() > this.mFullFovRatio.floatValue()) {
                return new RectF(0.0f, 0.0f, (float) this.mFullFovRatio.getNumerator(), (((float) rational.getDenominator()) * ((float) this.mFullFovRatio.getNumerator())) / ((float) rational.getNumerator()));
            }
            return new RectF(0.0f, 0.0f, (((float) rational.getNumerator()) * ((float) this.mFullFovRatio.getDenominator())) / ((float) rational.getDenominator()), (float) this.mFullFovRatio.getDenominator());
        }

        private boolean isMappingAreaCovered(RectF rectF, RectF rectF2) {
            return rectF.width() >= rectF2.width() && rectF.height() >= rectF2.height();
        }

        private float getMappingAreaSize(RectF rectF) {
            return rectF.width() * rectF.height();
        }

        private float getOverlappingAreaSize(RectF rectF, RectF rectF2) {
            return (rectF.width() < rectF2.width() ? rectF.width() : rectF2.width()) * (rectF.height() < rectF2.height() ? rectF.height() : rectF2.height());
        }
    }
}
