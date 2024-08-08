package com.android.imageresearcher.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.imageresearcher.DataClass.Document
import com.android.imageresearcher.DataClass.ResponseDocuments
import com.android.imageresearcher.databinding.PartImageItemBinding

class ImageResearchAdapter(val ImgList: MutableList<Document>):
    RecyclerView.Adapter<ImageResearchAdapter.Container>() {

        // 뷰홀더 클래스 생성
        class Container(val itemBinding: PartImageItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
            // (체크)받아온 아이템을 넣어라 !!
            fun matching(item: Document){

                itemBinding.partResearchimageImageview.setImageResource()
                itemBinding.partResearchimagetitleTextview.setText()
                itemBinding.partResearchimagedateTextview.setText()
            }
        }


    // 뷰홀더 신규 생성 로직
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageResearchAdapter.Container {
        val newHolder:PartImageItemBinding = PartImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageResearchAdapter.Container(newHolder)
    }

    // 뷰홀더 전체 길이 계산 로직
    override fun getItemCount(): Int {

        return ImgList.size
    }

    // 뷰홀더 데이터 매칭용 로직
    override fun onBindViewHolder(holder: ImageResearchAdapter.Container, position: Int) {

        holder.matching(ImgList[position])
    }
}