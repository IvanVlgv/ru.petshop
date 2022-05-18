package tests;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pageobjects.AlertPage;
import pageobjects.ProductsPage;

import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.Selenide.open;

@Owner("VolegovIvan")
class SortCheckTest extends TestBase{
    ProductsPage productsPage = new ProductsPage();
    AlertPage alertPage = new AlertPage();

    @ValueSource(strings = {
            "/brand/pedigree/dogs/vitamins",
            "/catalog/dogs/games/gantelya/"
    })
    @ParameterizedTest(name = "Проверка сортировки по возрастанию для страницы {0}.")
    @Tag("sort")
    @Step("Проверка сортировки по возрастанию.")
        void checkSorting(String testData) {
        open(testData);
        productsPage
                .closeCoockie()
                .openSortListTop()
                .selectAscendingOrder();
        open(WebDriverRunner.url());//Костыль, но по другому не получается дождаться отсортированного списка.
        alertPage
                .checkExistOfFrame()
                .closeAlert();
        productsPage.scrollIntoBottom();
        int n =  productsPage.getAmountOfProducts();
        n = Math.min(n, 32);
        ArrayList<Integer> amountOfPrices = new ArrayList<>();
        for (int i = 0; i < n-1; i++) {
            amountOfPrices.add(Integer.parseInt(productsPage
                    .getTextOfPrice(i)
                    .replaceAll("\\s+", "")));
        }
        ArrayList<Integer> notSortedAmountOfPrices = new ArrayList<>(amountOfPrices);
        Collections.sort(amountOfPrices);
        boolean isSorted = amountOfPrices.equals(notSortedAmountOfPrices);
        Assertions.assertTrue(isSorted);
    }
}

