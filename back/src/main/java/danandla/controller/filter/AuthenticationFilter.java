package danandla.controller.filter;

import danandla.model.PasswordKitchen;
import danandla.model.dbutils.UserTableUtil;
import danandla.model.entities.User;
import io.jsonwebtoken.Claims;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @EJB
    private final PasswordKitchen passwordKitchen = new PasswordKitchen();

    @EJB
    UserTableUtil userTableUtil = new UserTableUtil();

    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // Get the Authorization header from the request
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        // Validate the Authorization header
        if (!isTokenBasedAuthentication(authorizationHeader)) {
            abortWithUnauthorized(requestContext);
            return;
        }

        // Extract the token from the Authorization header
        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            // Validate the token
            validateToken(token);

        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader) {

        // Check if the Authorization header is valid
        // It must not be null and must be prefixed with "Bearer" plus a whitespace
        // The authentication scheme comparison must be case-insensitive
        return authorizationHeader != null && authorizationHeader.toLowerCase()
                .startsWith(AUTHENTICATION_SCHEME.toLowerCase() + " ");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {

        // Abort the filter chain with a 401 status code response
        // The WWW-Authenticate header is sent along with the response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                AUTHENTICATION_SCHEME + " realm=\"" + REALM + "\"")
                        .build());
    }

    private void validateToken(String token) throws Exception {
        // Check if the token was issued by the server and if it's not expired
        // Throw an Exception if the token is invalid
        Claims parsed = passwordKitchen.decodeJWT(token);
        System.out.println("Decoded jwt: \n" + "id: " + parsed.getId() +
                "\n expiration time: " + parsed.getExpiration() +
                "\n issued at: " + parsed.getIssuedAt() +
                "\n issuer: " + parsed.getIssuer() +
                "\n subject: " + parsed.getSubject());
        User found = userTableUtil.getUserByLogin(parsed.getSubject());
        if (found == null) {
            System.out.println("user wasn't found");
            throw new Exception();
        }
        if (!found.getToken().equals(token)) {
            System.out.println("tokens are not equal");
            throw new Exception();
        }

        Claims fromDB = passwordKitchen.decodeJWT(found.getToken());

        System.out.println("expired at from db: " + fromDB.getExpiration());

        long nowMillis = System.currentTimeMillis();
        long expMillis = parsed.getExpiration().getTime();
        if (expMillis < nowMillis) {
            System.out.println("time expired");
            throw new Exception();
        }


    }
}