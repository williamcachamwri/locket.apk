package org.apache.commons.io.input;

import java.io.Reader;
import java.io.Serializable;

public class CharSequenceReader extends Reader implements Serializable {
    private static final long serialVersionUID = 3724187752191401220L;
    private final CharSequence charSequence;
    private int idx;
    private int mark;

    public boolean markSupported() {
        return true;
    }

    public CharSequenceReader(String str) {
        this.charSequence = str == null ? "" : str;
    }

    public void close() {
        this.idx = 0;
        this.mark = 0;
    }

    public void mark(int i) {
        this.mark = this.idx;
    }

    public int read() {
        if (this.idx >= this.charSequence.length()) {
            return -1;
        }
        CharSequence charSequence2 = this.charSequence;
        int i = this.idx;
        this.idx = i + 1;
        return charSequence2.charAt(i);
    }

    public int read(char[] cArr, int i, int i2) {
        if (this.idx >= this.charSequence.length()) {
            return -1;
        }
        if (cArr == null) {
            throw new NullPointerException("Character array is missing");
        } else if (i2 < 0 || i < 0 || i + i2 > cArr.length) {
            throw new IndexOutOfBoundsException("Array Size=" + cArr.length + ", offset=" + i + ", length=" + i2);
        } else {
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                int read = read();
                if (read == -1) {
                    return i3;
                }
                cArr[i + i4] = (char) read;
                i3++;
            }
            return i3;
        }
    }

    public void reset() {
        this.idx = this.mark;
    }

    public long skip(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("Number of characters to skip is less than zero: " + j);
        } else if (this.idx >= this.charSequence.length()) {
            return -1;
        } else {
            int min = (int) Math.min((long) this.charSequence.length(), ((long) this.idx) + j);
            this.idx = min;
            return (long) (min - this.idx);
        }
    }

    public String toString() {
        return this.charSequence.toString();
    }
}
