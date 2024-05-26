package ir.sajjadasadi.goldapp

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import ir.sajjadasadi.goldapp.databinding.RecylerMainViewBinding
import ir.sajjadasadi.goldapp.remote.dataModel.ContentModel

class RecyclerMainAdapter(
    private val Alldata: ArrayList<ContentModel>
) : RecyclerView.Adapter<RecyclerMainAdapter.MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, ViewType: Int): MainViewHolder {
        return MainViewHolder(
            RecylerMainViewBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = Alldata.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.setData(Alldata[position])
    }


    inner class MainViewHolder(
        private val binding: RecylerMainViewBinding
    ) : ViewHolder(binding.root) {

        fun setData(data: ContentModel) {
            if (data.label == "بیت کوین") {
                binding.txtLabel.text = data.label
                val a = (data.price)
                val formattedString = String.format("%,d", a)
                binding.txtPrice.text = "${formattedString} دلار "
            }
            else if(data.label=="طلای 24 عیار")
            {

                binding.imageView3.setImageResource(R.drawable.ic_24)
                binding.txtLabel.text = data.label
                val a = (data.price / 10)
                val formattedString = String.format("%,d", a)
                binding.txtPrice.text = "${formattedString} تومان"
            }
            else {
                binding.txtLabel.text = data.label
                val a = (data.price / 10)
                val formattedString = String.format("%,d", a)
                binding.txtPrice.text = "${formattedString} تومان"
            }

        }
    }
}