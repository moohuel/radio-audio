package yang.radio.audio.download.entity

data class WonderfulRadioAudioItemInfo(
    val audioTitle: String,
    val broadcastDay: String,
    val url: String,
    val length: Int,
    val type: String
)