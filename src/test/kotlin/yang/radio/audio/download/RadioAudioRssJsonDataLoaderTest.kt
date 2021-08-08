package yang.radio.audio.download

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import yang.radio.audio.download.entity.WonderfulRadioParameter

//@RunWith(SpringRunner::class)
//@SpringBootTest(classes = [RadioApplication::class])
@SpringBootTest(classes = [RadioAudioRssJsonDataLoader::class])
class RadioAudioRssJsonDataLoaderTest {

    @Autowired
    lateinit var radioAudioRssJsonDataLoader: RadioAudioRssJsonDataLoader

    @Test
    fun convertTest() {

        var param = WonderfulRadioParameter(
            firstBroadcastDay = "20210531",
            fromDay = "20210804",
            jsonFilePath = "C:\\download\\json_ansi.txt",
            audioOutputFileDir = "C:\\download\\wonderful"
        )

        radioAudioRssJsonDataLoader.audioRootInfoFromJson(param)
    }
}