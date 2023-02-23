package com.booktracker.booktracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.util.List;

@Table(value = "book_by_id")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {

    @Id
    @PrimaryKeyColumn(name = "book_id",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String id;


    @Column("author_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;

    @Column("book_description")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String description;


    @Column("published_date")
    @CassandraType(type = CassandraType.Name.DATE)
    private LocalDate publishedDate;


    @Column("cover_ids")
    //ovo je list,a svaki element je text,ovako radimo sa listom
    @CassandraType(type = CassandraType.Name.LIST,typeArguments = CassandraType.Name.TEXT)
    private List<String> coversIds;


    @Column("author_names")
     @CassandraType(type = CassandraType.Name.LIST,typeArguments = CassandraType.Name.TEXT)
    private List<String> authorNames;

    @Column("author_id")
    @CassandraType(type = CassandraType.Name.LIST,typeArguments = CassandraType.Name.TEXT)
    private List<String> authorId;



}
