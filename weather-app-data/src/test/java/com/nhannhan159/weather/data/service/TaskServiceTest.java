package com.nhannhan159.weather.data.service;

import com.nhannhan159.weather.data.api.service.OpenWeatherApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskServiceTest {

    @Autowired
    private TaskService taskService;

    @Autowired
    private OpenWeatherApiService openWeatherApiService;

    @Test
    public void getBulkCities_success() {
        //this.taskService.getBulkCities();
        this.openWeatherApiService.fetchResourceTest("city.list.json.gz");
    }
}
