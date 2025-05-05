package androidx.camera.video;

import androidx.camera.video.FallbackStrategy;

final class AutoValue_FallbackStrategy_RuleStrategy extends FallbackStrategy.RuleStrategy {
    private final Quality fallbackQuality;
    private final int fallbackRule;

    AutoValue_FallbackStrategy_RuleStrategy(Quality quality, int i) {
        if (quality != null) {
            this.fallbackQuality = quality;
            this.fallbackRule = i;
            return;
        }
        throw new NullPointerException("Null fallbackQuality");
    }

    /* access modifiers changed from: package-private */
    public Quality getFallbackQuality() {
        return this.fallbackQuality;
    }

    /* access modifiers changed from: package-private */
    public int getFallbackRule() {
        return this.fallbackRule;
    }

    public String toString() {
        return "RuleStrategy{fallbackQuality=" + this.fallbackQuality + ", fallbackRule=" + this.fallbackRule + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FallbackStrategy.RuleStrategy)) {
            return false;
        }
        FallbackStrategy.RuleStrategy ruleStrategy = (FallbackStrategy.RuleStrategy) obj;
        if (!this.fallbackQuality.equals(ruleStrategy.getFallbackQuality()) || this.fallbackRule != ruleStrategy.getFallbackRule()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((this.fallbackQuality.hashCode() ^ 1000003) * 1000003) ^ this.fallbackRule;
    }
}
