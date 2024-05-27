package com.example.demo.service.impl;


import com.example.demo.entity.Admin;
import com.example.demo.repository.AdminRepository;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;


@Service
public class AdminServiceimpl implements AdminService {
    private String UPLOAD_DIR = System.getProperty("user.dir")
            + "/src/main/resources/static/uploadspdf/";
    @Autowired
    public AdminRepository repository;

    @Override
    public List<Admin> getAllAdmin() {
        return repository.findAll();
    }

    @Override
    public List<Admin> getAdminByPagination(int pageNo, int pageSize) {

        PageRequest pageReq = PageRequest.of(pageNo-1, pageSize);
        Page<Admin> page = repository.findAll(pageReq);

        return page.getContent();
    }

    @Override
    public Integer getAdminCount() {
        return (int) repository.count();
    }

    @Override
    public Admin getAdminById(Long id) {
        return repository.findById(id).get();    }

    @Override
    public Admin saveAdmin(Admin admin) {
        return repository.save(admin);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return repository.save(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        repository.deleteById(id);

    }

    @Override
    public String uploadImage(MultipartFile image) throws IOException {
        if(image != null && !image.isEmpty()) {

            String originalFilename = image.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            String uniqueName = UUID.randomUUID().toString() + extension;

            Path path = Paths.get(UPLOAD_DIR + uniqueName);
            Files.write(path, image.getBytes());

            System.out.println("UPLOAD PATH : " + UPLOAD_DIR + uniqueName);
            return uniqueName;
        }
        return null;
    }

    @Override
    public String updateImage(String oldImageName, MultipartFile image) throws IOException {
        if(image != null && !image.isEmpty()) {
            File oldImage = new File(UPLOAD_DIR + oldImageName);
            if(oldImage.exists() && oldImage.isFile()) {
                oldImage.delete();
            }
        }
        return uploadImage(image);

    }

    @Override
    public String encryptPassword(String password) {
        int strength = 10;
        BCryptPasswordEncoder bCryptPasswordEncoder =
                new BCryptPasswordEncoder(strength, new SecureRandom());
        String encodedPassword = bCryptPasswordEncoder.encode(password);

        return encodedPassword;
    }


}






















/*
import com.example.GestionScolarite.Models.Etudiant;
import com.example.GestionScolarite.Models.admin;
import com.example.GestionScolarite.controller.AdminController;
import com.example.GestionScolarite.repository.AdminRepository;
import com.example.GestionScolarite.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    public final AdminRepository adminRepository;

    @Autowired

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Optional<admin> login(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password).orElse(null);
    }


    public admin createAdmin(admin Admin) {
        return adminRepository.save(Admin);
    }

    public admin getadminById(Long id) {
        return adminRepository.findById(id).orElse(null);
    }

    public admin updateAdmin(Long id, admin updatedAdmin) {
        admin existingAdmin = getadminById(id);
        if (existingAdmin != null) {
            existingAdmin.setEmail(updatedAdmin.getEmail());
            existingAdmin.setPassword(updatedAdmin.getPassword());
            existingAdmin.setNom(updatedAdmin.getNom());
            existingAdmin.setPrenom(updatedAdmin.getPrenom());

            return adminRepository.save(updatedAdmin);
        }
        return null;
    }

    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    public List<admin> getAllAdmin() {
        return adminRepository.findAll();
    }


}
*/