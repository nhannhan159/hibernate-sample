package com.nhannhan159.openweather.config;

import com.nhannhan159.openweather.api.OpenWeatherRawApi;
import com.nhannhan159.openweather.api.OpenWeatherAuthInterceptor;
import com.nhannhan159.openweather.api.OpenWeatherProperties;
import lombok.RequiredArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tien.tan
 */
@RequiredArgsConstructor
@Configuration
@ConditionalOnClass(OpenWeatherRawApi.class)
@ConditionalOnMissingBean(type = "com.nhannhan159.openweather.api.OpenWeatherRawApi")
@EnableConfigurationProperties({
    RetrofitProperties.class,
    OpenWeatherProperties.class
})
public class RetrofitConfig {
    private final RetrofitProperties retrofitProperties;
    private final OpenWeatherProperties openWeatherProperties;

    @Bean
    @ConditionalOnMissingBean
    public Converter.Factory gsonConverter() {
        return GsonConverterFactory.create();
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenWeatherAuthInterceptor openWeatherAuthInterceptor() {
        return new OpenWeatherAuthInterceptor(this.openWeatherProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenWeatherRawApi openWeatherApi() {
        var okHttpClient = this.httpClientBuilder(Collections.singletonList(this.openWeatherAuthInterceptor()));
        var retrofit = this.retrofitBuilder(this.openWeatherProperties.getUrl(), okHttpClient);
        return retrofit.create(OpenWeatherRawApi.class);
    }

    private OkHttpClient httpClientBuilder(List<Interceptor> interceptors) {
        var loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        var builder = new OkHttpClient.Builder()
            .connectTimeout(this.retrofitProperties.getConnectTimeout(), TimeUnit.SECONDS);
        interceptors.forEach(builder::addInterceptor);
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    private Retrofit retrofitBuilder(String baseUrl, OkHttpClient client) {
        var converterList = Collections.singletonList(this.gsonConverter());
        return this.retrofitBuilder(baseUrl, client, converterList);
    }

    private Retrofit retrofitBuilder(String baseUrl, OkHttpClient client, List<Converter.Factory> converterList) {
        var builder = new Retrofit.Builder();
        converterList.forEach(builder::addConverterFactory);
        return builder
            .client(client)
            .baseUrl(baseUrl)
            .build();
    }
}
