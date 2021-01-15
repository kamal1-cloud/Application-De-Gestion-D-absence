/*==============================================================*/
/* Table : Absence                                              */
/*==============================================================*/
create table Absence 
(
   idEtudiant           integer                        not null,
   idUser               integer                        null,
   dateDebu             integer                        not null,
   dateFin              integer                        not null,
   isJustifie           smallint                       not null,
   retard               smallint                       not null,
   constraint PK_ABSENCE primary key clustered (idEtudiant)
);

/*==============================================================*/
/* Table : Admin                                                */
/*==============================================================*/
create table Admin 
(
   idUser               integer                        not null,
   idAdmin              integer                        not null,
   constraint PK_ADMIN primary key clustered (idUser, idAdmin),
   constraint AK_ID_ADMIN_ADMIN unique ()
);

/*==============================================================*/
/* Table : Apprenant                                            */
/*==============================================================*/
create table Apprenant 
(
   idUser               integer                        not null,
   idPromo              integer                        null,
   isCurrentActif       smallint                       not null,
   promo                integer                        not null,
   classe               integer                        not null,
   specialite           varchar(254)                   not null,
   idApprenant          integer                        not null,
   constraint PK_APPRENANT primary key clustered (idUser)
);

/*==============================================================*/
/* Table : Formateur                                            */
/*==============================================================*/
create table Formateur 
(
   idUser               integer                        not null,
   id                   integer                        not null,
   classe               integer                        not null,
   idFormateur          integer                        not null,
   constraint PK_FORMATEUR primary key clustered (idUser, id, idFormateur)
);

/*==============================================================*/
/* Table : Promotion                                            */
/*==============================================================*/
create table Promotion 
(
   idPromo              integer                        not null,
   annee                integer                        not null,
   constraint PK_PROMOTION primary key clustered (idPromo)
);

/*==============================================================*/
/* Table : Secretaire                                           */
/*==============================================================*/
create table Secretaire 
(
   idUser               integer                        null,
   idSecretaire         integer                        null
);

/*==============================================================*/
/* Table : SpecialiteClasse                                     */
/*==============================================================*/
create table SpecialiteClasse 
(
   id                   integer                        not null,
   name                 varchar(254)                   not null,
   isClasse             smallint                       not null,
   constraint PK_SPECIALITECLASSE primary key clustered (id)
);

/*==============================================================*/
/* Table : "User"                                               */
/*==============================================================*/
create table "User" 
(
   idUser               integer                        not null,
   nom                  varchar(254)                   not null,
   prenom               varchar(254)                   not null,
   numTel               integer                        not null,
   email                varchar(254)                   not null,
   password             varchar(254)                   not null,
   cin                  varchar(254)                   not null,
   dateNaissance        integer                        not null,
   constraint PK_USER primary key clustered (idUser)
);

/*==============================================================*/
/* Table : association1                                         */
/*==============================================================*/
create table association1 
(
   idUser               integer                        null,
   id                   integer                        null,
   idFormateur          integer                        null,
   idEtudiant           integer                        null
);

/*==============================================================*/
/* Table : association3                                         */
/*==============================================================*/
create table association3 
(
   idEtudiant           integer                        null
);

alter table Absence
   add constraint FK_ABSENCE_ASSOCIATI_APPRENAN foreign key (idUser)
      references Apprenant (idUser)
      on update restrict
      on delete restrict;

alter table Admin
   add constraint FK_ADMIN_GENERALIS_USER foreign key (idUser)
      references "User" (idUser)
      on update restrict
      on delete restrict;

alter table Apprenant
   add constraint FK_APPRENAN_GENERALIS_USER foreign key (idUser)
      references "User" (idUser)
      on update restrict
      on delete restrict;

alter table Apprenant
   add constraint FK_APPRENAN_ASSOCIATI_SPECIALI foreign key ()
      references SpecialiteClasse (id)
      on update restrict
      on delete restrict;

alter table Apprenant
   add constraint FK_APPRENAN_ASSOCIATI_PROMOTIO foreign key (idPromo)
      references Promotion (idPromo)
      on update restrict
      on delete restrict;

alter table Formateur
   add constraint FK_FORMATEU_GENERALIS_USER foreign key (idUser)
      references "User" (idUser)
      on update restrict
      on delete restrict;

alter table Formateur
   add constraint FK_FORMATEU_ASSOCIATI_SPECIALI foreign key (id)
      references SpecialiteClasse (id)
      on update restrict
      on delete restrict;

alter table Secretaire
   add constraint FK_SECRETAI_GENERALIS_USER foreign key (idUser)
      references "User" (idUser)
      on update restrict
      on delete restrict;

alter table association1
   add constraint FK_ASSOCIAT_ASSOCIATI_ABSENCE foreign key (idEtudiant)
      references Absence (idEtudiant)
      on update restrict
      on delete restrict;

alter table association1
   add constraint FK_ASSOCIAT_ASSOCIATI_FORMATEU foreign key (idUser, id, idFormateur)
      references Formateur (idUser, id, idFormateur)
      on update restrict
      on delete restrict;

alter table association3
   add constraint FK_ASSOCIAT_ASSOCIATI_ABSENCE foreign key (idEtudiant)
      references Absence (idEtudiant)
      on update restrict
      on delete restrict;

alter table association3
   add constraint FK_ASSOCIAT_ASSOCIATI_SECRETAI foreign key ()
      references Secretaire
      on update restrict
      on delete restrict;
