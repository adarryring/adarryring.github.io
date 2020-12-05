package io.github.darryring.mybatis.mapper;

import io.github.darryring.mybatis.entity.UserDO;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *
 * </p>
 *
 * @author darryring
 * @since 2020-10-27 11:49
 */
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id = #{id}")
    UserDO selectUser(int id);

}
