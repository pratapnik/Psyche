package com.first.myapplication.mht.widgets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.first.myapplication.mht.actions.BottomSheetAction
import com.first.myapplication.mht.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.jarvis_menu_bottom_sheet.*

class JarvisMenuBottomSheet : BottomSheetDialogFragment() {

    var actionListener: ActionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.jarvis_menu_bottom_sheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ivCloseBottomSheet.setOnClickListener {
            dismiss()
        }

        btnProfileItem.setOnClickListener {
            actionListener?.onActionListener(BottomSheetAction.OPEN_PROFILE)
        }

        btnScale.setOnClickListener {
            actionListener?.onActionListener(BottomSheetAction.OPEN_SCALE)

        }

        btnAboutUs.setOnClickListener {
            actionListener?.onActionListener(BottomSheetAction.OPEN_ABOUT_US)

        }

        btnExercises.setOnClickListener {
            actionListener?.onActionListener(BottomSheetAction.OPEN_EXERCISES)
        }

        btnSignOut.setOnClickListener {
            actionListener?.onActionListener(BottomSheetAction.OPEN_SIGN_OUT)
        }
    }

    fun addOnActionClickListener(onActionClickListener: ActionListener) {
        this.actionListener = onActionClickListener
    }

    interface ActionListener {
        fun onActionListener(action: BottomSheetAction)
    }

}
