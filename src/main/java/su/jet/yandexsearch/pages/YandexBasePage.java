package su.jet.yandexsearch.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * YandexBasePage - yandex base page with common elements (Elements is preset on MainPage, ImagesPage).
 *
 * @author Pavel_Markin
 */
@Getter
abstract class YandexBasePage extends BasePage {

    public YandexBasePage(String url, String name) {
        super(name, url);
    }

    @FindBy(xpath = "//li[contains(@class,'suggest2-item')]")
    protected ElementsCollection searchResults;

    @FindBy(name = "text")
    protected SelenideElement searchInput;

    @FindBy(xpath = "//span[contains(@class,'input__clear')]")
    protected SelenideElement clearSearchInput;

    @Step("Ввести \"{request}\" в строку поиска")
    public void search(String request) {
        if (!searchInput.text().isEmpty()) {
            clearSearchInput.click();
        }
        searchInput.sendKeys(request);
    }

    @Step("Получить список результатов быстрого поиска")
    public List<String> getSearchResults() {
        searchResults.shouldBe(CollectionCondition.sizeGreaterThan(0));
        return searchResults.stream().map(SelenideElement::innerText).collect(Collectors.toList());
    }
}
