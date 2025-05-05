package androidx.media3.exoplayer.ima;

import android.net.Uri;
import android.text.TextUtils;
import androidx.media3.common.C;
import androidx.media3.common.util.Assertions;
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory;
import com.google.ads.interactivemedia.v3.api.StreamRequest;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import java.util.HashMap;
import java.util.Map;

public final class ImaServerSideAdInsertionUriBuilder {
    private static final String ADS_ID = "adsId";
    private static final String AD_TAG_PARAMETERS = "adTagParameters";
    private static final String API_KEY = "apiKey";
    private static final String ASSET_KEY = "assetKey";
    private static final String AUTH_TOKEN = "authToken";
    private static final String CONTENT_SOURCE_ID = "contentSourceId";
    private static final String CONTENT_URL = "contentUrl";
    public static final int DEFAULT_LOAD_VIDEO_TIMEOUT_MS = 10000;
    private static final String FORMAT = "format";
    static final String IMA_AUTHORITY = "dai.google.com";
    private static final String LOAD_VIDEO_TIMEOUT_MS = "loadVideoTimeoutMs";
    private static final String MANIFEST_SUFFIX = "manifestSuffix";
    private static final String STREAM_ACTIVITY_MONITOR_ID = "streamActivityMonitorId";
    private static final String VIDEO_ID = "videoId";
    private ImmutableMap<String, String> adTagParameters = ImmutableMap.of();
    private String adsId;
    private String apiKey;
    private String assetKey;
    private String authToken;
    private String contentSourceId;
    private String contentUrl;
    public int format = 4;
    private int loadVideoTimeoutMs = 10000;
    private String manifestSuffix;
    private String streamActivityMonitorId;
    private String videoId;

    public ImaServerSideAdInsertionUriBuilder setAdsId(String str) {
        this.adsId = str;
        return this;
    }

    public ImaServerSideAdInsertionUriBuilder setAssetKey(String str) {
        this.assetKey = str;
        return this;
    }

    public ImaServerSideAdInsertionUriBuilder setAuthToken(String str) {
        this.authToken = str;
        return this;
    }

    public ImaServerSideAdInsertionUriBuilder setContentSourceId(String str) {
        this.contentSourceId = str;
        return this;
    }

    public ImaServerSideAdInsertionUriBuilder setVideoId(String str) {
        this.videoId = str;
        return this;
    }

    public ImaServerSideAdInsertionUriBuilder setFormat(int i) {
        Assertions.checkArgument(i == 0 || i == 2);
        this.format = i;
        return this;
    }

    public ImaServerSideAdInsertionUriBuilder setApiKey(String str) {
        this.apiKey = str;
        return this;
    }

    public ImaServerSideAdInsertionUriBuilder setStreamActivityMonitorId(String str) {
        this.streamActivityMonitorId = str;
        return this;
    }

    public ImaServerSideAdInsertionUriBuilder setAdTagParameters(Map<String, String> map) {
        this.adTagParameters = ImmutableMap.copyOf(map);
        return this;
    }

    public ImaServerSideAdInsertionUriBuilder setManifestSuffix(String str) {
        this.manifestSuffix = str;
        return this;
    }

    public ImaServerSideAdInsertionUriBuilder setContentUrl(String str) {
        this.contentUrl = str;
        return this;
    }

    public ImaServerSideAdInsertionUriBuilder setLoadVideoTimeoutMs(int i) {
        this.loadVideoTimeoutMs = i;
        return this;
    }

