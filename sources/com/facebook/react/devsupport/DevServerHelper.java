package com.facebook.react.devsupport;

import android.net.Uri;
import android.os.AsyncTask;
import com.facebook.common.logging.FLog;
import com.facebook.hermes.intl.Constants;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.BundleDownloader;
import com.facebook.react.devsupport.InspectorPackagerConnection;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.FileIoHandler;
import com.facebook.react.packagerconnection.JSPackagerClient;
import com.facebook.react.packagerconnection.NotificationOnlyHandler;
import com.facebook.react.packagerconnection.PackagerConnectionSettings;
import com.facebook.react.packagerconnection.ReconnectingWebSocket;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.packagerconnection.RequestOnlyHandler;
import com.facebook.react.packagerconnection.Responder;
import com.facebook.react.util.RNLog;
import com.google.firebase.perf.network.FirebasePerfOkHttpClient;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

public class DevServerHelper {
    private static final String DEBUGGER_MSG_DISABLE = "{ \"id\":1,\"method\":\"Debugger.disable\" }";
    private static final int HTTP_CONNECT_TIMEOUT_MS = 5000;
    public static final String RELOAD_APP_EXTRA_JS_PROXY = "jsproxy";
    private final BundleDownloader mBundleDownloader;
    /* access modifiers changed from: private */
    public final InspectorPackagerConnection.BundleStatusProvider mBundlerStatusProvider;
    private final OkHttpClient mClient;
    /* access modifiers changed from: private */
    public InspectorPackagerConnection mInspectorPackagerConnection;
    /* access modifiers changed from: private */
    public final String mPackageName;
    /* access modifiers changed from: private */
    public JSPackagerClient mPackagerClient;
    /* access modifiers changed from: private */
    public final PackagerConnectionSettings mPackagerConnectionSettings;
    private final PackagerStatusCheck mPackagerStatusCheck;
    private final DeveloperSettings mSettings;

    public interface PackagerCommandListener {
        Map<String, RequestHandler> customCommandHandlers();

        void onCaptureHeapCommand(Responder responder);

        void onPackagerConnected();

        void onPackagerDevMenuCommand();

        void onPackagerDisconnected();

        void onPackagerReloadCommand();
    }

    private enum BundleType {
        BUNDLE("bundle"),
        MAP("map");
        
        private final String mTypeID;

        private BundleType(String str) {
            this.mTypeID = str;
        }

        public String typeID() {
            return this.mTypeID;
        }
    }

    public DevServerHelper(DeveloperSettings developerSettings, String str, InspectorPackagerConnection.BundleStatusProvider bundleStatusProvider, PackagerConnectionSettings packagerConnectionSettings) {
        this.mSettings = developerSettings;
        this.mPackagerConnectionSettings = packagerConnectionSettings;
        this.mBundlerStatusProvider = bundleStatusProvider;
        OkHttpClient build = new OkHttpClient.Builder().connectTimeout(5000, TimeUnit.MILLISECONDS).readTimeout(0, TimeUnit.MILLISECONDS).writeTimeout(0, TimeUnit.MILLISECONDS).build();
        this.mClient = build;
        this.mBundleDownloader = new BundleDownloader(build);
        this.mPackagerStatusCheck = new PackagerStatusCheck(build);
        this.mPackageName = str;
    }

