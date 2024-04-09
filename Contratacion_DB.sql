create database Contratacion_Riwi;
use Contratacion_Riwi;

	CREATE TABLE empresa(
    id_empresa int(11) primary key auto_increment,
    nombre varchar(50) not null, 
    sector varchar(50) not null,
    contacto varchar(50) not null
    );
    
    CREATE TABLE coder (
    id_coder int(11) primary key auto_increment,
    nombre varchar(50) not null,
	apellidos varchar(50) not null,
    documento varchar(50) unique not null,
    cohorte int(11) not null,
    cv text 
    );
    
    CREATE TABLE vacante (
    id_vacante int(11) primary key auto_increment,
    titulo varchar(50) not null,
    descripcion text,
    duracion varchar(50) not null,
    estado varchar(50) not null check (estado in("activo","inactivo")),
    empresa_id_fk int(11),
    constraint fk_id_empresa foreign key (empresa_id_fk) references empresa (id_empresa) on delete cascade
    );
    
    CREATE TABLE contratacion(
    id_contratacion int (11) primary key auto_increment,
    fecha_aplicacion timestamp default current_timestamp,
    estado varchar(25) not null check (estado in("activo","inactivo")) ,
    salario decimal(10,2) not null,
    coder_id_fk int (11),
    vacante_id_fk int (11), 
    constraint fk_id_vacante foreign key (vacante_id_fk) references vacante (id_vacante) on delete cascade,
    constraint fk_id_coder foreign key (coder_id_fk) references coder (id_coder) on delete cascade
    );
    
    INSERT INTO empresa (nombre,sector,contacto) VALUES ("epm","energia","123");
        INSERT INTO empresa (nombre,sector,contacto) VALUES ("sura","salud","123");
            INSERT INTO empresa (nombre,sector,contacto) VALUES ("argos","construccion","123");
    

alter table vacante add tecnologia varchar(50) not null;

alter table coder add clan varchar(50) not null;
    
    
    