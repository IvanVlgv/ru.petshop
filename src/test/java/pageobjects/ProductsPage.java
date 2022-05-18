package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductsPage {

    private final SelenideElement sortListTop = $x("//div[@class='goods-nav top']//div[@id='sort-by-styler']");
    private final SelenideElement ascendingOrder = $x("//div[@class='goods-nav top']//div[@id='sort-by-styler']//li[contains(text(),'Цена по возрастанию')]");
    private final SelenideElement sortListBottom = $x("//div[@class='goods-nav bottom']//div[@id='sort-by-styler']");
    private final SelenideElement amountOfProducts = $x("//div[@class=\"goods-nav top\"]/*[@data-testid=\"pagenation\"]");
    private final ElementsCollection products = $$x("//*[@data-testid=\"product__item\"]");
    private final SelenideElement closeBtnCoockie = $x("//div[contains(@class,'CookieInformer')]//button");


    public ProductsPage closeCoockie() {
        closeBtnCoockie
                .shouldBe(Condition.visible, Duration.ofMillis(10000))
                .scrollIntoView(true)
                .click();
        return this;
    }

     public ProductsPage openSortListTop() {
        sortListTop
                .shouldBe(Condition.visible, Duration.ofMillis(10000))
                .click();
        return this;
    }

    public void selectAscendingOrder() {
        ascendingOrder
                .shouldBe(Condition.visible, Duration.ofMillis(10000))
                .click();
    }

    public void scrollIntoBottom() {
        sortListBottom
                .shouldBe(Condition.visible, Duration.ofMillis(10000))
                .scrollIntoView(true);
    }

    public int getAmountOfProducts() {

        return Integer.parseInt(amountOfProducts
                .shouldBe(Condition.visible, Duration.ofMillis(10000))
                .getText().split(" ")[0]);
    }

    public String getTextOfPrice(int index) {
        return products
                .get(index)
                .$$x(".//*[@class=\"price-optimal\"]/span")
                .last()
                .getText();
    }
}
