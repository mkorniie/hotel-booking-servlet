package ua.mkorniie.controller.util;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import ua.mkorniie.model.enums.Language;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {
    @Getter @Setter private static Language currentLanguage = Language.ua;
    private static Locale engLocale = new Locale("en", "US");
    private static Locale uaLocale = new Locale("ua", "UA");

    public static ResourceBundle getCurrentBundle() {
        ResourceBundle resourceBundle = null;
        if (currentLanguage == Language.en) {
            resourceBundle = ResourceBundle.getBundle("MessagesBundle_en_US", engLocale);
        } else if (currentLanguage == Language.ua) {
            resourceBundle = ResourceBundle.getBundle("MessagesBundle_ua_UA", uaLocale);
        }

        return resourceBundle;
    }

    public static String getString(@NotNull String tag) {
        return getStringByLang(tag, currentLanguage);
    }

    public static String getUaString(@NotNull String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("MessagesBundle_ua_UA", uaLocale);
        return new String(resourceBundle.getString(key).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }

    public static String getEnString(@NotNull String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("MessagesBundle_en_US", engLocale);
        return resourceBundle.getString(key);
    }

    /**
     * Returns localized String by key and language from bundle.
     * @param key
     * @param language
     * @return null if language is not supported by method
     */
    public static String getStringByLang(@NotNull String key, @NotNull Language language) {
        if (language == Language.en) {
            return getEnString(key);
        } else if (language == Language.ua) {
            return getUaString(key);
        }
        return null;
    }

    public static void changeLocale(@NotNull HttpServletRequest request) {
        try {
            String param = request.getParameter("lang");
            if (param != null) {
                Language language = Language.valueOf(param);
                Localization.setCurrentLanguage(language);
            }
        } catch (Exception e) {
            System.out.println("Error: impossible to get language parameter and set locale");
        }
    }

    private Localization() {}
}