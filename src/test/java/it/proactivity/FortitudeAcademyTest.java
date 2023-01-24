package it.proactivity;

import it.proactivity.methods.CustomerMethod;
import it.proactivity.methods.JobDescriptionMethod;
import it.proactivity.methods.ProjectMethod;
import it.proactivity.methods.TechnologyMethod;
import it.proactivity.model.Customer;
import it.proactivity.model.JobDescription;
import it.proactivity.model.Project;
import it.proactivity.model.Technology;
import it.proactivity.utility.SessionUtility;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void findCustomerFromIdPositiveTest() {
        Session session = SessionUtility.createSession();

        Customer customer = CustomerMethod.findCustomerFromId(session, 3l);
        assertTrue(customer.getId() == 3l);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromIdWithIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();

        Customer customer = CustomerMethod.findCustomerFromId(session, 0l);
        assertNull(customer);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromIdWithIdNullNegativeTest() {
        Session session = SessionUtility.createSession();

        Customer customer = CustomerMethod.findCustomerFromId(session, null);
        assertNull(customer);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologyFromIdPositiveTest() {
        Session session = SessionUtility.createSession();

        Technology technology = TechnologyMethod.findTechnologyFromId(session, 3l);
        assertTrue(technology.getId() == 3l);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologyFromIdWithIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();

        Technology technology = TechnologyMethod.findTechnologyFromId(session, 0l);
        assertNull(technology);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologyFromIdWithIdNullNegativeTest() {
        Session session = SessionUtility.createSession();

        Technology technology = TechnologyMethod.findTechnologyFromId(session, null);
        assertNull(technology);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectFromIdPositiveTest() {
        Session session = SessionUtility.createSession();

        Project project = ProjectMethod.findProjectFromId(session, 4l);
        assertNotNull(project);
        assertTrue(project.getId() == 4);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectFromIdWithIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();

        Project project = ProjectMethod.findProjectFromId(session, 0l);
        assertNull(project);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectFromIdWithIdNullNegativeTest() {
        Session session = SessionUtility.createSession();

        Project project = ProjectMethod.findProjectFromId(session, null);
        assertNull(project);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromIdPositiveTest() {
        Session session = SessionUtility.createSession();

        JobDescription jobDescription = JobDescriptionMethod.findJobDescriptionFromId(session, 4l);
        assertNotNull(jobDescription);
        assertTrue(jobDescription.getId() == 4);
        assertFalse(session.isOpen());
    }

    @Test
    public void  findJobDescriptionFromidWithIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();

        JobDescription jobDescription = JobDescriptionMethod.findJobDescriptionFromId(session, 0l);
        assertNull(jobDescription);
        assertFalse(session.isOpen());
    }

    @Test
    public void  findJobDescriptionFromidWithIdNullNegativeTest() {
        Session session = SessionUtility.createSession();

        JobDescription jobDescription = JobDescriptionMethod.findJobDescriptionFromId(session, null);
        assertNull(jobDescription);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromNamePositiveTest() {
        Session session = SessionUtility.createSession();

        List<Customer> customers = CustomerMethod.findCustomersFromName(session,"roberto");

        assertNotNull(customers);
        assertTrue(customers.get(1).getId() == 8);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromNameWithWrongNameNegativeTest() {
        Session session = SessionUtility.createSession();

        List<Customer> customers = CustomerMethod.findCustomersFromName(session, "Giovanna");

        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromNameWithNullNameNegativeTest() {
        Session session = SessionUtility.createSession();

        List<Customer> customers = CustomerMethod.findCustomersFromName(session, null);

        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromNameWithEmptyNameNegativeTest() {
        Session session = SessionUtility.createSession();

        List<Customer> customers = CustomerMethod.findCustomersFromName(session, "");

        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromNamePositiveTest() {
        Session session = SessionUtility.createSession();

        List<Project> projects = ProjectMethod.findProjectsFromName(session, "Java");

        assertNotNull(projects);
        assertTrue(projects.get(0).getId() == 6);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromNameWithNullNameNegativeTest() {
        Session session = SessionUtility.createSession();

        List<Project> projects = ProjectMethod.findProjectsFromName(session, null);

        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromNameWithEmptyNameNameNegativeTest() {
        Session session = SessionUtility.createSession();

        List<Project> projects = ProjectMethod.findProjectsFromName(session, "");

        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromNameWithWrongNameNegativeTest() {
        Session session = SessionUtility.createSession();

        List<Project> projects = ProjectMethod.findProjectsFromName(session, "Javascript");

        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologiesFromNamePositiveTest() {
        Session session = SessionUtility.createSession();

        List<Technology> technologies = TechnologyMethod.findTechnologiesFromName(session, "Postgree");

        assertNotNull(technologies);
        assertTrue(technologies.get(0).getId() == 3);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologiesFromNameWithNullNameNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Technology> technologies = TechnologyMethod.findTechnologiesFromName(session, null);

        assertNull(technologies);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologiesFromNameWithEmptyNameNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Technology> technologies = TechnologyMethod.findTechnologiesFromName(session, "");

        assertNull(technologies);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologiesFromNameWithWrongNameNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Technology> technologies = TechnologyMethod.findTechnologiesFromName(session, "Mysql");

        assertNull(technologies);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromDatePositiveTest() {
        Session session = SessionUtility.createSession();

        List<Project> projects = ProjectMethod.findProjectsFromDate(session, "2023-01-19");
        assertNotNull(projects);
        assertTrue(projects.get(0).getId() == 2);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromDateWithEmptyDateNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromDate(session, "");

        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromDateWithNullDateNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromDate(session, null);

        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromDateWithWrongDateNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromDate(session,"2023-09-21");

        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromDateWithWrongFormatDateNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromDate(session,"03-09-2023");

        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromReportingIdPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromReportingId(session,"1");
        assertNotNull(projects);
        assertTrue(projects.get(0).getId() == 6);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromreportingIdWithNullValueNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromReportingId(session,null);
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromreportingIdWithEmptyValueNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromReportingId(session,"");
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromreportingIdWithWrongValueNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromReportingId(session,"22");
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromCustomerPositiveTest() {
        Session session = SessionUtility.createSession();

        List<Project> projects = ProjectMethod.findProjectFromCustomer(session, 3l);
        assertNotNull(projects);
        assertTrue(projects.get(0).getId() == 2);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromCustomerWithCustomerIdNullNegativeTest() {
        Session session = SessionUtility.createSession();

        List<Project> projects = ProjectMethod.findProjectFromCustomer(session, null);
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromCustomerWithCustomerIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();

        List<Project> projects = ProjectMethod.findProjectFromCustomer(session, 0l);
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromEmailPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomerFromEmail(session, "prova@prova.it");
        assertNotNull(customers);
        assertTrue(customers.get(0).getId() == 5);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromEmailWithNullEmailNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomerFromEmail(session, null);
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromEmailWithEmptyEmailNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomerFromEmail(session, "");
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromEmailWithWrongEmailNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomerFromEmail(session, "ciao@ciao.it");
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromPhoneNumberPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromPhoneNumber(session, "83647848");
        assertNotNull(customers);
        assertTrue(customers.get(0).getId() == 8);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromPhoneNumberWithNullNumberNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromPhoneNumber(session, null);
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromPhoneNumberWithEmptyNumberNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromPhoneNumber(session, "");
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromPhoneNumberWithWrongNumberNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromPhoneNumber(session, "000909");
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromDetailEmptyDetailPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromDetails(session, "");

        assertNotNull(customers);
        assertTrue(customers.get(0).getId() == 5);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromDetailPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromDetails(session, "Insert");

        assertNotNull(customers);
        assertTrue(customers.get(0).getId() == 3);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromDetailNullDetailNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromDetails(session, null);

        assertNull(customers);

        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromTechnologyPositiveTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobDescriptionFromTechnology(session, 3l);
        assertNotNull(jobDescriptions);
        assertTrue(jobDescriptions.get(0).getId() == 4);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromTechnologyNullIdNegativeTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobDescriptionFromTechnology(session, null);
        assertNull(jobDescriptions);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromTechnologyIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobDescriptionFromTechnology(session, 0l);
        assertNull(jobDescriptions);
        assertFalse(session.isOpen());
    }

    @Test
    public void  findJobDescriptionFromProjectPositiveTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobDescriptionFromProject(session, 6l);
        assertNotNull(jobDescriptions);
        assertTrue(jobDescriptions.get(0).getId() == 4);
        assertFalse(session.isOpen());
    }
    @Test
    public void findJobDescriptionFromProjectNullIdNegativeTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobDescriptionFromProject(session, null);
        assertNull(jobDescriptions);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromProjectIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobDescriptionFromProject(session, 0l);
        assertNull(jobDescriptions);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromIdWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();

        Customer customer = CustomerMethod.findCustomerFromIdWithCriteria(session, 3l);
        assertNotNull(customer);
        assertTrue(customer.getId() == 3l);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromIdWithCriteriaIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();
        Customer customer = CustomerMethod.findCustomerFromIdWithCriteria(session, 0l);
        assertNull(customer);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromIdWithCriteriaIdNullNegativeTest() {
        Session session = SessionUtility.createSession();
        Customer customer = CustomerMethod.findCustomerFromIdWithCriteria(session, null);
        assertNull(customer);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescroptionFromIdWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();

        JobDescription jobDescriptionMethod = JobDescriptionMethod.findJobDescriptionFromIdWithCriteria(session, 4l);
        assertNotNull(jobDescriptionMethod);
        assertTrue(jobDescriptionMethod.getId() == 4l);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescroptionFromIdWithCriteriaIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();
        JobDescription jobDescription = JobDescriptionMethod.findJobDescriptionFromIdWithCriteria(session, 0l);
        assertNull(jobDescription);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescroptionFromIdWithCriteriaIdNullNegativeTest() {
        Session session = SessionUtility.createSession();
        JobDescription jobDescription = JobDescriptionMethod.findJobDescriptionFromIdWithCriteria(session, null);
        assertNull(jobDescription);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectFromIdWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();

        Project project = ProjectMethod.findProjectFromIdWithCriteria(session, 4l);
        assertNotNull(project);
        assertTrue(project.getId() == 4l);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectFromIdWithCriteriaIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();

        Project project = ProjectMethod.findProjectFromIdWithCriteria(session, 0l);
        assertNull(project);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectFromIdWithCriteriaIdNullNegativeTest() {
        Session session = SessionUtility.createSession();

        Project project = ProjectMethod.findProjectFromIdWithCriteria(session, null);
        assertNull(project);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologyFromIdWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();

        Technology technology = TechnologyMethod.findTechnologyFromIdWithCriteria(session, 3l);
        assertNotNull(technology);
        assertTrue(technology.getId() == 3l);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologyFromIdWithCriteriaIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();

        Technology technology = TechnologyMethod.findTechnologyFromIdWithCriteria(session, 0l);
        assertNull(technology);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologyFromIdWithCriteriaIdNullNegativeTest() {
        Session session = SessionUtility.createSession();

        Technology technology = TechnologyMethod.findTechnologyFromIdWithCriteria(session, null);
        assertNull(technology);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromNameWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();

        List<Customer> customers = CustomerMethod.findCustomerFromNameWithCriteria(session,"roberto");
        assertNotNull(customers);
        assertTrue(customers.get(0).getId() == 5l);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromNameWithCriteriaNameNullNegativeTest() {
        Session session = SessionUtility.createSession();

        List<Customer> customers = CustomerMethod.findCustomerFromNameWithCriteria(session,null);
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromNameWithCriteriaNameEmptyNegativeTest() {
        Session session = SessionUtility.createSession();

        List<Customer> customers = CustomerMethod.findCustomerFromNameWithCriteria(session,"");
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromNameWithCriteriaNameWrongNegativeTest() {
        Session session = SessionUtility.createSession();

        List<Customer> customers = CustomerMethod.findCustomerFromNameWithCriteria(session,"Claudio");
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectFromNameWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects =  ProjectMethod.findProjectFromNameWithCriteria(session,"Java");
        assertNotNull(projects);
        assertTrue(projects.get(0).getId() == 6);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectFromNameWithCriteriaNameNullNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects =  ProjectMethod.findProjectFromNameWithCriteria(session,null);
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectFromNameWithCriteriaNameEmptyNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects =  ProjectMethod.findProjectFromNameWithCriteria(session,"");
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectFromNameWithCriteriaNameWrongNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects =  ProjectMethod.findProjectFromNameWithCriteria(session,"Javascript");
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologyFromNameWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Technology> technologies = TechnologyMethod.findTechnologyFromNameWithCriteria(session, "Postgree");
        assertNotNull(technologies);
        assertTrue(technologies.get(0).getId() == 3);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologyFromNameWithCriteriaNameNullNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Technology> technologies = TechnologyMethod.findTechnologyFromNameWithCriteria(session, null);
        assertNull(technologies);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologyFromNameWithCriteriaNameEmptyNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Technology> technologies = TechnologyMethod.findTechnologyFromNameWithCriteria(session, "");
        assertNull(technologies);
        assertFalse(session.isOpen());
    }

    @Test
    public void findTechnologyFromNameWithCriteriaNameWrongNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Technology> technologies = TechnologyMethod.findTechnologyFromNameWithCriteria(session, "Mysql");
        assertNull(technologies);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromEmailWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomerFromEmailWithCriteria(session, "roberto@roberto.it");
        assertNotNull(customers);
        assertTrue(customers.get(0).getId() == 3);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromEmailWithCriteriaNullEmailPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomerFromEmailWithCriteria(session, null);
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromEmailWithCriteriaEmptyEmailPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomerFromEmailWithCriteria(session, "");
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromEmailWithCriteriaWrongEmailPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomerFromEmailWithCriteria(session, "Sbagliata@sbagliata.it");
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromPhoneNumberWithcriteriaPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromPhoneNumberWithCriteria(session, "19283746");
        assertNotNull(customers);
        assertTrue(customers.get(0).getId() == 7);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromPhoneNumberWithcriteriaNullPhoneNumberNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromPhoneNumberWithCriteria(session, null);
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromPhoneNumberWithcriteriaEmptyPhoneNumberNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromPhoneNumberWithCriteria(session, "");
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomerFromPhoneNumberWithcriteriaWrongPhoneNumberNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromPhoneNumberWithCriteria(session, "999990");
        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromDetailWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromDetailWithCriteria(session, "Insert");

        assertNotNull(customers);
        assertTrue(customers.get(0).getId() == 3);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromDetailWithCriteriaEmptyDetailPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromDetailWithCriteria(session, "");

        assertNotNull(customers);
        assertTrue(customers.get(0).getId() == 5);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromDetailWithCriteriaNullDetailNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromDetailWithCriteria(session, null);

        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findCustomersFromDetailWithCriteriaWrongDetailNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Customer> customers = CustomerMethod.findCustomersFromDetailWithCriteria(session, "prova");

        assertNull(customers);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromDateWithCriteriaPositieTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromDateWithCriteria(session,"2023-01-19");
        assertNotNull(projects);
        assertTrue(projects.get(0).getId() == 2);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromDateWithCriteriaNullDateNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromDateWithCriteria(session,null);
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromDateWithCriteriaEmptyDateNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromDateWithCriteria(session,"");
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromDateWithCriteriaWrongDateNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromDateWithCriteria(session,"2029-09-09");
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromDateWithCriteriaWrongFormatDateNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromDateWithCriteria(session,"09-09-2029");
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromreportingIdWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromreportingIdWithCriteria(session, "1112203938");
        assertNotNull(projects);
        assertTrue(projects.get(0).getId() == 2);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromreportingIdWithCriteriaNullReportingIdNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromreportingIdWithCriteria(session, null);
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromreportingIdWithCriteriaEmptyReportingIdNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromreportingIdWithCriteria(session, "");
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromreportingIdWithCriteriaWrongReportingIdNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromreportingIdWithCriteria(session, "77777");
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromCustomerWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromCustomerWithCriteria(session, 3l);
        assertNotNull(projects);
        assertTrue(projects.get(0).getId() == 2);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromCustomerWithCriteriaCustomerIdNullNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromCustomerWithCriteria(session, null);
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromCustomerWithCriteriaCustomerIdEqualToZeroNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromCustomerWithCriteria(session, 0l);
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findProjectsFromCustomerWithCriteriaCustomerIdWrongNegativeTest() {
        Session session = SessionUtility.createSession();
        List<Project> projects = ProjectMethod.findProjectsFromCustomerWithCriteria(session, 10l);
        assertNull(projects);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromTechnologyWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobdescriptionFromTechnologyWithCriteria(session,3l);
        assertNotNull(jobDescriptions);
        assertTrue(jobDescriptions.get(0).getId() == 4);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromTechnologyWithCriteriaNullTechnologyIdNegativeTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobdescriptionFromTechnologyWithCriteria(session,null);
        assertNull(jobDescriptions);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromTechnologyWithCriteriaEqualToZeroTechnologyIdNegativeTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobdescriptionFromTechnologyWithCriteria(session,0l);
        assertNull(jobDescriptions);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromTechnologyWithCriteriaWrongTechnologyIdNegativeTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobdescriptionFromTechnologyWithCriteria(session,10l);
        assertNull(jobDescriptions);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromProjectWithCriteriaPositiveTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobDescriptionsFromProjectWithCriteria(session,6l);
        assertNotNull(jobDescriptions);
        assertTrue(jobDescriptions.get(0).getId() == 4);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromProjectWithCriteriaNullProjectIdNegativeTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobDescriptionsFromProjectWithCriteria(session,null);
        assertNull(jobDescriptions);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromProjectWithCriteriaEqualToZeroProjectIdNegativeTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobDescriptionsFromProjectWithCriteria(session,0l);
        assertNull(jobDescriptions);
        assertFalse(session.isOpen());
    }

    @Test
    public void findJobDescriptionFromProjectWithCriteriaWrongProjectIdNegativeTest() {
        Session session = SessionUtility.createSession();
        List<JobDescription> jobDescriptions = JobDescriptionMethod.findJobDescriptionsFromProjectWithCriteria(session,10l);
        assertNull(jobDescriptions);
        assertFalse(session.isOpen());
    }


}
