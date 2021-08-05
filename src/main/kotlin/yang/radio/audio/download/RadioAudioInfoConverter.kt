package yang.radio.audio.download

import org.springframework.stereotype.Service
import yang.radio.audio.download.entity.WonderfulRadioAudioInfo
import yang.radio.audio.download.entity.WonderfulRadioItemInfo

@Service
class RadioAudioInfoConverter {

    fun rebuildAudioInfo(item: WonderfulRadioItemInfo): WonderfulRadioAudioInfo? {

        // 8/2(월) 원더풀라디오 이석훈입니다 1,2부/오늘 나의 하루는/원더풀 차차차
        val originalTitle = item.title

        if(originalTitle.indexOf("강수지") > 0) {
            return null
        }

        var timeTitle = originalTitle.substring(0, originalTitle.indexOf("("))

        var year = "2021"
        var monthDay = makeMonthDay(timeTitle)
        var weekName = originalTitle.substring(originalTitle.indexOf("(")+1, originalTitle.indexOf(")"))

        var divide = "0"
        if(originalTitle.indexOf("1,2부") > 0 || originalTitle.indexOf("1, 2부") > 0) {
            divide = "1"
        }
        if(originalTitle.indexOf("3,4부") > 0 || originalTitle.indexOf("3, 4부") > 0) {
            divide = "2"
        }

        var audioTitle = "wonderfulradio_leeseokhun_${year}${monthDay}_${divide}_${weekName}.mp3"

        return WonderfulRadioAudioInfo(
            audioTitle = audioTitle,
            publicDay = "",
            url = item.enclosure.url,
            length = item.enclosure.length,
            type = item.enclosure.type
        )
    }

    private fun makeMonthDay(timeTitle: String): String {

        var month = timeTitle.substring(0, timeTitle.indexOf("/"))
        var day = timeTitle.substring(timeTitle.indexOf("/")+1)

        if(month.length == 1) {
            month = "0"+month
        }
        if(day.length == 1) {
            day = "0"+day
        }

        return month + day
    }
}