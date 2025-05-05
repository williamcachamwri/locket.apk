package androidx.camera.video;

import android.util.Size;
import androidx.camera.video.Quality;
import java.util.List;

final class AutoValue_Quality_ConstantQuality extends Quality.ConstantQuality {
    private final String name;
    private final List<Size> typicalSizes;
    private final int value;

    AutoValue_Quality_ConstantQuality(int i, String str, List<Size> list) {
        this.value = i;
        if (str != null) {
            this.name = str;
            if (list != null) {
                this.typicalSizes = list;
                return;
            }
            throw new NullPointerException("Null typicalSizes");
        }
        throw new NullPointerException("Null name");
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public List<Size> getTypicalSizes() {
        return this.typicalSizes;
    }

    public String toString() {
        return "ConstantQuality{value=" + this.value + ", name=" + this.name + ", typicalSizes=" + this.typicalSizes + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Quality.ConstantQuality)) {
            return false;
        }
        Quality.ConstantQuality constantQuality = (Quality.ConstantQuality) obj;
        if (this.value != constantQuality.getValue() || !this.name.equals(constantQuality.getName()) || !this.typicalSizes.equals(constantQuality.getTypicalSizes())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((this.value ^ 1000003) * 1000003) ^ this.name.hashCode()) * 1000003) ^ this.typicalSizes.hashCode();
    }
}
