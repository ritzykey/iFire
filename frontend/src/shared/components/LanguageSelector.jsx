import { useTranslation } from "react-i18next";





const LanguageSelector = () => {

    const { i18n } = useTranslation();

    const onSelectLanguage = (language) => {
        i18n.changeLanguage(language)
        localStorage.setItem('lang', language)
    }

    return (
        <div className="d-flex justify-content-end me-4">
            <img
                className="me-2"
                role="button"
                src="https://flagcdn.com/24x18/tr.png"
                width="24"
                height="18"
                alt="Türkçe"
                onClick={() => onSelectLanguage('tr')} />
            <img
                role="button"
                src="https://flagcdn.com/24x18/us.png"
                width="24"
                height="18"
                alt="English"
                onClick={() => onSelectLanguage('en')} />
        </div>
    );
}

export default LanguageSelector;