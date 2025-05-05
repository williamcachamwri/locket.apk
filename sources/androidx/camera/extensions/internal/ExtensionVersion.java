package androidx.camera.extensions.internal;

import androidx.camera.core.Logger;
import androidx.camera.extensions.impl.ExtensionVersionImpl;

public abstract class ExtensionVersion {
    private static final String TAG = "ExtenderVersion";
    private static volatile ExtensionVersion sExtensionVersion;

    /* access modifiers changed from: package-private */
    public abstract Version getVersionObject();

    /* access modifiers changed from: package-private */
    public abstract boolean isAdvancedExtenderSupportedInternal();

    public static void injectInstance(ExtensionVersion extensionVersion) {
        sExtensionVersion = extensionVersion;
    }

    private static ExtensionVersion getInstance() {
        if (sExtensionVersion != null) {
            return sExtensionVersion;
        }
        synchronized (ExtensionVersion.class) {
            if (sExtensionVersion == null) {
                try {
                    sExtensionVersion = new VendorExtenderVersioning();
                } catch (NoClassDefFoundError unused) {
                    Logger.d(TAG, "No versioning extender found. Falling back to default.");
                    sExtensionVersion = new DefaultExtenderVersioning();
                }
            }
        }
        return sExtensionVersion;
    }

    public static boolean isExtensionVersionSupported() {
        return getInstance().getVersionObject() != null;
    }

    public static Version getRuntimeVersion() {
        return getInstance().getVersionObject();
    }

    public static boolean isAdvancedExtenderSupported() {
        return getInstance().isAdvancedExtenderSupportedInternal();
    }

    public static boolean isMinimumCompatibleVersion(Version version) {
        return getRuntimeVersion().compareTo(version.getMajor(), version.getMinor()) >= 0;
    }

    public static boolean isMaximumCompatibleVersion(Version version) {
        return getRuntimeVersion().compareTo(version.getMajor(), version.getMinor()) <= 0;
    }

    private static class VendorExtenderVersioning extends ExtensionVersion {
        private static ExtensionVersionImpl sImpl;
        private Version mRuntimeVersion;

        VendorExtenderVersioning() {
            if (sImpl == null) {
                sImpl = new ExtensionVersionImpl();
            }
            Version parse = Version.parse(sImpl.checkApiVersion(ClientVersion.getCurrentVersion().toVersionString()));
            if (parse != null && ClientVersion.getCurrentVersion().getVersion().getMajor() == parse.getMajor()) {
                this.mRuntimeVersion = parse;
            }
            Logger.d(ExtensionVersion.TAG, "Selected vendor runtime: " + this.mRuntimeVersion);
        }

        /* access modifiers changed from: package-private */
        public Version getVersionObject() {
            return this.mRuntimeVersion;
        }

        /* access modifiers changed from: package-private */
        public boolean isAdvancedExtenderSupportedInternal() {
            try {
                return sImpl.isAdvancedExtenderImplemented();
            } catch (NoSuchMethodError unused) {
                return false;
            }
        }
    }

    private static class DefaultExtenderVersioning extends ExtensionVersion {
        /* access modifiers changed from: package-private */
        public Version getVersionObject() {
            return null;
        }

        /* access modifiers changed from: package-private */
        public boolean isAdvancedExtenderSupportedInternal() {
            return false;
        }

        DefaultExtenderVersioning() {
        }
    }
}
