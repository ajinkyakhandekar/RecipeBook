package com.tghc.recipebook.extention

import androidx.transition.Transition

fun getTransitionListener(): Transition.TransitionListener {
    return object : Transition.TransitionListener{
        override fun onTransitionEnd(transition: Transition) {
        }

        override fun onTransitionResume(transition: Transition) {
        }

        override fun onTransitionPause(transition: Transition) {
        }

        override fun onTransitionCancel(transition: Transition) {
        }

        override fun onTransitionStart(transition: Transition) {
        }
    }
}