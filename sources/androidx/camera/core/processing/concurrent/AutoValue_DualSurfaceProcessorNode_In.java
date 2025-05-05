package androidx.camera.core.processing.concurrent;

import androidx.camera.core.processing.SurfaceEdge;
import androidx.camera.core.processing.concurrent.DualSurfaceProcessorNode;
import java.util.List;

final class AutoValue_DualSurfaceProcessorNode_In extends DualSurfaceProcessorNode.In {
    private final List<DualOutConfig> outConfigs;
    private final SurfaceEdge primarySurfaceEdge;
    private final SurfaceEdge secondarySurfaceEdge;

    AutoValue_DualSurfaceProcessorNode_In(SurfaceEdge surfaceEdge, SurfaceEdge surfaceEdge2, List<DualOutConfig> list) {
        if (surfaceEdge != null) {
            this.primarySurfaceEdge = surfaceEdge;
            if (surfaceEdge2 != null) {
                this.secondarySurfaceEdge = surfaceEdge2;
                if (list != null) {
                    this.outConfigs = list;
                    return;
                }
                throw new NullPointerException("Null outConfigs");
            }
            throw new NullPointerException("Null secondarySurfaceEdge");
        }
        throw new NullPointerException("Null primarySurfaceEdge");
    }

    public SurfaceEdge getPrimarySurfaceEdge() {
        return this.primarySurfaceEdge;
    }

    public SurfaceEdge getSecondarySurfaceEdge() {
        return this.secondarySurfaceEdge;
    }

    public List<DualOutConfig> getOutConfigs() {
        return this.outConfigs;
    }

    public String toString() {
        return "In{primarySurfaceEdge=" + this.primarySurfaceEdge + ", secondarySurfaceEdge=" + this.secondarySurfaceEdge + ", outConfigs=" + this.outConfigs + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DualSurfaceProcessorNode.In)) {
            return false;
        }
        DualSurfaceProcessorNode.In in = (DualSurfaceProcessorNode.In) obj;
        if (!this.primarySurfaceEdge.equals(in.getPrimarySurfaceEdge()) || !this.secondarySurfaceEdge.equals(in.getSecondarySurfaceEdge()) || !this.outConfigs.equals(in.getOutConfigs())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((this.primarySurfaceEdge.hashCode() ^ 1000003) * 1000003) ^ this.secondarySurfaceEdge.hashCode()) * 1000003) ^ this.outConfigs.hashCode();
    }
}
