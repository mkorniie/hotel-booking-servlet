package util_test;

import org.junit.Test;
import ua.mkorniie.model.enums.Language;
import ua.mkorniie.model.util.Localization;


//TODO: finish tests for util package
public class LocaleTest {
    @Test
    void getTexts() {
        assert (Localization.getUaString("greeting").equals("Привіт."));
        assert (Localization.getEnString("greeting").equals("Hello."));

        assert (Localization.getStringByLang("greeting", Language.ua).equals("Привіт."));
        assert (Localization.getStringByLang("greeting", Language.en).equals("Hello."));
    }
}