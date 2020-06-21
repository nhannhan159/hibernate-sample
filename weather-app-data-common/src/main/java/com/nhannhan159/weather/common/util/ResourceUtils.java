package com.nhannhan159.weather.common.util;

import com.nhannhan159.weather.common.base.AppException;
import com.nhannhan159.weather.common.base.ErrorCode;
import org.springframework.core.io.ByteArrayResource;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.zip.GZIPInputStream;

/**
 * @author tien.tan
 */
public class ResourceUtils {
    private static final String ERROR_MSG = "error reading stream";

    private ResourceUtils() {}

    public static Flux<String> readLinesFromGzipStream(InputStream is) {
        return Flux.create(sink -> {
            try (var gis = new GZIPInputStream(is);
                 var isr = new InputStreamReader(gis);
                 var reader = new BufferedReader(isr)) {
                while(reader.ready()) {
                    sink.next(reader.readLine());
                }
            } catch (IOException e) {
                sink.error(new AppException(ErrorCode.SYSTEM_ERROR, ERROR_MSG, e));
                return;
            }
            sink.complete();
        });
    }

    public static Flux<String> readLinesFromStream(InputStream is) {
        return Flux.create(sink -> {
            try (var isr = new InputStreamReader(is);
                 var reader = new BufferedReader(isr)) {
                while(reader.ready()) {
                    sink.next(reader.readLine());
                }
            } catch (IOException e) {
                sink.error(new AppException(ErrorCode.SYSTEM_ERROR, ERROR_MSG, e));
                return;
            }
            sink.complete();
        });
    }

    public static String readAllLinesFromGzipStream(InputStream is) {
        try (var gis = new GZIPInputStream(is);
             var isr = new InputStreamReader(gis);
             var reader = new BufferedReader(isr)) {
            var allLines = new StringBuilder();
            while(reader.ready()) {
                allLines.append(reader.readLine());
            }
            return allLines.toString();
        } catch (IOException e) {
            throw new AppException(ErrorCode.SYSTEM_ERROR, ERROR_MSG, e);
        }
    }

    public static String readAllLinesFromStream(InputStream is) {
        try (var isr = new InputStreamReader(is);
             var reader = new BufferedReader(isr)) {
            var allLines = new StringBuilder();
            while(reader.ready()) {
                allLines.append(reader.readLine());
            }
            return allLines.toString();
        } catch (IOException e) {
            throw new AppException(ErrorCode.SYSTEM_ERROR, ERROR_MSG, e);
        }
    }

    public static Flux<String> readLinesFromByteArrayResource(ByteArrayResource bar, boolean isGz) {
        var monoStream = readStreamFromByteArrayResource(bar);
        if (isGz) {
            return monoStream
                .map(ResourceUtils::readLinesFromGzipStream)
                    .flatMapMany(Flux::from);
            }
            return monoStream
                .map(ResourceUtils::readLinesFromStream)
                .flatMapMany(Flux::from);
        }

    public static Mono<String> readAllLinesFromByteArrayResource(ByteArrayResource bar, boolean isGz) {
        var monoStream = readStreamFromByteArrayResource(bar);
        if (isGz) {
            return monoStream
                .map(i -> Optional.of(ResourceUtils.readAllLinesFromGzipStream(i))
                    .orElseThrow());
        }
        return monoStream
            .map(i -> Optional.of(ResourceUtils.readAllLinesFromStream(i))
                .orElseThrow());
    }

    public static Mono<InputStream> readStreamFromByteArrayResource(ByteArrayResource bar) {
        return Mono.fromCallable(() -> {
            try (var is = bar.getInputStream()) {
                return is;
            } catch (IOException e) {
                throw new AppException(ErrorCode.SYSTEM_ERROR, ERROR_MSG, e);
            }
        });
    }

    public static void main(String[] args) {
        //Resource fileResource = new ClassPathResource("weather_14.json.gz");
        //try (var fis = new FileInputStream(fileResource.getFile())) {
        //    readLinesFromGzipStream(fis)
        //        .subscribe(log::info);
        //} catch (IOException e) {
        //    log.error(ERROR_MSG, e);
        //}
    }
}