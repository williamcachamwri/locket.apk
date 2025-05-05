package expo.modules.clipboard;

import expo.modules.kotlin.records.Field;
import expo.modules.kotlin.records.Record;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lexpo/modules/clipboard/SetStringOptions;", "Lexpo/modules/kotlin/records/Record;", "()V", "inputFormat", "Lexpo/modules/clipboard/StringFormat;", "getInputFormat$annotations", "getInputFormat", "()Lexpo/modules/clipboard/StringFormat;", "setInputFormat", "(Lexpo/modules/clipboard/StringFormat;)V", "expo-clipboard_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ClipboardOptions.kt */
public final class SetStringOptions implements Record {
    private StringFormat inputFormat = StringFormat.PLAIN;

    @Field
    public static /* synthetic */ void getInputFormat$annotations() {
    }

    public final StringFormat getInputFormat() {
        return this.inputFormat;
    }

    public final void setInputFormat(StringFormat stringFormat) {
        Intrinsics.checkNotNullParameter(stringFormat, "<set-?>");
        this.inputFormat = stringFormat;
    }
}
