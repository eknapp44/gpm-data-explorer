package com.knapptown.gpmdataexplorer.mappers;

import java.util.List;

public interface BasicMapper<Model, Entity> {

    Model mapEntityToModel(Entity entity);

    List<Model> mapEntitiesToModels(List<Entity> entities);

    Entity mapModelToEntity(Model model);

    List<Entity> mapModelsToEntities(List<Model> models);
}
