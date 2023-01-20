package it.proactivity;

import it.proactivity.utility.SessionUtility;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static it.proactivity.utility.CompanyUtility.insertOrUpdateCompany;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FortitudeAcademyTest {
    @Test
    public void insertOrUpdateCompanyPositiveTest() {
        Session session = SessionUtility.createSession();
        assertTrue(insertOrUpdateCompany(session, null, "Prova Insert Company"));
    }

    @Test
    public void insertOrUpdateCompanyNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(insertOrUpdateCompany(null, null, "Prova Insert Company"));
    }

}
