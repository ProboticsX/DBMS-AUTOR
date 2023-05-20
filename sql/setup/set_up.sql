drop table serveRequest;
drop table mechanicAvailability;
drop table slots;
drop table customerRequest;
drop table requestedServices;
drop table serviceRequest;
drop table carOwner;
drop table cars;
drop table userCustomer;
drop table customer;
drop table serviceProvided;
drop table maintenance;
drop table repair;
drop table services;
drop table mechanicWorkingHours;
drop table mechanic;
drop table receptionist;
drop table manager;
drop table model;
drop table userEmployee;
drop table pendingRequest;
drop table employee;
drop table serviceCenter;
drop table users;

create table users(
    username varchar(20),
    password varchar(20),
    role varchar(20),
    primary key (username),
    CHECK (role IN ('Admin', 'Manager', 'Customer', 'Receptionist','Mechanic'))
);

CREATE TABLE serviceCenter(
    scId VARCHAR(10),
    address VARCHAR(50) NOT NULL,
    city VARCHAR(20) NOT NULL,
    state VARCHAR(20) NOT NULL,
    zip varchar(10) not null,
    phoneNo VARCHAR(10),
    status varchar(10) DEFAULT 'Pending',
    mngrId VARCHAR(10) NOT NULL,
    minWage number not null,
    maxWage number not null,
    openOnSat varchar(1) default 'N',
    PRIMARY KEY (scId)
);

CREATE TABLE employee(
    eId VARCHAR(10),
    scId VARCHAR(10),
    name VARCHAR(30) NOT NULL,
    phone VARCHAR(10),
    email VARCHAR(30),
    address VARCHAR(50),
    PRIMARY KEY (eId, scId),
    FOREIGN KEY (scId) REFERENCES serviceCenter (scId)
);

CREATE TABLE userEmployee(
    username varchar(20),
    eId VARCHAR(10) NOT NULL,
    scId VARCHAR(10) NOT NULL,
    PRIMARY KEY (username),
    FOREIGN KEY (username) REFERENCES users(username),
    Foreign Key (eId, scId) REFERENCES employee(eId, scId)
);

CREATE TABLE model(
    mId integer,
    make VARCHAR(10) NOT NULL,
    PRIMARY KEY (mId)
);


CREATE TABLE manager(
    eId VARCHAR(10),
    scId VARCHAR(10),
    annualSal NUMBER NOT NULL,
    PRIMARY KEY (eId, scId),
    FOREIGN KEY (eId, scId) REFERENCES employee (eId, scId)
);

CREATE TABLE receptionist(
    eId VARCHAR(10),
    scId VARCHAR(10),
    annualSal NUMBER NOT NULL,
    PRIMARY KEY (eId, scId),
    FOREIGN KEY (eId, scId) REFERENCES employee (eId, scId)
);

CREATE TABLE mechanic(
    eId VARCHAR(10),
    scId VARCHAR(10),
    hrRate NUMBER NOT NULL,
    PRIMARY KEY (eId, scId),
    FOREIGN KEY (eId, scId) REFERENCES employee (eId, scId)
);

CREATE TABLE mechanicWorkingHours(
    eId VARCHAR(10),
    scId VARCHAR(10),
    weekId NUMBER(2, 0),
    hrsWorked integer not null,
    PRIMARY KEY (eId, scId, weekId),
    FOREIGN KEY (eId, scId) REFERENCES mechanic (eId, scId)
);

CREATE TABLE services(
    sNo VARCHAR(10),
    sName VARCHAR(30),
    duration number not null,
    PRIMARY KEY (sNo)
);

CREATE TABLE repair(
    sNo VARCHAR(10),
    repairCat VARCHAR(30) NOT NULL,
    PRIMARY KEY (sNo),
    FOREIGN KEY (sNo) REFERENCES services (sNo)
);

CREATE TABLE maintenance(
    sNo VARCHAR(10),
    sName VARCHAR(30),
    PRIMARY KEY (sNo, sName),
    FOREIGN KEY (sNo) REFERENCES services (sNo)
);

CREATE TABLE serviceProvided(
    scId VARCHAR(10),
    sNo VARCHAR(10),
    mId integer,
    price NUMBER NOT NULL,
    PRIMARY KEY (scId, sNo, mId),
    FOREIGN KEY (scId) REFERENCES serviceCenter(scId),
    FOREIGN KEY (sNo) REFERENCES services(sNo)
);

