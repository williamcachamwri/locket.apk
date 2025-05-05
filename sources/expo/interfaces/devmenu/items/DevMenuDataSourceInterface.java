package expo.interfaces.devmenu.items;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H¦@¢\u0006\u0002\u0010\tR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\n"}, d2 = {"Lexpo/interfaces/devmenu/items/DevMenuDataSourceInterface;", "", "id", "", "getId", "()Ljava/lang/String;", "fetchData", "", "Lexpo/interfaces/devmenu/items/DevMenuDataSourceItem;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuDataSource.kt */
public interface DevMenuDataSourceInterface {
    Object fetchData(Continuation<? super List<? extends DevMenuDataSourceItem>> continuation);

    String getId();
}
