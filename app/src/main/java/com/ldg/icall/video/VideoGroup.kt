package com.ldg.icall.video

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.ldg.icall.call.react.PhoneHolder
import com.ldg.icall.databinding.ViewVideoCallBinding

/**
 * Created by gui on 2020/12/11
 */
class VideoGroup : ConstraintLayout {

    private var phoneHolder: PhoneHolder = PhoneHolder()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        ViewVideoCallBinding.inflate(LayoutInflater.from(context), this).apply {
            videoContainer.addView(VideoView(context), ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
            ivHandUp.setOnClickListener { phoneHolder.endCall() }
            ivPickUp.setOnClickListener { phoneHolder.answer() }
        }
    }
}