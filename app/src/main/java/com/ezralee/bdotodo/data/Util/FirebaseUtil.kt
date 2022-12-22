package com.ezralee.bdotodo.data.Util

import android.content.Context
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class FirebaseUtil {
    companion object{
        private var firebase: DocumentReference? = null

        //해당 유저의 document로 바로 접근하는 인스턴스
        fun getInstance() : DocumentReference?{
            if (firebase == null){
                firebase = FirebaseFirestore
                    .getInstance()
                    .collection("userData")
                    .document(KakaoLogin.USER_ID)
            }
            return firebase
        }
    }
}