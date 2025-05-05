package expo.modules.devlauncher.launcher;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\bHÆ\u0003¢\u0006\u0002\u0010\u000eJ\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0005HÆ\u0003JT\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\b2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\u0007\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006\""}, d2 = {"Lexpo/modules/devlauncher/launcher/DevLauncherAppEntry;", "", "timestamp", "", "name", "", "url", "isEASUpdate", "", "updateMessage", "branchName", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)V", "getBranchName", "()Ljava/lang/String;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getName", "getTimestamp", "()J", "getUpdateMessage", "getUrl", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;)Lexpo/modules/devlauncher/launcher/DevLauncherAppEntry;", "equals", "other", "hashCode", "", "toString", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherRecentlyOpenedAppsRegistry.kt */
public final class DevLauncherAppEntry {
    private final String branchName;
    private final Boolean isEASUpdate;
    private final String name;
    private final long timestamp;
    private final String updateMessage;
    private final String url;

    public static /* synthetic */ DevLauncherAppEntry copy$default(DevLauncherAppEntry devLauncherAppEntry, long j, String str, String str2, Boolean bool, String str3, String str4, int i, Object obj) {
        DevLauncherAppEntry devLauncherAppEntry2 = devLauncherAppEntry;
        return devLauncherAppEntry.copy((i & 1) != 0 ? devLauncherAppEntry2.timestamp : j, (i & 2) != 0 ? devLauncherAppEntry2.name : str, (i & 4) != 0 ? devLauncherAppEntry2.url : str2, (i & 8) != 0 ? devLauncherAppEntry2.isEASUpdate : bool, (i & 16) != 0 ? devLauncherAppEntry2.updateMessage : str3, (i & 32) != 0 ? devLauncherAppEntry2.branchName : str4);
    }

    public final long component1() {
        return this.timestamp;
    }

    public final String component2() {
        return this.name;
    }

    public final String component3() {
        return this.url;
    }

    public final Boolean component4() {
        return this.isEASUpdate;
    }

    public final String component5() {
        return this.updateMessage;
    }

    public final String component6() {
        return this.branchName;
    }

    public final DevLauncherAppEntry copy(long j, String str, String str2, Boolean bool, String str3, String str4) {
        return new DevLauncherAppEntry(j, str, str2, bool, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DevLauncherAppEntry)) {
            return false;
        }
        DevLauncherAppEntry devLauncherAppEntry = (DevLauncherAppEntry) obj;
        return this.timestamp == devLauncherAppEntry.timestamp && Intrinsics.areEqual((Object) this.name, (Object) devLauncherAppEntry.name) && Intrinsics.areEqual((Object) this.url, (Object) devLauncherAppEntry.url) && Intrinsics.areEqual((Object) this.isEASUpdate, (Object) devLauncherAppEntry.isEASUpdate) && Intrinsics.areEqual((Object) this.updateMessage, (Object) devLauncherAppEntry.updateMessage) && Intrinsics.areEqual((Object) this.branchName, (Object) devLauncherAppEntry.branchName);
    }

    public int hashCode() {
        int hashCode = Long.hashCode(this.timestamp) * 31;
        String str = this.name;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.url;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.isEASUpdate;
        int hashCode4 = (hashCode3 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str3 = this.updateMessage;
        int hashCode5 = (hashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.branchName;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode5 + i;
    }

    public String toString() {
        long j = this.timestamp;
        String str = this.name;
        String str2 = this.url;
        Boolean bool = this.isEASUpdate;
        String str3 = this.updateMessage;
        return "DevLauncherAppEntry(timestamp=" + j + ", name=" + str + ", url=" + str2 + ", isEASUpdate=" + bool + ", updateMessage=" + str3 + ", branchName=" + this.branchName + ")";
    }

    public DevLauncherAppEntry(long j, String str, String str2, Boolean bool, String str3, String str4) {
        this.timestamp = j;
        this.name = str;
        this.url = str2;
        this.isEASUpdate = bool;
        this.updateMessage = str3;
        this.branchName = str4;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final String getName() {
        return this.name;
    }

    public final String getUrl() {
        return this.url;
    }

    public final Boolean isEASUpdate() {
        return this.isEASUpdate;
    }

    public final String getUpdateMessage() {
        return this.updateMessage;
    }

    public final String getBranchName() {
        return this.branchName;
    }
}
