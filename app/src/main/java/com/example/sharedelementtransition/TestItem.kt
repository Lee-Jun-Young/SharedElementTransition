package com.example.sharedelementtransition

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ListItem(
    val id: Int,
    val name: String,
    val description: String,
    val image: String
) : Parcelable

val testData = listOf(
    ListItem(
        id = 0,
        name = "Item One",
        description = "This is the first item.",
        image = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d"
    ),
    ListItem(
        id = 1,
        name = "Item Two",
        description = "This is the second item.",
        image = "https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0"
    ),
    ListItem(
        id = 2,
        name = "Item Three",
        description = "This is the third item.",
        image = "https://images.unsplash.com/photo-1504610926078-a1611febcad3"
    ),
    ListItem(
        id = 3,
        name = "Item Four",
        description = "This is the fourth item.",
        image = "https://images.unsplash.com/photo-1504384308090-c894fdcc538d"
    ),
    ListItem(
        id = 4,
        name = "Item Five",
        description = "This is the fifth item.",
        image = "https://images.unsplash.com/photo-1516802273409-68526ee1bdd6"
    ),
    ListItem(
        id = 5,
        name = "Item Six",
        description = "This is the sixth item.",
        image = "https://images.unsplash.com/photo-1519125323398-675f0ddb6308"
    ),
    ListItem(
        id = 6,
        name = "Item Seven",
        description = "This is the seventh item.",
        image = "https://images.unsplash.com/photo-1481277542470-605612bd2d61"
    ),
    ListItem(
        id = 7,
        name = "Item Eight",
        description = "This is the eighth item.",
        image = "https://images.unsplash.com/photo-1482062364825-616fd23b8fc1"
    ),
    ListItem(
        id = 8,
        name = "Item Nine",
        description = "This is the ninth item.",
        image = "https://images.unsplash.com/photo-1447752875215-b2761acb3c5d"
    ),
    ListItem(
        id = 9,
        name = "Item Ten",
        description = "This is the tenth item.",
        image = "https://images.unsplash.com/photo-1425321395722-b1dd54a97cf3"
    ),
    ListItem(
        id = 10,
        name = "Item Eleven",
        description = "This is the eleventh item.",
        image = "https://images.unsplash.com/photo-1432139509613-5c4255815697"
    ),
    ListItem(
        id = 11,
        name = "Item Twelve",
        description = "This is the twelfth item.",
        image = "https://images.unsplash.com/photo-1446776811953-b23d57bd21aa"
    ),
    ListItem(
        id = 12,
        name = "Item Thirteen",
        description = "This is the thirteenth item.",
        image = "https://images.unsplash.com/photo-1501594907352-04cda38ebc29"
    ),
    ListItem(
        id = 13,
        name = "Item Fourteen",
        description = "This is the fourteenth item.",
        image = "https://images.unsplash.com/photo-1507525428034-b723cf961d3e"
    ),
    ListItem(
        id = 14,
        name = "Item Fifteen",
        description = "This is the fifteenth item.",
        image = "https://images.unsplash.com/photo-1519452575417-564c1401ecc0"
    )
)
