package to.link.urlshortener.service.util;

import java.util.Collection;

public interface CrudMethods<Entity, ID> {
    Entity findById(ID id);

    Collection<Entity> findAll();

    Entity save(Entity entity);

    Entity update(Entity entity);

    void deleteById(ID id);
}
