package expo.modules.notifications.serverregistration;

import android.content.Context;
import java.io.File;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068DX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000f"}, d2 = {"Lexpo/modules/notifications/serverregistration/RegistrationInfo;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "nonBackedUpRegistrationInfoFile", "Ljava/io/File;", "getNonBackedUpRegistrationInfoFile", "()Ljava/io/File;", "get", "", "set", "", "registrationInfo", "Companion", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RegistrationInfo.kt */
public class RegistrationInfo {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String REGISTRATION_INFO_FILE_NAME = "expo_notifications_registration_info.txt";
    private final Context context;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lexpo/modules/notifications/serverregistration/RegistrationInfo$Companion;", "", "()V", "REGISTRATION_INFO_FILE_NAME", "", "expo-notifications_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RegistrationInfo.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public RegistrationInfo(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    /* access modifiers changed from: protected */
    public final File getNonBackedUpRegistrationInfoFile() {
        return new File(this.context.getNoBackupFilesDir(), REGISTRATION_INFO_FILE_NAME);
    }

    public final String get() {
        if (getNonBackedUpRegistrationInfoFile().exists()) {
            return FilesKt.readText$default(getNonBackedUpRegistrationInfoFile(), (Charset) null, 1, (Object) null);
        }
        return null;
    }

    public final void set(String str) {
        getNonBackedUpRegistrationInfoFile().delete();
        if (str != null) {
            FilesKt.writeText$default(getNonBackedUpRegistrationInfoFile(), str, (Charset) null, 2, (Object) null);
        }
    }
}
