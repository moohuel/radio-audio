package yang.radio.audio.download.entity

data class WonderfulRadioRssJsonRootInfo(
    val rss: WonderfulRadioChannelInfo?
)

data class WonderfulRadioChannelInfo(
    val channel: WonderfulRadioItemRootInfo
)

data class WonderfulRadioItemRootInfo(
    val item: List<WonderfulRadioItemInfo>
)

data class WonderfulRadioItemInfo(
    val title: String,
    val enclosure: WonderfulRadioAudioEnclosureInfo
)

data class WonderfulRadioAudioEnclosureInfo(
    val url: String,
    val length: Int,
    val type: String
)