package sheridan.jawedzak.assignment3.network
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PicturesJson(val small: String, val large: String) : Parcelable {

}