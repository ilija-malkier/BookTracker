package com.booktracker.booktracker.repositories;

import com.booktracker.booktracker.model.Author;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CassandraRepository<Author,String> {
}
