create sequence "USER_id_seq"
    as integer;

alter sequence "USER_id_seq" owner to postgres;

create sequence "ROLE_id_seq"
    as integer;

alter sequence "ROLE_id_seq" owner to postgres;

create sequence "PRIVILAGE_id_seq"
    as integer;

alter sequence "PRIVILAGE_id_seq" owner to postgres;

create sequence "ADDRESS_id_seq"
    as integer;

alter sequence "ADDRESS_id_seq" owner to postgres;

create sequence "STATUS_DICT_id_seq"
    as integer;

alter sequence "STATUS_DICT_id_seq" owner to postgres;

create sequence "PACKAGE_id_seq"
    as integer;

alter sequence "PACKAGE_id_seq" owner to postgres;

create sequence "ORDER_id_seq"
    as integer;

alter sequence "ORDER_id_seq" owner to postgres;

create sequence "REVIEW_id_seq"
    as integer;

alter sequence "REVIEW_id_seq" owner to postgres;

create table app_user
(
    id            integer default nextval('"USER_id_seq"'::regclass) not null
        constraint "USER_pkey"
            primary key,
    first_name    varchar(255)                                       not null,
    last_name     varchar(255)                                       not null,
    mail          varchar(255)                                       not null
        constraint "USER_mail_key"
            unique,
    password      varchar(255)                                       not null,
    mobile        varchar(20),
    date_of_birth date                                               not null
);

alter table app_user
    owner to postgres;

alter sequence "USER_id_seq" owned by app_user.id;

create table role
(
    id   integer default nextval('"ROLE_id_seq"'::regclass) not null
        constraint "ROLE_pkey"
            primary key,
    name varchar(255)                                       not null
        constraint "ROLE_name_key"
            unique
);

alter table role
    owner to postgres;

alter sequence "ROLE_id_seq" owned by role.id;

create table user_role
(
    user_id integer not null
        constraint "USER_ROLE_user_id_fkey"
            references app_user,
    role_id integer not null
        constraint "USER_ROLE_user_id_fkey1"
            references role,
    constraint "USER_ROLE_pkey"
        primary key (user_id, role_id)
);

alter table user_role
    owner to postgres;

create table privilage
(
    id             integer default nextval('"PRIVILAGE_id_seq"'::regclass) not null
        constraint "PRIVILAGE_pkey"
            primary key,
    privilege_name varchar(255)                                            not null
        constraint "PRIVILAGE_privilege_name_key"
            unique
);

alter table privilage
    owner to postgres;

alter sequence "PRIVILAGE_id_seq" owned by privilage.id;

create table role_privilage
(
    role_id      integer not null
        constraint "ROLE_PRIVILAGE_role_id_fkey"
            references role,
    privilage_id integer not null
        constraint "ROLE_PRIVILAGE_privilage_id_fkey"
            references privilage,
    constraint "ROLE_PRIVILAGE_pkey"
        primary key (role_id, privilage_id)
);

alter table role_privilage
    owner to postgres;

create table address
(
    id          integer default nextval('"ADDRESS_id_seq"'::regclass) not null
        constraint "ADDRESS_pkey"
            primary key,
    user_id     integer                                               not null
        constraint "ADDRESS_user_id_fkey"
            references app_user,
    street      varchar(255)                                          not null,
    postal_code varchar(20)                                           not null,
    home_number varchar(10)                                           not null,
    door_key    varchar(20),
    is_main     boolean default false
);

alter table address
    owner to postgres;

alter sequence "ADDRESS_id_seq" owned by address.id;

create table status_dict
(
    id   integer default nextval('"STATUS_DICT_id_seq"'::regclass) not null
        constraint "STATUS_DICT_pkey"
            primary key,
    name varchar(255)                                              not null
        constraint "STATUS_DICT_name_key"
            unique
);

alter table status_dict
    owner to postgres;

alter sequence "STATUS_DICT_id_seq" owned by status_dict.id;

create table package
(
    id     integer default nextval('"PACKAGE_id_seq"'::regclass) not null
        constraint "PACKAGE_pkey"
            primary key,
    height numeric                                               not null,
    width  numeric                                               not null,
    depth  numeric                                               not null,
    weight numeric                                               not null
);

alter table package
    owner to postgres;

alter sequence "PACKAGE_id_seq" owned by package.id;

create table "order"
(
    id           integer default nextval('"ORDER_id_seq"'::regclass) not null
        constraint "ORDER_pkey"
            primary key,
    sender_id    integer                                             not null
        constraint "ORDER_sender_id_fkey"
            references app_user,
    recipient_id integer                                             not null
        constraint "ORDER_recipient_id_fkey"
            references app_user,
    courier_id   integer
        constraint "ORDER_courier_id_fkey"
            references app_user,
    price        numeric                                             not null,
    package_id   integer                                             not null
        constraint "ORDER_package_id_fkey"
            references package,
    status_id    integer                                             not null
        constraint "ORDER_status_id_fkey"
            references status_dict
);

alter table "order"
    owner to postgres;

alter sequence "ORDER_id_seq" owned by "order".id;

create table review
(
    id       integer default nextval('"REVIEW_id_seq"'::regclass) not null
        constraint "REVIEW_pkey"
            primary key,
    user_id  integer                                              not null
        constraint "REVIEW_user_id_fkey"
            references app_user,
    order_id integer                                              not null
        constraint "REVIEW_order_id_fkey"
            references "order",
    comment  text,
    value    integer                                              not null
);

alter table review
    owner to postgres;

alter sequence "REVIEW_id_seq" owned by review.id;

