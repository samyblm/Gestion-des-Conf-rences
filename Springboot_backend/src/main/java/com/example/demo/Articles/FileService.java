package com.example.demo.Articles;


import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

 
@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public File storeFile(MultipartFile fil,Long id) {
        String fileName =fil.getOriginalFilename();

        try{
            if(fileName.contains("..")){
                throw new FileStorageException("Filename contains invalid path sequence" + fileName);

            }

        File file = new File(fileName, fil.getContentType(), fil.getBytes());
            file.setId(id);
        return fileRepository.save(file);
        } catch(IOException ex){
            throw new FileStorageException("could not found store file" + fileName  + "try again", ex);
        }
    }
    public File getFile(Long fileId) {
        return fileRepository.findById(fileId)             
        .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));

}
}
