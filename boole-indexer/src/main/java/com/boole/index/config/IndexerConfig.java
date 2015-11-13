package com.boole.index.config;

import com.boole.index.service.IndexService;
import com.boole.index.util.DateTimeTypeConverter;
import com.boole.common.BooleCommonApplicationConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created on 11/3/2015.
 */
@SpringBootApplication
@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"com.boole.common.service",
        "com.boole.index",
        "com.boole.config"})
@Import({BooleCommonApplicationConfig.class})
class IndexerConfig implements CommandLineRunner {

    @Autowired
    IndexService indexService;

   /* @Autowired
    Indexer<IndexFilm> filmIndexer;*/

    /*@Autowired
    FilmRepository filmRepository;*/

    @Bean
    public TaskScheduler scheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }

    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(10);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public JestClient jestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder("http://localhost:9200")
                .multiThreaded(true)
                .gson(gson())
                .build());
        return factory.getObject();
    }

    public static void main(String[] args) {
        SpringApplication.run(IndexerConfig.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        /*Film film = filmRepository.findFilmWithDetails(1);
        System.out.println(film.toString());
        filmIndexer.indexItem(new IndexFilm(film));*/
        /*List<Film> films = filmRepository.findAllWithDetails();
        List<IndexFilm> indexFilms = films.
                stream()
                .map(IndexFilm::new)
                .collect(Collectors.toList());
        filmIndexer.bulkIndexItems(indexFilms);*/

        int itemsPerPage = 20;
        //Page<Film> filmPage = filmRepository.findFilmWithDetailsPaged(new PageRequest(initialPage, itemsPerPage));

        //int numberOfPages = filmPage.getTotalPages();
      /*  do {
            System.out.println("Processing Page: ++>" + filmPage.getNumber());
            List<Film> films = filmPage.getContent();
            List<IndexFilm> indexFilms = films.
                    stream()
                    .map(IndexFilm::new)
                    .collect(Collectors.toList());
            filmIndexer.bulkIndexItems(indexFilms);

            initialPage += 1;
            filmPage = filmRepository.findFilmWithDetailsPaged(new PageRequest(initialPage, itemsPerPage));
            //System.out.println("Film Page: -> " + filmPage.getNumber() + " " + filmPage.getNumberOfElements());
        } while (initialPage < numberOfPages);*/
       /* for (int index = 0; index < 50; index++) {
            Page<Film> filmPage = filmRepository.findFilmWithDetailsPaged(new PageRequest(index, itemsPerPage));
            System.out.println("Processing Page: ++>" + filmPage.getNumber());
            List<Film> films = filmPage.getContent();
            List<IndexFilm> indexFilms = films.
                    stream()
                    .map(IndexFilm::new)
                    .collect(Collectors.toList());
            filmIndexer.bulkIndexItems(indexFilms);
            Thread.sleep(2000);
        }*/

      /*  List<Film> films = filmRepository.findAllWithDetails();
        List<IndexFilm> indexFilms = films.
                stream()
                .map(IndexFilm::new)
                .collect(Collectors.toList());
        filmIndexer.bulkIndexItems(indexFilms);*/
    }


    @Bean
    Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeTypeConverter());
        return gsonBuilder.create();
    }
}
