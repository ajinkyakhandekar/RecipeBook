package com.tghc.recipebook.extention

import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

fun setPhoneVerification(complete: () -> Unit, failed: (firebaseException: FirebaseException) -> Unit): PhoneAuthProvider.OnVerificationStateChangedCallbacks {
    return object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
            complete()
        }

        override fun onVerificationFailed(firebaseException: FirebaseException) {
            failed(firebaseException)
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
        }
    }
}