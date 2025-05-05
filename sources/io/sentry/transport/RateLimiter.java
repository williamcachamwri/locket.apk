package io.sentry.transport;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.Scopes;
import io.sentry.DataCategory;
import io.sentry.Hint;
import io.sentry.SentryEnvelope;
import io.sentry.SentryEnvelopeItem;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.cache.EnvelopeCache;
import io.sentry.clientreport.DiscardReason;
import io.sentry.hints.Retryable;
import io.sentry.hints.SubmissionResult;
import io.sentry.util.HintUtils;
import io.sentry.util.StringUtils;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class RateLimiter {
    private static final int HTTP_RETRY_AFTER_DEFAULT_DELAY_MILLIS = 60000;
    private final ICurrentDateProvider currentDateProvider;
    private final SentryOptions options;
    private final Map<DataCategory, Date> sentryRetryAfterLimit;

    public RateLimiter(ICurrentDateProvider iCurrentDateProvider, SentryOptions sentryOptions) {
        this.sentryRetryAfterLimit = new ConcurrentHashMap();
        this.currentDateProvider = iCurrentDateProvider;
        this.options = sentryOptions;
    }

    public RateLimiter(SentryOptions sentryOptions) {
        this(CurrentDateProvider.getInstance(), sentryOptions);
    }

    public SentryEnvelope filter(SentryEnvelope sentryEnvelope, Hint hint) {
        ArrayList arrayList = null;
        for (SentryEnvelopeItem next : sentryEnvelope.getItems()) {
            if (isRetryAfter(next.getHeader().getType().getItemType())) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(next);
                this.options.getClientReportRecorder().recordLostEnvelopeItem(DiscardReason.RATELIMIT_BACKOFF, next);
            }
        }
        if (arrayList == null) {
            return sentryEnvelope;
        }
        this.options.getLogger().log(SentryLevel.INFO, "%d items will be dropped due rate limiting.", Integer.valueOf(arrayList.size()));
        ArrayList arrayList2 = new ArrayList();
        for (SentryEnvelopeItem next2 : sentryEnvelope.getItems()) {
            if (!arrayList.contains(next2)) {
                arrayList2.add(next2);
            }
        }
        if (!arrayList2.isEmpty()) {
            return new SentryEnvelope(sentryEnvelope.getHeader(), arrayList2);
        }
        this.options.getLogger().log(SentryLevel.INFO, "Envelope discarded due all items rate limited.", new Object[0]);
        markHintWhenSendingFailed(hint, false);
        return null;
    }

    private static void markHintWhenSendingFailed(Hint hint, boolean z) {
        HintUtils.runIfHasType(hint, SubmissionResult.class, new RateLimiter$$ExternalSyntheticLambda0());
        HintUtils.runIfHasType(hint, Retryable.class, new RateLimiter$$ExternalSyntheticLambda1(z));
    }

    private boolean isRetryAfter(String str) {
        Date date;
        DataCategory categoryFromItemType = getCategoryFromItemType(str);
        Date date2 = new Date(this.currentDateProvider.getCurrentTimeMillis());
        Date date3 = this.sentryRetryAfterLimit.get(DataCategory.All);
        if (date3 != null && !date2.after(date3)) {
            return true;
        }
        if (!DataCategory.Unknown.equals(categoryFromItemType) && (date = this.sentryRetryAfterLimit.get(categoryFromItemType)) != null) {
            return !date2.after(date);
        }
        return false;
    }

    private DataCategory getCategoryFromItemType(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1963501277:
                if (str.equals("attachment")) {
                    c = 0;
                    break;
                }
                break;
            case -309425751:
                if (str.equals(Scopes.PROFILE)) {
                    c = 1;
                    break;
                }
                break;
            case 96891546:
                if (str.equals(NotificationCompat.CATEGORY_EVENT)) {
                    c = 2;
                    break;
                }
                break;
            case 1984987798:
                if (str.equals(EnvelopeCache.PREFIX_CURRENT_SESSION_FILE)) {
                    c = 3;
                    break;
                }
                break;
            case 2141246174:
                if (str.equals("transaction")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return DataCategory.Attachment;
            case 1:
                return DataCategory.Profile;
            case 2:
                return DataCategory.Error;
            case 3:
                return DataCategory.Session;
            case 4:
                return DataCategory.Transaction;
            default:
                return DataCategory.Unknown;
        }
    }

    public void updateRetryAfterLimits(String str, String str2, int i) {
        if (str != null) {
            for (String replace : str.split(",", -1)) {
                String[] split = replace.replace(" ", "").split(":", -1);
                if (split.length > 0) {
                    long parseRetryAfterOrDefault = parseRetryAfterOrDefault(split[0]);
                    if (split.length > 1) {
                        String str3 = split[1];
                        Date date = new Date(this.currentDateProvider.getCurrentTimeMillis() + parseRetryAfterOrDefault);
                        if (str3 == null || str3.isEmpty()) {
                            applyRetryAfterOnlyIfLonger(DataCategory.All, date);
                        } else {
                            for (String str4 : str3.split(";", -1)) {
                                DataCategory dataCategory = DataCategory.Unknown;
                                try {
                                    String capitalize = StringUtils.capitalize(str4);
                                    if (capitalize != null) {
                                        dataCategory = DataCategory.valueOf(capitalize);
                                    } else {
                                        this.options.getLogger().log(SentryLevel.ERROR, "Couldn't capitalize: %s", str4);
                                    }
                                } catch (IllegalArgumentException e) {
                                    this.options.getLogger().log(SentryLevel.INFO, e, "Unknown category: %s", str4);
                                }
                                if (!DataCategory.Unknown.equals(dataCategory)) {
                                    applyRetryAfterOnlyIfLonger(dataCategory, date);
                                }
                            }
                        }
                    }
                }
            }
        } else if (i == 429) {
            applyRetryAfterOnlyIfLonger(DataCategory.All, new Date(this.currentDateProvider.getCurrentTimeMillis() + parseRetryAfterOrDefault(str2)));
        }
    }

    private void applyRetryAfterOnlyIfLonger(DataCategory dataCategory, Date date) {
        Date date2 = this.sentryRetryAfterLimit.get(dataCategory);
        if (date2 == null || date.after(date2)) {
            this.sentryRetryAfterLimit.put(dataCategory, date);
        }
    }

    private long parseRetryAfterOrDefault(String str) {
        if (str != null) {
            try {
                return (long) (Double.parseDouble(str) * 1000.0d);
            } catch (NumberFormatException unused) {
            }
        }
        return 60000;
    }
}
