package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.UUID;
import org.apache.commons.io.TaggedIOException;

public class TaggedOutputStream extends ProxyOutputStream {
    private final Serializable tag = UUID.randomUUID();

    public TaggedOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public boolean isCauseOf(Exception exc) {
        return TaggedIOException.isTaggedWith(exc, this.tag);
    }

    public void throwIfCauseOf(Exception exc) throws IOException {
        TaggedIOException.throwCauseIfTaggedWith(exc, this.tag);
    }

    /* access modifiers changed from: protected */
    public void handleIOException(IOException iOException) throws IOException {
        throw new TaggedIOException(iOException, this.tag);
    }
}
