package com.ruoyi.project.system.qualify.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * author:zwy
 * Date:2019-10-20
 * Time:15:40
 */
@Data
public class ConfirmHistoryDTO {

    private String img;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date time;
}
