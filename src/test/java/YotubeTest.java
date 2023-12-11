import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import data.Language;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class YotubeTest {

    @ValueSource(strings = {"Miyagi - Captain (Official Audio)"})
    @ParameterizedTest(name = "Для поиского запроса {0} должен отдавать не пустой запрос")
    @DisplayName("Тест-кейс")
    @Tag("BLOCKER")
    public void checkTestCaseForWorkTest(String searchQuery){
        open("https://www.youtube.com/");
        $("#search-form #search").setValue(searchQuery).pressEnter();
        $$("ytd-video-renderer .ytd-video-renderer")
              .shouldHave(itemWithText(searchQuery));
    }
    @CsvSource(value = {"frog| 2022 (p)&(c) Mach1 Records GmbH & Co KG All music tracks are produced by Reinhard Raith, Henning Reith and Wolfgang ..."},
    delimiter='|')
    @ParameterizedTest(name = "Для поиского запроса {0} должен отдавать не пустой запрос")
    @DisplayName("Тест-кейс")
    public void searchYouTube(String searchFix, String searchText){
        Configuration.holdBrowserOpen = true;
        open("https://www.youtube.com/");
        $("#search-form #search").setValue(searchFix).pressEnter();
        $("ytd-video-renderer .ytd-video-renderer").shouldHave(text(searchText));
    }




    @EnumSource(Language.class)
    @ParameterizedTest
    void selenideSiteShouldDisplayCorrectText(Language language) {
        open("https://ru.selenide.org/");
        $$("#languages a").find(text(language.name())).click();
        $("h3").shouldHave(text(language.description));
    }
}

