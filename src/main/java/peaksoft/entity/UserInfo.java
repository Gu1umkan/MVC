package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_infos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "base_id_gen",sequenceName = "user_info_seq",allocationSize = 1)
public class UserInfo extends BaseEntity {
    @Column(name = "full_name")
    private String fullName;
    private String biography;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String image;
    @OneToOne(mappedBy = "userInfo",cascade = CascadeType.PERSIST)
    private User user;
}
