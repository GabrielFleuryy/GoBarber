create table users(
    user_id SERIAL PRIMARY KEY,
    login VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) not null
);

create table barbers(

    barber_id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    instagram VARCHAR(100) NOT NULL UNIQUE,
    birthday DATE,
    phone_number VARCHAR(20) UNIQUE,
    style VARCHAR(50),
    role VARCHAR(20),
    updated_at DATE,
    created_at DATE,

 FOREIGN KEY (user_id) REFERENCES users(user_id)
);

create table customers(

    customer_id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    birthday DATE,
    phone_number VARCHAR(20) UNIQUE,
    role VARCHAR(20),
    updated_at DATE,
    created_at DATE,

 FOREIGN KEY (user_id) REFERENCES users(user_id)
);