insert into authors (id, firstName, lastName) values (1, 'Федор', 'Достоевский');
insert into authors (id, firstName, lastName) values (2, 'Лев', 'Толстой');
insert into authors (id, firstName, lastName) values (3, 'TestDelFirstname', 'TestDelLastname');
insert into authors (id, firstName, lastName) values (4, 'TestUpdFirstname', 'TestUpdLastname');

insert into genres(id, genreName) values (1, 'Роман');
insert into genres(id, genreName) values (2, 'Повесть');
insert into genres(id, genreName) values (3, 'TestDel');
insert into genres(id, genreName) values (4, 'TestUpd');

insert into books(id, authorId, genreId, title) values (1, 1, 1, "Преступление и наказание");