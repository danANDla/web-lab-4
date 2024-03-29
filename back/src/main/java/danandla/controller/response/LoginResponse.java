package danandla.controller.response;

public class LoginResponse {
    private LoginStatus loginStatus;
    private String jwtToken;

    public LoginResponse() {
    }

    public LoginResponse(LoginStatus loginStatus, String jwtToken) {
        this.loginStatus = loginStatus;
        this.jwtToken = jwtToken;
    }

    public LoginStatus getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(LoginStatus loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
