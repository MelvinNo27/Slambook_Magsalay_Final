package com.example.my_slambook1

import android.os.Parcel
import android.os.Parcelable

data class DataClass(
    val fullName: String? = null,
    val nickname: String? = null,
    val address: String? = null,
    val email: String? = null,
    val age: String? = null,
    val gender: String? = null,
    val status: String? = null,
    val birthMonth: String? = null,
    val birthDay: String? = null,
    val birthYear: String? = null,
    val favoriteHobbies: String? = null,
    val freeTimeActivities: String? = null,
    val indoorOutdoorPreference: String? = null,
    val wantedHobby: String? = null,
    val favoriteMovie: String? = null,
    val favoriteColor: String? = null,
    val favoriteSong: String? = null,
    val favoriteCelebrity: String? = null,
    val favoriteSport: String? = null,
    val favoriteFood: String? = null,
    val favoritePerson: String? = null,
    val favoritePlace: String? = null,
    val selfDescription: String? = null,
    val biggestFear: String? = null,
    val happinessSource: String? = null,
    val selfLove: String? = null,
    val inspirationSource: String? = null,
    val uniqueTrait: String? = null,
    val craziestThing: String? = null,
    val proudOf: String? = null,
    val avatarResId: Int? = null,

) : Parcelable {
    constructor(parcel: Parcel) : this(
        fullName = parcel.readString(),
        nickname = parcel.readString(),
        address = parcel.readString(),
        email = parcel.readString(),
        age = parcel.readString(),
        gender = parcel.readString(),
        status = parcel.readString(),
        birthMonth = parcel.readString(),
        birthDay = parcel.readString(),
        birthYear = parcel.readString(),
        favoriteHobbies = parcel.readString(),
        freeTimeActivities = parcel.readString(),
        indoorOutdoorPreference = parcel.readString(),
        wantedHobby = parcel.readString(),
        favoriteMovie = parcel.readString(),
        favoriteColor = parcel.readString(),
        favoriteSong = parcel.readString(),
        favoriteCelebrity = parcel.readString(),
        favoriteSport = parcel.readString(),
        favoriteFood = parcel.readString(),
        favoritePerson = parcel.readString(),
        favoritePlace = parcel.readString(),
        selfDescription = parcel.readString(),
        biggestFear = parcel.readString(),
        happinessSource = parcel.readString(),
        selfLove = parcel.readString(),
        inspirationSource = parcel.readString(),
        uniqueTrait = parcel.readString(),
        craziestThing = parcel.readString(),
        proudOf = parcel.readString(),

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fullName)
        parcel.writeString(nickname)
        parcel.writeString(address)
        parcel.writeString(email)
        parcel.writeString(age)
        parcel.writeString(gender)
        parcel.writeString(status)
        parcel.writeString(birthMonth)
        parcel.writeString(birthDay)
        parcel.writeString(birthYear)
        parcel.writeString(favoriteHobbies)
        parcel.writeString(freeTimeActivities)
        parcel.writeString(indoorOutdoorPreference)
        parcel.writeString(wantedHobby)
        parcel.writeString(favoriteMovie)
        parcel.writeString(favoriteColor)
        parcel.writeString(favoriteSong)
        parcel.writeString(favoriteCelebrity)
        parcel.writeString(favoriteSport)
        parcel.writeString(favoriteFood)
        parcel.writeString(favoritePerson)
        parcel.writeString(favoritePlace)
        parcel.writeString(selfDescription)
        parcel.writeString(biggestFear)
        parcel.writeString(happinessSource)
        parcel.writeString(selfLove)
        parcel.writeString(inspirationSource)
        parcel.writeString(uniqueTrait)
        parcel.writeString(craziestThing)
        parcel.writeString(proudOf)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<DataClass> {
        override fun createFromParcel(parcel: Parcel): DataClass = DataClass(parcel)
        override fun newArray(size: Int): Array<DataClass?> = arrayOfNulls(size)
    }
}
