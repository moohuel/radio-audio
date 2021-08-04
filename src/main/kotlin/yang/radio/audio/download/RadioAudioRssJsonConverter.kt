package yang.radio.audio.download

import com.google.gson.Gson
import org.springframework.boot.json.GsonJsonParser
import org.springframework.stereotype.Service
import yang.radio.audio.download.entity.WonderfulRadioRssJsonRootInfo
import java.io.*

@Service
class RadioAudioRssJsonConverter {

    fun jsonParser() {

        var bufferedReader = BufferedReader(FileReader("C:\\download\\json_1.txt"))
        var body = bufferedReader.use { it.readText() }
        body = body.replace("-url", "url")
        body = body.replace("-type", "type")
        body = body.replace("-length", "length")

        val parsedObj = Gson().fromJson(body, WonderfulRadioRssJsonRootInfo::class.java)
        println("parsedObj: $parsedObj")

//        val parsedMap = GsonJsonParser().parseMap(body)
//        println("parsedMap: $parsedMap")
    }
}