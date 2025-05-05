package androidx.media3.datasource.okhttp;

import android.net.Uri;
import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.BaseDataSource;
import androidx.media3.datasource.DataSourceException;
import androidx.media3.datasource.DataSpec;
import androidx.media3.datasource.HttpDataSource;
import androidx.media3.datasource.HttpUtil;
import androidx.media3.datasource.TransferListener;
import com.google.common.base.Predicate;
import com.google.common.io.ByteStreams;
import com.google.common.net.HttpHeaders;
import com.google.common.util.concurrent.SettableFuture;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class OkHttpDataSource extends BaseDataSource implements HttpDataSource {
    private long bytesRead;
    private long bytesToRead;
    private final CacheControl cacheControl;
    private final Call.Factory callFactory;
    private boolean connectionEstablished;
    private final Predicate<String> contentTypePredicate;
    private DataSpec dataSpec;
    private final HttpDataSource.RequestProperties defaultRequestProperties;
    private final HttpDataSource.RequestProperties requestProperties;
    private Response response;
    private InputStream responseByteStream;
    private final String userAgent;

    static {
        MediaLibraryInfo.registerModule("media3.datasource.okhttp");
    }

    public static final class Factory implements HttpDataSource.Factory {
        private CacheControl cacheControl;
        private final Call.Factory callFactory;
        private Predicate<String> contentTypePredicate;
        private final HttpDataSource.RequestProperties defaultRequestProperties = new HttpDataSource.RequestProperties();
        private TransferListener transferListener;
        private String userAgent;

        public Factory(Call.Factory factory) {
            this.callFactory = factory;
        }

        public final Factory setDefaultRequestProperties(Map<String, String> map) {
            this.defaultRequestProperties.clearAndSet(map);
            return this;
        }

        public Factory setUserAgent(String str) {
            this.userAgent = str;
            return this;
        }

        public Factory setCacheControl(CacheControl cacheControl2) {
            this.cacheControl = cacheControl2;
            return this;
        }

        public Factory setContentTypePredicate(Predicate<String> predicate) {
            this.contentTypePredicate = predicate;
            return this;
        }

        public Factory setTransferListener(TransferListener transferListener2) {
            this.transferListener = transferListener2;
            return this;
        }

        public OkHttpDataSource createDataSource() {
            OkHttpDataSource okHttpDataSource = new OkHttpDataSource(this.callFactory, this.userAgent, this.cacheControl, this.defaultRequestProperties, this.contentTypePredicate);
            TransferListener transferListener2 = this.transferListener;
            if (transferListener2 != null) {
                okHttpDataSource.addTransferListener(transferListener2);
            }
            return okHttpDataSource;
        }
    }

    private OkHttpDataSource(Call.Factory factory, String str, CacheControl cacheControl2, HttpDataSource.RequestProperties requestProperties2, Predicate<String> predicate) {
        super(true);
        this.callFactory = (Call.Factory) Assertions.checkNotNull(factory);
        this.userAgent = str;
        this.cacheControl = cacheControl2;
        this.defaultRequestProperties = requestProperties2;
        this.contentTypePredicate = predicate;
        this.requestProperties = new HttpDataSource.RequestProperties();
    }

    public Uri getUri() {
        Response response2 = this.response;
        if (response2 != null) {
            return Uri.parse(response2.request().url().toString());
        }
        DataSpec dataSpec2 = this.dataSpec;
        if (dataSpec2 != null) {
            return dataSpec2.uri;
        }
        return null;
    }

    public int getResponseCode() {
        Response response2 = this.response;
        if (response2 == null) {
            return -1;
        }
        return response2.code();
    }

    public Map<String, List<String>> getResponseHeaders() {
        Response response2 = this.response;
        return response2 == null ? Collections.emptyMap() : response2.headers().toMultimap();
    }

    public void setRequestProperty(String str, String str2) {
        Assertions.checkNotNull(str);
        Assertions.checkNotNull(str2);
        this.requestProperties.set(str, str2);
    }

    public void clearRequestProperty(String str) {
        Assertions.checkNotNull(str);
        this.requestProperties.remove(str);
    }

    public void clearAllRequestProperties() {
        this.requestProperties.clear();
    }

    public long open(DataSpec dataSpec2) throws HttpDataSource.HttpDataSourceException {
        byte[] bArr;
        this.dataSpec = dataSpec2;
        long j = 0;
        this.bytesRead = 0;
        this.bytesToRead = 0;
        transferInitializing(dataSpec2);
        try {
            Response executeCall = executeCall(this.callFactory.newCall(makeRequest(dataSpec2)));
            this.response = executeCall;
            ResponseBody responseBody = (ResponseBody) Assertions.checkNotNull(executeCall.body());
            this.responseByteStream = responseBody.byteStream();
            int code = executeCall.code();
            long j2 = -1;
            if (!executeCall.isSuccessful()) {
                if (code == 416) {
                    if (dataSpec2.position == HttpUtil.getDocumentSize(executeCall.headers().get(HttpHeaders.CONTENT_RANGE))) {
                        this.connectionEstablished = true;
                        transferStarted(dataSpec2);
                        if (dataSpec2.length != -1) {
                            return dataSpec2.length;
                        }
                        return 0;
                    }
                }
                try {
                    bArr = ByteStreams.toByteArray((InputStream) Assertions.checkNotNull(this.responseByteStream));
                } catch (IOException unused) {
                    bArr = Util.EMPTY_BYTE_ARRAY;
                }
                byte[] bArr2 = bArr;
                Map<String, List<String>> multimap = executeCall.headers().toMultimap();
                closeConnectionQuietly();
                throw new HttpDataSource.InvalidResponseCodeException(code, executeCall.message(), code == 416 ? new DataSourceException(2008) : null, multimap, dataSpec2, bArr2);
            }
            MediaType contentType = responseBody.contentType();
            String mediaType = contentType != null ? contentType.toString() : "";
            Predicate<String> predicate = this.contentTypePredicate;
            if (predicate == null || predicate.apply(mediaType)) {
                if (code == 200 && dataSpec2.position != 0) {
                    j = dataSpec2.position;
                }
                if (dataSpec2.length != -1) {
                    this.bytesToRead = dataSpec2.length;
                } else {
                    long contentLength = responseBody.contentLength();
                    if (contentLength != -1) {
                        j2 = contentLength - j;
                    }
                    this.bytesToRead = j2;
                }
                this.connectionEstablished = true;
                transferStarted(dataSpec2);
                try {
                    skipFully(j, dataSpec2);
                    return this.bytesToRead;
                } catch (HttpDataSource.HttpDataSourceException e) {
                    closeConnectionQuietly();
                    throw e;
                }
            } else {
                closeConnectionQuietly();
                throw new HttpDataSource.InvalidContentTypeException(mediaType, dataSpec2);
            }
        } catch (IOException e2) {
            throw HttpDataSource.HttpDataSourceException.createForIOException(e2, dataSpec2, 1);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws HttpDataSource.HttpDataSourceException {
        try {
            return readInternal(bArr, i, i2);
        } catch (IOException e) {
            throw HttpDataSource.HttpDataSourceException.createForIOException(e, (DataSpec) Util.castNonNull(this.dataSpec), 2);
        }
    }

    public void close() {
        if (this.connectionEstablished) {
            this.connectionEstablished = false;
            transferEnded();
            closeConnectionQuietly();
        }
        this.response = null;
        this.dataSpec = null;
    }

    private Request makeRequest(DataSpec dataSpec2) throws HttpDataSource.HttpDataSourceException {
        RequestBody requestBody;
        long j = dataSpec2.position;
        long j2 = dataSpec2.length;
        HttpUrl parse = HttpUrl.parse(dataSpec2.uri.toString());
        if (parse != null) {
            Request.Builder url = new Request.Builder().url(parse);
            CacheControl cacheControl2 = this.cacheControl;
            if (cacheControl2 != null) {
                url.cacheControl(cacheControl2);
            }
            HashMap hashMap = new HashMap();
            HttpDataSource.RequestProperties requestProperties2 = this.defaultRequestProperties;
            if (requestProperties2 != null) {
                hashMap.putAll(requestProperties2.getSnapshot());
            }
            hashMap.putAll(this.requestProperties.getSnapshot());
            hashMap.putAll(dataSpec2.httpRequestHeaders);
            for (Map.Entry entry : hashMap.entrySet()) {
                url.header((String) entry.getKey(), (String) entry.getValue());
            }
            String buildRangeRequestHeader = HttpUtil.buildRangeRequestHeader(j, j2);
            if (buildRangeRequestHeader != null) {
                url.addHeader(HttpHeaders.RANGE, buildRangeRequestHeader);
            }
            String str = this.userAgent;
            if (str != null) {
                url.addHeader(HttpHeaders.USER_AGENT, str);
            }
            if (!dataSpec2.isFlagSet(1)) {
                url.addHeader(HttpHeaders.ACCEPT_ENCODING, InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY);
            }
            if (dataSpec2.httpBody != null) {
                requestBody = RequestBody.create(dataSpec2.httpBody);
            } else {
                requestBody = dataSpec2.httpMethod == 2 ? RequestBody.create(Util.EMPTY_BYTE_ARRAY) : null;
            }
            url.method(dataSpec2.getHttpMethodString(), requestBody);
            return url.build();
        }
        throw new HttpDataSource.HttpDataSourceException("Malformed URL", dataSpec2, 1004, 1);
    }

    private Response executeCall(Call call) throws IOException {
        final SettableFuture create = SettableFuture.create();
        FirebasePerfOkHttpClient.enqueue(call, new Callback() {
            public void onFailure(Call call, IOException iOException) {
                create.setException(iOException);
            }

            public void onResponse(Call call, Response response) {
                create.set(response);
            }
        });
        try {
            return (Response) create.get();
        } catch (InterruptedException unused) {
            call.cancel();
            throw new InterruptedIOException();
        } catch (ExecutionException e) {
            throw new IOException(e);
        }
    }

    private void skipFully(long j, DataSpec dataSpec2) throws HttpDataSource.HttpDataSourceException {
        if (j != 0) {
            byte[] bArr = new byte[4096];
            while (j > 0) {
                try {
                    int read = ((InputStream) Util.castNonNull(this.responseByteStream)).read(bArr, 0, (int) Math.min(j, (long) 4096));
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedIOException();
                    } else if (read != -1) {
                        j -= (long) read;
                        bytesTransferred(read);
                    } else {
                        throw new HttpDataSource.HttpDataSourceException(dataSpec2, 2008, 1);
                    }
                } catch (IOException e) {
                    if (e instanceof HttpDataSource.HttpDataSourceException) {
                        throw ((HttpDataSource.HttpDataSourceException) e);
                    }
                    throw new HttpDataSource.HttpDataSourceException(dataSpec2, 2000, 1);
                }
            }
        }
    }

    private int readInternal(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        long j = this.bytesToRead;
        if (j != -1) {
            long j2 = j - this.bytesRead;
            if (j2 == 0) {
                return -1;
            }
            i2 = (int) Math.min((long) i2, j2);
        }
        int read = ((InputStream) Util.castNonNull(this.responseByteStream)).read(bArr, i, i2);
        if (read == -1) {
            return -1;
        }
        this.bytesRead += (long) read;
        bytesTransferred(read);
        return read;
    }

    private void closeConnectionQuietly() {
        Response response2 = this.response;
        if (response2 != null) {
            ((ResponseBody) Assertions.checkNotNull(response2.body())).close();
        }
        this.responseByteStream = null;
    }
}
