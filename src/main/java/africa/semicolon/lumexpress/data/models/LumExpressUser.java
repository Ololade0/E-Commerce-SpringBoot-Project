package africa.semicolon.lumexpress.data.models;


import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class LumExpressUser {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String imageUrl;
    private boolean isEnabled;
    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Notification> messages = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<Authority> authorities=new HashSet<>();
}
