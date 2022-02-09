package com.myself.demo.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.myself.demo.pojo.FilePojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FileMapper extends BaseMapper<FilePojo> {
    FilePojo selectByUUID(@Param("uuid")String uuid);
}

