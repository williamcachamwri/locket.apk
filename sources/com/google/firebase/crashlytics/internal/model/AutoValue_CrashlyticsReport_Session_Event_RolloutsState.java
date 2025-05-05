package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.encoders.annotations.Encodable;
import java.util.List;

final class AutoValue_CrashlyticsReport_Session_Event_RolloutsState extends CrashlyticsReport.Session.Event.RolloutsState {
    private final List<CrashlyticsReport.Session.Event.RolloutAssignment> rolloutAssignments;

    private AutoValue_CrashlyticsReport_Session_Event_RolloutsState(List<CrashlyticsReport.Session.Event.RolloutAssignment> list) {
        this.rolloutAssignments = list;
    }

    @Encodable.Field(name = "assignments")
    public List<CrashlyticsReport.Session.Event.RolloutAssignment> getRolloutAssignments() {
        return this.rolloutAssignments;
    }

    public String toString() {
        return "RolloutsState{rolloutAssignments=" + this.rolloutAssignments + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CrashlyticsReport.Session.Event.RolloutsState) {
            return this.rolloutAssignments.equals(((CrashlyticsReport.Session.Event.RolloutsState) obj).getRolloutAssignments());
        }
        return false;
    }

    public int hashCode() {
        return this.rolloutAssignments.hashCode() ^ 1000003;
    }

    static final class Builder extends CrashlyticsReport.Session.Event.RolloutsState.Builder {
        private List<CrashlyticsReport.Session.Event.RolloutAssignment> rolloutAssignments;

        Builder() {
        }

        public CrashlyticsReport.Session.Event.RolloutsState.Builder setRolloutAssignments(List<CrashlyticsReport.Session.Event.RolloutAssignment> list) {
            if (list != null) {
                this.rolloutAssignments = list;
                return this;
            }
            throw new NullPointerException("Null rolloutAssignments");
        }

        public CrashlyticsReport.Session.Event.RolloutsState build() {
            if (this.rolloutAssignments != null) {
                return new AutoValue_CrashlyticsReport_Session_Event_RolloutsState(this.rolloutAssignments);
            }
            throw new IllegalStateException("Missing required properties: rolloutAssignments");
        }
    }
}
