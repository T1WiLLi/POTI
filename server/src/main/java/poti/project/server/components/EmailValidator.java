package poti.project.server.components;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final String CEGEP_DOMAIN = "@cegepst.qc.ca";
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^\\d{7}@cegepst\\.qc\\.ca$");

    public boolean isValid(String email) {
        if (email == null) {
            return false;
        }

        return EMAIL_PATTERN.matcher(email).matches() && isCegepEmail(email);
    }

    private boolean isCegepEmail(String email) {
        return CEGEP_DOMAIN.equals(extractDomain(email));
    }

    private String extractDomain(String email) {
        if (email == null || !email.contains("@")) {
            return null;
        }
        return email.substring(email.indexOf("@"));
    }
}
