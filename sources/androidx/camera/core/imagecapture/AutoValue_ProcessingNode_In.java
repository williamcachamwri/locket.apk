package androidx.camera.core.imagecapture;

import androidx.camera.core.imagecapture.ProcessingNode;
import androidx.camera.core.processing.Edge;
import java.util.List;

final class AutoValue_ProcessingNode_In extends ProcessingNode.In {
    private final Edge<ProcessingNode.InputPacket> edge;
    private final int inputFormat;
    private final List<Integer> outputFormats;
    private final Edge<ProcessingNode.InputPacket> postviewEdge;

    AutoValue_ProcessingNode_In(Edge<ProcessingNode.InputPacket> edge2, Edge<ProcessingNode.InputPacket> edge3, int i, List<Integer> list) {
        if (edge2 != null) {
            this.edge = edge2;
            if (edge3 != null) {
                this.postviewEdge = edge3;
                this.inputFormat = i;
                if (list != null) {
                    this.outputFormats = list;
                    return;
                }
                throw new NullPointerException("Null outputFormats");
            }
            throw new NullPointerException("Null postviewEdge");
        }
        throw new NullPointerException("Null edge");
    }

    /* access modifiers changed from: package-private */
    public Edge<ProcessingNode.InputPacket> getEdge() {
        return this.edge;
    }

    /* access modifiers changed from: package-private */
    public Edge<ProcessingNode.InputPacket> getPostviewEdge() {
        return this.postviewEdge;
    }

    /* access modifiers changed from: package-private */
    public int getInputFormat() {
        return this.inputFormat;
    }

    /* access modifiers changed from: package-private */
    public List<Integer> getOutputFormats() {
        return this.outputFormats;
    }

    public String toString() {
        return "In{edge=" + this.edge + ", postviewEdge=" + this.postviewEdge + ", inputFormat=" + this.inputFormat + ", outputFormats=" + this.outputFormats + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProcessingNode.In)) {
            return false;
        }
        ProcessingNode.In in = (ProcessingNode.In) obj;
        if (!this.edge.equals(in.getEdge()) || !this.postviewEdge.equals(in.getPostviewEdge()) || this.inputFormat != in.getInputFormat() || !this.outputFormats.equals(in.getOutputFormats())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((this.edge.hashCode() ^ 1000003) * 1000003) ^ this.postviewEdge.hashCode()) * 1000003) ^ this.inputFormat) * 1000003) ^ this.outputFormats.hashCode();
    }
}
