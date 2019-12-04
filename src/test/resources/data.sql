insert into author (first_name, last_name) values ('Федор', 'Достоевский');
insert into author (first_name, last_name) values ('Лев', 'Толстой');
insert into author (first_name, last_name) values ('TestDelFirstname', 'TestDelLastname');
insert into author (first_name, last_name) values ('TestUpdFirstname', 'TestUpdLastname');

insert into genre(genre_name) values ('Роман');
insert into genre(genre_name) values ('Повесть');
insert into genre(genre_name) values ('TestDel');
insert into genre(genre_name) values ('TestUpd');

insert into book(author_id, genre_id, title) values (1, 1, 'Преступление и наказание');
insert into book(author_id, genre_id, title) values (1, 1, 'TestDel');