-- create database cinemaMainDB;
-- use cinemaMainDB;

-- select *from Movie;
-- select *from DICTMovieType;
-- select *from DICTMovieState;
-- select *from DICTPersonType;
-- select *from Person;
-- select *from MoviePersonPersonType;

--------------------------------------------------- dictonary inserts ------------------------------------------------------
use IO
-- 1) Movie Type inserts --

insert into DICTMovieType values ('Action')
insert into DICTMovieType values ('Animation')
insert into DICTMovieType values ('Comedy')
insert into DICTMovieType values ('Crime')
insert into DICTMovieType values ('Drama')
insert into DICTMovieType values ('Fantasy')
insert into DICTMovieType values ('Historical')
insert into DICTMovieType values ('Horror')
insert into DICTMovieType values ('Romance')
insert into DICTMovieType values ('Science Fiction')
insert into DICTMovieType values ('Thriller')
insert into DICTMovieType values ('Western')
insert into DICTMovieType values ('Other')

-- 2) Movie State inserts

insert into DICTMovieState values ('Upcoming')
insert into DICTMovieState values ('Current')
insert into DICTMovieState values ('Expired')

--3) Person Type inserts

insert into DICTPersonType values ('Director')
insert into DICTPersonType values ('Actor')
insert into DICTPersonType values ('Screenwriter')
insert into DICTPersonType values ('Editor')
insert into DICTPersonType values ('Camera Operator')

--------------------------------------------------- main inserts ------------------------------------------------------

-- 1) Movie inserts --

insert into Movie values (1, 0, 1, 5, 2, 'Titanic', 'Di Caprio first success', '3:20:00');
insert into Movie values (1, 1, 0, 1, 1, 'Fast and furious', 'Too fast to catch', '2:15:00');
insert into Movie values (1, 0, 0, 10, 3, 'Black panther', 'Marvel popular hit', '1:56:00');
insert into Movie values (1, 1, 0, 2, 2, 'Smolin the king', 'About the whitest of ppl', '1:30:00');
insert into Movie values (1, 0, 1, 6, 2, 'Women on FTIMS', 'Powerfull and dangerous', '2:37:00');
insert into Movie values (1, 1, 0, 11, 1, 'Final judgement', 'Final round with Ewka', '1:54:00');
insert into Movie values (1, 1, 1, 12, 3, 'WEEIA vs FTIMS', 'Only one true win(n)er', '1:40:00');
insert into Movie values (1, 0, 0, 8, 2, 'Electro exam', 'Rand(time(NULL))', '2:27:00');
insert into Movie values (1, 0, 1, 4, 2, 'Hope dies last', 'Java 1.2 with prof. Niewiadomski', '2:45:00');
insert into Movie values (1, 1, 0, 9, 1, 'Here we go again', '2020 sysop editon', '3:03:00');
insert into Movie values (1, 1, 0, 2, 1, 'WFTIMS true heros', 'Mega saved another student', '1:41:00');
insert into Movie values (1, 1, 0, 3, 3, 'Its good that you remember', 'S. Sciezko warm-up', '1:30:00');
insert into Movie values (1, 0, 1, 4, 3, 'First but not last', 'XML exam', '1:42:00');
insert into Movie values (1, 1, 0, 3, 2, '...yessss, ... of course', 'Electro logic gates lesson', '1:35:00');
insert into Movie values (1, 1, 1, 9, 3, 'Works like a charm', 'First test suit on object oriented programming', '2:15:00');
insert into Movie values (1, 0, 0, 7, 3, 'Never ending story', 'Mrs Ochelska is not here', '2:20:00');
insert into Movie values (1, 0, 1, 6, 1, 'Too good to be true', 'Kacpro will be alone today', '1:29:00');
insert into Movie values (1, 1, 0, 8, 2, 'I will not let you...', 'SQL exam', '2:51:00');
insert into Movie values (0, 1, 0, 7, 2, 'Bad decisions', 'White Sysops and Shining Kompo', '1:50:00');
insert into Movie values (1, 1, 0, 2, 1, 'Slowly but surely', 'Kamil Winer walk', '2:04:00');
insert into Movie values (1, 1, 0, 10, 3, 'Its never too late', 'Trying too start doing anything for studies', '2:13:00');
insert into Movie values (0, 1, 0, 13, 2, 'Blind in elevator', 'Is that 3rd floor?', '1:44:00');

