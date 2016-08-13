drop table IF EXISTS category_service;
drop table IF EXISTS pol_service;
drop table IF EXISTS special_hours;
drop table IF EXISTS category;
drop table IF EXISTS service;
drop table IF EXISTS pol;
drop table IF EXISTS spb;
drop table IF EXISTS parcel_locker;
drop table IF EXISTS outstation;
drop view  IF EXISTS pol_view;

CREATE TABLE  category (
    id          integer unsigned NOT NULL AUTO_INCREMENT,
    name        varchar(45)      NOT NULL,
    description varchar(200)     NOT NULL,
    PRIMARY KEY  (id)
);

CREATE TABLE service (
    id                INTEGER UNSIGNED  NOT NULL,
    name              VARCHAR(45)       NOT NULL,
    attribute         SMALLINT UNSIGNED NOT NULL,
    parent_service_id INTEGER UNSIGNED,
    appt_rqd          CHAR ASCII        NOT NULL DEFAULT 'N',
    is_searchable     CHAR ASCII        NOT NULL DEFAULT 'Y',
    searchable_name   varchar(200)      NOT NULL,
    PRIMARY KEY (id)
);

CREATE  TABLE category_service (
    category_id     INT UNSIGNED NOT NULL ,
    service_id      INT UNSIGNED NOT NULL ,
    description     VARCHAR(200) NOT NULL,
    PRIMARY KEY (category_id, service_id, description) ,
    INDEX FK_CS_SERVICE_idx (service_id ASC) ,
    INDEX FK_CS_CATEGORY_idx (category_id ASC) ,
    CONSTRAINT FK_CS_SERVICE
    FOREIGN KEY (service_id )
    REFERENCES service (id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT FK_CS_CATEGORY
    FOREIGN KEY (category_id )
    REFERENCES category (id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE TABLE pol (
    org_id               INTEGER UNSIGNED NOT NULL,
    org_unit_type        VARCHAR(55)      NOT NULL,
    pol_name             VARCHAR(50)      NOT NULL,
    org_unit_addr_1      VARCHAR(45)      NOT NULL,
    org_unit_addr_2      VARCHAR(45)      NOT NULL,
    org_unit_addr_3      VARCHAR(45)      NOT NULL,
    suburb               VARCHAR(45)      NOT NULL,
    state                VARCHAR(45)      NOT NULL,
    post_code            INTEGER          NOT NULL,
    phone_number         VARCHAR(45)      NOT NULL,
    fax_number           VARCHAR(45)      NOT NULL,
    latitude             VARCHAR(45)      NOT NULL,
    longitude            VARCHAR(45)      NOT NULL,
    mon                  VARCHAR(45)      NOT NULL,
    opening_time_mon     TIME             NOT NULL,
    closing_time_mon     TIME             NOT NULL,
    tue                  VARCHAR(45)      NOT NULL,
    opening_time_tue     TIME             NOT NULL,
    closing_time_tue     TIME             NOT NULL,
    wed                  VARCHAR(45)      NOT NULL,
    opening_time_wed     TIME             NOT NULL,
    closing_time_wed     TIME             NOT NULL,
    thu                  VARCHAR(45)      NOT NULL,
    opening_time_thu     TIME             NOT NULL,
    closing_time_thu     TIME             NOT NULL,
    fri                  VARCHAR(45)      NOT NULL,
    opening_time_fri     TIME             NOT NULL,
    closing_time_fri     TIME             NOT NULL,
    sat                  VARCHAR(45)      NOT NULL,
    opening_time_sat     TIME             NOT NULL,
    closing_time_sat     TIME             NOT NULL,
    sun                  VARCHAR(45)      NOT NULL,
    opening_time_sun     TIME             NOT NULL,
    closing_time_sun     TIME             NOT NULL,
    cl_mon               VARCHAR(45)      NOT NULL,
    lunch_time_start_mon TIME             NOT NULL,
    lunch_time_end_mon   TIME             NOT NULL,
    cl_tue               VARCHAR(45)      NOT NULL,
    lunch_time_start_tue TIME             NOT NULL,
    lunch_time_end_tue   TIME             NOT NULL,
    cl_wed               VARCHAR(45)      NOT NULL,
    lunch_time_start_wed TIME             NOT NULL,
    lunch_time_end_wed   TIME             NOT NULL,
    cl_thu               VARCHAR(45)      NOT NULL,
    lunch_time_start_thu TIME             NOT NULL,
    lunch_time_end_thu   TIME             NOT NULL,
    cl_fri               VARCHAR(45)      NOT NULL,
    lunch_time_start_fri TIME             NOT NULL,
    lunch_time_end_fri   TIME             NOT NULL,
    cl_sat               VARCHAR(45)      NOT NULL,
    lunch_time_start_sat TIME             NOT NULL,
    lunch_time_end_sat   TIME             NOT NULL,
    cl_sun               VARCHAR(45)      NOT NULL,
    lunch_time_start_sun TIME             NOT NULL,
    lunch_time_end_sun   TIME             NOT NULL,
    hatch_open_mon       TIME             NOT NULL,
    hatch_close_mon      TIME             NOT NULL,
    hatch_open_tue       TIME             NOT NULL,
    hatch_close_tue      TIME             NOT NULL,
    hatch_open_wed       TIME             NOT NULL,
    hatch_close_wed      TIME             NOT NULL,
    hatch_open_thu       TIME             NOT NULL,
    hatch_close_thu      TIME             NOT NULL,
    hatch_open_fri       TIME             NOT NULL,
    hatch_close_fri      TIME             NOT NULL,
    hatch_open_sat       TIME             NOT NULL,
    hatch_close_sat      TIME             NOT NULL,
    hatch_open_sun       TIME             NOT NULL,
    hatch_close_sun      TIME             NOT NULL,
    hatch_desc           VARCHAR(255)     NOT NULL,
    PRIMARY KEY (org_id)
);

CREATE TABLE special_hours (
    org_id     INTEGER UNSIGNED NOT NULL,
    start_date       DATE NOT NULL,
    end_date         DATE NOT NULL,
    opening_time_mon TIME,
    closing_time_mon TIME,
    opening_time_tue TIME,
    closing_time_tue TIME,
    opening_time_wed TIME,
    closing_time_wed TIME,
    opening_time_thu TIME,
    closing_time_thu TIME,
    opening_time_fri TIME,
    closing_time_fri TIME,
    opening_time_sat TIME,
    closing_time_sat TIME,
    opening_time_sun TIME,
    closing_time_sun TIME,
    lunch_time_start_mon TIME,
    lunch_time_end_mon TIME,
    lunch_time_start_tue TIME,
    lunch_time_end_tue TIME,
    lunch_time_start_wed TIME,
    lunch_time_end_wed TIME,
    lunch_time_start_thu TIME,
    lunch_time_end_thu TIME,
    lunch_time_start_fri TIME,
    lunch_time_end_fri TIME,
    lunch_time_start_sat TIME,
    lunch_time_end_sat TIME,
    lunch_time_start_sun TIME,
    lunch_time_end_sun TIME,
    PRIMARY KEY (Org_ID, start_date, end_date)
);


CREATE  TABLE pol_service (
    org_id     INT UNSIGNED NOT NULL ,
    service_id INT UNSIGNED NOT NULL ,
    PRIMARY KEY (org_id, service_id) ,
    INDEX FK_PS_SERVICE_idx (service_id ASC) ,
    INDEX FK_PS_POL_idx (org_id ASC) ,
    CONSTRAINT FK_PS_SERVICE
    FOREIGN KEY (service_id )
    REFERENCES service (id )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    CONSTRAINT FK_PS_POL
    FOREIGN KEY (org_id )
    REFERENCES pol (Org_ID )
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE TABLE spb (
    UUID          INTEGER UNSIGNED NOT NULL,
    ID_CODE       VARCHAR(45) NOT NULL,
    WCC           VARCHAR(45) NOT NULL,
    AP_CODE       VARCHAR(45) NOT NULL,
    AP_FACILITY   VARCHAR(100) NOT NULL,
    ADDRESS1      VARCHAR(200) NOT NULL,
    ADDRESS2      VARCHAR(200) NOT NULL,
    SUBURB        VARCHAR(45) NOT NULL,
    STATE         VARCHAR(45) NOT NULL,
    POSTCODE      INTEGER UNSIGNED NOT NULL,
    Latitude      VARCHAR(45) NOT NULL,
    Longitude     VARCHAR(45) NOT NULL,
    BOX           VARCHAR(45) NOT NULL,
    ACTIVE        VARCHAR(45) NOT NULL,
    NOT24HRACCESS VARCHAR(45) NOT NULL,
    RESULT        VARCHAR(100) NOT NULL,
    Datestamp     DATETIME,
    OTHER_CODE    VARCHAR(45) NOT NULL,
    TIS_CODE      VARCHAR(45) NOT NULL,
    BOX_TYPE      VARCHAR(45) NOT NULL,
    COMMENTS      VARCHAR(200) NOT NULL,
    NOTES         VARCHAR(200) NOT NULL,
    PRIMARY KEY (UUID)
);

CREATE TABLE parcel_locker (
    Org_ID        INTEGER UNSIGNED NOT NULL,
    Org_Name      VARCHAR(100) NOT NULL,
    Comments      VARCHAR(200) NOT NULL,
    Comments_2    VARCHAR(200) NOT NULL,
    Directions    VARCHAR(200) NOT NULL,
    Org_Type_Code VARCHAR(45) NOT NULL,
    Address_2     VARCHAR(200) NOT NULL,
    Address_3     VARCHAR(200) NOT NULL,
    SUBURB        VARCHAR(45) NOT NULL,
    STATE         VARCHAR(45) NOT NULL,
    POSTCODE      INTEGER UNSIGNED NOT NULL,
    Latitude      VARCHAR(45) NOT NULL,
    Longitude     VARCHAR(45) NOT NULL,
    DPID          VARCHAR(8) NOT NULL,
    PRIMARY KEY (Org_ID)
);

CREATE TABLE outstation (
    WCID          INTEGER UNSIGNED NOT NULL,
    Description   VARCHAR(200) NOT NULL,
    Parent_Flag   CHAR NOT NULL,
    ControlWCC    INTEGER UNSIGNED NOT NULL,
    Name          VARCHAR(200) NOT NULL,
    Address_1     VARCHAR(200) NOT NULL,
    Address_2     VARCHAR(200) NOT NULL,
    Address_3     VARCHAR(200) NOT NULL,
    Suburb        VARCHAR(45) NOT NULL,
    State         VARCHAR(45) NOT NULL,
    Postcode      INTEGER UNSIGNED NOT NULL,
    Latitude      VARCHAR(45) NOT NULL,
    Longitude     VARCHAR(45) NOT NULL,
    POB_available CHAR NOT NULL,
    POB_installed CHAR NOT NULL,
    PRIMARY KEY (WCID)
);

create view pol_view as
    select 'RETAIL' as type,
           Org_ID as unique_id,
           Org_Unit_Type as org_unit_type,
           pol_name as name,
           ORG_UNIT_ADDR_1 as address1,
           ORG_UNIT_ADDR_2 as address2,
           ORG_UNIT_ADDR_3 as address3,
           Suburb as suburb,
           State as state,
           Post_Code as postcode,
           Phone_Number as phone_number,
           Fax_Number as fax_number,
           Latitude as latitude,
           Longitude as longitude,
           null as ID_CODE,
           null as WCC,
           null as AP_CODE,
           null as AP_FACILITY,
           null as BOX,
           null as ACTIVE,
           null as NOT24HRACCESS,
           null as RESULT,
           null as OTHER_CODE,
           null as TIS_CODE,
           null as BOX_TYPE,
           null as comments,
           null as notes,
           null as comments_2,
           null as directions,
           null as org_type_code,
           closing_time_mon,
           opening_time_mon,
           closing_time_tue,
           opening_time_tue,
           opening_time_wed,
           closing_time_wed,
           opening_time_thu,
           closing_time_thu,
           opening_time_fri,
           closing_time_fri,
           opening_time_sat,
           closing_time_sat,
           opening_time_sun,
           closing_time_sun,
           cl_mon as is_closed_mon,
           lunch_time_start_mon,
           lunch_time_end_mon,
           cl_tue as is_closed_tue,
           lunch_time_start_tue,
           lunch_time_end_tue,
           cl_wed as is_closed_wed,
           lunch_time_start_wed,
           lunch_time_end_wed,
           cl_thu as is_closed_thu,
           lunch_time_start_thu,
           lunch_time_end_thu,
           cl_fri as is_closed_fri,
           lunch_time_start_fri,
           lunch_time_end_fri,
           cl_sat as is_closed_sat,
           lunch_time_start_sat,
           lunch_time_end_sat,
           cl_sun as is_closed_sun,
           lunch_time_start_sun,
           lunch_time_end_sun,
           null as po_box_available
    FROM pol
    UNION
    select 'SPB' as type,
           UUID as unique_id,
           null as org_unit_type,
           null as name,
           ADDRESS1 as address1,
           ADDRESS2 as address2,
           null as address3,
           SUBURB as suburb,
           STATE as state,
           POSTCODE as postcode,
           null as phone_number,
           null as fax_number,
           Latitude as latitude,
           Longitude as longitude,
           ID_CODE,
           WCC,
           AP_CODE,
           AP_FACILITY,
           BOX,
           ACTIVE,
           NOT24HRACCESS,
           RESULT,
           OTHER_CODE,
           TIS_CODE,
           BOX_TYPE,
           COMMENTS as comments,
           NOTES as notes,
           null as comments_2,
           null as directions,
           null as org_type_code,
           null as opening_time_mon,
           null as closing_time_mon,
           null as opening_time_tue,
           null as closing_time_tue,
           null as opening_time_wed,
           null as closing_time_wed,
           null as opening_time_thu,
           null as closing_time_thu,
           null as opening_time_fri,
           null as closing_time_fri,
           null as opening_time_sat,
           null as closing_time_sat,
           null as opening_time_sun,
           null as closing_time_sun,
           null as is_closed_mon,
           null as lunch_time_start_mon,
           null as lunch_time_end_mon,
           null as is_closed_tue,
           null as lunch_time_start_tue,
           null as lunch_time_end_tue,
           null as is_closed_wed,
           null as lunch_time_start_wed,
           null as lunch_time_end_wed,
           null as is_closed_thu,
           null as lunch_time_start_thu,
           null as lunch_time_end_thu,
           null as is_closed_fri,
           null as lunch_time_start_fri,
           null as lunch_time_end_fri,
           null as is_closed_sat,
           null as lunch_time_start_sat,
           null as lunch_time_end_sat,
           null as is_closed_sun,
           null as lunch_time_start_sun,
           null as lunch_time_end_sun,
           null as po_box_available
    FROM spb
    UNION
    select 'PARCEL_LOCKER' as type,
           Org_ID as unique_id,
           null as org_unit_type,
           Org_Name as name,
           null as address1,
           Address_2 as address2,
           Address_3 as address3,
           SUBURB as suburb,
           STATE as state,
           POSTCODE as postcode,
           null as phone_number,
           null as fax_number,
           Latitude as latitude,
           Longitude as longitude,
           null as ID_CODE,
           null as WCC,
           null as AP_CODE,
           null as AP_FACILITY,
           null as BOX,
           null as ACTIVE,
           null as NOT24HRACCESS,
           null as RESULT,
           null as OTHER_CODE,
           null as TIS_CODE,
           null as BOX_TYPE,
           COMMENTS as comments,
           null as notes,
           Comments_2 as comments_2,
           Directions as directions,
           Org_Type_Code as org_type_code,
           null as opening_time_mon,
           null as closing_time_mon,
           null as opening_time_tue,
           null as closing_time_tue,
           null as opening_time_wed,
           null as closing_time_wed,
           null as opening_time_thu,
           null as closing_time_thu,
           null as opening_time_fri,
           null as closing_time_fri,
           null as opening_time_sat,
           null as closing_time_sat,
           null as opening_time_sun,
           null as closing_time_sun,
           null as is_closed_mon,
           null as lunch_time_start_mon,
           null as lunch_time_end_mon,
           null as is_closed_tue,
           null as lunch_time_start_tue,
           null as lunch_time_end_tue,
           null as is_closed_wed,
           null as lunch_time_start_wed,
           null as lunch_time_end_wed,
           null as is_closed_thu,
           null as lunch_time_start_thu,
           null as lunch_time_end_thu,
           null as is_closed_fri,
           null as lunch_time_start_fri,
           null as lunch_time_end_fri,
           null as is_closed_sat,
           null as lunch_time_start_sat,
           null as lunch_time_end_sat,
           null as is_closed_sun,
           null as lunch_time_start_sun,
           null as lunch_time_end_sun,
           null as po_box_available
    FROM parcel_locker
    UNION
    select 'OUTSTATION' as type,
           WCID as unique_id,
           null as org_unit_type,
           Name as name,
           Address_1 as address1,
           Address_2 as address2,
           Address_3 as address3,
           Suburb as suburb,
           State as state,
           Postcode as postcode,
           null as phone_number,
           null as fax_number,
           Latitude as latitude,
           Longitude as longitude,
           null as ID_CODE,
           null as WCC,
           null as AP_CODE,
           null as AP_FACILITY,
           null as BOX,
           null as ACTIVE,
           null as NOT24HRACCESS,
           null as RESULT,
           null as OTHER_CODE,
           null as TIS_CODE,
           null as BOX_TYPE,
           Description as comments,
           null as notes,
           null as comments_2,
           null as directions,
           null as org_type_code,
           null as opening_time_mon,
           null as closing_time_mon,
           null as opening_time_tue,
           null as closing_time_tue,
           null as opening_time_wed,
           null as closing_time_wed,
           null as opening_time_thu,
           null as closing_time_thu,
           null as opening_time_fri,
           null as closing_time_fri,
           null as opening_time_sat,
           null as closing_time_sat,
           null as opening_time_sun,
           null as closing_time_sun,
           null as is_closed_mon,
           null as lunch_time_start_mon,
           null as lunch_time_end_mon,
           null as is_closed_tue,
           null as lunch_time_start_tue,
           null as lunch_time_end_tue,
           null as is_closed_wed,
           null as lunch_time_start_wed,
           null as lunch_time_end_wed,
           null as is_closed_thu,
           null as lunch_time_start_thu,
           null as lunch_time_end_thu,
           null as is_closed_fri,
           null as lunch_time_start_fri,
           null as lunch_time_end_fri,
           null as is_closed_sat,
           null as lunch_time_start_sat,
           null as lunch_time_end_sat,
           null as is_closed_sun,
           null as lunch_time_start_sun,
           null as lunch_time_end_sun,
           POB_available as po_box_available
    FROM outstation;
