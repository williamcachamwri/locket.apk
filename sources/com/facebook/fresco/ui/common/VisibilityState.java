package com.facebook.fresco.ui.common;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\nB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\u000b"}, d2 = {"Lcom/facebook/fresco/ui/common/VisibilityState;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "UNKNOWN", "VISIBLE", "INVISIBLE", "Companion", "ui-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: VisibilityState.kt */
public enum VisibilityState {
    UNKNOWN(-1),
    VISIBLE(1),
    INVISIBLE(2);
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public static final VisibilityState[] VALUES = null;
    private final int value;

    private VisibilityState(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
        VALUES = values();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\n"}, d2 = {"Lcom/facebook/fresco/ui/common/VisibilityState$Companion;", "", "()V", "VALUES", "", "Lcom/facebook/fresco/ui/common/VisibilityState;", "[Lcom/facebook/fresco/ui/common/VisibilityState;", "fromInt", "value", "", "ui-common_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: VisibilityState.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VisibilityState fromInt(int i) {
            for (VisibilityState visibilityState : VisibilityState.VALUES) {
                if (visibilityState.getValue() == i) {
                    return visibilityState;
                }
            }
            return null;
        }
    }
}
