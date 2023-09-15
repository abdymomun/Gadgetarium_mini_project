package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoComment.CommentRequest;
import peaksoft.dto.dtoComment.CommentResponse;

import java.util.List;

public interface CommentService {
    SimpleResponse create(CommentRequest commentRequest,Long productId);
    List<CommentResponse> getAll(Long productId);
    CommentResponse getById(Long id);
    SimpleResponse update(Long id,CommentRequest commentRequest);
    SimpleResponse delete(Long id);

}
