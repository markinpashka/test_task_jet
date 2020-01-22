import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import su.jet.yandexsearch.Tabs;
import su.jet.yandexsearch.pages.YandexImagesPage;
import su.jet.yandexsearch.pages.YandexMainPage;
import su.jet.yandexsearch.utils.Common;

import java.util.NoSuchElementException;

/**
 * YandexTest.
 *
 * @author Pavel_Markin
 */
public class YandexTest extends BaseUiTest {

    @ParameterizedTest(name = "Get first value in fast search results by search: {0}")
    @ValueSource(strings = {"погода", "Липецк", "Лото"})
    public void search(String searchRequest) {
        YandexMainPage yandexMainPage = new YandexMainPage().open();
        yandexMainPage.search(searchRequest);
        String firstSearchResult = yandexMainPage.getSearchResults()
                                                 .stream()
                                                 .findFirst()
                                                 .orElseThrow(() -> new NoSuchElementException("There are no results in fast search"));
        Common.addStep(String.format("First found value in fast search : %s", firstSearchResult));

    }

    @Test
    @DisplayName("Check images tab")
    public void checkImages() {
        YandexMainPage yandexMainPage = new YandexMainPage().open();
        yandexMainPage.openTab(Tabs.IMAGES);

        YandexImagesPage yandexImagesPage = new YandexImagesPage();
        yandexImagesPage.getSearchInput().shouldBe(Condition.visible);
        yandexImagesPage.checkNavigationItems();
        yandexImagesPage.checkImages();
    }

}
