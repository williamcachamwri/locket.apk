package expo.modules;

import expo.modules.adapters.react.ReactAdapterPackage;
import expo.modules.application.ApplicationModule;
import expo.modules.blur.BlurModule;
import expo.modules.brightness.BrightnessModule;
import expo.modules.camera.CameraViewModule;
import expo.modules.camera.next.CameraViewNextModule;
import expo.modules.clipboard.ClipboardModule;
import expo.modules.constants.ConstantsModule;
import expo.modules.constants.ConstantsPackage;
import expo.modules.contacts.ContactsModule;
import expo.modules.core.BasePackage;
import expo.modules.core.interfaces.Package;
import expo.modules.crypto.CryptoModule;
import expo.modules.device.DeviceModule;
import expo.modules.devlauncher.DevLauncherPackage;
import expo.modules.devmenu.DevMenuPackage;
import expo.modules.devmenu.modules.DevMenuModule;
import expo.modules.devmenu.modules.DevMenuPreferences;
import expo.modules.filesystem.FileSystemModule;
import expo.modules.filesystem.FileSystemPackage;
import expo.modules.font.FontLoaderModule;
import expo.modules.haptics.HapticsModule;
import expo.modules.imageloader.ImageLoaderPackage;
import expo.modules.imagemanipulator.ImageManipulatorModule;
import expo.modules.imagepicker.ImagePickerModule;
import expo.modules.intentlauncher.IntentLauncherModule;
import expo.modules.keepawake.KeepAwakeModule;
import expo.modules.keepawake.KeepAwakePackage;
import expo.modules.kotlin.ModulesProvider;
import expo.modules.kotlin.modules.Module;
import expo.modules.lineargradient.LinearGradientModule;
import expo.modules.localization.LocalizationModule;
import expo.modules.localization.LocalizationPackage;
import expo.modules.navigationbar.NavigationBarModule;
import expo.modules.navigationbar.NavigationBarPackage;
import expo.modules.notifications.NotificationsPackage;
import expo.modules.notifications.badge.BadgeModule;
import expo.modules.notifications.notifications.background.ExpoBackgroundNotificationTasksModule;
import expo.modules.notifications.notifications.categories.ExpoNotificationCategoriesModule;
import expo.modules.notifications.notifications.channels.NotificationChannelGroupManagerModule;
import expo.modules.notifications.notifications.channels.NotificationChannelManagerModule;
import expo.modules.notifications.notifications.emitting.NotificationsEmitter;
import expo.modules.notifications.notifications.handling.NotificationsHandler;
import expo.modules.notifications.notifications.presentation.ExpoNotificationPresentationModule;
import expo.modules.notifications.notifications.scheduling.NotificationScheduler;
import expo.modules.notifications.permissions.NotificationPermissionsModule;
import expo.modules.notifications.serverregistration.ServerRegistrationModule;
import expo.modules.notifications.tokens.PushTokenModule;
import expo.modules.splashscreen.SplashScreenModule;
import expo.modules.splashscreen.SplashScreenPackage;
import expo.modules.storereview.StoreReviewModule;
import expo.modules.videothumbnails.VideoThumbnailsModule;
import java.util.Arrays;
import java.util.List;

public class ExpoModulesPackageList implements ModulesProvider {

    private static class LazyHolder {
        static final List<Class<? extends Module>> modulesList = Arrays.asList(new Class[]{ApplicationModule.class, BlurModule.class, BrightnessModule.class, CameraViewModule.class, CameraViewNextModule.class, ClipboardModule.class, ConstantsModule.class, ContactsModule.class, CryptoModule.class, DevMenuModule.class, DevMenuPreferences.class, DeviceModule.class, FileSystemModule.class, FontLoaderModule.class, HapticsModule.class, ImageManipulatorModule.class, ImagePickerModule.class, IntentLauncherModule.class, KeepAwakeModule.class, LinearGradientModule.class, LocalizationModule.class, NavigationBarModule.class, BadgeModule.class, ExpoBackgroundNotificationTasksModule.class, ExpoNotificationCategoriesModule.class, NotificationChannelGroupManagerModule.class, NotificationChannelManagerModule.class, NotificationsEmitter.class, NotificationsHandler.class, NotificationPermissionsModule.class, ExpoNotificationPresentationModule.class, NotificationScheduler.class, ServerRegistrationModule.class, PushTokenModule.class, SplashScreenModule.class, StoreReviewModule.class, VideoThumbnailsModule.class});
        static final List<Package> packagesList = Arrays.asList(new Package[]{new ReactAdapterPackage(), new ConstantsPackage(), new BasePackage(), new DevLauncherPackage(), new DevMenuPackage(), new FileSystemPackage(), new ImageLoaderPackage(), new KeepAwakePackage(), new LocalizationPackage(), new NavigationBarPackage(), new NotificationsPackage(), new SplashScreenPackage()});

        private LazyHolder() {
        }
    }

    public static List<Package> getPackageList() {
        return LazyHolder.packagesList;
    }

    public List<Class<? extends Module>> getModulesList() {
        return LazyHolder.modulesList;
    }
}
