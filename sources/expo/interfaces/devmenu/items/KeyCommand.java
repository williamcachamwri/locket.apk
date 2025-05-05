package expo.interfaces.devmenu.items;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lexpo/interfaces/devmenu/items/KeyCommand;", "", "code", "", "withShift", "", "(IZ)V", "getCode", "()I", "getWithShift", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "toString", "", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuItems.kt */
public final class KeyCommand {
    private final int code;
    private final boolean withShift;

    public static /* synthetic */ KeyCommand copy$default(KeyCommand keyCommand, int i, boolean z, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = keyCommand.code;
        }
        if ((i2 & 2) != 0) {
            z = keyCommand.withShift;
        }
        return keyCommand.copy(i, z);
    }

    public final int component1() {
        return this.code;
    }

    public final boolean component2() {
        return this.withShift;
    }

    public final KeyCommand copy(int i, boolean z) {
        return new KeyCommand(i, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyCommand)) {
            return false;
        }
        KeyCommand keyCommand = (KeyCommand) obj;
        return this.code == keyCommand.code && this.withShift == keyCommand.withShift;
    }

    public int hashCode() {
        return (Integer.hashCode(this.code) * 31) + Boolean.hashCode(this.withShift);
    }

    public String toString() {
        int i = this.code;
        return "KeyCommand(code=" + i + ", withShift=" + this.withShift + ")";
    }

    public KeyCommand(int i, boolean z) {
        this.code = i;
        this.withShift = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ KeyCommand(int i, boolean z, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? false : z);
    }

    public final int getCode() {
        return this.code;
    }

    public final boolean getWithShift() {
        return this.withShift;
    }
}
