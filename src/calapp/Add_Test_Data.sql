/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  swade
 * Created: Feb 11, 2017
 */

INSERT INTO `country` (`countryID`, `country`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) 
VALUES ('1', 'USA', '2017-02-11 00:00:00', 'swade', NULL, NULL), 
('2', 'Mexico', '2017-02-11 00:00:00', 'swade', NULL, NULL);

INSERT INTO `city` (`cityID`, `city`, `countryID`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) 
VALUES ('1', 'Knoxville', '1', '2017-02-11 00:00:00', 'swade', NULL, NULL),
('2', 'Indianapolis', '1', '2017-02-11 00:00:00', 'swade', NULL, NULL),
('3', 'Austin', '1', '2017-02-11 00:00:00', 'swade', NULL, NULL),
('4', 'Ft. Myers', '1', '2017-02-11 00:00:00', 'swade', NULL, NULL);

INSERT INTO `address` (`addressID`, `address`, `address2`, `cityID`, `postalCode`, `phone`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdateBy`) 
VALUES ('1', '123 Main Street', NULL, '2', '46248', '123-555-1212', '2017-02-11 00:00:00', 'swade', NULL, NULL),
('2', '1515 Duvall Street', NULL, '3', '78725', '512-555-1111', '2017-02-11 00:00:00', 'swade', NULL, NULL);

INSERT INTO `customer` (`customerID`, `customerName`, `addressID`, `active`, `createDate`, `createdBy`, `lastUpdate`, `lastUpdatedBy`) 
VALUES ('1', 'Joe Duncan', '1', '1', '2017-02-11 00:00:00', 'swade', NULL, NULL), 
('2', 'Sandy Cheeks', '2', '1', '2017-02-11 00:00:00', 'swade', NULL, NULL);


INSERT INTO `customer` (`customerID`, `customerName`, `addressID`, `active`, `createDate`, `createdBy`) 
VALUES ('99', 'ButtHead CircleJeans', '1', '1', '2017-02-11 00:00:00', 'swade');
