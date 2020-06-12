package com.tghc.recipebook.extention

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.showAlertDialog(
    message: String, isCancelable: Boolean = false, isCancelableTouchOutside: Boolean = false,
    builderFunction: AlertDialog.Builder.() -> Any
) : Dialog {
    val builder = AlertDialog.Builder(requireContext())
    builder.builderFunction()
    val dialog = builder.create()

    dialog.setMessage(message)
    dialog.setCancelable(isCancelable)
    dialog.setCanceledOnTouchOutside(isCancelableTouchOutside)
    dialog.show()

    return dialog
}

fun AlertDialog.Builder.okButton(handleClick: () -> Unit = {}) {
    setPositiveButton("OK") { dialogInterface, i -> handleClick() }
}

fun AlertDialog.Builder.cancelButton(handleClick: () -> Unit = {}) {
    setNegativeButton("Cancel") { dialogInterface, i -> handleClick() }
}

fun AlertDialog.Builder.yesButton(handleClick: () -> Unit = {}) {
    setPositiveButton("Yes") { dialogInterface, i -> handleClick() }
}

fun AlertDialog.Builder.noButton(handleClick: () -> Unit = {}) {
    setNegativeButton("No") { dialogInterface, i -> handleClick() }
}

fun AlertDialog.Builder.neutralButton(text: String, handleClick: () -> Unit = {}) {
    setNeutralButton(text) { dialogInterface, i -> handleClick() }
}


/*fun  Dialog.showListDialog(message: String
                           , cancelable: Boolean = false
                           , cancelableTouchOutside: Boolean = false
                           , itemList: ArrayList<ModelDialog>
                           , onItemClick:(pos:Int)->Unit) {
    window!!.setBackgroundDrawableResource(R.drawable.round_w)
    setContentView(R.layout.dialog_list_main)
    recycler_dialog.withAdapter(itemList, R.layout.row_dialog_list, {item, position ->
        text_row_dialog.text = item.title
        image_row_dialog.setImageResource(R.drawable.ic_share)
    },{
        text_row_dialog.setOnClickListener { onItemClick(pos()) }
    })

    window!!.setDimAmount(0.0f)
    setCancelable(cancelable)
    setCanceledOnTouchOutside(cancelableTouchOutside)
}*/

/*fun BottomSheetDialog.showBottomListDialog(message: String
                                           , itemList: ArrayList<ModelDialog>
                                           , onItemClick:(option:DialogOptions)->Unit) {
    setContentView(R.layout.dialog_list_main)
    recycler_dialog.withAdapter(itemList, R.layout.row_dialog_list, {item, position ->
        text_row_dialog.text = item.title
        image_row_dialog.setImageResource(item.icon)
    },{
        text_row_dialog.setOnClickListener { onItemClick(itemList[pos()].options) }
    })

    window!!.decorView.findViewById<View>(R.id.design_bottom_sheet) .setBackgroundResource(android.R.color.transparent)
    window!!.setDimAmount(0.0f)
}*/

fun Dialog.setDialog(
    layoutRes: Int
    , yesClick: () -> Unit
    , noClick: () -> Unit
) {
    setContentView(layoutRes)
    setCancelable(true)
    setCanceledOnTouchOutside(true)
//    dialog_yes.setOnClickListener { yesClick() }
//    dialog_no.setOnClickListener { noClick() }

    window!!.setBackgroundDrawableResource(android.R.color.transparent)
    window!!.setDimAmount(0.0f)
}