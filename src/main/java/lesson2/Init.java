package lesson2;

import org.apache.commons.cli.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Init {
    Properties props = new Properties();
    private static WebDriver driver;

    public static void main(String[] args) throws IOException, ParseException, org.apache.commons.cli.ParseException {
        CommandLine line = parseArgs(args);
        Init init = new Init();
    }

    private Init() throws IOException {
        props.load(new FileInputStream("src/main/resources/configuration/app.properties"));
        startChromeDriver();
        startIEDriver();
        startFirefoxDriver();
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
        Map<String, Object> prefsFirefox = new HashMap<>();
        prefsFirefox.put("profile.default_content_settings.popups", 0);
        prefsFirefox.put("download.default_directory_firefox", props.getProperty("download.default_directory_firefox"));

        ProfilesIni profile = new ProfilesIni();
        FirefoxProfile myAutomatizationProjectProfile = profile.getProfile("selenium");
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(myAutomatizationProjectProfile);
        driver = new FirefoxDriver(options);
    }

    private void startIEDriver() {
        System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/IEDriverServer.exe");
        driver = new InternetExplorerDriver();
    }

    private static org.apache.commons.cli.CommandLine parseArgs (String[] args) throws ParseException, org.apache.commons.cli.ParseException {
        org.apache.commons.cli.Options options = new Options();
        Option driver = org.apache.commons.cli.Option.builder("headless")
                .longOpt("headless")
                .required(true)
                .hasArg()
                .desc("invisible mode")
                .build();
        options.addOption(driver);
        CommandLineParser parser = new DefaultParser();
        System.out.println(args);
        return parser.parse(options, args);
    }
}
