package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.model.SnapshotVersion;

public class NamedQuery implements BundleElement {
    private final BundledQuery bundledQuery;
    private final String name;
    private final SnapshotVersion readTime;

    public NamedQuery(String str, BundledQuery bundledQuery2, SnapshotVersion snapshotVersion) {
        this.name = str;
        this.bundledQuery = bundledQuery2;
        this.readTime = snapshotVersion;
    }

    public String getName() {
        return this.name;
    }

    public BundledQuery getBundledQuery() {
        return this.bundledQuery;
    }

    public SnapshotVersion getReadTime() {
        return this.readTime;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NamedQuery namedQuery = (NamedQuery) obj;
        if (this.name.equals(namedQuery.name) && this.bundledQuery.equals(namedQuery.bundledQuery)) {
            return this.readTime.equals(namedQuery.readTime);
        }
        return false;
    }

    public int hashCode() {
        return (((this.name.hashCode() * 31) + this.bundledQuery.hashCode()) * 31) + this.readTime.hashCode();
    }
}
