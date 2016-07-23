package br.com.consultafacil.domain.util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
