package com.example.demo.service;

import com.example.demo.entity.Admin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    List<Admin> getAllAdmin();

    List<Admin> getAdminByPagination(int pageNo, int pageSize);

    Integer getAdminCount();

    Admin getAdminById(Long id);

    Admin saveAdmin(Admin admin);

    Admin updateAdmin(Admin admin);

    void deleteAdmin(Long id);

    String uploadImage(MultipartFile image) throws IOException;

    String updateImage(String oldImageName, MultipartFile image) throws IOException;

    String encryptPassword(String password);

}
