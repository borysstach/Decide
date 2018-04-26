package pl.borys.decide.common.views

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.animation.Animation
import android.widget.ProgressBar
import pl.borys.decide.common.isTesting

class TestableProgressBar : ProgressBar {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        if (isTesting()) {
            indeterminateDrawable = ColorDrawable(Color.RED)
        }
    }

    override fun setIndeterminateDrawable(originalDrawable: Drawable?) {
        super.setIndeterminateDrawable(
                if (isTesting()) {
                    ColorDrawable(Color.RED)
                } else {
                    originalDrawable
                }
        )
    }

    override fun startAnimation(animation: Animation?) {
        if (!isTesting()) {
            super.startAnimation(animation)
        }
    }
}