package com.google.firebase.firestore;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class TransactionOptions {
    static final TransactionOptions DEFAULT = new Builder().build();
    static final int DEFAULT_MAX_ATTEMPTS_COUNT = 5;
    /* access modifiers changed from: private */
    public final int maxAttempts;

    private TransactionOptions(int i) {
        this.maxAttempts = i;
    }

    public static final class Builder {
        private int maxAttempts = 5;

        public Builder() {
        }

        public Builder(TransactionOptions transactionOptions) {
            this.maxAttempts = transactionOptions.maxAttempts;
        }

        public Builder setMaxAttempts(int i) {
            if (i >= 1) {
                this.maxAttempts = i;
                return this;
            }
            throw new IllegalArgumentException("Max attempts must be at least 1");
        }

        public TransactionOptions build() {
            return new TransactionOptions(this.maxAttempts);
        }
    }

    public int getMaxAttempts() {
        return this.maxAttempts;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.maxAttempts == ((TransactionOptions) obj).maxAttempts) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.maxAttempts;
    }

    public String toString() {
        return "TransactionOptions{maxAttempts=" + this.maxAttempts + AbstractJsonLexerKt.END_OBJ;
    }
}
