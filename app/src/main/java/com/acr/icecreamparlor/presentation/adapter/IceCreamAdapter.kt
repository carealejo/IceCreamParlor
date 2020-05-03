package com.acr.icecreamparlor.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.acr.icecreamparlor.R
import com.acr.icecreamparlor.domain.model.IceCream
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_ice_cream.view.*

class IceCreamAdapter(private val context: Context, private val listener: Listener) : RecyclerView.Adapter<IceCreamAdapter.IceCreamHolder>() {

    interface Listener{
        fun onIceCreamClick(iceCream: IceCream)
    }

    companion object {
        private const val CONE = "cone"
        private const val FROYO = "froyo"
        private const val ICE_CREAM = "ice_cream"
        private const val POPSICLE = "popsicle"
    }

    private var iceCreamList = mutableListOf<Pair<IceCream?, IceCream?>>()

    class IceCreamHolder(iceCreamView: View) : RecyclerView.ViewHolder(iceCreamView) {
        var iceCream1Container = iceCreamView.iceCream1
        var iceCream1Counter = iceCreamView.iceCream1Counter
        var iceCream1Image = iceCreamView.iceCream1Image
        var iceCream1Title = iceCreamView.iceCream1Title
        var iceCream1Price = iceCreamView.iceCream1Price
        var iceCream2Container = iceCreamView.iceCream2
        var iceCream2Counter = iceCreamView.iceCream2Counter
        var iceCream2Image = iceCreamView.iceCream2Image
        var iceCream2Title = iceCreamView.iceCream2Title
        var iceCream2Price = iceCreamView.iceCream2Price
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): IceCreamHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_ice_cream, parent, false)
        return IceCreamHolder(view)
    }

    override fun onBindViewHolder(holder: IceCreamHolder, position: Int) {
        val iceCreams = iceCreamList.getOrNull(position)
        iceCreams?.let {
            iceCreams.first?.let { iceCream ->
                val imageResource = when(iceCream.type) {
                    CONE -> R.drawable.cone
                    FROYO -> R.drawable.froyo
                    ICE_CREAM -> R.drawable.ice_cream
                    POPSICLE -> R.drawable.popsicle
                    else -> R.drawable.cone
                }
                Glide.with(context)
                    .load(context.resources.getDrawable(imageResource))
                    .into(holder.iceCream1Image)
                holder.iceCream1Image.setBackgroundColor(Color.parseColor(iceCream.bg_color))
                holder.iceCream1Title.text = iceCream.completeName
                holder.iceCream1Price.text = "${iceCream.price}"
                holder.iceCream1Container.setOnClickListener {
                    if (iceCream.counter < 2) {
                        iceCream.counter += 1
                        holder.iceCream1Counter.text = iceCream.counter.toString()
                        holder.iceCream1Counter.visibility = View.VISIBLE
                    } else {
                        iceCream.counter = 0
                        holder.iceCream1Counter.visibility = View.INVISIBLE
                    }
                    listener.onIceCreamClick(iceCream)
                }
            }

            val secondIceCream = iceCreams.second

            if (secondIceCream == null){
                holder.iceCream2Container.visibility = View.INVISIBLE
            } else {
                val imageResource = when(secondIceCream.type) {
                    CONE -> R.drawable.cone
                    FROYO -> R.drawable.froyo
                    ICE_CREAM -> R.drawable.ice_cream
                    POPSICLE -> R.drawable.popsicle
                    else -> R.drawable.cone
                }
                Glide.with(context)
                    .load(context.resources.getDrawable(imageResource))
                    .into(holder.iceCream2Image)
                holder.iceCream2Image.setBackgroundColor(Color.parseColor(secondIceCream.bg_color))
                holder.iceCream2Title.text = secondIceCream.completeName
                holder.iceCream2Price.text = "${secondIceCream.price}"
                holder.iceCream1Container.setOnClickListener {
                    if (secondIceCream.counter < 2) {
                        secondIceCream.counter += 1
                        holder.iceCream1Counter.text = secondIceCream.counter.toString()
                        holder.iceCream1Counter.visibility = View.VISIBLE
                    } else {
                        secondIceCream.counter = 0
                        holder.iceCream1Counter.visibility = View.INVISIBLE
                    }
                    listener.onIceCreamClick(secondIceCream)
                }
            }
        }
    }

    override fun getItemCount() = iceCreamList.size

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    fun updateOptionsSpaceList(newData: List<IceCream?>){
        iceCreamList = mutableListOf()
        for (iterator in 0 until newData.size step 2) {
            val iceCream1 = newData.getOrNull(iterator)
            val iceCream2 = newData.getOrNull(iterator + 1)
            iceCreamList.add(Pair(iceCream1, iceCream2))
        }
        notifyDataSetChanged()
    }
}