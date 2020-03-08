package com.nhannhan159.base.api;

import com.nhannhan159.base.application.AppException;
import com.nhannhan159.base.application.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;

import java.io.IOException;

/**
 * @author tien.tan
 */
@Slf4j
public class BaseApiService {

    protected <T> T execute(Call<T> apiCallable) {
        try {
            return apiCallable.execute().body();
        } catch (IOException e) {
            throw new AppException(ErrorCode.SYSTEM_API_ERROR, e);
        }
    }
}
