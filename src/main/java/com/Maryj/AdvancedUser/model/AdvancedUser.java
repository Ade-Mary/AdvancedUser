package com.Maryj.AdvancedUser.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

//@Data // it is a combination of both @setter and @getter
@Setter
@Getter

//@NoArgsConstructor
//@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "advanced_user")
public class AdvancedUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id" , nullable = false)
    private Long id;

    @Column(name = "full_name")
    @Length(min = 6, message = "Enter your first and last name")
    private  String fullName;

    @Email(message = "Enter a valid email")
    private String email;

    @Length(min = 4)
    private  String gender;

    @Pattern(regexp = "[0-9]{11}")
    @Column(name = "phone_number")
    private String PhoneNumber;

    public AdvancedUser( String fullName, String email, String gender, String phoneNumber) {
        this.fullName = fullName;
        this.email = email;
        this.gender = gender;
        PhoneNumber = phoneNumber;
    }
}
