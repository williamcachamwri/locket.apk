package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.util.Logger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

public class BundleReader {
    protected static final int BUFFER_CAPACITY = 1024;
    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private ByteBuffer buffer;
    private final InputStream bundleInputStream;
    long bytesRead;
    private final InputStreamReader dataReader;
    BundleMetadata metadata;
    private final BundleSerializer serializer;

    public BundleReader(BundleSerializer bundleSerializer, InputStream inputStream) {
        this.serializer = bundleSerializer;
        this.bundleInputStream = inputStream;
        this.dataReader = new InputStreamReader(inputStream);
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        this.buffer = allocate;
        allocate.flip();
    }

    public BundleMetadata getBundleMetadata() throws IOException, JSONException {
        BundleMetadata bundleMetadata = this.metadata;
        if (bundleMetadata != null) {
            return bundleMetadata;
        }
        BundleElement readNextElement = readNextElement();
        if (readNextElement instanceof BundleMetadata) {
            BundleMetadata bundleMetadata2 = (BundleMetadata) readNextElement;
            this.metadata = bundleMetadata2;
            this.bytesRead = 0;
            return bundleMetadata2;
        }
        throw abort("Expected first element in bundle to be a metadata object");
    }

    public BundleElement getNextElement() throws IOException, JSONException {
        getBundleMetadata();
        return readNextElement();
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    public void close() throws IOException {
        this.bundleInputStream.close();
    }

    private BundleElement readNextElement() throws IOException, JSONException {
        String readLengthPrefix = readLengthPrefix();
        if (readLengthPrefix == null) {
            return null;
        }
        int parseInt = Integer.parseInt(readLengthPrefix);
        String readJsonString = readJsonString(parseInt);
        this.bytesRead += (long) (readLengthPrefix.getBytes(UTF8_CHARSET).length + parseInt);
        return decodeBundleElement(readJsonString);
    }

    /*  JADX ERROR: StackOverflow in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    private java.lang.String readLengthPrefix() throws java.io.IOException {
        /*
            r3 = this;
        L_0x0000:
            int r0 = r3.indexOfOpenBracket()
            r1 = -1
            if (r0 != r1) goto L_0x000d
            boolean r2 = r3.pullMoreData()
            if (r2 != 0) goto L_0x0000
        L_0x000d:
            java.nio.ByteBuffer r2 = r3.buffer
            int r2 = r2.remaining()
            if (r2 != 0) goto L_0x0017
            r0 = 0
            return r0
        L_0x0017:
            if (r0 == r1) goto L_0x002f
            byte[] r0 = new byte[r0]
            java.nio.ByteBuffer r1 = r3.buffer
            r1.get(r0)
            java.nio.charset.Charset r1 = UTF8_CHARSET
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r0)
            java.nio.CharBuffer r0 = r1.decode(r0)
            java.lang.String r0 = r0.toString()
            return r0
        L_0x002f:
            java.lang.String r0 = "Reached the end of bundle when a length string is expected."
            java.lang.IllegalArgumentException r0 = r3.abort(r0)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.bundle.BundleReader.readLengthPrefix():java.lang.String");
    }

    private int indexOfOpenBracket() {
        this.buffer.mark();
        int i = 0;
        while (i < this.buffer.remaining()) {
            try {
                if (this.buffer.get() == 123) {
                    return i;
                }
                i++;
            } finally {
                this.buffer.reset();
            }
        }
        this.buffer.reset();
        return -1;
    }

    private String readJsonString(int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (i > 0) {
            if (this.buffer.remaining() != 0 || pullMoreData()) {
                int min = Math.min(i, this.buffer.remaining());
                byteArrayOutputStream.write(this.buffer.array(), this.buffer.arrayOffset() + this.buffer.position(), min);
                ByteBuffer byteBuffer = this.buffer;
                byteBuffer.position(byteBuffer.position() + min);
                i -= min;
            } else {
                throw abort("Reached the end of bundle when more data was expected.");
            }
        }
        return byteArrayOutputStream.toString(UTF8_CHARSET.name());
    }

    private boolean pullMoreData() throws IOException {
        this.buffer.compact();
        int read = this.bundleInputStream.read(this.buffer.array(), this.buffer.arrayOffset() + this.buffer.position(), this.buffer.remaining());
        boolean z = read > 0;
        if (z) {
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.position() + read);
        }
        this.buffer.flip();
        return z;
    }

    private BundleElement decodeBundleElement(String str) throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has(TtmlNode.TAG_METADATA)) {
            BundleMetadata decodeBundleMetadata = this.serializer.decodeBundleMetadata(jSONObject.getJSONObject(TtmlNode.TAG_METADATA));
            Logger.debug("BundleElement", "BundleMetadata element loaded", new Object[0]);
            return decodeBundleMetadata;
        } else if (jSONObject.has("namedQuery")) {
            NamedQuery decodeNamedQuery = this.serializer.decodeNamedQuery(jSONObject.getJSONObject("namedQuery"));
            Logger.debug("BundleElement", "Query loaded: " + decodeNamedQuery.getName(), new Object[0]);
            return decodeNamedQuery;
        } else if (jSONObject.has("documentMetadata")) {
            BundledDocumentMetadata decodeBundledDocumentMetadata = this.serializer.decodeBundledDocumentMetadata(jSONObject.getJSONObject("documentMetadata"));
            Logger.debug("BundleElement", "Document metadata loaded: " + decodeBundledDocumentMetadata.getKey(), new Object[0]);
            return decodeBundledDocumentMetadata;
        } else if (jSONObject.has("document")) {
            BundleDocument decodeDocument = this.serializer.decodeDocument(jSONObject.getJSONObject("document"));
            Logger.debug("BundleElement", "Document loaded: " + decodeDocument.getKey(), new Object[0]);
            return decodeDocument;
        } else {
            throw abort("Cannot decode unknown Bundle element: " + str);
        }
    }

    private IllegalArgumentException abort(String str) throws IOException {
        close();
        throw new IllegalArgumentException("Invalid bundle: " + str);
    }
}
