/*==============================================================*/
/* Table : Absence                                              */
/*==============================================================*/
create table Absence 
(
   idEtudiant           integer                        not null,
   dateDebu             integer                        not null,
   dateFin              integer                         not null,
   isJustifie           smallint                       not null,
   retard               smallint                       not null
);

/*==============================================================*/
/* Table : Admin                                                */
/*==============================================================*/
create table Admin 
(
   idAdmin              integer                        not null,
);

/*==============================================================*/
/* Table : Apprenant                                            */
/*==============================================================*/
create table Apprenant 
(
   isCurrentActif       smallint                       not null,
   promo                integer                        not null,
   classe               integer                        not null,
   specialite           varchar(254)                   not null,
   idApprenant          integer                        not null
);

/*==============================================================*/
/* Table : Formateur                                            */
/*==============================================================*/
create table Formateur 
(
   classe               integer                        not null,
   idFormateur          integer                        not null
);

/*==============================================================*/
/* Table : Promotion                                            */
/*==============================================================*/
create table Promotion 
(
   idPromo              integer                        not null,
   annee                integer                        not null
);

/*==============================================================*/
/* Table : Secretaire                                           */
/*==============================================================*/
create table Secretaire 
(
   idSecretaire         integer                        not null);

/*==============================================================*/
/* Table : SpecialiteClasse                                     */
/*==============================================================*/
create table SpecialiteClasse 
(
   id                   integer                        not null,
   name                 varchar(254)                   not null,
   isClasse             smallint                       not null
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
   dateNaissance        integer                        not null
);

/*==============================================================*/
/* Table : association1                                         */
/*==============================================================*/
create table association1 
(
   
);

/*==============================================================*/
/* Table : association3                                         */
/*==============================================================*/
create table association3 
(
   
);

alter table Absence
   add constraint FK_ABSENCE_ASSOCIATI_APPRENAN foreign key ()
      references Apprenant
      on update restrict
      on delete restrict;

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
      references SpecialiteClasse
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
      references SpecialiteClasse
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
