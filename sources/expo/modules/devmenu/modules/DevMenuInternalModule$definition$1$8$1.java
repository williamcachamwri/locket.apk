package expo.modules.devmenu.modules;

import com.facebook.react.bridge.Arguments;
import expo.interfaces.devmenu.items.DevMenuDataSourceItem;
import expo.modules.devmenu.DevMenuManager;
import expo.modules.kotlin.Promise;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "expo.modules.devmenu.modules.DevMenuInternalModule$definition$1$8$1", f = "DevMenuInternalModule.kt", i = {}, l = {65}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DevMenuInternalModule.kt */
final class DevMenuInternalModule$definition$1$8$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $id;
    final /* synthetic */ Promise $promise;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DevMenuInternalModule$definition$1$8$1(String str, Promise promise, Continuation<? super DevMenuInternalModule$definition$1$8$1> continuation) {
        super(2, continuation);
        this.$id = str;
        this.$promise = promise;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DevMenuInternalModule$definition$1$8$1(this.$id, this.$promise, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DevMenuInternalModule$definition$1$8$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = DevMenuManager.INSTANCE.fetchDataSource(this.$id, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Iterable<DevMenuDataSourceItem> iterable = (List) obj;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (DevMenuDataSourceItem serialize : iterable) {
            arrayList.add(serialize.serialize());
        }
        this.$promise.resolve(Arguments.fromList((List) arrayList));
        return Unit.INSTANCE;
    }
}
