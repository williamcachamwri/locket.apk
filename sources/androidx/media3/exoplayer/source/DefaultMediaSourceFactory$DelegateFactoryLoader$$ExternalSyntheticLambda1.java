package androidx.media3.exoplayer.source;

import androidx.media3.datasource.DataSource;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda1 implements Supplier {
    public final /* synthetic */ Class f$0;
    public final /* synthetic */ DataSource.Factory f$1;

    public /* synthetic */ DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda1(Class cls, DataSource.Factory factory) {
        this.f$0 = cls;
        this.f$1 = factory;
    }

    public final Object get() {
        return DefaultMediaSourceFactory.newInstance(this.f$0, this.f$1);
    }
}
