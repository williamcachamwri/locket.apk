package expo.modules.clipboard;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lexpo/modules/clipboard/ContentType;", "", "jsName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getJsName", "()Ljava/lang/String;", "PLAIN_TEXT", "HTML", "IMAGE", "expo-clipboard_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClipboardModule.kt */
enum ContentType {
    PLAIN_TEXT("plain-text"),
    HTML("html"),
    IMAGE("image");
    
    private final String jsName;

    public static EnumEntries<ContentType> getEntries() {
        return $ENTRIES;
    }

    private ContentType(String str) {
        this.jsName = str;
    }

    public final String getJsName() {
        return this.jsName;
    }

    static {
        ContentType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
