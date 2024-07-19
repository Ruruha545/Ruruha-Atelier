package com.android.spartanmarket_240719

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.spartanmarket_240719.databinding.SecondScreenBinding

class SecActivity : AppCompatActivity() {

    private lateinit var secondScreenBinding: SecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        secondScreenBinding = SecondScreenBinding.inflate(layoutInflater)
        setContentView(secondScreenBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.second_screen)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val PresentItem:SpartanItem? = intent.getParcelableExtra<SpartanItem>("SelectItem")


        PresentItem?.let{
            secondScreenBinding.apply{

                // 소개용 레이아웃(item_main_imageView)
                secItemMainImageView.setImageResource(PresentItem.img_index)

                // 소개용 레이아웃(item_title_Container) 내부 요소
                secItemTitleNameTextView.text = PresentItem?.name
                secItemTitleLocationTextView.text = PresentItem?.location

                // 소개용 레이아웃(item_intro_Container) 내부 요소
                secItemIntroTitleTextView.text = PresentItem?.title
                secItemIntroPromotionTextView.text = PresentItem?.promotion
                secItemIntroPriceTextView.text = "${String.format("%,d", PresentItem?.price)}원"
            }
        }
    }
}