package yang.radio.audio.download

import org.springframework.stereotype.Service
import yang.radio.audio.download.entity.WonderfulRadioAudioItemInfo
import yang.radio.audio.download.entity.WonderfulRadioParameter
import java.io.*
import java.net.URL

@Service
class RadioAudioDownloader {

    fun download(audioInfo: WonderfulRadioAudioItemInfo, param: WonderfulRadioParameter) {

        var url = URL(audioInfo.url)
        var connection = url.openConnection()
        connection.connect()
        var inputStream = url.openStream()

        var output = buildOutputStream(audioInfo.audioTitle, param.audioOutputFileDir)
        var byteArray = ByteArray(100)
        var count = 0
        var accumedLength = 0

        while(true) {

            count = inputStream.read(byteArray)
            accumedLength += count

            if(count <= 0) {
                break
            }

            output.write(byteArray, 0, count)
        }

        output.close()
        inputStream.close()

//        if(accumedLength != audioInfo.length) {
//            println("NOT MATCH LENGTH: ${audioInfo.audioTitle}, ${audioInfo.length}, $accumedLength")
//        }
    }

    private fun buildOutputStream(fileName: String, audioOutputFileDir: String): OutputStream {
        var fileOutputStream = FileOutputStream(File("${audioOutputFileDir}\\$fileName"))
        return fileOutputStream
    }
}