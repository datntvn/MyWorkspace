/**
 * @author DatNT29
 *
 * File       : Greeting.java
 * Created On : 10/09/2018 (dd/MM/YYYY)
 */

package com.util.resources;

import java.util.Locale;

import javax.annotation.PostConstruct;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class Messages {

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        Locale currentLocale = LocaleContextHolder.getLocale();
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        // BEGIN - setting proper language by locale
        // Later on, if we need to support other language, we can clone the below logic
        if (Locale.US.equals(currentLocale)) {
            messageSource.setBasenames("messages_en_us");
        }
        // END - setting proper language by locale

        accessor = new MessageSourceAccessor(messageSource);
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }

}
