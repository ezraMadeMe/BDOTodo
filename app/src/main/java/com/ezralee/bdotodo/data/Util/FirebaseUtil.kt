package com.ezralee.bdotodo.data.Util

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseUtil {
    private val USER_DB: CollectionReference =
        FirebaseFirestore.getInstance().collection("userData")

    fun getCollection(docKey: String, colKey: String): CollectionReference {
        return getDocument(docKey).collection(colKey)
    }

    fun setCollection(docKey: String, colKey: String) {
        getDocument(docKey).collection(colKey)
    }

    fun getDocument(key: String): DocumentReference {
        return USER_DB.document(key)
    }

    fun setDocument(key: String) {
        USER_DB.document(key)
    }
}