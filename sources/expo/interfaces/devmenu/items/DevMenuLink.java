package expo.interfaces.devmenu.items;

import android.os.Bundle;
import com.google.firebase.messaging.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lexpo/interfaces/devmenu/items/DevMenuLink;", "Lexpo/interfaces/devmenu/items/DevMenuScreenItem;", "target", "", "(Ljava/lang/String;)V", "glyphName", "Lkotlin/Function0;", "getGlyphName", "()Lkotlin/jvm/functions/Function0;", "setGlyphName", "(Lkotlin/jvm/functions/Function0;)V", "label", "getLabel", "setLabel", "getExportedType", "", "serialize", "Landroid/os/Bundle;", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuItems.kt */
public final class DevMenuLink extends DevMenuScreenItem {
    private Function0<String> glyphName = DevMenuLink$glyphName$1.INSTANCE;
    private Function0<String> label = DevMenuLink$label$1.INSTANCE;
    private final String target;

    public int getExportedType() {
        return 4;
    }

    public DevMenuLink(String str) {
        Intrinsics.checkNotNullParameter(str, "target");
        this.target = str;
    }

    public final Function0<String> getLabel() {
        return this.label;
    }

    public final void setLabel(Function0<String> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.label = function0;
    }

    public final Function0<String> getGlyphName() {
        return this.glyphName;
    }

    public final void setGlyphName(Function0<String> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.glyphName = function0;
    }

    public Bundle serialize() {
        Bundle serialize = super.serialize();
        serialize.putString("target", this.target);
        serialize.putString(Constants.ScionAnalytics.PARAM_LABEL, this.label.invoke());
        serialize.putString("glyphName", this.glyphName.invoke());
        return serialize;
    }
}
