package com.raka.myapp.data

data class Project(
    val id: String = java.util.UUID.randomUUID().toString(),
    val name: String,
    val description: String,
    val techStack: List<String>,
    val imageUrl: String = "",
    val githubUrl: String = ""
)

// Sample project data
object ProjectData {
    fun getSampleProjects() = listOf(
        Project(
            name = "DevPortfolio Hub",
            description = "An Android app combining portfolio showcase with functional note-taking and time tracking features",
            techStack = listOf("Kotlin", "Jetpack Compose", "Material3", "MVVM"),
            githubUrl = "github.com/sandipgautam"
        ),
        Project(
            name = "Weather Forecast App",
            description = "Real-time weather updates with beautiful UI and location-based forecasts",
            techStack = listOf("Kotlin", "Retrofit", "API Integration", "MVVM"),
            githubUrl = "github.com/sandipgautam"
        ),
        Project(
            name = "E-Commerce Mobile",
            description = "Full-featured shopping app with cart, payment gateway, and order tracking",
            techStack = listOf("Kotlin", "Firebase", "Room DB", "Payment APIs"),
            githubUrl = "github.com/sandipgautam"
        )
    )
}
