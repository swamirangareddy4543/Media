package com.swami.media.data


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
                    "https://media.istockphoto.com/id/2211042706/photo/tractor-fertilizing-verdant-agricultural-field-during-golden-sunset.webp?a=1&b=1&s=612x612&w=0&k=20&c=Gu3PE3beJRYzN954Eae7Xh7wDxM6lfehkHxxpXZ4L4c=",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m3",
                    "https://images.unsplash.com/photo-1559884743-74a57598c6c7?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDN8fGFncmljdWx0dXJlfGVufDB8fDB8fHww&auto=format&fit=crop&q=60&w=500",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m3",
                    "https://plus.unsplash.com/premium_photo-1661808770389-30a3ed35b7fe?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDl8fGFncmljdWx0dXJlfGVufDB8fDB8fHww&auto=format&fit=crop&q=60&w=500",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m3",
                    "https://images.unsplash.com/photo-1464226184884-fa280b87c399?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=1170",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m4",
                    "https://media.istockphoto.com/id/184819009/video/combine-harvesting-corn-for-silage.mp4?s=mp4-640x640-is&k=20&c=qheZVwWZz4Tw_Y2LQk98N23axyYQkC-cX9lPFb3PIdI=",
                    MediaType.VIDEO
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
                    "https://images.unsplash.com/photo-1589876568181-a1508b8ef473?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTg3fHxhZ3JpY3VsdHVyZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&q=60&w=500",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m3",
                    "https://images.unsplash.com/photo-1723540561785-6e428f03a3e4?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjQyfHxhZ3JpY3VsdHVyZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&q=60&w=500",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m3",
                    "https://images.unsplash.com/photo-1558289282-647de9fdf608?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mzc2fHxhZ3JpY3VsdHVyZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&q=60&w=500",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m3",
                    "https://images.unsplash.com/photo-1709534004784-d8c6455d03dc?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDI0fHxhZ3JpY3VsdHVyZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&q=60&w=500",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m4",
                    "https://media.istockphoto.com/id/1387179891/video/tractor-spraying-soybean-plantation-in-brazil.mp4?s=mp4-640x640-is&k=20&c=TJkTfFoWERZI-DsMYXvi515l55o_spTNSSjL5YEr29E=",
                    MediaType.VIDEO
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
                    "https://images.unsplash.com/photo-1580982327559-c1202864eb05?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8ODZ8fGFncmljdWx0dXJlfGVufDB8fDB8fHww&auto=format&fit=crop&q=60&w=500",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m3",
                    "https://images.unsplash.com/photo-1566218246241-934ad8b38ea6?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTEwfHxhZ3JpY3VsdHVyZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&q=60&w=500",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m3",
                    "https://images.unsplash.com/photo-1709532390940-5c09fcdf098d?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTg4fHxhZ3JpY3VsdHVyZXxlbnwwfHwwfHx8MA%3D%3D&auto=format&fit=crop&q=60&w=500",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m3",
                    "https://media.istockphoto.com/id/2224548406/photo/tractor-preparing-ground-for-seeding.webp?a=1&b=1&s=612x612&w=0&k=20&c=l_LYNYZ4zRV2SbmheJRVOyFUOxWY2Gr_lk8g2DjlTIU=",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m4",
                    "https://media.istockphoto.com/id/1305087489/video/agriculture-drone-fly-to-sprayed-fertilizer-on-the-rice-field.mp4?s=mp4-640x640-is&k=20&c=YgorSU5dffESoTCI2YKyRxgb2kxdi1saTb_fqSYz0y8=",
                    MediaType.VIDEO
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
                    "https://plus.unsplash.com/premium_photo-1661962692059-55d5a4319814?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&q=80&w=1974",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m3",
                    "https://images.unsplash.com/photo-1492496913980-501348b61469?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8N3x8YWdyaWN1bHR1cmV8ZW58MHx8MHx8fDA%3D&auto=format&fit=crop&q=60&w=500",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m3",
                    "https://images.unsplash.com/photo-1597916829826-02e5bb4a54e0?ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTJ8fGFncmljdWx0dXJlfGVufDB8fDB8fHww&auto=format&fit=crop&q=60&w=500",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m3",
                    "https://media.istockphoto.com/id/2224548406/photo/tractor-preparing-ground-for-seeding.webp?a=1&b=1&s=612x612&w=0&k=20&c=l_LYNYZ4zRV2SbmheJRVOyFUOxWY2Gr_lk8g2DjlTIU=",
                    MediaType.IMAGE
                ),
                MediaItem(
                    "m4",
                    "https://media.istockphoto.com/id/1397019394/video/camera-moves-over-the-ground-after-rain.mp4?s=mp4-640x640-is&k=20&c=BEjEWLekfrjLlE6yNRhCpdLSSJIjrfZiVnR_Jo44qwk=",
                    MediaType.VIDEO
                )
            )
        )
    )
}