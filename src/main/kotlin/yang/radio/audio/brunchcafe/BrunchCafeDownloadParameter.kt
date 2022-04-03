package yang.radio.audio.brunchcafe

data class BrunchCafeDownloadParameter(
    val mp3UrlList: List<String>,
    val outputAudioTitleList: List<String>,
    val outputDir: String
)