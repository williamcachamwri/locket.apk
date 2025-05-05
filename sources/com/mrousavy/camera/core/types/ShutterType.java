package com.mrousavy.camera.core.types;

import io.sentry.protocol.SentryStackTrace;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/mrousavy/camera/core/types/ShutterType;", "", "Lcom/mrousavy/camera/core/types/JSUnionValue;", "unionValue", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getUnionValue", "()Ljava/lang/String;", "PHOTO", "SNAPSHOT", "react-native-vision-camera_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ShutterType.kt */
public enum ShutterType implements JSUnionValue {
    PHOTO("photo"),
    SNAPSHOT(SentryStackTrace.JsonKeys.SNAPSHOT);
    
    private final String unionValue;

    public static EnumEntries<ShutterType> getEntries() {
        return $ENTRIES;
    }

    private ShutterType(String str) {
        this.unionValue = str;
    }

    public String getUnionValue() {
        return this.unionValue;
    }

    static {
        ShutterType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
