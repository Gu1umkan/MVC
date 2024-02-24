package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id_gen",sequenceName = "user_seq",allocationSize = 1)
public class User extends BaseEntity {
    @Column(unique = true,nullable = false)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.PERSIST)
    private UserInfo userInfo;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Follower follower;
    @OneToMany(mappedBy = "user",cascade ={ CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.DETACH},fetch = FetchType.EAGER)
    private List<Post> posts;
    @ManyToOne
    private Image image;
    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
    @OneToOne(mappedBy = "user")
    private Like like;

    public User(String userName, String password, String email, String phoneNumber) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void addComment(Comment comment){
        if(this.comments == null)this.comments = new ArrayList<>();
        this.comments.add(comment);
    }
}
