package yang.radio.audio.download

import org.springframework.stereotype.Service
import yang.radio.audio.download.entity.WonderfulRadioAudioInfo
import java.io.*
import java.net.URL

@Service
class RadioAudioDownloader {

    fun download(audioInfo: WonderfulRadioAudioInfo) {

        //var url = URL("https://rss.art19.com/episodes/4cad71a1-de84-40e2-98cd-7431e2743acc.mp3?rss_browser=BAhJIgtDaHJvbWUGOgZFVA%3D%3D--d05363d83ce333c74f32188013892b2863ad051c")
        var url = URL(audioInfo.url)
        var connection = url.openConnection()
        connection.connect()
        var inputStream = url.openStream()

        var output = buildOutputStream(audioInfo.audioTitle)
        var byteArray = ByteArray(100)
        var count = 0

        while(true) {

            count = inputStream.read(byteArray)

            if(count <= 0) {
                break
            }

            output.write(byteArray, 0, count)
        }

        output.close()
        inputStream.close()
    }

    private fun buildOutputStream(fileName: String): OutputStream {
        var fileOutputStream = FileOutputStream(File("C:\\download\\$fileName"))
        return fileOutputStream
    }
}