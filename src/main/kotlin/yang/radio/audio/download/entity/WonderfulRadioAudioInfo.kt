package yang.radio.audio.download.entity

data class WonderfulRadioAudioInfo(
    val audioTitle: String,
    val publicDay: String,
    val url: String,
    val length: Int,
    val type: String
)