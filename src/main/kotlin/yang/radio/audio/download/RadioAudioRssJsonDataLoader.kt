package yang.radio.audio.download

import com.google.gson.Gson
import org.springframework.stereotype.Service
import yang.radio.audio.download.entity.WonderfulRadioParameter
import yang.radio.audio.download.entity.WonderfulRadioRssJsonRootInfo
import java.io.BufferedReader
import java.io.FileReader

@Service
class RadioAudioRssJsonDataLoader {

    fun audioRootInfoFromJson(param: WonderfulRadioParameter): WonderfulRadioRssJsonRootInfo {

        var bufferedReader = BufferedReader(FileReader(param.jsonFilePath))
        var body = bufferedReader.use { it.readText() }

        body = body.replace("-url", "url")
        body = body.replace("-type", "type")
        body = body.replace("-length", "length")

        val parsedObj = Gson().fromJson(body, WonderfulRadioRssJsonRootInfo::class.java)
        var itemList = parsedObj.rss.channel.item

        return parsedObj
    }
}