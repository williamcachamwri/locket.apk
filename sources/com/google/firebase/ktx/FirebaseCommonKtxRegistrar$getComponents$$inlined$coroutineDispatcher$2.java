package com.google.firebase.ktx;

import com.google.firebase.annotations.concurrent.Lightweight;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.components.Qualified;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001\"\n\b\u0000\u0010\u0003\u0018\u0001*\u00020\u00042\u000e\u0010\u0005\u001a\n \u0002*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/CoroutineDispatcher;", "kotlin.jvm.PlatformType", "T", "", "c", "Lcom/google/firebase/components/ComponentContainer;", "create", "com/google/firebase/ktx/FirebaseKt$coroutineDispatcher$1"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Firebase.kt */
public final class FirebaseCommonKtxRegistrar$getComponents$$inlined$coroutineDispatcher$2<T> implements ComponentFactory {
    public static final FirebaseCommonKtxRegistrar$getComponents$$inlined$coroutineDispatcher$2<T> INSTANCE = new FirebaseCommonKtxRegistrar$getComponents$$inlined$coroutineDispatcher$2<>();

    public final CoroutineDispatcher create(ComponentContainer componentContainer) {
        Object obj = componentContainer.get(Qualified.qualified(Lightweight.class, Executor.class));
        Intrinsics.checkNotNullExpressionValue(obj, "c.get(Qualified.qualifie…a, Executor::class.java))");
        return ExecutorsKt.from((Executor) obj);
    }
}
