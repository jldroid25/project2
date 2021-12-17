package com.expenseapp.expenseapp.service;

import java.io.IOException;
import java.util.stream.Stream;

import com.expenseapp.expenseapp.dao.FileDBRepository;
import com.expenseapp.expenseapp.entity.FileDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public FileDB store(MultipartFile file, int rid) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes(), rid);

        return fileDBRepository.save(FileDB);
    }

    public FileDB getFile(int id) {
        return fileDBRepository.findByRid(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
