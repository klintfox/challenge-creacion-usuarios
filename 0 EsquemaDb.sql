create table user ( 
   id  varchar(255)  primary key not null,
   name varchar(50) not null, 
   email varchar(255) not null, 
   password varchar(20)  not null, 
   created timestamp null, 
   modified timestamp null,
   last_login timestamp null,
   is_active null,
   token varchar(300) not null
);

create table user_phones ( 
   id INT primary key not null,
   number  int null, 
   city_code int null, 
   country_code int null, 
   fk_user varchar(300),
  foreign key (fk_user ) references user(id)
);