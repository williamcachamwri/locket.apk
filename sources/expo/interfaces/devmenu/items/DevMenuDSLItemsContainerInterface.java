package expo.interfaces.devmenu.items;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J7\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\b\nH&J!\u0010\u000b\u001a\u00020\f2\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\b\nH&J)\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\b\nH&J!\u0010\u0010\u001a\u00020\u00112\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\b\nH&¨\u0006\u0012"}, d2 = {"Lexpo/interfaces/devmenu/items/DevMenuDSLItemsContainerInterface;", "Lexpo/interfaces/devmenu/items/DevMenuItemsContainerInterface;", "action", "Lexpo/interfaces/devmenu/items/DevMenuAction;", "actionId", "", "Lkotlin/Function0;", "", "init", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "group", "Lexpo/interfaces/devmenu/items/DevMenuGroup;", "link", "Lexpo/interfaces/devmenu/items/DevMenuLink;", "target", "selectionList", "Lexpo/interfaces/devmenu/items/DevMenuSelectionList;", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuItemsContainerInterface.kt */
public interface DevMenuDSLItemsContainerInterface extends DevMenuItemsContainerInterface {
    DevMenuAction action(String str, Function0<Unit> function0, Function1<? super DevMenuAction, Unit> function1);

    DevMenuGroup group(Function1<? super DevMenuGroup, Unit> function1);

    DevMenuLink link(String str, Function1<? super DevMenuLink, Unit> function1);

    DevMenuSelectionList selectionList(Function1<? super DevMenuSelectionList, Unit> function1);
}
