package com.fadi.excercice_dev_movile

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Event(
    val id: Int,
    @param:StringRes val title: Int,
    @param:StringRes val category: Int,
    @param:StringRes val location: Int?,
    @param:StringRes val description: Int,
    val capacity: Int,
    @param:DrawableRes val image: Int? = null
)

internal val campusEvents = listOf(
    Event(
        id = 1,
        title = R.string.kotlin_talk_title,
        category = R.string.category_development,
        location = R.string.location_amphi_a,
        description = R.string.kotlin_talk_description,
        capacity = 40
    ),
    Event(
        id = 2,
        title = R.string.compose_workshop_title,
        category = R.string.category_development,
        location = R.string.location_salle_b12,
        description = R.string.compose_workshop_description,
        capacity = 18
    ),
    Event(
        id = 3,
        title = R.string.student_clubs_title,
        category = R.string.category_student_life,
        location = null,
        description = R.string.student_clubs_description,
        capacity = 30
    ),
    Event(
        id = 4,
        title = R.string.badminton_title,
        category = R.string.category_sport,
        location = R.string.location_gymnase,
        description = R.string.badminton_description,
        capacity = 0
    ),
    Event(
        id = 5,
        title = R.string.film_screening_title,
        category = R.string.category_culture,
        location = R.string.location_amphi_c,
        description = R.string.film_screening_description,
        capacity = 25
    )
)
