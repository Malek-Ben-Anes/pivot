package io.pivot.repository;

import java.util.List;

public interface TodoRepository {

    List<String> findAll();

    void delete(String todo);
}
