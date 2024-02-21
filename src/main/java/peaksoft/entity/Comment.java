package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id_gen",sequenceName = "comment_seq",allocationSize = 1)
public class Comment extends BaseEntity{
    private String comment;
    private LocalDate createdAt;
    @ManyToOne(cascade = CascadeType.DETACH)
    private User user;
    @ManyToOne
    private Post post;
    @OneToMany(mappedBy = "comment")
    private List<Like> likes;
}
