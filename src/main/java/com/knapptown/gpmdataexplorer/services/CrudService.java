package com.knapptown.gpmdataexplorer.services;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Validated
public interface CrudService<Model> {

    List<Model> getAll();

    Model getById(@Positive Long id);

    Model create(@Valid Model model);

    Model update(@Positive Long id, @Valid Model model);

    void delete(@Positive Long id);
}
