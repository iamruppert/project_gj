package com.lukasz.project.database.auth;

import com.lukasz.project.database.request.CompanyRequest;
import com.lukasz.project.database.request.RegisterRequest;
import com.lukasz.project.model.Admin;
import com.lukasz.project.model.Company;
import com.lukasz.project.model.Recruiter;
import com.lukasz.project.model.RegisteredUser;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Component
public class Extractor {

    private final PasswordEncoder passwordEncoder;

    public <T> T createActorFromRequest(RegisterRequest request, Class<T> type) {
        if (type.isAssignableFrom(Admin.class)) {
            Admin admin = new Admin();
            admin.setName(request.getName());
            admin.setSurname(request.getSurname());
            admin.setEmail(request.getEmail());
            admin.setUsername(request.getUsername());
            admin.setPesel(request.getPesel());
            admin.setCountry(request.getCountry());
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
            admin.setCreationDate(OffsetDateTime.now());
            return type.cast(admin);

        } else if (type.isAssignableFrom(Recruiter.class)) {
            Recruiter recruiter = new Recruiter();
            recruiter.setName(request.getName());
            recruiter.setSurname(request.getSurname());
            recruiter.setEmail(request.getEmail());
            recruiter.setUsername(request.getUsername());
            recruiter.setPesel(request.getPesel());
            recruiter.setCountry(request.getCountry());
            recruiter.setPassword(passwordEncoder.encode(request.getPassword()));
            recruiter.setCreationDate(OffsetDateTime.now());
            return type.cast(recruiter);
        } else if (type.isAssignableFrom(RegisteredUser.class)) {
            RegisteredUser registeredUser = new RegisteredUser();
            registeredUser.setName(request.getName());
            registeredUser.setSurname(request.getSurname());
            registeredUser.setEmail(request.getEmail());
            registeredUser.setUsername(request.getUsername());
            registeredUser.setPesel(request.getPesel());
            registeredUser.setCountry(request.getCountry());
            registeredUser.setPassword(passwordEncoder.encode(request.getPassword()));
            registeredUser.setCreationDate(OffsetDateTime.now());
            return type.cast(registeredUser);
        } else {
            throw new IllegalArgumentException("Unsupported type: " + type.getSimpleName());
        }
    }
    public Company createCompanyFromRequest(CompanyRequest request){
        return new Company()
                .withName(request.getName())
                .withAddress(request.getAddress())
                .withWebsite(request.getWebsite());
    }

}
