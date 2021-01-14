if exists(select 1 from sys.sysforeignkey where role='FK_ADMIN_GENERALIS_USER') then
    alter table Admin
       delete foreign key FK_ADMIN_GENERALIS_USER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_APPRENAN_GENERALIS_USER') then
    alter table Apprenant
       delete foreign key FK_APPRENAN_GENERALIS_USER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_APPRENAN_ASSOCIATI_SPECIALI') then
    alter table Apprenant
       delete foreign key FK_APPRENAN_ASSOCIATI_SPECIALI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_APPRENAN_ASSOCIATI_PROMOTIO') then
    alter table Apprenant
       delete foreign key FK_APPRENAN_ASSOCIATI_PROMOTIO
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_FORMATEU_GENERALIS_USER') then
    alter table Formateur
       delete foreign key FK_FORMATEU_GENERALIS_USER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_FORMATEU_ASSOCIATI_SPECIALI') then
    alter table Formateur
       delete foreign key FK_FORMATEU_ASSOCIATI_SPECIALI
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_PROMOTIO_ASSOCIATI_ANNEE') then
    alter table Promotion
       delete foreign key FK_PROMOTIO_ASSOCIATI_ANNEE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_SECRETAI_GENERALIS_USER') then
    alter table Secretaire
       delete foreign key FK_SECRETAI_GENERALIS_USER
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASSOCIAT_ASSOCIATI_ABSENCE') then
    alter table association1
       delete foreign key FK_ASSOCIAT_ASSOCIATI_ABSENCE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASSOCIAT_ASSOCIATI_FORMATEU') then
    alter table association1
       delete foreign key FK_ASSOCIAT_ASSOCIATI_FORMATEU
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASSOCIAT_ASSOCIATI_ABSENCE') then
    alter table association2
       delete foreign key FK_ASSOCIAT_ASSOCIATI_ABSENCE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASSOCIAT_ASSOCIATI_APPRENAN') then
    alter table association2
       delete foreign key FK_ASSOCIAT_ASSOCIATI_APPRENAN
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASSOCIAT_ASSOCIATI_ABSENCE') then
    alter table association3
       delete foreign key FK_ASSOCIAT_ASSOCIATI_ABSENCE
end if;

if exists(select 1 from sys.sysforeignkey where role='FK_ASSOCIAT_ASSOCIATI_SECRETAI') then
    alter table association3
       delete foreign key FK_ASSOCIAT_ASSOCIATI_SECRETAI
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Absence'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Absence
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='GENERALISATION_2_FK'
     and t.table_name='Admin'
) then
   drop index Admin.GENERALISATION_2_FK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Admin'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Admin
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Annee'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Annee
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION4_FK'
     and t.table_name='Apprenant'
) then
   drop index Apprenant.ASSOCIATION4_FK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Apprenant'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Apprenant
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION5_FK'
     and t.table_name='Formateur'
) then
   drop index Formateur.ASSOCIATION5_FK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Formateur'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Formateur
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION6_FK'
     and t.table_name='Promotion'
) then
   drop index Promotion.ASSOCIATION6_FK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Promotion'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Promotion
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='GENERALISATION_4_FK'
     and t.table_name='Secretaire'
) then
   drop index Secretaire.GENERALISATION_4_FK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Secretaire'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Secretaire
end if;

if exists(
   select 1 from sys.systable 
   where table_name='Specialite_Classe'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table Specialite_Classe
end if;

if exists(
   select 1 from sys.systable 
   where table_name='User'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table "User"
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION1_FK'
     and t.table_name='association1'
) then
   drop index association1.ASSOCIATION1_FK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='association1'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table association1
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION2_FK'
     and t.table_name='association2'
) then
   drop index association2.ASSOCIATION2_FK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='association2'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table association2
end if;

if exists(
   select 1 from sys.sysindex i, sys.systable t
   where i.table_id=t.table_id 
     and i.index_name='ASSOCIATION3_FK'
     and t.table_name='association3'
) then
   drop index association3.ASSOCIATION3_FK
end if;

if exists(
   select 1 from sys.systable 
   where table_name='association3'
     and table_type in ('BASE', 'GBL TEMP')
) then
    drop table association3
end if;

/*==============================================================*/
/* Table : Absence                                              */
/*==============================================================*/
create table Absence 
(
   idEtudiant           integer                        null,
   dateDebu             integer                        null,
   dateFin              integer                        null,
   isJustifie           integer                        null
);

/*==============================================================*/
/* Table : Admin                                                */
/*==============================================================*/
create table Admin 
(
   
);

/*==============================================================*/
/* Index : GENERALISATION_2_FK                                  */
/*==============================================================*/
create index GENERALISATION_2_FK on Admin (

);

/*==============================================================*/
/* Table : Annee                                                */
/*==============================================================*/
create table Annee 
(
   idAnnee              integer                        null,
   "date"               integer                        null
);

