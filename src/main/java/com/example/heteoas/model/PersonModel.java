package com.example.heteoas.model;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@Data
public class PersonModel extends RepresentationModel<PersonModel> {
    int id;
    String firstName, lastName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonModel that = (PersonModel) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName);
    }
}
