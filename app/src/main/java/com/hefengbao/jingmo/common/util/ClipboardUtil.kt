package com.hefengbao.jingmo.common.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.widget.Toast

/**
 * https://developer.android.com/develop/ui/views/touch-and-input/copy-paste
 */
object ClipboardUtil {
    fun textCopyThenPost(context: Context, textCopied: String) {
        val clipboardManager = context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        // When setting the clip board text.
        clipboardManager.setPrimaryClip(
            ClipData.newPlainText(
                SystemUtil.getAppName(context),
                textCopied
            )
        )
        // Only show a toast for Android 12 and lower.
        /*if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)*/
        Toast.makeText(context, "已复制到剪贴板", Toast.LENGTH_SHORT).show()
    }
}