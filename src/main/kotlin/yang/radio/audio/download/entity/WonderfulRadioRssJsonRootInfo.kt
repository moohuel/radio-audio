package yang.radio.audio.download.entity

data class WonderfulRadioRssJsonRootInfo(
    val rss: WonderfulRadioRssInfo
)

data class WonderfulRadioRssInfo(
    val channel: WonderfulRadioRssChannelInfo
)

data class WonderfulRadioRssChannelInfo(
    val item: List<WonderfulRadioRssItemInfo>
)

data class WonderfulRadioRssItemInfo(
    val title: String,
    val enclosure: WonderfulRadiRssEnclosureInfo
)

data class WonderfulRadiRssEnclosureInfo(
    val url: String,
    val length: Int,
    val type: String
)