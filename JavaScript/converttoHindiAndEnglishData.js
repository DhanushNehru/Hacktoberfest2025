//===================================================
// Made by Gurdeep Singh https://github.com/Gurdeep99
//===================================================

function formatDate(dateString, lang = "en") {
    const date = new Date(dateString);
  
    // Define locales
    const locales = {
      en: "en-IN", // English (India)
      hi: "hi-IN"  // Hindi (India)
    };
  
    // Use Intl.DateTimeFormat for localization
    const formatter = new Intl.DateTimeFormat(locales[lang], {
      day: "numeric",
      month: "long",
      year: "numeric"
    });
  
    return formatter.format(date);
  }
  
  // Example usage:
  console.log(formatDate("2025-03-01", "en")); // "1 March 2025"
  console.log(formatDate("2025-03-01", "hi")); // "1 मार्च 2025"
  