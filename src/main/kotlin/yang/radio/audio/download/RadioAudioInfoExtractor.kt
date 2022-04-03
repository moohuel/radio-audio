package yang.radio.audio.download

import org.springframework.stereotype.Service
import yang.radio.audio.download.entity.WonderfulRadioAudioItemInfo
import yang.radio.audio.download.entity.WonderfulRadioRssItemInfo
import java.time.LocalDateTime
import java.util.*

@Service
class RadioAudioInfoExtractor {

    fun extractAudioInfo(rssItem: WonderfulRadioRssItemInfo): WonderfulRadioAudioItemInfo? {

        // 8/2(월) 원더풀라디오 이석훈입니다 1,2부/오늘 나의 하루는/원더풀 차차차
        val originalTitle = rssItem.title

        if(originalTitle.indexOf("강수지") > 0) {
            return null
        }

        var timeTitle = originalTitle.substring(0, originalTitle.indexOf("("))
        var pubDate = rssItem.pubDate

        //@ToDo
        var year = "2021"
        if(pubDate.indexOf("2022") > 0) {
            year = "2022"
        }

        var monthDay = makeMonthDay(timeTitle)
        var weekName = originalTitle.substring(originalTitle.indexOf("(")+1, originalTitle.indexOf(")"))

        var divide = "0"
        if(originalTitle.indexOf("1,2부") > 0 || originalTitle.indexOf("1, 2부") > 0) {
            divide = "1"
        }
        if(originalTitle.indexOf("3,4부") > 0 || originalTitle.indexOf("3, 4부") > 0) {
            divide = "2"
        }

        var audioTitle = "wonderfulradio_leeseokhoon_${year}${monthDay}_${divide}_${weekName}.mp3"

        return WonderfulRadioAudioItemInfo(
            audioTitle = audioTitle,
            broadcastDay = "${year}${monthDay}",
            url = rssItem.enclosure.url,
            length = rssItem.enclosure.length,
            type = rssItem.enclosure.type
        )
    }

    private fun makeMonthDay(timeTitle: String): String {

        try {
            var month = (timeTitle.substring(0, timeTitle.indexOf("/"))).trim()
            var day = (timeTitle.substring(timeTitle.indexOf("/")+1)).trim()

            if(month.length == 1) {
                month = "0"+month
            }
            if(day.length == 1) {
                day = "0"+day
            }

            return month + day
        }
        catch(e: Exception) {
            println("ErrorLine: $timeTitle")
            e.printStackTrace()
            throw e
        }
    }
}