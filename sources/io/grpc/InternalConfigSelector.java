package io.grpc;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.LoadBalancer;
import javax.annotation.Nullable;

public abstract class InternalConfigSelector {
    public static final Attributes.Key<InternalConfigSelector> KEY = Attributes.Key.create("internal:io.grpc.config-selector");

    public abstract Result selectConfig(LoadBalancer.PickSubchannelArgs pickSubchannelArgs);

    public static final class Result {
        private final Object config;
        @Nullable
        public ClientInterceptor interceptor;
        private final Status status;

        private Result(Status status2, Object obj, ClientInterceptor clientInterceptor) {
            this.status = (Status) Preconditions.checkNotNull(status2, "status");
            this.config = obj;
            this.interceptor = clientInterceptor;
        }

        public static Result forError(Status status2) {
            Preconditions.checkArgument(!status2.isOk(), "status is OK");
            return new Result(status2, (Object) null, (ClientInterceptor) null);
        }

        public Status getStatus() {
            return this.status;
        }

        public Object getConfig() {
            return this.config;
        }

        @Nullable
        public ClientInterceptor getInterceptor() {
            return this.interceptor;
        }

        public static Builder newBuilder() {
            return new Builder();
        }

        public static final class Builder {
            private Object config;
            private ClientInterceptor interceptor;

            private Builder() {
            }

            public Builder setConfig(Object obj) {
                this.config = Preconditions.checkNotNull(obj, "config");
                return this;
            }

            public Builder setInterceptor(ClientInterceptor clientInterceptor) {
                this.interceptor = (ClientInterceptor) Preconditions.checkNotNull(clientInterceptor, "interceptor");
                return this;
            }

            public Result build() {
                Preconditions.checkState(this.config != null, "config is not set");
                return new Result(Status.OK, this.config, this.interceptor);
            }
        }
    }
}
