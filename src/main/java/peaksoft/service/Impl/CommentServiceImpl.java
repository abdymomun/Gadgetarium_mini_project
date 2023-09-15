package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.dtoComment.CommentRequest;
import peaksoft.dto.dtoComment.CommentResponse;
import peaksoft.exception.NotFoundException;
import peaksoft.models.Comment;
import peaksoft.models.Product;
import peaksoft.models.User;
import peaksoft.repository.CommentRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.CommentService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    @Override
    public SimpleResponse create(CommentRequest commentRequest, Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
       User user = userRepository.getUserByEmail(email).orElseThrow(()->
               new NotFoundException("User with email: " + email + " not found !"));
       Product product = productRepository.findById(productId).orElseThrow(() ->
               new NotFoundException("Product with id: " + productId + " not found !" ));
        Comment comment = new Comment();
        comment.setComment(commentRequest.getComment());
        comment.setCreateDate(LocalDate.now());
        comment.setProduct(product);
        product.setComments(List.of(comment));
        user.setComments(List.of(comment));
        comment.setUser(user);
        commentRepository.save(comment);
        return new SimpleResponse(HttpStatus.CONTINUE,"Comment successfully saved ! ");
    }

    @Override
    public List<CommentResponse> getAll(@Param("id") Long id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Product with id: " +id + " not found !" ));
        List<CommentResponse>commentResponses = new ArrayList<>();
        CommentResponse commentResponse = new CommentResponse();
        commentResponses.add(commentResponse);
        List<Comment> comments = product.getComments();
        comments.forEach(comment -> {
            commentResponse.setComment(comment.getComment());
            commentResponse.setId(comment.getId());
            commentResponse.setCreateDate(comment.getCreateDate());
        });


        return commentResponses;
    }

    @Override
    public CommentResponse getById(Long id) {
       Comment comment = commentRepository.findById(id).orElseThrow(()->
                new NotFoundException("Comment with id: " + id + " not found !"));
       CommentResponse commentResponse = new CommentResponse();
       commentResponse.setId(comment.getId());
       commentResponse.setComment(comment.getComment());
       commentResponse.setCreateDate(comment.getCreateDate());
        return commentResponse;
    }

    @Override
    public SimpleResponse update(Long id, CommentRequest commentRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(()->
                new NotFoundException("User with email: " + email + " not found !"));
        Comment comment = commentRepository.findById(id).orElseThrow(()->
                new NotFoundException("Comment with id: " + id + " not found !"));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("You do not have permission to update this comment!");
        }
        comment.setComment(commentRequest.getComment());
        commentRepository.save(comment);
        return new SimpleResponse(HttpStatus.OK,"Comment with id: " + id + " successfully updated !");
    }

    @Override
    public SimpleResponse delete(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(()->
                new NotFoundException("User with email: " + email + " not found !"));
        Comment comment = commentRepository.findById(id).orElseThrow(()->
                new NotFoundException("Comment with id: " + id + " not found !"));
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("You do not have permission to update this comment!");
        }
         commentRepository.delete(comment);
        return new SimpleResponse(HttpStatus.OK,"Comment with id: " + id + " successfully deleted !");
    }
}
