package danandla.controller.response;

public enum LoginStatus {
    OK("ok"),
    USER_ALREADY_EXISTS("User with such login already exists"),
    NO_USER_FOUND("User with such login doesn't exists"),
    WRONG_PASSWORD("Wrong password"),
    REQUEST_ERROR("Request error"),
    BAD_TOKEN("Bad token"),
    UNABLE_TO_INSERT("Unable to insert new user in db"),
    UNABLE_TO_UPDATE("Unable to update entity"),
    UNDEFINED_ERROR("Undefined error");

    private final String description;

    LoginStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
