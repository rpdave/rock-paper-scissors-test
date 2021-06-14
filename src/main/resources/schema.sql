drop table if exists players;

create table players (id bigint not null auto_increment, games_draw integer not null, games_lost integer not null, games_played integer not null, games_won integer not null, password varchar(255), roles varchar(255), username varchar(255), primary key (id)) engine=InnoDB;