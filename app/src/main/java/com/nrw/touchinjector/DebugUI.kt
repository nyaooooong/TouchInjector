package com.nrw.touchinjector

import android.app.Service
import android.content.Context
import android.graphics.Color
import android.graphics.PixelFormat
import android.util.TypedValue
import android.view.Gravity
import android.view.WindowManager
import android.widget.TextView

class DebugUI {

    private lateinit var mPopupView: TextView
    private var mParams : WindowManager.LayoutParams? = null
    private var mWindowManager : WindowManager? = null
    private var mContext : Context

    constructor(context : Context) {
        mContext = context;
    }

    fun showPopup() {
        /* start : always popup to receive event */
        mPopupView = TextView(mContext) //뷰 생성
        mPopupView!!.text = "이 뷰는 항상 위에 있다." //텍스트 설정
        mPopupView!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f) //텍스트 크기 18sp
        mPopupView!!.setTextColor(Color.BLUE) //글자 색상
        mPopupView!!.setBackgroundColor(Color.argb(127, 0, 255, 255)) //텍스트뷰 배경 색

        //최상위 윈도우에 넣기 위한 설정
        mParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            //WindowManager.LayoutParams.TYPE_PHONE,  //항상 최 상위. 터치 이벤트 받을 수 있음. TYPE_PHONE은 API 26부터 deprecated. 대신 TYPE_APPLICATION_OVERLAY 사용
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,  //항상 최 상위. 터치 이벤트 받을 수 있음.
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,  //포커스를 가지지 않음
            PixelFormat.TRANSLUCENT
        ) //투명
        mParams!!.gravity = Gravity.LEFT or Gravity.TOP //왼쪽 상단에 위치하게 함.
        mWindowManager = mContext.getSystemService(Service.WINDOW_SERVICE) as WindowManager //윈도우 매니저
        //mNotiManager = mContext.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager //윈도우 매니저
        mWindowManager!!.addView(mPopupView, mParams) //윈도우에 뷰 넣기. permission 필요.
        /* end : always popup to receive event */
    }

    fun closePopup() {
        if (mWindowManager != null) {        //서비스 종료시 뷰 제거. *중요 : 뷰를 꼭 제거 해야함.
            if (mPopupView != null) mWindowManager!!.removeView(mPopupView)
        }
    }
}