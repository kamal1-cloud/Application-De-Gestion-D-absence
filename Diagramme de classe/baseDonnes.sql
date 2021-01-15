/*==============================================================*/
/* Table : Absence                                              */
/*==============================================================*/
create table Absence 
(
   idEtudiant           integer                        null,
   dateDebu             integer                        null,
   dateFin              integer                        null,
   isJustifie           smallint                       null,
   retard               smallint                       null
);

/*==============================================================*/
/* Table : Admin                                                */
/*==============================================================*/
create table Admin 
(
   idAdmin              integer                        null
);

/*==============================================================*/
/* Table : Apprenant                                            */
/*==============================================================*/
create table Apprenant 
(
   isCurrentActif       smallint                       null,
   promo                integer                        null,
   classe               integer                        null,
   specialite           varchar(254)                   null,
   idApprenant          integer                        null
);

/*==============================================================*/
/* Table : Formateur                                            */
/*==============================================================*/
create table Formateur 
(
   classe               integer                        null,
   idFormateur          integer                        null
);

/*==============================================================*/
/* Table : Promotion                                            */
/*==============================================================*/
create table Promotion 
(
   idPromo              integer                        null,
   annee                integer                        null
);

/*==============================================================*/
/* Table : Secretaire                                           */
/*==============================================================*/
create table Secretaire 
(
   idSecretaire         integer                        null
);

/*==============================================================*/
/* Table : SpecialiteClasse                                     */
/*==============================================================*/
create table SpecialiteClasse 
(
   id                   integer                        null,
   name                 varchar(254)                   null,
   isClasse             smallint                       null
);

/*==============================================================*/
/* Table : "User"                                               */
/*==============================================================*/
create table "User" 
(
   idUser               integer                        null,
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
