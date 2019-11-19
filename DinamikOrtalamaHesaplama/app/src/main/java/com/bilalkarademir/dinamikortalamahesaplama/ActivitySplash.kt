package com.bilalkarademir.dinamikortalamahesaplama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*
import javax.xml.transform.Templates

class ActivitySplash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        var hareketliButton = AnimationUtils.loadAnimation(this,R.anim.animasyon)
        var hareketliResim = AnimationUtils.loadAnimation(this,R.anim.resim_animasyon)
        var yukariyagidenResim = AnimationUtils.loadAnimation(this,R.anim.yukari_giden_resim_animasyon)
        var asagiyagidenButton = AnimationUtils.loadAnimation(this,R.anim.asagi_giden_button_animasyon)
        hareketliBtnHesapla.animation=hareketliButton
        resim_hareket.animation=hareketliResim


        hareketliBtnHesapla.setOnClickListener {



            hareketliBtnHesapla.startAnimation(asagiyagidenButton)
            resim_hareket.startAnimation(yukariyagidenResim)
            object : CountDownTimer(1000,1000){
                override fun onFinish() {
                    var intent = Intent(this@ActivitySplash,MainActivity::class.java)
                    startActivity(intent)

                }

                override fun onTick(millisUntilFinished: Long) {

                }


            }.start()

        }


    }
}
