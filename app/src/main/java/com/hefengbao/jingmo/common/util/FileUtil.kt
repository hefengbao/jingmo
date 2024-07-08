/*
 * This file is part of the 京墨（jingmo）APP.
 *
 * (c) 贺丰宝（hefengbao） <hefengbao@foxmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

package com.hefengbao.jingmo.common.util

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream


/**
 * Android 6.0 以前，应用要想保存图片到相册，只需要通过File对象打开IO流就可以保存；
 * Android 6.0 添加了运行时权限，需要先申请存储权限才可以保存图片；
 * Android 10 引入了分区存储，但不是强制的，可以通过清单配置android:requestLegacyExternalStorage="true"关闭分区存储；
 * Android 11 强制开启分区存储，应用以 Android 11 为目标版本，系统会忽略 requestLegacyExternalStorage标记，访问共享存储空间都需要使用MediaStore进行访问。
 *
 * https://juejin.cn/post/7042218651482587172
 *
 * https://juejin.cn/post/6874559308328075278
 *
 * https://androidexplained.github.io/android/android11/scoped-storage/2020/09/29/file-saving-android-11.html
 *
 * https://zhuanlan.zhihu.com/p/172493773
 *
 * https://www.bilibili.com/video/BV1Fj411z7HS
 */
object FileUtil {
    fun saveImageToStorage(
        context: Context,
        bitmap: Bitmap,
        filename: String,
        mimeType: String = "image/jpeg",
        directory: String = Environment.DIRECTORY_PICTURES,
        mediaContentUri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
    ): Boolean {
        val contentResolver: ContentResolver = context.contentResolver

        val imageOutStream: OutputStream

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, filename)
                put(MediaStore.Images.Media.MIME_TYPE, mimeType)
                put(MediaStore.Images.Media.RELATIVE_PATH, directory)
            }

            contentResolver.run {
                val uri =
                    contentResolver.insert(mediaContentUri, values)
                        ?: return false
                imageOutStream = openOutputStream(uri) ?: return false
            }
        } else {
            val imagePath = Environment.getExternalStoragePublicDirectory(directory).absolutePath
            val image = File(imagePath, filename)
            imageOutStream = FileOutputStream(image)
        }

        imageOutStream.use { bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it) }

        return true
    }
}