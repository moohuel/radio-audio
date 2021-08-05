package yang.radio.audio.download

import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import yang.radio.audio.env.RadioApplication

//@RunWith(SpringRunner::class)
//@SpringBootTest(classes = [RadioApplication::class])
@SpringBootTest(classes = [RadioAudioRssJsonConverter::class])
class RadioAudioRssJsonConverterTest {

    @Autowired
    lateinit var radioAudioRssJsonConverter: RadioAudioRssJsonConverter

    @Test
    fun convertTest() {
        radioAudioRssJsonConverter.jsonParser()
    }
}