package com.android.mynote.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.mynote.Fragment.PageRead
import com.android.mynote.Fragment.PageWrite
import com.android.mynote.R
import com.android.mynote.databinding.PageReadBinding
import com.android.mynote.databinding.PageWriteBinding

class MenuSelecter(tgtActivity: AppCompatActivity) :
    RecyclerView.Adapter<MenuSelecter.PageHolder>() {

    // 페이지용 클릭 리스너 객체화
    private var clickListener: OnItemClickListener? = null

    // 클릭 리스너용 인터페이스
    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    // 클릭 리스너 지정용 메서드
    public fun setClickListener(tgtListener: OnItemClickListener){
        this.clickListener = tgtListener
    }

    // 뷰홀더 클래스 정의
    inner class PageHolder(val tgtView: View) : RecyclerView.ViewHolder(tgtView){
        public fun getIn(){
            tgtView.setOnClickListener{
                clickListener?.onItemClick(position)
            }
        }
    }

    // 뷰 홀더용 개별 페이지 레이아웃 출력용 메서드
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageHolder {
        // 레이아웃 출력기 객체화
        val dispLayout: LayoutInflater = LayoutInflater.from(parent.context)

        // 대상 뷰에 대한 분기문 로직
        val page: View = when (viewType){
            0 -> dispLayout.inflate(R.layout.page_read, parent, false)
            else -> dispLayout.inflate(R.layout.page_write, parent, false)
        }

        return PageHolder(page)
    }

    // 페이지별 뷰타입 반환용 메서드
    override fun getItemViewType(position: Int): Int {
        return position
    }

    // 뷰홀더 데이터 바인딩용 메서드
    override fun onBindViewHolder(holder: PageHolder, position: Int) {
        holder.getIn()
    }

    // 전시할 페이지 수 반환용 코드
    override fun getItemCount(): Int {
        return 2
    }
}