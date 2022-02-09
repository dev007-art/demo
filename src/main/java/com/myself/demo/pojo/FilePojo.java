package com.myself.demo.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

@Data
@TableName("file")
public class FilePojo {
    @TableId(value="id",type= IdType.AUTO)
    private int id;
    @TableField("size")
    private  int size;
    @TableField("type")
    private String type;
    @TableField("name")
    private String name;
    @TableField("data")
    private String data;
    @TableField("path")
    private String path;
    @TableField("uuid")
    private String uuid;
}
