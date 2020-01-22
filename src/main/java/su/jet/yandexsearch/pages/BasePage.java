package su.jet.yandexsearch.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;

/**
 * Abstract page, initialize selenide elements in constructor.
 *
 * @author Pavel_Markin
 */

@Slf4j
@SuppressWarnings("unchecked")
abstract public class BasePage {

    protected String url;

    protected String name;

    public BasePage(String url, String name) {
        this.url = url;
        this.name = name;
        Selenide.page(this);
    }

    @Step("Открыть страницу {this.name} по {this.url}")
    public <T extends BasePage> T open() {
        log.info("Open page {}", this.url);
        Selenide.open(url);
        return (T) this;
    }

}
