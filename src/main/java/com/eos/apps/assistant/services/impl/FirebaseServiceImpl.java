package com.eos.apps.assistant.services.impl;

import com.eos.apps.assistant.objects.MessageType;
import com.eos.apps.assistant.services.FirebaseService;
import com.eos.apps.assistant.utils.CustomSocketHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public record FirebaseServiceImpl<T>(Class<T> type, String collection) implements FirebaseService<T> {
//    static Firestore dbFirestore = FirestoreClient.getFirestore();

    @Override
    public void getAll() {
        MessageType messageType = collection.equals("shopping_item") ? MessageType.ShoppingItem : MessageType.ShelfItem;
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference().child(collection);
        List<T> result = new ArrayList<>();
        ref.addValueEventListener(
            new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        CustomSocketHandler.sendItem((T)dataSnapshot.getValue(), messageType);
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
    public boolean delete(String id) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child(collection).child(id).removeValueAsync();
        return true;
    }

    @Override
    public T edit(String id, Object company) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child(collection).child(id).setValueAsync(company);
        return (T) company;
    }

    @Override
    public boolean add(String id, Object company) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference().child(collection).child(id).setValueAsync(company);
        return true;
    }
}
