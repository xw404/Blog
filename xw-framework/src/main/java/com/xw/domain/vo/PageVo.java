package com.xw.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author 小吴
 * @Date 2023/05/30 13:12
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PageVo {
    private List rows;
    private Long count;
}