    public Uri build() {
        boolean z = true;
        Assertions.checkState((TextUtils.isEmpty(this.assetKey) && !TextUtils.isEmpty(this.contentSourceId) && !TextUtils.isEmpty(this.videoId)) || (!TextUtils.isEmpty(this.assetKey) && TextUtils.isEmpty(this.contentSourceId) && TextUtils.isEmpty(this.videoId)));
        if (this.format == 4) {
            z = false;
        }
        Assertions.checkState(z);
        String str = this.adsId;
        if (str == null && (str = this.assetKey) == null) {
            str = (String) Assertions.checkNotNull(this.videoId);
        }
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(C.SSAI_SCHEME);
        builder.authority(IMA_AUTHORITY);
        builder.appendQueryParameter(ADS_ID, str);
        int i = this.loadVideoTimeoutMs;
        if (i != 10000) {
            builder.appendQueryParameter(LOAD_VIDEO_TIMEOUT_MS, String.valueOf(i));
        }
        String str2 = this.assetKey;
        if (str2 != null) {
            builder.appendQueryParameter(ASSET_KEY, str2);
        }
        String str3 = this.apiKey;
        if (str3 != null) {
            builder.appendQueryParameter("apiKey", str3);
        }
        String str4 = this.contentSourceId;
        if (str4 != null) {
            builder.appendQueryParameter(CONTENT_SOURCE_ID, str4);
        }
        String str5 = this.videoId;
        if (str5 != null) {
            builder.appendQueryParameter(VIDEO_ID, str5);
        }
        String str6 = this.manifestSuffix;
        if (str6 != null) {
            builder.appendQueryParameter(MANIFEST_SUFFIX, str6);
        }
        String str7 = this.contentUrl;
        if (str7 != null) {
            builder.appendQueryParameter(CONTENT_URL, str7);
        }
        String str8 = this.authToken;
        if (str8 != null) {
            builder.appendQueryParameter(AUTH_TOKEN, str8);
        }
        String str9 = this.streamActivityMonitorId;
        if (str9 != null) {
            builder.appendQueryParameter(STREAM_ACTIVITY_MONITOR_ID, str9);
        }
        if (!this.adTagParameters.isEmpty()) {
            Uri.Builder builder2 = new Uri.Builder();
            UnmodifiableIterator<Map.Entry<String, String>> it = this.adTagParameters.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                builder2.appendQueryParameter((String) next.getKey(), (String) next.getValue());
            }
            builder.appendQueryParameter(AD_TAG_PARAMETERS, builder2.build().toString());
        }
        builder.appendQueryParameter(FORMAT, String.valueOf(this.format));
        return builder.build();
    }

    static boolean isLiveStream(Uri uri) {
        return !TextUtils.isEmpty(uri.getQueryParameter(ASSET_KEY));
    }

    static String getAdsId(Uri uri) {
        return (String) Assertions.checkNotNull(uri.getQueryParameter(ADS_ID));
    }

    static int getLoadVideoTimeoutMs(Uri uri) {
        String queryParameter = uri.getQueryParameter(LOAD_VIDEO_TIMEOUT_MS);
        if (TextUtils.isEmpty(queryParameter)) {
            return 10000;
        }
        return Integer.parseInt(queryParameter);
    }

    static StreamRequest createStreamRequest(Uri uri) {
        StreamRequest streamRequest;
        if (!C.SSAI_SCHEME.equals(uri.getScheme()) || !IMA_AUTHORITY.equals(uri.getAuthority())) {
            throw new IllegalArgumentException("Invalid URI scheme or authority.");
        }
        String queryParameter = uri.getQueryParameter(ASSET_KEY);
        String queryParameter2 = uri.getQueryParameter("apiKey");
        String queryParameter3 = uri.getQueryParameter(CONTENT_SOURCE_ID);
        String queryParameter4 = uri.getQueryParameter(VIDEO_ID);
        if (!TextUtils.isEmpty(queryParameter)) {
            streamRequest = ImaSdkFactory.getInstance().createLiveStreamRequest(queryParameter, queryParameter2);
        } else {
            streamRequest = ImaSdkFactory.getInstance().createVodStreamRequest((String) Assertions.checkNotNull(queryParameter3), (String) Assertions.checkNotNull(queryParameter4), queryParameter2);
        }
        int parseInt = Integer.parseInt(uri.getQueryParameter(FORMAT));
        if (parseInt == 0) {
            streamRequest.setFormat(StreamRequest.StreamFormat.DASH);
        } else if (parseInt == 2) {
            streamRequest.setFormat(StreamRequest.StreamFormat.HLS);
        } else {
            throw new IllegalArgumentException("Unsupported stream format:" + parseInt);
        }
        String queryParameter5 = uri.getQueryParameter(AD_TAG_PARAMETERS);
        if (!TextUtils.isEmpty(queryParameter5)) {
            HashMap hashMap = new HashMap();
            Uri parse = Uri.parse(queryParameter5);
            for (String next : parse.getQueryParameterNames()) {
                String queryParameter6 = parse.getQueryParameter(next);
                if (!TextUtils.isEmpty(queryParameter6)) {
                    hashMap.put(next, queryParameter6);
                }
            }
            streamRequest.setAdTagParameters(hashMap);
        }
        String queryParameter7 = uri.getQueryParameter(MANIFEST_SUFFIX);
        if (queryParameter7 != null) {
            streamRequest.setManifestSuffix(queryParameter7);
        }
        String queryParameter8 = uri.getQueryParameter(CONTENT_URL);
        if (queryParameter8 != null) {
            streamRequest.setContentUrl(queryParameter8);
        }
        String queryParameter9 = uri.getQueryParameter(AUTH_TOKEN);
        if (queryParameter9 != null) {
            streamRequest.setAuthToken(queryParameter9);
        }
        String queryParameter10 = uri.getQueryParameter(STREAM_ACTIVITY_MONITOR_ID);
        if (queryParameter10 != null) {
            streamRequest.setStreamActivityMonitorId(queryParameter10);
        }
        return streamRequest;
    }
}
