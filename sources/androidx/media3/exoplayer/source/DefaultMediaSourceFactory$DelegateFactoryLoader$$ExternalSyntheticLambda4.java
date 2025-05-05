package androidx.media3.exoplayer.source;

import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory;
import com.google.common.base.Supplier;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda4 implements Supplier {
    public final /* synthetic */ DefaultMediaSourceFactory.DelegateFactoryLoader f$0;
    public final /* synthetic */ DataSource.Factory f$1;

    public /* synthetic */ DefaultMediaSourceFactory$DelegateFactoryLoader$$ExternalSyntheticLambda4(DefaultMediaSourceFactory.DelegateFactoryLoader delegateFactoryLoader, DataSource.Factory factory) {
        this.f$0 = delegateFactoryLoader;
        this.f$1 = factory;
    }

    public final Object get() {
        return this.f$0.m856lambda$loadSupplier$4$androidxmedia3exoplayersourceDefaultMediaSourceFactory$DelegateFactoryLoader(this.f$1);
    }
}
