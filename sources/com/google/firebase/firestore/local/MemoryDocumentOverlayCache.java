package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.util.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;

public class MemoryDocumentOverlayCache implements DocumentOverlayCache {
    private final Map<Integer, Set<DocumentKey>> overlayByBatchId = new HashMap();
    private final TreeMap<DocumentKey, Overlay> overlays = new TreeMap<>();

    public Overlay getOverlay(DocumentKey documentKey) {
        return this.overlays.get(documentKey);
    }

    public Map<DocumentKey, Overlay> getOverlays(SortedSet<DocumentKey> sortedSet) {
        HashMap hashMap = new HashMap();
        for (DocumentKey documentKey : sortedSet) {
            Overlay overlay = this.overlays.get(documentKey);
            if (overlay != null) {
                hashMap.put(documentKey, overlay);
            }
        }
        return hashMap;
    }

    private void saveOverlay(int i, Mutation mutation) {
        Overlay overlay = this.overlays.get(mutation.getKey());
        if (overlay != null) {
            this.overlayByBatchId.get(Integer.valueOf(overlay.getLargestBatchId())).remove(mutation.getKey());
        }
        this.overlays.put(mutation.getKey(), Overlay.create(i, mutation));
        if (this.overlayByBatchId.get(Integer.valueOf(i)) == null) {
            this.overlayByBatchId.put(Integer.valueOf(i), new HashSet());
        }
        this.overlayByBatchId.get(Integer.valueOf(i)).add(mutation.getKey());
    }

    public void saveOverlays(int i, Map<DocumentKey, Mutation> map) {
        for (Map.Entry next : map.entrySet()) {
            saveOverlay(i, (Mutation) Preconditions.checkNotNull((Mutation) next.getValue(), "null value for key: %s", next.getKey()));
        }
    }

    public void removeOverlaysForBatchId(int i) {
        if (this.overlayByBatchId.containsKey(Integer.valueOf(i))) {
            this.overlayByBatchId.remove(Integer.valueOf(i));
            for (DocumentKey remove : this.overlayByBatchId.get(Integer.valueOf(i))) {
                this.overlays.remove(remove);
            }
        }
    }

    public Map<DocumentKey, Overlay> getOverlays(ResourcePath resourcePath, int i) {
        HashMap hashMap = new HashMap();
        int length = resourcePath.length() + 1;
        for (Overlay next : this.overlays.tailMap(DocumentKey.fromPath((ResourcePath) resourcePath.append(""))).values()) {
            DocumentKey key = next.getKey();
            if (!resourcePath.isPrefixOf(key.getPath())) {
                break;
            } else if (key.getPath().length() == length && next.getLargestBatchId() > i) {
                hashMap.put(next.getKey(), next);
            }
        }
        return hashMap;
    }

    public Map<DocumentKey, Overlay> getOverlays(String str, int i, int i2) {
        TreeMap treeMap = new TreeMap();
        for (Overlay next : this.overlays.values()) {
            if (next.getKey().getCollectionGroup().equals(str) && next.getLargestBatchId() > i) {
                Map map = (Map) treeMap.get(Integer.valueOf(next.getLargestBatchId()));
                if (map == null) {
                    map = new HashMap();
                    treeMap.put(Integer.valueOf(next.getLargestBatchId()), map);
                }
                map.put(next.getKey(), next);
            }
        }
        HashMap hashMap = new HashMap();
        for (Map putAll : treeMap.values()) {
            hashMap.putAll(putAll);
            if (hashMap.size() >= i2) {
                break;
            }
        }
        return hashMap;
    }
}
