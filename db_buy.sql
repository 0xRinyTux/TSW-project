drop database if exists BuyandPlay;
create database BuyandPlay;
use BuyandPlay;

create table Chiave(
id_chiave char(6),
gioco varchar (45) references Gioco(titolo_gioco),
fattura int references Fattura(id_fattura), 
keyGioco varchar (40),
 primary key( id_chiave, gioco, fattura)
 )
 ;

create table Collocamento(
 id_gioco varchar(45) references Gioco(titolo_gioco),
  id_fattura int references Fattura(id_fattura),
  pezzi int,
primary key(id_gioco,id_fattura)
);


create table Fattura(
  id_fattura int auto_increment,
  data_acquisto date ,
  nickname varchar(45) references Cliente(nickname),
  carta varchar(45) ,
  numero_carta varchar(40) ,
  primary key (id_fattura)
);

create table Gioco
(
    titolo_gioco       varchar(45)     not null 
      primary key,
    prezzo_gioco       double          null,
    descrizione_gioco  varchar(5000)   null,
    anno_gioco         char(4)         null,
    console            varchar(45)     null,
    softwarehouse      varchar(45)     null,
    url                varchar(300)    not null,
    in_sconto          tinyint(1)      not null,
    prezzo_scont_gioco double          null,
    genere             varchar(45)     null,
    in_vendita         tinyint(1)      null,
    nome_immagine      varchar(40)     not null,
    numero_vendite     int default 100 not null,
    constraint Gioco_nome_immagine_uindex
        unique (nome_immagine)
);

create table Cliente
(
    nickname               varchar(45)  not null
        primary key,
    passwordCliente        varchar(256) not null,
    email                  varchar(45)  not null,
    nome                   varchar(20)  not null,
    cognome                varchar(20)  not null,
    data_di_nascita        date         null,
    tipo_carta             varchar(20)  not null,
    numero_carta           varchar(30)  null,
    indirizzo_fatturazione varchar(50)  null,
    constraint email
        unique (email)
);

Create table Newsletter (
  email_news varchar(45) primary key
);
