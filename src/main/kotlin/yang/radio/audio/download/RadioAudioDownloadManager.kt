package yang.radio.audio.download

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import yang.radio.audio.download.entity.WonderfulRadioAudioItemInfo
import yang.radio.audio.download.entity.WonderfulRadioParameter
import yang.radio.audio.download.entity.WonderfulRadioRssJsonRootInfo

@Service
class RadioAudioDownloadManager {

    @Autowired
    lateinit var radioAudioRssJsonDataLoader: RadioAudioRssJsonDataLoader
    @Autowired
    lateinit var radioAudioInfoExtractor: RadioAudioInfoExtractor
    @Autowired
    lateinit var radioAudioDownloader: RadioAudioDownloader

    fun wonderfulRadio() {

        val param = makeParameter()

        val rssJsonRootInfo = radioAudioRssJsonDataLoader.audioRootInfoFromJson(param)
        val audioInfoList = extractAudioInfo(rssJsonRootInfo)
        val targetDownloadList = extractTargetDownload(audioInfoList, param)

        downloadRadioAudio(targetDownloadList, param)
    }

    private fun makeParameter(): WonderfulRadioParameter {

        return WonderfulRadioParameter(
            firstBroadcastDay = "20210531",
            fromDay = "20210809",
            jsonFilePath = "C:\\download\\wonderful_json\\json_ansi_210821.txt",
            audioOutputFileDir = "C:\\download\\wonderful"
        )
    }

    private fun extractAudioInfo(rssJsonRootInfo: WonderfulRadioRssJsonRootInfo): List<WonderfulRadioAudioItemInfo> {

        val audioInfoList = ArrayList<WonderfulRadioAudioItemInfo>()

        for ((index, item) in rssJsonRootInfo.rss.channel.item.withIndex()) {

            val audioInfo = radioAudioInfoExtractor.extractAudioInfo(item)
            if (audioInfo == null) {
                continue
            }
            audioInfoList.add(audioInfo)
        }
        return audioInfoList
    }

    private fun extractTargetDownload(audioItemList: List<WonderfulRadioAudioItemInfo> , param: WonderfulRadioParameter):
            List<WonderfulRadioAudioItemInfo> {

        val downloadList = ArrayList<WonderfulRadioAudioItemInfo>()

        for ((index, item) in audioItemList.withIndex()) {

//            println("item: $item")
//            println("${item.broadcastDay.toInt()}, ${param.fromDay.toInt()}")

            if(item.broadcastDay.toInt() >= param.fromDay.toInt()) {
                downloadList.add(item)
            }
        }

        return downloadList
    }

    private fun downloadRadioAudio(
        targetDownloadList: List<WonderfulRadioAudioItemInfo>,
        param: WonderfulRadioParameter
    ) {

        for ((index, item) in targetDownloadList.withIndex()) {
            println(item.audioTitle)
            radioAudioDownloader.download(item, param)
        }
    }
}