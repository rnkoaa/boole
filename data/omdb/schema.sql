create TABLE crews (
    id integer not null default nextval('crews_id_seq'::regclass),
    name varchar,
    created_at timestamp not null,
    updated_at timestamp not null,
    PRIMARY KEY (id)
);
CREATE UNIQUE INDEX crews_pkey ON crews (id)

create TABLE genres (
    id integer not null default nextval('genres_id_seq'::regclass),
    name varchar,
    created_at timestamp not null,
    updated_at timestamp not null,
    PRIMARY KEY (id)
);
CREATE UNIQUE INDEX genres_pkey ON genres (id)

create TABLE genres_movies (
    genre_id integer,
    movie_id integer
);

CREATE INDEX index_genres_movies_on_genre_id ON genres_movies (genre_id);
CREATE INDEX index_genres_movies_on_movie_id ON genres_movies (movie_id)

create TABLE movies (
    id integer not null default nextval('movies_id_seq'::regclass),
    name varchar,
    synopsis text,
    created_at timestamp not null,
    updated_at timestamp not null,
    country varchar,
    "year" integer,
    review double precision,
    rating varchar,
    runtime integer,
    "language" varchar,
    release_date date,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX movies_pkey ON movies (id)

create TABLE roles (
    id integer not null default nextval('roles_id_seq'::regclass),
    movie_id integer,
    crew_id integer,
    job varchar,
    created_at timestamp not null,
    updated_at timestamp not null,
    PRIMARY KEY (id),
    FOREIGN KEY (movie_id) REFERENCES movies (id),
    FOREIGN KEY (crew_id) REFERENCES crews (id)
);

CREATE UNIQUE INDEX roles_pkey ON roles (id);
CREATE INDEX index_roles_on_movie_id ON roles (movie_id);
CREATE INDEX index_roles_on_crew_id ON roles (crew_id);
