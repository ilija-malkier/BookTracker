package com.booktracker.booktracker.application_runner;

import com.booktracker.booktracker.model.Author;
import com.booktracker.booktracker.model.Book;
import com.booktracker.booktracker.repositories.AuthorRepository;
import com.booktracker.booktracker.repositories.BookRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MyAppRunner  implements ApplicationRunner {


    //lazy load bean
    @Lazy
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Value("${datadump.location.author}")
    private String authorDumpLocation;

    @Value("${datadump.location.works}")
    private String worksDumpLocation;
    @Override
    public void run(ApplicationArguments args) throws Exception {
       // initAuthors();
        //initWorks();
    }

    public void initAuthors(){

        Path path = Paths.get(authorDumpLocation);

        try(Stream<String> lines= Files.lines(path)){
            lines.forEach(line->{
                //Reand and parse the line
                int jsonStart=line.indexOf("{");
                int jsonEnd=line.indexOf("}");
                String jsonString=line.substring(jsonStart);
                JSONObject jsonObject=new JSONObject(jsonString);
                //Construct Author object
                Author author=new Author();
                author.setName(jsonObject.optString("name"));
                author.setPersonalName(jsonObject.optString("personal_name"));
                author.setId(jsonObject.optString("key").replace("/authors/",""));


                //Persist
                authorRepository.save(author);
                System.out.println("Adding " + author.getName() );

            });
        } catch (IOException e) {


        }
    }
    public void initWorks(){

        Path path=Paths.get(worksDumpLocation);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

        try(Stream<String> lines=Files.lines(path)){
            lines.forEach(line->{
                int jsonStart=line.indexOf("{");
                String jsonString=line.substring(jsonStart);
                JSONObject jsonObject=new JSONObject(jsonString);
                Book book=new Book();
                book.setId(jsonObject.optString("key").replace("/works/",""));
                book.setName(jsonObject.optString("title"));
                JSONObject description = jsonObject.optJSONObject("description");
                if(description!=null)
                    book.setDescription(description.optString("value"));

               JSONObject publiskedObj=jsonObject.optJSONObject("created");
               if(publiskedObj!=null)
                   book.setPublishedDate(LocalDate.parse(publiskedObj.optString("value"),dateTimeFormatter));

                JSONArray coversJSONArr=jsonObject.optJSONArray("covers");
                if(coversJSONArr!=null){
                    List<String> coverIds=new ArrayList<>();
                    for(int i=0;i<coversJSONArr.length();i++){
                        coverIds.add(coversJSONArr.get(i).toString());
                    }
                    book.setCoversIds(coverIds);
                }

                JSONArray authorsJSONArr=jsonObject.optJSONArray("authors");
                 if(authorsJSONArr!=null){
                    List<String> authorIds=new ArrayList<>();
                    for(int i=0;i<authorsJSONArr.length();i++){
                        String authorId=authorsJSONArr.getJSONObject(i).getJSONObject("author").getString("key").replace("/authors/","");

                        authorIds.add(authorId);
                    }
                    book.setAuthorId(authorIds);
                    List<String> names = authorIds.stream().map(id -> authorRepository.findById(id))
                            .map(optionalAuthor -> {
                                if (!optionalAuthor.isPresent()) return "Unknown Author";
                                return optionalAuthor.get().getName();
                            }).collect(Collectors.toList());
                    ;
                    book.setAuthorNames(names);
                }
                System.out.println("Saved " + book.getName());
                bookRepository.save(book);

            });
        }catch (Exception e){

            System.out.println(e.getMessage());
        }
    }
}
