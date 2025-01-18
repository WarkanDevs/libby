package net.byteflux.libby;

import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Repository {

    private String url;
    private String basicHTTPAuthUser;
    private String basicHTTPAuthPassword;

    public Repository(String url, String basicHTTPAuthUser, String basicHTTPAuthPassword) {
        if (url == null) {
            throw new IllegalArgumentException("URL cannot be null");
        }
        this.url = url;
        this.basicHTTPAuthUser = basicHTTPAuthUser;
        this.basicHTTPAuthPassword = basicHTTPAuthPassword;
    }

    public String getURL() {
        return url;
    }

    public boolean hasBasicHTTPAuth() {
        return basicHTTPAuthUser != null && basicHTTPAuthPassword != null;
    }

    public String getBasicHTTPAuthUser() {
        return basicHTTPAuthUser;
    }

    public String getBasicHTTPAuthPassword() {
        return basicHTTPAuthPassword;
    }

    public void configureURLConnection(URLConnection connection) {
        if (hasBasicHTTPAuth()) {
            String encoded = Base64.getEncoder().encodeToString((getBasicHTTPAuthUser() + ":" + getBasicHTTPAuthPassword()).getBytes(StandardCharsets.UTF_8));
            connection.setRequestProperty("Authorization", "Basic " + encoded);
        }
    }

}
