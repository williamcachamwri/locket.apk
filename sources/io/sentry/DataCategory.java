package io.sentry;

import com.google.android.gms.common.Scopes;
import io.sentry.cache.EnvelopeCache;

public enum DataCategory {
    All("__all__"),
    Default("default"),
    Error("error"),
    Session(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE),
    Attachment("attachment"),
    Profile(Scopes.PROFILE),
    Transaction("transaction"),
    Security("security"),
    UserReport("user_report"),
    Unknown("unknown");
    
    private final String category;

    private DataCategory(String str) {
        this.category = str;
    }

    public String getCategory() {
        return this.category;
    }
}
