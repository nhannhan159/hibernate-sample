package com.nhannhan159.weather.data.repository.jpa;

import com.nhannhan159.weather.data.entity.CityDO;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author tien.tan
 */
@Repository
public interface CityRepository extends JpaRepository<CityDO, String> {

    default WebsterUserTemplateFp10DO createOrUpdate(WebsterUserTemplateFp10DO entity) {
        Optional<WebsterUserTemplateFp10DO> existEntityOptional =
            findByUserIdAndSerialNoAndFingerId(entity.getUserId(), entity.getSerialNo(), entity.getFingerId());
        if (existEntityOptional.isPresent()) {
            WebsterUserTemplateFp10DO oldEntity = existEntityOptional.get();
            BeanUtils.copyProperties(entity, oldEntity, "id", "gmtCreated", "tenantId");
            entity = oldEntity;
        }
        return saveAndFlush(entity);
    }
}
