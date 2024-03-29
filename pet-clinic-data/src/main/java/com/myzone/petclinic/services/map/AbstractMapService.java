package com.myzone.petclinic.services.map;

import com.myzone.petclinic.models.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    Optional<T> findById(ID id) {
        return Optional.ofNullable(map.get(id));
    }

    T save(T object) {
        if (object != null) {
            if (object.getId() == null) {
                object.setId(getNextId());
            }
            map.put(object.getId(), object);
        } else {
            throw new RuntimeException("Object can not be null");
        }

        return object;
    }

    void deleteById(ID id) {
        this.findById(id).ifPresent(f -> delete(f));
    }

    void delete(T object) {
        map.values().removeIf(o->o.equals(object));
    }

    private Long getNextId() {
        if (map.isEmpty())
            return 1L;
        return Collections.max(map.keySet()) + 1;
    }
}
