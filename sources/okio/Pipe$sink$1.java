package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0018\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"okio/Pipe$sink$1", "Lokio/Sink;", "timeout", "Lokio/Timeout;", "close", "", "flush", "write", "source", "Lokio/Buffer;", "byteCount", "", "okio"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Pipe.kt */
public final class Pipe$sink$1 implements Sink {
    final /* synthetic */ Pipe this$0;
    private final Timeout timeout = new Timeout();

    Pipe$sink$1(Pipe pipe) {
        this.this$0 = pipe;
    }

    public void write(Buffer buffer, long j) {
        Sink sink;
        Intrinsics.checkNotNullParameter(buffer, "source");
        Lock lock = this.this$0.getLock();
        Pipe pipe = this.this$0;
        lock.lock();
        try {
            if (!(!pipe.getSinkClosed$okio())) {
                throw new IllegalStateException("closed".toString());
            } else if (!pipe.getCanceled$okio()) {
                while (true) {
                    if (j <= 0) {
                        sink = null;
                        break;
                    }
                    sink = pipe.getFoldedSink$okio();
                    if (sink != null) {
                        break;
                    } else if (!pipe.getSourceClosed$okio()) {
                        long maxBufferSize$okio = pipe.getMaxBufferSize$okio() - pipe.getBuffer$okio().size();
                        if (maxBufferSize$okio == 0) {
                            this.timeout.awaitSignal(pipe.getCondition());
                            if (pipe.getCanceled$okio()) {
                                throw new IOException("canceled");
                            }
                        } else {
                            long min = Math.min(maxBufferSize$okio, j);
                            pipe.getBuffer$okio().write(buffer, min);
                            j -= min;
                            pipe.getCondition().signalAll();
                        }
                    } else {
                        throw new IOException("source is closed");
                    }
                }
                Unit unit = Unit.INSTANCE;
                if (sink != null) {
                    Pipe pipe2 = this.this$0;
                    Timeout timeout2 = sink.timeout();
                    Timeout timeout3 = pipe2.sink().timeout();
                    long timeoutNanos = timeout2.timeoutNanos();
                    timeout2.timeout(Timeout.Companion.minTimeout(timeout3.timeoutNanos(), timeout2.timeoutNanos()), TimeUnit.NANOSECONDS);
                    if (timeout2.hasDeadline()) {
                        long deadlineNanoTime = timeout2.deadlineNanoTime();
                        if (timeout3.hasDeadline()) {
                            timeout2.deadlineNanoTime(Math.min(timeout2.deadlineNanoTime(), timeout3.deadlineNanoTime()));
                        }
                        try {
                            sink.write(buffer, j);
                            Unit unit2 = Unit.INSTANCE;
                        } finally {
                            timeout2.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout3.hasDeadline()) {
                                timeout2.deadlineNanoTime(deadlineNanoTime);
                            }
                        }
                    } else {
                        if (timeout3.hasDeadline()) {
                            timeout2.deadlineNanoTime(timeout3.deadlineNanoTime());
                        }
                        try {
                            sink.write(buffer, j);
                            Unit unit3 = Unit.INSTANCE;
                        } finally {
                            timeout2.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout3.hasDeadline()) {
                                timeout2.clearDeadline();
                            }
                        }
                    }
                }
            } else {
                throw new IOException("canceled");
            }
        } finally {
            lock.unlock();
        }
    }

    public void flush() {
        Lock lock = this.this$0.getLock();
        Pipe pipe = this.this$0;
        lock.lock();
        try {
            if (!(!pipe.getSinkClosed$okio())) {
                throw new IllegalStateException("closed".toString());
            } else if (!pipe.getCanceled$okio()) {
                Sink foldedSink$okio = pipe.getFoldedSink$okio();
                if (foldedSink$okio == null) {
                    if (pipe.getSourceClosed$okio()) {
                        if (pipe.getBuffer$okio().size() > 0) {
                            throw new IOException("source is closed");
                        }
                    }
                    foldedSink$okio = null;
                }
                Unit unit = Unit.INSTANCE;
                if (foldedSink$okio != null) {
                    Pipe pipe2 = this.this$0;
                    Timeout timeout2 = foldedSink$okio.timeout();
                    Timeout timeout3 = pipe2.sink().timeout();
                    long timeoutNanos = timeout2.timeoutNanos();
                    timeout2.timeout(Timeout.Companion.minTimeout(timeout3.timeoutNanos(), timeout2.timeoutNanos()), TimeUnit.NANOSECONDS);
                    if (timeout2.hasDeadline()) {
                        long deadlineNanoTime = timeout2.deadlineNanoTime();
                        if (timeout3.hasDeadline()) {
                            timeout2.deadlineNanoTime(Math.min(timeout2.deadlineNanoTime(), timeout3.deadlineNanoTime()));
                        }
                        try {
                            foldedSink$okio.flush();
                            Unit unit2 = Unit.INSTANCE;
                        } finally {
                            timeout2.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout3.hasDeadline()) {
                                timeout2.deadlineNanoTime(deadlineNanoTime);
                            }
                        }
                    } else {
                        if (timeout3.hasDeadline()) {
                            timeout2.deadlineNanoTime(timeout3.deadlineNanoTime());
                        }
                        try {
                            foldedSink$okio.flush();
                            Unit unit3 = Unit.INSTANCE;
                        } finally {
                            timeout2.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout3.hasDeadline()) {
                                timeout2.clearDeadline();
                            }
                        }
                    }
                }
            } else {
                throw new IOException("canceled");
            }
        } finally {
            lock.unlock();
        }
    }

    public void close() {
        Lock lock = this.this$0.getLock();
        Pipe pipe = this.this$0;
        lock.lock();
        try {
            if (!pipe.getSinkClosed$okio()) {
                Sink foldedSink$okio = pipe.getFoldedSink$okio();
                if (foldedSink$okio == null) {
                    if (pipe.getSourceClosed$okio()) {
                        if (pipe.getBuffer$okio().size() > 0) {
                            throw new IOException("source is closed");
                        }
                    }
                    pipe.setSinkClosed$okio(true);
                    pipe.getCondition().signalAll();
                    foldedSink$okio = null;
                }
                Unit unit = Unit.INSTANCE;
                lock.unlock();
                if (foldedSink$okio != null) {
                    Pipe pipe2 = this.this$0;
                    Timeout timeout2 = foldedSink$okio.timeout();
                    Timeout timeout3 = pipe2.sink().timeout();
                    long timeoutNanos = timeout2.timeoutNanos();
                    timeout2.timeout(Timeout.Companion.minTimeout(timeout3.timeoutNanos(), timeout2.timeoutNanos()), TimeUnit.NANOSECONDS);
                    if (timeout2.hasDeadline()) {
                        long deadlineNanoTime = timeout2.deadlineNanoTime();
                        if (timeout3.hasDeadline()) {
                            timeout2.deadlineNanoTime(Math.min(timeout2.deadlineNanoTime(), timeout3.deadlineNanoTime()));
                        }
                        try {
                            foldedSink$okio.close();
                            Unit unit2 = Unit.INSTANCE;
                        } finally {
                            timeout2.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout3.hasDeadline()) {
                                timeout2.deadlineNanoTime(deadlineNanoTime);
                            }
                        }
                    } else {
                        if (timeout3.hasDeadline()) {
                            timeout2.deadlineNanoTime(timeout3.deadlineNanoTime());
                        }
                        try {
                            foldedSink$okio.close();
                            Unit unit3 = Unit.INSTANCE;
                        } finally {
                            timeout2.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout3.hasDeadline()) {
                                timeout2.clearDeadline();
                            }
                        }
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public Timeout timeout() {
        return this.timeout;
    }
}
