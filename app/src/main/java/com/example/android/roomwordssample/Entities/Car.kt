package com.example.android.roomwordssample.Entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_table")
data class Car(@PrimaryKey@ColumnInfo(name = "id_car")
               var id: Int,
               @ColumnInfo(name = "brand")
               val brand : String,
               @ColumnInfo(name = "model")
               val model : String,
               @ColumnInfo(name = "year")
               val year : Int,
               @ColumnInfo(name = "info")
               val Info: String,
               @ColumnInfo(name = "price")
               val price: Int,
               @ColumnInfo(name = "specs")
               val specs: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            id = parcel.readInt(),
            brand = parcel.readString(),
            model = parcel.readString(),
            year = parcel.readInt(),
            Info = parcel.readString(),
            price = parcel.readInt(),
            specs = parcel.readString()
    )
    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(id)
        dest.writeString(brand)
        dest.writeString(model)
        dest.writeInt(year)
        dest.writeString(Info)
        dest.writeInt(price)
        dest.writeString(specs)

    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Car> {
        override fun createFromParcel(parcel: Parcel): Car {
            return Car(parcel)
        }

        override fun newArray(size: Int): Array<Car?> {
            return arrayOfNulls(size)
        }
    }
}
