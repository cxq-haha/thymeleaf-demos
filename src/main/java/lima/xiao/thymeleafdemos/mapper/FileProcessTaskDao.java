package lima.xiao.thymeleafdemos.mapper;

import lima.xiao.thymeleafdemos.entity.FileProcessTask;
import lima.xiao.thymeleafdemos.entity.FileProcessTaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileProcessTaskDao {
    long countByExample(FileProcessTaskExample example);

    int deleteByExample(FileProcessTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(FileProcessTask record);

    int insertSelective(FileProcessTask record);

    List<FileProcessTask> selectByExampleWithBLOBs(FileProcessTaskExample example);

    List<FileProcessTask> selectByExample(FileProcessTaskExample example);

    FileProcessTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") FileProcessTask record, @Param("example") FileProcessTaskExample example);

    int updateByExampleWithBLOBs(@Param("record") FileProcessTask record, @Param("example") FileProcessTaskExample example);

    int updateByExample(@Param("record") FileProcessTask record, @Param("example") FileProcessTaskExample example);

    int updateByPrimaryKeySelective(FileProcessTask record);

    int updateByPrimaryKeyWithBLOBs(FileProcessTask record);

    int updateByPrimaryKey(FileProcessTask record);
}