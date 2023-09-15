package peaksoft.controller;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoComment.CommentRequest;
import peaksoft.dto.dtoComment.CommentResponse;
import peaksoft.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
        private final CommentService commentService;

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PostMapping("/{id}")
        public SimpleResponse createComment(@RequestBody CommentRequest commentRequest, @PathVariable Long id) {
            return commentService.create(commentRequest,id);
        }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
        @GetMapping("/getAll/{id}")
        public List<CommentResponse> getAllComments(@PathVariable Long id) {
            return commentService.getAll(id);
        }

        @GetMapping("/{id}")
        public CommentResponse getCommentById(@PathVariable Long id) {
            return commentService.getById(id);
        }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
        @PutMapping("/{id}")
        public SimpleResponse updateComment(@PathVariable Long id, @RequestBody CommentRequest commentRequest) {
            return commentService.update(id, commentRequest);
        }
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
        @DeleteMapping("/{id}")
        public SimpleResponse deleteComment(@PathVariable Long id) {
            return commentService.delete(id);
        }

    }
