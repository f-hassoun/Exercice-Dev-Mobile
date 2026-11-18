package com.fadi.excercice_dev_movile

data class Event(
    val id: Int,
    val title: String,
    val category: String,
    val location: String?,
    val capacity: Int
)

internal val campusEvents = listOf(
    Event(1, "Conférence Kotlin", "Développement", "Amphi A", 40),
    Event(2, "Atelier Jetpack Compose", "Développement", "Salle B12", 18),
    Event(3, "Rencontre des associations", "Vie étudiante", null, 30),
    Event(4, "Tournoi de badminton", "Sport", "Gymnase", 0),
    Event(5, "Projection cinéma", "Culture", "Amphi C", 25)
)
