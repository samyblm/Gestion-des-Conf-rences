package com.example.demo.Articles;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table
public class File {
    @Id
    @SequenceGenerator(
        name = "file_sequence",
        sequenceName = "file_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "file_sequence")

    private Long id;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] data;

    public File() {
    
    }
    public File(Long id, String fileName, String fileType, byte[] data){
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
    public File(String fileName, String fileType, byte[] data){
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "File [data=" + Arrays.toString(data) + ", fileName=" + fileName + ", fileType=" + fileType + ", id="
                + id + "]";
    }


}