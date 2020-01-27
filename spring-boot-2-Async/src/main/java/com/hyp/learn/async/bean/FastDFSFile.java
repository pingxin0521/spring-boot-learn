package com.hyp.learn.async.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hyp
 * Project name is spring-boot-learn
 * Include in com.hyp.learn.async.bean
 * hyp create at 20-1-26
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FastDFSFile {
    private String name;
    private byte[] content;
    private String ext;
    private String md5;
    private String author;
    //省略getter、setter

    public FastDFSFile(String name,byte[] content,String ext)
    {
        this.name=name;
        this.content=content;
        this.ext=ext;
    }
}
