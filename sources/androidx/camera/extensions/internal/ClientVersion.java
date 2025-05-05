package androidx.camera.extensions.internal;

public class ClientVersion {
    private static ClientVersion sCurrent = new ClientVersion("1.4.0");
    private final Version mVersion;

    public static ClientVersion getCurrentVersion() {
        return sCurrent;
    }

    public static void setCurrentVersion(ClientVersion clientVersion) {
        sCurrent = clientVersion;
    }

    public Version getVersion() {
        return this.mVersion;
    }

    public ClientVersion(String str) {
        this.mVersion = Version.parse(str);
    }

    public static boolean isMinimumCompatibleVersion(Version version) {
        return getCurrentVersion().mVersion.compareTo(version.getMajor(), version.getMinor()) >= 0;
    }

    public static boolean isMaximumCompatibleVersion(Version version) {
        return getCurrentVersion().mVersion.compareTo(version.getMajor(), version.getMinor()) <= 0;
    }

    public String toVersionString() {
        return this.mVersion.toString();
    }
}
