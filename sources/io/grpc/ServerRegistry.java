package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.ServerProvider;
import io.grpc.ServiceProviders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ServerRegistry {
    private static ServerRegistry instance;
    private static final Logger logger = Logger.getLogger(ServerRegistry.class.getName());
    private final LinkedHashSet<ServerProvider> allProviders = new LinkedHashSet<>();
    private List<ServerProvider> effectiveProviders = Collections.emptyList();

    public synchronized void register(ServerProvider serverProvider) {
        addProvider(serverProvider);
        refreshProviders();
    }

    private synchronized void addProvider(ServerProvider serverProvider) {
        Preconditions.checkArgument(serverProvider.isAvailable(), "isAvailable() returned false");
        this.allProviders.add(serverProvider);
    }

    public synchronized void deregister(ServerProvider serverProvider) {
        this.allProviders.remove(serverProvider);
        refreshProviders();
    }

    private synchronized void refreshProviders() {
        ArrayList arrayList = new ArrayList(this.allProviders);
        Collections.sort(arrayList, Collections.reverseOrder(new Comparator<ServerProvider>() {
            public int compare(ServerProvider serverProvider, ServerProvider serverProvider2) {
                return serverProvider.priority() - serverProvider2.priority();
            }
        }));
        this.effectiveProviders = Collections.unmodifiableList(arrayList);
    }

    public static synchronized ServerRegistry getDefaultRegistry() {
        ServerRegistry serverRegistry;
        synchronized (ServerRegistry.class) {
            if (instance == null) {
                List<T> loadAll = ServiceProviders.loadAll(ServerProvider.class, getHardCodedClasses(), ServerProvider.class.getClassLoader(), new ServerPriorityAccessor());
                instance = new ServerRegistry();
                for (T t : loadAll) {
                    logger.fine("Service loader found " + t);
                    instance.addProvider(t);
                }
                instance.refreshProviders();
            }
            serverRegistry = instance;
        }
        return serverRegistry;
    }

    /* access modifiers changed from: package-private */
    public synchronized List<ServerProvider> providers() {
        return this.effectiveProviders;
    }

    /* access modifiers changed from: package-private */
    public ServerProvider provider() {
        List<ServerProvider> providers = providers();
        if (providers.isEmpty()) {
            return null;
        }
        return providers.get(0);
    }

    static List<Class<?>> getHardCodedClasses() {
        ArrayList arrayList = new ArrayList();
        try {
            arrayList.add(Class.forName("io.grpc.okhttp.OkHttpServerProvider"));
        } catch (ClassNotFoundException e) {
            logger.log(Level.FINE, "Unable to find OkHttpServerProvider", e);
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* access modifiers changed from: package-private */
    public ServerBuilder<?> newServerBuilderForPort(int i, ServerCredentials serverCredentials) {
        if (!providers().isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ServerProvider next : providers()) {
                ServerProvider.NewServerBuilderResult newServerBuilderForPort = next.newServerBuilderForPort(i, serverCredentials);
                if (newServerBuilderForPort.getServerBuilder() != null) {
                    return newServerBuilderForPort.getServerBuilder();
                }
                sb.append("; ");
                sb.append(next.getClass().getName());
                sb.append(": ");
                sb.append(newServerBuilderForPort.getError());
            }
            throw new ProviderNotFoundException(sb.substring(2));
        }
        throw new ProviderNotFoundException("No functional server found. Try adding a dependency on the grpc-netty, grpc-netty-shaded, or grpc-okhttp artifact");
    }

    private static final class ServerPriorityAccessor implements ServiceProviders.PriorityAccessor<ServerProvider> {
        private ServerPriorityAccessor() {
        }

        public boolean isAvailable(ServerProvider serverProvider) {
            return serverProvider.isAvailable();
        }

        public int getPriority(ServerProvider serverProvider) {
            return serverProvider.priority();
        }
    }

    public static final class ProviderNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public ProviderNotFoundException(String str) {
            super(str);
        }
    }
}
