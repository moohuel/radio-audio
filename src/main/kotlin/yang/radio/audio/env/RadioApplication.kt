package yang.radio.audio.env

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import yang.radio.audio.brunchcafe.BrunchCafeDownloadManager
import yang.radio.audio.download.RadioAudioDownloadManager
import yang.radio.audio.filerename.FileRenameManager

@SpringBootApplication
@ComponentScan("yang.radio.audio")
class RadioApplication: CommandLineRunner {

    @Autowired
    lateinit var radioAudioDownloadManager: RadioAudioDownloadManager
    @Autowired
    lateinit var brunchCafeDownloadManager: BrunchCafeDownloadManager
    @Autowired
    lateinit var fileRenameManager: FileRenameManager

    override fun run(vararg args: String?) {
        brunchCafeDownloadManager.audioDownload("20220328", "20220401")
    }
}

fun main(args: Array<String>) {
    runApplication<RadioApplication>(*args)
}