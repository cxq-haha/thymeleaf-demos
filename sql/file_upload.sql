create table cxq_demo.file_process_task
(
    id           bigint auto_increment
        primary key,
    task_id      varchar(50)                         not null,
    progress     int                                 null,
    file_name    varchar(255)                        null,
    file_size    bigint                              null,
    operator     varchar(50)                         null,
    extra_info   text                                null,
    gmt_created  timestamp default CURRENT_TIMESTAMP null,
    gmt_modified timestamp default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP
);

