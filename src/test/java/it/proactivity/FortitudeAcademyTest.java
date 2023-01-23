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
    public void createOrUpdateCustomerPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.createOrUpdateCustomer(session, 6l, null, "guglielmo@guglielmo.it",
                "111111", null));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateCustomerInsertionPositiveTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.createOrUpdateCustomer(session, null, "Guglielmo", "gugl@gugl.it",
                "19283746", "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateCustomerInsertionWithoutDetailPositiveTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.createOrUpdateCustomer(session, null, "Martina", "martina@martina.it",
                "19283746", null));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateCustomerEmptyNamePositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.createOrUpdateCustomer(session, 6l, "", "prova@prova.it",
                "2222222", "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateCustomerNullNamePositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.createOrUpdateCustomer(session, 6l, null, "prova@prova.it",
                "2222222", "Insert"));
        assertFalse(session.isOpen());
    }
    @Test
    public void createOrUpdateCustomerEmptyemailPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.createOrUpdateCustomer(session, 6l, "Maurizio", "",
                "2222222", "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateCustomerNullEmailPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.createOrUpdateCustomer(session, 6l, "Maurizio", null,
                "2222222", "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateCustomerEmptyPhoneNumberPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.createOrUpdateCustomer(session, 6l, "Maurizio", "prova@prova.it",
                "", "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateCustomerNullPhoneNumberPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.createOrUpdateCustomer(session, 6l, "Maurizio", "prova@prova.it",
                null, "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateCustomerEmptyDetailPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.createOrUpdateCustomer(session, 6l, "Maurizio", "prova@prova.it",
                "283938", ""));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateCustomerNullDetailPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.createOrUpdateCustomer(session, 6l, "Maurizio", "prova@prova.it",
                "283938", null));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateCustomerNullNameNegativeInsertionTest() {
        Session session = SessionUtility.createSession();

        assertFalse(CustomerMethod.createOrUpdateCustomer(session, null, null, "roberto@roberto.it",
                "673637", "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateCustomerNullEmailNegativeInsertionTest() {
        Session session = SessionUtility.createSession();

        assertFalse(CustomerMethod.createOrUpdateCustomer(session, null, "prova", null,
                "673637", "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateCustomerNullPhoneNumberNegativeInsertionTest() {
        Session session = SessionUtility.createSession();

        assertFalse(CustomerMethod.createOrUpdateCustomer(session, null, "roberto", "prova@prova.it",
                null, "Insert"));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteFromCustomerPositiveTest() {
        Session session = SessionUtility.createSession();

        assertTrue(CustomerMethod.deleteFromCustomer(session, 4l));
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
    public void createOrUpdateTechnologyInsertionPositiveTest() {
        Session session = SessionUtility.createSession();
        assertTrue(TechnologyMethod.createOrUpdateTechnology(session, null, "Postgree"));
        assertFalse(session.isOpen());
    }

    @Test
    public void insertOrUpdateTechnologyUpdatingPositiveTest() {
        Session session = SessionUtility.createSession();
        assertTrue(TechnologyMethod.createOrUpdateTechnology(session, 1l, "Spring"));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateTechnologyNullNameNegativeInsertionTest() {
        Session session = SessionUtility.createSession();
        assertFalse(TechnologyMethod.createOrUpdateTechnology(session, null, null));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateTechnologyPositiveUpdateTest() {
        Session session = SessionUtility.createSession();
        assertTrue(TechnologyMethod.createOrUpdateTechnology(session, 2l, "JavaEE"));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateTechnologyNegativeUpdateTest() {
        Session session = SessionUtility.createSession();
        assertFalse(TechnologyMethod.createOrUpdateTechnology(session, 12l, "JavaEE"));
        assertFalse(session.isOpen());
    }
    @Test
    public void deleteTechnologyPositiveTest() {
        Session session = SessionUtility.createSession();
        assertTrue(TechnologyMethod.deleteFromTechnology(session, 2l));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteTechnologyNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(TechnologyMethod.deleteFromTechnology(session, null));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteTechnologyNegativeWithIdEqualToZeroTest() {
        Session session = SessionUtility.createSession();
        assertFalse(TechnologyMethod.deleteFromTechnology(session, 0l));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteTechnologyNegativeWithouRecordInDatabaseTest() {
        Session session = SessionUtility.createSession();
        assertFalse(TechnologyMethod.deleteFromTechnology(session, 12l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateProjectPositiveInsertionTest() {
        Session session = SessionUtility.createSession();
        assertTrue(ProjectMethod.createOrUpdateProject(session, null, "JavaEE project", "2023-01-19",
                "1112203938", 6l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateProjectWithEmptyNamePositiveUpdatingTest() {
        Session session = SessionUtility.createSession();
        assertTrue(ProjectMethod.createOrUpdateProject(session, 6l, "", "2023-01-19",
                "11", 6l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateProjectWithNullNamePositiveUpdatingTest() {
        Session session = SessionUtility.createSession();
        assertTrue(ProjectMethod.createOrUpdateProject(session, 6l, null, "2023-01-19",
                "11", 6l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateProjectWithEmptyDatePositiveUpdatingTest() {
        Session session = SessionUtility.createSession();
        assertTrue(ProjectMethod.createOrUpdateProject(session, 6l, "Java", "",
                "11", 6l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateProjectWithNullDatePositiveUpdatingTest() {
        Session session = SessionUtility.createSession();
        assertTrue(ProjectMethod.createOrUpdateProject(session, 6l, "Java", "",
                "11", 6l));
        assertFalse(session.isOpen());
    }


    @Test
    public void insertOrUpdateProjectNullNameNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(ProjectMethod.createOrUpdateProject(session, 1l, null, "",
                "11", 4l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateProjectWithEmptyReportingIdPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();
        assertTrue(ProjectMethod.createOrUpdateProject(session, 6l, "Java", "2023-02-11",
                "", 6l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateProjectWithNullReportingIdPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();
        assertTrue(ProjectMethod.createOrUpdateProject(session, 6l, "Java", "2023-02-11",
                null, 6l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateProjectWithNullCustomerPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();
        assertTrue(ProjectMethod.createOrUpdateProject(session, 6l, "Java", "2023-02-11",
                "1", null));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateProjectNegativeUpdatingTest() {
        Session session = SessionUtility.createSession();
        assertFalse(ProjectMethod.createOrUpdateProject(session, 60l, "Java", "2023-02-11",
                "1", null));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateProjectNullNameNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(ProjectMethod.createOrUpdateProject(session, null, null, "2023-02-11",
                "11", 4l));
        assertFalse(session.isOpen());
    }
    @Test
    public void createOrUpdateProjectNullEndDateNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(ProjectMethod.createOrUpdateProject(session, null, "Java", null,
                "11", 4l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateProjectNullReportingIdNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(ProjectMethod.createOrUpdateProject(session, 1l, "Java", "2023-02-11",
                null, 4l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateProjectNullCustomerIdNegativeTest() {
        Session session = SessionUtility.createSession();
        assertFalse(ProjectMethod.createOrUpdateProject(session, 1l, "Java", "2023-02-11",
                "2283", null));
        assertFalse(session.isOpen());
    }
    @Test
    public void deleteFromProjectPositiveTest() {
        Session session = SessionUtility.createSession();
        assertTrue(ProjectMethod.deleteFromProject(session, 5l));
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
        assertFalse(ProjectMethod.deleteFromProject(session, 0l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateJobDescriptionPositiveInsertionTest() {
        Session session = SessionUtility.createSession();

        assertTrue(JobDescriptionMethod.createOrUpdateJobDescription(session, null, 3l, 3l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateJobDescriptionPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(JobDescriptionMethod.createOrUpdateJobDescription(session, 3l, 2l, 6l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateJobDescriptionWithNullTechnologyPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(JobDescriptionMethod.createOrUpdateJobDescription(session, 3l, null, 6l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateJobDescriptionWithNullProjectPositiveUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertTrue(JobDescriptionMethod.createOrUpdateJobDescription(session, 3l, 3l, null));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateJobDescriptionNegativeUpdatingTest() {
        Session session = SessionUtility.createSession();

        assertFalse(JobDescriptionMethod.createOrUpdateJobDescription(session, 80l, 3l, 6l));
        assertFalse(session.isOpen());
    }
    @Test
    public void createOrUpdateJobDescriptionNullTechnologyNegativeInsertionTest() {
        Session session = SessionUtility.createSession();

        assertFalse(JobDescriptionMethod.createOrUpdateJobDescription(session, null, null, 3l));
        assertFalse(session.isOpen());
    }

    @Test
    public void createOrUpdateJobDescriptionNullProjectNegativeInsertionTest() {
        Session session = SessionUtility.createSession();

        assertFalse(JobDescriptionMethod.createOrUpdateJobDescription(session, null, 2l, null));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteFromJobDescriptionPositiveTest() {
        Session session = SessionUtility.createSession();

        assertTrue(JobDescriptionMethod.deleteFromJobDescription(session, 3l));
        assertFalse(session.isOpen());
    }

    @Test
    public void deleteFromJobDescriptionNegativeTest() {
        Session session = SessionUtility.createSession();

        assertFalse(JobDescriptionMethod.deleteFromJobDescription(session, 12l));
        assertFalse(session.isOpen());
    }

}
