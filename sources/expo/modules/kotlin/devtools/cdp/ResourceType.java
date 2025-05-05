package expo.modules.kotlin.devtools.cdp;

import androidx.media3.common.MimeTypes;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0002\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/ResourceType;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "IMAGE", "MEDIA", "FONT", "SCRIPT", "FETCH", "OTHER", "Companion", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CdpNetworkTypes.kt */
public enum ResourceType {
    IMAGE("Image"),
    MEDIA("Media"),
    FONT("Font"),
    SCRIPT("Script"),
    FETCH("Fetch"),
    OTHER("Other");
    
    public static final Companion Companion = null;
    private final String value;

    public static EnumEntries<ResourceType> getEntries() {
        return $ENTRIES;
    }

    private ResourceType(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        ResourceType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lexpo/modules/kotlin/devtools/cdp/ResourceType$Companion;", "", "()V", "fromMimeType", "Lexpo/modules/kotlin/devtools/cdp/ResourceType;", "mimeType", "", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CdpNetworkTypes.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ResourceType fromMimeType(String str) {
            Intrinsics.checkNotNullParameter(str, "mimeType");
            if (StringsKt.startsWith$default(str, "image/", false, 2, (Object) null)) {
                return ResourceType.IMAGE;
            }
            if (StringsKt.startsWith$default(str, MimeTypes.BASE_TYPE_AUDIO, false, 2, (Object) null) || StringsKt.startsWith$default(str, MimeTypes.BASE_TYPE_VIDEO, false, 2, (Object) null)) {
                return ResourceType.MEDIA;
            }
            if (StringsKt.startsWith$default(str, "font", false, 2, (Object) null)) {
                return ResourceType.FONT;
            }
            return ResourceType.OTHER;
        }
    }
}
