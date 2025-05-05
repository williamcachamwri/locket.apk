package expo.modules.devmenu.extensions;

import android.app.Activity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.devsupport.DevMenuInternalSettingsWrapper;
import com.facebook.react.devsupport.interfaces.DevSupportManager;
import com.facebook.react.modules.debug.interfaces.DeveloperSettings;
import expo.interfaces.devmenu.DevMenuExtensionSettingsInterface;
import expo.interfaces.devmenu.items.DevMenuAction;
import expo.interfaces.devmenu.items.DevMenuDSLItemsContainerInterface;
import expo.interfaces.devmenu.items.DevMenuItemImportance;
import expo.interfaces.devmenu.items.KeyCommand;
import expo.modules.devmenu.DevMenuManager;
import expo.modules.devmenu.DevMenuUtilsKt;
import expo.modules.devmenu.devtools.DevMenuDevToolsDelegate;
import io.sentry.android.core.SentryLogcatAdapter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lexpo/interfaces/devmenu/items/DevMenuDSLItemsContainerInterface;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevMenuExtension.kt */
final class DevMenuExtension$devMenuItems$1 extends Lambda implements Function1<DevMenuDSLItemsContainerInterface, Unit> {
    final /* synthetic */ DevMenuExtensionSettingsInterface $settings;
    final /* synthetic */ DevMenuExtension this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DevMenuExtension$devMenuItems$1(DevMenuExtensionSettingsInterface devMenuExtensionSettingsInterface, DevMenuExtension devMenuExtension) {
        super(1);
        this.$settings = devMenuExtensionSettingsInterface;
        this.this$0 = devMenuExtension;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DevMenuDSLItemsContainerInterface) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(DevMenuDSLItemsContainerInterface devMenuDSLItemsContainerInterface) {
        Intrinsics.checkNotNullParameter(devMenuDSLItemsContainerInterface, "$this$export");
        if (this.$settings.wasRunOnDevelopmentBridge()) {
            ReactInstanceManager reactInstanceManager = DevMenuManager.INSTANCE.getReactInstanceManager();
            if (reactInstanceManager == null) {
                SentryLogcatAdapter.w(DevMenuUtilsKt.DEV_MENU_TAG, "Couldn't export dev-menu items, because the react instance manager isn't present.");
                return;
            }
            final DevMenuDevToolsDelegate devMenuDevToolsDelegate = new DevMenuDevToolsDelegate(this.$settings.getManager(), reactInstanceManager);
            DevSupportManager reactDevManager = devMenuDevToolsDelegate.getReactDevManager();
            final DeveloperSettings devSettings = devMenuDevToolsDelegate.getDevSettings();
            if (reactDevManager == null || devSettings == null) {
                SentryLogcatAdapter.w(DevMenuUtilsKt.DEV_MENU_TAG, "Couldn't export dev-menu items, because react-native bridge doesn't contain the dev support manager.");
                return;
            }
            devMenuDSLItemsContainerInterface.action("reload", new Function0<Unit>(devMenuDevToolsDelegate) {
                public final void invoke() {
                    ((DevMenuDevToolsDelegate) this.receiver).reload();
                }
            }, AnonymousClass2.INSTANCE);
            final DevMenuExtension devMenuExtension = this.this$0;
            devMenuDSLItemsContainerInterface.action("performance-monitor", new Function0<Unit>() {
                public final void invoke() {
                    Activity access$getCurrentActivity = devMenuExtension.getCurrentActivity();
                    if (access$getCurrentActivity != null) {
                        devMenuDevToolsDelegate.togglePerformanceMonitor(access$getCurrentActivity);
                    }
                }
            }, new Function1<DevMenuAction, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((DevMenuAction) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(final DevMenuAction devMenuAction) {
                    Intrinsics.checkNotNullParameter(devMenuAction, "$this$action");
                    final DeveloperSettings developerSettings = devSettings;
                    devMenuAction.setEnabled(new Function0<Boolean>() {
                        public final Boolean invoke() {
                            return Boolean.valueOf(developerSettings.isFpsDebugEnabled());
                        }
                    });
                    devMenuAction.setLabel(new Function0<String>() {
                        public final String invoke() {
                            return devMenuAction.isEnabled().invoke().booleanValue() ? "Hide Performance Monitor" : "Show Performance Monitor";
                        }
                    });
                    devMenuAction.setGlyphName(AnonymousClass3.INSTANCE);
                    devMenuAction.setKeyCommand(new KeyCommand(44, false, 2, (DefaultConstructorMarker) null));
                    devMenuAction.setImportance(DevMenuItemImportance.HIGH.getValue());
                }
            });
            devMenuDSLItemsContainerInterface.action("inspector", new Function0<Unit>(devMenuDevToolsDelegate) {
                public final void invoke() {
                    ((DevMenuDevToolsDelegate) this.receiver).toggleElementInspector();
                }
            }, new Function1<DevMenuAction, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((DevMenuAction) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(final DevMenuAction devMenuAction) {
                    Intrinsics.checkNotNullParameter(devMenuAction, "$this$action");
                    final DeveloperSettings developerSettings = devSettings;
                    devMenuAction.setEnabled(new Function0<Boolean>() {
                        public final Boolean invoke() {
                            return Boolean.valueOf(developerSettings.isElementInspectorEnabled());
                        }
                    });
                    devMenuAction.setLabel(new Function0<String>() {
                        public final String invoke() {
                            return devMenuAction.isEnabled().invoke().booleanValue() ? "Hide Element Inspector" : "Show Element Inspector";
                        }
                    });
                    devMenuAction.setGlyphName(AnonymousClass3.INSTANCE);
                    devMenuAction.setKeyCommand(new KeyCommand(37, false, 2, (DefaultConstructorMarker) null));
                    devMenuAction.setImportance(DevMenuItemImportance.HIGH.getValue());
                }
            });
            devMenuDSLItemsContainerInterface.action("remote-debug", new Function0<Unit>(devMenuDevToolsDelegate) {
                public final void invoke() {
                    ((DevMenuDevToolsDelegate) this.receiver).toggleRemoteDebugging();
                }
            }, new Function1<DevMenuAction, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((DevMenuAction) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(final DevMenuAction devMenuAction) {
                    Intrinsics.checkNotNullParameter(devMenuAction, "$this$action");
                    final DeveloperSettings developerSettings = devSettings;
                    devMenuAction.setEnabled(new Function0<Boolean>() {
                        public final Boolean invoke() {
                            return Boolean.valueOf(developerSettings.isRemoteJSDebugEnabled());
                        }
                    });
                    devMenuAction.setLabel(new Function0<String>() {
                        public final String invoke() {
                            return devMenuAction.isEnabled().invoke().booleanValue() ? "Stop Remote Debugging" : "Debug Remote JS";
                        }
                    });
                    devMenuAction.setGlyphName(AnonymousClass3.INSTANCE);
                    devMenuAction.setImportance(DevMenuItemImportance.LOW.getValue());
                }
            });
            DevMenuInternalSettingsWrapper devInternalSettings$expo_dev_menu_release = devMenuDevToolsDelegate.getDevInternalSettings$expo_dev_menu_release();
            if (devInternalSettings$expo_dev_menu_release != null) {
                DevMenuExtension devMenuExtension2 = this.this$0;
                devMenuDSLItemsContainerInterface.action("js-inspector", new DevMenuExtension$devMenuItems$1$9$1(devMenuDevToolsDelegate), DevMenuExtension$devMenuItems$1$9$2.INSTANCE);
                devMenuDSLItemsContainerInterface.action("fast-refresh", new DevMenuExtension$devMenuItems$1$9$fastRefreshAction$1(devInternalSettings$expo_dev_menu_release, devMenuExtension2, reactDevManager), new DevMenuExtension$devMenuItems$1$9$3(devInternalSettings$expo_dev_menu_release));
            }
        }
    }
}
