create table if not exists Taco_Order (
                                          id serial primary key, -- corrected identity column syntax
                                          delivery_name varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city varchar(50) not null,
    delivery_state char(2) not null, -- char is enough for state abbreviation
    delivery_zip varchar(10) not null,
    cc_number char(16) not null, -- char for fixed length fields
    cc_expiration char(5) not null,
    cc_cvv char(3) not null,
    placed_at timestamp not null
    );

create table if not exists Taco (
    id serial primary key, -- corrected identity column syntax
     name varchar(50) not null,
    taco_order bigint not null,
    taco_order_key bigint not null,
    created_at timestamp not null,
    foreign key (taco_order) references Taco_Order(id) -- added foreign key
    );


create table if not exists Ingredient (
                                          id varchar(4) primary key, -- added primary key
    name varchar(25) not null,
    type varchar(10) not null
    );


create table if not exists Ingredient_Ref (
    ingredient varchar(4) not null,
    taco bigint not null,
    taco_key bigint not null,
    foreign key (ingredient) references Ingredient(id) -- added foreign key

    );

