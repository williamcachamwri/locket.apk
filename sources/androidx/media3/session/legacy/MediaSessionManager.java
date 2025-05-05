package androidx.media3.session.legacy;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.session.MediaSessionManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.util.ObjectsCompat;

public final class MediaSessionManager {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    static final String TAG = "MediaSessionManager";
    private static final Object sLock = new Object();
    private static volatile MediaSessionManager sSessionManager;
    MediaSessionManagerImpl mImpl;

    interface MediaSessionManagerImpl {
        Context getContext();

        boolean isTrustedForMediaControl(RemoteUserInfoImpl remoteUserInfoImpl);
    }

    interface RemoteUserInfoImpl {
        String getPackageName();

        int getPid();

        int getUid();
    }

    public static MediaSessionManager getSessionManager(Context context) {
        MediaSessionManager mediaSessionManager;
        if (context != null) {
            synchronized (sLock) {
                if (sSessionManager == null) {
                    sSessionManager = new MediaSessionManager(context.getApplicationContext());
                }
                mediaSessionManager = sSessionManager;
            }
            return mediaSessionManager;
        }
        throw new IllegalArgumentException("context cannot be null");
    }

    private MediaSessionManager(Context context) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.mImpl = new MediaSessionManagerImplApi28(context);
        } else {
            this.mImpl = new MediaSessionManagerImplApi21(context);
        }
    }

    public boolean isTrustedForMediaControl(RemoteUserInfo remoteUserInfo) {
        if (remoteUserInfo != null) {
            return this.mImpl.isTrustedForMediaControl(remoteUserInfo.mImpl);
        }
        throw new IllegalArgumentException("userInfo should not be null");
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.mImpl.getContext();
    }

    public static final class RemoteUserInfo {
        public static final String LEGACY_CONTROLLER = "android.media.session.MediaController";
        public static final int UNKNOWN_PID = -1;
        public static final int UNKNOWN_UID = -1;
        RemoteUserInfoImpl mImpl;

        public RemoteUserInfo(String str, int i, int i2) {
            if (str == null) {
                throw new NullPointerException("package shouldn't be null");
            } else if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("packageName should be nonempty");
            } else if (Build.VERSION.SDK_INT >= 28) {
                this.mImpl = new MediaSessionManagerImplApi28.RemoteUserInfoImplApi28(str, i, i2);
            } else {
                this.mImpl = new MediaSessionManagerImplBase.RemoteUserInfoImplBase(str, i, i2);
            }
        }

        public RemoteUserInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
            String packageName = MediaSessionManagerImplApi28.RemoteUserInfoImplApi28.getPackageName(remoteUserInfo);
            if (packageName == null) {
                throw new NullPointerException("package shouldn't be null");
            } else if (!TextUtils.isEmpty(packageName)) {
                this.mImpl = new MediaSessionManagerImplApi28.RemoteUserInfoImplApi28(remoteUserInfo);
            } else {
                throw new IllegalArgumentException("packageName should be nonempty");
            }
        }

        public String getPackageName() {
            return this.mImpl.getPackageName();
        }

        public int getPid() {
            return this.mImpl.getPid();
        }

        public int getUid() {
            return this.mImpl.getUid();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof RemoteUserInfo)) {
                return false;
            }
            return this.mImpl.equals(((RemoteUserInfo) obj).mImpl);
        }

        public int hashCode() {
            return this.mImpl.hashCode();
        }
    }

    private static class MediaSessionManagerImplBase implements MediaSessionManagerImpl {
        private static final boolean DEBUG = MediaSessionManager.DEBUG;
        private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
        private static final String PERMISSION_MEDIA_CONTENT_CONTROL = "android.permission.MEDIA_CONTENT_CONTROL";
        private static final String PERMISSION_STATUS_BAR_SERVICE = "android.permission.STATUS_BAR_SERVICE";
        private static final String TAG = "MediaSessionManager";
        ContentResolver mContentResolver;
        Context mContext;

        MediaSessionManagerImplBase(Context context) {
            this.mContext = context;
            this.mContentResolver = context.getContentResolver();
        }

        public Context getContext() {
            return this.mContext;
        }

        public boolean isTrustedForMediaControl(RemoteUserInfoImpl remoteUserInfoImpl) {
            try {
                if (this.mContext.getPackageManager().getApplicationInfo(remoteUserInfoImpl.getPackageName(), 0) == null) {
                    return false;
                }
                if (isPermissionGranted(remoteUserInfoImpl, PERMISSION_STATUS_BAR_SERVICE) || isPermissionGranted(remoteUserInfoImpl, PERMISSION_MEDIA_CONTENT_CONTROL) || remoteUserInfoImpl.getUid() == 1000 || isEnabledNotificationListener(remoteUserInfoImpl)) {
                    return true;
                }
                return false;
            } catch (PackageManager.NameNotFoundException unused) {
                if (DEBUG) {
                    Log.d(TAG, "Package " + remoteUserInfoImpl.getPackageName() + " doesn't exist");
                }
                return false;
            }
        }

        private boolean isPermissionGranted(RemoteUserInfoImpl remoteUserInfoImpl, String str) {
            if (remoteUserInfoImpl.getPid() < 0) {
                if (this.mContext.getPackageManager().checkPermission(str, remoteUserInfoImpl.getPackageName()) == 0) {
                    return true;
                }
                return false;
            } else if (this.mContext.checkPermission(str, remoteUserInfoImpl.getPid(), remoteUserInfoImpl.getUid()) == 0) {
                return true;
            } else {
                return false;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isEnabledNotificationListener(RemoteUserInfoImpl remoteUserInfoImpl) {
            String string = Settings.Secure.getString(this.mContentResolver, ENABLED_NOTIFICATION_LISTENERS);
            if (string != null) {
                String[] split = string.split(":");
                for (String unflattenFromString : split) {
                    ComponentName unflattenFromString2 = ComponentName.unflattenFromString(unflattenFromString);
                    if (unflattenFromString2 != null && unflattenFromString2.getPackageName().equals(remoteUserInfoImpl.getPackageName())) {
                        return true;
                    }
                }
            }
            return false;
        }

        static class RemoteUserInfoImplBase implements RemoteUserInfoImpl {
            private String mPackageName;
            private int mPid;
            private int mUid;

            RemoteUserInfoImplBase(String str, int i, int i2) {
                this.mPackageName = str;
                this.mPid = i;
                this.mUid = i2;
            }

            public String getPackageName() {
                return this.mPackageName;
            }

            public int getPid() {
                return this.mPid;
            }

            public int getUid() {
                return this.mUid;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof RemoteUserInfoImplBase)) {
                    return false;
                }
                RemoteUserInfoImplBase remoteUserInfoImplBase = (RemoteUserInfoImplBase) obj;
                if (this.mPid < 0 || remoteUserInfoImplBase.mPid < 0) {
                    if (!TextUtils.equals(this.mPackageName, remoteUserInfoImplBase.mPackageName) || this.mUid != remoteUserInfoImplBase.mUid) {
                        return false;
                    }
                    return true;
                } else if (TextUtils.equals(this.mPackageName, remoteUserInfoImplBase.mPackageName) && this.mPid == remoteUserInfoImplBase.mPid && this.mUid == remoteUserInfoImplBase.mUid) {
                    return true;
                } else {
                    return false;
                }
            }

            public int hashCode() {
                return ObjectsCompat.hash(this.mPackageName, Integer.valueOf(this.mUid));
            }
        }
    }

    private static class MediaSessionManagerImplApi21 extends MediaSessionManagerImplBase {
        MediaSessionManagerImplApi21(Context context) {
            super(context);
            this.mContext = context;
        }

        public boolean isTrustedForMediaControl(RemoteUserInfoImpl remoteUserInfoImpl) {
            return hasMediaControlPermission(remoteUserInfoImpl) || super.isTrustedForMediaControl(remoteUserInfoImpl);
        }

        private boolean hasMediaControlPermission(RemoteUserInfoImpl remoteUserInfoImpl) {
            return getContext().checkPermission("android.permission.MEDIA_CONTENT_CONTROL", remoteUserInfoImpl.getPid(), remoteUserInfoImpl.getUid()) == 0;
        }
    }

    private static final class MediaSessionManagerImplApi28 extends MediaSessionManagerImplApi21 {
        android.media.session.MediaSessionManager mObject;

        MediaSessionManagerImplApi28(Context context) {
            super(context);
            this.mObject = (android.media.session.MediaSessionManager) context.getSystemService("media_session");
        }

        public boolean isTrustedForMediaControl(RemoteUserInfoImpl remoteUserInfoImpl) {
            return super.isTrustedForMediaControl(remoteUserInfoImpl);
        }

        private static final class RemoteUserInfoImplApi28 extends MediaSessionManagerImplBase.RemoteUserInfoImplBase {
            final MediaSessionManager.RemoteUserInfo mObject;

            RemoteUserInfoImplApi28(String str, int i, int i2) {
                super(str, i, i2);
                this.mObject = new MediaSessionManager.RemoteUserInfo(str, i, i2);
            }

            RemoteUserInfoImplApi28(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
                super(remoteUserInfo.getPackageName(), remoteUserInfo.getPid(), remoteUserInfo.getUid());
                this.mObject = remoteUserInfo;
            }

            static String getPackageName(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
                return remoteUserInfo.getPackageName();
            }
        }
    }
}
