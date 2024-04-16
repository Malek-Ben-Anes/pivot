package com.ricardo.ricardo.repository;

import java.util.List;

public interface TodoRepository {

    List<String> findAll();

    void delete(String todo);
}
