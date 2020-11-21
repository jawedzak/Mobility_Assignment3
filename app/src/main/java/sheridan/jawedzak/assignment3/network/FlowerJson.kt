package sheridan.jawedzak.assignment3.network

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlowerJson(
        val name: String,
        val label: String,
        val price: String,
        val text: String,
        val pictures: PicturesJson
) : Parcelable {

}