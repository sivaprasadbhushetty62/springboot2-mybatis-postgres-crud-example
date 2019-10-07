package com.dmas.springboot2mybatispostgrescrudexample.repository;

import com.dmas.springboot2mybatispostgrescrudexample.model.Video;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface VideoRepository  {
  @Results({
      @Result(property = "title", column = "title"),
      @Result(property = "url", column = "url"),
      @Result(property = "description", column = "description"),
      @Result(property = "id", column = "id")
  })
  @Select("select * from videos")
  public List<Video> findAll();

  @Select("SELECT * FROM videos WHERE id = #{id}")
  public Optional<Video> findById(long id);

  @Delete("DELETE FROM videos WHERE id = #{id}")
  public int deleteById(long id);

  @Insert("INSERT INTO videos(title, url, description) " +
      " VALUES (#{title}, #{url}, #{description})")
  @SelectKey(statement="select currval('videos_id_seq')",keyColumn = "id",keyProperty = "id", resultType = Long.class, before=false)
  public int insert(Video video);

  @Update("Update videos set title=#{title}, " +
      " url=#{url}, description=#{description} where id=#{id}")
  public int update(Video video);
}
