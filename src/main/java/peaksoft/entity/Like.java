package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id_gen",sequenceName = "like_seq",allocationSize = 1)
public class Like extends BaseEntity{
    @Column(name = "is_like")
    private Boolean isLike;
    @ManyToOne
    private Post post;
    @ManyToOne
    private Comment comment;
    @OneToOne
    private User user;
}
