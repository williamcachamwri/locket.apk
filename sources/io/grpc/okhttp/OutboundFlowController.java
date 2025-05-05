package io.grpc.okhttp;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import io.grpc.okhttp.internal.framed.FrameWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import javax.annotation.Nullable;
import okio.Buffer;

class OutboundFlowController {
    /* access modifiers changed from: private */
    public final StreamState connectionState = new StreamState(0, 65535, (Stream) null);
    /* access modifiers changed from: private */
    public final FrameWriter frameWriter;
    private int initialWindowSize = 65535;
    private final Transport transport;

    public interface Stream {
        void onSentBytes(int i);
    }

    public interface Transport {
        StreamState[] getActiveStreams();
    }

    public OutboundFlowController(Transport transport2, FrameWriter frameWriter2) {
        this.transport = (Transport) Preconditions.checkNotNull(transport2, NotificationCompat.CATEGORY_TRANSPORT);
        this.frameWriter = (FrameWriter) Preconditions.checkNotNull(frameWriter2, "frameWriter");
    }

    public boolean initialOutboundWindowSize(int i) {
        if (i >= 0) {
            int i2 = i - this.initialWindowSize;
            this.initialWindowSize = i;
            for (StreamState incrementStreamWindow : this.transport.getActiveStreams()) {
                incrementStreamWindow.incrementStreamWindow(i2);
            }
            if (i2 > 0) {
                return true;
            }
            return false;
        }
        throw new IllegalArgumentException("Invalid initial window size: " + i);
    }

    public int windowUpdate(@Nullable StreamState streamState, int i) {
        if (streamState == null) {
            int incrementStreamWindow = this.connectionState.incrementStreamWindow(i);
            writeStreams();
            return incrementStreamWindow;
        }
        int incrementStreamWindow2 = streamState.incrementStreamWindow(i);
        WriteStatus writeStatus = new WriteStatus();
        streamState.writeBytes(streamState.writableWindow(), writeStatus);
        if (writeStatus.hasWritten()) {
            flush();
        }
        return incrementStreamWindow2;
    }

    public void data(boolean z, StreamState streamState, Buffer buffer, boolean z2) {
        Preconditions.checkNotNull(buffer, "source");
        int writableWindow = streamState.writableWindow();
        boolean hasPendingData = streamState.hasPendingData();
        int size = (int) buffer.size();
        if (hasPendingData || writableWindow < size) {
            if (!hasPendingData && writableWindow > 0) {
                streamState.write(buffer, writableWindow, false);
            }
            streamState.enqueueData(buffer, (int) buffer.size(), z);
        } else {
            streamState.write(buffer, size, z);
        }
        if (z2) {
            flush();
        }
    }

    public void notifyWhenNoPendingData(StreamState streamState, Runnable runnable) {
        Preconditions.checkNotNull(runnable, "noPendingDataRunnable");
        if (streamState.hasPendingData()) {
            streamState.notifyWhenNoPendingData(runnable);
        } else {
            runnable.run();
        }
    }

