package expo.interfaces.devmenu.items;

import io.sentry.Session;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J7\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000eH\u0016J8\u0010\u000f\u001a\u0002H\u0010\"\b\b\u0000\u0010\u0010*\u00020\u00052\u0006\u0010\u0011\u001a\u0002H\u00102\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000eH\u0002¢\u0006\u0002\u0010\u0012J\u0010\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0014H\u0016J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0014H\u0016J!\u0010\u0016\u001a\u00020\u00172\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000eH\u0016J)\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\t2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000eH\u0016J!\u0010\u001b\u001a\u00020\u001c2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\u0002\b\u000eH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lexpo/interfaces/devmenu/items/DevMenuItemsContainer;", "Lexpo/interfaces/devmenu/items/DevMenuDSLItemsContainerInterface;", "()V", "items", "", "Lexpo/interfaces/devmenu/items/DevMenuScreenItem;", "action", "Lexpo/interfaces/devmenu/items/DevMenuAction;", "actionId", "", "Lkotlin/Function0;", "", "init", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "addItem", "T", "item", "(Lexpo/interfaces/devmenu/items/DevMenuScreenItem;Lkotlin/jvm/functions/Function1;)Lexpo/interfaces/devmenu/items/DevMenuScreenItem;", "getAllItems", "", "getRootItems", "group", "Lexpo/interfaces/devmenu/items/DevMenuGroup;", "link", "Lexpo/interfaces/devmenu/items/DevMenuLink;", "target", "selectionList", "Lexpo/interfaces/devmenu/items/DevMenuSelectionList;", "Companion", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuItemsContainer.kt */
public class DevMenuItemsContainer implements DevMenuDSLItemsContainerInterface {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final List<DevMenuScreenItem> items = new ArrayList();

    @JvmStatic
    public static final DevMenuItemsContainer export(Function1<? super DevMenuDSLItemsContainerInterface, Unit> function1) {
        return Companion.export(function1);
    }

    public List<DevMenuScreenItem> getRootItems() {
        CollectionsKt.sortedWith(this.items, new DevMenuItemsContainer$getRootItems$$inlined$compareBy$1());
        return this.items;
    }

    public List<DevMenuScreenItem> getAllItems() {
        LinkedList linkedList = new LinkedList();
        for (DevMenuScreenItem devMenuScreenItem : this.items) {
            linkedList.add(devMenuScreenItem);
            if (devMenuScreenItem instanceof DevMenuItemsContainerInterface) {
                linkedList.addAll(((DevMenuItemsContainerInterface) devMenuScreenItem).getAllItems());
            }
        }
        return linkedList;
    }

    private final void addItem(DevMenuScreenItem devMenuScreenItem) {
        this.items.add(devMenuScreenItem);
    }

    public DevMenuGroup group(Function1<? super DevMenuGroup, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        return (DevMenuGroup) addItem(new DevMenuGroup((DevMenuDSLItemsContainerInterface) null, 1, (DefaultConstructorMarker) null), function1);
    }

    public DevMenuAction action(String str, Function0<Unit> function0, Function1<? super DevMenuAction, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "actionId");
        Intrinsics.checkNotNullParameter(function0, "action");
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        return (DevMenuAction) addItem(new DevMenuAction(str, function0), function1);
    }

    public DevMenuLink link(String str, Function1<? super DevMenuLink, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "target");
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        return (DevMenuLink) addItem(new DevMenuLink(str), function1);
    }

    public DevMenuSelectionList selectionList(Function1<? super DevMenuSelectionList, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        return (DevMenuSelectionList) addItem(new DevMenuSelectionList(), function1);
    }

    private final <T extends DevMenuScreenItem> T addItem(T t, Function1<? super T, Unit> function1) {
        function1.invoke(t);
        addItem(t);
        return t;
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\tH\u0007¨\u0006\n"}, d2 = {"Lexpo/interfaces/devmenu/items/DevMenuItemsContainer$Companion;", "", "()V", "export", "Lexpo/interfaces/devmenu/items/DevMenuItemsContainer;", "init", "Lkotlin/Function1;", "Lexpo/interfaces/devmenu/items/DevMenuDSLItemsContainerInterface;", "", "Lkotlin/ExtensionFunctionType;", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DevMenuItemsContainer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final DevMenuItemsContainer export(Function1<? super DevMenuDSLItemsContainerInterface, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
            DevMenuItemsContainer devMenuItemsContainer = new DevMenuItemsContainer();
            function1.invoke(devMenuItemsContainer);
            return devMenuItemsContainer;
        }
    }
}
