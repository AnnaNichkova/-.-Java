package lessons;

import org.apache.commons.cli.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.TestNG;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Init {
    public Properties props = new Properties();
    private static WebDriver driver;

    public static void main(String[] args) throws IOException, ParseException, org.apache.commons.cli.ParseException {
        CommandLine line = parseArgs(args);
        System.setProperty("browsername", line.getOptionValue("browsername"));
        if (line.getOptionValue("browsername").equals("Chrome") | line.getOptionValue("browsername").equals("Firefox")) {
            System.setProperty("headless", "false");
        }
        Init init = new Init();
        init.startDriver();
        Tests tests = new Tests();
        //tests.test1();
//        switch (System.getProperty("testname")) {
//            case "test1":
//                tests.test1();
//            case "test2":
//                tests.test2();
//            default:
//                tests.test1();
//        }
        setImplicitTimeouts();
        //runTests(line);
    }


    private void startDriver() throws IOException {
        props.load(new FileInputStream("src/main/resources/configuration"));
        startChromeDriver();
    }

    public Init() throws IOException {
        props.load(new FileInputStream("src/main/resources/configuration/app.properties"));
        switch (System.getProperty("browsername")) {
            case "Chrome":
                startChromeDriver();
            case "Firefox":
                startFirefoxDriver();
            case "IE11":
                startIEDriver();
            default:
                startChromeDriver();
        }

    }

    private void startChromeDriver() {
        System.setProperty("webdriver.chrome.driver", props.getProperty("webdriver.chrome.driver"));
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.default_content_settings_values.notifications", 2);
        prefs.put("multiple-automatic-downloads", 1);
        prefs.put("profile.content_settings.exceptions.automatic_downloads.*.settings", 1);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.default_directory", props.getProperty("download.default_directory"));

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        if (props.getProperty("window.size").equals("1")) {
            options.addArguments("--start-maximized");
        }
        if (props.getProperty("scale").equals("0.7")) {
            options.addArguments("--high-dpi-support=0.3");
            options.addArguments("--force-device-scale-factor=0.5");
        }
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.navigate().to("https://geekbrains.ru/");
    }

    private void startFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/geckodriver.exe");

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile myAutomatizationProjectProfile = profile.getProfile("HomeWork");
        FirefoxOptions options = new FirefoxOptions();
        myAutomatizationProjectProfile.setPreference("browser.download.dir", "D:\\firefox_download");
        options.setProfile(myAutomatizationProjectProfile);
        driver = new FirefoxDriver(options);
        driver.navigate().to("https://www.cryptopro.ru/sites/default/files/products/cades/demopage/cades_bes_sample.html");
    }

    private void startIEDriver() {
        System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/IEDriverServer.exe");
        driver = new InternetExplorerDriver();
    }

    private static CommandLine parseArgs (String[] args) throws ParseException, org.apache.commons.cli.ParseException {
        Options options = new Options();
        Option browserName = Option.builder("browsername")
                .longOpt("browsername")
                .required(false)
                .hasArg()
                .desc("browser name")
                .build();

        Option testName = Option.builder("testname")
                .longOpt("testname")
                .required(false)
                .hasArg()
                .desc("test name")
                .build();
        options.addOption(browserName);
        options.addOption(testName);
        CommandLineParser parser = new DefaultParser();
        System.out.println(args);
        return parser.parse(options, args);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    private static void setImplicitTimeouts() {
        //Неявные ожидания - Implicit Waits - конфигурируют экземпляр WebDriver делать
        // многократные попытки найти элемент (элементы) на странице в течении
        // заданного периода времени, если элемент не найден сразу.
        // Tолько по истечении этого времени WebDriver бросит ElementNotFoundException.
        driver.manage().timeouts().implicitlyWait(Waits.big_wait, TimeUnit.SECONDS);
        //неявное ожидание загрузки страницы
        driver.manage().timeouts().pageLoadTimeout(Waits.big_wait, TimeUnit.SECONDS);
        //неявное ожидание отработки скриптов
        driver.manage().timeouts().setScriptTimeout(Waits.big_wait, TimeUnit.SECONDS);
    }

    private static void runTests(CommandLine line) throws Exception {
        TestNG testNG = new TestNG();
        testNG.setTestClasses(new Class[]{Tests.class});
        testNG.run();
    }
}
