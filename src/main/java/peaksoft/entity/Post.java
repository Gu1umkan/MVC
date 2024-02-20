package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id_gen",sequenceName = "post_seq",allocationSize = 1)
public class Post extends BaseEntity{
    private String title;
    private String description;
    @Column(name = "created_at")
    private LocalDate createdAt ;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH},fetch = FetchType.EAGER)
    private User user;
    @OneToMany(mappedBy = "post",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List<Image> images;
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
    @OneToMany(mappedBy = "post")
    private List<Like> likes;


    @Transient
    private String imageURL;

    public void addImage(Image image) {
        if (images == null) images = new ArrayList<>();
        images.add(image);
    }

//    public void addImage(List<Image> imagess){
//        if (images == null) images=new ArrayList<>();
//        images.addAll(imagess);
//    }
}
