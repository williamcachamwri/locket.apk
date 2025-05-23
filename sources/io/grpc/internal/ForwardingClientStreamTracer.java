package io.grpc.internal;

import com.google.common.base.MoreObjects;
import io.grpc.Attributes;
import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;
import io.grpc.Status;

public abstract class ForwardingClientStreamTracer extends ClientStreamTracer {
    /* access modifiers changed from: protected */
    public abstract ClientStreamTracer delegate();

    public void streamCreated(Attributes attributes, Metadata metadata) {
        delegate().streamCreated(attributes, metadata);
    }

    public void createPendingStream() {
        delegate().createPendingStream();
    }

    public void outboundHeaders() {
        delegate().outboundHeaders();
    }

    public void inboundHeaders() {
        delegate().inboundHeaders();
    }

    public void inboundTrailers(Metadata metadata) {
        delegate().inboundTrailers(metadata);
    }

    public void streamClosed(Status status) {
        delegate().streamClosed(status);
    }

    public void outboundMessage(int i) {
        delegate().outboundMessage(i);
    }

    public void inboundMessage(int i) {
        delegate().inboundMessage(i);
    }

    public void outboundMessageSent(int i, long j, long j2) {
        delegate().outboundMessageSent(i, j, j2);
    }

    public void inboundMessageRead(int i, long j, long j2) {
        delegate().inboundMessageRead(i, j, j2);
    }

    public void outboundWireSize(long j) {
        delegate().outboundWireSize(j);
    }

    public void outboundUncompressedSize(long j) {
        delegate().outboundUncompressedSize(j);
    }

    public void inboundWireSize(long j) {
        delegate().inboundWireSize(j);
    }

    public void inboundUncompressedSize(long j) {
        delegate().inboundUncompressedSize(j);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
