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

@Table(value = "author_by_id")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    //ordinal je ako imamo vise pk kolona pa koja ima prednost
    //partition hocu da ova kolona bude partition key,svaka kolona ovoga e imati novu particiju jer za svaki novi pk se stvara nova particija
    @PrimaryKeyColumn(name = "author_id",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String id;

    @Column("author_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String name;

    @Column("personal_name")
    @CassandraType(type = CassandraType.Name.TEXT)
    private String personalName;

}
