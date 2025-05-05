package com.google.firebase.functions;

public class HttpsCallableOptions {
    private final boolean limitedUseAppCheckTokens;

    private HttpsCallableOptions(boolean z) {
        this.limitedUseAppCheckTokens = z;
    }

    public boolean getLimitedUseAppCheckTokens() {
        return this.limitedUseAppCheckTokens;
    }

    public static class Builder {
        private boolean limitedUseAppCheckTokens = false;

        public Builder setLimitedUseAppCheckTokens(boolean z) {
            this.limitedUseAppCheckTokens = z;
            return this;
        }

        public boolean getLimitedUseAppCheckTokens() {
            return this.limitedUseAppCheckTokens;
        }

        public HttpsCallableOptions build() {
            return new HttpsCallableOptions(this.limitedUseAppCheckTokens);
        }
    }
}
