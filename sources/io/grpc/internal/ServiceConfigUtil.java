package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Verify;
import com.google.common.base.VerifyException;
import io.grpc.LoadBalancerProvider;
import io.grpc.LoadBalancerRegistry;
import io.grpc.NameResolver;
import io.grpc.Status;
import io.grpc.internal.RetriableStream;
import io.sentry.ProfilingTraceData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

public final class ServiceConfigUtil {
    private ServiceConfigUtil() {
    }

    @Nullable
    public static Map<String, ?> getHealthCheckedService(@Nullable Map<String, ?> map) {
        if (map == null) {
            return null;
        }
        return JsonUtil.getObject(map, "healthCheckConfig");
    }

    @Nullable
    public static String getHealthCheckedServiceName(@Nullable Map<String, ?> map) {
        if (map == null) {
            return null;
        }
        return JsonUtil.getString(map, "serviceName");
    }

    @Nullable
    static RetriableStream.Throttle getThrottlePolicy(@Nullable Map<String, ?> map) {
        Map<String, ?> object;
        if (map == null || (object = JsonUtil.getObject(map, "retryThrottling")) == null) {
            return null;
        }
        float floatValue = JsonUtil.getNumberAsDouble(object, "maxTokens").floatValue();
        float floatValue2 = JsonUtil.getNumberAsDouble(object, "tokenRatio").floatValue();
        boolean z = true;
        Preconditions.checkState(floatValue > 0.0f, "maxToken should be greater than zero");
        if (floatValue2 <= 0.0f) {
            z = false;
        }
        Preconditions.checkState(z, "tokenRatio should be greater than zero");
        return new RetriableStream.Throttle(floatValue, floatValue2);
    }

    @Nullable
    static Integer getMaxAttemptsFromRetryPolicy(Map<String, ?> map) {
        return JsonUtil.getNumberAsInteger(map, "maxAttempts");
    }

    @Nullable
    static Long getInitialBackoffNanosFromRetryPolicy(Map<String, ?> map) {
        return JsonUtil.getStringAsDuration(map, "initialBackoff");
    }

    @Nullable
    static Long getMaxBackoffNanosFromRetryPolicy(Map<String, ?> map) {
        return JsonUtil.getStringAsDuration(map, "maxBackoff");
    }

    @Nullable
    static Double getBackoffMultiplierFromRetryPolicy(Map<String, ?> map) {
        return JsonUtil.getNumberAsDouble(map, "backoffMultiplier");
    }

    @Nullable
    static Long getPerAttemptRecvTimeoutNanosFromRetryPolicy(Map<String, ?> map) {
        return JsonUtil.getStringAsDuration(map, "perAttemptRecvTimeout");
    }

    private static Set<Status.Code> getListOfStatusCodesAsSet(Map<String, ?> map, String str) {
        List<?> list = JsonUtil.getList(map, str);
        if (list == null) {
            return null;
        }
        return getStatusCodesFromList(list);
    }

    private static Set<Status.Code> getStatusCodesFromList(List<?> list) {
        Status.Code code;
        EnumSet<E> noneOf = EnumSet.noneOf(Status.Code.class);
        for (Object next : list) {
            if (next instanceof Double) {
                Double d = (Double) next;
                int intValue = d.intValue();
                boolean z = true;
                Verify.verify(((double) intValue) == d.doubleValue(), "Status code %s is not integral", (Object) next);
                code = Status.fromCodeValue(intValue).getCode();
                if (code.value() != d.intValue()) {
                    z = false;
                }
                Verify.verify(z, "Status code %s is not valid", (Object) next);
            } else if (next instanceof String) {
                try {
                    code = Status.Code.valueOf((String) next);
                } catch (IllegalArgumentException e) {
                    throw new VerifyException("Status code " + next + " is not valid", e);
                }
            } else {
                throw new VerifyException("Can not convert status code " + next + " to Status.Code, because its type is " + next.getClass());
            }
            noneOf.add(code);
        }
        return Collections.unmodifiableSet(noneOf);
    }

    static Set<Status.Code> getRetryableStatusCodesFromRetryPolicy(Map<String, ?> map) {
        Set<Status.Code> listOfStatusCodesAsSet = getListOfStatusCodesAsSet(map, "retryableStatusCodes");
        Verify.verify(listOfStatusCodesAsSet != null, "%s is required in retry policy", (Object) "retryableStatusCodes");
        Verify.verify(true ^ listOfStatusCodesAsSet.contains(Status.Code.OK), "%s must not contain OK", (Object) "retryableStatusCodes");
        return listOfStatusCodesAsSet;
    }

    @Nullable
    static Integer getMaxAttemptsFromHedgingPolicy(Map<String, ?> map) {
        return JsonUtil.getNumberAsInteger(map, "maxAttempts");
    }

    @Nullable
    static Long getHedgingDelayNanosFromHedgingPolicy(Map<String, ?> map) {
        return JsonUtil.getStringAsDuration(map, "hedgingDelay");
    }

    static Set<Status.Code> getNonFatalStatusCodesFromHedgingPolicy(Map<String, ?> map) {
        Set<Status.Code> listOfStatusCodesAsSet = getListOfStatusCodesAsSet(map, "nonFatalStatusCodes");
        if (listOfStatusCodesAsSet == null) {
            return Collections.unmodifiableSet(EnumSet.noneOf(Status.Code.class));
        }
        Verify.verify(!listOfStatusCodesAsSet.contains(Status.Code.OK), "%s must not contain OK", (Object) "nonFatalStatusCodes");
        return listOfStatusCodesAsSet;
    }

