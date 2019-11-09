package DBO;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryDBO<T> {
    public List<T> getAll() throws SQLException;
    public T getUnique(int objectID) throws SQLException;
    public boolean insert(T object) throws SQLException;
    public boolean update(T object);
    public boolean delete(T object);
}
