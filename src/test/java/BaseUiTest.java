import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import su.jet.yandexsearch.utils.PropertyHelper;

import java.util.Arrays;
import java.util.List;

/**
 * BaseUiTest - init selenide config before tests.
 *
 * @author Pavel_Markin
 */
public class BaseUiTest {

    private static final List<String> supportedBrowsers = Arrays.asList("chrome", "firefox");

    @BeforeAll
    public static void init() {
        Configuration.timeout = 6000;
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        System.setProperty("webdriver.firefox.driver", "src/test/resources/driver/geckodriver.exe");
        String browserType = PropertyHelper.getProperty("browser");
        if (!supportedBrowsers.contains(browserType))
            throw new RuntimeException("Check browser type. Supported types: \"chrome\", \"firefox\"");
        Configuration.browser = browserType;
    }
}
