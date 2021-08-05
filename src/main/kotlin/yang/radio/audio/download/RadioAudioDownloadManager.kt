package yang.radio.audio.download

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import yang.radio.audio.download.entity.WonderfulRadioItemInfo
import yang.radio.audio.download.entity.WonderfulRadioRssJsonRootInfo

@Service
class RadioAudioDownloadManager {

    @Autowired
    lateinit var radioAudioDownloader: RadioAudioDownloader
    @Autowired
    lateinit var radioAudioRssJsonConverter: RadioAudioRssJsonConverter
    @Autowired
    lateinit var radioAudioInfoConverter: RadioAudioInfoConverter

    fun wonderfulRadio() {

        val jsonData = radioAudioRssJsonConverter.jsonParser()

        for((index, item) in jsonData.rss.channel.item.withIndex()) {

            val audioInfo = radioAudioInfoConverter.rebuildAudioInfo(item)
            if(audioInfo == null) {
                continue
            }

            radioAudioDownloader.download(audioInfo)

//            if(index >= 5) {
//                break
//            }
        }
    }
}