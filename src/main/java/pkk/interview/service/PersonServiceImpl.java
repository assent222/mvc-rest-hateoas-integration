package pkk.interview.service;

import org.springframework.stereotype.Service;
import pkk.interview.domain.Person;

import java.util.*;

/**
 * Created by root on 26.01.2017.
 */

@Service
public class PersonServiceImpl implements PersonService {

    private Map<Integer, Person> repository = new HashMap<>();
    private int cnt;


    public PersonServiceImpl() {
        repository.put(1, new Person(1, "Ivan"));
        repository.put(2, new Person(2, "Petr"));
        repository.put(3, new Person(3, "Karl"));
        cnt = 3;
    }

    @Override
    public List<Person> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(repository.values()));
    }

    @Override
    public Person find(Integer id) {
        return repository.get(id);
    }

    @Override
    public Person create(Person entity) {
        entity.setId(cnt++);
        repository.put(cnt, entity);
        return entity;
    }

    @Override
    public Person update(Person entity) {
        repository.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Person delete(Person entity) {
        return repository.remove(entity.getId());
    }
}
