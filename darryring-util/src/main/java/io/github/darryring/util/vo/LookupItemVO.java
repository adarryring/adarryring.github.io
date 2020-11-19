package io.github.darryring.util.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-09-15 20:04
 */
@Data
@ApiModel(value = "LookupItemVO", description = "字典")
public class LookupItemVO {

    @ApiModelProperty(value = "键")
    private String lookupItemKey;

    @ApiModelProperty(value = "名称")
    private String lookupItemName;

}
