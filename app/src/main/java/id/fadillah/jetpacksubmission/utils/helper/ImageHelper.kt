package id.fadillah.jetpacksubmission.utils.helper

import android.util.Log
import android.widget.ImageView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import id.fadillah.jetpacksubmission.R


object ImageHelper {
    private const val TAG = "ImageHelper"
    fun getImage(
        imageView: ImageView,
        url: String,
        placeholder: Int = R.drawable.placeholder_loading,
        error: Int = R.drawable.ic_no_images
    ) {
        Picasso.get()
            .load(url)
            .placeholder(placeholder)
            .error(error)
            .into(imageView, object : Callback {
                override fun onSuccess() {}

                override fun onError(e: Exception?) {
                    Log.e(TAG, "Error: ${e?.message}")
                    Log.e(TAG, "URL: ${e?.message}")
                }
            })
    }
}