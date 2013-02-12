package calendar.controller;

import org.resthub.web.Client;
import org.resthub.web.Http;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RootController {
    private static final String CLIENT_ID = encode("800430709381-b7jjp8l92qn0jth8hkaar2gj08dvnmvp.apps.googleusercontent.com");
    private static final String CLIENT_SECRET = encode("-oDwYG12Mzdb8lhHxKeV38Z6");
    private static final String REFRESH_TOKEN = encode("1/2-og1uUV3uQtcbcdj2PKt4IfDl8Z9XylEiRrRZHsUxE");
    Client httpClient = new Client();

    private static String encode(String s) {
        try {
            return UriUtils.encodePathSegment(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
    }

    @RequestMapping(value = "/list")
    public
    @ResponseBody
    Map list() throws Exception {
        return httpClient.url("https://www.googleapis.com/calendar/v3/users/me/calendarList")
                .setQueryParameter("pp", "1")
                .setQueryParameter("access_token", getAccessToken())
                .get()
                .resource(HashMap.class);
    }

    @RequestMapping(value = "/details")
    public
    @ResponseBody
    String details(@RequestParam("calendar") String calendar, @RequestParam("today") String today, @RequestParam("tomorrow") String tomorrow) {
        String body = httpClient.url("https://www.googleapis.com/calendar/v3/calendars/" + calendar + "/events")
                .setQueryParameter("timeMax", tomorrow)
                .setQueryParameter("timeMin", today)
                .setQueryParameter("access_token", getAccessToken())
                .getJson().getBody();
        return body;
    }

    private String getAccessToken() {
        String body = "client_id=" + CLIENT_ID + "&" +
                "client_secret=" + CLIENT_SECRET + "&" +
                "refresh_token=" + REFRESH_TOKEN + "&" +
                "grant_type=refresh_token";

        HashMap<String, String> s = httpClient.url("https://accounts.google.com/o/oauth2/token")
                .setHeader(Http.CONTENT_TYPE, Http.FORM)
                .post(body)
                .resource(HashMap.class);
        return s.get("access_token");
    }
}