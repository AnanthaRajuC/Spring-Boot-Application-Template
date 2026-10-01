## Internationalization (i18n)

This app can be adapted to various languages and regions without engineering changes. Textual elements, such as status messages and the GUI component labels, are not hardcoded in the program. Instead they are stored outside the source code and retrieved dynamically.

*	The texts are stored in `src/main/resources/i18n`: `messages.properties` (English, the default) and `messages_es.properties` (Spanish).
*	Templates refer to them with Thymeleaf message expressions, for example `th:text="#{login.h1.text}"`.
*	The language is chosen with the language menu in the navigation bar, or by adding `?lang=es` (or `?lang=en`) to any page URL. The choice is kept for the rest of the session.

To add a language, create `messages_<language code>.properties` with the translated texts and add it to the language menu in `templates/fragments/navigation.html`.

Refer to `io.github.anantharajuc.sbat.core_backend.infra.config.I18Nconfiguration`.
