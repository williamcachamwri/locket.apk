package androidx.dynamicanimation.animation;

public class FloatValueHolder {
    private float mValue = 0.0f;

    public FloatValueHolder() {
    }

    public FloatValueHolder(float f) {
        setValue(f);
    }

    public void setValue(float f) {
        this.mValue = f;
    }

    public float getValue() {
        return this.mValue;
    }
}
