package kalchenko.bank.repositories;

import kalchenko.bank.entity.Entity;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EntityRepository {

    private final Map<Long, Entity> objects = new LinkedHashMap<>();
    private long currentId = 1L;

    /**
     * @param object Добавляет object в репозиторий и возвращает добавленный объект,
     *               если object не был равен null, иначе возвращает null.
     */
    public Entity add(Entity object) {

        if (object == null) {
            return null;
        }

        object.setId(currentId++);
        this.objects.put(object.getId(), object);
        return object;

    }

    /**
     * @return Возвращает истину, если при удалении объект был не null,
     * иначе возвращает ложь.
     */
    public boolean deleteById(Long id) {

        if (!this.objects.containsKey(id)) {
            return false;
        }

        objects.remove(id);
        return true;

    }

    /**
     * Возвращает объект по id, который хранится в репозитории.
     */
    public Entity findById(Long id) {

        return this.objects.get(id);

    }

    /**
     * Возвращает список объектов, которые хранятся в репозитории.
     */
    public List<Entity> findAll() {

        return this.objects.values().stream().toList();

    }

    /**
     * Если объект существует, то обновляет его и возвращает его,
     * иначе возвращает null.
     */
    public Entity update(Entity object) {

        if (object == null || !this.objects.containsKey(object.getId())) {
            return null;
        }

        this.objects.replace(object.getId(), object);
        return findById(object.getId());

    }
}
