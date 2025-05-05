package io.grpc.internal;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import io.grpc.CallOptions;
import io.grpc.InternalConfigSelector;
import io.grpc.LoadBalancer;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.RetriableStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

final class ManagedChannelServiceConfig {
    @Nullable
    private final MethodInfo defaultMethodConfig;
    @Nullable
    private final Map<String, ?> healthCheckingConfig;
    @Nullable
    private final Object loadBalancingConfig;
    @Nullable
    private final RetriableStream.Throttle retryThrottling;
    private final Map<String, MethodInfo> serviceMap;
    private final Map<String, MethodInfo> serviceMethodMap;

    ManagedChannelServiceConfig(@Nullable MethodInfo methodInfo, Map<String, MethodInfo> map, Map<String, MethodInfo> map2, @Nullable RetriableStream.Throttle throttle, @Nullable Object obj, @Nullable Map<String, ?> map3) {
        this.defaultMethodConfig = methodInfo;
        this.serviceMethodMap = Collections.unmodifiableMap(new HashMap(map));
        this.serviceMap = Collections.unmodifiableMap(new HashMap(map2));
        this.retryThrottling = throttle;
        this.loadBalancingConfig = obj;
        this.healthCheckingConfig = map3 != null ? Collections.unmodifiableMap(new HashMap(map3)) : null;
    }

    static ManagedChannelServiceConfig empty() {
        return new ManagedChannelServiceConfig((MethodInfo) null, new HashMap(), new HashMap(), (RetriableStream.Throttle) null, (Object) null, (Map<String, ?>) null);
    }

