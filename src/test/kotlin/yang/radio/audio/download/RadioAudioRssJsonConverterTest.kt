package yang.radio.audio.download

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import yang.radio.audio.env.RadioApplication

@SpringBootTest(classes = [RadioApplication::class])
class RadioAudioRssJsonConverterTest {

    @Autowired
    lateinit var radioAudioRssJsonConverter: RadioAudioRssJsonConverter

    @Test
    fun convertTest() {
        radioAudioRssJsonConverter.jsonParser()
    }
}