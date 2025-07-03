create database sistema_rrhh;
use sistema_rrhh;

create table riesgos_puesto(
    id_riesgo int not null primary key auto_increment,
    nombre_riesgo varchar(10) not null
);

insert into riesgos_puesto (nombre_riesgo) values
('Alto'),
('Medio'),
('Bajo');

create table departamentos(
    id_departamento int not null primary key AUTO_INCREMENT,
    nombre_departamento varchar(50) not null,
    eliminado bit null
);

create table puestos(
    id_puesto int not null primary key auto_increment,
    nombre_puesto varchar(30) not null,
    descripcion varchar(255) null,
    salario_minimo decimal(10,2) null,
    salario_maximo decimal(10,2) null,
    riesgo_id int not null,
    departamento_id int not null,
    vacantes int default 1,
    eliminado bit null
);

alter table puestos
add constraint fk_riesgo_puesto
foreign key (riesgo_id) references riesgos_puesto(id_riesgo);

alter table puestos
add constraint fk_departamento_puesto
foreign key (departamento_id) references departamentos(id_departamento);

create table candidatos(
    id_candidato int not null primary key auto_increment,
    cedula varchar(11) not null,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    email varchar(255) not null,
    puesto_id int not null, -- puesto al que aspira
    salario decimal(10,2) not null, -- salario al que aspira
    eliminado bit null
);

alter table candidatos
add constraint fk_puesto_candidato
foreign key (puesto_id) references puestos(id_puesto);

create table recomendaciones( -- personas que recomiendan a los candidatos
    id_recomendacion int not null primary key auto_increment,
    nombre_recomendador varchar(50) not null,
    empresa varchar(50) null,
    puesto varchar(50) null,
    contacto varchar(80) not null,
    candidato_id int not null,
    eliminado bit null
);

alter table recomendaciones
add constraint fk_recomendacion_candidato
foreign key (candidato_id) references candidatos(id_candidato);

create table competencias(
    id_competencia int not null primary key auto_increment,
    nombre_competencia varchar(30) not null,
    descripcion varchar(150) null,
    candidato_id int not null,
    eliminado bit null
);

alter table competencias
add constraint fk_competencia_candidato
foreign key (candidato_id) references candidatos(id_candidato);

create table idiomas(
    id_idioma int not null primary key auto_increment,
    nombre_idioma varchar(15) not null,
    eliminado bit null
);

create table nivel_idioma(
    id_nivel int not null primary key auto_increment,
    nombre_nivel varchar(8) not null
);

create table idiomas_candidatos(
    id_idioma_candidato int not null primary key auto_increment,
    candidato_id int not null,
    idioma_id int not null,
    nivel_id int not null
);

alter table idiomas_candidatos
add constraint fk_idiomas_candidatos_candidato
foreign key (candidato_id) references candidatos(id_candidato);

alter table idiomas_candidatos
add constraint fk_idiomas_candidatos_idioma
foreign key (idioma_id) references idiomas(id_idioma);

alter table idiomas_candidatos
add constraint fk_idiomas_candidatos_nivel
foreign key (nivel_id) references nivel_idioma(id_nivel);

create table niveles_capacitacion(
    id_nivel int not null primary key auto_increment,
    nombre_nivel varchar(30) not null
);

insert into niveles_capacitacion (nombre_nivel) values
('Grado'),
('Post-grado'),
('Maestria'),
('Doctorado'),
('Tecnico'),
('Gestion');

create table capacitaciones(
    id_capacitacion int not null primary key auto_increment,
    nombre_capacitacion varchar(30) not null,
    descripcion varchar(255) null,
    nivel_id int not null,
    fecha_desde date null,
    fecha_hasta date null,
    institucion varchar(100) not null,
    candidato_id int not null,
    eliminado bit null
);

alter table capacitaciones
add constraint fk_nivel_capacitacion
foreign key (nivel_id) references niveles_capacitacion(id_nivel);

alter table capacitaciones
add constraint fk_capacitacion_candidato
foreign key (candidato_id) references candidatos(id_candidato);

create table empleados(
    id_empleado int not null primary key auto_increment,
    cedula varchar(15) not null,
    nombre_empleado varchar(50) not null,
    apellido_empleado varchar(50) not null,
    fecha_ingreso date,
    departamento_id int not null,
    puesto varchar(30),
    eliminado bit null
);

create table usuarios(
    id_usuario int not null primary key auto_increment,
    nombre_usuario varchar(255) not null,
    contrasenia varchar(255) not null,
    tipo_usuario varchar(10) not null,
    empleado_id int null,
    candidato_id int null,
    eliminado bit null
);