    static ManagedChannelServiceConfig fromServiceConfig(Map<String, ?> map, boolean z, int i, int i2, @Nullable Object obj) {
        boolean z2 = z;
        RetriableStream.Throttle throttlePolicy = z2 ? ServiceConfigUtil.getThrottlePolicy(map) : null;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Map<String, ?> healthCheckedService = ServiceConfigUtil.getHealthCheckedService(map);
        List<Map<String, ?>> methodConfigFromServiceConfig = ServiceConfigUtil.getMethodConfigFromServiceConfig(map);
        if (methodConfigFromServiceConfig == null) {
            return new ManagedChannelServiceConfig((MethodInfo) null, hashMap, hashMap2, throttlePolicy, obj, healthCheckedService);
        }
        MethodInfo methodInfo = null;
        for (Map next : methodConfigFromServiceConfig) {
            MethodInfo methodInfo2 = new MethodInfo(next, z2, i, i2);
            List<Map<String, ?>> nameListFromMethodConfig = ServiceConfigUtil.getNameListFromMethodConfig(next);
            if (nameListFromMethodConfig != null && !nameListFromMethodConfig.isEmpty()) {
                for (Map next2 : nameListFromMethodConfig) {
                    String serviceFromName = ServiceConfigUtil.getServiceFromName(next2);
                    String methodFromName = ServiceConfigUtil.getMethodFromName(next2);
                    boolean z3 = true;
                    if (Strings.isNullOrEmpty(serviceFromName)) {
                        Preconditions.checkArgument(Strings.isNullOrEmpty(methodFromName), "missing service name for method %s", (Object) methodFromName);
                        if (methodInfo != null) {
                            z3 = false;
                        }
                        Preconditions.checkArgument(z3, "Duplicate default method config in service config %s", (Object) map);
                        methodInfo = methodInfo2;
                    } else {
                        Map<String, ?> map2 = map;
                        if (Strings.isNullOrEmpty(methodFromName)) {
                            Preconditions.checkArgument(!hashMap2.containsKey(serviceFromName), "Duplicate service %s", (Object) serviceFromName);
                            hashMap2.put(serviceFromName, methodInfo2);
                        } else {
                            String generateFullMethodName = MethodDescriptor.generateFullMethodName(serviceFromName, methodFromName);
                            Preconditions.checkArgument(!hashMap.containsKey(generateFullMethodName), "Duplicate method name %s", (Object) generateFullMethodName);
                            hashMap.put(generateFullMethodName, methodInfo2);
                        }
                    }
                }
            }
            Map<String, ?> map3 = map;
        }
        return new ManagedChannelServiceConfig(methodInfo, hashMap, hashMap2, throttlePolicy, obj, healthCheckedService);
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Map<String, ?> getHealthCheckingConfig() {
        return this.healthCheckingConfig;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public InternalConfigSelector getDefaultConfigSelector() {
        if (!this.serviceMap.isEmpty() || !this.serviceMethodMap.isEmpty() || this.defaultMethodConfig != null) {
            return new ServiceConfigConvertedSelector();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public Object getLoadBalancingConfig() {
        return this.loadBalancingConfig;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public RetriableStream.Throttle getRetryThrottling() {
        return this.retryThrottling;
    }

    /* access modifiers changed from: package-private */
    @Nullable
    public MethodInfo getMethodConfig(MethodDescriptor<?, ?> methodDescriptor) {
        MethodInfo methodInfo = this.serviceMethodMap.get(methodDescriptor.getFullMethodName());
        if (methodInfo == null) {
            methodInfo = this.serviceMap.get(methodDescriptor.getServiceName());
        }
        return methodInfo == null ? this.defaultMethodConfig : methodInfo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ManagedChannelServiceConfig managedChannelServiceConfig = (ManagedChannelServiceConfig) obj;
        if (!Objects.equal(this.defaultMethodConfig, managedChannelServiceConfig.defaultMethodConfig) || !Objects.equal(this.serviceMethodMap, managedChannelServiceConfig.serviceMethodMap) || !Objects.equal(this.serviceMap, managedChannelServiceConfig.serviceMap) || !Objects.equal(this.retryThrottling, managedChannelServiceConfig.retryThrottling) || !Objects.equal(this.loadBalancingConfig, managedChannelServiceConfig.loadBalancingConfig)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Objects.hashCode(this.defaultMethodConfig, this.serviceMethodMap, this.serviceMap, this.retryThrottling, this.loadBalancingConfig);
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object) this).add("defaultMethodConfig", (Object) this.defaultMethodConfig).add("serviceMethodMap", (Object) this.serviceMethodMap).add("serviceMap", (Object) this.serviceMap).add("retryThrottling", (Object) this.retryThrottling).add("loadBalancingConfig", this.loadBalancingConfig).toString();
    }

    static final class MethodInfo {
        static final CallOptions.Key<MethodInfo> KEY = CallOptions.Key.create("io.grpc.internal.ManagedChannelServiceConfig.MethodInfo");
        final HedgingPolicy hedgingPolicy;
        final Integer maxInboundMessageSize;
        final Integer maxOutboundMessageSize;
        final RetryPolicy retryPolicy;
        final Long timeoutNanos;
        final Boolean waitForReady;

        MethodInfo(Map<String, ?> map, boolean z, int i, int i2) {
            RetryPolicy retryPolicy2;
            this.timeoutNanos = ServiceConfigUtil.getTimeoutFromMethodConfig(map);
            this.waitForReady = ServiceConfigUtil.getWaitForReadyFromMethodConfig(map);
            Integer maxResponseMessageBytesFromMethodConfig = ServiceConfigUtil.getMaxResponseMessageBytesFromMethodConfig(map);
            this.maxInboundMessageSize = maxResponseMessageBytesFromMethodConfig;
            boolean z2 = true;
            if (maxResponseMessageBytesFromMethodConfig != null) {
                Preconditions.checkArgument(maxResponseMessageBytesFromMethodConfig.intValue() >= 0, "maxInboundMessageSize %s exceeds bounds", (Object) maxResponseMessageBytesFromMethodConfig);
            }
            Integer maxRequestMessageBytesFromMethodConfig = ServiceConfigUtil.getMaxRequestMessageBytesFromMethodConfig(map);
            this.maxOutboundMessageSize = maxRequestMessageBytesFromMethodConfig;
            if (maxRequestMessageBytesFromMethodConfig != null) {
                Preconditions.checkArgument(maxRequestMessageBytesFromMethodConfig.intValue() < 0 ? false : z2, "maxOutboundMessageSize %s exceeds bounds", (Object) maxRequestMessageBytesFromMethodConfig);
            }
            HedgingPolicy hedgingPolicy2 = null;
            Map<String, ?> retryPolicyFromMethodConfig = z ? ServiceConfigUtil.getRetryPolicyFromMethodConfig(map) : null;
            if (retryPolicyFromMethodConfig == null) {
                retryPolicy2 = null;
            } else {
                retryPolicy2 = retryPolicy(retryPolicyFromMethodConfig, i);
            }
            this.retryPolicy = retryPolicy2;
            Map<String, ?> hedgingPolicyFromMethodConfig = z ? ServiceConfigUtil.getHedgingPolicyFromMethodConfig(map) : null;
            this.hedgingPolicy = hedgingPolicyFromMethodConfig != null ? hedgingPolicy(hedgingPolicyFromMethodConfig, i2) : hedgingPolicy2;
        }

        public int hashCode() {
            return Objects.hashCode(this.timeoutNanos, this.waitForReady, this.maxInboundMessageSize, this.maxOutboundMessageSize, this.retryPolicy, this.hedgingPolicy);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof MethodInfo)) {
                return false;
            }
            MethodInfo methodInfo = (MethodInfo) obj;
            if (!Objects.equal(this.timeoutNanos, methodInfo.timeoutNanos) || !Objects.equal(this.waitForReady, methodInfo.waitForReady) || !Objects.equal(this.maxInboundMessageSize, methodInfo.maxInboundMessageSize) || !Objects.equal(this.maxOutboundMessageSize, methodInfo.maxOutboundMessageSize) || !Objects.equal(this.retryPolicy, methodInfo.retryPolicy) || !Objects.equal(this.hedgingPolicy, methodInfo.hedgingPolicy)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("timeoutNanos", (Object) this.timeoutNanos).add("waitForReady", (Object) this.waitForReady).add("maxInboundMessageSize", (Object) this.maxInboundMessageSize).add("maxOutboundMessageSize", (Object) this.maxOutboundMessageSize).add("retryPolicy", (Object) this.retryPolicy).add("hedgingPolicy", (Object) this.hedgingPolicy).toString();
        }

        private static RetryPolicy retryPolicy(Map<String, ?> map, int i) {
            int intValue = ((Integer) Preconditions.checkNotNull(ServiceConfigUtil.getMaxAttemptsFromRetryPolicy(map), "maxAttempts cannot be empty")).intValue();
            boolean z = true;
            Preconditions.checkArgument(intValue >= 2, "maxAttempts must be greater than 1: %s", intValue);
            int min = Math.min(intValue, i);
            long longValue = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getInitialBackoffNanosFromRetryPolicy(map), "initialBackoff cannot be empty")).longValue();
            Preconditions.checkArgument(longValue > 0, "initialBackoffNanos must be greater than 0: %s", longValue);
            long longValue2 = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getMaxBackoffNanosFromRetryPolicy(map), "maxBackoff cannot be empty")).longValue();
            Preconditions.checkArgument(longValue2 > 0, "maxBackoff must be greater than 0: %s", longValue2);
            double doubleValue = ((Double) Preconditions.checkNotNull(ServiceConfigUtil.getBackoffMultiplierFromRetryPolicy(map), "backoffMultiplier cannot be empty")).doubleValue();
            Preconditions.checkArgument(doubleValue > 0.0d, "backoffMultiplier must be greater than 0: %s", (Object) Double.valueOf(doubleValue));
            Long perAttemptRecvTimeoutNanosFromRetryPolicy = ServiceConfigUtil.getPerAttemptRecvTimeoutNanosFromRetryPolicy(map);
            Preconditions.checkArgument(perAttemptRecvTimeoutNanosFromRetryPolicy == null || perAttemptRecvTimeoutNanosFromRetryPolicy.longValue() >= 0, "perAttemptRecvTimeout cannot be negative: %s", (Object) perAttemptRecvTimeoutNanosFromRetryPolicy);
            Set<Status.Code> retryableStatusCodesFromRetryPolicy = ServiceConfigUtil.getRetryableStatusCodesFromRetryPolicy(map);
            if (perAttemptRecvTimeoutNanosFromRetryPolicy == null && retryableStatusCodesFromRetryPolicy.isEmpty()) {
                z = false;
            }
            Preconditions.checkArgument(z, "retryableStatusCodes cannot be empty without perAttemptRecvTimeout");
            return new RetryPolicy(min, longValue, longValue2, doubleValue, perAttemptRecvTimeoutNanosFromRetryPolicy, retryableStatusCodesFromRetryPolicy);
        }

