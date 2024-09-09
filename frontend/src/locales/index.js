import i18n from "i18next";
import { useTranslation, initReactI18next } from "react-i18next";
import en from "./translations/en.json"
import tr from "./translations/tr.json"

const initialLanguage = localStorage.getItem('lang') || navigator.language || 'en'

export const i18nInstance = i18n
  .use(initReactI18next) // passes i18n down to react-i18next


i18nInstance.init({
  // the translations
  // (tip move them in a JSON file and import them,
  // or even better, manage them via a UI: https://react.i18next.com/guides/multiple-translation-files#manage-your-translations-with-a-management-gui)
  resources: {
    en: {
      translation: en
    },
    tr: {
      translation: tr
    }
  },
  fallbackLng: initialLanguage,

  interpolation: {
    escapeValue: false // react already safes from xss => https://www.i18next.com/translation-function/interpolation#unescape
  }
});