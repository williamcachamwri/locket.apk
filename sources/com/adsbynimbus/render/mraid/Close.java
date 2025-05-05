package com.adsbynimbus.render.mraid;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerialName;
import kotlinx.serialization.Serializable;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00000\u0004HÆ\u0001¨\u0006\u0005"}, d2 = {"Lcom/adsbynimbus/render/mraid/Close;", "Lcom/adsbynimbus/render/mraid/Command;", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "static_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Serializable
@SerialName("close")
/* compiled from: Command.kt */
public final class Close extends Command {
    private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, AnonymousClass1.INSTANCE);
    public static final Close INSTANCE = new Close();

    private final /* synthetic */ KSerializer get$cachedSerializer() {
        return $cachedSerializer$delegate.getValue();
    }

    public final KSerializer<Close> serializer() {
        return get$cachedSerializer();
    }

    private Close() {
        super((DefaultConstructorMarker) null);
    }
}
