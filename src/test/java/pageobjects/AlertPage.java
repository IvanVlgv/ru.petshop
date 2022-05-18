package pageobjects;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class AlertPage {
    private final SelenideElement form = $x("//iframe[@class='flocktory-widget']");
    private final SelenideElement closeBtnAlert =$x("//h2[contains(text(),'Нажмите «Разрешить»')]//parent::main/button");

    public AlertPage checkExistOfFrame() {
        form.shouldBe(Condition.visible, Duration.ofMillis(10000));
        return this;
    }
    public void closeAlert() {
        switchTo().frame(form);
        closeBtnAlert
                .shouldBe(Condition.visible, Duration.ofMillis(10000))
                .click();
        switchTo().parentFrame();
    }
}
