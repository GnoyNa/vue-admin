package pers.xp.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.xp.bean.Star;
import pers.xp.bean.StarExample;

public interface StarMapper {
    long countByExample(StarExample example);

    int deleteByExample(StarExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Star record);

    int insertSelective(Star record);

    List<Star> selectByExample(StarExample example);

    Star selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Star record, @Param("example") StarExample example);

    int updateByExample(@Param("record") Star record, @Param("example") StarExample example);

    int updateByPrimaryKeySelective(Star record);

    int updateByPrimaryKey(Star record);
}