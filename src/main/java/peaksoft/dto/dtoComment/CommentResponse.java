package peaksoft.dto.dtoComment;

import lombok.Getter;
import lombok.Setter;
import peaksoft.models.Product;
import peaksoft.models.User;
import java.time.LocalDate;
@Getter
@Setter
public class CommentResponse {
    private Long id;
    private String comment;
    private LocalDate createDate;

    public CommentResponse() {
    }

    public CommentResponse(Long id, String comment, LocalDate createDate) {
        this.id = id;
        this.comment = comment;
        this.createDate = createDate;
    }
}
