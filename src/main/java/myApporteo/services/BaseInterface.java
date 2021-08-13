package myApporteo.services;




import java.util.List;

public interface BaseInterface<T> {

    T save(T t);

    List<T> findAll();

    T findById(Integer id);

}

