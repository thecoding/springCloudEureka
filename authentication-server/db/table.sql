create table clientdetails
(
    appId                  varchar(128)  not null
        primary key,
    resourceIds            varchar(256)  null,
    appSecret              varchar(256)  null,
    scope                  varchar(256)  null,
    grantTypes             varchar(256)  null,
    redirectUrl            varchar(256)  null,
    authorities            varchar(256)  null,
    access_token_validity  int           null,
    refresh_token_validity int           null,
    additionalInformation  varchar(4096) null,
    autoApproveScopes      varchar(256)  null
);

create table oauth_access_token
(
    token_id          varchar(256) null,
    token             blob         null,
    authentication_id varchar(128) not null
        primary key,
    user_name         varchar(256) null,
    client_id         varchar(256) null,
    authentication    blob         null,
    refresh_token     varchar(256) null
);

create table oauth_approvals
(
    userId         varchar(256) null,
    clientId       varchar(256) null,
    scope          varchar(256) null,
    status         varchar(10)  null,
    expiresAt      timestamp    null,
    lastModifiedAt timestamp    null
);

create table oauth_client_details
(
    client_id               varchar(128)  not null
        primary key,
    resource_ids            varchar(256)  null,
    client_secret           varchar(256)  null,
    scope                   varchar(256)  null,
    authorized_grant_types  varchar(256)  null,
    web_server_redirect_uri varchar(256)  null,
    authorities             varchar(256)  null,
    access_token_validity   int           null,
    refresh_token_validity  int           null,
    additional_information  varchar(4096) null,
    autoapprove             varchar(256)  null
);

create table oauth_client_token
(
    token_id          varchar(256) null,
    token             blob         null,
    authentication_id varchar(128) not null
        primary key,
    user_name         varchar(256) null,
    client_id         varchar(256) null
);

create table oauth_code
(
    code           varchar(256) null,
    authentication blob         null
);

create table oauth_refresh_token
(
    token_id       varchar(256) null,
    token          blob         null,
    authentication blob         null
);