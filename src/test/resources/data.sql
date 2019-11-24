insert into author (id, firstName, lastName) values (1, 'Федор', 'Достоевский');
insert into author (id, firstName, lastName) values (2, 'Лев', 'Толстой');
insert into author (id, firstName, lastName) values (3, 'TestDelFirstname', 'TestDelLastname');
insert into author (id, firstName, lastName) values (4, 'TestUpdFirstname', 'TestUpdLastname');

insert into genre(id, genreName) values (1, 'Роман');
insert into genre(id, genreName) values (2, 'Повесть');
insert into genre(id, genreName) values (3, 'TestDel');
insert into genre(id, genreName) values (4, 'TestUpd');

insert into book(id, authorId, genreId, title) values (1, 1, 1, 'Преступление и наказание');
insert into book(id, authorId, genreId, title) values (2, 1, 1, 'TestDel');