-- 2) Person inserts --

insert into Person (FirstName, LastName, DateBorn) values ('Mateusz', 'Walczak', '1982-07-30')
insert into Person (FirstName, LastName, DateBorn) values ('Konrad', 'Kajszczak', '1938-09-02')
insert into Person (FirstName, LastName, DateBorn) values ('Brad', 'Pitt', '1974-05-13')
insert into Person (FirstName, LastName, DateBorn) values ('Morgan', 'Freeman', '1956-09-11')
insert into Person (FirstName, LastName, DateBorn) values ('Michal', 'Zebrowski', '1975-11-28')
insert into Person (FirstName, LastName, DateBorn) values ('Geaorge', 'Clooney', '1972-09-11')
insert into Person (FirstName, LastName, DateBorn) values ('Angelina', 'Jolie', '1968-11-25')
insert into Person (FirstName, LastName, DateBorn) values ('Leonardo', 'Di Caprio', '1990-03-23')
insert into Person (FirstName, LastName, DateBorn) values ('Piotr', 'Adamczyk', '1982-06-13')
insert into Person (FirstName, LastName, DateBorn) values ('Katarzyna', 'Kowalska', '1949-01-03')

-- 3) MoviePersonPerosnType inserts 

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (1, 1, 3)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (1, 2, 4)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (1, 2, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (1, 3, 6)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (1, 5, 2)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (2, 1, 1)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (2, 2, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (2, 2, 9)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (2, 4, 3)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (2, 3, 6)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (3, 1, 1)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (3, 2, 4)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (3, 2, 5)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (3, 4, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (3, 3, 2)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (4, 1, 4)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (4, 2, 3)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (4, 2, 1)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (4, 3, 5)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (4, 5, 2)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (5, 1, 2)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (5, 1, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (5, 2, 3)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (5, 4, 5)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (5, 3, 4)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (6, 1, 10)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (6, 1, 9)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (6, 2, 8)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (6, 2, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (6, 3, 2)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (6, 4, 3)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (7, 1, 8)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (7, 2, 8)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (7, 4, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (7, 3, 5)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (8, 1, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (8, 2, 8)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (8, 2, 9)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (8, 5, 5)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (8, 2, 2)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (9, 1, 10)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (9, 1, 3)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (9, 2, 9)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (9, 4, 5)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (9, 3, 2)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (10, 1, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (10, 2, 10)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (10, 2, 8)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (10, 2, 9)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (10, 3, 2)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (11, 1, 4)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (11, 2, 3)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (11, 2, 6)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (11, 3, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (11, 5, 5)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (12, 1, 1)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (12, 2, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (12, 2, 9)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (12, 3, 5)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (12, 4, 6)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (13, 1, 1)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (13, 2, 4)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (13, 2, 5)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (13, 4, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (13, 3, 2)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (14, 1, 4)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (14, 2, 3)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (14, 2, 1)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (14, 3, 5)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (14, 5, 2)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (15, 1, 2)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (15, 1, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (15, 2, 3)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (15, 4, 5)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (15, 3, 4)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (16, 1, 10)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (16, 1, 9)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (16, 2, 8)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (16, 2, 7)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (17, 1, 8)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (17, 2, 8)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (17, 3, 6)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (17, 4, 8)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (18, 1, 8)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (18, 2, 3)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (18, 2, 9)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (18, 5, 5)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (18, 2, 2)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (19, 1, 10)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (19, 1, 3)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (19, 2, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (19, 3, 5)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (19, 5, 2)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (20, 1, 9)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (20, 2, 10)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (20, 2, 8)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (20, 2, 9)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (20, 3, 2)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (21, 1, 10)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (21, 2, 10)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (21, 2, 4)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (21, 3, 5)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (21, 4, 2)

insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (22, 1, 6)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (22, 2, 7)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (22, 3, 1)
insert into MoviePersonPersonType (MovieId, PersonTypeId, PersonId) values (22, 4, 2)
