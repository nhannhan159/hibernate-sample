package com.nhannhan159.openweather.api;

import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.springframework.lang.NonNull;

import java.io.IOException;

/**
 * @author tien.tan
 */
@RequiredArgsConstructor
public class OpenWeatherAuthInterceptor implements Interceptor {

    private final OpenWeatherProperties properties;

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        var original = chain.request();
        var originalHttpUrl = original.url();

        var url = originalHttpUrl.newBuilder()
            .addQueryParameter("appid", this.properties.getAppId())
            .build();

        var request = original.newBuilder()
            .url(url)
            .build();

        return chain.proceed(request);
    }
}
