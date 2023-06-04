package com.xw.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 小吴
 * @Date 2023/06/03 17:38
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListDto {
    //标签名
    private String name;
    //描述
    private String remark;
}
