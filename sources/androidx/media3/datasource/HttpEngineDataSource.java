package androidx.media3.datasource;

import android.net.Uri;
import android.net.http.HttpEngine;
import android.net.http.HttpException;
import android.net.http.NetworkException;
import android.net.http.UrlRequest;
import android.net.http.UrlResponseInfo;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.HttpDataSource;
import com.google.common.base.Ascii;
import com.google.common.base.Predicate;
import com.google.common.net.HttpHeaders;
import com.google.common.primitives.Longs;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public final class HttpEngineDataSource extends BaseDataSource implements HttpDataSource {
    public static final int DEFAULT_CONNECT_TIMEOUT_MILLIS = 8000;
    public static final int DEFAULT_READ_TIMEOUT_MILLIS = 8000;
    private static final int READ_BUFFER_SIZE_BYTES = 32768;
    private long bytesRemaining;
    private final Clock clock = Clock.DEFAULT;
    private final int connectTimeoutMs;
    private Predicate<String> contentTypePredicate;
    private volatile long currentConnectTimeoutMs;
    /* access modifiers changed from: private */
    public DataSpec currentDataSpec;
    /* access modifiers changed from: private */
    public UrlRequestWrapper currentUrlRequestWrapper;
    private final HttpDataSource.RequestProperties defaultRequestProperties;
    /* access modifiers changed from: private */
    public IOException exception;
    private final Executor executor;
    /* access modifiers changed from: private */
    public boolean finished;
    /* access modifiers changed from: private */
    public final boolean handleSetCookieRequests;
    private final HttpEngine httpEngine;
    /* access modifiers changed from: private */
    public final boolean keepPostFor302Redirects;
    /* access modifiers changed from: private */
    public final ConditionVariable operation = new ConditionVariable();
    private ByteBuffer readBuffer;
    private final int readTimeoutMs;
    private final int requestPriority;
    private final HttpDataSource.RequestProperties requestProperties = new HttpDataSource.RequestProperties();
    /* access modifiers changed from: private */
    public final boolean resetTimeoutOnRedirects;
    /* access modifiers changed from: private */
    public UrlResponseInfo responseInfo;
    private boolean transferStarted;
    private final String userAgent;

    public static final class Factory implements HttpDataSource.Factory {
        private int connectTimeoutMs = 8000;
        private Predicate<String> contentTypePredicate;
        private final HttpDataSource.RequestProperties defaultRequestProperties = new HttpDataSource.RequestProperties();
        private final Executor executor;
        private boolean handleSetCookieRequests;
        private final HttpEngine httpEngine;
        private boolean keepPostFor302Redirects;
        private int readTimeoutMs = 8000;
        private int requestPriority = 3;
        private boolean resetTimeoutOnRedirects;
        private TransferListener transferListener;
        private String userAgent;

        public Factory(HttpEngine httpEngine2, Executor executor2) {
            this.httpEngine = (HttpEngine) Assertions.checkNotNull(httpEngine2);
            this.executor = executor2;
        }

        public final Factory setDefaultRequestProperties(Map<String, String> map) {
            this.defaultRequestProperties.clearAndSet(map);
            return this;
        }

        public Factory setUserAgent(String str) {
            this.userAgent = str;
            return this;
        }

        public Factory setRequestPriority(int i) {
            this.requestPriority = i;
            return this;
        }

        public Factory setConnectionTimeoutMs(int i) {
            this.connectTimeoutMs = i;
            return this;
        }

        public Factory setResetTimeoutOnRedirects(boolean z) {
            this.resetTimeoutOnRedirects = z;
            return this;
        }

        public Factory setHandleSetCookieRequests(boolean z) {
            this.handleSetCookieRequests = z;
            return this;
        }

        public Factory setReadTimeoutMs(int i) {
            this.readTimeoutMs = i;
            return this;
        }

        public Factory setContentTypePredicate(Predicate<String> predicate) {
            this.contentTypePredicate = predicate;
            return this;
        }

        public Factory setKeepPostFor302Redirects(boolean z) {
            this.keepPostFor302Redirects = z;
            return this;
        }

        public Factory setTransferListener(TransferListener transferListener2) {
            this.transferListener = transferListener2;
            return this;
        }

        public HttpDataSource createDataSource() {
            HttpEngineDataSource httpEngineDataSource = new HttpEngineDataSource(this.httpEngine, this.executor, this.requestPriority, this.connectTimeoutMs, this.readTimeoutMs, this.resetTimeoutOnRedirects, this.handleSetCookieRequests, this.userAgent, this.defaultRequestProperties, this.contentTypePredicate, this.keepPostFor302Redirects);
            TransferListener transferListener2 = this.transferListener;
            if (transferListener2 != null) {
                httpEngineDataSource.addTransferListener(transferListener2);
            }
            return httpEngineDataSource;
        }
    }

    public static final class OpenException extends HttpDataSource.HttpDataSourceException {
        public final int httpEngineConnectionStatus;

        public OpenException(IOException iOException, DataSpec dataSpec, int i, int i2) {
            super(iOException, dataSpec, i, 1);
            this.httpEngineConnectionStatus = i2;
        }

        public OpenException(String str, DataSpec dataSpec, int i, int i2) {
            super(str, dataSpec, i, 1);
            this.httpEngineConnectionStatus = i2;
        }

        public OpenException(DataSpec dataSpec, int i, int i2) {
            super(dataSpec, i, 1);
            this.httpEngineConnectionStatus = i2;
        }
    }

    HttpEngineDataSource(HttpEngine httpEngine2, Executor executor2, int i, int i2, int i3, boolean z, boolean z2, String str, HttpDataSource.RequestProperties requestProperties2, Predicate<String> predicate, boolean z3) {
        super(true);
        this.httpEngine = (HttpEngine) Assertions.checkNotNull(httpEngine2);
        this.executor = (Executor) Assertions.checkNotNull(executor2);
        this.requestPriority = i;
        this.connectTimeoutMs = i2;
        this.readTimeoutMs = i3;
        this.resetTimeoutOnRedirects = z;
        this.handleSetCookieRequests = z2;
        this.userAgent = str;
        this.defaultRequestProperties = requestProperties2;
        this.contentTypePredicate = predicate;
        this.keepPostFor302Redirects = z3;
    }

    public void setRequestProperty(String str, String str2) {
        this.requestProperties.set(str, str2);
    }

    public void clearRequestProperty(String str) {
        this.requestProperties.remove(str);
    }

    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    public int getResponseCode() {
        UrlResponseInfo urlResponseInfo = this.responseInfo;
        if (urlResponseInfo == null || urlResponseInfo.getHttpStatusCode() <= 0) {
            return -1;
        }
        return this.responseInfo.getHttpStatusCode();
    }

    public Map<String, List<String>> getResponseHeaders() {
        UrlResponseInfo urlResponseInfo = this.responseInfo;
        return urlResponseInfo == null ? Collections.emptyMap() : urlResponseInfo.getHeaders().getAsMap();
    }

    public Uri getUri() {
        UrlResponseInfo urlResponseInfo = this.responseInfo;
        if (urlResponseInfo != null) {
            return Uri.parse(urlResponseInfo.getUrl());
        }
        DataSpec dataSpec = this.currentDataSpec;
        if (dataSpec != null) {
            return dataSpec.uri;
        }
        return null;
    }

    public long open(DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        byte[] bArr;
        String firstHeader;
        DataSpec dataSpec2 = dataSpec;
        Assertions.checkNotNull(dataSpec);
        Assertions.checkState(!this.transferStarted);
        this.operation.close();
        resetConnectTimeout();
        this.currentDataSpec = dataSpec2;
        try {
            UrlRequestWrapper buildRequestWrapper = buildRequestWrapper(dataSpec);
            this.currentUrlRequestWrapper = buildRequestWrapper;
            buildRequestWrapper.start();
            transferInitializing(dataSpec);
            try {
                boolean blockUntilConnectTimeout = blockUntilConnectTimeout();
                IOException iOException = this.exception;
                if (iOException != null) {
                    String message = iOException.getMessage();
                    if (message == null || !Ascii.toLowerCase(message).contains("err_cleartext_not_permitted")) {
                        throw new OpenException(iOException, dataSpec2, 2001, buildRequestWrapper.getStatus());
                    }
                    throw new HttpDataSource.CleartextNotPermittedException(iOException, dataSpec2);
                } else if (blockUntilConnectTimeout) {
                    UrlResponseInfo urlResponseInfo = (UrlResponseInfo) Assertions.checkNotNull(this.responseInfo);
                    int httpStatusCode = urlResponseInfo.getHttpStatusCode();
                    Map asMap = urlResponseInfo.getHeaders().getAsMap();
                    long j = 0;
                    long j2 = -1;
                    if (httpStatusCode < 200 || httpStatusCode > 299) {
                        if (httpStatusCode == 416) {
                            if (dataSpec2.position == HttpUtil.getDocumentSize(getFirstHeader(asMap, HttpHeaders.CONTENT_RANGE))) {
                                this.transferStarted = true;
                                transferStarted(dataSpec);
                                if (dataSpec2.length != -1) {
                                    return dataSpec2.length;
                                }
                                return 0;
                            }
                        }
                        try {
                            bArr = readResponseBody();
                        } catch (IOException unused) {
                            bArr = Util.EMPTY_BYTE_ARRAY;
                        }
                        byte[] bArr2 = bArr;
                        throw new HttpDataSource.InvalidResponseCodeException(httpStatusCode, urlResponseInfo.getHttpStatusText(), httpStatusCode == 416 ? new DataSourceException(2008) : null, asMap, dataSpec, bArr2);
                    }
                    Predicate<String> predicate = this.contentTypePredicate;
                    if (predicate == null || (firstHeader = getFirstHeader(asMap, HttpHeaders.CONTENT_TYPE)) == null || predicate.apply(firstHeader)) {
                        if (httpStatusCode == 200 && dataSpec2.position != 0) {
                            j = dataSpec2.position;
                        }
                        if (isCompressed(urlResponseInfo)) {
                            this.bytesRemaining = dataSpec2.length;
                        } else if (dataSpec2.length != -1) {
                            this.bytesRemaining = dataSpec2.length;
                        } else {
                            long contentLength = HttpUtil.getContentLength(getFirstHeader(asMap, HttpHeaders.CONTENT_LENGTH), getFirstHeader(asMap, HttpHeaders.CONTENT_RANGE));
                            if (contentLength != -1) {
                                j2 = contentLength - j;
                            }
                            this.bytesRemaining = j2;
                        }
                        this.transferStarted = true;
                        transferStarted(dataSpec);
                        skipFully(j, dataSpec2);
                        return this.bytesRemaining;
                    }
                    throw new HttpDataSource.InvalidContentTypeException(firstHeader, dataSpec2);
                } else {
                    throw new OpenException((IOException) new SocketTimeoutException(), dataSpec2, 2002, buildRequestWrapper.getStatus());
                }
            } catch (InterruptedException unused2) {
                Thread.currentThread().interrupt();
                throw new OpenException((IOException) new InterruptedIOException(), dataSpec2, 1004, -1);
            }
        } catch (IOException e) {
            if (e instanceof HttpDataSource.HttpDataSourceException) {
                throw ((HttpDataSource.HttpDataSourceException) e);
            }
            throw new OpenException(e, dataSpec2, 2000, 0);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws HttpDataSource.HttpDataSourceException {
        Assertions.checkState(this.transferStarted);
        if (i2 == 0) {
            return 0;
        }
        if (this.bytesRemaining == 0) {
            return -1;
        }
        ByteBuffer orCreateReadBuffer = getOrCreateReadBuffer();
        if (!orCreateReadBuffer.hasRemaining()) {
            this.operation.close();
            orCreateReadBuffer.clear();
            readInternal(orCreateReadBuffer, (DataSpec) Util.castNonNull(this.currentDataSpec));
            if (this.finished) {
                this.bytesRemaining = 0;
                return -1;
            }
            orCreateReadBuffer.flip();
            Assertions.checkState(orCreateReadBuffer.hasRemaining());
        }
        long[] jArr = new long[3];
        long j = this.bytesRemaining;
        if (j == -1) {
            j = Long.MAX_VALUE;
        }
        jArr[0] = j;
        jArr[1] = (long) orCreateReadBuffer.remaining();
        jArr[2] = (long) i2;
        int min = (int) Longs.min(jArr);
        orCreateReadBuffer.get(bArr, i, min);
        long j2 = this.bytesRemaining;
        if (j2 != -1) {
            this.bytesRemaining = j2 - ((long) min);
        }
        bytesTransferred(min);
        return min;
    }

    public int read(ByteBuffer byteBuffer) throws HttpDataSource.HttpDataSourceException {
        int copyByteBuffer;
        Assertions.checkState(this.transferStarted);
        if (byteBuffer.isDirect()) {
            boolean z = false;
            if (!byteBuffer.hasRemaining()) {
                return 0;
            }
            if (this.bytesRemaining == 0) {
                return -1;
            }
            int remaining = byteBuffer.remaining();
            ByteBuffer byteBuffer2 = this.readBuffer;
            if (byteBuffer2 == null || (copyByteBuffer = copyByteBuffer(byteBuffer2, byteBuffer)) == 0) {
                this.operation.close();
                readInternal(byteBuffer, (DataSpec) Util.castNonNull(this.currentDataSpec));
                if (this.finished) {
                    this.bytesRemaining = 0;
                    return -1;
                }
                if (remaining > byteBuffer.remaining()) {
                    z = true;
                }
                Assertions.checkState(z);
                int remaining2 = remaining - byteBuffer.remaining();
                long j = this.bytesRemaining;
                if (j != -1) {
                    this.bytesRemaining = j - ((long) remaining2);
                }
                bytesTransferred(remaining2);
                return remaining2;
            }
            long j2 = this.bytesRemaining;
            if (j2 != -1) {
                this.bytesRemaining = j2 - ((long) copyByteBuffer);
            }
            bytesTransferred(copyByteBuffer);
            return copyByteBuffer;
        }
        throw new IllegalArgumentException("Passed buffer is not a direct ByteBuffer");
    }

    public synchronized void close() {
        UrlRequestWrapper urlRequestWrapper = this.currentUrlRequestWrapper;
        if (urlRequestWrapper != null) {
            urlRequestWrapper.close();
            this.currentUrlRequestWrapper = null;
        }
        ByteBuffer byteBuffer = this.readBuffer;
        if (byteBuffer != null) {
            byteBuffer.limit(0);
        }
        this.currentDataSpec = null;
        this.responseInfo = null;
        this.exception = null;
        this.finished = false;
        if (this.transferStarted) {
            this.transferStarted = false;
            transferEnded();
        }
    }

    /* access modifiers changed from: package-private */
    public UrlRequest.Callback getCurrentUrlRequestCallback() {
        UrlRequestWrapper urlRequestWrapper = this.currentUrlRequestWrapper;
        if (urlRequestWrapper == null) {
            return null;
        }
        return urlRequestWrapper.getUrlRequestCallback();
    }

    /* access modifiers changed from: private */
    public UrlRequestWrapper buildRequestWrapper(DataSpec dataSpec) throws IOException {
        UrlRequestCallback urlRequestCallback = new UrlRequestCallback();
        return new UrlRequestWrapper(buildRequestBuilder(dataSpec, urlRequestCallback).build(), urlRequestCallback);
    }

    private UrlRequest.Builder buildRequestBuilder(DataSpec dataSpec, UrlRequest.Callback callback) throws IOException {
        UrlRequest.Builder directExecutorAllowed = this.httpEngine.newUrlRequestBuilder(dataSpec.uri.toString(), this.executor, callback).setPriority(this.requestPriority).setDirectExecutorAllowed(true);
        HashMap hashMap = new HashMap();
        HttpDataSource.RequestProperties requestProperties2 = this.defaultRequestProperties;
        if (requestProperties2 != null) {
            hashMap.putAll(requestProperties2.getSnapshot());
        }
        hashMap.putAll(this.requestProperties.getSnapshot());
        hashMap.putAll(dataSpec.httpRequestHeaders);
        for (Map.Entry entry : hashMap.entrySet()) {
            directExecutorAllowed.addHeader((String) entry.getKey(), (String) entry.getValue());
        }
        if (dataSpec.httpBody == null || hashMap.containsKey(HttpHeaders.CONTENT_TYPE)) {
            String buildRangeRequestHeader = HttpUtil.buildRangeRequestHeader(dataSpec.position, dataSpec.length);
            if (buildRangeRequestHeader != null) {
                directExecutorAllowed.addHeader(HttpHeaders.RANGE, buildRangeRequestHeader);
            }
            String str = this.userAgent;
            if (str != null) {
                directExecutorAllowed.addHeader(HttpHeaders.USER_AGENT, str);
            }
            directExecutorAllowed.setHttpMethod(dataSpec.getHttpMethodString());
            if (dataSpec.httpBody != null) {
                directExecutorAllowed.setUploadDataProvider(new ByteArrayUploadDataProvider(dataSpec.httpBody), this.executor);
            }
            return directExecutorAllowed;
        }
        throw new OpenException("HTTP request with non-empty body must set Content-Type", dataSpec, 1004, 0);
    }

    private boolean blockUntilConnectTimeout() throws InterruptedException {
        long elapsedRealtime = this.clock.elapsedRealtime();
        boolean z = false;
        while (!z && elapsedRealtime < this.currentConnectTimeoutMs) {
            z = this.operation.block((this.currentConnectTimeoutMs - elapsedRealtime) + 5);
            elapsedRealtime = this.clock.elapsedRealtime();
        }
        return z;
    }

    /* access modifiers changed from: private */
    public void resetConnectTimeout() {
        this.currentConnectTimeoutMs = this.clock.elapsedRealtime() + ((long) this.connectTimeoutMs);
    }

    private void skipFully(long j, DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        if (j != 0) {
            ByteBuffer orCreateReadBuffer = getOrCreateReadBuffer();
            while (j > 0) {
                try {
                    this.operation.close();
                    orCreateReadBuffer.clear();
                    readInternal(orCreateReadBuffer, dataSpec);
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedIOException();
                    } else if (!this.finished) {
                        orCreateReadBuffer.flip();
                        Assertions.checkState(orCreateReadBuffer.hasRemaining());
                        int min = (int) Math.min((long) orCreateReadBuffer.remaining(), j);
                        orCreateReadBuffer.position(orCreateReadBuffer.position() + min);
                        j -= (long) min;
                    } else {
                        throw new OpenException(dataSpec, 2008, 14);
                    }
                } catch (IOException e) {
                    if (!(e instanceof HttpDataSource.HttpDataSourceException)) {
                        throw new OpenException(e, dataSpec, e instanceof SocketTimeoutException ? 2002 : 2001, 14);
                    }
                    throw ((HttpDataSource.HttpDataSourceException) e);
                }
            }
        }
    }

    private byte[] readResponseBody() throws IOException {
        byte[] bArr = Util.EMPTY_BYTE_ARRAY;
        ByteBuffer orCreateReadBuffer = getOrCreateReadBuffer();
        while (!this.finished) {
            this.operation.close();
            orCreateReadBuffer.clear();
            readInternal(orCreateReadBuffer, (DataSpec) Util.castNonNull(this.currentDataSpec));
            orCreateReadBuffer.flip();
            if (orCreateReadBuffer.remaining() > 0) {
                int length = bArr.length;
                bArr = Arrays.copyOf(bArr, bArr.length + orCreateReadBuffer.remaining());
                orCreateReadBuffer.get(bArr, length, orCreateReadBuffer.remaining());
            }
        }
        return bArr;
    }

    private void readInternal(ByteBuffer byteBuffer, DataSpec dataSpec) throws HttpDataSource.HttpDataSourceException {
        ((UrlRequestWrapper) Util.castNonNull(this.currentUrlRequestWrapper)).read(byteBuffer);
        try {
            if (this.operation.block((long) this.readTimeoutMs)) {
                IOException iOException = this.exception;
                if (iOException == null) {
                    return;
                }
                if (iOException instanceof HttpDataSource.HttpDataSourceException) {
                    throw ((HttpDataSource.HttpDataSourceException) iOException);
                }
                throw HttpDataSource.HttpDataSourceException.createForIOException(iOException, dataSpec, 2);
            }
            throw new SocketTimeoutException();
        } catch (InterruptedException unused) {
            if (byteBuffer == this.readBuffer) {
                this.readBuffer = null;
            }
            Thread.currentThread().interrupt();
            this.exception = new InterruptedIOException();
        } catch (SocketTimeoutException e) {
            if (byteBuffer == this.readBuffer) {
                this.readBuffer = null;
            }
            this.exception = new HttpDataSource.HttpDataSourceException((IOException) e, dataSpec, 2002, 2);
        }
    }

    private ByteBuffer getOrCreateReadBuffer() {
        if (this.readBuffer == null) {
            ByteBuffer allocateDirect = ByteBuffer.allocateDirect(32768);
            this.readBuffer = allocateDirect;
            allocateDirect.limit(0);
        }
        return this.readBuffer;
    }

    private static boolean isCompressed(UrlResponseInfo urlResponseInfo) {
        for (Map.Entry entry : urlResponseInfo.getHeaders().getAsList()) {
            if (((String) entry.getKey()).equalsIgnoreCase(HttpHeaders.CONTENT_ENCODING)) {
                return !((String) entry.getValue()).equalsIgnoreCase(InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static String parseCookies(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return TextUtils.join(";", list);
    }

    private static String getFirstHeader(Map<String, List<String>> map, String str) {
        List list = map.get(str);
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (String) list.get(0);
    }

    private static int copyByteBuffer(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int min = Math.min(byteBuffer.remaining(), byteBuffer2.remaining());
        int limit = byteBuffer.limit();
        byteBuffer.limit(byteBuffer.position() + min);
        byteBuffer2.put(byteBuffer);
        byteBuffer.limit(limit);
        return min;
    }

    private static final class UrlRequestWrapper {
        private final UrlRequest urlRequest;
        private final UrlRequestCallback urlRequestCallback;

        UrlRequestWrapper(UrlRequest urlRequest2, UrlRequestCallback urlRequestCallback2) {
            this.urlRequest = urlRequest2;
            this.urlRequestCallback = urlRequestCallback2;
        }

        public void start() {
            this.urlRequest.start();
        }

        public void read(ByteBuffer byteBuffer) {
            this.urlRequest.read(byteBuffer);
        }

        public void close() {
            this.urlRequestCallback.close();
            this.urlRequest.cancel();
        }

        public UrlRequest.Callback getUrlRequestCallback() {
            return this.urlRequestCallback;
        }

        public int getStatus() throws InterruptedException {
            final ConditionVariable conditionVariable = new ConditionVariable();
            final int[] iArr = new int[1];
            this.urlRequest.getStatus(new UrlRequest.StatusListener() {
                public void onStatus(int i) {
                    iArr[0] = i;
                    conditionVariable.open();
                }
            });
            conditionVariable.block();
            return iArr[0];
        }
    }

    private final class UrlRequestCallback implements UrlRequest.Callback {
        private volatile boolean isClosed;

        private UrlRequestCallback() {
            this.isClosed = false;
        }

        public void close() {
            this.isClosed = true;
        }

        public synchronized void onRedirectReceived(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, String str) {
            DataSpec dataSpec;
            if (!this.isClosed) {
                DataSpec dataSpec2 = (DataSpec) Assertions.checkNotNull(HttpEngineDataSource.this.currentDataSpec);
                int httpStatusCode = urlResponseInfo.getHttpStatusCode();
                if (dataSpec2.httpMethod == 2 && (httpStatusCode == 307 || httpStatusCode == 308)) {
                    IOException unused = HttpEngineDataSource.this.exception = new HttpDataSource.InvalidResponseCodeException(httpStatusCode, urlResponseInfo.getHttpStatusText(), (IOException) null, urlResponseInfo.getHeaders().getAsMap(), dataSpec2, Util.EMPTY_BYTE_ARRAY);
                    HttpEngineDataSource.this.operation.open();
                    return;
                }
                if (HttpEngineDataSource.this.resetTimeoutOnRedirects) {
                    HttpEngineDataSource.this.resetConnectTimeout();
                }
                boolean z = HttpEngineDataSource.this.keepPostFor302Redirects && dataSpec2.httpMethod == 2 && httpStatusCode == 302;
                if (z || HttpEngineDataSource.this.handleSetCookieRequests) {
                    String access$800 = HttpEngineDataSource.parseCookies((List) urlResponseInfo.getHeaders().getAsMap().get(HttpHeaders.SET_COOKIE));
                    if (z || !TextUtils.isEmpty(access$800)) {
                        urlRequest.cancel();
                        if (z || dataSpec2.httpMethod != 2) {
                            dataSpec = dataSpec2.withUri(Uri.parse(str));
                        } else {
                            dataSpec = dataSpec2.buildUpon().setUri(str).setHttpMethod(1).setHttpBody((byte[]) null).build();
                        }
                        if (!TextUtils.isEmpty(access$800)) {
                            HashMap hashMap = new HashMap();
                            hashMap.putAll(dataSpec2.httpRequestHeaders);
                            hashMap.put("Cookie", access$800);
                            dataSpec = dataSpec.buildUpon().setHttpRequestHeaders(hashMap).build();
                        }
                        try {
                            UrlRequestWrapper access$900 = HttpEngineDataSource.this.buildRequestWrapper(dataSpec);
                            if (HttpEngineDataSource.this.currentUrlRequestWrapper != null) {
                                HttpEngineDataSource.this.currentUrlRequestWrapper.close();
                            }
                            UrlRequestWrapper unused2 = HttpEngineDataSource.this.currentUrlRequestWrapper = access$900;
                            HttpEngineDataSource.this.currentUrlRequestWrapper.start();
                        } catch (IOException e) {
                            IOException unused3 = HttpEngineDataSource.this.exception = e;
                        }
                    } else {
                        urlRequest.followRedirect();
                    }
                } else {
                    urlRequest.followRedirect();
                }
            }
        }

        public synchronized void onResponseStarted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            if (!this.isClosed) {
                UrlResponseInfo unused = HttpEngineDataSource.this.responseInfo = urlResponseInfo;
                HttpEngineDataSource.this.operation.open();
            }
        }

        public synchronized void onReadCompleted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer) {
            if (!this.isClosed) {
                HttpEngineDataSource.this.operation.open();
            }
        }

        public synchronized void onSucceeded(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            if (!this.isClosed) {
                boolean unused = HttpEngineDataSource.this.finished = true;
                HttpEngineDataSource.this.operation.open();
            }
        }

        public synchronized void onFailed(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, HttpException httpException) {
            if (!this.isClosed) {
                if (!(httpException instanceof NetworkException) || ((NetworkException) httpException).getErrorCode() != 1) {
                    IOException unused = HttpEngineDataSource.this.exception = httpException;
                } else {
                    IOException unused2 = HttpEngineDataSource.this.exception = new UnknownHostException();
                }
                HttpEngineDataSource.this.operation.open();
            }
        }

        public synchronized void onCanceled(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
        }
    }
}
