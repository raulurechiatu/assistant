package com.eos.apps.assistant.services.impl;

import com.eos.apps.assistant.objects.MessageType;
import com.eos.apps.assistant.objects.ShelfItem;
import com.eos.apps.assistant.services.FirebaseService;
import com.eos.apps.assistant.utils.CustomSocketHandler;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public record FirebaseServiceImpl<T>(Class<T> type, String collection) implements FirebaseService<T> {
//    static Firestore dbFirestore = FirestoreClient.getFirestore();

    @Override
    public void getAll() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference().child(collection);
        List<T> result = new ArrayList<>();
        ref.addValueEventListener(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        CustomSocketHandler.sendItem((T)dataSnapshot.getValue(), MessageType.ShelfItem);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    //handle databaseError
                }
            });
    }

    @Override
    public T getById(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(collection).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        T obj = null;

        if (document.exists()) {
            obj = document.toObject(type);
        }
        return obj;
    }

    @Override
    public boolean delete(String id) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(collection).document(id).delete();
        return true;
    }

    @Override
    public T edit(String id, Object company) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(collection).document(id).set(company);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return getById(id);
    }

    @Override
    public boolean add(String id, Object company) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(collection).document(id).set(company);
        return true;
    }

    @Override
    public boolean addWithCallback(String id, T company, @Nonnull EventListener<QuerySnapshot> listener) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(collection).document(id).set(company);
        dbFirestore.collection(collection).addSnapshotListener(listener);
        return true;
    }
}
