

create table public.USERS (
    ID_USER UUID not null default random_uuid(),
    USER_NAME varchar(50) not null,
    constraint PK_USER primary key(ID_USER)
);

create table public.POKEMON (
    ID_POKEMON Integer not null,
    POKEMON_NAME varchar(100) not null,
    POKEMON_URL varchar(256) not null,
    constraint PK_POKEMON primary key(ID_POKEMON)
);


create table public.FAVORITE_ANNOTATION (
    ID_USER UUID not null ,
    ID_POKEMON Integer not null,
    constraint PK_FAVORITE_ANNOTATION primary key(ID_USER, ID_POKEMON)
);

alter table public.FAVORITE_ANNOTATION add FOREIGN KEY (ID_USER) REFERENCES public.USERS(ID_USER);
alter table public.FAVORITE_ANNOTATION add FOREIGN KEY (ID_POKEMON) REFERENCES public.POKEMON(ID_POKEMON);










