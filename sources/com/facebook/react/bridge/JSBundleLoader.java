package com.facebook.react.bridge;

import android.content.Context;
import com.facebook.react.common.DebugServerException;
import java.util.Objects;

public abstract class JSBundleLoader {
    public abstract String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate);

    public static JSBundleLoader createAssetLoader(final Context context, final String str, final boolean z) {
        return new JSBundleLoader() {
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                jSBundleLoaderDelegate.loadScriptFromAssets(context.getAssets(), str, z);
                return str;
            }
        };
    }

    public static JSBundleLoader createFileLoader(String str) {
        return createFileLoader(str, str, false);
    }

    public static JSBundleLoader createFileLoader(final String str, final String str2, final boolean z) {
        return new JSBundleLoader() {
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                jSBundleLoaderDelegate.loadScriptFromFile(str, str2, z);
                return str;
            }
        };
    }

    public static JSBundleLoader createCachedBundleFromNetworkLoader(final String str, final String str2) {
        return new JSBundleLoader() {
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                try {
                    jSBundleLoaderDelegate.loadScriptFromFile(str2, str, false);
                    return str;
                } catch (Exception e) {
                    throw DebugServerException.makeGeneric(str, Objects.toString(e.getMessage(), ""), e);
                }
            }
        };
    }

    public static JSBundleLoader createCachedSplitBundleFromNetworkLoader(final String str, final String str2) {
        return new JSBundleLoader() {
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                try {
                    jSBundleLoaderDelegate.loadSplitBundleFromFile(str2, str);
                    return str;
                } catch (Exception e) {
                    throw DebugServerException.makeGeneric(str, Objects.toString(e.getMessage(), ""), e);
                }
            }
        };
    }

    public static JSBundleLoader createRemoteDebuggerBundleLoader(final String str, final String str2) {
        return new JSBundleLoader() {
            public String loadScript(JSBundleLoaderDelegate jSBundleLoaderDelegate) {
                jSBundleLoaderDelegate.setSourceURLs(str2, str);
                return str2;
            }
        };
    }
}
