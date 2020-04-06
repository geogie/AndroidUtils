package com.example.george.androidutils.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.FileDescriptor
import java.io.FileInputStream
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

/**
 * Created By George
 * Description:
 */
class BitmapUtils {
    companion object {
        const val TAG = "BitmapUtils"
        /**
         * compute Sample Size
         *
         * @param options
         * @param minSideLength
         * @param maxNumOfPixels
         * @return
         */
        private fun computeSampleSize(
                options: BitmapFactory.Options,
                minSideLength: Int, maxNumOfPixels: Int
        ): Int {
            val initialSize: Int = computeInitialSampleSize(
                    options, minSideLength,
                    maxNumOfPixels
            )
            var roundedSize: Int
            if (initialSize <= 8) {
                roundedSize = 1
                while (roundedSize < initialSize) {
                    roundedSize = roundedSize shl 1
                }
            } else {
                roundedSize = (initialSize + 7) / 8 * 8
            }
            return roundedSize
        }

        /**
         * compute Initial Sample Size
         *
         * @param options
         * @param minSideLength
         * @param maxNumOfPixels
         * @return
         */
        private fun computeInitialSampleSize(
                options: BitmapFactory.Options,
                minSideLength: Int, maxNumOfPixels: Int
        ): Int {
            val w = options.outWidth.toDouble()
            val h = options.outHeight.toDouble()
            // 上下限范围
            val lowerBound = if (maxNumOfPixels == -1) 1 else ceil(
                    sqrt(w * h / maxNumOfPixels)
            ).toInt()
            val upperBound = if (minSideLength == -1) 128 else floor(w / minSideLength).coerceAtMost(
                    floor(h / minSideLength)
            ).toInt()

            if (upperBound < lowerBound) { // return the larger one when there is no overlapping zone.
                Log.d(TAG, "lowerBound:$lowerBound")
                return lowerBound
            }
            return if (maxNumOfPixels == -1 && minSideLength == -1) {
                Log.d(TAG, "返回:1")
                1
            } else if (minSideLength == -1) {
                Log.d(TAG, "返回-1:lowerBound：$lowerBound")
                lowerBound
            } else {
                Log.d(TAG, "返回-1:upperBound：$upperBound")
                upperBound
            }
        }

        /**
         *
         * 以像素为：700*488例子
         * 完整的图片获取参数为：minSideLength：488； maxNumOfPixels：700*488--》图片大小为：4*700*488
         * "byteCount: ${bitmap?.byteCount} row:${bitmap?.rowBytes} height:${bitmap?.height}
         * bitmap?.height为图片的高像素488
         * bitmap?.rowBytes为图片的宽像素*4
         *
         // size--->4*WPix*HPix-->4*700*488
        // byteCount: 1366400 row:2800 height:488-->byteCount: 1366400 row:700*4 height:488

        // size--->4*WPix*HPix-->4*350*244
        // byteCount: 1366400 row:2800 height:488-->byteCount: 341600 row:350*4 height:244

        // 控件目前需要300dp*300dp--> 300*3px * 300*3px-->900px*900px 屏幕：1080x2340

         * get Bitmap
         *
         * @param imgFile 图片路径
         * @param minSideLength 最小边的长度(作为最大缩放值) 取(Wpix, Hpix)最小的，然后根据最小的进行2倍缩放,值越小越虚化
         * @param maxNumOfPixels 最大像素数:Wpix * Hpix。
         * 最后两个参数可以根据控件大小进行像素显示大小处理
         * @return
         */
        fun tryGetBitmap(
                imgFile: String?, minSideLength: Int,
                maxNumOfPixels: Int
        ): Bitmap? {
            return if (imgFile == null || imgFile.isEmpty()) null else try {
                val fd: FileDescriptor = FileInputStream(imgFile).fd
                val options = BitmapFactory.Options()
                options.inJustDecodeBounds = true // 在不分配空间bitmap情况下，快速计算出原始图片的长度和宽度
                // BitmapFactory.decodeFile(imgFile, options);
                BitmapFactory.decodeFileDescriptor(fd, null, options)
                options.inSampleSize = computeSampleSize(
                        options, minSideLength,
                        maxNumOfPixels
                )
                try { // 这里一定要将其设置回false，因为之前我们将其设置成了true
                    // 设置inJustDecodeBounds为true后，decodeFile并不分配空间，即，BitmapFactory解码出来的Bitmap为Null,但可计算出原始图片的长度和宽度
                    options.inJustDecodeBounds = false
                    val bmp = BitmapFactory.decodeFile(imgFile, options)
                    Log.d(TAG, "返回：bmp")
                    bmp
                } catch (err: OutOfMemoryError) {
                    Log.d(TAG, "异常-1：$err")
                    null
                }
            } catch (e: Exception) {
                Log.d(TAG, "异常-2：$e")
                null
            }
        }
    }
}