package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ManagedChannelProvider;
import io.grpc.ServiceProviders;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ManagedChannelRegistry {
    private static ManagedChannelRegistry instance;
    private static final Logger logger = Logger.getLogger(ManagedChannelRegistry.class.getName());
    private final LinkedHashSet<ManagedChannelProvider> allProviders = new LinkedHashSet<>();
    private List<ManagedChannelProvider> effectiveProviders = Collections.emptyList();

    public synchronized void register(ManagedChannelProvider managedChannelProvider) {
        addProvider(managedChannelProvider);
        refreshProviders();
    }

    private synchronized void addProvider(ManagedChannelProvider managedChannelProvider) {
        Preconditions.checkArgument(managedChannelProvider.isAvailable(), "isAvailable() returned false");
        this.allProviders.add(managedChannelProvider);
    }

    public synchronized void deregister(ManagedChannelProvider managedChannelProvider) {
        this.allProviders.remove(managedChannelProvider);
        refreshProviders();
    }

    private synchronized void refreshProviders() {
        ArrayList arrayList = new ArrayList(this.allProviders);
        Collections.sort(arrayList, Collections.reverseOrder(new Comparator<ManagedChannelProvider>() {
            public int compare(ManagedChannelProvider managedChannelProvider, ManagedChannelProvider managedChannelProvider2) {
                return managedChannelProvider.priority() - managedChannelProvider2.priority();
            }
        }));
        this.effectiveProviders = Collections.unmodifiableList(arrayList);
    }

    public static synchronized ManagedChannelRegistry getDefaultRegistry() {
        ManagedChannelRegistry managedChannelRegistry;
        synchronized (ManagedChannelRegistry.class) {
            if (instance == null) {
                List<T> loadAll = ServiceProviders.loadAll(ManagedChannelProvider.class, getHardCodedClasses(), ManagedChannelProvider.class.getClassLoader(), new ManagedChannelPriorityAccessor());
                instance = new ManagedChannelRegistry();
                for (T t : loadAll) {
                    logger.fine("Service loader found " + t);
                    instance.addProvider(t);
                }
                instance.refreshProviders();
            }
            managedChannelRegistry = instance;
        }
        return managedChannelRegistry;
    }

    /* access modifiers changed from: package-private */
    public synchronized List<ManagedChannelProvider> providers() {
        return this.effectiveProviders;
    }

    /* access modifiers changed from: package-private */
    public ManagedChannelProvider provider() {
        List<ManagedChannelProvider> providers = providers();
        if (providers.isEmpty()) {
            return null;
        }
        return providers.get(0);
    }

    static List<Class<?>> getHardCodedClasses() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(Class.forName("io.grpc.okhttp.OkHttpChannelProvider"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Unable to find OkHttpChannelProvider", e);
        }
        try {
            arrayList.add(Class.forName("io.grpc.netty.NettyChannelProvider"));
        } catch (ClassNotFoundException e2) {
            logger.log(Level.FINE, "Unable to find NettyChannelProvider", e2);
        }
        try {
            arrayList.add(Class.forName("io.grpc.netty.UdsNettyChannelProvider"));
        } catch (ClassNotFoundException e3) {
            logger.log(Level.FINE, "Unable to find UdsNettyChannelProvider", e3);
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* access modifiers changed from: package-private */
    public ManagedChannelBuilder<?> newChannelBuilder(String str, ChannelCredentials channelCredentials) {
        return newChannelBuilder(NameResolverRegistry.getDefaultRegistry(), str, channelCredentials);
    }

    /* access modifiers changed from: package-private */
    public ManagedChannelBuilder<?> newChannelBuilder(NameResolverRegistry nameResolverRegistry, String str, ChannelCredentials channelCredentials) {
        NameResolverProvider nameResolverProvider;
        Collection collection;
        try {
            nameResolverProvider = nameResolverRegistry.getProviderForScheme(new URI(str).getScheme());
        } catch (URISyntaxException unused) {
            nameResolverProvider = null;
        }
        if (nameResolverProvider == null) {
            nameResolverProvider = nameResolverRegistry.getProviderForScheme(nameResolverRegistry.getDefaultScheme());
        }
        if (nameResolverProvider != null) {
            collection = nameResolverProvider.getProducedSocketAddressTypes();
        } else {
            collection = Collections.emptySet();
        }
        if (!providers().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ManagedChannelProvider next : providers()) {
                if (!next.getSupportedSocketAddressTypes().containsAll(collection)) {
                    sb.append("; ");
                    sb.append(next.getClass().getName());
                    sb.append(": does not support 1 or more of ");
                    sb.append(Arrays.toString(collection.toArray()));
                } else {
                    ManagedChannelProvider.NewChannelBuilderResult newChannelBuilder = next.newChannelBuilder(str, channelCredentials);
                    if (newChannelBuilder.getChannelBuilder() != null) {
                        return newChannelBuilder.getChannelBuilder();
                    }
                    sb.append("; ");
                    sb.append(next.getClass().getName());
                    sb.append(": ");
                    sb.append(newChannelBuilder.getError());
                }
            }
            throw new ProviderNotFoundException(sb.substring(2));
        }
        throw new ProviderNotFoundException("No functional channel service provider found. Try adding a dependency on the grpc-okhttp, grpc-netty, or grpc-netty-shaded artifact");
    }

    private static final class ManagedChannelPriorityAccessor implements ServiceProviders.PriorityAccessor<ManagedChannelProvider> {
        private ManagedChannelPriorityAccessor() {
        }

        public boolean isAvailable(ManagedChannelProvider managedChannelProvider) {
            return managedChannelProvider.isAvailable();
        }

        public int getPriority(ManagedChannelProvider managedChannelProvider) {
            return managedChannelProvider.priority();
        }
    }

    public static final class ProviderNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ProviderNotFoundException(String str) {
            super(str);
        }
    }
}
