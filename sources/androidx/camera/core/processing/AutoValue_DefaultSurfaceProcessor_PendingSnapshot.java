package androidx.camera.core.processing;

import androidx.camera.core.processing.DefaultSurfaceProcessor;
import androidx.concurrent.futures.CallbackToFutureAdapter;

final class AutoValue_DefaultSurfaceProcessor_PendingSnapshot extends DefaultSurfaceProcessor.PendingSnapshot {
    private final CallbackToFutureAdapter.Completer<Void> completer;
    private final int jpegQuality;
    private final int rotationDegrees;

    AutoValue_DefaultSurfaceProcessor_PendingSnapshot(int i, int i2, CallbackToFutureAdapter.Completer<Void> completer2) {
        this.jpegQuality = i;
        this.rotationDegrees = i2;
        if (completer2 != null) {
            this.completer = completer2;
            return;
        }
        throw new NullPointerException("Null completer");
    }

    /* access modifiers changed from: package-private */
    public int getJpegQuality() {
        return this.jpegQuality;
    }

    /* access modifiers changed from: package-private */
    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    /* access modifiers changed from: package-private */
    public CallbackToFutureAdapter.Completer<Void> getCompleter() {
        return this.completer;
    }

    public String toString() {
        return "PendingSnapshot{jpegQuality=" + this.jpegQuality + ", rotationDegrees=" + this.rotationDegrees + ", completer=" + this.completer + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DefaultSurfaceProcessor.PendingSnapshot)) {
            return false;
        }
        DefaultSurfaceProcessor.PendingSnapshot pendingSnapshot = (DefaultSurfaceProcessor.PendingSnapshot) obj;
        if (this.jpegQuality == pendingSnapshot.getJpegQuality() && this.rotationDegrees == pendingSnapshot.getRotationDegrees() && this.completer.equals(pendingSnapshot.getCompleter())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.jpegQuality ^ 1000003) * 1000003) ^ this.rotationDegrees) * 1000003) ^ this.completer.hashCode();
    }
}
