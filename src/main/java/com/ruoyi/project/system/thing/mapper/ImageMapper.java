package com.ruoyi.project.system.thing.mapper;

import com.ruoyi.project.system.thing.domain.Image;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-10
 * Time:13:22
 */
@Repository
public interface ImageMapper {

    Image getById(Long id);

    List<Image> getByThingId(Long id);

    int insertImage(Image image);
}
