import java.util.Optional;
import java.util.Collection;

public interface Dao<T> {

    Optional<T> get(int id);
    Collection<T> getAll();
    int save(T t);
    void update(T t);
    void delete(T t);
}
