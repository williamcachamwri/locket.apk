package com.swmansion.gesturehandler.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/swmansion/gesturehandler/core/GestureHandler;", "invoke", "(Lcom/swmansion/gesturehandler/core/GestureHandler;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: GestureHandlerOrchestrator.kt */
final class GestureHandlerOrchestrator$cleanupFinishedHandlers$2 extends Lambda implements Function1<GestureHandler<?>, Boolean> {
    public static final GestureHandlerOrchestrator$cleanupFinishedHandlers$2 INSTANCE = new GestureHandlerOrchestrator$cleanupFinishedHandlers$2();

    GestureHandlerOrchestrator$cleanupFinishedHandlers$2() {
        super(1);
    }

    public final Boolean invoke(GestureHandler<?> gestureHandler) {
        Intrinsics.checkNotNullParameter(gestureHandler, "it");
        return Boolean.valueOf(GestureHandlerOrchestrator.Companion.isFinished(gestureHandler.getState()) && !gestureHandler.isAwaiting());
    }
}
