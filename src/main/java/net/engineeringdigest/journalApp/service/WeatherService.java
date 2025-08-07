package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Constant.Placeholder;
import net.engineeringdigest.journalApp.apiResponse.WeatherResponse;
import net.engineeringdigest.journalApp.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {//weatherStack.com from there , you can create apikey.
    // And here we are hitting api with the codebase instead of postman or browser.

    @Value("${weather_api_key}")
    private String apikey ;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city){
        WeatherResponse weatherResponse = redisService.get("Weather of " + city, WeatherResponse.class);
        if (weatherResponse != null){
            return  weatherResponse;
        }else {
            String FinalApi = appCache.appCache.get("weather_api").replace(Placeholder.CITY , city).replace(Placeholder.API_KEY , apikey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(FinalApi, HttpMethod.GET,null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null){
                redisService.set("Weather of " + city, body ,300l);
            }
            return body;
        }

    }

    private static HttpEntity<Object> getHttpEntity(HttpHeaders httpHeaders) {
        return new HttpEntity<>(httpHeaders);
    }

}
//
//        String requestBody = "{\n" +
//                "\"userName\":\"saurabh\",\n" +
//                "\"password\":\"saurabh\",\n" +
//                "}  ";
//        HttpEntity<String> httpEntity = new HttpEntity<>(requestBody);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set("key","value");
//        HttpEntity<String> httpEntity1 = new HttpEntity<>(httpHeaders);
//
//        httpEntity1 and httpEntity are passed as a request entity in restTemplate.exchange method.
//        this for external api post call
//
