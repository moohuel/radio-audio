package yang.radio.audio.env

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import yang.radio.audio.download.RadioAudioDownloadManager

@SpringBootApplication
@ComponentScan("yang.radio.audio")
class RadioApplication: CommandLineRunner {

    @Autowired
    lateinit var radioAudioDownloadManager: RadioAudioDownloadManager

    override fun run(vararg args: String?) {
        radioAudioDownloadManager.wonderfulRadio()
    }
}

fun main(args: Array<String>) {
    runApplication<RadioApplication>(*args)
}