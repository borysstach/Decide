package pl.borys.decide.common.views

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import androidx.core.view.isGone
import pl.borys.decide.common.isTesting

class TestableProgressBar : ProgressBar {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        isGone = isTesting()
    }

    override fun setVisibility(visibility: Int) {
        super.setVisibility(
                if (isTesting()) {
                    visibility
                } else {
                    View.GONE
                }
        )
    }
}