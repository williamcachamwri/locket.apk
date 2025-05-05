package com.jimmydaddy.imagemarker.base;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/PositionEnum;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "TOP_LEFT", "TOP_CENTER", "TOP_RIGHT", "CENTER", "BOTTOM_LEFT", "BOTTOM_CENTER", "BOTTOM_RIGHT", "Companion", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PositionEnum.kt */
public enum PositionEnum {
    TOP_LEFT("topLeft"),
    TOP_CENTER("topCenter"),
    TOP_RIGHT("topRight"),
    CENTER(TtmlNode.CENTER),
    BOTTOM_LEFT("bottomLeft"),
    BOTTOM_CENTER("bottomCenter"),
    BOTTOM_RIGHT("bottomRight");
    
    public static final Companion Companion = null;
    private final String value;

    public static EnumEntries<PositionEnum> getEntries() {
        return $ENTRIES;
    }

    private PositionEnum(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    static {
        PositionEnum[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u0007"}, d2 = {"Lcom/jimmydaddy/imagemarker/base/PositionEnum$Companion;", "", "()V", "getPosition", "Lcom/jimmydaddy/imagemarker/base/PositionEnum;", "position", "", "react-native-image-marker_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PositionEnum.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PositionEnum getPosition(String str) {
            if (str != null) {
                switch (str.hashCode()) {
                    case -1682792238:
                        if (str.equals("bottomLeft")) {
                            return PositionEnum.BOTTOM_LEFT;
                        }
                        break;
                    case -1364013995:
                        if (str.equals(TtmlNode.CENTER)) {
                            return PositionEnum.CENTER;
                        }
                        break;
                    case -1140120836:
                        if (str.equals("topLeft")) {
                            return PositionEnum.TOP_LEFT;
                        }
                        break;
                    case -978346553:
                        if (str.equals("topRight")) {
                            return PositionEnum.TOP_RIGHT;
                        }
                        break;
                    case -696883702:
                        if (str.equals("topCenter")) {
                            return PositionEnum.TOP_CENTER;
                        }
                        break;
                    case 1781909088:
                        if (str.equals("bottomCenter")) {
                            return PositionEnum.BOTTOM_CENTER;
                        }
                        break;
                }
            }
            return PositionEnum.BOTTOM_RIGHT;
        }
    }
}
