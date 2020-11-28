package sheridan.jawedzak.assignment3

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import sheridan.jawedzak.assignment3.network.FlowerJson
import sheridan.jawedzak.assignment3.overview.FlowersApiStatus
import sheridan.jawedzak.assignment3.overview.PhotoGridAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<FlowerJson>?) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?)  {
    val img = "http://tetervak.dev.fast.sheridanc.on.ca/Examples/jQuery/Flowers3/images/flowers/$imgUrl"
    imgUrl.let {
        val imgUri = img.toUri().buildUpon()
                .scheme("https")
                .build()
        Glide.with(imgView.context)
                .load(imgUri)
                .apply(RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imgView)
    }
}

@BindingAdapter("flowersApiStatus")
fun bindStatus(statusImageView: ImageView, status: FlowersApiStatus?) {
    when (status) {
        FlowersApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        FlowersApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        FlowersApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}
