package expo.modules.devlauncher.logs;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B'\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004¢\u0006\u0002\u0010\u0007J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0004HÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0004HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0006\u0010\u001a\u001a\u00020\u0004J\t\u0010\u001b\u001a\u00020\u0004HÖ\u0001R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\t8\u0002X\u0004¢\u0006\u0004\n\u0002\u0010\nR\u0016\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0010\u0010\u0010\u001a\u00020\u00048\u0002XD¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lexpo/modules/devlauncher/logs/DevLauncherRemoteLog;", "", "messages", "", "", "level", "mode", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "data", "", "[Ljava/lang/String;", "getLevel", "()Ljava/lang/String;", "getMessages", "()Ljava/util/List;", "getMode", "type", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toJson", "toString", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherRemoteLog.kt */
public final class DevLauncherRemoteLog {
    @Expose
    private final String[] data;
    @Expose
    private final String level;
    private final List<String> messages;
    @Expose
    private final String mode;
    @Expose
    private final String type;

    public static /* synthetic */ DevLauncherRemoteLog copy$default(DevLauncherRemoteLog devLauncherRemoteLog, List<String> list, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = devLauncherRemoteLog.messages;
        }
        if ((i & 2) != 0) {
            str = devLauncherRemoteLog.level;
        }
        if ((i & 4) != 0) {
            str2 = devLauncherRemoteLog.mode;
        }
        return devLauncherRemoteLog.copy(list, str, str2);
    }

    public final List<String> component1() {
        return this.messages;
    }

    public final String component2() {
        return this.level;
    }

    public final String component3() {
        return this.mode;
    }

    public final DevLauncherRemoteLog copy(List<String> list, String str, String str2) {
        Intrinsics.checkNotNullParameter(list, "messages");
        Intrinsics.checkNotNullParameter(str, "level");
        Intrinsics.checkNotNullParameter(str2, "mode");
        return new DevLauncherRemoteLog(list, str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DevLauncherRemoteLog)) {
            return false;
        }
        DevLauncherRemoteLog devLauncherRemoteLog = (DevLauncherRemoteLog) obj;
        return Intrinsics.areEqual((Object) this.messages, (Object) devLauncherRemoteLog.messages) && Intrinsics.areEqual((Object) this.level, (Object) devLauncherRemoteLog.level) && Intrinsics.areEqual((Object) this.mode, (Object) devLauncherRemoteLog.mode);
    }

    public int hashCode() {
        return (((this.messages.hashCode() * 31) + this.level.hashCode()) * 31) + this.mode.hashCode();
    }

    public String toString() {
        List<String> list = this.messages;
        String str = this.level;
        return "DevLauncherRemoteLog(messages=" + list + ", level=" + str + ", mode=" + this.mode + ")";
    }

    public DevLauncherRemoteLog(List<String> list, String str, String str2) {
        Intrinsics.checkNotNullParameter(list, "messages");
        Intrinsics.checkNotNullParameter(str, "level");
        Intrinsics.checkNotNullParameter(str2, "mode");
        this.messages = list;
        this.level = str;
        this.mode = str2;
        this.data = new String[]{CollectionsKt.joinToString$default(list, "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null)};
        this.type = "log";
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DevLauncherRemoteLog(List list, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? "error" : str, (i & 4) != 0 ? "BRIDGE" : str2);
    }

    public final List<String> getMessages() {
        return this.messages;
    }

    public final String getLevel() {
        return this.level;
    }

    public final String getMode() {
        return this.mode;
    }

    public final String toJson() {
        String json = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson((Object) this);
        Intrinsics.checkNotNullExpressionValue(json, "toJson(...)");
        return json;
    }
}
