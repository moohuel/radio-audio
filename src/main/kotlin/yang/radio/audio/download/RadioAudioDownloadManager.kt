package yang.radio.audio.download

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import yang.radio.audio.download.entity.WonderfulRadioAudioItemInfo
import yang.radio.audio.download.entity.WonderfulRadioParameter
import yang.radio.audio.download.entity.WonderfulRadioRssJsonRootInfo

@Service
class RadioAudioDownloadManager {

    @Autowired
    lateinit var radioAudioDownloader: RadioAudioDownloader
    @Autowired
    lateinit var radioAudioRssJsonDataLoader: RadioAudioRssJsonDataLoader
    @Autowired
    lateinit var radioAudioInfoReformer: RadioAudioInfoReformer

    fun wonderfulRadio() {

        val param = makeParameter()

        val rssJsonRootInfo = radioAudioRssJsonDataLoader.audioRootInfoFromJson(param)
        val reformedList = reformAudioInfo(rssJsonRootInfo)
        val targetDownloadList = extractTargetDownload(reformedList, param)

        downloadRadioAudio(targetDownloadList, param)
    }

    private fun makeParameter(): WonderfulRadioParameter {

        return WonderfulRadioParameter(
            firstBroadcastDay = "20210531",
            fromDay = "20210804",
            jsonFilePath = "C:\\download\\wonderful_json\\json_ansi_210808.txt",
            audioOutputFileDir = "C:\\download\\wonderful"
        )
    }

    private fun reformAudioInfo(rssJsonRootInfo: WonderfulRadioRssJsonRootInfo): List<WonderfulRadioAudioItemInfo> {

        val downloadList = ArrayList<WonderfulRadioAudioItemInfo>()

        for ((index, item) in rssJsonRootInfo.rss.channel.item.withIndex()) {

            val audioInfo = radioAudioInfoReformer.reformAudioInfo(item)
            if (audioInfo == null) {
                continue
            }
            downloadList.add(audioInfo)
        }
        return downloadList
    }

    private fun extractTargetDownload(audioItemList: List<WonderfulRadioAudioItemInfo> , param: WonderfulRadioParameter):
            List<WonderfulRadioAudioItemInfo> {

        val downloadList = ArrayList<WonderfulRadioAudioItemInfo>()

        for ((index, item) in audioItemList.withIndex()) {

            //println("${item.broadcastDay.toInt()}, ${param.fromDay.toInt()}")

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