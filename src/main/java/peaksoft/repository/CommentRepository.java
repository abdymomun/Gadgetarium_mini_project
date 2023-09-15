package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.dtoComment.CommentResponse;
import peaksoft.models.Comment;

import java.util.List;

@Repository

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select  new peaksoft.dto.dtoComment.CommentResponse(c.id,c.comment,c.createDate) " +
            "from Comment  c join Product p on p.id = c.product.id where p.id=:id")
    List<CommentResponse> getAllComment(@Param("id") Long id);
}