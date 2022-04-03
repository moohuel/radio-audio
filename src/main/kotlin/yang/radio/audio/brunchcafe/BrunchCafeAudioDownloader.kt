package yang.radio.audio.brunchcafe

import org.springframework.stereotype.Service
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.net.URL

@Service
class BrunchCafeAudioDownloader {

    fun download(url: String, outputAudioTitle: String, outputDir: String) {

        var url = URL(url)
        var connection = url.openConnection()
        connection.connect()
        var inputStream = url.openStream()

        var output = buildOutputStream(outputAudioTitle, outputDir)
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