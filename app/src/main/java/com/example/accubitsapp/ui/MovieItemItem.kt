package com.example.accubitsapp.ui


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.accubitsapp.R
import com.example.accubitsapp.databinding.ItemMovieBinding
import com.example.accubitsapp.db.model.movie.ResultsMovie
import com.squareup.picasso.Picasso
import com.xwray.groupie.databinding.BindableItem


class MovieItemItem(

    private val emp: ResultsMovie
) : BindableItem<ItemMovieBinding>(){

    override fun getLayout() = R.layout.item_movie

    override fun bind(viewBinding: ItemMovieBinding, position: Int) {
        viewBinding.setMoviedata(emp)
        if (viewBinding.moviedata?.poster_path!=null){
            loadImage(viewBinding.moviePoster, viewBinding.moviedata?.poster_path!!)
        }

    }


}
@BindingAdapter("bind:imageUrl")
fun loadImage(
    imageView: ImageView,
    url: String?

) {
	var urll : String?=null
        if (url!=null){
            urll="http://image.tmdb.org/t/p/w185"+url!!
            Picasso.get().load(urll)
                .error(R.drawable.no_image)
                .placeholder(R.drawable.no_image)
                .into(imageView)
        }


}
