package ru.hogwarts.school.controller;

import org.springframework.data.domain.Page;
import ru.hogwarts.school.entity.Avatar;
import ru.hogwarts.school.service.AvatarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(value = "/{studentId}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long studentId, @RequestParam MultipartFile avatar) throws IOException {
        avatarService.uploadAvatar(studentId, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/student/{id}/avatar-from-db")
    public ResponseEntity<byte[]> downloadAvatarFromDb(@PathVariable Long id) {
        return avatarService.downloadAvatarByStudentFromDb(id);
    }

    @GetMapping(value = "/student/{id}/avatar-from-file")
    public void downloadAvatarFromFileSystem(@PathVariable Long id, HttpServletResponse response) throws IOException{
        avatarService.downloadAvatarFromFileSystem(id, response);
    }
    @GetMapping
    public Page<Avatar> getWithPageable(@RequestParam Integer page, @RequestParam Integer count) throws IOException{
        return avatarService.getWithPageable(page, count);
    }
}
