package woowacourse.paint.model

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Path.Direction
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import androidx.annotation.ColorInt
import woowacourse.paint.util.addCircle

class PathPaint(
    val path: Path = Path(),
    val paint: Paint = Paint(),
) {
    private var currentCX: Float = 0f
    private var currentCY: Float = 0f

    var brush: Brush = Brush.PEN
        set(value) {
            when (value) {
                Brush.PEN -> {
                    paint.style = Paint.Style.STROKE
                    paint.xfermode = null
                }

                Brush.ERASER -> {
                    paint.style = Paint.Style.STROKE
                    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
                }

                else -> {
                    paint.xfermode = null
                    paint.style = Paint.Style.FILL
                }
            }
            field = value
        }

    lateinit var shape: Shape

    init {
        with(paint) {
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
        }
    }

    fun setPaintColor(@ColorInt color: Int) {
        paint.color = color
    }

    fun setPaintStrokeSize(size: Float) {
        paint.strokeWidth = size
    }

    fun resetPaint(): PathPaint =
        PathPaint(paint = Paint(paint)).apply { brush = this@PathPaint.brush }

    fun moveToPath(x: Float, y: Float) {
        currentCX = x
        currentCY = y
        path.moveTo(x, y)
    }

    fun drawToPath(x: Float, y: Float) {
        when (brush) {
            Brush.PEN -> drawLine(x, y)
            Brush.RECT -> drawRect(x, y)
            Brush.CIRCLE -> drawCircle(x, y)
            Brush.ERASER -> drawLine(x, y)
        }
    }

    private fun drawLine(x: Float, y: Float) {
        path.lineTo(x, y)
    }

    private fun drawRect(x: Float, y: Float) {
        shape = Rectangle(currentCX, currentCY, x, y)
        path.addRect(shape as RectF, Direction.CW)
    }

    private fun drawCircle(x: Float, y: Float) {
        shape = Circle(currentCX, currentCY, x, y)
        path.addCircle(shape as Circle, Direction.CW)
    }
}
