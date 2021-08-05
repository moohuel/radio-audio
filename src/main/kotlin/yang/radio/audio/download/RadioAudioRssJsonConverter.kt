package yang.radio.audio.download

import com.google.gson.Gson
import org.springframework.stereotype.Service
import yang.radio.audio.download.entity.WonderfulRadioRssJsonRootInfo
import java.io.BufferedReader
import java.io.FileReader

@Service
class RadioAudioRssJsonConverter {

    fun jsonParser(): WonderfulRadioRssJsonRootInfo {

        var bufferedReader = BufferedReader(FileReader("C:\\download\\json_ansi.txt"))
        var body = bufferedReader.use { it.readText() }
        body = body.replace("-url", "url")
        body = body.replace("-type", "type")
        body = body.replace("-length", "length")

        //println(body)

        val parsedObj = Gson().fromJson(body, WonderfulRadioRssJsonRootInfo::class.java)
        var itemList = parsedObj.rss.channel.item

        return parsedObj
    }
}