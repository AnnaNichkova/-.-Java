package lesson2;

import java.io.IOException;

public class Tests {

    public static void main(String[] args) {

    }

    public void test1 () throws IOException {
        Init init = new Init();
        init.getDriver().navigate().to("https://geekbrains.ru/");

    }

    public void test2 () {

    }
}
