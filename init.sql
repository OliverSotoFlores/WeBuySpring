create table admin(
    admin_id int primary key,
    admin_name varchar(20) not null,
    email varchar(25) not null unique,
    admin_password varchar(30) not null
);

create table product_category (
    product_category_id int primary key,
    category_name varchar(15) not null unique,
    category_description varchar(30),
    category_image_path varchar(255)
);

create table product (
    product_id int primary key auto_increment not null,
    product_name varchar(255) not null,
    product_company varchar(30) not null,
    product_price decimal(20,2) not null,
    product_description varchar(90),
    product_image_path varchar(255),
    product_category_id int not null,
    foreign key(product_category_id) references product_category(product_category_id)
);

create table promotion_event (
    promotion_event_id int primary key,
    promotion_event_name varchar(15) not null,
    promotion_event_description varchar(30) not null,
    promotion_event_start_date date not null,
    promotion_event_end_date date not null,
    promotion_event_status enum('valid','expired','cancelled') not null,
    admin_id int not null,
    foreign key(admin_id) references admin(admin_id)
);

create table coupon (
    coupon_id int primary key,
    coupon_name varchar(15) not null unique,
    coupon_type enum('open','n_open') not null,
    coupon_discount float not null,
    promotion_event_id int not null,
    foreign key(promotion_event_id) references promotion_event(promotion_event_id),
    product_category_id int,
    foreign key(product_category_id) references product_category(product_category_id)
);

create table registered_user (
    user_id int primary key auto_increment,
    user_email varchar(25) not null unique,
    user_password varchar(30) not null,
    user_fullname varchar(20) not null,
    user_address varchar(40) not null,
    user_contact_number varchar(30) not null
);

create table shopping_cart(
    shopping_cart_id int primary key,
    creation_date date,
    cart_status enum('complete','in_session') not null,
    user_id int not null,
    foreign key(user_id) references registered_user(user_id)
);

create table shopping_product_details (
    shopping_product_details_id int primary key auto_increment,
    quantity int not null,
    shopping_cost decimal(20,2),
    cost_after_applying_coupon decimal(20,2),
    product_id int not null,
    foreign key(product_id) references product(product_id),
    shopping_cart_id int not null,
    foreign key (shopping_cart_id) references shopping_cart(shopping_cart_id)
);

create table payment(
    payment_id int primary key auto_increment,
    open_coupon int,
    shopping_final_amount decimal(20,2),
    payment_status enum('complete','in_negotiation') not null,
    shopping_cart_id int not null, 
    foreign key(shopping_cart_id) references shopping_cart(shopping_cart_id)
);

insert into admin values (1, 'Alan', 'alan@gmail.com', 'pass123');
insert into admin values (2, 'Oliver', 'oliver@gmail.com', 'pass123');
insert into admin values (3, 'Andre', 'andre@gmail.com', 'pass123');
insert into admin values (4, 'Carlos', 'carlos@gmail.com', 'pass123');
insert into admin values (5, 'Alejandro', 'alejandro@gmail.com', 'pass123');
insert into admin values (6, 'Hazzim', 'hazzim@gmail.com', 'pass123');

insert into product_category values (1, 'Electronics', 'Electronics category','headphones');
insert into product_category values (2, 'Videogames', 'Videogames category', 'sports_esports');
insert into product_category values (3, 'Kitchen', 'Kitchen category', 'blender');
insert into product_category values (4, 'Clothing', 'Clothing category', 'checkroom');
insert into product_category values (5, 'Toys', 'Toys category', 'smart_toy');

insert into promotion_event values (1, 'Christmas2021', 'Christmas of 2021', '2021-12-21', '2022-01-21', 'valid', 1);
insert into promotion_event values (2, 'Christmas2020', 'Christmas of 2020', '2020-12-21', '2021-01-21', 'expired', 1);
insert into promotion_event values (3, 'Halloween2021', 'Halloween of 2021', '2021-10-21', '2021-11-06', 'Expired', 2);
insert into promotion_event values (4, 'NewYear2022', 'New Year 2022', '2021-12-31', '2022-01-01', 'Expired', 4);
insert into promotion_event values (5, 'HotSale', 'Hot Sale of June', '2021-06-01', '2021-07-01', 'Cancelled', 5);

insert into coupon values (1, 'C2021', 'Open', 20, 1, 1);
insert into coupon values (2, 'C2020', 'Open', 20, 2, 1);
insert into coupon values (3, 'H2021', 'n_open', 15, 3, 4);
insert into coupon values (4, 'NY2021', 'Open', 30, 4, 2);
insert into coupon values (5, 'HS2021', 'Open', 60, 5, 3);

/*insert into product values (1, "LG TV 4K", "LG", 2000, "Biggest TV on store", "test", 1);
insert into product values (2, "LG TV 2K", "LG", 1500, "Medium sized smart TV", "test", 1);
insert into product values (3, "LG TV 2K", "LG", 1500, "Medium sized smart TV", "test", 1);
insert into product values (4, "White T-Shirt", "Dopp", 10, "Simple fast fashion white t-shirt", "test", 4);
insert into product values (5, "White T-Shirt", "Dopp", 10, "Simple fast fashion white t-shirt", "test", 4);
insert into product values (6, "White T-Shirt in page 1", "Dopp", 10, "Simple fast fashion white t-shirt in page 2", "test", 4);
insert into product values (7, "White T-Shirt in page 1", "Dopp", 10, "Simple fast fashion white t-shirt in page 2", "test", 4);
insert into product values (8, "White T-Shirt in page 1", "Dopp", 10, "Simple fast fashion white t-shirt in page 2", "test", 4);
insert into product values (9, "White T-Shirt in page 2", "Dopp", 10, "Simple fast fashion white t-shirt in page 2", "test", 4);
insert into product values (10, "White T-Shirt in page 2", "Dopp", 10, "Simple fast fashion white t-shirt in page 2", "test", 4);
insert into product values (11, "White T-Shirt in page 2", "Dopp", 10, "Simple fast fashion white t-shirt in page 3", "test", 4);
insert into product values (12, "White T-Shirt in page 2", "Dopp", 10, "Simple fast fashion white t-shirt in page 3", "test", 4);
insert into product values (13, "White T-Shirt in page 2", "Dopp", 10, "Simple fast fashion white t-shirt in page 3", "test", 4);
insert into product values (14, "White T-Shirt in page 2", "Dopp", 10, "Simple fast fashion white t-shirt in page 3", "test", 4);
insert into product values (15, "White T-Shirt in page 2", "Dopp", 10, "Simple fast fashion white t-shirt in page 3", "test", 4);
insert into product values (16, "White T-Shirt in page 2", "Dopp", 10, "Simple fast fashion white t-shirt in page 3", "test", 4);
insert into product values (17, "White T-Shirt in page 3", "Dopp", 10, "Simple fast fashion white t-shirt in page 4", "test", 4);
*/
insert into registered_user values (1, 'test@gmail.com', 'test123', 'Test Subject', 'Mexico', '123456789');
insert into registered_user values (2, 'test2@gmail.com', 'test123', 'Test Subject', 'Mexico', '123456789');

insert into shopping_cart values(1, '2022-01-18', 'in_session', 1);
insert into shopping_cart values(2, '2022-01-18', 'in_session', 2);

insert into shopping_product_details values (1, 1, 2000, 2000, 1, 1);
insert into shopping_product_details values (2, 1, 1500, 1500, 2, 1);
insert into shopping_product_details values (3, 1, 15, 15, 4, 2);
