use cinema;

-- persons inserts --
select *from Person

insert into Person (FirstName, LastName, DateBorn) values ('Mateusz', 'Walczak', '1998-07-30')
insert into Person (FirstName, LastName, DateBorn) values ('Konrad', 'Kajszczak', '1998-09-02')
insert into Person (FirstName, LastName, DateBorn) values ('Angelina', 'Malina', '1974-05-13')
insert into Person (FirstName, LastName, DateBorn) values ('Brad', 'Kit', '1956-09-11')
insert into Person (FirstName, LastName, DateBorn) values ('Micha³', '¯ebrowski', '1975-11-28')
insert into Person (FirstName, LastName, DateBorn) values ('Geaorge', 'Clooney', '1972-09-11')
insert into Person (FirstName, LastName, DateBorn) values ('Morgan', 'Freeman', '1968-11-25')
insert into Person (FirstName, LastName, DateBorn) values ('Maciek', 'Nowak', '1990-03-23')
insert into Person (FirstName, LastName, DateBorn) values ('Aleksander', 'Wielki', '1982-06-13')
insert into Person (FirstName, LastName, DateBorn) values ('Katarzyna', 'Kowalska', '1949-01-03')

-- person type inserts --
select *from DICTPersonType

insert into DICTPersonType (Name) values ('Director')
insert into DICTPersonType (Name) values ('Actor')

-- checking results --
select *from Person
select *from DICTPersonType