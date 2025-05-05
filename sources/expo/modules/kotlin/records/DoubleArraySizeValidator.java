package expo.modules.kotlin.records;

import expo.modules.kotlin.exception.ValidationException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0002H\u0016R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lexpo/modules/kotlin/records/DoubleArraySizeValidator;", "Lexpo/modules/kotlin/records/FieldValidator;", "", "min", "", "max", "(II)V", "validate", "", "value", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FieldValidator.kt */
public final class DoubleArraySizeValidator implements FieldValidator<double[]> {
    private final int max;
    private final int min;

    public DoubleArraySizeValidator(int i, int i2) {
        this.min = i;
        this.max = i2;
    }

    public void validate(double[] dArr) {
        Intrinsics.checkNotNullParameter(dArr, "value");
        if (dArr.length < this.min || dArr.length > this.max) {
            int i = this.min;
            int i2 = this.max;
            throw new ValidationException("Number of elements in the array should be between " + i + " and " + i2 + ", got " + dArr.length);
        }
    }
}
