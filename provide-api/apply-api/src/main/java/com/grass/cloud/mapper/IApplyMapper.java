package com.grass.cloud.mapper;

import com.grass.cloud.entity.Apply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户 mybatis 映射文件。
 *
 * @author hmilyylimh
 *
 * @version 0.0.1
 *
 * @date 2017-10-19
 *
 */
public interface IApplyMapper {

    @Select("select * from apply where id = #{id}")
    Apply findApplyById(Integer id);

    @Select("select * from apply where from_id = #{from_id}")
    List<Apply> findAllApplys(String from_id);

    @Select("select * from apply where to_id = #{to_id}")
    List<Apply> findAllApplysTo(String to_id);

    @Insert("INSERT INTO apply(from_id, to_id, from_time, to_time, start_time, end_time, destination, reason, comment, status) VALUES(#{from_id}, #{to_id}, #{from_time}, #{to_time}, #{start_time}, #{end_time}, #{destination}, #{reason}, #{comment}, #{status})")
    @SelectKey(statement="select LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertApply(Apply apply);

    @Update("UPDATE apply SET from_id=#{from_id}, to_id=#{to_id}, from_time=#{from_id}, to_time=#{to_time}, start_time=#{start_time}, end_time=#{end_time}, destination=#{destination}, reason=#{reason}, comment=#{comment}, status=#{status} WHERE id=#{id}")
    int updateApply(Apply apply);
}