/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Abdella
 */
public class ControllerProjectionTest {
    
    public ControllerProjectionTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }


    /**
     * Test of calculerDate method, of class ControllerProjection.
     */
    @Test
    public void testCalculerDate() {
        System.out.println("calculerDate");
        LocalDate date = LocalDate.of(2020, 1, 20);
        LocalTime time = LocalTime.of(14, 30);
        String expResult = "2020-01-20T14:30";
        Timestamp result = ControllerProjection.calculerDate(date, time);
        assertEquals(expResult, result);
    }

}
