package com.facebook.react.devsupport;

import android.content.DialogInterface;
import com.facebook.react.devsupport.interfaces.DevOptionHandler;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DevSupportManagerBase$$ExternalSyntheticLambda5 implements DialogInterface.OnClickListener {
    public final /* synthetic */ DevSupportManagerBase f$0;
    public final /* synthetic */ DevOptionHandler[] f$1;

    public /* synthetic */ DevSupportManagerBase$$ExternalSyntheticLambda5(DevSupportManagerBase devSupportManagerBase, DevOptionHandler[] devOptionHandlerArr) {
        this.f$0 = devSupportManagerBase;
        this.f$1 = devOptionHandlerArr;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f$0.lambda$showDevOptionsDialog$8(this.f$1, dialogInterface, i);
    }
}
