package com.ldg.icall

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 *
 * created by gui 2020/11/15
 *
 */
public class RingSetFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val add = 6.add(90).add(78)
        Log.d("ldg", "onCreateView: $add")
        return super.onCreateView(inflater, container, savedInstanceState)
        add1(6)(7)
    }

    private fun add1(a: Int): (Int) -> Int {
        return { b: Int -> a + b }
    }

    private fun <T : Int> T.add(a: T): T {
        return (this + a) as T
    }
}