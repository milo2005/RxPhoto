package org.test.testimg

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.jakewharton.rxbinding2.view.clicks
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.test.testimg.util.LifeDisposable
import org.test.testimg.util.PhotoUtil

class MainActivity : AppCompatActivity() {

    private val dis:LifeDisposable = LifeDisposable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        dis add btn.clicks()
                .flatMap { PhotoUtil.captureImage(this) }
                .subscribe()

        dis add PhotoUtil.processedImg
                .subscribe {//Nombre de Archivo
                    Log.i("JIJIJIJ","ENTRO CARAJO")
                    Picasso.get().load(it)
                            .into(img)
                }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        PhotoUtil.processImage(this, requestCode, resultCode, 800, 800)
    }

}
