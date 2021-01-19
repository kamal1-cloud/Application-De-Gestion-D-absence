CREATE database gestionDabsence;
use gestionDabsence;





/*==============================================================*/
/* Table : Promotion                                            */
/*==============================================================*/
create table Promotion 
(
   idPromo BIGINT NOT NULL AUTO_INCREMENT,
   annee YEAR,
   PRIMARY KEY (idPromo)
);


/*==============================================================*/
/* Table : Classe                                     */
/*==============================================================*/
create table Classe
(
	
   idClasse BIGINT NOT NULL AUTO_INCREMENT,
   name varchar(254) NOT NULL,
   annee YEAR,
 PRIMARY KEY (idClasse)
);

/*==============================================================*/
/* Table :Specialite                         */
/*==============================================================*/
create table Specialite
(
  
    idSpecialite BIGINT NOT NULL AUTO_INCREMENT,
   name varchar(254) NOT NULL,
   annee YEAR,
 PRIMARY KEY (idSpecialite)
  
);

/*==============================================================*/
/* Table : Admin                                                */
/*==============================================================*/
create table Admin 
(
   idAdmin BIGINT AUTO_INCREMENT,
   nom VARCHAR(50) NOT NULL,
   prenom VARCHAR(50) NOT NULL,
   numTele VARCHAR(12),
   email VARCHAR(100),
   password VARCHAR(255),
   CIN VARCHAR(12),
   dateNaissance DATE,
   PRIMARY KEY (idAdmin)
   
);

/*==============================================================*/
/* Table : Secretaire                                           */
/*==============================================================*/
create table Secretaire 
(
   
   idSecretaire BIGINT NOT NULL AUTO_INCREMENT,
   nom VARCHAR(50) NOT NULL,
   prenom VARCHAR(50) NOT NULL,
   numTele VARCHAR(12),
   email VARCHAR(60),
   password VARCHAR(255),
   CIN VARCHAR(12),
   dateNaissance DATE,
   PRIMARY KEY (idSecretaire)
   
);

/*==============================================================*/
/* Table : Formateur                                            */
/*==============================================================*/
create table Formateur 
(
   idFormateur BIGINT NOT NULL AUTO_INCREMENT,
   nom VARCHAR(50) NOT NULL,
   prenom VARCHAR(50) NOT NULL,
   numTele VARCHAR(12),
   email VARCHAR(100),
   password VARCHAR(255),
   CIN VARCHAR(12),
   dateNaissance DATE,
   classe BIGINT,
   PRIMARY KEY (idFormateur),
   FOREIGN KEY(classe) REFERENCES Classe(idClasse)
);
/*==============================================================*/
/* Table : Apprenant                                    */
/*==============================================================*/
create table Apprenant 
(
   idApprenant BIGINT NOT NULL AUTO_INCREMENT,
   nom VARCHAR(50) NOT NULL,
   prenom VARCHAR(50) NOT NULL,
   numTele VARCHAR(12),
   email VARCHAR(100),
   password VARCHAR(255),
   CIN VARCHAR(12),
   dateNaissance DATE,
   idPromo BIGINT,
   isCurrentActif BOOLEAN NOT NULL,
   classe BIGINT,
   specialite BIGINT,
    PRIMARY KEY (idApprenant),
    FOREIGN KEY(idPromo) REFERENCES Promotion(idPromo),
     FOREIGN KEY(classe) REFERENCES Classe(idClasse),
      FOREIGN KEY(specialite) REFERENCES Specialite(idSpecialite)

);
/*==============================================================*/
/* Table : Absence                                      */
/*==============================================================*/
create table Absence
(
  
   dateDebu  DATETIME NOT NULL,
   dateFin DATETIME NOT null,
   isJustifie BOOLEAN not null,
   retard BOOLEAN,
   idApprenant BIGINT,
   FOREIGN KEY (idApprenant) REFERENCES Apprenant(idApprenant)
);

