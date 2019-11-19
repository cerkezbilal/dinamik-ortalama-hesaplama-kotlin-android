package com.bilalkarademir.dinamikortalamahesaplama

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.NonNull
import com.shashank.sony.fancytoastlib.FancyToast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.yeni_ders_layout.view.*

class MainActivity : AppCompatActivity() {

    private val DERSLER = arrayOf("Matematik","Türkçe","Tarih","Fizik","Edebiyat","Algoritma","Programlama")
    private  var tumDersler:ArrayList<Dersler> = ArrayList(5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,DERSLER)
        etDersAd.setAdapter(adapter)

        if(linear.childCount==0){

            btnHesapla.visibility= View.INVISIBLE
        }else{

            btnHesapla.visibility = View.VISIBLE
        }

        btnDersEkle.setOnClickListener {

            if(!etDersAd.text.isNullOrEmpty()){

                var inflater = LayoutInflater.from(this)
                var yeniDersView= inflater.inflate(R.layout.yeni_ders_layout,null)
                // var inflater2 = layoutInflater
                // var inflater3 = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

               yeniDersView.etYeniDersAd.setAdapter(adapter)

                var dersAdi = etDersAd.text.toString()
                var kredi = spKredi.selectedItem.toString()
                var not = spNot.selectedItem.toString()

                yeniDersView.etYeniDersAd.setText(dersAdi)
                yeniDersView.spYeniKredi.setSelection(spinnerPositionAl(spKredi,kredi))
                yeniDersView.spYeniNot.setSelection(spinnerPositionAl(spNot,not))




                yeniDersView.btnDersSil.setOnClickListener {

                    linear.removeView(yeniDersView)
                    if(linear.childCount==0){

                        btnHesapla.visibility= View.INVISIBLE
                    }else{

                        btnHesapla.visibility = View.VISIBLE
                    }
                }


                linear.addView(yeniDersView)
                btnHesapla.visibility = View.VISIBLE
                sifirla()


            }else{
                //Toast.makeText(this,"Ders Adı Yazınız!!",Toast.LENGTH_LONG).show()
                FancyToast.makeText(this,"Ders Adınız Giriniz!",FancyToast.LENGTH_LONG,FancyToast.ERROR,false).show()
            }

        }
    }


    fun sifirla(){


        etDersAd.setText("")
        spKredi.setSelection(0)
        spNot.setSelection(0)


    }

    fun spinnerPositionAl( spinner : Spinner,aranacakDeger : String): Int{

        var index = 0
        for(i in 0..spinner.count){
            if(spinner.getItemAtPosition(i).toString().equals(aranacakDeger)){

                index = i
                break
            }
        }

        return index

    }

    fun ortalamaHesapla(view:View){

        var deger:Int = linear.childCount-1
        var toplamNot:Double = 0.0
        var toplamKredi:Double = 0.0

        for(i in 0..deger){

            var tekSatir = linear.getChildAt(i)
            var geciciDers= Dersler(tekSatir.etYeniDersAd.text.toString(),((tekSatir.spYeniKredi.selectedItemPosition)+1).toString(),tekSatir.spYeniNot.selectedItem.toString())


            tumDersler.add(geciciDers)
        }

        for (oankiDers in tumDersler){


            toplamNot+= harfiNotaCevir(oankiDers.dersHarf)*(oankiDers.dersKredi.toDouble())
            toplamKredi +=oankiDers.dersKredi.toDouble()


        }

        var sonNot:Double = toplamNot/toplamKredi

        //Toast.makeText(this,"Ortalama: $sonNot",Toast.LENGTH_LONG).show()
        FancyToast.makeText(this,"Ortalama: $sonNot",FancyToast.LENGTH_LONG,FancyToast.SUCCESS,false).show()

        tumDersler.clear()





    }

    fun harfiNotaCevir(str:String):Double{

        var deger = 0.0

        when (str){

            "AA"->deger=4.0
            "BA"->deger=3.5
            "BB"->deger=3.0
            "CB"->deger=2.5
            "CC"->deger=2.0
            "DC"->deger=1.5
            "DD"->deger=1.0
            "FF"->deger=0.0
        }


        return deger



    }
}
