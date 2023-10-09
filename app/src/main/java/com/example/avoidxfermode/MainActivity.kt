package com.example.avoidxfermode

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff.Mode
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DrawView(this))
    }

    internal inner class DrawView(context: Context?): View(context) {
        private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
        private var rect: Rect = Rect(0, 0, 50, 100)
        private var bitmap: Bitmap? = null
        private var mode: Mode = Mode.SRC

        init {
            createBitmap()
        }

        override fun onDraw(canvas: Canvas) {
            canvas.drawARGB(80, 102,204,255)
            canvas.drawBitmap(bitmap!!, 0f, 0f, paint)
        }

        private fun createBitmap() {
            bitmap = Bitmap.createBitmap(1100, 700, Bitmap.Config.ARGB_8888)
            val bitmapCanvas = Canvas(bitmap!!)

            val redPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            redPaint.style = Paint.Style.FILL_AND_STROKE
            redPaint.color = Color.RED

            drawBitmap(bitmapCanvas, redPaint)

            redPaint.xfermode = AvoidXfermode(Color.BLUE, 0, mode)
            drawBitmap(bitmapCanvas, redPaint)
            redPaint.xfermode = AvoidXfermode(Color.BLUE, 127, mode)
            drawBitmap(bitmapCanvas, redPaint)
            redPaint.xfermode = AvoidXfermode(Color.BLUE, 255, mode)
            drawBitmap(bitmapCanvas, redPaint)
        }

        private fun drawBitmap(bitmapCanvas: Canvas, redPaint: Paint) {
            val bitmapPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            bitmapPaint.style = Paint.Style.FILL_AND_STROKE

            bitmapCanvas.save()
            bitmapCanvas.translate(25f, 25f)
            for (i in 0..19) {
                bitmapPaint.color = Color.rgb(i * 10, i * 10, 255)
                bitmapCanvas.drawRect(rect, bitmapPaint)
                bitmapCanvas.translate(50f, 0f)
            }
            bitmapCanvas.restore()
            bitmapCanvas.drawRect(10f,75f,1035f,150f, redPaint)
            bitmapCanvas.translate(0f, 150f)
        }
    }
}