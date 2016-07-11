package br.com.consultafacil.domain.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Isaias on 16/06/2016.
 */
public final class LibraryClass {

    private static DatabaseReference firebaseDataBase;

    private LibraryClass() {
        super();
    }

    public static DatabaseReference getFirebaseDatabase() {
        if (firebaseDataBase == null) {
            firebaseDataBase = FirebaseDatabase.getInstance().getReference();
        }
        return firebaseDataBase;
    }
}
