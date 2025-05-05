package expo.interfaces.devmenu.items;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.sentry.Session;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J8\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\r0\u000f¢\u0006\u0002\b\u0010H\u0001J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0001J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0001J\"\u0010\u0017\u001a\u00020\u00182\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\r0\u000f¢\u0006\u0002\b\u0010H\u0001J*\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\r0\u000f¢\u0006\u0002\b\u0010H\u0001J\"\u0010\u001c\u001a\u00020\u001d2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\r0\u000f¢\u0006\u0002\b\u0010H\u0001J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006 "}, d2 = {"Lexpo/interfaces/devmenu/items/DevMenuScreen;", "Lexpo/interfaces/devmenu/items/DevMenuItem;", "Lexpo/interfaces/devmenu/items/DevMenuDSLItemsContainerInterface;", "screenName", "", "itemsContainer", "(Ljava/lang/String;Lexpo/interfaces/devmenu/items/DevMenuDSLItemsContainerInterface;)V", "getScreenName", "()Ljava/lang/String;", "action", "Lexpo/interfaces/devmenu/items/DevMenuAction;", "actionId", "Lkotlin/Function0;", "", "init", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "getAllItems", "", "Lexpo/interfaces/devmenu/items/DevMenuScreenItem;", "getExportedType", "", "getRootItems", "group", "Lexpo/interfaces/devmenu/items/DevMenuGroup;", "link", "Lexpo/interfaces/devmenu/items/DevMenuLink;", "target", "selectionList", "Lexpo/interfaces/devmenu/items/DevMenuSelectionList;", "serialize", "Landroid/os/Bundle;", "expo-dev-menu-interface_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuItems.kt */
public final class DevMenuScreen extends DevMenuItem implements DevMenuDSLItemsContainerInterface {
    private final /* synthetic */ DevMenuDSLItemsContainerInterface $$delegate_0;
    private final String screenName;

    public DevMenuAction action(String str, Function0<Unit> function0, Function1<? super DevMenuAction, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "actionId");
        Intrinsics.checkNotNullParameter(function0, "action");
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        return this.$$delegate_0.action(str, function0, function1);
    }

    public List<DevMenuScreenItem> getAllItems() {
        return this.$$delegate_0.getAllItems();
    }

    public int getExportedType() {
        return 3;
    }

    public List<DevMenuScreenItem> getRootItems() {
        return this.$$delegate_0.getRootItems();
    }

    public DevMenuGroup group(Function1<? super DevMenuGroup, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        return this.$$delegate_0.group(function1);
    }

    public DevMenuLink link(String str, Function1<? super DevMenuLink, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "target");
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        return this.$$delegate_0.link(str, function1);
    }

    public DevMenuSelectionList selectionList(Function1<? super DevMenuSelectionList, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, Session.JsonKeys.INIT);
        return this.$$delegate_0.selectionList(function1);
    }

    public final String getScreenName() {
        return this.screenName;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ DevMenuScreen(String str, DevMenuDSLItemsContainerInterface devMenuDSLItemsContainerInterface, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? new DevMenuItemsContainer() : devMenuDSLItemsContainerInterface);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DevMenuScreen(String str, DevMenuDSLItemsContainerInterface devMenuDSLItemsContainerInterface) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "screenName");
        Intrinsics.checkNotNullParameter(devMenuDSLItemsContainerInterface, "itemsContainer");
        this.screenName = str;
        this.$$delegate_0 = devMenuDSLItemsContainerInterface;
    }

    public Bundle serialize() {
        Bundle serialize = super.serialize();
        serialize.putString("screenName", this.screenName);
        serialize.putParcelableArray(FirebaseAnalytics.Param.ITEMS, (Parcelable[]) DevMenuItemsContainerInterfaceKt.serializeItems(this));
        return serialize;
    }
}
