package com.tghc.recipebook.constant

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class Utils {
    fun setIntent(activity: Activity, mClass: Class<*>, extras: Bundle?) {
        val intent = Intent(activity, mClass)
        intent.putExtras(extras!!)
        activity.startActivity(intent)
    }

    fun setIntentFinish(activity: Activity, mClass: Class<*>, extras: Bundle?) {
        val intent = Intent(activity, mClass)
        intent.putExtras(extras!!)
        activity.startActivity(intent)
        activity.finish()
    }

    fun setString(editText: EditText, text: String) {
        if (!TextUtils.isEmpty(text)) editText.setText(text) else editText.setText("")
    }

    fun setString(textView: TextView, text: String) {
        if (!TextUtils.isEmpty(text)) textView.text = text else textView.text = ""
    }

    fun isEmpty(data: String?): Boolean {
        return if (data == null)
            true
         else data == ""
    }


    // Get Strings from Views
    fun getString(editText: EditText):String{
        return if(!TextUtils.isEmpty(editText.text.toString()))
            editText.text.toString().trim()
        else ""
    }
    fun getString(textView: TextView):String{
        return if(!TextUtils.isEmpty(textView.text.toString()))
            textView.text.toString().trim()
        else ""
    }

    fun getString(radioGroup: RadioGroup): String {
        val radioButton :RadioButton = radioGroup.findViewById(radioGroup.checkedRadioButtonId)
        return if(!TextUtils.isEmpty(radioButton.text.toString()))
            radioButton.text.toString().trim()
        else ""
    }
}
