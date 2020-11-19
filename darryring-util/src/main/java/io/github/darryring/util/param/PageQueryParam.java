package io.github.darryring.util.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author darryring
 */
@Data
@ApiModel(value = "PageQueryParam", description = "分页查询入参")
public class PageQueryParam {

    @ApiModelProperty(value = "当前页")
    private Integer current = 1;

    @ApiModelProperty(value = "每页大小")
    private Integer size = 10;
}
