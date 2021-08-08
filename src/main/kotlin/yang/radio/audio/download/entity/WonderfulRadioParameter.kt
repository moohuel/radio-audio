package yang.radio.audio.download.entity

data class WonderfulRadioParameter(
    var firstBroadcastDay: String="20210531",
    val fromDay: String,
    val jsonFilePath: String,
    val audioOutputFileDir: String
)