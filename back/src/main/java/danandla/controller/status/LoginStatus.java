package danandla.controller.status;

public enum LoginStatus {
    OK("ok"),
    USER_ALREADY_EXISTS("User with such login already exists"),
    TOO_SHORT_PASSWORD("Your password is too short"),
    TOO_SHORT_LOGIN("Your login is too short"),
    NO_USER_FOUND("User with such login doesn't exists"),
    WRONG_PASSWORD("Wrong password"),
    REQUEST_ERROR("Request error"),
    BAD_TOKEN("Bad token"),
    UNDEFINED_ERROR("Undefined error");

    private final String description;

    LoginStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
