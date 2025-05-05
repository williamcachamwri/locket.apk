package com.google.devtools.ksp.symbol;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/google/devtools/ksp/symbol/NonExistLocation;", "Lcom/google/devtools/ksp/symbol/Location;", "()V", "api"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Location.kt */
public final class NonExistLocation extends Location {
    public static final NonExistLocation INSTANCE = new NonExistLocation();

    private NonExistLocation() {
        super((DefaultConstructorMarker) null);
    }
}
