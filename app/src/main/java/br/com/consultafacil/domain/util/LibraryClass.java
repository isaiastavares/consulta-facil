package br.com.consultafacil.domain.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Isaias on 16/06/2016.
 */
public final class LibraryClass {

    public static String PREF = "br.com.consultafacil.PREF";
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

    public static void saveSP(Context context, String key, String value ){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).apply();
    }

    public static String getSP(Context context, String key ){
        SharedPreferences sp = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        String token = sp.getString(key, "");
        return token;
    }
}
