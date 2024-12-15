package com.example.my_slambook1

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable

data class Item(
    var fullName: String? = null,
    var nickname: String? = null,
    var address: String? = null,
    var email: String? = null,
    var birthMonth: String? = null,
    var birthDay: String? = null,
    var birthYear: String? = null,
    var gender: String? = null,
    var status: String? = null,

    var favoriteHobbies: String? = "",
    var freeTimeActivities: String? = "",
    var indoorOutdoorPreference: String? = "",
    var wantedHobby: String? = "",

    var favoriteMovie: String? = "",
    var favoriteColor: String? = "",
    var favoriteSong: String? = "",
    var favoriteCelebrity: String? = "",
    var favoriteSport: String? = "",
    var favoriteFood: String? = "",
    var favoritePerson: String? = "",
    var favoritePlace: String? = "",

    var selfDescription: String? = "",
    var biggestFear: String? = "",
    var happinessSource: String? = "",
    var selfLove: String? = "",
    var inspirationSource: String? = "",
    var uniqueTrait: String? = "",
    var craziestThing: String? = "",
    var proudOf: String? = "",
    var age: Int? = 0,
    val id: Int = 0,

    var avatar: Drawable? = null
) : Parcelable {

    // Constructor for Parcelable
    constructor(parcel: Parcel) : this(
        fullName = parcel.readString(),
        nickname = parcel.readString(),
        address = parcel.readString(),
        email = parcel.readString(),
        birthMonth = parcel.readString(),
        birthDay = parcel.readString(),
        birthYear = parcel.readString(),
        gender = parcel.readString(),
        status = parcel.readString(),
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
        age = parcel.readInt() // Reading the age as an integer
    )

    // Writing to the Parcel
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(fullName)
        parcel.writeString(nickname)
        parcel.writeString(address)
        parcel.writeString(email)
        parcel.writeString(birthMonth)
        parcel.writeString(birthDay)
        parcel.writeString(birthYear)
        parcel.writeString(gender)
        parcel.writeString(status)
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
        parcel.writeInt(age ?: 0) // Writing the age
    }

    override fun describeContents(): Int {
        return 0
    }

    // Companion object to generate the Parcelable implementation
    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }

    // Add an update method to update specific fields
    fun update(
        fullName: String? = null,
        nickname: String? = null,
        address: String? = null,
        email: String? = null,
        birthMonth: String? = null,
        birthDay: String? = null,
        birthYear: String? = null,
        gender: String? = null,
        status: String? = null,
        favoriteHobbies: String? = null,
        freeTimeActivities: String? = null,
        indoorOutdoorPreference: String? = null,
        wantedHobby: String? = null,
        favoriteMovie: String? = null,
        favoriteColor: String? = null,
        favoriteSong: String? = null,
        favoriteCelebrity: String? = null,
        favoriteSport: String? = null,
        favoriteFood: String? = null,
        favoritePerson: String? = null,
        favoritePlace: String? = null,
        selfDescription: String? = null,
        biggestFear: String? = null,
        happinessSource: String? = null,
        selfLove: String? = null,
        inspirationSource: String? = null,
        uniqueTrait: String? = null,
        craziestThing: String? = null,
        proudOf: String? = null,
        avatar: Drawable? = null
    ) {
        this.fullName = fullName ?: this.fullName
        this.nickname = nickname ?: this.nickname
        this.address = address ?: this.address
        this.email = email ?: this.email
        this.birthMonth = birthMonth ?: this.birthMonth
        this.birthDay = birthDay ?: this.birthDay
        this.birthYear = birthYear ?: this.birthYear
        this.gender = gender ?: this.gender
        this.status = status ?: this.status
        this.favoriteHobbies = favoriteHobbies ?: this.favoriteHobbies
        this.freeTimeActivities = freeTimeActivities ?: this.freeTimeActivities
        this.indoorOutdoorPreference = indoorOutdoorPreference ?: this.indoorOutdoorPreference
        this.wantedHobby = wantedHobby ?: this.wantedHobby
        this.favoriteMovie = favoriteMovie ?: this.favoriteMovie
        this.favoriteColor = favoriteColor ?: this.favoriteColor
        this.favoriteSong = favoriteSong ?: this.favoriteSong
        this.favoriteCelebrity = favoriteCelebrity ?: this.favoriteCelebrity
        this.favoriteSport = favoriteSport ?: this.favoriteSport
        this.favoriteFood = favoriteFood ?: this.favoriteFood
        this.favoritePerson = favoritePerson ?: this.favoritePerson
        this.favoritePlace = favoritePlace ?: this.favoritePlace
        this.selfDescription = selfDescription ?: this.selfDescription
        this.biggestFear = biggestFear ?: this.biggestFear
        this.happinessSource = happinessSource ?: this.happinessSource
        this.selfLove = selfLove ?: this.selfLove
        this.inspirationSource = inspirationSource ?: this.inspirationSource
        this.uniqueTrait = uniqueTrait ?: this.uniqueTrait
        this.craziestThing = craziestThing ?: this.craziestThing
        this.proudOf = proudOf ?: this.proudOf
        this.avatar = avatar ?: this.avatar
    }
}
