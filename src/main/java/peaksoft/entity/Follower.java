package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "followers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id_gen",sequenceName = "follower_seq",allocationSize = 1)
public class Follower extends BaseEntity {
    @ElementCollection
    private List<Long> subscribers;
    @ElementCollection
    private List<Long> subscription;
    @OneToOne(mappedBy = "follower",cascade = CascadeType.PERSIST)
    private User user;
}
