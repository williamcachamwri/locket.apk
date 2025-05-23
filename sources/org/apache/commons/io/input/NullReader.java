package org.apache.commons.io.input;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class NullReader extends Reader {
    private boolean eof;
    private long mark;
    private final boolean markSupported;
    private long position;
    private long readlimit;
    private final long size;
    private final boolean throwEofException;

    /* access modifiers changed from: protected */
    public int processChar() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void processChars(char[] cArr, int i, int i2) {
    }

    public NullReader(long j) {
        this(j, true, false);
    }

    public NullReader(long j, boolean z, boolean z2) {
        this.mark = -1;
        this.size = j;
        this.markSupported = z;
        this.throwEofException = z2;
    }

    public long getPosition() {
        return this.position;
    }

    public long getSize() {
        return this.size;
    }

    public void close() throws IOException {
        this.eof = false;
        this.position = 0;
        this.mark = -1;
    }

    public synchronized void mark(int i) {
        if (this.markSupported) {
            this.mark = this.position;
            this.readlimit = (long) i;
        } else {
            throw new UnsupportedOperationException("Mark not supported");
        }
    }

    public boolean markSupported() {
        return this.markSupported;
    }

    public int read() throws IOException {
        if (!this.eof) {
            long j = this.position;
            if (j == this.size) {
                return doEndOfFile();
            }
            this.position = j + 1;
            return processChar();
        }
        throw new IOException("Read after end of file");
    }

    public int read(char[] cArr) throws IOException {
        return read(cArr, 0, cArr.length);
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        if (!this.eof) {
            long j = this.position;
            long j2 = this.size;
            if (j == j2) {
                return doEndOfFile();
            }
            long j3 = j + ((long) i2);
            this.position = j3;
            if (j3 > j2) {
                i2 -= (int) (j3 - j2);
                this.position = j2;
            }
            processChars(cArr, i, i2);
            return i2;
        }
        throw new IOException("Read after end of file");
    }

    public synchronized void reset() throws IOException {
        if (this.markSupported) {
            long j = this.mark;
            if (j < 0) {
                throw new IOException("No position has been marked");
            } else if (this.position <= this.readlimit + j) {
                this.position = j;
                this.eof = false;
            } else {
                throw new IOException("Marked position [" + this.mark + "] is no longer valid - passed the read limit [" + this.readlimit + "]");
            }
        } else {
            throw new UnsupportedOperationException("Mark not supported");
        }
    }

    public long skip(long j) throws IOException {
        if (!this.eof) {
            long j2 = this.position;
            long j3 = this.size;
            if (j2 == j3) {
                return (long) doEndOfFile();
            }
            long j4 = j2 + j;
            this.position = j4;
            if (j4 <= j3) {
                return j;
            }
            long j5 = j - (j4 - j3);
            this.position = j3;
            return j5;
        }
        throw new IOException("Skip after end of file");
    }

    private int doEndOfFile() throws EOFException {
        this.eof = true;
        if (!this.throwEofException) {
            return -1;
        }
        throw new EOFException();
    }
}
