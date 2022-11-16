package kalchenko.bank.entity;

/**
 * Класс сущности для хранения в репозитории
 */
public interface Entity {

    /**
     * @return Возвращает идентификатор сущности
     */
    Long getId();

    /**
     * @param id идентификатор который будет установлен для сущности
     */
    void setId(Long id);

}
