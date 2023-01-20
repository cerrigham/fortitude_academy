package it.proactivity;

import it.proactivity.methods.CustomerMethod;
import it.proactivity.methods.JobDescriptionMethod;
import it.proactivity.methods.ProjectMethod;
import it.proactivity.methods.TechnologyMethod;
import it.proactivity.utility.SessionUtility;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FortitudeAcademyTest {

    @Test
    public void insertOrUpdatePositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.insertOrUpdateCustomer(session, 1l, "Luigi", "luigi@luigi.it",
                "673637", "second try"));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateInsertionPositiveTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.insertOrUpdateCustomer(session, null, "Roberto", "roberto@roberto.it",
                "673637", "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateEmptyNamePositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.insertOrUpdateCustomer(session, 2l, "", "roberto@giorgio.it",
                "673637", "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateNullNameNegativeTest() {
        Session session = SessionUtility.createSession();

        assertFalse(CustomerMethod.insertOrUpdateCustomer(session, null, null, "roberto@roberto.it",
                "673637", "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateEmptyEmailPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.insertOrUpdateCustomer(session, 2l, "roberto", "",
                "673637", "Update"));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateNullEmailNegativeTest() {
        Session session = SessionUtility.createSession();

        assertFalse(CustomerMethod.insertOrUpdateCustomer(session, null, "roberto", null,
                "673637", "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateEmptyPhoneNumberPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.insertOrUpdateCustomer(session, 2l, "Giacomo", "prova@prova.it",
                "", "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateNullPhoneNumberNegativeTest() {
        Session session = SessionUtility.createSession();

        assertFalse(CustomerMethod.insertOrUpdateCustomer(session, null, "roberto", "prova@prova.it",
                null, "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateEmptydetailPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.insertOrUpdateCustomer(session, 2l, "roberto", "prova@prova.it",
                "1111111111", ""));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateNullDetailNegativeTest() {
        Session session = SessionUtility.createSession();

        assertFalse(CustomerMethod.insertOrUpdateCustomer(session, null, "roberto", "prova@prova.it",
                "83647848", null));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteFromCustomerPositiveTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.deleteFromCustomer(session, 2l));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteFromCustomerNullIdNegativeTest() {
        Session session = SessionUtility.createSession();

        assertFalse(CustomerMethod.deleteFromCustomer(session, null));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteFromCustomerIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();

        assertFalse(CustomerMethod.deleteFromCustomer(session, 0l));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateTechnologyInsertionPositiveTest() {
        Session session = SessionUtility.createSession();
        assertTrue(TechnologyMethod.insertOrUpdateTechnology(session, null, "Java"));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateTechnologyUpdatingPositiveTest() {
        Session session = SessionUtility.createSession();
        assertTrue(TechnologyMethod.insertOrUpdateTechnology(session, 1l, "Spring"));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateTechnologyNullNameNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(TechnologyMethod.insertOrUpdateTechnology(session, null, null));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteTechnologyPositiveTest() {
        Session session = SessionUtility.createSession();
        assertTrue(TechnologyMethod.deleteFromTechnology(session, 1l));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteTechnologyNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(TechnologyMethod.deleteFromTechnology(session, null));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateProjectPositiveInsertionTest() {
        Session session = SessionUtility.createSession();
        assertTrue(ProjectMethod.insertOrUpdateProject(session, null, "Php project", "2023-01-19",
                "1112203938", 3l));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateProjectPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();
        assertTrue(ProjectMethod.insertOrUpdateProject(session, 1l, "Java", "",
                "11", 4l));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateProjectNullNameNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(ProjectMethod.insertOrUpdateProject(session, 1l, null, "",
                "11", 4l));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateProjectNullEndDateNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(ProjectMethod.insertOrUpdateProject(session, 1l, "Java", null,
                "11", 4l));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateProjectNullReportingIdNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(ProjectMethod.insertOrUpdateProject(session, 1l, "Java", "", null, 4l));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateProjectCustomerNameNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(ProjectMethod.insertOrUpdateProject(session, 1l, "java", "",
                "11", null));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteFromProjectPositiveTest() {
        Session session = SessionUtility.createSession();
        assertTrue(ProjectMethod.deleteFromProject(session, 1l));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteFromProjectNullIdNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(ProjectMethod.deleteFromProject(session, null));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteFromProjectIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(ProjectMethod.deleteFromProject(session, null));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateJobDescriptionInsertPositiveInsertionTest() {
        Session session = SessionUtility.createSession();

        assertTrue(JobDescriptionMethod.insertOrUpdateJobDescription(session, null, 2l, 2l));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateJobDescriptionInsertPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(JobDescriptionMethod.insertOrUpdateJobDescription(session, 2l, 2l, 3l));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateJobDescriptionInsertNullTechnologyNegativeTest() {
        Session session = SessionUtility.createSession();

        assertFalse(JobDescriptionMethod.insertOrUpdateJobDescription(session, null, null, 3l));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateJobDescriptionInsertNullProjectNegativeTest() {
        Session session = SessionUtility.createSession();

        assertFalse(JobDescriptionMethod.insertOrUpdateJobDescription(session, null, 2l, null));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteFromJobDescriptionPositiveTest() {
        Session session = SessionUtility.createSession();

        assertTrue(JobDescriptionMethod.deleteFromJobDescription(session, 2l));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteFromJobDescriptionNegativeTest() {
        Session session = SessionUtility.createSession();

        assertFalse(JobDescriptionMethod.deleteFromJobDescription(session, 12l));
        assertFalse(session.isOpen());
    }

}
