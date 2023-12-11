import data.Language;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
public class YotubeTest {

    static Stream<Arguments> youtubeSiteShouldDisplayCorrectButtons() {
        return Stream.of(
                Arguments.of(
                        Language.RU,
                        List.of("Видеоигры", "Джемы", "Музыка", "Ситкомы")
                )
        );
    }
    @MethodSource
    @ParameterizedTest
    void youtubeSiteShouldDisplayCorrectButtons(Language language, List<String> expectedButtons) {
        open("https://www.youtube.com/");
        $$("#chips").find(text(language.name())).click();
        $$(".main-menu-pages a").filter(visible)
                .shouldHave(texts(expectedButtons));
    }
}