CREATE TABLE customer(
    scId VARCHAR(10),
    cId VARCHAR(10),
    firstName VARCHAR(20) NOT NULL,
    lastName VARCHAR(20) NOT NULL,
    email varchar(30),
    phoneNo number,
    address varchar(50),
    status VARCHAR(1) NOT NULL,
    PRIMARY KEY (scId, cId),
    FOREIGN KEY (scId) REFERENCES serviceCenter(scId)
);

Create table userCustomer(
    username varchar(20),
    cId varchar(10) not null,
    scId VARCHAR(10) NOT NULL,
    primary key (username),
    FOREIGN KEY (username) REFERENCES users(username),
    Foreign Key (scId,cId) REFERENCES customer(scId, cId)
);


CREATE TABLE cars(
    vinNo VARCHAR(8),
    mId integer NOT NULL,
    year VARCHAR(4) NOT NULL,
    currMileage integer NOT NULL,
    PRIMARY KEY (vinNo),
    FOREIGN KEY (mId) REFERENCES model (mId)
);

CREATE TABLE carOwner(
    scId VARCHAR(10),
    cId VARCHAR(10),
    vinNo VARCHAR(8),
    nextMaintenance VARCHAR(1) NOT NULL,
    PRIMARY KEY (scId, cId, vinNo),
    FOREIGN KEY (scId, cId) REFERENCES customer (scId, cId),
    FOREIGN KEY (vinNo) REFERENCES cars(vinNo)
);

CREATE TABLE serviceRequest(
    invoiceId VARCHAR(10),
    totalAmount integer NOT NULL,
    status VARCHAR(1) NOT NULL,
    weekId NUMBER(2, 0),
    dayId NUMBER(1, 0),
    startSlot integer,
    endSlot integer,
    PRIMARY KEY (invoiceId),
    FOREIGN KEY (startSlot) REFERENCES slots (slotId),
    FOREIGN KEY (endSlot) REFERENCES slots (slotId),
);

CREATE TABLE requestedServices(
    invoiceId VARCHAR(10),
    scId VARCHAR(10),
    sNo VARCHAR(10),
    mId integer,
    PRIMARY KEY (invoiceId, scId, sNo, mId),
    FOREIGN KEY (invoiceId) REFERENCES serviceRequest(invoiceId),
    FOREIGN KEY (scId, sNo, mId) REFERENCES serviceProvided(scId, sNo, mId)
);

CREATE TABLE customerRequest(
    scId VARCHAR(10),
    cId VARCHAR(10),
    vinNo VARCHAR(8),
    invoiceId VARCHAR(10),
    PRIMARY KEY (scId, cId, vinNo, invoiceId),
    FOREIGN KEY (invoiceId) REFERENCES serviceRequest(invoiceId),
    FOREIGN KEY (scId, cId) REFERENCES customer (scId, cId),
    FOREIGN KEY (vinNo) REFERENCES cars(vinNo)
);

CREATE TABLE slots(
    slotId integer,
    startTime VARCHAR(8) NOT NULL,
    endTime VARCHAR(8) NOT NULL,
    PRIMARY KEY (slotId)
);

CREATE TABLE mechanicAvailability(
    eId VARCHAR(10),
    scId VARCHAR(10),
    weekId NUMBER(2, 0),
    dayId NUMBER(1, 0),
    slotId integer,
    available VARCHAR(1) NOT NULL,
    PRIMARY KEY (eId, scId, weekId, dayId, slotId),
    FOREIGN KEY (slotId) REFERENCES slots (slotId),
    FOREIGN KEY (eId, scId) REFERENCES employee (eId, scId)
);

CREATE TABLE serveRequest(
    eId VARCHAR(10),
    scId VARCHAR(10),
    invoiceId VARCHAR(10),
    PRIMARY KEY (eId, scId, invoiceId),
    FOREIGN KEY (eId, scId) REFERENCES employee (eId, scId),
    FOREIGN KEY (invoiceId) REFERENCES serviceRequest (invoiceId)
);

create table pendingRequest(
    eId VARCHAR(10),
    scId VARCHAR(10),
    requestId varchar(10),
    usereId varchar(10),
    userWeek NUMBER(2, 0),
    userDay NUMBER(1, 0),
    userStartSlot integer,
    userEndSlot integer,
    requestingMechanicName varchar(20),
    requestedWeek NUMBER(2, 0),
    requestedDay NUMBER(1, 0),
    requestedStartSlot integer,
    requestedEndSlot integer,
    PRIMARY KEY(eId, scId,requestId),
    FOREIGN KEY(eId, scId) REFERENCES employee (eId, scId)
);