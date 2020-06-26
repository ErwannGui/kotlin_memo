package com.ynov.memokotlin.storage

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_memo")
class Memo(@PrimaryKey(autoGenerate = true)
           var id: Int,
           var title: String?,
           var content: String?) :Parcelable{


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString())


    // Method #1
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    // Method #2
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(content)
    }

    // Method #3
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Memo> {
        // Method #4
        override fun createFromParcel(parcel: Parcel): Memo {
            return Memo(parcel)
        }

        // Method #5
        override fun newArray(size: Int): Array<Memo?> {
            return arrayOfNulls(size)
        }
    }

    // Method #6
    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        return result
    }


}