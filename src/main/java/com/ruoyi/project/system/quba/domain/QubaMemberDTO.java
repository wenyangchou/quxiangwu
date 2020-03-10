package com.ruoyi.project.system.quba.domain;

import lombok.Data;

import java.util.List;

/**
 * author:zwy
 * Date:2020-03-10
 * Time:23:16
 */
@Data
public class QubaMemberDTO {

    private String logo;

    private String name;

    private String desc;

    private Integer status;

    private Integer memberNum;

    private List<String> avatarList;
}