        private static HedgingPolicy hedgingPolicy(Map<String, ?> map, int i) {
            int intValue = ((Integer) Preconditions.checkNotNull(ServiceConfigUtil.getMaxAttemptsFromHedgingPolicy(map), "maxAttempts cannot be empty")).intValue();
            boolean z = true;
            Preconditions.checkArgument(intValue >= 2, "maxAttempts must be greater than 1: %s", intValue);
            int min = Math.min(intValue, i);
            long longValue = ((Long) Preconditions.checkNotNull(ServiceConfigUtil.getHedgingDelayNanosFromHedgingPolicy(map), "hedgingDelay cannot be empty")).longValue();
            if (longValue < 0) {
                z = false;
            }
            Preconditions.checkArgument(z, "hedgingDelay must not be negative: %s", longValue);
            return new HedgingPolicy(min, longValue, ServiceConfigUtil.getNonFatalStatusCodesFromHedgingPolicy(map));
        }
    }

    static final class ServiceConfigConvertedSelector extends InternalConfigSelector {
        final ManagedChannelServiceConfig config;

        private ServiceConfigConvertedSelector(ManagedChannelServiceConfig managedChannelServiceConfig) {
            this.config = managedChannelServiceConfig;
        }

        public InternalConfigSelector.Result selectConfig(LoadBalancer.PickSubchannelArgs pickSubchannelArgs) {
            return InternalConfigSelector.Result.newBuilder().setConfig(this.config).build();
        }
    }
}
