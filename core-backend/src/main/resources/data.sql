
drop table if exists CaseRecord;

create table CaseRecord (
    id int auto_increment primary key,
    caseRefNum varchar(50) not null,
    createdDate datetime not null,
    name varchar(100) null,
    primaryMobile varchar(100) null,
    primaryChannel varchar(100) null,
    viberId varchar(100) null,
    status varchar(50) null,
    remarks varchar(4000) null,
    channels varchar(200) null
);

drop table if exists TheMessage;

create table TheMessage (
    id int auto_increment primary key,
    createdDate datetime not null,
    caseRecordId int not null,
    caseRefNum varchar(50) not null,
    raw varchar(4000) not null,
    channel varchar(50) not null,
    msgSid varchar(100) not null,
    chatName varchar(200) null,
    fromSid varchar(200) null,
    fromUser varchar(200) null,
    toUser varchar(200) null,
    avatar varchar(200) null,
    message varchar(4000) null,
    messageType varchar(50),
    CONSTRAINT `fk_TheMessage_CaseRecord` FOREIGN KEY (`caseRecordId`) REFERENCES `CaseRecord` (`id`)
);