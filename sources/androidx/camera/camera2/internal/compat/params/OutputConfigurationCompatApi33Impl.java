package androidx.camera.camera2.internal.compat.params;

import android.hardware.camera2.params.OutputConfiguration;
import android.view.Surface;
import androidx.core.util.Preconditions;
import java.util.List;

public class OutputConfigurationCompatApi33Impl extends OutputConfigurationCompatApi28Impl {
    public /* bridge */ /* synthetic */ void addSurface(Surface surface) {
        super.addSurface(surface);
    }

    public /* bridge */ /* synthetic */ void enableSurfaceSharing() {
        super.enableSurfaceSharing();
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ int getMaxSharedSurfaceCount() {
        return super.getMaxSharedSurfaceCount();
    }

    public /* bridge */ /* synthetic */ String getPhysicalCameraId() {
        return super.getPhysicalCameraId();
    }

    public /* bridge */ /* synthetic */ Surface getSurface() {
        return super.getSurface();
    }

    public /* bridge */ /* synthetic */ int getSurfaceGroupId() {
        return super.getSurfaceGroupId();
    }

    public /* bridge */ /* synthetic */ List getSurfaces() {
        return super.getSurfaces();
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ void removeSurface(Surface surface) {
        super.removeSurface(surface);
    }

    public /* bridge */ /* synthetic */ void setPhysicalCameraId(String str) {
        super.setPhysicalCameraId(str);
    }

    OutputConfigurationCompatApi33Impl(Surface surface) {
        super((Object) new OutputConfiguration(surface));
    }

    OutputConfigurationCompatApi33Impl(int i, Surface surface) {
        this((Object) new OutputConfiguration(i, surface));
    }

    OutputConfigurationCompatApi33Impl(Object obj) {
        super(obj);
    }

    static OutputConfigurationCompatApi33Impl wrap(OutputConfiguration outputConfiguration) {
        return new OutputConfigurationCompatApi33Impl((Object) outputConfiguration);
    }

    public int getMirrorMode() {
        return ((OutputConfiguration) getOutputConfiguration()).getMirrorMode();
    }

    public void setMirrorMode(int i) {
        ((OutputConfiguration) getOutputConfiguration()).setMirrorMode(i);
    }

    public long getDynamicRangeProfile() {
        return ((OutputConfiguration) getOutputConfiguration()).getDynamicRangeProfile();
    }

    public void setDynamicRangeProfile(long j) {
        ((OutputConfiguration) getOutputConfiguration()).setDynamicRangeProfile(j);
    }

    public Object getOutputConfiguration() {
        Preconditions.checkArgument(this.mObject instanceof OutputConfiguration);
        return this.mObject;
    }

    public void setStreamUseCase(long j) {
        if (j != -1) {
            ((OutputConfiguration) getOutputConfiguration()).setStreamUseCase(j);
        }
    }

    public long getStreamUseCase() {
        return ((OutputConfiguration) getOutputConfiguration()).getStreamUseCase();
    }
}
