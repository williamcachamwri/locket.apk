package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

/* compiled from: com.google.android.gms:play-services-base@@18.4.0 */
public abstract class AbstractDataBuffer<T> implements DataBuffer<T> {
    protected final DataHolder mDataHolder;

    protected AbstractDataBuffer(DataHolder dataHolder) {
        this.mDataHolder = dataHolder;
    }

    public final void close() {
        release();
    }

    public abstract T get(int i);

    public int getCount() {
        DataHolder dataHolder = this.mDataHolder;
        if (dataHolder == null) {
            return 0;
        }
        return dataHolder.getCount();
    }

    public final Bundle getMetadata() {
        DataHolder dataHolder = this.mDataHolder;
        if (dataHolder == null) {
            return null;
        }
        return dataHolder.getMetadata();
    }

    @Deprecated
    public boolean isClosed() {
        DataHolder dataHolder = this.mDataHolder;
        return dataHolder == null || dataHolder.isClosed();
    }

    public Iterator<T> iterator() {
        return new DataBufferIterator(this);
    }

    public void release() {
        DataHolder dataHolder = this.mDataHolder;
        if (dataHolder != null) {
            dataHolder.close();
        }
    }

    public Iterator<T> singleRefIterator() {
        return new SingleRefDataBufferIterator(this);
    }
}
