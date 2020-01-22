package su.jet.yandexsearch.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;
import su.jet.yandexsearch.utils.PropertyHelper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * YandexMainPage.
 *
 * @author Pavel_Markin
 */
@Getter
public class YandexImagesPage extends YandexBasePage {

    public static final String URL = PropertyHelper.getProperty("host") + "images/";

    public static final List<String> navigationItemsList = Arrays.asList("Поиск", "Картинки","Видео", "Карты", "Маркет", "Новости", "Переводчик", "Эфир", "Коллекции", "Кью", "Услуги", "Ещё");

    @FindBy(xpath = "//div[@class='navigation__region']/div[contains(@class,'navigation__item')and not(contains(@class,'hidden'))]")
    protected ElementsCollection navigationItems;

    @FindBy(xpath = "//div[contains(@class,'teaser_card')]")
    protected ElementsCollection images;

    public YandexImagesPage() {
        super("Картинки", URL);
    }

    @Step("Проверяем список элементов навигационного меню")
    public void checkNavigationItems() {
        List<String> values = navigationItems.stream().map(SelenideElement::innerText).collect(Collectors.toList());
        assertTrue(values.containsAll(navigationItemsList), "Список навигационного меню верен");
        navigationItems.stream().forEach(navigationItem-> navigationItem.shouldBe(Condition.visible));
    }

    @Step("Проверяем наличие картинок на форме")
    public void checkImages() {
        assertTrue(!images.isEmpty(), "Картинки присутствуют на форме");
    }

}
