package com.example.msspuzzle

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class PuzzlePiece(
    var piece_id:Int,
    var face: ImageView,
    var point: Int,
    var posX: Int,
    var posY: Int,
    var chk_col: Boolean,
    var life: Boolean,
    var col_id: Char,) {

    // 퍼즐 조각의 점수에 따른 이미지 뷰 표시
    private fun displ_pic(codename: Context, point: Int): ImageView {
        return when (point) {
            1 -> ImageView(codename).apply { setImageResource(R.drawable.cnc00) }
            2 -> ImageView(codename).apply { setImageResource(R.drawable.cnc01) }
            3 -> ImageView(codename).apply { setImageResource(R.drawable.cnc02) }
            4 -> ImageView(codename).apply { setImageResource(R.drawable.cnc03) }
            5 -> ImageView(codename).apply { setImageResource(R.drawable.cnc04) }
            else -> throw IllegalArgumentException("에러가 나버렸다, 포인트값 : $point")
        }
    }

    // 퍼즐 조각 충돌 시 합치는 함수
    private fun col_plus() {


    }


}

// 게임 전체 동작용 코드
class MainActivity_test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)

        var gaming: Boolean = true


    }

    // 퍼즐조각 배치용 함수(매턴 반복)
    private fun dep_puz() {


    }


    // 퍼즐조각 움직이는 함수
    private fun mov_puz() {


    }


    // 퍼즐조각 충돌처리용 함수
    private fun col_puz() {


    }


    // 게임종료 처리용 함수
    private fun end_puz(gaming: Boolean) {
        if (!gaming) {
            val end_scr = findViewById<View>(R.id.overlay_end_table)
            end_scr.visibility = View.VISIBLE
        }
    }
}

