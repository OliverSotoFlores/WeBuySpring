create table admin(
    admin_id int primary key,
    admin_name varchar(20) not null,
    email varchar(25) not null unique,
    admin_password varchar(30) not null
);

create table product_category (
    product_category_id int primary key,
    category_name varchar(15) not null unique,
    category_description varchar(30)
);

create table product (
    product_id int primary key,
    product_name varchar(20) not null,
    product_company varchar(20) not null,
    product_price decimal(20,2) not null,
    product_description varchar(30),
    product_image_path varchar(50),
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
    user_id int primary key,
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
    shopping_product_details_id int primary key,
    quantity int not null,
    shopping_cost decimal(20,2),
    cost_after_applying_coupon decimal(20,2),
    product_id int not null unique,
    foreign key(product_id) references product(product_id),
    shopping_cart_id int not null,
    foreign key (shopping_cart_id) references shopping_cart(shopping_cart_id)
);

create table payment(
    payment_id int primary key,
    open_coupon int,
    shopping_final_amount decimal(20,2),
    payment_status enum('complete','in_negotiation') not null,
    shopping_cart_id int not null, 
    foreign key(shopping_cart_id) references shopping_cart(shopping_cart_id)
);