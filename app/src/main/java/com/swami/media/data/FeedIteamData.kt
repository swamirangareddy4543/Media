package com.swami.media.data

import com.swami.media.R

data class FeedItemData(
    val id: Long,
    val title: String,
    val shortDescription: String,
    val fullDescription: String,
    val mediaItems: List<MediaItem>
)

data class MediaItem(
    val id: String,
    val url: String,
    val type: MediaType
)

enum class MediaType {
    IMAGE, VIDEO
}

fun getMockFeedItems(): List<FeedItemData> {
    return listOf(
        FeedItemData(
            id = 1,
            title = "Architecture",
            shortDescription = "Agriculture is the practice of cultivating plants and raising livestock to produce food, fiber, fuel, and other products.",
            fullDescription = "Agriculture is the science and art of cultivating the soil, growing crops, and raising livestock to provide food, fiber, fuel, and other products to sustain and enhance human life. It was a pivotal development in human history, enabling people to settle in one place and eventually form civilizations by creating food surpluses. Today, agriculture remains crucial for global food security, provides employment for a significant portion of the world's population, and supplies raw materials for various industries. Modern agriculture faces challenges related to sustainability, climate change, and meeting the demands of a growing global population.",
            mediaItems = listOf(
                MediaItem(
                    "m3",
                    "https://media.giphy.com/media/3o7aD2saalBwwftBIY/giphy.gif",
                    MediaType.IMAGE
                )
            )
        ),
        FeedItemData(
            id = 2,
            title = "The Water-Food Connection",
            shortDescription = "Focuses on the critical challenge of water scarcity in farming and the innovative practices required to ensure sustainable food production using less water.",
            fullDescription = "Water is agriculture's most precious resource. Discover the challenges of water scarcity and how smart irrigation systems, drought-resistant crops, and water-efficient farming practices are vital to ensuring sustainable food production for a growing world.",
            mediaItems = listOf(
                MediaItem(
                    "m3",
                    "https://media.giphy.com/media/3o7aD2saalBwwftBIY/giphy.gif",
                    MediaType.IMAGE
                )
            )
        ),
        FeedItemData(
            id = 3,
            title = "Technology in the Fields: Not Your Grandfather's Farm",
            shortDescription = "Explore how high-tech tools like GPS, drones, and AI are used in precision agriculture to maximize crop yields while minimizing environmental impact.",
            fullDescription = "Agriculture is high-tech! Precision farming uses drones, GPS, and AI to monitor crops, predict yields, and optimize resources like water and fertilizer. Dive into the world of agritech and see how science and engineering are helping farmers grow more food with less environmental impact.",
            mediaItems = listOf(
                MediaItem(
                    "m3",
                    "https://media.giphy.com/media/3o7aD2saalBwwftBIY/giphy.gif",
                    MediaType.IMAGE
                )
            )
        ),
        FeedItemData(
            id = 4,
            title = "The Superpower of Soil",
            shortDescription = "Soil is a vital living ecosystem that stores carbon, retains water, and is the foundation for growing all nutritious food.",
            fullDescription = "Most people see dirt, but farmers see a complex, living ecosystem! The soil is the foundation of all agriculture. Learn about the incredible world beneath your feet: how healthy soil stores carbon to fight climate change, holds water to prevent drought, and is packed with microorganisms essential for growing nutritious food.",
            mediaItems = listOf(
                MediaItem(
                    "m3",
                    "https://media.giphy.com/media/3o7aD2saalBwwftBIY/giphy.gif",
                    MediaType.IMAGE
                )
            )
        )
    )
}