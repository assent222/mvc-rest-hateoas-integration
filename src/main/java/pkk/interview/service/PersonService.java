package pkk.interview.service;

import pkk.interview.domain.Person;

import java.util.List;

/**
 * Created by root on 26.01.2017.
 */


public interface PersonService {

    List<Person> findAll();

    Person find(Integer id);

    Person create(Person entity);

    Person delete(Person entity);

    Person update(Person entity);
}
