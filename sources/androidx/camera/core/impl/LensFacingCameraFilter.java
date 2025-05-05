package androidx.camera.core.impl;

import androidx.camera.core.CameraFilter;
import androidx.camera.core.CameraInfo;
import androidx.core.util.Preconditions;
import java.util.ArrayList;
import java.util.List;

public class LensFacingCameraFilter implements CameraFilter {
    private final int mLensFacing;

    public LensFacingCameraFilter(int i) {
        this.mLensFacing = i;
    }

    public List<CameraInfo> filter(List<CameraInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (CameraInfo next : list) {
            Preconditions.checkArgument(next instanceof CameraInfoInternal, "The camera info doesn't contain internal implementation.");
            if (next.getLensFacing() == this.mLensFacing) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public int getLensFacing() {
        return this.mLensFacing;
    }
}
