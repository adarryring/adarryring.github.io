package io.github.xiaohong2019.util.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author xiaohong
 */
@Data
@ApiModel(value = "PageQueryParam", description = "分页查询入参")
public class PageQueryParam {

    @ApiModelProperty(value = "当前页")
    private Integer current = 1;

    @ApiModelProperty(value = "每页大小")
    private Integer size = 10;
}