    @Nullable
    static String getServiceFromName(Map<String, ?> map) {
        return JsonUtil.getString(map, NotificationCompat.CATEGORY_SERVICE);
    }

    @Nullable
    static String getMethodFromName(Map<String, ?> map) {
        return JsonUtil.getString(map, "method");
    }

    @Nullable
    static Map<String, ?> getRetryPolicyFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getObject(map, "retryPolicy");
    }

    @Nullable
    static Map<String, ?> getHedgingPolicyFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getObject(map, "hedgingPolicy");
    }

    @Nullable
    static List<Map<String, ?>> getNameListFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getListOfObjects(map, "name");
    }

    @Nullable
    static Long getTimeoutFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getStringAsDuration(map, ProfilingTraceData.TRUNCATION_REASON_TIMEOUT);
    }

    @Nullable
    static Boolean getWaitForReadyFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getBoolean(map, "waitForReady");
    }

    @Nullable
    static Integer getMaxRequestMessageBytesFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getNumberAsInteger(map, "maxRequestMessageBytes");
    }

    @Nullable
    static Integer getMaxResponseMessageBytesFromMethodConfig(Map<String, ?> map) {
        return JsonUtil.getNumberAsInteger(map, "maxResponseMessageBytes");
    }

    @Nullable
    static List<Map<String, ?>> getMethodConfigFromServiceConfig(Map<String, ?> map) {
        return JsonUtil.getListOfObjects(map, "methodConfig");
    }

    public static List<Map<String, ?>> getLoadBalancingConfigsFromServiceConfig(Map<String, ?> map) {
        String string;
        ArrayList arrayList = new ArrayList();
        if (map.containsKey("loadBalancingConfig")) {
            arrayList.addAll(JsonUtil.getListOfObjects(map, "loadBalancingConfig"));
        }
        if (arrayList.isEmpty() && (string = JsonUtil.getString(map, "loadBalancingPolicy")) != null) {
            arrayList.add(Collections.singletonMap(string.toLowerCase(Locale.ROOT), Collections.emptyMap()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static LbConfig unwrapLoadBalancingConfig(Map<String, ?> map) {
        if (map.size() == 1) {
            String str = (String) map.entrySet().iterator().next().getKey();
            return new LbConfig(str, JsonUtil.getObject(map, str));
        }
        throw new RuntimeException("There are " + map.size() + " fields in a LoadBalancingConfig object. Exactly one is expected. Config=" + map);
    }

    public static List<LbConfig> unwrapLoadBalancingConfigList(List<Map<String, ?>> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Map<String, ?> unwrapLoadBalancingConfig : list) {
            arrayList.add(unwrapLoadBalancingConfig(unwrapLoadBalancingConfig));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public static NameResolver.ConfigOrError selectLbPolicyFromList(List<LbConfig> list, LoadBalancerRegistry loadBalancerRegistry) {
        ArrayList arrayList = new ArrayList();
        for (LbConfig next : list) {
            String policyName = next.getPolicyName();
            LoadBalancerProvider provider = loadBalancerRegistry.getProvider(policyName);
            if (provider == null) {
                arrayList.add(policyName);
            } else {
                if (!arrayList.isEmpty()) {
                    Logger.getLogger(ServiceConfigUtil.class.getName()).log(Level.FINEST, "{0} specified by Service Config are not available", arrayList);
                }
                NameResolver.ConfigOrError parseLoadBalancingPolicyConfig = provider.parseLoadBalancingPolicyConfig(next.getRawConfigValue());
                if (parseLoadBalancingPolicyConfig.getError() != null) {
                    return parseLoadBalancingPolicyConfig;
                }
                return NameResolver.ConfigOrError.fromConfig(new PolicySelection(provider, parseLoadBalancingPolicyConfig.getConfig()));
            }
        }
        return NameResolver.ConfigOrError.fromError(Status.UNKNOWN.withDescription("None of " + arrayList + " specified by Service Config are available."));
    }

    public static final class LbConfig {
        private final String policyName;
        private final Map<String, ?> rawConfigValue;

        public LbConfig(String str, Map<String, ?> map) {
            this.policyName = (String) Preconditions.checkNotNull(str, "policyName");
            this.rawConfigValue = (Map) Preconditions.checkNotNull(map, "rawConfigValue");
        }

        public String getPolicyName() {
            return this.policyName;
        }

        public Map<String, ?> getRawConfigValue() {
            return this.rawConfigValue;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof LbConfig)) {
                return false;
            }
            LbConfig lbConfig = (LbConfig) obj;
            if (!this.policyName.equals(lbConfig.policyName) || !this.rawConfigValue.equals(lbConfig.rawConfigValue)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.policyName, this.rawConfigValue);
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("policyName", (Object) this.policyName).add("rawConfigValue", (Object) this.rawConfigValue).toString();
        }
    }

    public static final class PolicySelection {
        @Nullable
        final Object config;
        final LoadBalancerProvider provider;

        public PolicySelection(LoadBalancerProvider loadBalancerProvider, @Nullable Object obj) {
            this.provider = (LoadBalancerProvider) Preconditions.checkNotNull(loadBalancerProvider, "provider");
            this.config = obj;
        }

        public LoadBalancerProvider getProvider() {
            return this.provider;
        }

        @Nullable
        public Object getConfig() {
            return this.config;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            PolicySelection policySelection = (PolicySelection) obj;
            if (!Objects.equal(this.provider, policySelection.provider) || !Objects.equal(this.config, policySelection.config)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return Objects.hashCode(this.provider, this.config);
        }

        public String toString() {
            return MoreObjects.toStringHelper((Object) this).add("provider", (Object) this.provider).add("config", this.config).toString();
        }
    }
}
