/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calapp;

import java.sql.Timestamp;
import java.time.*;

/**
 *
 * @author swade
 */
public class MySqlDate {
    public static Instant getGMTMySQLDate(LocalDateTime date){
       LocalDateTime ldt = date;
       OffsetDateTime odt = OffsetDateTime.now ();
       ZoneOffset zoneOffset = odt.getOffset ();
       Instant instant = ldt.toInstant(zoneOffset);
       System.err.println("Instant:"+instant.toString());
       return instant; 
    }
    
    public static ZonedDateTime getZonedDate (Timestamp ldt, ZoneId id) {
        Timestamp gmtsql = ldt;
        Instant gmt = gmtsql.toInstant();
        ZonedDateTime date = gmt.atZone(id);
        return date;
    }
    
//    public static OffsetDateTime getOffSetDateTime (Timestamp ts, ZoneOffsetId id) {
//        Timestamp sqlts = ts;
//        Instant gmt = sqlts.toInstant();
//        OffsetDateTime odt = gmt.atOffset(ZoneOffset.of(id));
//    }
//    
}
