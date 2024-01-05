package com.example.moive.Utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import com.example.moive.R

class LoadingIndicator(context: Context) {
    private var dialog = Dialog(context, android.R.style.Theme_Black).apply {
        setCanceledOnTouchOutside(false)
        setCancelable(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setContentView(R.layout.layout_loading_dialog)
    }
    fun show() {
        dialog.apply {
            if (!isShowing) show()
        }
    }


    fun hide() {
        dialog.apply {
            if (isShowing) dismiss()
        }
    }
}