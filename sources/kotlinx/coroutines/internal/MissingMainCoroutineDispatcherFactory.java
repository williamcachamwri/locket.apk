package kotlinx.coroutines.internal;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.internal.MainDispatcherFactory;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/internal/MissingMainCoroutineDispatcherFactory;", "Lkotlinx/coroutines/internal/MainDispatcherFactory;", "<init>", "()V", "loadPriority", "", "getLoadPriority", "()I", "createDispatcher", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "allFactories", "", "kotlinx-coroutines-core"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: MainDispatchers.kt */
public final class MissingMainCoroutineDispatcherFactory implements MainDispatcherFactory {
    public static final MissingMainCoroutineDispatcherFactory INSTANCE = new MissingMainCoroutineDispatcherFactory();

    public int getLoadPriority() {
        return -1;
    }

    private MissingMainCoroutineDispatcherFactory() {
    }

    public String hintOnError() {
        return MainDispatcherFactory.DefaultImpls.hintOnError(this);
    }

    public MainCoroutineDispatcher createDispatcher(List<? extends MainDispatcherFactory> list) {
        return new MissingMainCoroutineDispatcher((Throwable) null, (String) null, 2, (DefaultConstructorMarker) null);
    }
}