    public void flush() {
        try {
            this.frameWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public StreamState createState(Stream stream, int i) {
        return new StreamState(i, this.initialWindowSize, (Stream) Preconditions.checkNotNull(stream, "stream"));
    }

    public void writeStreams() {
        int i;
        StreamState[] activeStreams = this.transport.getActiveStreams();
        Collections.shuffle(Arrays.asList(activeStreams));
        int window = this.connectionState.window();
        int length = activeStreams.length;
        while (true) {
            i = 0;
            if (length <= 0 || window <= 0) {
                WriteStatus writeStatus = new WriteStatus();
                StreamState[] activeStreams2 = this.transport.getActiveStreams();
                int length2 = activeStreams2.length;
            } else {
                int ceil = (int) Math.ceil((double) (((float) window) / ((float) length)));
                for (int i2 = 0; i2 < length && window > 0; i2++) {
                    StreamState streamState = activeStreams[i2];
                    int min = Math.min(window, Math.min(streamState.unallocatedBytes(), ceil));
                    if (min > 0) {
                        streamState.allocateBytes(min);
                        window -= min;
                    }
                    if (streamState.unallocatedBytes() > 0) {
                        activeStreams[i] = streamState;
                        i++;
                    }
                }
                length = i;
            }
        }
        WriteStatus writeStatus2 = new WriteStatus();
        StreamState[] activeStreams22 = this.transport.getActiveStreams();
        int length22 = activeStreams22.length;
        while (i < length22) {
            StreamState streamState2 = activeStreams22[i];
            streamState2.writeBytes(streamState2.allocatedBytes(), writeStatus2);
            streamState2.clearAllocatedBytes();
            i++;
        }
        if (writeStatus2.hasWritten()) {
            flush();
        }
    }

    private static final class WriteStatus {
        int numWrites;

        private WriteStatus() {
        }

        /* access modifiers changed from: package-private */
        public void incrementNumWrites() {
            this.numWrites++;
        }

        /* access modifiers changed from: package-private */
        public boolean hasWritten() {
            return this.numWrites > 0;
        }
    }

    public final class StreamState {
        private int allocatedBytes;
        private Runnable noPendingDataRunnable;
        private boolean pendingBufferHasEndOfStream = false;
        private final Buffer pendingWriteBuffer = new Buffer();
        private final Stream stream;
        private final int streamId;
        private int window;

        StreamState(int i, int i2, Stream stream2) {
            this.streamId = i;
            this.window = i2;
            this.stream = stream2;
        }

        /* access modifiers changed from: package-private */
        public int window() {
            return this.window;
        }

        /* access modifiers changed from: package-private */
        public void allocateBytes(int i) {
            this.allocatedBytes += i;
        }

        /* access modifiers changed from: package-private */
        public int allocatedBytes() {
            return this.allocatedBytes;
        }

        /* access modifiers changed from: package-private */
        public int unallocatedBytes() {
            return streamableBytes() - this.allocatedBytes;
        }

        /* access modifiers changed from: package-private */
        public void clearAllocatedBytes() {
            this.allocatedBytes = 0;
        }

        /* access modifiers changed from: package-private */
        public int incrementStreamWindow(int i) {
            if (i <= 0 || Integer.MAX_VALUE - i >= this.window) {
                int i2 = this.window + i;
                this.window = i2;
                return i2;
            }
            throw new IllegalArgumentException("Window size overflow for stream: " + this.streamId);
        }

        /* access modifiers changed from: package-private */
        public int writableWindow() {
            return Math.min(this.window, OutboundFlowController.this.connectionState.window());
        }

        /* access modifiers changed from: package-private */
        public int streamableBytes() {
            return Math.max(0, Math.min(this.window, (int) this.pendingWriteBuffer.size()));
        }

        /* access modifiers changed from: package-private */
        public boolean hasPendingData() {
            return this.pendingWriteBuffer.size() > 0;
        }

        /* access modifiers changed from: package-private */
        public int writeBytes(int i, WriteStatus writeStatus) {
            Runnable runnable;
            int min = Math.min(i, writableWindow());
            int i2 = 0;
            while (hasPendingData() && min > 0) {
                if (((long) min) >= this.pendingWriteBuffer.size()) {
                    i2 += (int) this.pendingWriteBuffer.size();
                    Buffer buffer = this.pendingWriteBuffer;
                    write(buffer, (int) buffer.size(), this.pendingBufferHasEndOfStream);
                } else {
                    i2 += min;
                    write(this.pendingWriteBuffer, min, false);
                }
                writeStatus.incrementNumWrites();
                min = Math.min(i - i2, writableWindow());
            }
            if (!hasPendingData() && (runnable = this.noPendingDataRunnable) != null) {
                runnable.run();
                this.noPendingDataRunnable = null;
            }
            return i2;
        }

        /* access modifiers changed from: package-private */
        public void write(Buffer buffer, int i, boolean z) {
            do {
                int min = Math.min(i, OutboundFlowController.this.frameWriter.maxDataLength());
                int i2 = -min;
                OutboundFlowController.this.connectionState.incrementStreamWindow(i2);
                incrementStreamWindow(i2);
                try {
                    OutboundFlowController.this.frameWriter.data(buffer.size() == ((long) min) && z, this.streamId, buffer, min);
                    this.stream.onSentBytes(min);
                    i -= min;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } while (i > 0);
        }

        /* access modifiers changed from: package-private */
        public void enqueueData(Buffer buffer, int i, boolean z) {
            this.pendingWriteBuffer.write(buffer, (long) i);
            this.pendingBufferHasEndOfStream |= z;
        }

        /* access modifiers changed from: package-private */
        public void notifyWhenNoPendingData(Runnable runnable) {
            Preconditions.checkState(this.noPendingDataRunnable == null, "pending data notification already requested");
            this.noPendingDataRunnable = runnable;
        }
    }
}
