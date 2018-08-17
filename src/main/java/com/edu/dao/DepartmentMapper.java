package com.edu.dao;

import com.edu.dao.implProvider.DepartmentSqlProvider;
import com.edu.entity.Department;
import com.edu.entity.DepartmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface DepartmentMapper {
    @SelectProvider(type=DepartmentSqlProvider.class, method="countByExample")
    long countByExample(DepartmentExample example);

    @DeleteProvider(type=DepartmentSqlProvider.class, method="deleteByExample")
    int deleteByExample(DepartmentExample example);

    @Delete({
        "delete from department",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into department (id, department_name)",
        "values (#{id,jdbcType=INTEGER}, #{departmentName,jdbcType=VARCHAR})"
    })
    int insert(Department record);

    @InsertProvider(type=DepartmentSqlProvider.class, method="insertSelective")
    int insertSelective(Department record);

    @SelectProvider(type=DepartmentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="department_name", property="departmentName", jdbcType=JdbcType.VARCHAR)
    })
    List<Department> selectByExample(DepartmentExample example);

    @Select({
        "select",
        "id, department_name",
        "from department",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="department_name", property="departmentName", jdbcType=JdbcType.VARCHAR)
    })
    Department selectByPrimaryKey(Integer id);

    @UpdateProvider(type=DepartmentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    @UpdateProvider(type=DepartmentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    @UpdateProvider(type=DepartmentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Department record);

    @Update({
        "update department",
        "set department_name = #{departmentName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Department record);
}