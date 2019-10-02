package com.ruoyi.project.system.user.domain;

import com.ruoyi.project.system.quba.domain.QubaDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * author:zwy
 * Date:2019-10-02
 * Time:16:39
 */
@Data
public class UserDTO {

    private Long id;

    private String userAvatar;

    private String userName;

    private Boolean isQualified;

    private String gender;

    private BigDecimal coinNumber;

    private Long collectedNumber;

    private Long focusNumber;

    private List<QubaDTO> myBar;
}
