create extension if not exists "uuid-ossp";

create extension if not exists pgcrypto;

create table client (
  client_id  varchar(255) not null,
  name       varchar(255) not null,
  secret     varchar(255) not null,
  created_at timestamp    not null default current_timestamp,
  updated_at timestamp    null,
  version    int8         not null default 0,
  constraint pk_client primary key (client_id)
);


create table "user" (
  user_id    varchar(255) not null,
  client_id  varchar(255) not null,
  created_at timestamp    not null default current_timestamp,
  updated_at timestamp    null,
  version    int8         not null default 0,
  constraint pk_end_user primary key (user_id)
);

create table oauth_access_token
(
  token_id          character varying(256),
  token             bytea,
  authentication_id character varying(256),
  user_name         character varying(256),
  client_id         character varying(256),
  authentication    bytea,
  refresh_token     character varying(256)
);

create table oauth_refresh_token
(
  token_id       character varying(256),
  token          bytea,
  authentication bytea
);

create table billing_info
(
  billing_id uuid         not null,
  user_id    varchar(255) not null,
  constraint pk_billing_id primary key (billing_id)
);