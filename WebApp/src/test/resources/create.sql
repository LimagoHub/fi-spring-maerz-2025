drop table if exists `tbl_personen`;
CREATE TABLE `tbl_personen` (
        `id` uuid NOT NULL,
        `nachname` varchar(50) NOT NULL,
        `vorname` varchar(50) DEFAULT NULL,
        PRIMARY KEY (`id`)
) ;