create table file_upload_record
(
   id                   bigint(20) unsigned not null auto_increment comment '主键id',
   file_type             varchar(30) not null comment '文件类型',
   business_type          varchar(30) not null comment '上传业务场景',
    file_url           varchar(300) not null comment '文件在服务器地址',
    create_by          varchar(60) not null comment '创建人(文件上传人)',
    update_by          varchar(60) not null comment '最后更新人',
   create_time          timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
   update_time          timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
    is_delete            tinyint(1) unsigned not null default 0 comment '是否删除',
   primary key (id),
   index idx_file_url_file_type_business_type_is_delete (file_url,file_type, usiness_type,is_delete)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT='文件上传记录表';