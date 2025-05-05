package io.grpc;

import com.google.common.base.MoreObjects;
import io.grpc.ServerBuilder;
import io.grpc.ServerStreamTracer;
import java.io.File;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public abstract class ForwardingServerBuilder<T extends ServerBuilder<T>> extends ServerBuilder<T> {
    private T thisT() {
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract ServerBuilder<?> delegate();

    protected ForwardingServerBuilder() {
    }

    public static ServerBuilder<?> forPort(int i) {
        throw new UnsupportedOperationException("Subclass failed to hide static factory");
    }

    public T directExecutor() {
        delegate().directExecutor();
        return thisT();
    }

    public T executor(@Nullable Executor executor) {
        delegate().executor(executor);
        return thisT();
    }

    public T callExecutor(ServerCallExecutorSupplier serverCallExecutorSupplier) {
        delegate().callExecutor(serverCallExecutorSupplier);
        return thisT();
    }

    public T addService(ServerServiceDefinition serverServiceDefinition) {
        delegate().addService(serverServiceDefinition);
        return thisT();
    }

    public T addService(BindableService bindableService) {
        delegate().addService(bindableService);
        return thisT();
    }

    public T intercept(ServerInterceptor serverInterceptor) {
        delegate().intercept(serverInterceptor);
        return thisT();
    }

    public T addTransportFilter(ServerTransportFilter serverTransportFilter) {
        delegate().addTransportFilter(serverTransportFilter);
        return thisT();
    }

    public T addStreamTracerFactory(ServerStreamTracer.Factory factory) {
        delegate().addStreamTracerFactory(factory);
        return thisT();
    }

    public T fallbackHandlerRegistry(@Nullable HandlerRegistry handlerRegistry) {
        delegate().fallbackHandlerRegistry(handlerRegistry);
        return thisT();
    }

    public T useTransportSecurity(File file, File file2) {
        delegate().useTransportSecurity(file, file2);
        return thisT();
    }

    public T useTransportSecurity(InputStream inputStream, InputStream inputStream2) {
        delegate().useTransportSecurity(inputStream, inputStream2);
        return thisT();
    }

    public T decompressorRegistry(@Nullable DecompressorRegistry decompressorRegistry) {
        delegate().decompressorRegistry(decompressorRegistry);
        return thisT();
    }

    public T compressorRegistry(@Nullable CompressorRegistry compressorRegistry) {
        delegate().compressorRegistry(compressorRegistry);
        return thisT();
    }

    public T handshakeTimeout(long j, TimeUnit timeUnit) {
        delegate().handshakeTimeout(j, timeUnit);
        return thisT();
    }

    public T keepAliveTime(long j, TimeUnit timeUnit) {
        delegate().keepAliveTime(j, timeUnit);
        return thisT();
    }

    public T keepAliveTimeout(long j, TimeUnit timeUnit) {
        delegate().keepAliveTimeout(j, timeUnit);
        return thisT();
    }

    public T maxConnectionIdle(long j, TimeUnit timeUnit) {
        delegate().maxConnectionIdle(j, timeUnit);
        return thisT();
    }

    public T maxConnectionAge(long j, TimeUnit timeUnit) {
        delegate().maxConnectionAge(j, timeUnit);
        return thisT();
    }

    public T maxConnectionAgeGrace(long j, TimeUnit timeUnit) {
        delegate().maxConnectionAgeGrace(j, timeUnit);
        return thisT();
    }

    public T permitKeepAliveTime(long j, TimeUnit timeUnit) {
        delegate().permitKeepAliveTime(j, timeUnit);
        return thisT();
    }

    public T permitKeepAliveWithoutCalls(boolean z) {
        delegate().permitKeepAliveWithoutCalls(z);
        return thisT();
    }

    public T maxInboundMessageSize(int i) {
        delegate().maxInboundMessageSize(i);
        return thisT();
    }

    public T maxInboundMetadataSize(int i) {
        delegate().maxInboundMetadataSize(i);
        return thisT();
    }

    public T setBinaryLog(BinaryLog binaryLog) {
        delegate().setBinaryLog(binaryLog);
        return thisT();
    }

    public Server build() {
        return delegate().build();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("delegate", (Object) delegate()).toString();
    }
}
