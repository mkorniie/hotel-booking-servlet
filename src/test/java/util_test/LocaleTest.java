package util_test;

import org.junit.Test;
import ua.mkorniie.controller.util.Localization;
import ua.mkorniie.model.enums.Language;


public class LocaleTest {
    @Test
    public void getTexts() {
        assert (Localization.getUaString("greeting").equals("Привіт."));
        assert (Localization.getEnString("greeting").equals("Hello."));

        assert (Localization.getStringByLang("greeting", Language.ua).equals("Привіт."));
        assert (Localization.getStringByLang("greeting", Language.en).equals("Hello."));
    }
}