/*==============================================================*/
/* Table : Apprenant                                            */
/*==============================================================*/
create table Apprenant 
(
   isCurrentActif       integer                        null,
   promo                integer                        null,
   classe               integer                        null,
   specialite           varchar(254)                   null
);

/*==============================================================*/
/* Index : ASSOCIATION4_FK                                      */
/*==============================================================*/
create index ASSOCIATION4_FK on Apprenant (

);

/*==============================================================*/
/* Table : Formateur                                            */
/*==============================================================*/
create table Formateur 
(
   classe               integer                        null
);

/*==============================================================*/
/* Index : ASSOCIATION5_FK                                      */
/*==============================================================*/
create index ASSOCIATION5_FK on Formateur (

);

/*==============================================================*/
/* Table : Promotion                                            */
/*==============================================================*/
create table Promotion 
(
   idPromo              integer                        null,
   nom                  varchar(254)                   null
);

/*==============================================================*/
/* Index : ASSOCIATION6_FK                                      */
/*==============================================================*/
create index ASSOCIATION6_FK on Promotion (

);

/*==============================================================*/
/* Table : Secretaire                                           */
/*==============================================================*/
create table Secretaire 
(
   isAdmin              integer                        null
);

/*==============================================================*/
/* Index : GENERALISATION_4_FK                                  */
/*==============================================================*/
create index GENERALISATION_4_FK on Secretaire (

);

/*==============================================================*/
/* Table : Specialite_Classe                                    */
/*==============================================================*/
create table Specialite_Classe 
(
   id                   integer                        null,
   name                 varchar(254)                   null
);

/*==============================================================*/
/* Table : "User"                                               */
/*==============================================================*/
create table "User" 
(
   id                   integer                        null,
   nom                  varchar(254)                   null,
   prenom               varchar(254)                   null,
   numTel               integer                        null,
   email                varchar(254)                   null,
   password             varchar(254)                   null,
   cin                  varchar(254)                   null,
   dateNaissance        integer                        null
);

/*==============================================================*/
/* Table : association1                                         */
/*==============================================================*/
create table association1 
(
   
);

/*==============================================================*/
/* Index : ASSOCIATION1_FK                                      */
/*==============================================================*/
create index ASSOCIATION1_FK on association1 (

);

/*==============================================================*/
/* Table : association2                                         */
/*==============================================================*/
create table association2 
(
   
);

/*==============================================================*/
/* Index : ASSOCIATION2_FK                                      */
/*==============================================================*/
create index ASSOCIATION2_FK on association2 (

);

/*==============================================================*/
/* Table : association3                                         */
/*==============================================================*/
create table association3 
(
   
);

/*==============================================================*/
/* Index : ASSOCIATION3_FK                                      */
/*==============================================================*/
create index ASSOCIATION3_FK on association3 (

);

alter table Admin
   add constraint FK_ADMIN_GENERALIS_USER foreign key ()
      references "User"
      on update restrict
      on delete restrict;

alter table Apprenant
   add constraint FK_APPRENAN_GENERALIS_USER foreign key ()
      references "User"
      on update restrict
      on delete restrict;

alter table Apprenant
   add constraint FK_APPRENAN_ASSOCIATI_SPECIALI foreign key ()
      references Specialite_Classe
      on update restrict
      on delete restrict;

alter table Apprenant
   add constraint FK_APPRENAN_ASSOCIATI_PROMOTIO foreign key ()
      references Promotion
      on update restrict
      on delete restrict;

alter table Formateur
   add constraint FK_FORMATEU_GENERALIS_USER foreign key ()
      references "User"
      on update restrict
      on delete restrict;

alter table Formateur
   add constraint FK_FORMATEU_ASSOCIATI_SPECIALI foreign key ()
      references Specialite_Classe
      on update restrict
      on delete restrict;

alter table Promotion
   add constraint FK_PROMOTIO_ASSOCIATI_ANNEE foreign key ()
      references Annee
      on update restrict
      on delete restrict;

alter table Secretaire
   add constraint FK_SECRETAI_GENERALIS_USER foreign key ()
      references "User"
      on update restrict
      on delete restrict;

alter table association1
   add constraint FK_ASSOCIAT_ASSOCIATI_ABSENCE foreign key ()
      references Absence
      on update restrict
      on delete restrict;

alter table association1
   add constraint FK_ASSOCIAT_ASSOCIATI_FORMATEU foreign key ()
      references Formateur
      on update restrict
      on delete restrict;

alter table association2
   add constraint FK_ASSOCIAT_ASSOCIATI_ABSENCE foreign key ()
      references Absence
      on update restrict
      on delete restrict;

alter table association2
   add constraint FK_ASSOCIAT_ASSOCIATI_APPRENAN foreign key ()
      references Apprenant
      on update restrict
      on delete restrict;

alter table association3
   add constraint FK_ASSOCIAT_ASSOCIATI_ABSENCE foreign key ()
      references Absence
      on update restrict
      on delete restrict;

alter table association3
   add constraint FK_ASSOCIAT_ASSOCIATI_SECRETAI foreign key ()
      references Secretaire
      on update restrict
      on delete restrict;
