package androidx.camera.core;

import java.util.concurrent.atomic.AtomicBoolean;

final class SingleCloseImageProxy extends ForwardingImageProxy {
    private final AtomicBoolean mClosed = new AtomicBoolean(false);

    SingleCloseImageProxy(ImageProxy imageProxy) {
        super(imageProxy);
    }

    public void close() {
        if (!this.mClosed.getAndSet(true)) {
            super.close();
        }
    }
}
