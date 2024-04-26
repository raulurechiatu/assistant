package com.eos.apps.assistant.services;

import com.google.cloud.firestore.EventListener;
import com.google.cloud.firestore.QuerySnapshot;

public interface FirebaseService<T> {
    void getAll();

    Object getById(String id);

    boolean delete(String id);

    T edit(String id, T obj);

    boolean add(String id, T obj);
    boolean addWithCallback(String id, T obj, EventListener<QuerySnapshot> listener);
}
