package su.jet.yandexsearch.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import su.jet.yandexsearch.Tabs;
import su.jet.yandexsearch.utils.PropertyHelper;

import java.util.NoSuchElementException;

/**
 * YandexMainPage.
 *
 * @author Pavel_Markin
 */
public class YandexMainPage extends YandexBasePage {

    private static final String URL = PropertyHelper.getProperty("host");

    @FindBy(xpath = "//div[contains(@class,'home-tabs')]//a[contains(@class,'home-link')]")
    private ElementsCollection tabs;

    public YandexMainPage() {
        super("Главная страница", URL);
    }

    @Step("Переходим на вкладку {tab.title}")
    public void openTab(Tabs tab) {
        tabs.stream()
            .filter(link -> tab.getTitle().equals(link.innerText()))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException(String.format("No tab %s found", tab.getTitle())))
            .click();
    }

}
