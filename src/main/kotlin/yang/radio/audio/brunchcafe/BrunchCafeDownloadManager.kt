package yang.radio.audio.brunchcafe

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class BrunchCafeDownloadManager {

    @Autowired
    lateinit var brunchCafeAudioDownloader: BrunchCafeAudioDownloader

    fun audioDownload(fromDate: String, toDate: String) {

        var targetDateList = makeTargetDateList(fromDate, toDate)
        var targetAudioUrlList = makeTargetAudioUrlList(targetDateList)
        var titleList = makeTitleList(targetDateList)
        var parameter = makeParameter(targetDateList, targetAudioUrlList, titleList)
        doDownload(parameter)
    }

    private fun doDownload(parameter: BrunchCafeDownloadParameter) {

        var urlList = parameter.mp3UrlList
        for((index, url) in urlList.withIndex()) {

            var outputAudioTitle = parameter.outputAudioTitleList.get(index)
            println("${outputAudioTitle} downloading..")
            brunchCafeAudioDownloader.download(url, outputAudioTitle, parameter.outputDir)
        }
    }

    private fun makeTargetDateList(fromDate: String, toDate: String): List<LocalDateTime> {

        var fromTime = LocalDateTime.of(fromDate.substring(0, 4).toInt(),
            fromDate.substring(4, 6).toInt(), fromDate.substring(6).toInt(), 0, 0)

        var toTime = LocalDateTime.of(toDate.substring(0, 4).toInt(),
            toDate.substring(4, 6).toInt(), toDate.substring(6).toInt(), 0, 0)

        var dateList = ArrayList<LocalDateTime>()
        while(fromTime.isBefore(toTime) || fromTime.isEqual(toTime)) {

            //dateList.add(fromTime.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
            //println(fromDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")))
            dateList.add(fromTime)
            fromTime = fromTime.plusDays(1)
        }

        return dateList
    }

    private fun makeTargetAudioUrlList(targetDateList: List<LocalDateTime>): List<String> {

        var urlList = ArrayList<String>()
        for(targetDate in targetDateList) {

            var yyyyMMdd = targetDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            urlList.add("http://podcastfile.imbc.com/cgi-bin/podcast.fcgi/podcast/brunchcafe/BRUNCHCAFE_${yyyyMMdd}.mp3")
        }

        return urlList
    }

    private fun makeTitleList(targetDateList: List<LocalDateTime>): List<String> {

        var titleList = ArrayList<String>()
        for(targetDate in targetDateList) {

            var weekName = getWeekName(targetDate)
            var yyyyMMdd = targetDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            titleList.add("leeseokhoon_brunchcafe_${yyyyMMdd}_${weekName}.mp3")
        }

        return titleList
    }

    private fun getWeekName(targetDate: LocalDateTime): String {

        var weekName = ""
        when (targetDate.dayOfWeek.value) {

            1 -> weekName = "월"
            2 -> weekName = "화"
            3 -> weekName = "수"
            4 -> weekName = "목"
            5 -> weekName = "금"
            6 -> weekName = "토"
            7 -> weekName = "일"
        }

        return weekName
    }

    private fun makeParameter(
        targetDateList: List<LocalDateTime>,
        targetAudioUrlList: List<String>,
        titleList: List<String>
    ): BrunchCafeDownloadParameter {

        var param = BrunchCafeDownloadParameter(
            mp3UrlList = targetAudioUrlList,
            outputAudioTitleList = titleList,
            outputDir = "C:\\download\\brunchcafe"
        )

        return param
    }



}