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

            return type.cast(
                    new Admin()
                            .withName(request.getName())
                            .withSurname(request.getSurname())
                            .withEmail(request.getEmail())
                            .withUsername(request.getUsername())
                            .withPesel(request.getPesel())
                            .withCountry(request.getCountry())
                            .withPassword(passwordEncoder.encode(request.getPassword()))
                            .withCreationDate(OffsetDateTime.now())
            );
        } else if (type.isAssignableFrom(Recruiter.class)) {
            return type.cast(
                    new Recruiter()
                            .withName(request.getName())
                            .withSurname(request.getSurname())
                            .withEmail(request.getEmail())
                            .withUsername(request.getUsername())
                            .withCountry(request.getCountry())
                            .withPesel(request.getPesel())
                            .withPassword(passwordEncoder.encode(request.getPassword()))
                            .withCreationDate(OffsetDateTime.now())
            );
        } else if (type.isAssignableFrom(RegisteredUser.class)) {
            return type.cast(
                    new RegisteredUser()
                            .withName(request.getName())
                            .withSurname(request.getSurname())
                            .withEmail(request.getEmail())
                            .withUsername(request.getUsername())
                            .withCountry(request.getCountry())
                            .withPesel(request.getPesel())
                            .withPassword(passwordEncoder.encode(request.getPassword()))
                            .withCreationDate(OffsetDateTime.now())
            );
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
