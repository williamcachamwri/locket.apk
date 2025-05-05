package com.google.firebase.firestore.remote;

import com.google.firestore.v1.BloomFilter;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class ExistenceFilter {
    private final int count;
    private BloomFilter unchangedNames;

    public ExistenceFilter(int i) {
        this.count = i;
    }

    public ExistenceFilter(int i, BloomFilter bloomFilter) {
        this.count = i;
        this.unchangedNames = bloomFilter;
    }

    public int getCount() {
        return this.count;
    }

    public BloomFilter getUnchangedNames() {
        return this.unchangedNames;
    }

    public String toString() {
        return "ExistenceFilter{count=" + this.count + ", unchangedNames=" + this.unchangedNames + AbstractJsonLexerKt.END_OBJ;
    }
}