    public void openPackagerConnection(final String str, final PackagerCommandListener packagerCommandListener) {
        if (this.mPackagerClient != null) {
            FLog.w(ReactConstants.TAG, "Packager connection already open, nooping.");
        } else {
            new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                public Void doInBackground(Void... voidArr) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("reload", new NotificationOnlyHandler() {
                        public void onNotification(Object obj) {
                            packagerCommandListener.onPackagerReloadCommand();
                        }
                    });
                    hashMap.put("devMenu", new NotificationOnlyHandler() {
                        public void onNotification(Object obj) {
                            packagerCommandListener.onPackagerDevMenuCommand();
                        }
                    });
                    hashMap.put("captureHeap", new RequestOnlyHandler() {
                        public void onRequest(Object obj, Responder responder) {
                            packagerCommandListener.onCaptureHeapCommand(responder);
                        }
                    });
                    Map<String, RequestHandler> customCommandHandlers = packagerCommandListener.customCommandHandlers();
                    if (customCommandHandlers != null) {
                        hashMap.putAll(customCommandHandlers);
                    }
                    hashMap.putAll(new FileIoHandler().handlers());
                    DevServerHelper.this.mPackagerClient = new JSPackagerClient(str, DevServerHelper.this.mPackagerConnectionSettings, hashMap, new ReconnectingWebSocket.ConnectionCallback() {
                        public void onConnected() {
                            packagerCommandListener.onPackagerConnected();
                        }

                        public void onDisconnected() {
                            packagerCommandListener.onPackagerDisconnected();
                        }
                    });
                    DevServerHelper.this.mPackagerClient.init();
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void closePackagerConnection() {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                if (DevServerHelper.this.mPackagerClient != null) {
                    DevServerHelper.this.mPackagerClient.close();
                    DevServerHelper.this.mPackagerClient = null;
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void openInspectorConnection() {
        if (this.mInspectorPackagerConnection != null) {
            FLog.w(ReactConstants.TAG, "Inspector connection already open, nooping.");
        } else {
            new AsyncTask<Void, Void, Void>() {
                /* access modifiers changed from: protected */
                public Void doInBackground(Void... voidArr) {
                    DevServerHelper.this.mInspectorPackagerConnection = new InspectorPackagerConnection(DevServerHelper.this.getInspectorDeviceUrl(), DevServerHelper.this.mPackageName, DevServerHelper.this.mBundlerStatusProvider);
                    DevServerHelper.this.mInspectorPackagerConnection.connect();
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void disableDebugger() {
        InspectorPackagerConnection inspectorPackagerConnection = this.mInspectorPackagerConnection;
        if (inspectorPackagerConnection != null) {
            inspectorPackagerConnection.sendEventToAllConnections(DEBUGGER_MSG_DISABLE);
        }
    }

    public void closeInspectorConnection() {
        new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                if (DevServerHelper.this.mInspectorPackagerConnection != null) {
                    DevServerHelper.this.mInspectorPackagerConnection.closeQuietly();
                    DevServerHelper.this.mInspectorPackagerConnection = null;
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public String getWebsocketProxyURL() {
        return String.format(Locale.US, "ws://%s/debugger-proxy?role=client", new Object[]{this.mPackagerConnectionSettings.getDebugServerHost()});
    }

    private static String getSHA256(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance(MessageDigestAlgorithms.SHA_256);
            instance.reset();
            try {
                byte[] digest = instance.digest(str.getBytes("UTF-8"));
                return String.format("%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x%02x", new Object[]{Byte.valueOf(digest[0]), Byte.valueOf(digest[1]), Byte.valueOf(digest[2]), Byte.valueOf(digest[3]), Byte.valueOf(digest[4]), Byte.valueOf(digest[5]), Byte.valueOf(digest[6]), Byte.valueOf(digest[7]), Byte.valueOf(digest[8]), Byte.valueOf(digest[9]), Byte.valueOf(digest[10]), Byte.valueOf(digest[11]), Byte.valueOf(digest[12]), Byte.valueOf(digest[13]), Byte.valueOf(digest[14]), Byte.valueOf(digest[15]), Byte.valueOf(digest[16]), Byte.valueOf(digest[17]), Byte.valueOf(digest[18]), Byte.valueOf(digest[19])});
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError("This environment doesn't support UTF-8 encoding", e);
            }
        } catch (NoSuchAlgorithmException e2) {
            throw new AssertionError("Could not get standard SHA-256 algorithm", e2);
        }
    }

    private String getInspectorDeviceId() {
        return getSHA256(String.format(Locale.US, "android-%s-%s", new Object[]{this.mPackageName, "android_id"}));
    }

    /* access modifiers changed from: private */
    public String getInspectorDeviceUrl() {
        return String.format(Locale.US, "http://%s/inspector/device?name=%s&app=%s&device=%s", new Object[]{this.mPackagerConnectionSettings.getInspectorServerHost(), Uri.encode(AndroidInfoHelpers.getFriendlyDeviceName()), Uri.encode(this.mPackageName), Uri.encode(getInspectorDeviceId())});
    }

    public void downloadBundleFromURL(DevBundleDownloadListener devBundleDownloadListener, File file, String str, BundleDownloader.BundleInfo bundleInfo) {
        this.mBundleDownloader.downloadBundleFromURL(devBundleDownloadListener, file, str, bundleInfo);
    }

    public void downloadBundleFromURL(DevBundleDownloadListener devBundleDownloadListener, File file, String str, BundleDownloader.BundleInfo bundleInfo, Request.Builder builder) {
        this.mBundleDownloader.downloadBundleFromURL(devBundleDownloadListener, file, str, bundleInfo, builder);
    }

    private String getHostForJSProxy() {
        String str = (String) Assertions.assertNotNull(this.mPackagerConnectionSettings.getDebugServerHost());
        int lastIndexOf = str.lastIndexOf(58);
        if (lastIndexOf > -1) {
            return AndroidInfoHelpers.DEVICE_LOCALHOST + str.substring(lastIndexOf);
        }
        return AndroidInfoHelpers.DEVICE_LOCALHOST;
    }

    private boolean getDevMode() {
        return this.mSettings.isJSDevModeEnabled();
    }

    private boolean getJSMinifyMode() {
        return this.mSettings.isJSMinifyEnabled();
    }

    private String createBundleURL(String str, BundleType bundleType, String str2) {
        return createBundleURL(str, bundleType, str2, false, true);
    }

    private String createSplitBundleURL(String str, String str2) {
        return createBundleURL(str, BundleType.BUNDLE, str2, true, false);
    }

    private String createBundleURL(String str, BundleType bundleType, String str2, boolean z, boolean z2) {
        boolean devMode = getDevMode();
        Locale locale = Locale.US;
        Object[] objArr = new Object[9];
        objArr[0] = str2;
        objArr[1] = str;
        objArr[2] = bundleType.typeID();
        objArr[3] = Boolean.valueOf(devMode);
        objArr[4] = Boolean.valueOf(devMode);
        objArr[5] = Boolean.valueOf(getJSMinifyMode());
        objArr[6] = this.mPackageName;
        String str3 = "true";
        objArr[7] = z ? str3 : Constants.CASEFIRST_FALSE;
        if (!z2) {
            str3 = Constants.CASEFIRST_FALSE;
        }
        objArr[8] = str3;
        return String.format(locale, "http://%s/%s.%s?platform=android&dev=%s&lazy=%s&minify=%s&app=%s&modulesOnly=%s&runModule=%s", objArr);
    }

    private String createBundleURL(String str, BundleType bundleType) {
        return createBundleURL(str, bundleType, this.mPackagerConnectionSettings.getDebugServerHost());
    }

    private static String createResourceURL(String str, String str2) {
        return String.format(Locale.US, "http://%s/%s", new Object[]{str, str2});
    }

    public String getDevServerBundleURL(String str) {
        return createBundleURL(str, BundleType.BUNDLE, this.mPackagerConnectionSettings.getDebugServerHost());
    }

    public String getDevServerSplitBundleURL(String str) {
        return createSplitBundleURL(str, this.mPackagerConnectionSettings.getDebugServerHost());
    }

    public void isPackagerRunning(PackagerStatusCallback packagerStatusCallback) {
        String debugServerHost = this.mPackagerConnectionSettings.getDebugServerHost();
        if (debugServerHost == null) {
            FLog.w(ReactConstants.TAG, "No packager host configured.");
            packagerStatusCallback.onPackagerStatusFetched(false);
            return;
        }
        this.mPackagerStatusCheck.run(debugServerHost, packagerStatusCallback);
    }

    private String createLaunchJSDevtoolsCommandUrl() {
        return String.format(Locale.US, "http://%s/launch-js-devtools", new Object[]{this.mPackagerConnectionSettings.getDebugServerHost()});
    }

    public void launchJSDevtools() {
        FirebasePerfOkHttpClient.enqueue(this.mClient.newCall(new Request.Builder().url(createLaunchJSDevtoolsCommandUrl()).build()), new Callback() {
            public void onFailure(Call call, IOException iOException) {
            }

            public void onResponse(Call call, Response response) {
            }
        });
    }

    public String getSourceMapUrl(String str) {
        return createBundleURL(str, BundleType.MAP);
    }

    public String getSourceUrl(String str) {
        return createBundleURL(str, BundleType.BUNDLE);
    }

    public String getJSBundleURLForRemoteDebugging(String str) {
        return createBundleURL(str, BundleType.BUNDLE, getHostForJSProxy());
    }

    public File downloadBundleResourceFromUrlSync(String str, File file) {
        Sink sink;
        try {
            Response execute = FirebasePerfOkHttpClient.execute(this.mClient.newCall(new Request.Builder().url(createResourceURL(this.mPackagerConnectionSettings.getDebugServerHost(), str)).build()));
            try {
                if (!execute.isSuccessful()) {
                    if (execute != null) {
                        execute.close();
                    }
                    return null;
                }
                sink = Okio.sink(file);
                Okio.buffer((Source) execute.body().source()).readAll(sink);
                if (sink != null) {
                    sink.close();
                }
                if (execute != null) {
                    execute.close();
                }
                return file;
            } catch (Throwable th) {
                if (execute != null) {
                    execute.close();
                }
                throw th;
            }
            throw th;
        } catch (Exception e) {
            FLog.e(ReactConstants.TAG, "Failed to fetch resource synchronously - resourcePath: \"%s\", outputFile: \"%s\"", str, file.getAbsolutePath(), e);
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    public void openDebugger(final ReactContext reactContext, final String str) {
        FirebasePerfOkHttpClient.enqueue(this.mClient.newCall(new Request.Builder().url(String.format(Locale.US, "http://%s/open-debugger?appId=%s&device=%s", new Object[]{this.mPackagerConnectionSettings.getInspectorServerHost(), Uri.encode(this.mPackageName), Uri.encode(getInspectorDeviceId())})).method("POST", RequestBody.create((MediaType) null, "")).build()), new Callback() {
            public void onResponse(Call call, Response response) {
            }

            public void onFailure(Call call, IOException iOException) {
                RNLog.w(reactContext, str);
            }
        });
    }
}
