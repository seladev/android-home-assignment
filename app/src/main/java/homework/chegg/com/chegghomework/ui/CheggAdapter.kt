package homework.chegg.com.chegghomework.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import homework.chegg.com.chegghomework.data.CheggDataModel
import homework.chegg.com.chegghomework.databinding.CardItemBinding


/**
 * CheggAdapter - Adapter for display all chegg items
 */
class CheggAdapter():
    RecyclerView.Adapter<CheggAdapter.CheggViewHolder?>() {

    var mData: List<CheggDataModel> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    fun getItem(id: Int) = mData[id]

    override fun getItemCount() = mData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheggViewHolder {
        val binding = CardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CheggViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CheggViewHolder, position: Int) {
        val cheggDataModel = mData[position]
        holder.binding.textViewCardItemTitle.text = cheggDataModel.title
        holder.binding.textViewCardItemSubtitle.text = cheggDataModel.subtitle
//        Glide.with(holder.itemView.context).load(cheggDataModel.imageUrl)
//            .into( holder.binding.imageViewCardItem);

        Picasso.get()
            .load(cheggDataModel.imageUrl)
            .into(holder.binding.imageViewCardItem);
    }


    inner class CheggViewHolder(val binding: CardItemBinding)
        :RecyclerView.ViewHolder(binding.root)




}
