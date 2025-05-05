package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.NamedQuery;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.bundle.BundledQuery;
import com.google.protobuf.InvalidProtocolBufferException;

class SQLiteBundleCache implements BundleCache {
    private final SQLitePersistence db;
    private final LocalSerializer serializer;

    SQLiteBundleCache(SQLitePersistence sQLitePersistence, LocalSerializer localSerializer) {
        this.db = sQLitePersistence;
        this.serializer = localSerializer;
    }

    public BundleMetadata getBundleMetadata(String str) {
        return (BundleMetadata) this.db.query("SELECT schema_version, create_time_seconds, create_time_nanos, total_documents,  total_bytes FROM bundles WHERE bundle_id = ?").binding(str).firstValue(new SQLiteBundleCache$$ExternalSyntheticLambda0(str));
    }

    static /* synthetic */ BundleMetadata lambda$getBundleMetadata$0(String str, Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        return new BundleMetadata(str, cursor.getInt(0), new SnapshotVersion(new Timestamp(cursor.getLong(1), cursor.getInt(2))), cursor.getInt(3), cursor.getLong(4));
    }

    public void saveBundleMetadata(BundleMetadata bundleMetadata) {
        this.db.execute("INSERT OR REPLACE INTO bundles (bundle_id, schema_version, create_time_seconds, create_time_nanos, total_documents, total_bytes) VALUES (?, ?, ?, ?, ?, ?)", bundleMetadata.getBundleId(), Integer.valueOf(bundleMetadata.getSchemaVersion()), Long.valueOf(bundleMetadata.getCreateTime().getTimestamp().getSeconds()), Integer.valueOf(bundleMetadata.getCreateTime().getTimestamp().getNanoseconds()), Integer.valueOf(bundleMetadata.getTotalDocuments()), Long.valueOf(bundleMetadata.getTotalBytes()));
    }

    public NamedQuery getNamedQuery(String str) {
        return (NamedQuery) this.db.query("SELECT read_time_seconds, read_time_nanos, bundled_query_proto FROM named_queries WHERE name = ?").binding(str).firstValue(new SQLiteBundleCache$$ExternalSyntheticLambda1(this, str));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$getNamedQuery$1$com-google-firebase-firestore-local-SQLiteBundleCache  reason: not valid java name */
    public /* synthetic */ NamedQuery m699lambda$getNamedQuery$1$comgooglefirebasefirestorelocalSQLiteBundleCache(String str, Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            return new NamedQuery(str, this.serializer.decodeBundledQuery(BundledQuery.parseFrom(cursor.getBlob(2))), new SnapshotVersion(new Timestamp(cursor.getLong(0), cursor.getInt(1))));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("NamedQuery failed to parse: %s", e);
        }
    }

    public void saveNamedQuery(NamedQuery namedQuery) {
        this.db.execute("INSERT OR REPLACE INTO named_queries (name, read_time_seconds, read_time_nanos, bundled_query_proto) VALUES (?, ?, ?, ?)", namedQuery.getName(), Long.valueOf(namedQuery.getReadTime().getTimestamp().getSeconds()), Integer.valueOf(namedQuery.getReadTime().getTimestamp().getNanoseconds()), this.serializer.encodeBundledQuery(namedQuery.getBundledQuery()).toByteArray());
    }
}
