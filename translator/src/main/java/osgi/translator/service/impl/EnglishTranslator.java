package osgi.translator.service.impl;

import osgi.translator.service.TranslatorService;

public class EnglishTranslator
    implements TranslatorService
{
    @Override
    public String translate( final String phrase )
    {
        return "Howdy!";
    }
}
