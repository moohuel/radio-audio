package yang.radio.audio.download

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import yang.radio.audio.download.entity.WonderfulRadioRssJsonRootInfo

@Service
class RadioAudioDownloadManager {

    @Autowired
    lateinit var radioAudioDownloader: RadioAudioDownloader

    fun wonderfulRadio() {

        val jsonData = radioAudioJsonData()

//        for(item in jsonData.rss.item) {
//            val audioInfo = makeAudioInfo(item)
//            radioAudioDownloader.download(audioInfo)
//        }
    }

    private fun radioAudioJsonData(): WonderfulRadioRssJsonRootInfo {
        return WonderfulRadioRssJsonRootInfo(null)
    }
}