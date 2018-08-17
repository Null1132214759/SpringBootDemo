package com.edu.dao;

import com.edu.dao.implProvider.EmployeeSqlProvider;
import com.edu.entity.Employee;
import com.edu.entity.EmployeeExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;

public interface EmployeeMapper {
    @SelectProvider(type=EmployeeSqlProvider.class, method="countByExample")
    long countByExample(EmployeeExample example);

    @DeleteProvider(type=EmployeeSqlProvider.class, method="deleteByExample")
    int deleteByExample(EmployeeExample example);

    @Delete({
        "delete from employee",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into employee (id, last_name, ",
        "email, gender, d_id)",
        "values (#{id,jdbcType=INTEGER}, #{lastName,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{gender,jdbcType=INTEGER}, #{dId,jdbcType=INTEGER})"
    })
    int insert(Employee record);

    @InsertProvider(type=EmployeeSqlProvider.class, method="insertSelective")
    int insertSelective(Employee record);

    @SelectProvider(type=EmployeeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="last_name", property="lastName", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="gender", property="gender", jdbcType=JdbcType.INTEGER),
        @Result(column="d_id", property="dId", jdbcType=JdbcType.INTEGER)
    })
    List<Employee> selectByExample(EmployeeExample example);

    // 带部门的查询
    @SelectProvider(type=EmployeeSqlProvider.class, method="selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="last_name", property="lastName", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="gender", property="gender", jdbcType=JdbcType.INTEGER),
            @Result(column="d_id", property="department", 
                    one = @One(select = "com.edu.dao.DepartmentMapper.selectByPrimaryKey",
                    fetchType = FetchType.EAGER))
    })
    List<Employee> selectByExampleWithDept(EmployeeExample example);

    @Select({
            "select",
            "id, last_name, email, gender, d_id",
            "from employee",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="last_name", property="lastName", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="gender", property="gender", jdbcType=JdbcType.INTEGER),
            @Result(column="d_id", property="dId", jdbcType=JdbcType.INTEGER)
    })
    Employee selectByPrimaryKey(Integer id);

    // 带部门的通过主键查询
    @Select({
            "select",
            "id, last_name, email, gender, d_id",
            "from employee",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="last_name", property="lastName", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="gender", property="gender", jdbcType=JdbcType.INTEGER),
            @Result(column="d_id", property="department",
                    one = @One(select = "com.edu.dao.DepartmentMapper.selectByPrimaryKey",
                            fetchType = FetchType.EAGER))
    })
    Employee selectByPrimaryKeyWithDetp(Integer id);

    @UpdateProvider(type=EmployeeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Employee record, @Param("example") EmployeeExample example);

    @UpdateProvider(type=EmployeeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Employee record, @Param("example") EmployeeExample example);

    @UpdateProvider(type=EmployeeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Employee record);

    @Update({
        "update employee",
        "set last_name = #{lastName,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "gender = #{gender,jdbcType=INTEGER},",
          "d_id = #{dId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Employee record);
}