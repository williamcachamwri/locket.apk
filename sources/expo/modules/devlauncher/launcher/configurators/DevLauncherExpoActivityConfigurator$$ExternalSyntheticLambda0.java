package expo.modules.devlauncher.launcher.configurators;

import com.facebook.react.ReactActivity;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DevLauncherExpoActivityConfigurator$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ ReactActivity f$0;
    public final /* synthetic */ DevLauncherExpoActivityConfigurator f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ boolean f$3;
    public final /* synthetic */ String f$4;
    public final /* synthetic */ String f$5;

    public /* synthetic */ DevLauncherExpoActivityConfigurator$$ExternalSyntheticLambda0(ReactActivity reactActivity, DevLauncherExpoActivityConfigurator devLauncherExpoActivityConfigurator, boolean z, boolean z2, String str, String str2) {
        this.f$0 = reactActivity;
        this.f$1 = devLauncherExpoActivityConfigurator;
        this.f$2 = z;
        this.f$3 = z2;
        this.f$4 = str;
        this.f$5 = str2;
    }

    public final void run() {
        DevLauncherExpoActivityConfigurator.applyStatusBarConfiguration$lambda$0(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
